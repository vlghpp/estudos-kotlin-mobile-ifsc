package ads.mov.comviewmodel

import ads.mov.comviewmodel.ui.theme.JogoDeDadosTheme
import ads.mov.comviewmodel.views.DadosViewModal
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.EaseInOutBounce
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ads.mov.comviewmodel.enums.SituacaoJogo


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JogoDeDadosTheme {
                JogoDeDadosScreen()
            }
        }
    }
}

fun imagemDado(dado: Int): Int {
    return when (dado) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
}

@Composable
fun JogoDeDadosScreen(modifier: Modifier = Modifier, viewModel: DadosViewModal = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    var dado1 by remember { mutableIntStateOf(6) }
    var dado2 by remember { mutableIntStateOf(6) }
    var situacao by remember { mutableStateOf(SituacaoJogo.INICIO) }
    var pontoASerBuscado by remember { mutableIntStateOf(0) }

    // Define a cor de fundo de acordo com a situação do jogo
    val corDeFundoDeAcordoComSituacao = when (uiState.situacaoJogo) {
        SituacaoJogo.INICIO -> MaterialTheme.colorScheme.primaryContainer
        SituacaoJogo.PONTO -> Color(0xFFFFF9C4)
        SituacaoJogo.VENCEU -> Color(0xFFC8E6C9)
        SituacaoJogo.PERDEU -> Color(0xFFFFCDD2)
    }

    // Anima a transição de cor de fundo
    val corComAnimacao by animateColorAsState(
        targetValue = corDeFundoDeAcordoComSituacao,
        animationSpec = tween(
            durationMillis = 1000, // velocidade da animação
            easing = EaseInOutBounce // curva elástica
        )
    )

    Surface(
        modifier = modifier.fillMaxSize(),
        color = corComAnimacao
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .windowInsetsPadding(WindowInsets.statusBars),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = viewModel.getMensagem(),
                style = MaterialTheme.typography.headlineMedium
            )
            Row {
                Image(
                    painter = painterResource(imagemDado(uiState.dado1)),
                    contentDescription = null,
                    modifier = Modifier.weight(1f)
                )
                Image(
                    painter = painterResource(imagemDado(uiState.dado2)),
                    contentDescription = null,
                    modifier = Modifier.weight(1f)
                )
            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { viewModel.jogarDados() }
            ) {
                Text(
                    text = viewModel.getEncerramentoJogo(),
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun JogoDeDadosPreview() {
    JogoDeDadosScreen()
}
