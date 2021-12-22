package com.christidischristos.kinandcarta.network

import com.christidischristos.kinandcarta.core.ImageElement
import com.christidischristos.kinandcarta.core.StoryElement
import com.christidischristos.kinandcarta.core.TextElement
import com.google.gson.*
import java.lang.reflect.Type

class CaseStudyBodyElementDeserializer : JsonDeserializer<StoryElement>,
    JsonSerializer<StoryElement> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): StoryElement? {
        return try {
            json?.asString?.let {
                TextElement(it)
            }
        } catch (e: Exception) {
            json?.asJsonObject?.get("image_url")?.asString?.let {
                ImageElement(it)
            }
        }
    }

    override fun serialize(
        src: StoryElement?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return when (src) {
            is ImageElement -> JsonObject().apply { addProperty("image_url", src.imageUrl) }
            is TextElement -> JsonPrimitive(src.text)
            null -> JsonNull.INSTANCE
        }
    }
}
