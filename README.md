# kotlincrud
example microservice without bloat such as spring or ORM
## Start the services
Run `docker-compose --env-file dbcredentials.env up -d`. This will start postgres and kotlincrud.

Create the table by running `psql -h 0.0.0.0 -p 54321 --user kotlincrud -d customerdb -f postgresUtils/createTable.sql`. [excercise: add version controlled db migrations using Flyway]

## Try it out

* Create a customer: `curl -H "Content-Type: application/json" -d @body.json 0.0.0.0:8080/customer`
* Get it: `curl 127.0.0.1:8080/customer?id=12345`
* Delete it: `curl -X DELETE 127.0.0.1:8080/customer?id=12345`
* You can test the update part by modifying `body.json`
