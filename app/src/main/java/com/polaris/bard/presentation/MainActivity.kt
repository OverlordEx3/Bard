package com.polaris.bard.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.polaris.bard.Modules
import com.polaris.bard.presentation.character.*
import com.polaris.bard.ui.theme.BardTheme
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            modules(Modules.all)
        }

        setContent {
            MainScreen {
                CharacterSummaryScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MainScreen(content: @Composable () -> Unit) {
    BardTheme {
        Scaffold(
            topBar = {
                TopAppBar (
                    title = { Text(text = "Texto")},
                    navigationIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu icon")
                        }
                    }
                )
            },
        ) {
            Box(modifier = Modifier.padding(paddingValues = it)) {
                content()
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    val viewModel = CharacterViewModel(
        name = "Brandil",
        race = "Elfo",
        className = "Druida",
        level = 1,
        experiencePoints = 6,
        traits = listOf(
            Trait("PV", 4),
            Trait("PV(MAX)", 6),
            Trait("DEF", 10),
            Trait("MOV", 12),
            Trait("ATK", 0),
            Trait("PWR", 2),
            Trait("INS", 0),
        ),
        attributes = listOf(
            Attribute("Fuerza", "STR", 0, 0),
            Attribute("Destreza", "DEX", 0, 0),
            Attribute("Constitucion", "CON", 0, 0),
            Attribute("Inteligencia", "INT", 0, 0),
            Attribute("Sabiduria", "SAB", 0, 0),
            Attribute("Carisma", "CHR", 0, 0),
        ),
        abilities = listOf(
            Ability(name = "Alerta", value = 0),
            Ability(name = "Comunicación", value = 0),
            Ability(name = "Manipulación", value = 0),
            Ability(name = "Erudición", value = 0),
            Ability(name = "Subterfugio", value = 0),
            Ability(name = "Supervivencia", value = 0),
        )
    )

    MainScreen {
        CharacterSummaryScreen(viewModel = viewModel)
    }
}

