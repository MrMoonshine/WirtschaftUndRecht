package UI

import scalafx.scene.Scene
import scalafx.scene.layout.{HBox, VBox}

class InputUI extends Scene{

  fill = UI.background_color

  content = new HBox {
    children = Seq(
      UI.Input.FbEzUst,
      new VBox{
        children = Seq(UI.Input.Sonderzahlungen, UI.Input.MonthSelecter)
        minHeight = 250
        style = "-fx-height: 100%;"
      },
      new VBox{
        children = Seq(UI.Input.Loehne, UI.Input.Submit)
        minHeight = 250
        style = "-fx-height: 100%;"
      }
    )
  }
}
