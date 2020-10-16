package lohnsteuer.perpetual

import UI.{CalculationUI, Description}
object Zwischenrechnung{
  var NOE:Int = 0
}
case class Zwischenrechnung(title:String,info_i:Description = new Description()) extends CalculationUI(info_i){
  smallTitle.text = title
  Zwischenrechnung.NOE += 1
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
    addCalculation(a,ADD)
    result.value = result + a
  }

  def -=(a:Buchungswerte): Unit ={
    cliStr += a.substractString() + "\n"
    addCalculation(a,SUBSTRACT)
    result.value = result - a
  }

  def *=(a:Buchungswerte): Unit ={
    cliStr += "*" + a.toString() + "\n"
    addCalculation(a,MULTIPLY)
    result.value = result * a
  }

  def line(): Unit ={
    UI_Line()
    cliStr += fullLine + "\n"
  }

  def drawResult(): Unit ={
    line()
    addCalculation(result)
    line()
    cliStr += result.toString() + "\n"
  }

  def drawTentativeResult(name_i:String): Unit ={
    line()
    result.name = name_i
    cliStr += result.toString() + "\n"
    result.name = title
  }

  override def toString: String = cliStr
}
