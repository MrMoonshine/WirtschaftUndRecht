package lohnsteuer.perpetual.MonatslohnsteuerTabelle

class Constants {
  var grenzsteuersatz: Double = 0
  var kinder:Array[Double] = Array()
  def getAVAB(kinder_i:Int): Double ={
    if(kinder_i < 0){
      throw new Exception("Negative anzahl an Kinder")
    }else if(kinder_i > 5){
      return kinder(5)
    }
    kinder(kinder_i)
  }
}
