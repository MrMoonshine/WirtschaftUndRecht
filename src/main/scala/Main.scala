import java.time.Month

import UI.Description
import lohnsteuer.perpetual.PendlerPauschale
import lohnsteuer.{Buchungsdaten, Lohnsteuer}
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.layout.VBox
import scalafx.scene.paint.Color.White
import scalafx.scene.text.Text

//import scalafx.scene.paint.Color._

object Main extends JFXApp{
  //def main(args: Array[String]): Unit = {
    private val a = new Buchungsdaten {
      name = "Gustav Olafson"
      brutto.value = 3120
      pendlerPauschale = new PendlerPauschale {
        distance = 21
        type_pp = PendlerPauschale.KLEIN
      }
      kinder = 4
      freibetrag.value = 24.80
      ust = 25
      weihnachtsgeld.value = 6400
      urlaubsgeld.value = 6100
      target_lohn = Buchungsdaten.LOHN_DEFAULT
    }

    var b = Lohnsteuer(a, Month.NOVEMBER)
    println(a.toString)
    println(b.toString)
  //}

  stage = new PrimaryStage {
    title = a.name
    scene = new Scene {
      fill = White
      content = new VBox {
        padding = Insets(20)
        children = Seq(
          new UI.Title("Lohnsteuer"),
          new Text(a.toString),
          new Text(b.toString),
          new Description().getButton
        )
      }
    }
  }
}
