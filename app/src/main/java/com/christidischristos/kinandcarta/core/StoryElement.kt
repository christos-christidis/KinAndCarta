package com.christidischristos.kinandcarta.core

sealed class StoryElement
data class TextElement(val text: String) : StoryElement()
data class ImageElement(val imageUrl: String) : StoryElement()
