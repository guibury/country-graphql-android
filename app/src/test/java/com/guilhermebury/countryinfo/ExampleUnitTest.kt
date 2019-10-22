package com.guilhermebury.countryinfo

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    private val mock = "{\n" +
            "  \"data\": {\n" +
            "    \"country\": {\n" +
            "      \"name\": \"Argentina\",\n" +
            "      \"native\": \"Argentina\",\n" +
            "      \"emoji\": \"\uD83C\uDDE6\uD83C\uDDF7\",\n" +
            "      \"continent\": {\n" +
            "        \"name\": \"South America\"\n" +
            "      },\n" +
            "      \"languages\": [\n" +
            "        {\n" +
            "          \"name\": \"Spanish\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"name\": \"Guarani\"\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  }\n" +
            "}"
}
