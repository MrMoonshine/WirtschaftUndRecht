package UI

import javafx.event.ActionEvent
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control.{Alert, Button}

import scala.io.Source

object Description{
  lazy val Sonderzahlungen:Description = new Description("Sonderzahlungen","assets/Sonderzahlungen.txt")
  lazy val Lohnsteuer:Description = new Description("Lohnsteuer","assets/Lohnsteuer.txt")
  lazy val Netto:Description = new Description("Netto","assets/Netto.txt")
  lazy val Lohnsteuerbemessungsgrundlage:Description = new Description("Lohnsteuerbemessungsgrundlage","assets/Lohnsteuerbemessungsgrundlage.txt")
}
class Description(name_i:String = "Lorem Ipsum",file_i:String = "assets/loremIpsum.txt") extends Alert(AlertType.Information){
  private def loadContent(fname:String = file_i): String ={
    var outstr = ""
    try{
      for (line <- Source.fromFile(fname).getLines) {
        outstr += line
      }
    }catch {
      case ex:Exception => outstr += "Failded to load " + fname
    }

    outstr
  }
  def getButton:Button = {
    val dispButt:Button = new Button(name_i){
      tooltip = "Hilfe zu " + name_i
      onAction = (event:ActionEvent) => {
        println(loadContent())
        showAndWait()
      }
    }
    dispButt.setStyle(loadContent("assets/styles/Button.css"))
    dispButt
  }

  title = name_i
  headerText = name_i
  contentText = loadContent()

  initStyle(UI.infoStyle)

}
