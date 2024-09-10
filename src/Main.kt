//Clase principal llamada candidato, contiene los nombres y tipos de medios los cuales pueden elegir los usuarios
data class Candidato(val nombre:String, var VotoInternet: Int=0, var VotoRadio: Int=0, var VotoTelevision: Int=0){
    fun CalcularValorVotos():Int{
        val CostInternet = VotoInternet * 700000
        val CostRadio = VotoRadio * 200000
        val CostTelevision = VotoTelevision * 600000
        return CostInternet + CostRadio + CostTelevision
    }
    //Calcula el total de votos en la campaña municipal
    fun TotalVotos():Int{
        return VotoInternet+VotoRadio+VotoTelevision
    }
    //Funcion para calcular los porcentajes
    fun PorcentajeVotos(TotalVotos:Int):Double{
        if(TotalVotos==0) return 0.0
        return(TotalVotos().toDouble()/TotalVotos)*100
    }
}
//Por cada opcion 1,2, y 3 que tome el usuario va a acumular votos a los diferentes medios (Internet, TV o Radio)
fun AdicionarVoto (Candidato:Candidato, medio: Int?){
    when(medio){
        1 -> Candidato.VotoInternet++
        2 -> Candidato.VotoRadio++
        3 -> Candidato.VotoTelevision++
        else -> println("Medio no válido")
    }
}

//Funcion principal
fun main() {
    //Se definen a los 3 tipos de candidatos con nombres fijos
    val Candidato1 = Candidato("JhonF Cruger")
    val Candidato2 = Candidato("Harley Morizon F")
    val Candidato3 = Candidato("Martin Luther M")

    //Variable TotalVotos para que la inicialice en ceros
    var TotalVotos = 0

    //Menú incial
    while(true){
        println(" ")
        println("Bienvenido a las campañas para representante del municipio")
        println("1- Votar por un candidato")
        println("2- Mostrar costo de campaña por candidato")
        println("3- Vaciar urnas de votación")
        println("4- Ver el número total de votos")
        println("5- Ver porcentaje de votos por candidato")
        println("6- Ver costo promedio de la campaña en las elecciones")
        println("7- Ver el candidato ganador")
        println("8- Salir")
        println(" ")

        val opcion = readLine()?.toIntOrNull() ?: continue
        when(opcion){

            1 -> {
                println("Elija a un candidato:")
                //Mostrará los candidatos con sus nombres
                println("1- ${Candidato1.nombre}")
                println("2- ${Candidato2.nombre}")
                println("3- ${Candidato3.nombre}")
                //Guarda el candidato que eligieron
                val candidatoElegido = readLine()?.toIntOrNull()

                println("Seleccione el medio que lo impulsó:")
                println("1- Internet")
                println("2- Radio")
                println("3- Televisión")
                //Guarda el medio que seleccionaron
                val medioSeleccionado = readLine()?.toIntOrNull()

                when(candidatoElegido){
                    1 -> AdicionarVoto (Candidato1, medioSeleccionado)
                    2 -> AdicionarVoto (Candidato2, medioSeleccionado)
                    3 -> AdicionarVoto (Candidato3, medioSeleccionado)
                    else -> println("Candidato no existe, seleccione de nuevo")
                }
                //Acumula los votos por cada usuario que elige un candidato y medio
                TotalVotos++
            }

            2 -> {
                println("Elija un candidato a calcular el costo de campaña:")
                println("1- ${Candidato1.nombre}")
                println("2- ${Candidato2.nombre}")
                println("3- ${Candidato3.nombre}")
                //Guarda el candidato al cual se va a calcular el costo de su campaña
                val candidatoElegido = readLine()?.toIntOrNull()

                when(candidatoElegido){
                    //toma cada candidato dependiendo la eleccion del usuario y realiza el cálculo
                    1 -> println("Valor de campaña de: ${Candidato1.nombre}= ${Candidato1.CalcularValorVotos()}")
                    2 -> println("Valor de campaña de: ${Candidato2.nombre}= ${Candidato2.CalcularValorVotos()}")
                    3 -> println("Valor de campaña de: ${Candidato3.nombre}= ${Candidato3.CalcularValorVotos()}")
                    else -> println("Candidato no existe, seleccione de nuevo")
                }
            }

            3 -> {
                //Por los 3 candidatos se reinician los valores de cada voto y votos totales para que queden en ceros
                Candidato1.VotoInternet = 0
                Candidato1.VotoRadio = 0
                Candidato1.VotoTelevision = 0
                Candidato2.VotoInternet = 0
                Candidato2.VotoRadio = 0
                Candidato2.VotoTelevision = 0
                Candidato3.VotoInternet = 0
                Candidato3.VotoRadio = 0
                Candidato3.VotoTelevision = 0
                TotalVotos = 0
                println("Las urnas fueron vaciadas")
            }

            4 -> {
                println("Número total de votos: $TotalVotos")
            }

            5 -> {
                println("Pocentaje votos por cada candidato:")
                println("${Candidato1.nombre}: ${Candidato1.PorcentajeVotos(TotalVotos)}%")
                println("${Candidato2.nombre}: ${Candidato2.PorcentajeVotos(TotalVotos)}%")
                println("${Candidato3.nombre}: ${Candidato3.PorcentajeVotos(TotalVotos)}%")
            }

            6 -> {
                val totalValorVotos = Candidato1.CalcularValorVotos()+Candidato2.CalcularValorVotos()+Candidato3.CalcularValorVotos()
                val valorPromedio = if(TotalVotos == 0) 0 else totalValorVotos / TotalVotos
                println("El costo promedio de campaña por voto es: $valorPromedio")
            }

            7 -> {
                val gano = when{
                    Candidato1.TotalVotos() > Candidato2.TotalVotos() && Candidato1.TotalVotos() > Candidato3.TotalVotos() -> Candidato1.nombre
                    Candidato2.TotalVotos() > Candidato1.TotalVotos() && Candidato2.TotalVotos() > Candidato3.TotalVotos() -> Candidato2.nombre
                    Candidato3.TotalVotos() > Candidato1.TotalVotos() && Candidato3.TotalVotos() > Candidato2.TotalVotos() -> Candidato3.nombre
                    else -> "Quedó un empate"
                }
                println("El candidato que ganó es: $gano")
            }

            8 -> break
            else -> println("Selección inválida")
        }
    }
}


