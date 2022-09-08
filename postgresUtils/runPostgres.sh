docker run --name postgresql -e POSTGRES_USER=kotlincrud -e POSTGRES_PASSWORD=secretpw -p 5432:5432 -v /data:/var/lib/postgresql/data -d postgres
