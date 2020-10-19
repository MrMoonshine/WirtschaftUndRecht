package lohnsteuer

import lohnsteuer.perpetual.{Buchungswerte, Zwischenrechnung}

final class Uberstunden(pers_i:Buchungsdaten){
  val ustCalc:Zwischenrechnung = new Zwischenrechnung("Überstundenlohn")
  def getUstLohn:Zwischenrechnung = {
    import pers_i._
    ustCalc += ugl
    ustCalc *= ust_factor
    ustCalc.drawTentativeResult("ÜST-Lohn ohne ÜZ")
    ustCalc.line()
    val tentative_ustgl:Buchungswerte = Buchungswerte("ÜST-Lohn ohne ÜZ",ustCalc.result.value)
    ustCalc.result.value = 0
    ustCalc += tentative_ustgl
    ustCalc *= Buchungswerte("Anzahl Überstunden",ust)
    ustCalc.drawTentativeResult("ÜST-Lohn ohne Üz")
    val tentative_ugl:Buchungswerte = Buchungswerte("ÜST-Lohn ohne Üz",ustCalc.result.value)
    ustCalc.line()
    ustCalc.result.value = 0

    ustCalc += tentative_ustgl
    ustCalc *= uz
    ustCalc.drawTentativeResult("Überstundenzuschlag")
    val tentative_uz:Buchungswerte = Buchungswerte("ÜST-Lohn ohne Üz",ustCalc.result.value)
    ustCalc.line()

    ustCalc.result.value = 0
    if(ust > perpetual.ust_lst_frei){
      ustCalc += Buchungswerte(s"${perpetual.ust_lst_frei} Üz",perpetual.ust_lst_frei * tentative_uz.value)
      ustCalc += Buchungswerte(s"${ust - perpetual.ust_lst_frei} Üz",(ust - perpetual.ust_lst_frei) * tentative_uz.value)
      ustCalc += tentative_ugl
    }else{
      ustCalc += Buchungswerte(s"${ust} Üz",ust * tentative_uz.value)
    }
    ustCalc.drawResult()
    ustCalc
  }
}
