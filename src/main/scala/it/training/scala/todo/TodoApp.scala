package it.training.scala.todo

import scala.collection.mutable

object TodoApp {

  def main(args: Array[String]): Unit = {
    var lastCmd = ""

    val todos = mutable.Map[Long,ToDo]()
    val todoCmds = new TodoCmds(todos)

    while (lastCmd != "quit") {
      lastCmd = scala.io.StdIn.readLine("cmd > ")
      try {
        val cmd = lastCmd.split(" ").head
        val args = lastCmd.split(" ").slice(1, lastCmd.length).toList

        var found = false

        todoCmds
          .getClass
          .getDeclaredMethods
          .foreach( m => if (m.getName==cmd) {
            m.invoke(todoCmds, args)
            found=true
          })

        if (!found) throw new Exception("Invalid command")
      } catch {
        case e: Exception => println(s"Error: ${e.getMessage}")
      }
    }

  }

}
