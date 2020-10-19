package UI

import lohnsteuer.perpetual.Zwischenrechnung
import scalafx.scene.layout.VBox
import scalafx.scene.text.Text

class Zwischenrechnung2UI(list_i:Seq[Zwischenrechnung]) extends VBox{
  //private var children_n:Seq[GridPane] = Seq()
  //list_i.foreach(children_n :+= _.calculationPane)

  children = Seq()

  for(n <- list_i.indices){
    //list_i(n).colorSelection = n % UI.soconadary_title_colors.length
    children.add(list_i(n).calculationPane)
  }
}
