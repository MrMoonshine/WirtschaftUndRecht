package lohnsteuer

import java.time.Month

import lohnsteuer.perpetual.{Buchungswerte, MonatslohnsteuerTabelle, Zwischenrechnung}

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
  private def calcLohnsteuerBemessungsgrundlage(): Buchungswerte ={
    var lstbmgl_n:Double = pers_i.brutto - pers_i.sv.value - pers_i.pendlerPauschale.value - pers_i.gewerkschaftsbeitrag - pers_i.freibetrag
    lstbmglBerechnung += pers_i.brutto.toString
    lstbmglBerechnung += pers_i.sv.substractString()
    lstbmglBerechnung += pers_i.pendlerPauschale.substractString()
    lstbmglBerechnung += Buchungswerte("Gewerkschaftsbeitrag",pers_i.gewerkschaftsbeitrag).substractString()
    lstbmglBerechnung += Buchungswerte("Freibetrag",pers_i.freibetrag).substractString()
    if(month == Month.NOVEMBER){
      lstbmgl_n -= perpetual.e_card
      lstbmglBerechnung += Buchungswerte("E-Card",perpetual.e_card).substractString()
    }
    val lstbmgl = Buchungswerte("Lohnsteuer Bemessungsgrundlage",lstbmgl_n)
    lstbmglBerechnung.line()
    lstbmglBerechnung += lstbmgl.toString()
    lstbmgl
  }
  private def calcLohnsteuer():Buchungswerte = {
    val lstcat = getLSTBMGLcat(lstbmgl.value)
    val AVAB:Double = lstcat.getAVAB(pers_i.kinder)

    val lst_n:Double = lstbmgl - lstbmgl*lstcat.grenzsteuersatz - AVAB - pers_i.pendlerPauschale.pendlereuro.value
    lstBerechnung += lstbmgl.toString()
    lstBerechnung += Buchungswerte(s"${(lstcat.grenzsteuersatz*100).asInstanceOf[Int]}% LSTBMGL",lstbmgl*lstcat.grenzsteuersatz).substractString()
    lstBerechnung += Buchungswerte("AVAB",AVAB).substractString()
    lstBerechnung += pers_i.pendlerPauschale.pendlereuro.substractString()
    lstBerechnung.line()
    val lst_bn = Buchungswerte("Lohnsteuer",lst_n)
    lstBerechnung += lst_bn.toString()
    lst_bn
  }
  private def calcNetto():Buchungswerte = {
    var netto_n:Double = pers_i.brutto - lst - pers_i.gewerkschaftsbeitrag - pers_i.sv.value


    nettoBerechnung += pers_i.brutto.toString()
    nettoBerechnung += lst.substractString()
    nettoBerechnung += Buchungswerte("Gewerkschaftsbeitrag",pers_i.gewerkschaftsbeitrag).substractString()
    nettoBerechnung += pers_i.sv.substractString()

    if(month == Month.NOVEMBER){
      netto_n -= perpetual.e_card
      nettoBerechnung += Buchungswerte("E-Card",perpetual.e_card).substractString()
    }
    val netto_bn = Buchungswerte("Netto",netto_n)
    nettoBerechnung.line()
    nettoBerechnung += netto_bn.toString()
    netto_bn
  }

  def getLohnsteuer(): Buchungswerte ={
    lst
  }

  private val lstbmgl:Buchungswerte = calcLohnsteuerBemessungsgrundlage()


  override def toString: String = {
    lstbmglBerechnung.toString + lstBerechnung.toString + nettoBerechnung.toString
  }

  val lst = calcLohnsteuer()
  val netto = calcNetto()
}
