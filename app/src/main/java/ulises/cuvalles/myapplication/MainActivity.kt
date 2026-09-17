package ulises.cuvalles.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ulises.cuvalles.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    val personas = arrayListOf(
        Data("Juan Perez", "Programador Web", 84),
        Data("Ulises Sánchez Camarena", "Programador Web", 84),
        Data("María Rodríguez", "Diseñadora UX/UI", 92),
        Data("Carlos Mendoza", "Desarrollador Backend", 78),
        Data("Ana Martínez", "Ingeniera de Software", 95),
        Data("Luis Hernández", "Administrador de Bases de Datos", 88),
        Data("Sofía Torres", "Desarrolladora Mobile", 90),
        Data("Fernando Gómez", "DevOps Engineer", 83),
        Data("Valeria Ramírez", "Analista de Datos", 89)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    myColum(personas, modifier = Modifier.padding(innerPadding))
                    myRowLazy()
                }
            }
        }
    }
}

@Composable
fun myColum(personas: List<Data>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        items(personas) { item ->
            Card(
                modifier = Modifier
                    .padding(all = 16.dp)
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.onSecondary)
            ) {
                Text(text = item.nombre)
                Text(text = item.description)
                Text(text = "${item.likes} Me gusta")
            }

        }
    }
}

@Preview(showBackground = true, heightDp = 800, widthDp = 600)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        myColum(
            listOf(
                Data("Juan Perez", "Programador Web", 84),
                Data("Ulises Sánchez Camarena", "Programador Web", 84),
                Data("María Rodríguez", "Diseñadora UX/UI", 92),
                Data("Carlos Mendoza", "Desarrollador Backend", 78),
                Data("Ana Martínez", "Ingeniera de Software", 95),
                Data("Luis Hernández", "Administrador de Bases de Datos", 88),
                Data("Sofía Torres", "Desarrolladora Mobile", 90),
                Data("Fernando Gómez", "DevOps Engineer", 83),
                Data("Valeria Ramírez", "Analista de Datos", 89)


            )
        )
        //myRowLazy()
    }
}