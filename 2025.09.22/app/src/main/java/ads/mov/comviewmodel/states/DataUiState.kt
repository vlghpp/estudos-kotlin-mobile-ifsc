package ads.mov.comviewmodel.states
import ads.mov.comviewmodel.enums.SituacaoJogo


data class DadosUiState(
    val dado1: Int = 6,
    val dado2: Int = 6,
    val situacaoJogo: SituacaoJogo = SituacaoJogo.INICIO,
    val pontoASerBuscado: Int = 0
)
