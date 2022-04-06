def sommatoriaIter(numbers: List[Int]): Int = {
  var cont = 0
  for (i <- numbers) {
    cont+=i
  }

  //println(s"La somma dei valori è: $cont")
  cont
}

def sommatoriaFunc(numbers: List[Int]): Int = {
  val cont = numbers.foldLeft(0)(_+_)
  //println(s"La somma dei valori è: $cont")
  cont
}

def sommatoriaRic(numbers: List[Int]): Int = {
  if (numbers.length==1) return numbers(0)
  else numbers(0) + sommatoriaRic(numbers.slice(1,numbers.length))
}

val myList = (1 to 10).toList
println(s"Sommatoria (iterativa) ${sommatoriaIter(myList)}")
println(s"Sommatoria (funzionale) ${sommatoriaIter(myList)}")
println(s"Sommatoria (ricorsiva) ${sommatoriaIter(myList)}")