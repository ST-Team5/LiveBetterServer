#!/bin/bash
mvn clean compile war:war
cp target/live-better-0.1.0.BUILD-SNAPSHOT.war /var/lib/tomcat/webapps/lb.war
