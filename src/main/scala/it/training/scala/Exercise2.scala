package it.training.scala

import scala.collection.mutable

object Exercise2 extends App {

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

  //1. Write a function that returns the largest element in a list.
  def exercise1(): Unit = {
    try {
      val numbers = scala.io.StdIn.readLine("Write a list of numbers: ")
      val numberList = numbers.split(" ").map(_.toInt)
      println("Largest element is: " + numberList.foldLeft(0)((x, y) => if (x > y) x else y))
    } catch {
      case _ => println("Invalid number!")
    }
  }

  //2 Write a custom function that reverses a list.
  def exercise2(): Unit = {
    try {
      val words = scala.io.StdIn.readLine("Write words: ")
      val wordList = words.split(" ")
      val reversed = wordList.foldRight("")((x,y) => {s"$y $x"})
      //println("Reversed: " + wordList.reverse.toList.mkString(" "))
      println(s"Reversed: $reversed")
    } catch {
      case _ => println("Error!")
    }
  }

  // 3. Write a function that returns the elements on odd positions in a list.
  def exercise3(): Unit = {
    try {
      val words = scala.io.StdIn.readLine("Write words: ")
      val wordList = words.split(" ")
      println("Odd elements: " + wordList.zip(1 to wordList.length).filter(x => x._2 % 2 != 0).map(_._1).mkString(" "))
    } catch {
      case _ => println("Error!")
    }
  }

  // 4. Write three functions that compute the sum of the numbers in a list: using a for-loop, a while-loop and recursion.
  def exercise4(): Unit = {
    try {
      val numbers = scala.io.StdIn.readLine("Write a list of numbers: ")
      val numberList = numbers.split(" ").map(_.toInt).toList

      var counter = 0
      for (i <- numberList) counter += i
      println(s"Sum (for-loop) = $counter")

      counter = 0
      var idx = 0
      while (idx < numberList.length) {
        counter += numberList(idx)
        idx += 1
      }
      println(s"Sum (while-loop) = $counter")

      println("Sum (recursive) = " + recSum(numberList))

    } catch {
      case _ => println("Invalid number!")
    }
  }

  // 5. Write a custom function that concatenates two lists. [a,b,c], [1,2,3] → [a,b,c,1,2,3]
  def exercise5(): Unit = {
    val words1 = scala.io.StdIn.readLine("Insert list 1: ").split(" ")
    val words2 = scala.io.StdIn.readLine("Insert list 2: ").split(" ")
    println("Concatedated list: "+words1.concat(words2).mkString(" "))
  }

  // 6. Write a custom function that combines two lists by alternatingly taking elements, e.g. [a,b,c], [1,2,3] → [a,1,b,2,c,3].
  def exercise6(): Unit = {
    val words1 = scala.io.StdIn.readLine("Insert list 1: ").split(" ")
    val words2 = scala.io.StdIn.readLine("Insert list 2: ").split(" ")

    val newList = new mutable.ListBuffer[String]

    words1.zip(words2).foreach(x=>{
      newList.addOne(x._1)
      newList.addOne(x._2)
    })
    println("New list: "+newList.mkString(" "))
  }

  // 7. Write a function that merges two sorted lists into a new sorted list. [1,4,6],[2,3,5]
  def exercise7(): Unit = {
    try {
      val numbers1 = scala.io.StdIn.readLine("Write a list of sorted numbers (1): ")
      val numberList1 = numbers1.split(" ").map(_.toInt).toList
      val numbers2 = scala.io.StdIn.readLine("Write a list of sorted numbers (2): ")
      val numberList2 = numbers2.split(" ").map(_.toInt).toList

      val newList = new mutable.ListBuffer[Int]

      numberList1.zip(numberList2).foreach(x=>{
        if (x._1>x._2) {
          newList.addOne(x._2)
          newList.addOne(x._1)
        } else {
          newList.addOne(x._1)
          newList.addOne(x._2)
        }
      })
      println("New (sorted) list: "+newList.mkString(" "))
    } catch {
      case _ => println("Invalid number!")
    }
  }

  // 8. Write a function that takes a number and returns a list of its digits. So for 2342 it should return [2,3,4,2].
  def exercise8(): Unit = {
    try {
      val number = scala.io.StdIn.readLine("Write a number with a lot of digits: ").toInt
      println(number.toString.toList.mkString(","))
    } catch {
      case _ => println("Invalid number!")
    }
  }

  //9. Implement the following sorting algorithm: Merge sort (iterative)
  //   https://en.wikipedia.org/wiki/Merge_sort
  def exercise9(): Unit = {
    //TODO
  }

  //10. Implement the following sorting algorithm: Quick sort (recursive)
  //    https://en.wikipedia.org/wiki/Quicksort
  def exercise10(): Unit = {
    //TODO
  }

  def recSum(nList: List[Int]): Int = {
    if (nList.length == 1) nList(0)
    else nList(0) + recSum(nList.slice(1, nList.length))
  }

  def merge(nList: List[Int], l: Int, m: Int, r: Int): List[Int] = {
    // Find sizes of two subarrays to be merged
    val n1 = m - l + 1
    val n2 = r - m

    /* Create temp arrays */
    val tmpLeft = Array.ofDim[Int](n1)
    val tmpRight = Array.ofDim[Int](n2)

    val arr = Array.ofDim[Int](nList.length)

    /* Copy data to temp arrays */
    (0 to n1 - 1).foreach(x => tmpLeft(x) = nList(l + x))
    (0 to n2 - 1).foreach(x => tmpRight(x) = nList(m + 1 + x))

    /* Merge the temp arrays */
    // Initial indexes of first and second subarrays
    var i = 0
    var j = 0;

    var k = l
    while(i < n1 && j < n2) {
      if (tmpLeft(i) <= tmpRight(j)) {
        arr(k) = tmpLeft(i);
        i+=1;
      }
      else {
        arr(k) = tmpRight(j);
        j+=1;
      }
      k+=1;
    }

    /* Copy remaining elements of L[] if any */
    while (i < n1) {
      arr(k) = tmpLeft(i)
      i+=1
      k+=1
    }

    /* Copy remaining elements of R[] if any */
    while (j < n2) {
      arr(k) = tmpRight(j)
      j+=1
      k+=1
    }

    arr.toList
  }

  def sort(nList: List[Int], l: Int, r: Int): List[Int] = {
    var sorted = List[Int]().empty
    if (l < r) {

      // Find the middle point
      val m = l + (r - l) / 2

      val nList1 = nList.slice(l,m+1).sorted
      val nList2 = nList.slice(m+1,r+1).sorted

      // Merge the sorted halves
      sorted = merge(nList1.concat(nList2), l, m, r);
    }
    sorted
  }

}
