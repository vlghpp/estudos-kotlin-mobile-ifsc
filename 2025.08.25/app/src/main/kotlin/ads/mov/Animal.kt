package ads.mov

open class Animal( val nome: String ) {
    open fun fazerSom(): String {
        return "Som genérico"
    }
    fun dormir(){
        println("Está dormindo...")
    }
}