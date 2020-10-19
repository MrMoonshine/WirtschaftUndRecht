package UI.Input

import javafx.event.ActionEvent
import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.{HBox, VBox}

object Avab extends VBox{
  var numbOfChildren:Int = 0
  private val plusbutt:Button = new Button("+"){
    onAction =  (event:ActionEvent) =>{
      updateNumb(1)
    }
  }
  private val minusbutt:Button = new Button("-"){
    onAction =  (event:ActionEvent) =>{
      updateNumb(-1)
    }
  }
  private val numblabel:Label = new Label(s"$numbOfChildren"){
    style = "-fx-padding: 8px;"
  }

  private def updateNumb(a:Int): Unit ={
    if(numbOfChildren + a >= 0){
      numbOfChildren += a
    }
    numblabel.setText(s"$numbOfChildren")
  }

  children = Seq(
    new UI.smallInputTitle("AVAB/AEVB"),
    new Label("Wie viele Kinder?"),
    new HBox{
      children = Seq(
        minusbutt,
        numblabel,
        plusbutt
      )
    }
  )
}
