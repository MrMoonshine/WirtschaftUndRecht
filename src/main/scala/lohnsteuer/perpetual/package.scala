package lohnsteuer

import java.text.DecimalFormat


package object perpetual {
  //Sozialversicherungsbeitrag
  val sv_prozent:Double = 18.12
  lazy val sv_faktor:Double = sv_prozent/100
  val sv_max:Double = 5370

  //Gewerkschaftsbeitrag
  val gew_prozent:Double = 1
  lazy val gew_faktor:Double = gew_prozent/100
  val gew_max:Double = 33.8

  val e_card:Double = 12.30

  //Konstanten für Sonderzahlungen
  val sz_hoechstbetrag:Buchungswerte = Buchungswerte("Hoechstbetrag",10740)
  val sz_percentFactor:Buchungswerte = Buchungswerte("LST-Faktor",6.0/100.0)
  var sz_hb_diff:Buchungswerte = Buchungswerte("UG + WG - HB")
  //17.12% weil 0,5% an Kammerumlage und 0,5% an der Wohnbauförderung abgezogen werden
  val sz_svdna_factor:Buchungswerte = Buchungswerte("SV-DNA 17,12%",17.12/100.0,Buchungswerte.PROZENT)
  val sz_lst_factor:Buchungswerte = Buchungswerte("LST 6%",6.0/100.0,Buchungswerte.PROZENT)

  //Überstunden
  val ust_teiler:Double = 1.0/158.0

  def euroString(a:Double,console:Boolean = false): String ={
    var outstr:String = ""

    if(console){
      if((a - Math.floor(a)) == 0.0){
        outstr += s"${a.asInstanceOf[Int]},-;"
      }else{
        outstr += f"$a%.2f" + ";"
      }
      return outstr
    }

    val s = new DecimalFormat("###.###,00")
    s.setDecimalSeparatorAlwaysShown(true)
    s.format(a)
  }
}
