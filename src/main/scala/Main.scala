import java.time.Month

import lohnsteuer.perpetual.PendlerPauschale
import lohnsteuer.{Buchungsdaten, Lohnsteuer}
import scalafx.application.JFXApp

//import scalafx.scene.paint.Color._

object Main{
  def main(args: Array[String]): Unit = {
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
  }
}
