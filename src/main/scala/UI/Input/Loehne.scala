package UI.Input

import UI.Description
import scalafx.scene.control.Label
import scalafx.scene.layout.{HBox, VBox}

object Loehne extends VBox{
  val btto:MoneyInput = new MoneyInput
  val weih:MoneyInput = new MoneyInput
  val urla:MoneyInput = new MoneyInput

  children = Seq(
    new UI.smallInputTitle("Bruttogehalt"),
    new HBox{
      children = Seq(btto,new Label("€"))
    },
    new UI.smallInputTitle("Weihnachtsgehalt"),
    new HBox{
      children = Seq(weih,new Label("€"))
    },
    new UI.smallInputTitle("Urlaubsgehalt"),
    new HBox{
      children = Seq(urla,new Label("€"))
    },
    new HBox{
      children = Seq(
        new Description("Gehälter","assets/gehaltinput.txt").getButton,
        new Label("€€€€.cc")
      )
    },
  )
  style = UI.inputBoxStyle
  minHeight = 200
}
