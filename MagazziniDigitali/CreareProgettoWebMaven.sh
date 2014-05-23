#!/bin/bash

mvn archetype:generate \
    -DgroupId=it.opendigital.teca \
    -DartifactId=TecaSearch \
    -DarchetypeArtifactId=maven-archetype-webapp

cd TecaSearch

mvn eclipse:eclipse -Dwtpversion=2.0
