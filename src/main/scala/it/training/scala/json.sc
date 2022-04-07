import io.circe.generic.auto._
import io.circe.parser._

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
