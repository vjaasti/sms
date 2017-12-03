# plivo

How to start the plivo application
---

1. Run `mvn package` to build your application
1. Start application with `java -jar target/plivo-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running hit url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`

Get Started
---
Dump the sql(db_dumps/plivo.sql) into mysql DB 
Start memcached Server and mention the host and port in config.yml
Define other required configs in config.yml and run the application with cmd mentioned above


APIs Avaialable
---

POST    /inbound/sms 
POST    /outbound/sms 

Refer requirement Spec for API parameters

