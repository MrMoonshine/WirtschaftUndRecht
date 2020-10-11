package lohnsteuer.perpetual

package object MonatslohnsteuerTabelle {
  object cl0 extends Constants{
    grenzsteuersatz = 0
    kinder = Array[Double](0,0,0,0,0,0)
  }
  object cl1 extends Constants{
    grenzsteuersatz = 25.0/100.0
    kinder = Array[Double](266.5,307.67,322.25,340.58,358.92,377.25)
  }
  object cl2 extends Constants{
    grenzsteuersatz = 35.0/100.0
    kinder = Array[Double](418.1,459.27,473.85,492.18,510.52,528.85)
  }
  object cl3 extends Constants{
    grenzsteuersatz = 42.0/100.0
    kinder = Array[Double](600.05,641.22,655.8,674.14,692.48,710.81)
  }
  object cl4 extends Constants{
    grenzsteuersatz = 48.0/100.0
    kinder = Array[Double](901.01,942.18,956.76,975.1,993.43,1011.77)
  }
  object cl5 extends Constants{
    grenzsteuersatz = 50.0/100.0
    kinder = Array[Double](1051.33,1092.50,1107.08,1125.42,1143.76,1162.09)
  }
  object cl6 extends Constants{
    grenzsteuersatz = 55.0/100.0
    kinder = Array[Double](5218.80,5259.97,5274.55,5292.88,5311,22,5329.55)
  }
}
