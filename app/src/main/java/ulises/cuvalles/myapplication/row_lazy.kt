package ulises.cuvalles.myapplication

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun myRowLazy(modifier: Modifier = Modifier) {
    val datos01 = listOf(
        "Lunes",
        "Martes",
        "Miercoles",
        "Jueves",
        "Viernes",
        "Sabado",
        "Domingo"
    )

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        items(datos01) { item ->
            Text(
                text = item,
                modifier = Modifier
                    .padding(end=12.dp)
                )
        }
    }
}
