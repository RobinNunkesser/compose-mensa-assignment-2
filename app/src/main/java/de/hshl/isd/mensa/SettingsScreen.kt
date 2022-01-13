package de.hshl.isd.mensa

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import de.hshl.isd.mensa.ui.theme.MensaTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

@Composable
fun Settings(navController: NavController) {
    val radioOptions = listOf("Student", "Employee", "Pupil", "Other")
    var selectedOption by remember { mutableStateOf(radioOptions[prefs.getInt(statusKey, statusDefaultValue)]) }

    MensaTheme {
        Scaffold {
            Column(Modifier.selectableGroup()) {
                radioOptions.forEach { text ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .selectable(
                                selected = (text == selectedOption),
                                onClick = {
                                    selectedOption = text
                                    prefs.edit().putInt(statusKey,radioOptions.indexOf(text)).apply()
                                          },
                                role = Role.RadioButton
                            )
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (text == selectedOption),
                            onClick = null
                        )
                        Text(
                            text = text,
                            style = MaterialTheme.typography.body1.merge(),
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }
        }
    }
}

