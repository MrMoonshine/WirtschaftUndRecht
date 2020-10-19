package lohnsteuer.perpetual

object Buchungswerte{
  val EURO:Int = 0
  val PROZENT:Int = 1
  val MULTIPLIER:Int = 2
}

case class Buchungswerte(name_i:String,value_i:Double = 0,number_unit:Int = Buchungswerte.EURO){
  var name:String = name_i
  var value:Double = value_i

  def +(a:Double):Double = value + a
  def -(a:Double):Double = value - a
  def *(a:Double):Double = value * a
  def /(a:Double):Double = value / a

  def +(a:Buchungswerte):Double = value + a.value
  def -(a:Buchungswerte):Double = value - a.value
  def *(a:Buchungswerte):Double = value * a.value
  def /(a:Buchungswerte):Double = value / a.value

  private def drawString(negative_display:Boolean = false): String ={
    val length = 32 - name.length
    var dispneg:String = " "

    if(negative_display){
      dispneg = "-"
    }

    var spaces = ""
    for(m <- 0 to length){
      spaces += " "
    }

    if(number_unit == Buchungswerte.EURO){
      s"$name $spaces $dispneg${euroString(value,true)}"
    }else if(number_unit == Buchungswerte.PROZENT){
      s"$name $spaces $dispneg${value * 100.0}%"
    }else if(number_unit == Buchungswerte.MULTIPLIER){
      s"$name $spaces *$dispneg$value"
    }else{
     throw new Exception("Falsche Einheit")
    }
  }

  override def toString(): String = {
    drawString()
  }

  def euroStringUI(): String ={
    euroString(value,false)
  }

  def substractString(): String ={
    drawString(true)
  }
}
