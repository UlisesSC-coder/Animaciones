package ulises.cuvalles.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ulises.cuvalles.myapplication.ui.theme.MyApplicationTheme

private val personas = listOf(
    Data(foto = R.drawable.img, nombre = "Juan Perez", description = "Programador Web", likes = 84),
    Data(foto = R.drawable.img_1, nombre = "Ulises Sánchez Camarena", description = "Programador Web", likes = 84),
    Data(foto = R.drawable.img_2, nombre = "María Rodríguez", description = "Diseñadora UX/UI", likes = 92),
    Data(foto = R.drawable.img_3, nombre = "Carlos Mendoza", description = "Desarrollador Backend", likes = 78),
    Data(foto = R.drawable.img_4, nombre = "Ana Martínez", description = "Ingeniera de Software", likes = 95),
    Data(foto = R.drawable.img_5, nombre = "Valeria Ramírez", description = "Analista de Datos", likes = 89)
)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        MyColum(personas, modifier = Modifier.weight(1f))
                        myRowLazy()
                    }
                }
            }
        }
    }
}

@Composable
fun MyColum(personas: List<Data>, modifier: Modifier = Modifier) {
    var seleccionado by remember { mutableStateOf<Data?>(null) }

    LazyColumn(modifier = modifier.fillMaxWidth()) {
        items(personas) { item ->
            PersonaCard(
                item = item,
                estaSeleccionado = item == seleccionado,
                onClick = {
                    seleccionado = if (seleccionado == item) null else item
                }
            )
        }
    }
}

@Composable
fun PersonaCard(item: Data, estaSeleccionado: Boolean, onClick: () -> Unit) {
    var expandido by remember { mutableStateOf(true) }

    val colorFondo by animateColorAsState(
        targetValue = if (estaSeleccionado) MaterialTheme.colorScheme.primaryContainer
        else MaterialTheme.colorScheme.surfaceVariant,
        label = "colorFondo"
    )
    val tamanoFoto by animateDpAsState(
        targetValue = if (estaSeleccionado) 100.dp else 80.dp,
        label = "tamanoFoto"
    )

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = colorFondo)
    ) {
        Image(
            modifier = Modifier
                .size(tamanoFoto)
                .clip(CircleShape),
            painter = painterResource(id = item.foto),
            contentDescription = "Foto de ${item.nombre}",
            contentScale = ContentScale.Crop
        )
        Text(text = item.nombre)

        AnimatedVisibility(visible = expandido) {
            Column {
                Text(text = item.description)
                Text(text = "${item.likes} Me gusta")
            }
        }

        Button(onClick = { expandido = !expandido }) {
            Text(text = if (expandido) "Ocultar" else "Mostrar")
        }
    }
}

@Preview(showBackground = true, heightDp = 800, widthDp = 600)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        MyColum(personas)
    }
}