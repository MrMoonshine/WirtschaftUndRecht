import java.time.Month

import lohnsteuer.perpetual.PendlerPauschale
import lohnsteuer.{Buchungsdaten, Lohnsteuer}
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color.{Black, Pink, Purple}
import scalafx.scene.paint.{LinearGradient, Stops}
import scalafx.scene.text.Text

//import scalafx.scene.paint.Color._

object Main extends JFXApp{
    val a = new Buchungsdaten{
      name = "Gustav Olafson"
      brutto.value = 3120
      pendlerPauschale = new PendlerPauschale{
        distance = 21
        type_pp = PendlerPauschale.KLEIN
      }
      kinder = 4
      freibetrag.value = 24.80
      ust = 25
    }

    var b = Lohnsteuer(a,Month.NOVEMBER)
    println(a.toString)
    println(b.toString)

  stage = new PrimaryStage {
    title = a.name
    scene = new Scene {
      fill = Black
      content = new HBox {
        padding = Insets(20)
        children = Seq(
          new UI.Title("Lohnsteuer")
        )
      }
    }
  }
}
