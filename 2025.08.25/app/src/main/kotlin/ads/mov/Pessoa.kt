package ads.mov

import java.time.LocalDate
import java.time.Period

class Pessoa (var nome: String = "Desconhecido", var dataNascimento: LocalDate = LocalDate.now()){
    fun getIdade(): Int{
        return Period.between(this.dataNascimento, LocalDate.now()).years
    }

    override fun toString(): String {
        return "Nome: $nome, Data de Nascimento: $dataNascimento, Idade: ${getIdade()}"
    }
}