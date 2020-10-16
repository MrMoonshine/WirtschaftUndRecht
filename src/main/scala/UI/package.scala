import scalafx.scene.paint.Color
import scalafx.stage.StageStyle

package object UI {
  lazy val background_color:Color = Color.rgb(40,40,40)
  lazy val foreground_color:Color = Color.White
  lazy val title_color:Color = Color.DarkCyan
  lazy val smalltitle_color:Color = Color.Khaki
  lazy val soconadary_title_colors:Seq[Color] = Seq(
    Color.LightBlue,
    Color.Pink,
    Color.LightGreen,
    Color.Khaki,
    Color.Lavender,
    Color.Orange
  )
  lazy val infoStyle:StageStyle = StageStyle.Transparent


}
