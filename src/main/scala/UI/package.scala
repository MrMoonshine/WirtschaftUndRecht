import scalafx.scene.paint.Color
import scalafx.scene.text.Text
import scalafx.stage.StageStyle

package object UI {
  lazy val background_color:Color = Color.White
  lazy val foreground_color:Color = Color.Black
  lazy val title_color:Color = Color.DarkCyan
  lazy val smalltitle_color:Color = Color.Red
  lazy val soconadary_title_colors:Seq[Color] = Seq(
    Color.Blue,
    Color.Red,
    Color.Green,
    Color.Orange
  )
  lazy val infoStyle:StageStyle = StageStyle.Decorated
  lazy val inputTitle:String = "Gib deine Daten ein"
  lazy val inputBoxStyle:String =
    """-fx-padding: 10 ;
      |-fx-border-width: 1px;
      |-fx-border-color: #000000;
      |-fx-width: 100%;
      |""".stripMargin

  lazy val style_redundant:String = "-fx-Background-color: pink ;"
  lazy val style_optional:String = "-fx-Background-color: khaki ;"
  lazy val style_used:String = "-fx-Background-color: #8ed6e6 ;"

  val height_total:Int = 300
  val height_Sonderzahlung:Int = 150
  val height_MonthSelect:Int = height_total - height_Sonderzahlung
  val height_Submit:Int = 50
  val height_Loehne:Int = height_total -height_Submit

  val heightMax_Lohnrechnung:Int = 800

  class smallInputTitle(text_i:String) extends Text{
    style = "-fx-font-size: 12pt;"
    text = text_i
  }

  def fxPadding(a:Int): String ={
    s"-fx-padding: $a ;"
  }
}
