package de.hshl.isd.mensa

import io.github.italbytz.ports.meal.Category
import io.github.italbytz.ports.meal.Meal
import io.github.italbytz.ports.meal.MealCollection
import java.text.NumberFormat
import java.util.*

fun Meal.toMealViewModel() : MealViewModel {
    var status = prefs.getInt(statusKey, statusDefaultValue)
    val price = when(status) {
        0 -> this.price.students
        1 -> this.price.employees
        2 -> this.price.pupils
        3 -> this.price.others
        else -> null
    }
    val formatter = NumberFormat.getCurrencyInstance(Locale.GERMANY)
    var priceString = "Unknown Price"
    try {
        priceString = formatter.format(price)
    } catch (e: Throwable) {

    }
    return MealViewModel(
        this.name,
        priceString,
        this.image
    )
}

fun MealCollection.toCollectionViewModel(): CollectionViewModel {
    val name = when(this.category) {
        Category.NONE -> ""
        Category.DESSERT -> "Desserts"
        Category.DISH -> "Hauptgerichte"
        Category.SIDEDISH -> "Beilagen"
        Category.SOUP -> "Suppen / Eintöpfe"
    }

    return CollectionViewModel(name = name,
        meals = this.meals.map { it.toMealViewModel() })
}