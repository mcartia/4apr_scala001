package it.training.scala.todo

import scala.collection.mutable

class TodoCmds(todos: mutable.Map[Long,ToDo]) {

  def help(args: List[String]=List.empty): Boolean = {
    this.getClass.getDeclaredMethods.filter(m=> ! m.getName.contains("$") ).foreach( m => println(s"- ${m.getName}"))
    true
  }

  def add(args: List[String]=List.empty): Boolean = {
    if (args.length==3) todos.addOne(args(0).toInt,ToDo(args(0).trim.toInt,args(1).trim,args(2).trim))
    true
  }

  def remove(args: List[String]=List.empty): Boolean = {
    true
  }

  def list(args: List[String]=List.empty): Boolean = {
    todos.foreach( todo => {
      println(
        s"""TODO
           |----
           |id:          ${todo._1}
           |description: ${todo._2.description}
           |due date:    ${todo._2.dueDate}
           |completed:   ${todo._2.completed}
           |""".stripMargin)
    })
    true
  }

  def completed(args: List[String]=List.empty): Boolean = {
    if (args.length>=1) todos.get(args.head.toInt).get.completed = true
    true
  }

  def save(args: List[String]=List.empty): Boolean = {
    true
  }

  def load(args: List[String]=List.empty): Boolean = {
    true
  }

  def populate(args: List[String]=List.empty): Boolean = {
    if (todos.size==0) {
      println("Creating sample entries...")
      todos.addOne(1, ToDo(1, "pagare assicurazione auto", "22/05/2022"))
      todos.addOne(2, ToDo(1, "pagare bolletta luce", "13/06/2022"))
      todos.addOne(3, ToDo(1, "andare in palestra", "04/06/2022"))
    } else {
      println("Already populated!")
    }
    list()
    true
  }
}
