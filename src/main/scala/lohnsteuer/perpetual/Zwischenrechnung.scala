package lohnsteuer.perpetual

case class Zwischenrechnung(title:String){
  private val fullLine:String = "-------------------------------------------"
  private var mainStr =
    s"""$fullLine
       |$title
       |$fullLine
       |""".stripMargin

  def +=(a:String): Unit ={
    mainStr += a + "\n"
  }

  def line(): Unit ={
    mainStr += fullLine + "\n"
  }

  override def toString: String = mainStr
}
