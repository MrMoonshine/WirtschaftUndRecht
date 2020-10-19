package lohnsteuer.perpetual

trait Uberstunden {
  //Überstunden
  //Wie Viele
  var ust:Int = 0
  //Überstunden Grundlohn
  var ugl:Buchungswerte = Buchungswerte("Überstundenteiler")
  //ÜstTeiler
  var ust_factor:Buchungswerte = Buchungswerte("Überstundenteiler",1.0/158.0,Buchungswerte.PROZENT)
  //Überstundenzuschlag
  var uz:Buchungswerte = Buchungswerte("Überstundenzuschlag",0,Buchungswerte.PROZENT)
  //Überstundenlohn
  var ust_lohn:Buchungswerte = Buchungswerte("Überstundenlohn")
}
