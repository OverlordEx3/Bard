package com.polaris.bard.presentation.character

import androidx.lifecycle.ViewModel

data class CharacterViewModel(val name: String,
                         val race: String,
                         val className: String,
                         val level: Int,
                         val experiencePoints: Int,
                         val traits: List<Trait>,
                         val attributes: List<Attribute>,
                         val abilities: List<Ability>,
) : ViewModel() {
}

data class Trait(val name: String, val value: Int)
data class Attribute(val name: String,
                     val shortName: String,
                     val value: Int,
                     val modifier: Int)
data class Ability(val name: String, val value: Int)