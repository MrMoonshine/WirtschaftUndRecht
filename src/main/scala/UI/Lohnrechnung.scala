package UI

import UI.Input.MonthSelecter
import lohnsteuer.{Buchungsdaten, Lohnsteuer}
import scalafx.scene.control.ScrollPane.ScrollBarPolicy
import scalafx.scene.control.{ScrollBar, ScrollPane}
import scalafx.scene.{Group, Scene}
import scalafx.scene.layout.{HBox, Priority, VBox}
import scalafx.stage.Stage

class Lohnrechnung(data_i:Buchungsdaten) {
  val lr:Lohnsteuer = Lohnsteuer(data_i,MonthSelecter.getMonth)
  val sp:ScrollPane = new ScrollPane{
    content = new Zwischenrechnung2UI(lr.getCalculations())
    hbarPolicy = ScrollBarPolicy.Never
    vbarPolicy = ScrollBarPolicy.Always
    maxHeight = UI.heightMax_Lohnrechnung
    fitToHeight = true
  }


  val rechnungsFenster:Stage = new Stage{
    resizable = false
    maxHeight = UI.heightMax_Lohnrechnung + 28
    title = data_i.name
    scene = new Scene {
      fill = UI.background_color
      content = new VBox{
        vgrow = Priority.Always
        children = Seq(sp)
      }
    }
  }

  rechnungsFenster.show()
}
