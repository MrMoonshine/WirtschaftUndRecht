package lohnsteuer

import lohnsteuer.perpetual.{Buchungswerte, Zwischenrechnung}

class Sonderzahlungen(pers_i:Buchungsdaten){
  import pers_i._

  private def getSzDiff(): Zwischenrechnung ={
    var sz_diff:Zwischenrechnung = Zwischenrechnung("UG + WG - HB")
    sz_diff += weihnachtsgeld
    sz_diff += urlaubsgeld
    sz_diff.drawTentativeResult("Summe")
    sz_diff -= perpetual.sz_hoechstbetrag
    if(sz_diff.result.value < 0){
      sz_diff.result.value = 0
    }
    sz_diff.drawResult()
    sz_diff
  }
  private def getSV_DNA(sonderzahlung:Buchungswerte): Zwischenrechnung ={
    var sz_svdna:Zwischenrechnung = Zwischenrechnung("SV-DNA " + sonderzahlung.name)
    sz_svdna += sonderzahlung
    sz_svdna -= diffCalc.result
    sz_svdna *= perpetual.sz_svdna_factor
    sz_svdna.drawResult()
    sz_svdna
  }

  private def getNetto(): Zwischenrechnung ={
    var name_n:String = ""
    target_lohn match {
      case Buchungsdaten.LOHN_WEIHNACHTEN => name_n = s"Weihnachtsgeld Netto"
      case Buchungsdaten.LOHN_URLAUB => name_n = s"Urlaubsgeld Netto"
    }

    var ntto:Zwischenrechnung = Zwischenrechnung(name_n)
    ntto
  }

  override def toString: String = {
    diffCalc.toString + svdna_urlaubsgeld.toString + svdna_weihnachtsgeld.toString
  }

  val diffCalc = getSzDiff()
  lazy val svdna_weihnachtsgeld = getSV_DNA(weihnachtsgeld)
  lazy val svdna_urlaubsgeld = getSV_DNA(urlaubsgeld)

  //lazy val weihnachtsgeld_netto =
}
