import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._

/*
dato formato json:

{
  "id": 1,
  "firstName": "Mario",
  "lastName": "Cartia",
  "email": "mario.cartia@gmail.com"
}
 */

val jsonString =
  """
    |[{
    |  "id": "1",
    |  "firstName": "Mario",
    |  "lastName": "Cartia",
    |  "contacts" : {
    |               "phoneNumber" : "+39 320 332211",
    |               "email": "mario.cartia@gmail.com"
    |               }
    |},
    |{
    |  "id": "2",
    |  "firstName": "Giuseppe",
    |  "lastName": "Rossi",
    |  "contacts" : {
    |               "phoneNumber" : "+39 320 332211",
    |               "email": "mario.cartia@gmail.com"
    |               }
    |}]
    |""".stripMargin

case class Contact(phoneNumber: Option[String], email: Option[String])
case class Person(id: Long,
                  firstName: String,
                  lastName: String,
                  contacts: Contact)

val decodeResult = decode[List[Person]](jsonString)

decodeResult match {
  case Left(err) => {
    println(s"Error: $err")
  }
  case Right(personList) => {
    personList.foreach( person =>
    println(
      s"""
         |Person
         |------
         |id: ${person.id}
         |firstName: ${person.firstName}
         |lastName: ${person.lastName}
         |phoneNumber: ${person.contacts.phoneNumber.getOrElse("undefined")}
         |email: ${person.contacts.email.getOrElse("undefined")}
         |""".stripMargin)
    )
  }
}

/*val decodeResult = decode[List[Map[String,String]]](jsonString)

decodeResult match {
  case Left(err) => {
    println(s"Error: $err")
  }
  case Right(pList) => pList.foreach( pMap => {
    println(s"Person\n-----")
    pMap.foreach(p => {
      println(s"${p._1}: ${p._2}")
    })
    println("")
  })
}*/

val personList = List(
  Person(1,
        "Mario",
        "Cartia",
        Contact(
            Some("+39 321 345632"),
            Some("mario.cartia@gmail.com")
        )),
  Person(2,
    "Giuseppe",
    "Rossi",
    Contact(
      Some("+39 321 345688"),
      Some("g.rossi@gmail.com")
    )),
)

val pListJson = personList.asJson

println(pListJson)
