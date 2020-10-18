package UI.Input

import scalafx.scene.control.{Label, RadioButton, ToggleGroup}
import scalafx.scene.layout.{HBox, VBox}

object Pendlerpauschale extends VBox{
  def getType: Int ={
    9
  }
  val pp:MoneyInput = new MoneyInput("Pendlerpauschale")
  pp.maxWidth = 50
  val butt_big:RadioButton = new RadioButton("Groß")
  val butt_small:RadioButton = new RadioButton("Klein")
  val butt_none:RadioButton = new RadioButton("Keine")

  val tg:ToggleGroup = new ToggleGroup()
  butt_small.setToggleGroup(tg)
  butt_big.setToggleGroup(tg)
  butt_none.setToggleGroup(tg)

  butt_none.setSelected(true)

  children = Seq(
    new UI.smallInputTitle("Pendlerpauschale"),
    new HBox{
      children = Seq(butt_small,butt_big,butt_none)
    },
    new HBox{
      children = Seq(
        pp,
        new Label("km")
      )
    },
    new Label(pp.getText)
  )
}