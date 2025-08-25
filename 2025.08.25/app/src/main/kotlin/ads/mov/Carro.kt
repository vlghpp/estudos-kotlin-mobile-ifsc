package ads.mov

class Carro() {
    companion object{
        private const val VELOCIDADE_MAXIMA = 200
    }
    var velocidade: Int = 0
        set (value){
            require(value >= 0) { "Velocidade não pode ser negativa" }
            field = value
        }

        get (){
            return ((field * 0.621371).toInt())
        }
}