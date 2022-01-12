package de.hshl.isd.mensa

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
    val viewModel = MensaViewModel()

    viewModel.collections += CollectionViewModel("Test", listOf(MealViewModel("S","I","T")))

    fun success(collections: List<MealCollection>) {
        Log.d("MainContent",collections.toString())
        viewModel.collections += CollectionViewModel("Test", listOf(MealViewModel("S","I","T")))
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
            LazyColumn {
                items(viewModel.collections) { collection ->
                    SectionHeader(title = collection.name)
                    collection.meals.forEach { meal ->
                        MealRow(meal)
                    }
                }
            }
        }
    }
}

