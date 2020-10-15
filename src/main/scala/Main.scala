import java.time.Month

import UI.{Description, Zwischenrechnung2UI}
import lohnsteuer.perpetual.PendlerPauschale
import lohnsteuer.{Buchungsdaten, Lohnsteuer}
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.layout.{HBox, VBox}
import scalafx.scene.text.Text

//import scalafx.scene.paint.Color._

object Main extends JFXApp{
  //def main(args: Array[String]): Unit = {
    val a = new Buchungsdaten {
      name = "Gustav Olafson"
      brutto.value = 3120
      pendlerPauschale = new PendlerPauschale {
        distance = 21
        type_pp = PendlerPauschale.KLEIN
      }
      kinder = 4
      freibetrag.value = 24.80
      ust = 25
      weihnachtsgeld.value = 6400
      urlaubsgeld.value = 6100
      target_lohn = Buchungsdaten.LOHN_DEFAULT
    }

    var b = Lohnsteuer(a, Month.NOVEMBER)
    println(a.toString)
    println(b.toString)
  //}

  stage = new PrimaryStage {
    title = a.name
    scene = new Scene {
      fill = UI.background_color
      content = new HBox {
        children = Seq(
          new VBox(){
            padding = Insets(20)
            children = Seq(
              new UI.Title("Lohnsteuer"),
              new Text(a.toString){
                fill = UI.foreground_color
              },
              new Text(b.toString){
                fill = UI.foreground_color
              },
              new Description().getButton,
              new Description("Sonderzahlungen","assets/Sonderzahlungen").getButton
            )
          },
          new Zwischenrechnung2UI(b.getCalculations())
        )

      }
    }
  }
}
