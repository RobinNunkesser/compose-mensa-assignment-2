package de.hshl.isd.mensa

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
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

    fun success(collections: List<MealCollection>) {
        viewModel.collections = collections.map { collection ->
            CollectionViewModel(name = collection.category.toString(),
                meals = collection.meals.map { meal -> MealViewModel(meal.name, meal.price.employees!!.toString(), meal.image) }) }
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

