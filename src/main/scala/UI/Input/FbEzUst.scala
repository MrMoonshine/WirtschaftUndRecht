package UI.Input

import UI.Title
import scalafx.scene.control.{Accordion, TitledPane}

object FbEzUst extends Accordion{
  panes = List(
    new TitledPane {
      text = "Freibetrag"
      content = Freibetrag
    },
    new TitledPane {
      text = "Pendlerpauschale"
      content = Pendlerpauschale
    },
    new TitledPane {
      text = "Erschwerniszulagen"
      content = new Title("W.I.P")
    },
    new TitledPane {
      text = "Überstunden"
      content = new Title("W.I.P")
    },
  )
  style =
    """-fx-border-width: 1px;
      |-fx-border-color: #000000;
      |-fx-width: 100%;
      |-fx-height:100%;
      |""".stripMargin
}
