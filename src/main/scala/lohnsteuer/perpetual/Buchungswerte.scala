package lohnsteuer.perpetual

case class Buchungswerte(name_i:String,value_i:Double = 0){
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

    s"$name $spaces $dispneg${euroString(value,true)}"
  }

  override def toString(): String = {
    drawString()
  }

  def substractString(): String ={
    drawString(true)
  }
}
