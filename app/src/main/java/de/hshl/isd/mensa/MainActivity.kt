package de.hshl.isd.mensa

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import de.hshl.isd.mensa.ui.theme.MensaTheme
import io.github.italbytz.adapters.meal.MockGetMealsCommand
import io.github.italbytz.ports.meal.MealCollection
import java.time.LocalDate

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MensaTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainContent()
                }
            }
        }
    }
}

@Composable
fun MainContent() {
    val service = MockGetMealsCommand()

    fun success(collections: List<MealCollection>) {
        Log.d("MainContent",collections.toString())
    }

    fun failure(error: Throwable) {
        Log.e("MainContent", error.localizedMessage!!)
    }

    service.execute(
        MealQueryDTO(42, LocalDate.now()),
        ::success,
        ::failure
    )

    Text(text = "Hello World!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MensaTheme {
        MainContent()
    }
}