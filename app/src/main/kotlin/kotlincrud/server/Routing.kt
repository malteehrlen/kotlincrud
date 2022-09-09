package kotlincrud.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import kotlincrud.routes.*

fun Application.configureRouting() {
    routing { customerRouting() }
}
