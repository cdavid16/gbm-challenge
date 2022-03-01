# GBM Backend Challenge

Prerequisites:
1. Maven 3
2. Java 11
3. Docker
4. Docker hub account
5. Make sure you have below ports: 8080, 3306, 9000
6. Postman**

How to get the code:
Clone report from [GitHub](https://github.com/cdavid16/gbm-challenge)

How to install database
1. Locate in [docker-compose.yml](src/main/resources/database) directory using the terminal
2. Execute `docker-compose up` command.
3. Execute `docker-compose ps` in the same directory, but using a different terminal instance, take note of the
container with prefix "database_gbm-challenges-server", you will need it later on.
5. Open [this URL](http://localhost:8080/) in your browser
6. Set below parameters to establish the connection properly:
- System: MySQL
- Server: gbm-challenges-server
- Username: root
- Password: cvillarreal1990
- Database: gbm_challenge
- Permanent login: Checked
6. Click on SQL Command option from the left panel.
7. Copy and paste the content from [ddl.sql](src/main/resources/database/ddl/ddl.sql).
8. Click on execute button.
9. Then click again in SQL Command option and copy and paste the content 
[issuers_dml.sql](src/main/resources/database/issuers_dml.sql).
10. Ready database is up and running.


Create network in Docker.
1. In order to make your database container visible for application container, you need to create a network using:
   `docker network create gbm-challenge-connection`
2. Then get network's IP using `docker network inspect gbm-challenge-connection`
3. Connect database container to the network created using
   `docker network connect gbm-challenge-connection database_gbm-challenges-server_1` 
(note: last parameter is container name, make sure it matches from the one you got from db installation)

How to install Java Application
1. Locate in [application folder](/).
2. Execute `mvn spring-boot:build-image` command
3. Then execute `docker run -it -p9000:9000 --env-file variables.env --network gbm-challenge-connection 
gbm-challenge:0.0.1-SNAPSHOT`
4. Application should start right away under your port 9000, you can test it using 
[this link](http://localhost:9000/api/swagger-ui/index.html).
5. In case you would like to review sample request, you can find some sample request in 
[this postman collection](src/main/resources/postman/GBM-Challenge.postman_collection.json).

How to query tables
1. Open [this URL](http://localhost:8080/) in your browser.
2. Click on the name of the table you want to query (like tbl_account to export account_id).
3. Chose **Select data** from the menu and data will be displayed.

Note:
**Is very important to keep in mind that DB has no persistance enable, hence installation 
is required if you delete the container, in case you want to enable persistance in the execution.
please `execute docker volume create --name=mydb` command and uncomment code from 
[docker-compose.yml](src/main/resources/database/docker-compose.yml) **
