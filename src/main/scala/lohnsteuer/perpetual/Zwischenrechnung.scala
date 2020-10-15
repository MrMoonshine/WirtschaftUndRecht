package lohnsteuer.perpetual

import UI.Description
import scalafx.scene.control.Label
import scalafx.scene.layout.GridPane
import scalafx.scene.text.Text

case class Zwischenrechnung(title:String){
  private object smallTitle extends Text{
    text = title
    style = "-fx-font-size: 16pt"
    fill = UI.subtitle_color
  }

  private class DarkLabel(text_i:String) extends Label{
    text = text_i
    textFill = UI.foreground_color
  }

  private val fullLine:String = "-------------------------------------------"
  private var cliStr =
    s"""$fullLine
       |$title
       |$fullLine
       |""".stripMargin

  private var info:Description = new Description()
  var result:Buchungswerte = Buchungswerte(title)
  var calculationPane:GridPane = new GridPane
  private var row_id:Int = 0
  calculationPane.addRow(row_id,smallTitle,info.getButton)

  def +=(a:String): Unit ={
    cliStr += a + "\n"
  }

  def +=(a:Buchungswerte): Unit ={
    cliStr += a.toString() + "\n"
    row_id += 1
    calculationPane.addRow(row_id,new DarkLabel(a.name),new DarkLabel(a.euroStringUI()))
    result.value = result + a
  }

  def -=(a:Buchungswerte): Unit ={
    cliStr += a.substractString() + "\n"
    row_id += 1
    calculationPane.addRow(row_id,new DarkLabel(a.name),new DarkLabel("-" + a.euroStringUI()))
    result.value = result - a
  }

  def *=(a:Buchungswerte): Unit ={
    cliStr += "*" + a.toString() + "\n"
    row_id += 1
    calculationPane.addRow(row_id,new DarkLabel(a.name),new DarkLabel(s"${a.value * 100.0}%"))
    result.value = result * a
  }

  def line(): Unit ={
    cliStr += fullLine + "\n"
  }

  def drawResult(): Unit ={
    line()
    cliStr += result.toString() + "\n"
  }

  def drawTentativeResult(name_i:String): Unit ={
    line()
    result.name = name_i
    cliStr += result.toString() + "\n"
    result.name = title
  }

  def setInfo(name_i:String,path_i:String): Unit ={
    info = new Description(name_i,path_i)
  }

  override def toString: String = cliStr
}
