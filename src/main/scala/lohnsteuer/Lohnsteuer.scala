package lohnsteuer

import java.time.Month

import lohnsteuer.perpetual.{Buchungswerte, MonatslohnsteuerTabelle}

case class Lohnsteuer(pers_i:Buchungsdaten, month: Month = Month.JANUARY){
  pers_i.refactor()
  private val fullLine:String = "-------------------------------------------"
  var lstbmglBerechnung:String =
    s"""$fullLine
       |Lohnsteuerbemessungsgrundlage
       |$fullLine
       |""".stripMargin
  var lstBerechnung:String =
    s"""$fullLine
       |Lohnsteuer Berechnung
       |$fullLine
       |""".stripMargin
  var nettoBerechnung = ""
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
    lstbmglBerechnung += pers_i.brutto.toString + "\n"
    lstbmglBerechnung += pers_i.sv.substractString() + "\n"
    lstbmglBerechnung += pers_i.pendlerPauschale.substractString() + "\n"
    lstbmglBerechnung += Buchungswerte("Gewerkschaftsbeitrag",pers_i.gewerkschaftsbeitrag).substractString() + "\n"
    lstbmglBerechnung += Buchungswerte("Freibetrag",pers_i.freibetrag).substractString() + "\n"
    if(month == Month.NOVEMBER){
      lstbmgl_n -= perpetual.e_card
      lstbmglBerechnung += Buchungswerte("E-Card",perpetual.e_card).substractString() + "\n"
    }
    val lstbmgl = Buchungswerte("Lohnsteuer Bemessungsgrundlage",lstbmgl_n)
    lstbmglBerechnung += fullLine + "\n"
    lstbmglBerechnung += lstbmgl.toString()
    lstbmgl
  }
  private def calcNetto():Buchungswerte = {
    var netto_n:Double = pers_i.brutto - lst - pers_i.gewerkschaftsbeitrag - pers_i.sv.value

    nettoBerechnung =
      s"""$lstBerechnung
         |$fullLine
         |Netto
         |$fullLine
         |""".stripMargin
    nettoBerechnung += pers_i.brutto.toString() + "\n"
    nettoBerechnung += lst.substractString() + "\n"
    nettoBerechnung += Buchungswerte("Gewerkschaftsbeitrag",pers_i.gewerkschaftsbeitrag).substractString() + "\n"
    nettoBerechnung += pers_i.sv.substractString() + "\n"

    if(month == Month.NOVEMBER){
      netto_n -= perpetual.e_card
      nettoBerechnung += Buchungswerte("E-Card",perpetual.e_card).substractString() + "\n"
    }
    val netto_bn = Buchungswerte("Lohnsteuer",netto_n)
    nettoBerechnung += fullLine +"\n"
    nettoBerechnung += netto_bn.toString() + "\n"
    netto_bn
  }
  private def calcLohnsteuer():Buchungswerte = {
    lstBerechnung =
    s"""$lstbmglBerechnung
       |$fullLine
       |Lohnsteuer Berechnung
       |$fullLine
       |""".stripMargin
    val lstcat = getLSTBMGLcat(lstbmgl.value)
    val AVAB:Double = lstcat.getAVAB(pers_i.kinder)

    val lst_n:Double = lstbmgl - lstbmgl*lstcat.grenzsteuersatz - AVAB - pers_i.pendlerPauschale.pendlereuro.value
    lstBerechnung += lstbmgl.toString() + "\n"
    lstBerechnung += Buchungswerte(s"${(lstcat.grenzsteuersatz*100).asInstanceOf[Int]}% LSTBMGL",lstbmgl*lstcat.grenzsteuersatz).substractString() + "\n"
    lstBerechnung += Buchungswerte("AVAB",AVAB).substractString() + "\n"
    lstBerechnung += pers_i.pendlerPauschale.pendlereuro.substractString() + "\n"
    lstBerechnung += fullLine + "\n"
    val lst_bn = Buchungswerte("Lohnsteuer",lst_n)
    lstBerechnung += lst_bn.toString()
    lst_bn
  }
  def getLohnsteuer(): Buchungswerte ={
    lst
  }

  private val lstbmgl:Buchungswerte = calcLohnsteuerBemessungsgrundlage()


  override def toString: String = nettoBerechnung

  val lst = calcLohnsteuer()
  val netto = calcNetto()
}
