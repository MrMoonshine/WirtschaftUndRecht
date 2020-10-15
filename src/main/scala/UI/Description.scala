package UI

import javafx.event.ActionEvent
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control.{Alert, Button}

import scala.io.Source

class Description(name_i:String = "Lorem Ipsum",file_i:String = "assets/loremIpsum.txt") extends Alert(AlertType.Information){
  private def loadContent(): String ={
    var outstr = ""
    for (line <- Source.fromFile(file_i).getLines) {
      outstr += line
    }
    outstr
  }
  def getButton:Button = {
    val dispButt:Button = new Button{
      text = name_i
      tooltip = "Hilfe zu " + name_i
      onAction = (event:ActionEvent) => {
        println(loadContent())
        showAndWait()
      }
    }
    dispButt
  }

  title = name_i
  headerText = name_i
  contentText = loadContent()

  initStyle(UI.globalStyle)
}
