package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HidratacaoComEstado()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun Hidratacao(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .padding(16.dp)
            .windowInsetsPadding(WindowInsets.statusBars),
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ){
            var contador: Int by remember { mutableIntStateOf(0) }
            Text(
                modifier =Modifier.padding(vertical = 15.dp),
                text = "Você bebeu $contador copos de água",
                style = MaterialTheme.typography.headlineSmall
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { contador++ }
            ){
                Text(
                    text = "Beber águar",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }
}

@Composable
fun HidratacaoSemEstado(
    contador: Int,
    texto: String,
    onValueChange: () -> Unit, // Callback para beber água
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        Text("Você bebeu $contador copos de $texto hoje!")
        Button(
            onClick = { onValueChange() }, enabled = contador < 10
        ) {
            Text("Beber")
        }
    }
}
@Composable
fun HidratacaoComEstado(modifier: Modifier = Modifier) {
    var contadorAgua by rememberSaveable { mutableIntStateOf(0) }
    var contadorSuco by rememberSaveable { mutableIntStateOf(0) }
    Column(modifier= modifier.fillMaxSize()) {
        HidratacaoSemEstado(contador = contadorAgua, texto = "água", onValueChange = { contadorAgua++ })
        HidratacaoSemEstado(contador = contadorSuco, texto = "suco", onValueChange = { contadorSuco++ })
    }
}

fun faceDado(valorDado: Int): Int {
    val imagem = when (valorDado) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    return imagem
}

@Composable
fun primeiraVez(valorDado1: Int, valorDado2: Int, contador: Int): Boolean {
    var soma = valorDado1 + valorDado2
    
    //true se pertence a lógica, se não ele vai continuar no while
    if (contador == 0){
        if(soma == 7 || soma == 11) return true
        else if (soma == 2 || soma == 3 || soma == 12) return false
    }
    var ponto by rememberSaveable { mutableIntStateOf(soma) }

    if (soma == ponto){
        return true
    }
}



@Composable
fun Dados( modifier: Modifier = Modifier) {
// Cria um estado reativo inteiro com valor inicial 1
// O operador 'by' em Kotlin é utilizado para delegação de propriedades
// 'face' é uma propriedade delegada a um objeto criado por 'remember { mutableStateOf(1) }'.
// Isso significa que 'face' irá armazenar um estado mutável que persiste durante recomposições do Composable
    var face1 by rememberSaveable { mutableStateOf(1) }
    var face2 by rememberSaveable { mutableStateOf(1) }
    var contador by rememberSaveable { mutableIntStateOf(0) }

    val imagem1 = faceDado(face1)
    val imagem2 = faceDado(face2)

    Column {
        Image(
            painter = painterResource(imagem1),
            contentDescription = face1.toString()
        )
        Image(
            painter = painterResource(imagem2),
            contentDescription = face1.toString()
        )
        Button(onClick = {
            face1 = (1..6).random()
            face2 = (1..6).random()
            contador++
        }) {
            Text("Jogar", fontSize = 24.sp)
        }

        if (contador == 0) {
            if(primeiraVez(face1, face2, contador)) Text(text = "VOCÊ GANHOU")
            else Text(text = "VOCÊ PERDEU")
        }


    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {

        Dados()

        //enabled = false, se ganhar = true "Você ganhou!"
    }
}