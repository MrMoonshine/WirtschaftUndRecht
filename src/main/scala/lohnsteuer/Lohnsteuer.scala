package lohnsteuer

import java.time.Month

import lohnsteuer.perpetual.{Buchungswerte, MonatslohnsteuerTabelle, Zwischenrechnung}
import scalafx.application.JFXApp

case class Lohnsteuer(pers_i:Buchungsdaten, month: Month = Month.JANUARY){
  pers_i.refactor()
  var lstbmglBerechnung:Zwischenrechnung = Zwischenrechnung("Lohnsteuer Bemessungsgrundlage")
  var lstBerechnung:Zwischenrechnung = Zwischenrechnung("Lohnsteuer")
  var nettoBerechnung:Zwischenrechnung = Zwischenrechnung("Netto Berechnung")

  private def getLSTBMGLcat(lstbmgl:Double): MonatslohnsteuerTabelle.Constants ={
    if(lstbmgl < 0){
      throw new Exception("Falsche Lohnsteuerbemessungsgrundlage")
    }else if(lstbmgl <= 1066){
      MonatslohnsteuerTabelle.cl0
    }else if(lstbmgl <= 1516){
      MonatslohnsteuerTabelle.cl1
    }else if(lstbmgl <= 2599.33){
      MonatslohnsteuerTabelle.cl2
    }else if(lstbmgl <= 5016){
      MonatslohnsteuerTabelle.cl3
    }else if(lstbmgl <= 7516){
      MonatslohnsteuerTabelle.cl4
    }else if(lstbmgl <= 83349.33){
      MonatslohnsteuerTabelle.cl5
    }else{
      MonatslohnsteuerTabelle.cl6
    }
  }
  private def calcLohnsteuerBemessungsgrundlage(): Zwischenrechnung ={
    import pers_i._

    lstbmglBerechnung += brutto
    lstbmglBerechnung -= sv
    lstbmglBerechnung -= pendlerPauschale
    lstbmglBerechnung -= gewerkschaftsbeitrag
    lstbmglBerechnung -= freibetrag
    if(month == Month.NOVEMBER){
      lstbmglBerechnung -= e_card
    }
    lstbmglBerechnung.drawResult()
    lstbmglBerechnung
  }
  private def calcLohnsteuer():Zwischenrechnung = {
    import pers_i._
    val lstcat = getLSTBMGLcat(lstbmgl.value)
    val AVAB:Double = lstcat.getAVAB(kinder)

    lstBerechnung += lstbmgl
    lstBerechnung -= Buchungswerte(s"${(lstcat.grenzsteuersatz*100).asInstanceOf[Int]}% LSTBMGL",lstbmgl*lstcat.grenzsteuersatz)
    lstBerechnung -= Buchungswerte("AVAB",AVAB)
    lstBerechnung -= pendlerPauschale.pendlereuro

    lstBerechnung.drawResult()
    lstBerechnung
  }
  private def calcNetto():Zwischenrechnung = {
    import pers_i._
    nettoBerechnung += brutto
    nettoBerechnung -= lst
    nettoBerechnung -= gewerkschaftsbeitrag
    nettoBerechnung -= sv

    if(month == Month.NOVEMBER){
      nettoBerechnung -= e_card
    }

    nettoBerechnung.drawResult()
    nettoBerechnung
  }

  def getLohnsteuer: Buchungswerte ={
    lst
  }

  lstbmglBerechnung = calcLohnsteuerBemessungsgrundlage()
  private val lstbmgl:Buchungswerte = lstbmglBerechnung.result

  lstBerechnung = calcLohnsteuer()
  private val lst = lstBerechnung.result

  nettoBerechnung = calcNetto()
  private val netto = nettoBerechnung.result

  override def toString: String = {
    lstbmglBerechnung.toString + lstBerechnung.toString + nettoBerechnung.toString
  }


}
