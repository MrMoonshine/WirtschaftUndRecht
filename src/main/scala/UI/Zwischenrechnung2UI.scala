package UI

import lohnsteuer.perpetual.Zwischenrechnung
import scalafx.scene.layout.{GridPane, VBox}

class Zwischenrechnung2UI(list_i:List[Zwischenrechnung]) extends VBox{
  private var children_n:Seq[GridPane] = Seq()
  list_i.foreach(children_n :+= _.calculationPane)

  children = Seq()
  children.add(list_i.last.calculationPane)
}
