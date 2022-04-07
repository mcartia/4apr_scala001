
val myOpt1: Option[String] = Some("Mario")
val myOpt2: Option[String] = None

val optList = List(myOpt1, myOpt2)

/*
optList.foreach( opt => {
  opt match {
    case Some(x) => println(s"Option valorizzata: $x")
    case None => println(s"Option NON valorizzata :-(")
  }
})*/

optList.foreach( opt => println(s"Valore optional value: ${opt.getOrElse("default")}"))