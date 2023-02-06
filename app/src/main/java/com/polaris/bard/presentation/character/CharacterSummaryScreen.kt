package com.polaris.bard.presentation.character

import android.content.ClipData.Item
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.polaris.bard.presentation.*
import org.koin.androidx.compose.getViewModel

@Preview
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

    CharacterSummaryScreen(
        viewModel = viewModel
    )
}

@Composable
fun CharacterSummaryScreen(
    viewModel: CharacterViewModel = getViewModel(),
) {
    val scrollState = rememberScrollState()
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .verticalScroll(scrollState)
    ) {
        CharacterHeader(
            name = viewModel.name,
            race = viewModel.race,
            className = viewModel.className,
            level = viewModel.level,
            exp = viewModel.experiencePoints,
        )

        Traits(traits = viewModel.traits)

        AttributesSection(attributes = viewModel.attributes)

        AbilitiesSection(abilities = viewModel.abilities)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Item(
    headLine: String,
    trailing: @Composable RowScope.() -> Unit
) {
    ListItem(
        headlineText = {
            Text(
                text = headLine,
                fontSize = 20.sp,
            )
        },
        trailingContent = {
            Row {
                trailing()
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AbilitiesSection(abilities: List<Ability>) {
    Text(text = "Habilidades")
    Column {
        abilities.forEach {
            ListItem(
                headlineText = {
                    Text(
                        text = it.name,
                        fontSize = 20.sp,
                    )
                },
                trailingContent = {
                    Text(
                        text = it.value.toString(),
                        fontSize = 20.sp
                    )
                },
            )

            Divider(modifier = Modifier.padding(horizontal = 5.dp))
        }
    }
}

@Composable
private fun AttributesSection(
    attributes: List<Attribute>
) {
    Text(text = "Atributos")
    Column {
        attributes.forEach {
            AttributeItem(attribute = it)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AttributeItem(attribute: Attribute) {
    ListItem(
        headlineText = {
            Text(
                text = attribute.shortName,
                fontSize = 20.sp
            )
        },
        trailingContent = {
            Text(
                text = "${attribute.value} (+${attribute.modifier})",
                fontSize = 20.sp
            )
        }
    )
    Divider()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Traits(traits: List<Trait>) {
    Text(text = "Rasgos")
    Column {
        traits.forEach {
            ListItem(
                headlineText = {
                    Text(
                        text = it.name,
                        fontSize = 20.sp
                    )
                },
                trailingContent = {
                    BasicTextField (
                        value = it.value.toString(),
                        singleLine = true,
                        onValueChange = { /* TODO */ },
                        textStyle = TextStyle(
                            fontSize = 20.sp,
                        ),
                        modifier = Modifier.border(),

                        )
                },
            )
            Divider()
        }
    }
}

data class Border(val width: Dp, val brush: Brush)

@Stable
private fun Modifier.Borders(start: Border? = null,
                             top: Border? = null,
                             end: Border? = null,
                             bottom: Border? = null
) = drawBehind {

    start?.let {
        val width = it.width.toPx()
        if(width == 0f) return@let

        drawBorder(border = it) {
            moveTo(0f, 0f)
            lineTo(x = 0f, y = if(end != null) size.height - width else size.height)
        }
    }

    top?.let {
        drawBorder(border = it) {

        }
    }

    end?.let {
        drawBorder(border = it) {

        }
    }

    bottom?.let {
        drawBorder(border = it) {

        }
    }
}



private fun DrawScope.drawBorder(
    border: Border,
    path: Path.() -> Unit,
) {
    drawPath(
        Path().apply {
            path()
        },
        brush = border.brush,
    )
}

@Composable
private fun CharacterHeader(
    name: String,
    className: String,
    race: String,
    level: Int,
    exp: Int
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = name,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
        )

        Divider(
            thickness = 1.dp,
            color = Color.Gray,
            modifier = Modifier
                .padding(start = 15.dp, top = 5.dp, end = 15.dp, bottom = 5.dp)
                .fillMaxWidth()
        )

        Text(
            text = "$race - $className",
            fontSize = 22.sp,
        )
        Text(
            text = "Lvl. $level ($exp exp.)",
            fontSize = 16.sp,
            fontStyle = FontStyle.Italic,
        )
    }
}