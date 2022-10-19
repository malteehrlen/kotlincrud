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
                    ?: return@get call.respondText(
                        text = "Missing id", 
                        status = HttpStatusCode.BadRequest
                    )
            val customer =
                Repository.getCustomer(id)
                    ?: return@get call.respondText(
                        text = "No customer with id $id",
                        status = HttpStatusCode.NotFound
                    )
            call.respond(customer)
        }

        post {
            val customer = call.receive<Customer>()
            try {
                Repository.setCustomer(customer)
                call.respond(HttpStatusCode.OK)
            } catch (e: Throwable) {
                call.respondText(
                        text = "Could not set customer", 
                        status = HttpStatusCode.BadRequest
                    )
            }
        }

        delete("{id?}") {
            val id =
                call.parameters["id"]
                    ?: return@delete call.respondText(
                        text = "Missing id",
                        status = HttpStatusCode.BadRequest
                    )
            try {
                Repository.deleteCustomer(id)
                call.respond(HttpStatusCode.OK)
            } catch (e: Throwable) {
                call.respondText(
                    text = "Could not delete customer", 
                    status = HttpStatusCode.BadRequest
                )
            }
        }
    }
}
