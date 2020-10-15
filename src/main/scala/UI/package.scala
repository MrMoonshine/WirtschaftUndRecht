import scalafx.scene.paint.Color
import scalafx.stage.StageStyle

package object UI {
  lazy val background_color:Color = Color.rgb(40,40,40)
  lazy val foreground_color:Color = Color.White
  lazy val title_color:Color = Color.DarkCyan
  lazy val subtitle_color:Color = Color.Yellow
  lazy val globalStyle:StageStyle = StageStyle.Transparent
}
