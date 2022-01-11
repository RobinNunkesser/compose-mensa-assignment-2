package de.hshl.isd.mensa

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import de.hshl.isd.mensa.ui.theme.MensaTheme

@Composable
fun Settings(navController: NavController) {
    MensaTheme {
        Scaffold {
            Text(text = "Dashboard")
        }
    }
}

