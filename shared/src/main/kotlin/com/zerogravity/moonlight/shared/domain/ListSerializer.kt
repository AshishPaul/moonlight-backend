package com.zerogravity.moonlight.shared.domain

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonTransformingSerializer

class DataListSerializer<T>(
    serializer: KSerializer<T>
) : JsonTransformingSerializer<List<T>>(ListSerializer(serializer)) {
    // If response is not an array, then it is a single object that should be wrapped into the array
    override fun transformDeserialize(element: JsonElement): JsonElement =
        if (element !is JsonArray) {
            JsonArray(listOf(element))
        } else {
            if (element.isEmpty()) {
                JsonArray(listOf())
            } else {
                element
            }
        }
}