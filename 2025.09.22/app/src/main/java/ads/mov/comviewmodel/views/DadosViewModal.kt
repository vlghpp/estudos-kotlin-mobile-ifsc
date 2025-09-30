package ads.mov.comviewmodel.views

import ads.mov.comviewmodel.enums.SituacaoJogo
import ads.mov.comviewmodel.states.DadosUiState
import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class DadosViewModal: ViewModel() {
    private val _uiState = MutableStateFlow(DadosUiState())
    val uiState = _uiState.asStateFlow()

    fun jogarDados(){
        val dado1 = (1..6).random()
        val dado2 = (1..6).random()
        val soma = dado1 + dado2

        _uiState.update { estadoAtual -> estadoAtual.copy(dado1 = dado1, dado2 = dado2) }

        val novoEstado = when (uiState.value.situacaoJogo) {
            SituacaoJogo.INICIO -> {
                when (soma) {
                    7, 11 -> DadosUiState(dado1= dado1, dado2= dado2, situacaoJogo = SituacaoJogo.VENCEU)
                    2, 3, 12 ->  DadosUiState(dado1= dado1, dado2= dado2, situacaoJogo = SituacaoJogo.PERDEU)
                    else -> DadosUiState(dado1= dado1, dado2 = dado2, pontoASerBuscado = soma, situacaoJogo = SituacaoJogo.PONTO)
                }
            }

            SituacaoJogo.PONTO -> {
                when (soma) {
                    pontoASerBuscado -> DadosUiState(dado1 = dado1, dado2 = dado2, pontoASerBuscado = soma, situacaoJogo = SituacaoJogo.VENCEU)
                    7 -> DadosUiState(dado1 = dado1, dado2 = dado2, pontoASerBuscado = soma, situacaoJogo = SituacaoJogo.PERDEU)
            }

            else -> DadosUiState(
                    dado1 = 6,
                    dado2 = 6,
                    situacaoJogo = SituacaoJogo.INICIO,
                    pontoASerBuscado = 0
                )

        }

        _uiState.value = novoEstado()
        Log.d("Dados", "Você obteve $dado1 e $dado2")
    }

    fun getMensagem(): String{
        return when (uiState.value.situacaoJogo) {
            SituacaoJogo.INICIO -> "Clique em jogar dados"
            SituacaoJogo.PONTO -> "Ponto a ser buscado: ${uiState.value.pontoASerBuscado}"
            SituacaoJogo.VENCEU -> "Você venceu"
            SituacaoJogo.PERDEU -> "Você perdeu"
        }
    }

    fun getEncerramentoJogo(): String{
        return when (uiState.value.situacaoJogo) {
            SituacaoJogo.INICIO, SituacaoJogo.PONTO -> "Jogar dados"
            else -> "Reiniciar jogo"
        }
    }




}