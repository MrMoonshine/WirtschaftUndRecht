package UI

import scalafx.scene.effect.DropShadow
import scalafx.scene.paint.Color.{Pink, Purple}
import scalafx.scene.paint.{LinearGradient, Stops}
import scalafx.scene.text.Text

class Title(title_i:String) extends Text{
    text = title_i
    style = "-fx-font-size: 16pt"
    fill = new LinearGradient(
      endX = 0,
      stops = Stops(Pink, Purple)
    )
    effect = new DropShadow {
      color = Purple
      radius = 25
      spread = 0.25
    }
}
