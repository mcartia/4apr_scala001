package it.training.scala.hier

class SuperVeicolo extends Veicolo with Aereo with Bicicletta {

  def aaa(): Unit = {
    println("solo su SuperVeicolo...")
  }

}
