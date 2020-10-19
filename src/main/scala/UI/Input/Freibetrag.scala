package UI.Input

import scalafx.scene.control.Label
import scalafx.scene.layout.{HBox, VBox}

object Freibetrag extends VBox{
  val fb:MoneyInput = new MoneyInput("Freibetrag",0.0.toString)
  children = Seq(
    new UI.smallInputTitle("Freibetrag"),
    new HBox{
      children = Seq(
        fb,
        new Label("€")
      )
    }
  )
}
