package it.training.scala.todo

case class ToDo(id: Long, description: String, dueDate: String, var completed: Boolean=false)
