package UI

import lohnsteuer.perpetual.{Buchungswerte, Zwischenrechnung}
import scalafx.geometry.Insets
import scalafx.scene.control.Label
import scalafx.scene.layout.GridPane
import scalafx.scene.shape.Line
import scalafx.scene.text.Text

class CalculationUI(info:Description) {
  protected lazy val ADD:Int = 0
  protected lazy val SUBSTRACT:Int = 1
  protected lazy val MULTIPLY:Int = 2
  protected lazy val PERCENT:Int = 3

  private lazy val colorSelection:Int = Zwischenrechnung.NOE % UI.soconadary_title_colors.length

  protected object smallTitle extends Text{
    style = "-fx-font-size: 12pt;"
    fill = UI.soconadary_title_colors(colorSelection)
  }

  private class LineFill() extends Label{
    minWidth = 300
    maxHeight = 5
    graphic = new Line{
      startX = 0
      startY = 0
      endX = 300
      endY = 0
      strokeWidth = 2
      this.setStyle("-fx-stroke: #000000;")
    }
  }


  protected class DarkLabel(text_i:String) extends Label{
    text = text_i
    textFill = UI.foreground_color
    minWidth = 300
    //this.setStyle("-fx-padding-left: 10px;")
    this.setPadding(Insets.apply(0,0,0,10))
  }


  protected def UI_Line(): Unit ={
    row_id +=1
    calculationPane.addRow(row_id,new LineFill,new LineFill)
  }

  protected def addCalculation(a:Buchungswerte,calcType:Int = ADD):Unit = {
    row_id += 1
    calcType match {
      case ADD => calculationPane.addRow(row_id,new DarkLabel(a.name),new DarkLabel(a.euroStringUI()))
      case SUBSTRACT => calculationPane.addRow(row_id,new DarkLabel(a.name),new DarkLabel("-" + a.euroStringUI()))
      case MULTIPLY => calculationPane.addRow(row_id,new DarkLabel(a.name),new DarkLabel(s"*${a.value}"))
      case PERCENT => calculationPane.addRow(row_id,new DarkLabel(a.name),new DarkLabel(f"${a.value * 100.0}%1.2f" + "%"))
    }
  }

  var calculationPane:GridPane = new GridPane
  protected var row_id:Int = 0
  //calculationPane.setHgap(10)
  calculationPane.setVgap(10)
  calculationPane.addRow(row_id,smallTitle,info.getButton)
  calculationPane.setPadding(Insets.apply(10,10,10,10))
}
