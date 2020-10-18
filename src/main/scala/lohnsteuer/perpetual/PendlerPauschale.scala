package lohnsteuer.perpetual

object PendlerPauschale{
  val KLEIN:Int = 1
  val GROSS:Int = 2
  val NONE:Int = 0
}
class PendlerPauschale extends Buchungswerte("Pendlerpauschale"){
  var distance:Double = 0
  var type_pp:Int = PendlerPauschale.NONE
  var pendlereuroRechnung:Zwischenrechnung = Zwischenrechnung("Pendlereuro")
  var pendlereuro = Buchungswerte("Pendlereuro",0)

  def :=(distance_i:Double):Unit = {
    distance = distance_i
  }

  def refreshName(): Unit ={
    if(type_pp == PendlerPauschale.GROSS){
      name += " Gross"
    }else if(type_pp == PendlerPauschale.KLEIN){
      name += " Klein"
    }
  }

  //Pendlerpauschale berechnen
  def calc():Double = {
    if(type_pp == PendlerPauschale.NONE){return 0}
    if(distance < 0){throw new Exception("Falsche Pendlerpauschale!")}

    pendlereuroRechnung = Zwischenrechnung("Pendlereuro",UI.Description.PendlerEuro)
    pendlereuro.value = distance*2/12
    pendlereuroRechnung += Buchungswerte(s"$distance * 2 / 12", pendlereuro.value)
    pendlereuroRechnung.drawResult()

    if(type_pp == PendlerPauschale.GROSS){
      if(distance < 2){
        value = 0
        value
      }
      else if(distance < 20){
        value = 31
        value
      }
      else if(distance <= 40){
        value = 123
        value
      }
      else if(distance <= 60){
        value = 214
        value
      }
      else if(distance > 60){
        value = 306
        value
      }
      else{
        throw new Exception("Falsche Pendlerpauschale!")
      }
    }else{
      if(distance < 20){
        value = 0
        value
      }
      else if(distance <= 40){
        value = 58
        value
      }
      else if(distance <= 60){
        value = 113
        value
      }
      else if(distance > 60){
        value = 168
        value
      }
      else{
        throw new Exception("Falsche Pendlerpauschale!")
      }
    }
  }
}
