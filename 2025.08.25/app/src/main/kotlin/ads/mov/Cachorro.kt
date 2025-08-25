package ads.mov

class Cachorro( nome: String ): Animal (nome){
    override fun fazerSom(): String{
        return "Au au"
    }
}