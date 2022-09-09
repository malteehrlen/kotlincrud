package kotlincrud.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlincrud.models.*

fun Route.customerRouting() {
    route("/customer") {
        get("{id?}") {
            val id =
                call.parameters["id"]
                    ?: return@get call.respondText("Missing id", status = HttpStatusCode.BadRequest)
            val customer =
                DB.getCustomer(id)
                    ?: return@get call.respondText(
                        "No customer with id $id",
                        status = HttpStatusCode.NotFound
                    )
            call.respond(customer)
        }

        post {
            val customer = call.receive<Customer>()
            try {
                DB.setCustomer(customer)
                call.respond(HttpStatusCode.OK)
            } catch (e: Throwable) {
                call.respondText("Could not set customer", status = HttpStatusCode.BadRequest)
            }
        }

        delete("{id?}") {
            val id =
                call.parameters["id"]
                    ?: return@delete call.respondText(
                        "Missing id",
                        status = HttpStatusCode.BadRequest
                    )
            try {
                DB.deleteCustomer(id)
                call.respond(HttpStatusCode.OK)
            } catch (e: Throwable) {
                call.respondText("Could not delete customer", status = HttpStatusCode.BadRequest)
            }
        }
    }
}
