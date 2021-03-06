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
    sz_svdna.drawTentativeResult("SV-DNA Basis")
    sz_svdna *= perpetual.sz_svdna_factor
    sz_svdna.drawResult()
    sz_svdna
  }

  private def getLohnsteuer(sonderzahlung_i:Buchungswerte,svdna_i:Buchungswerte): Zwischenrechnung ={
    var name_n:String = ""
    target_lohn match {
      case Buchungsdaten.LOHN_WEIHNACHTEN => name_n = s"Lohnsteuer WG"
      case Buchungsdaten.LOHN_URLAUB => name_n = s"Lohnsteuer UG"
    }
    var lst_n:Zwischenrechnung = Zwischenrechnung(name_n)
    lst_n += sonderzahlung_i
    lst_n -= svdna_i
    lst_n -= freibetrag
    lst_n.drawTentativeResult("Zwischensumme")
    lst_n *= perpetual.sz_lst_factor
    lst_n.drawResult()
    lst_n
  }

  private def getNetto(sonderzahlung_i:Buchungswerte,lst_i:Buchungswerte): Zwischenrechnung ={
    var name_n:String = ""
    target_lohn match {
      case Buchungsdaten.LOHN_WEIHNACHTEN => name_n = s"Weihnachtsgeld Netto"
      case Buchungsdaten.LOHN_URLAUB => name_n = s"Urlaubsgeld Netto"
    }

    var ntto:Zwischenrechnung = Zwischenrechnung(name_n)
    ntto += sonderzahlung_i
    ntto -= lst_i
    ntto.drawResult()
    ntto
  }

  override def toString: String = {
    diffCalc.toString + svdna_urlaubsgeld.toString + svdna_weihnachtsgeld.toString
  }

  def getCalcUrlaub(): String ={
    diffCalc.toString + svdna_urlaubsgeld.toString + urlaubsgeld_netto.toString
  }

  def getCalcWeihnachten(): String ={
    diffCalc.toString + svdna_weihnachtsgeld.toString + weihnachtsgeld_netto.toString
  }

  val diffCalc:Zwischenrechnung = getSzDiff()
  lazy val svdna_weihnachtsgeld:Zwischenrechnung = getSV_DNA(weihnachtsgeld)
  lazy val svdna_urlaubsgeld:Zwischenrechnung = getSV_DNA(urlaubsgeld)
  lazy val weihnachtsgeld_lst:Zwischenrechnung = getLohnsteuer(weihnachtsgeld,svdna_weihnachtsgeld.result)
  lazy val urlaubsgeld_lst:Zwischenrechnung = getLohnsteuer(urlaubsgeld,svdna_urlaubsgeld.result)
  lazy val weihnachtsgeld_netto:Zwischenrechnung = getNetto(weihnachtsgeld,weihnachtsgeld_lst.result)
  lazy val urlaubsgeld_netto:Zwischenrechnung = getNetto(urlaubsgeld,urlaubsgeld_lst.result)
}
