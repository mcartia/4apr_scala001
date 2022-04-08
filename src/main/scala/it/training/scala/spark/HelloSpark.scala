package it.training.scala.spark

import com.github.javafaker.Faker
import org.apache.log4j.{BasicConfigurator, Level, Logger}
import org.apache.spark.sql.SparkSession

object HelloSpark {

  def main(args: Array[String]): Unit = {
    BasicConfigurator.configure()
    Logger.getRootLogger.setLevel(Level.WARN)

    val spark = SparkSession
      .builder
      .master("local[*]")
      .appName("Simple Application")
      .getOrCreate()
    val sc = spark.sparkContext

    val myList = (1 to 10).toList
    //Spark Core -> RDD
    val myRdd = sc.parallelize(myList, 4)

    /*println("Executing without parallelism...")
    spark.time({
    myList
      .map( x => verySlowOperation(x))
    })

    println("Executing with parallelism: 4")
    spark.time({
    myRdd
      .map( x => verySlowOperation(x)).collect
    })*/

    var pList = generatePeople(100)

    val peopleRdd = sc.parallelize(pList)

    //Spark SQL -> DataFrame
    import spark.implicits._
    val peopleDF = peopleRdd.toDF()

    peopleDF.show(truncate = false)
    peopleDF.createOrReplaceTempView("people")

    val usersByCountry = spark.sql(
      """
        |select country,count(*) as num_users from people
        |   group by country
        |   order by num_users desc
        |""".stripMargin)

    usersByCountry.repartition(1).write.format("json").save("/tmp/spark_out001")

    var lastCmd = ""
    while (lastCmd != "quit") {
      try {
        lastCmd = scala.io.StdIn.readLine("cmd> ")

        spark.sql(lastCmd).show
      } catch {
        case _ => println("Error!")
      }

    }

    spark.stop()
  }

  def verySlowOperation(x: Int): Int = {
    Thread.sleep(2000)
    x * 2
  }

  def generatePeople(howMany: Int): List[People] = {
    val faker = new Faker
    (1 to howMany).map(x => {
      val id = x
      val firstName = faker.name().firstName()
      val lastName = faker.name().lastName()
      val email = s"$firstName.$lastName@${faker.internet().domainName()}".toLowerCase
      val country = faker.country().name()

      People(id, firstName, lastName, email, country)
    }).toList
  }
}

case class People(id: Long, firstName: String, lastName: String, email: String, country: String)
