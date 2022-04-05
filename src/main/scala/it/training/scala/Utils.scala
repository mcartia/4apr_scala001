package it.training.scala

object Utils {

  def sum(a: Int, b: Int): Int = {
    println(s"Executing sum $a + $b...")
    a+b
  }

  def sample(firstName: String, lastName: String)(optVal: Int): Unit = {
    println(s"Passed arguments: $firstName, $lastName, $optVal")
  }

  def withDefaults(firstName: String, lastName: String, optVal: Int=0): Unit = {
    println(s"Passed arguments: $firstName, $lastName, $optVal")
  }

  def withOptional(firstName: String, lastName: String, optVal: Option[Int]=None): Unit = {
    optVal match {
      case None => {
        println("Funzione invocata con optVal assente")
        println(s"$firstName $lastName")
      }
      case Some(a) => {
        println("Funzione invocata con optVal valorizzato")
        println(s"$firstName $lastName $a")
      }
    }
  }

}
