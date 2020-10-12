package lohnsteuer

import lohnsteuer.perpetual.{Buchungswerte, PendlerPauschale}

class Buchungsdaten {
  var name:String = "Unbekannt"
  //Brutto gehalt in €
  var brutto:Buchungswerte = Buchungswerte("Brutto")
  //anzahl an kinder
  var kinder:Int = 0
  //Weg in Kilometer  (einfache Fahrtstrecke)
  //Pendlerpauschale: true => groß; false => klein
  var pendlerPauschale:PendlerPauschale = new PendlerPauschale
  //stundenlohn und arbeitszeit
  var h_lohn:Double = 0
  var h_arbeit:Double = 0
  //Freibetrag
  var freibetrag:Buchungswerte = Buchungswerte("Freibetrag")
  //schmutzzulage
  var sz:Buchungswerte = Buchungswerte("Schmutzzulage")
  var ez:Buchungswerte = Buchungswerte("Erschwerniszulage")
  var gz:Buchungswerte = Buchungswerte("Gefahrenzulage")
  //Überstunden
  var ust:Int = 0
  var gewerkschaftsbeitrag:Buchungswerte = Buchungswerte("Gewerkschaftsbeitrag")

  //sozialversichenrung
  var sv:Buchungswerte = Buchungswerte("SV-DNA")

  //E-Card
  lazy val e_card:Buchungswerte = Buchungswerte("E-Card",perpetual.e_card)

  private def calcGewerkschaftsbeitrag(): Unit ={
    val tentative_gew:Double = brutto * perpetual.gew_faktor  //1%
    if (tentative_gew > perpetual.gew_max){
      gewerkschaftsbeitrag.value = perpetual.gew_max
    }else{
     gewerkschaftsbeitrag.value = tentative_gew
    }
  }
  //Calculate Brutto if it is not set
  def getBrutto(): Double ={
    if(brutto.value > 0){
      brutto.value
    }else if(h_lohn > 0 && h_arbeit > 0){
      brutto.value = h_arbeit * h_lohn
      brutto.value
    }else{
      throw new Exception("Invalid value for \"brutto\"")
    }
  }

  private def calc_sv_lfd(): Unit ={
    if(brutto.value > 5370){
      sv.value = 5370 * perpetual.sv_faktor
    }else{
      sv.value = brutto * perpetual.sv_faktor
    }
  }

  def refactor(): Unit ={
    getBrutto
    calcGewerkschaftsbeitrag
    pendlerPauschale.calc
    calc_sv_lfd()
  }

  override def toString: String = {
    val fullLine:String = "-------------------------------------------"
    pendlerPauschale.refreshName
    val outstr =
      s"""$fullLine
         |$name
         |$fullLine
         |${brutto.toString()}
         |${Buchungswerte("Kinder",kinder).toString()}
         |${Buchungswerte("Pendlerpauschale Typ:",pendlerPauschale.distance).toString()} km ( ${pendlerPauschale.name} )
         |${pendlerPauschale.toString()}
         |${gewerkschaftsbeitrag.toString()}
         |${freibetrag.toString()}
         |${ez.toString()}
         |${gz.toString()}
         |${sz.toString()}
         |${Buchungswerte("Ueberstunden",ust).toString()}
         |$fullLine
         |""".stripMargin
    outstr
  }

}
