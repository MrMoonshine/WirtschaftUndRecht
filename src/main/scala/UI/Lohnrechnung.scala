package UI

import UI.Input.MonthSelecter
import lohnsteuer.{Buchungsdaten, Lohnsteuer}
import scalafx.scene.Scene
import scalafx.stage.Stage

class Lohnrechnung(data_i:Buchungsdaten) {
  val lr:Lohnsteuer = Lohnsteuer(data_i,MonthSelecter.getMonth)
  val rechnungsFenster:Stage = new Stage{
    title = data_i.name
    scene = new Scene {
      fill = UI.background_color
      content = new Zwischenrechnung2UI(lr.getCalculations())
    }
  }

  rechnungsFenster.show()
}
