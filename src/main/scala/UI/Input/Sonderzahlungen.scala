package UI.Input

import javafx.event.ActionEvent
import lohnsteuer.Buchungsdaten
import scalafx.scene.control.{RadioButton, ToggleGroup}
import scalafx.scene.layout.{GridPane, VBox}

object Sonderzahlungen extends VBox{
  def getGehaltType(): Int ={
    if(butt_default.isSelected){
      Buchungsdaten.LOHN_DEFAULT
    }else if(butt_weihnachten.isSelected){
      Buchungsdaten.LOHN_WEIHNACHTEN
    }else if(butt_urlaub.isSelected){
      Buchungsdaten.LOHN_URLAUB
    }else{
      throw new Exception("Ungueltiges Gehalt ausgewaehlt")
    }
  }
  private def loadDefault(): Unit ={
    UI.Input.Loehne.btto.markUsed
    UI.Input.Loehne.urla.markRedundant
    UI.Input.Loehne.weih.markRedundant
    UI.Input.Freibetrag.fb.markUsed
    UI.Input.Pendlerpauschale.pp.markUsed
  }

  val butt_default:RadioButton = new RadioButton("Standardgehalt"){
    onAction = (event:ActionEvent) => loadDefault()
  }
  val butt_weihnachten:RadioButton = new RadioButton("Wehinachtsgehalt"){
    onAction = (event:ActionEvent) => {
      UI.Input.Loehne.btto.markRedundant
      UI.Input.Loehne.urla.markOptional
      UI.Input.Loehne.weih.markUsed
      UI.Input.Pendlerpauschale.pp.markRedundant
    }
  }
  val butt_urlaub:RadioButton = new RadioButton("Urlaubsgehalt"){
    onAction = (event:ActionEvent) => {
      UI.Input.Loehne.btto.markRedundant
      UI.Input.Loehne.urla.markUsed
      UI.Input.Loehne.weih.markOptional
      UI.Input.Pendlerpauschale.pp.markRedundant
    }
  }

  val tg:ToggleGroup = new ToggleGroup()
  butt_default.setToggleGroup(tg)
  butt_weihnachten.setToggleGroup(tg)
  butt_urlaub.setToggleGroup(tg)

  butt_default.setSelected(true)


  private val myGrid:GridPane = new GridPane()
  myGrid.setHgap(10)
  myGrid.setVgap(10)
  myGrid.addRow(0,butt_default)
  myGrid.addRow(1,butt_weihnachten)
  myGrid.addRow(2,butt_urlaub)

  children = Seq(
    new UI.smallInputTitle("Welches Gehalt?"),
    myGrid
  )

  style = UI.inputBoxStyle
  minHeight = 150
  loadDefault()
}
