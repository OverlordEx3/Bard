package com.polaris.bard

import com.polaris.bard.presentation.character.Ability
import com.polaris.bard.presentation.character.Attribute
import com.polaris.bard.presentation.character.CharacterViewModel
import com.polaris.bard.presentation.character.Trait
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

class Modules {

    companion object {

        private val viewModels: Module = module {
            viewModel {
                CharacterViewModel(
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
            }
        }


        val all = listOf<Module>(viewModels)
    }
}

