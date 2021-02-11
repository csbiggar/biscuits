import helpers.assertThatJson
import org.assertj.core.api.Assertions.assertThat
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Status
import org.junit.jupiter.api.Test
import org.skyscreamer.jsonassert.JSONAssert

class ApplicationTest {

    @Test
    fun `should indicate status for use with readiness and liveness probe`() {
        // When
        val result = createApp()(Request(Method.GET, "/"))

        // Then
        assertThat(result.status).isEqualTo(Status.OK)
        assertThat(result.bodyString()).isEqualTo("Pact example provider application")
    }

    @Test
    fun `should return canned response`() {
        // When
        val result = createApp()(Request(Method.GET, "/biscuits/1"))

        assertThat(result.status).isEqualTo(Status.OK)
        assertThat(result.header("Content-Type")).isEqualTo("application/json; charset=utf-8")

        assertThatJson(result.body.toString()).isExactlyEqualTo(""" { "name" : "Club" }""", )
    }

    @Test
    fun `should return all biscuits`() {
        // When
        val result = createApp()(Request(Method.GET, "/biscuits"))

        assertThat(result.status).isEqualTo(Status.OK)
        assertThat(result.header("Content-Type")).isEqualTo("application/json; charset=utf-8")

        assertThatJson(result.body.toString()).isExactlyEqualTo(""" [{ "name" : "Club" }, { "name" : "Trio" }]""",)
    }

    @Test
    fun `should handle not found`() {
        // When
        val result = createApp()(Request(Method.GET, "/biscuits/missing"))

        assertThat(result.status).isEqualTo(Status.NOT_FOUND)
        assertThat(result.header("Content-Type")).isEqualTo("application/json; charset=utf-8")

        JSONAssert.assertEquals(""" { "message" : "There is no biscuit with id missing" }""", result.body.toString(), false)
    }

}