import biscuits.Biscuit
import biscuits.BiscuitNotFound
import biscuits.findBiscuit
import org.http4k.core.Body
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.NOT_FOUND
import org.http4k.core.Status.Companion.OK
import org.http4k.core.with
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Netty
import org.http4k.server.asServer
import org.http4k.format.Jackson.auto
import org.http4k.routing.path

fun main() {
    val port = 9000
    createApp()
        .asServer(Netty(port))
        .start()

    println("App running on http://localhost:$port")
}

fun createApp() = routes(
    "/" bind Method.GET to { request: Request -> Response(OK).body("Pact example provider application") },
    "biscuits/{id}" bind Method.GET to ::getBiscuit,
    "biscuits" bind Method.GET to ::getAllBiscuits,
)

fun getBiscuit(request: Request): Response {

    val biscuitId = request.path("id")
    println("Requesting biscuit of Id $biscuitId")
    val biscuit = findBiscuit(biscuitId)

    return when (biscuit) {
        is BiscuitNotFound -> {
            val errorResponseShape = Body.auto<ErrorResponse>().toLens()
            Response(NOT_FOUND).with(
                errorResponseShape of ErrorResponse("There is no biscuit with id $biscuitId")
            )
        }

        is Biscuit -> {
            val responseShape = Body.auto<Biscuit>().toLens()
            Response(OK).with(
                responseShape of biscuit
            )
        }
    }
}

fun getAllBiscuits(request: Request): Response {
    val biscuits = listOf(
        findBiscuit("1"),
        findBiscuit("2")
    )
        .filterIsInstance<Biscuit>()

    val responseShape = Body.auto<List<Biscuit>>().toLens()
    return Response(OK).with(
        responseShape of biscuits
    )
}

private data class ErrorResponse(val message: String)

