package it.training.scala

import scala.util.Random

object Exercise1 extends App {

  var lastCmd = ""

  while (lastCmd != "quit") {
    lastCmd = scala.io.StdIn.readLine("run exercise> ")

    lastCmd match {
      case "1" => exercise1()
      case "2" => exercise2()
      case "3" => exercise3()
      case "4" => exercise4()
      case "5" => exercise5()
      case "6" => exercise6()
      case "7" => exercise7()
      case "8" => exercise8()
      case "9" => exercise9()
      case "10" => exercise10()
      case "quit" => println("Exiting...")
      case _ => println("Not implemented!")
    }
  }

  //1. Write a program that prints ‘Hello World’ to the screen.
  def exercise1(): Unit = {
    println("Hello World")
  }

  //2 .Write a program that asks the user for their name and greets them with their name.
  def exercise2(): Unit = {
    val name = scala.io.StdIn.readLine("Write your name: ")
    println(s"Hello, $name")
  }

  // 3. Modify the previous program such that only the users Alice and Bob are greeted with their names.
  def exercise3(): Unit = {
    val name = scala.io.StdIn.readLine("Write your name: ")
    val allowedNames = List("Alice", "Bob")

    if (allowedNames.contains(name)) println(s"Hello, $name")
    else println("...")
  }

  // 4. Write a program that asks the user for a number n and prints the sum of the numbers 1 to n
  def exercise4(): Unit = {
    try {
      val number = scala.io.StdIn.readLine("Write a number: ").toInt
      println(s"Summation from 1 to $number = " + (1 to number).foldLeft(0)(_ + _))
    } catch {
      case _ => println("Invalid number!")
    }
  }

  // 5. Modify the previous program such that only multiples of three or five are considered in the sum, e.g. 3, 5, 6, 9, 10, 12, 15 for n=17
  def exercise5(): Unit = {
    try {
      val number = scala.io.StdIn.readLine("Write a number: ").toInt
      println(s"Summation from 1 to $number = " + (1 to number)
        .filter(x => x % 3 == 0 || x % 5 == 0)
        .foldLeft(0)(_ + _))
    } catch {
      case _ => println("Invalid number!")
    }
  }

  // 6. Write a program that asks the user for a number n and gives them the possibility to choose between computing the sum and computing the product of 1,…,n.
  def exercise6(): Unit = {
    try {
      val number = scala.io.StdIn.readLine("Write a number: ").toInt
      val op = scala.io.StdIn.readLine("Choose sum or multiplication (sum/mult): ")

      op match {
        case "sum" => {
          println(s"Summation from 1 to $number = " + (1 to number).foldLeft(0)(_ + _))
        }
        case "mult" => {
          println(s"Multiplication from 1 to $number = " + (1 to number).foldLeft(1)(_ * _))
        }
        case _ => println("Invalid operation!")
      }
    } catch {
      case _ => println("Invalid number!")
    }
  }

  // 7. Write a program that that asks the user for a number n and prints first n prime numbers.
  def exercise7(): Unit = {
    try {
      val number = scala.io.StdIn.readLine("Write a number: ").toInt
      (1 to number).foreach( x => if (x>1 && !((2 until x-1) exists (x % _ == 0))) println(s"$x ") )
    } catch {
      case _ => println("Invalid number!")
    }
  }

  // 8. Write a guessing game where the user has to guess a secret number. After every guess the program tells the user whether their number was too large or too small. At the end the number of tries needed should be printed. It counts only as one try if they input the same number multiple times consecutively.
  def exercise8(): Unit = {
    val secretNumber = new Random().nextInt(100)
    println(s"secret: $secretNumber")
    var times = 1
    var lastTry = -1
    var found = false
    while (!found) {
      try {
        val number = scala.io.StdIn.readLine("Try to guess: ").toInt
        if (number==secretNumber) found=true
        else {
          println(s"You didn't guess... try again using a ${if (number<secretNumber) "HIGHER" else "LOWER"} number")
          if (lastTry!=number) times+=1
          lastTry = number
        }
      } catch {
        case _ => println("Invalid number!")
      }
    }
    println(s"Guessed it in $times attempts!")
  }

  // 9. Write a function that tests whether a string is a palindrome
  def exercise9(): Unit = {
    val str = scala.io.StdIn.readLine("Write a string: ")
    println( if (str==str.reverse) "Is palindrome! :-)" else "Is NOT palindrome! :-(" )
  }

  //10. Write a function that takes a list of strings an prints them, one per line, in a rectangular frame. For example the list ["Hello", "World", "in", "a", "frame"] gets printed as:
  /*
  *********
  * Hello *
  * World *
  * in    *
  * a     *
  * frame *
  *********
  */
  def exercise10(): Unit = {
    val text = scala.io.StdIn.readLine("Write a text: ")
    // sample input: hello world lorem ipsum blablablablabla aeiou ae

    val words = text.split(" ")
    // words: ["hello","world","lorem","ipsum","blablablablabla","aeiou","ae"]

    val maxLen = words.map(_.length).foldLeft(1)((x,y)=> if (x>y) x else y)
    // words.map(_.length): [5,5,5,5,15,5,2]
    // .foldLeft(1)((x,y)=> if (x>y) x else y): maxlen = 15

    (1 to maxLen+2).foreach(x=>print("*"))
    println("")
    words.foreach(word=>println(s"*${word.padTo(maxLen," ").mkString("")}*"))
    (1 to maxLen+2).foreach(x=>print("*"))
    println("")
  }

}
