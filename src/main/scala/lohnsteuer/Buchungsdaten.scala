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
  var freibetrag:Double = 0
  //schmutzzulage
  var sz:Double = 0
  var ez:Double = 0
  //Überstunden
  var ust:Int = 0
  var gewerkschaftsbeitrag:Double = 0

  //sozialversichenrung
  var sv:Buchungswerte = Buchungswerte("Sozialversicherung",0)

  private def calcGewerkschaftsbeitrag(): Unit ={
    val tentative_gew:Double = brutto * perpetual.gew_faktor  //1%
    if (tentative_gew > perpetual.gew_max){
      gewerkschaftsbeitrag = perpetual.gew_max
    }else{
     gewerkschaftsbeitrag = tentative_gew
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
    val fullLine:String = "------------------------------------"
    pendlerPauschale.refreshName
    val outstr =
      s"""$fullLine
         |$name
         |$fullLine
         |Brutto               = ${perpetual.euroString(brutto.value,true)}
         |Kinder               = $kinder
         |Pendlerpauschale     = ${pendlerPauschale.distance} km ( ${pendlerPauschale.name} )
         |                     = ${perpetual.euroString(pendlerPauschale.value,true)}
         |Freibetrag           = ${perpetual.euroString(freibetrag,true)}
         |Gewerkschaftsbeitrag = ${perpetual.euroString(gewerkschaftsbeitrag,true)}
         |Schmutzzulage        = ${perpetual.euroString(sz,true)}
         |EZ???                = ${perpetual.euroString(ez,true)}
         |Ueberstunden         = $ust
         |$fullLine
         |""".stripMargin
    outstr
  }

}
