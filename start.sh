#!/bin/bash

# Exit immediately if any command fails
set -e

echo "=========================================="
echo " Building Player Communication Project... "
echo "=========================================="

# Compile the pure Java code using Maven
mvn clean compile

echo ""
echo "=========================================="
echo " Starting Single-Process Mode...          "
echo "=========================================="

# Execute the Main class.
# (Note: Standard bash doesn't need the quotes around the -D flag like Windows PowerShell does)
mvn exec:java -Dexec.mainClass="org.messaging.Main"

echo ""
echo "=========================================="
echo " Application Finished.         "
echo "=========================================="