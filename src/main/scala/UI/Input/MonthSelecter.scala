package UI.Input

import java.time.{LocalDate, Month}

import javafx.scene.control.Tooltip
import scalafx.scene.control.{DatePicker, Label}
import scalafx.scene.layout.VBox

object MonthSelecter extends VBox{
  def getMonth: Month ={
    monthpick.getValue.getMonth
  }
  private val monthpick:DatePicker = new DatePicker()
  monthpick.setShowWeekNumbers(true)
  monthpick.setTooltip(new Tooltip("Wähle einen Tag im Monat aus"))
  monthpick.value = LocalDate.now()
  children = Seq(
    new UI.smallInputTitle("Welches Monatsgehalt?"),
    monthpick,
    new Label("Wähle einen Tag im Monat aus")
  )
  style = UI.inputBoxStyle
  minHeight = 100
}
