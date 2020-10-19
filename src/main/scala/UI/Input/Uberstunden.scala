package UI.Input

import scalafx.scene.control.{Label, RadioButton, ToggleGroup}
import scalafx.scene.layout.{HBox, VBox}

object Uberstunden extends VBox{

  val ugl:MoneyInput = new MoneyInput("Überstundengrundlohn")
  val ust_amount:MoneyInput = new MoneyInput("Überstunden")
  val utl:MoneyInput = new MoneyInput("Überstundenteiler",(Submit.UST_ROUND_HELPER / 158.0).toString)
  private val butt_half:RadioButton = new RadioButton("50%")
  private val butt_full:RadioButton = new RadioButton("100%")
  private val tg:ToggleGroup = new ToggleGroup()
  butt_full.setToggleGroup(tg)
  butt_half.setToggleGroup(tg)
  butt_half.setSelected(true)

  def getUZ():Double = {
    if(butt_half.isSelected){
      0.5
    }else{
      1.0
    }
  }

  children = Seq(
    new UI.smallInputTitle("Überstunden"),
    new Label(ugl.getTooltip.getText),
    new HBox{
      children = Seq(
        ugl,
        new Label("€")
      )
    },
    new Label(ust_amount.getTooltip.getText + " Anzahl"),
    new HBox{
      children = Seq(
        ust_amount
      )
    },
    new Label(utl.getTooltip.getText + s" x ${Submit.UST_ROUND_HELPER}"),
    new HBox{
      children = Seq(
        utl
      )
    },
    new Label("Überstundenzuschlag"),
    new HBox{
      children = Seq(
        butt_full,butt_half
      )
    },
  )
}
