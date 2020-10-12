import java.time.Month

import lohnsteuer.perpetual.PendlerPauschale
import lohnsteuer.{Buchungsdaten, Lohnsteuer}

object Main{
  def main(args: Array[String]): Unit = {
    println("Oida!!!")
    val a = new Buchungsdaten{
      name = "Gustav Olafson"
      brutto.value = 3120
      pendlerPauschale = new PendlerPauschale{
        distance = 21
        type_pp = PendlerPauschale.KLEIN
      }
      kinder = 4
      freibetrag = 24.80
      ust = 25
    }

    var b = Lohnsteuer(a,Month.NOVEMBER)
    println(a.toString)
    println(b.toString)
  }
}
