package lohnsteuer.perpetual

class Buchung(value_i:Double){
  def substract(a:Double): Unit ={

  }

  def add(a:Double): Unit ={

  }

  def linebreak:Unit = {
    cliString += "------------------------------------\n"
  }

  override def toString: String = cliString

  var cliString:String = ""
  var value:Double = value_i
}
