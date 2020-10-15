package UI

import scalafx.scene.text.Text

class Title(title_i:String) extends Text{
    text = title_i
    style = "-fx-font-size: 16pt"
    fill = UI.title_color
}
