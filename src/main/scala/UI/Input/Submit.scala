package UI.Input

import java.time.format.TextStyle
import java.util.Locale

import UI.Lohnrechnung
import javafx.event.ActionEvent
import lohnsteuer.Buchungsdaten
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control.{Alert, Button}
import scalafx.scene.layout.HBox

object Submit extends HBox{
  val UST_ROUND_HELPER:Double = 1000.0
  private var infotext:String = ""
  private def removeComma(str_i:String): String ={
    str_i.replace(",","")
  }

  private def getPendlerpauschale(): lohnsteuer.perpetual.PendlerPauschale ={
    if(Pendlerpauschale.butt_none.isSelected)
      new lohnsteuer.perpetual.PendlerPauschale
    else if(Pendlerpauschale.butt_small.isSelected){
      new lohnsteuer.perpetual.PendlerPauschale{
        infotext = "Pendlerstrecke"
        distance = removeComma(Pendlerpauschale.pp.getText).toDouble
        type_pp = lohnsteuer.perpetual.PendlerPauschale.KLEIN
      }
    }
    else if(Pendlerpauschale.butt_big.isSelected){
      new lohnsteuer.perpetual.PendlerPauschale{
        infotext = "Pendlerstrecke"
        distance = removeComma(Pendlerpauschale.pp.getText).toDouble
        type_pp = lohnsteuer.perpetual.PendlerPauschale.GROSS
      }
    }else{
      throw new Exception("Ungültige Pendlerpauschale")
    }
  }
  private def showErrorNumb(): Unit ={
    val a:Alert = new Alert(AlertType.Error){
      headerText = s"$infotext Wurde in einem falschen Format geschrieben"
      contentText = "Alle Werte müssen in diesem Format sein: €,€€€.cc"
    }
    a.showAndWait()
  }

  def getUIValues(): Buchungsdaten ={
    println(Loehne.btto.getText)
    println(removeComma(Loehne.btto.getText))
    val bd:Buchungsdaten = new Buchungsdaten
    bd.target_lohn = Sonderzahlungen.getGehaltType()
    infotext = "Freibetrag"
    bd.freibetrag.value = removeComma(Freibetrag.fb.getText()).toDouble
    if(bd.target_lohn == Buchungsdaten.LOHN_DEFAULT){
      infotext = "Bruttogehalt"
      bd.brutto.value = removeComma(Loehne.btto.getText).toDouble
      bd.name = "Monatslohn " + MonthSelecter.getMonth.getDisplayName(TextStyle.FULL_STANDALONE,Locale.GERMAN)

      //Überstunden Zeug
      infotext = "Überstunden Grundlohn"
      bd.ugl.value = removeComma(UI.Input.Uberstunden.ugl.getText).toDouble
      infotext = "Anzahl der Überstunden"
      bd.ust = removeComma(UI.Input.Uberstunden.ust_amount.getText).toInt
      infotext = "Überstundenteiler"
      bd.ust_factor.value = removeComma(UI.Input.Uberstunden.utl.getText).toDouble/UST_ROUND_HELPER
      infotext = "Uberstundenzuschlag"
      bd.uz.value = UI.Input.Uberstunden.getUZ()

    }else{
      infotext = "Weihnachtsgehalt"
      bd.weihnachtsgeld.value = removeComma(Loehne.weih.getText).toDouble
      infotext = "Urlaubsgehalt"
      bd.urlaubsgeld.value = removeComma(Loehne.urla.getText).toDouble
      bd.name = "Sondergehalt"
    }

    bd.kinder = Avab.numbOfChildren
    bd.pendlerPauschale = getPendlerpauschale()



    bd
  }

  children = Seq(
    new Button("Berechnen"){
      onAction =  (event:ActionEvent) => {
        try{
          val a = getUIValues()
          println("Berechne Lohnsteuer von: " + a.name)
          new Lohnrechnung(a)
        }catch{
          case ex:NumberFormatException => showErrorNumb()
          case ex:Exception => new Alert(AlertType.Error){
            headerText = "Unbekannter Fehler"
            contentText = ex.toString
          }
        }
      }
    }
  )
  style = UI.inputBoxStyle
  minHeight = UI.height_Submit
}
