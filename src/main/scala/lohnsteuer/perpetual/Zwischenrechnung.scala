package lohnsteuer.perpetual

case class Zwischenrechnung(title:String){
  private val fullLine:String = "-------------------------------------------"
  private var cliStr =
    s"""$fullLine
       |$title
       |$fullLine
       |""".stripMargin

  var result:Buchungswerte = Buchungswerte(title)

  def +=(a:String): Unit ={
    cliStr += a + "\n"
  }

  def +=(a:Buchungswerte): Unit ={
    cliStr += a.toString() + "\n"
    result.value = result + a
  }

  def -=(a:Buchungswerte): Unit ={
    cliStr += a.substractString() + "\n"
    result.value = result - a
  }

  def line(): Unit ={
    cliStr += fullLine + "\n"
  }

  def drawResult(): Unit ={
    line()
    cliStr += result.toString() + "\n"
  }

  override def toString: String = cliStr
}
