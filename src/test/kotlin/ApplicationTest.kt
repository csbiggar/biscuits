import org.assertj.core.api.Assertions.assertThat
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Status
import org.junit.jupiter.api.Test

class ApplicationTest {

    @Test
    fun `should indicate status for use with readiness and liveness probe`() {
        // When
        val result = createApp()(Request(Method.GET, "/"))

        // Then
        assertThat(result.status).isEqualTo(Status.OK)
        assertThat(result.bodyString()).isEqualTo("Pact example provider application")
    }

}