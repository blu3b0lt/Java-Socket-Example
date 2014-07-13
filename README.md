Java-Socket-Example
===================

This repo contains a simple java socket program, the server authenticates the client using a passcode.

The class files are present in build/

The .java files are present in src/


Usage: This is a simple Authentication.
The port number can be specified as a command line arguement, the deafult port number is: 2500
The password for accessing the server is also given as an command line argument. Default is: "blu3b0lt"
This server then listens to on the port number and compares the passCode with that obtained from client.
If they match access is granted.

java Server portNumber passCode

