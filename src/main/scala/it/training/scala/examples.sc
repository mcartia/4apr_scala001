val myList = (1 to 10).toList

println("Ciclo tramite for-loop")
for (i <- myList) {
  println(s"Elemento: $i")
}

println("Ciclo tramite foreach (costrutto funzionale")
myList.foreach( i => {
  println(s"Elemento: $i")
})

myList
  // filtro gli elementi pari
  .filter( i => i%2==0 )
  // moltiplico ogni elemento per 2
  .map( i => i*2 )
  // stampo il risultato
  .foreach( i => println(s"Elemento: $i") )

myList
  // filtro gli elementi pari
  .filter( i => i%2==0 )
  // moltiplico ogni elemento per 2
  .map( i => i*2 )
  // calcolo la sommatoria
  .foldLeft(0)( (x,y) => {
    println(s"Elaboro: $x + $y")
    x+y
  })

//2 Write a custom function that reverses a list.
def exercise2(): Unit = {
  try {
    val words = "Hello world list test aaa"
    val wordList = words.split(" ")
    val reversed = wordList.foldLeft("")((x,y) => {s"$y $x"})
    //println("Reversed: " + wordList.reverse.toList.mkString(" "))
    println(s"Reversed: $reversed")
  } catch {
    case _ => println("Error!")
  }
}

exercise2()
