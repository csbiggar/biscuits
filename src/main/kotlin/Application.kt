import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Netty
import org.http4k.server.asServer

fun main() {
    val port = 9000
    createApp()
        .asServer(Netty(port))
        .start()

    println("App running on http://localhost:$port")
}

fun createApp() = routes(
    "/" bind Method.GET to { request: Request -> Response(OK).body("Pact example provider application") },
    "status" bind Method.GET to ::getStatus,
)

fun getStatus(request: Request): Response {
    return Response(OK).body("Ready to go!")
}


