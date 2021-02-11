package helpers

import mu.KotlinLogging
import org.skyscreamer.jsonassert.JSONAssert

private val logger = KotlinLogging.logger {}

fun assertThatJson(actualJson: String) = JsonToBeAsserted(actualJson)

data class JsonToBeAsserted(val actualJson: String) {
    fun isExactlyEqualTo(expectedJson: String) {
        try {
            JSONAssert.assertEquals(expectedJson, actualJson, true)
        } catch (e: Throwable) {
            logError()
            throw e
        }
    }

    fun contains(expectedJson: String) {
        try {
            JSONAssert.assertEquals(expectedJson, actualJson, false)
        } catch (e: Throwable) {
            logError()
            throw e
        }
    }

    private fun logError() {
        logger.error {
            """
                        
                        
    --------------------------------------------------------------------------
    Json is not as expected. Actual json: (see below this for assertion error) 
    --------------------------------------------------------------------------
    
    $actualJson
    
    --------------------------------------------------------------------------
 """.trimIndent()
        }
    }
}