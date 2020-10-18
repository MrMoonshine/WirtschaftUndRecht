package UI.Input

import javafx.scene.control.Tooltip
import scalafx.scene.control.{TextField, TextFormatter}
import scalafx.util.converter.NumberStringConverter

class MoneyInput(tooltip_i:String = "") extends TextField{
  def markRedundant:Unit={
    this.setTooltip(new Tooltip(s"$tooltip_i wird nicht in der ausgewählten Berechnung benutzt"))
      style = UI.style_redundant
  }

  def markOptional:Unit={
    this.setTooltip(new Tooltip(s"$tooltip_i kann benutzt werden"))
    style = UI.style_optional
  }

  def markUsed:Unit={
    this.setTooltip(new Tooltip(s"$tooltip_i wird benutzt"))
    style = UI.style_used
  }
  text = "0"
  this.setTextFormatter(new TextFormatter(new NumberStringConverter()))
  this.setTooltip(new Tooltip(tooltip_i))
}
