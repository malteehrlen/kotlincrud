import java.sql.DriverManager
import java.sql.Connection
import kotlincrud.models.Customer

object DB {
    private val host = System.getenv("POSTGRES_HOST")
    private val port = System.getenv("POSTGRES_PORT")
    private val dbName = System.getenv("POSTGRES_NAME")
    private val dbUser = System.getenv("POSTGRES_USER")
    private val dbPassword = System.getenv("POSTGRES_PASSWORD")
    private val url = "jdbc:postgresql://$host:$port/$dbName"

    val connection = DriverManager
        .getConnection(url, dbUser, dbPassword)

    val getCustomerQuery = connection.prepareStatement(
        "SELECT id, firstName, lastName, email FROM customers WHERE id = ?;"
    )

    val setCustomerStatement = connection.prepareStatement(
        "INSERT INTO customers (id, firstName, lastName, email) VALUES (?, ?, ?, ?) ON CONFLICT (id) DO UPDATE SET firstName = ?, lastName = ?, email = ?;"
    )

    val deleteCustomerStatement = connection.prepareStatement(
        "DELETE FROM customers WHERE id = ?;"
    )

    fun getCustomer(id: String): Customer? {
        getCustomerQuery.setString(1, id)
        val res = getCustomerQuery.executeQuery()
           while (res.next()) {
                // returns the first result as there can be a maximum of one hit
                return Customer(
                    id = res.getString("id"),
                    firstName = res.getString("firstName"),
                    lastName = res.getString("lastName"),
                    email = res.getString("email"),
                )
            }
            return null
        }

    fun setCustomer(customer: Customer) {

        // in case of create
        setCustomerStatement.setString(1, customer.id)
        setCustomerStatement.setString(2, customer.firstName)
        setCustomerStatement.setString(3, customer.lastName)
        setCustomerStatement.setString(4, customer.email)

        // in case of update
        setCustomerStatement.setString(5, customer.firstName)
        setCustomerStatement.setString(6, customer.lastName)
        setCustomerStatement.setString(7, customer.email)

        setCustomerStatement.executeUpdate()
    }

    fun deleteCustomer(id: String) {
        deleteCustomerStatement.setString(1, id)

        deleteCustomerStatement.executeUpdate()
    }
}
