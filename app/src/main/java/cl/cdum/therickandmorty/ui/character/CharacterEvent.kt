package cl.cdum.therickandmorty.ui.character

sealed class CharacterEvent {
    data class NavigateToDetailScreen(val identifier: Int) : CharacterEvent()
}