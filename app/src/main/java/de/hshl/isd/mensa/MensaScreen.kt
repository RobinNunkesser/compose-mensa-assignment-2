package de.hshl.isd.mensa

import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import de.hshl.isd.mensa.ui.theme.MensaTheme
import io.github.italbytz.adapters.meal.MockGetMealsCommand
import io.github.italbytz.ports.meal.MealCollection
import java.time.LocalDate

@Composable
fun Mensa(navController: NavController) {
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

    MensaTheme {
        Scaffold {

                Text(text = "Navigate deeper")

        }
    }
}

