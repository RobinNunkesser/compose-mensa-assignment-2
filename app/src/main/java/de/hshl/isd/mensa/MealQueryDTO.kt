package de.hshl.isd.mensa

import io.github.italbytz.ports.meal.MealQuery
import java.time.LocalDate

class MealQueryDTO(override val mensa: Int, override val date: LocalDate) : MealQuery {

}
