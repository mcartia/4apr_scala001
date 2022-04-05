package it.training.scala

class Person(id: Long, firstName: String, lastName: String, email: String) {

  def printFullName(): Unit = {
    println(s"$firstName $lastName")
  }

  def getFullName(): String = {
    s"$firstName $lastName"
  }

}
