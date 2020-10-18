import java.time.Month

import UI.InputUI
import lohnsteuer.perpetual.PendlerPauschale
import lohnsteuer.{Buchungsdaten, Lohnsteuer}
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage

//import scalafx.scene.paint.Color._

object Main extends JFXApp{
  //def main(args: Array[String]): Unit = {
    val a = new Buchungsdaten {
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
    title = UI.inputTitle
    scene = new InputUI
    resizable = false
  }
}
