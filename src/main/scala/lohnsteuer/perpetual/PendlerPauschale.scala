package lohnsteuer.perpetual

object PendlerPauschale{
  val KLEIN = false
  val GROSS = true
}
class PendlerPauschale extends Buchungswerte("Pendlerpauschale"){
  var distance:Double = 0
  var type_pp:Boolean = false
  var pendlereuroRechnung:Zwischenrechnung = Zwischenrechnung("Pendlereuro")
  var pendlereuro = Buchungswerte("Pendlereuro",0)

  def :=(distance_i:Double):Unit = {
    distance = distance_i
  }

  def refreshName(): Unit ={
    if(type_pp){
      name += " Gross"
    }else{
      name += " Klein"
    }
  }

  //Pendlerpauschale berechnen
  def calc():Double = {
    if(distance < 0){throw new Exception("Falsche Pendlerpauschale!")}

    pendlereuroRechnung = Zwischenrechnung("Pendlereuro")
    pendlereuro.value = distance*2/12
    pendlereuroRechnung += Buchungswerte(s"$distance * 2 / 12", pendlereuro.value)
    pendlereuroRechnung.drawResult()

    if(type_pp){
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
