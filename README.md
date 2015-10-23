# vefaval
Demo/test-repo.

Build and start server:

````
mvn package && java -jar target/vefaval-0.0.1-SNAPSHOT.jar
````

Then visit: [http://localhost:8080/](http://localhost:8080/)

To validate a local file using `curl`:


````
curl -F file=@/path/to/file.xml http://localhost:8080/validate
```
