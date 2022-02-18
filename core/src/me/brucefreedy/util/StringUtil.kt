package me.brucefreedy.util

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.nio.file.Files
import java.nio.file.Paths


object StringUtil {
    @JvmStatic
    fun replaceUnexpectedSyntax(string: String) = string
        .replace(",,", ",")
        .replace(", \n}", "\n}")
        .replace(", \n]", "\n]")

    @JvmStatic
    @Throws(Exception::class)
    fun readFileAsString(file: String?) = String(Files.readAllBytes(Paths.get(file)))

    @JvmStatic
    fun parseJsonFile(file: String): JsonObject =
        JsonParser().parse(replaceUnexpectedSyntax(readFileAsString(file)).apply { println(this) }).asJsonObject

}