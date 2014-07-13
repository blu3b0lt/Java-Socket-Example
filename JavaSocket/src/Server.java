/*
The MIT License (MIT)

Copyright (c) 2014 blu3b0lt

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */

package server;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author blu3b0lt
 */
/*
# Usage: This is a simple Authentication.
# The port number can be specified as a command line arguement, the deafult port number is: 2500
# The password for accessing the server is also given as an command line argument. Default is: "blu3b0lt"
# This server then listens to on the port number and compares the passCode with that obtained from client.
# If they match access is granted.

java Server portNumber passCode
*/
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int portNumber;
        String passCode = "blu3b0lt";
        if(args.length == 2) {
            portNumber = Integer.parseInt(args[0]);
            passCode = args[1];
        }
        else {
            portNumber = 2500;
        }
        ServerSocket authServer = null;
        try {
            authServer = new ServerSocket(portNumber);
        }
        catch(IOException e) {
            System.out.println(e);
        }
        Socket client = null;
        System.out.println("Authentication Server running on port "+portNumber);
        while(true) {
            try {
                client = authServer.accept();
            }
            catch(IOException e) {
                System.out.println(e);
            }
            String IP = client.getRemoteSocketAddress().toString();
            System.out.println("System "+IP+" is trying to connect");
            ObjectInputStream msgFromClient = null;
            ObjectOutputStream reply = null;
            String msg = null;
            try {
                reply = new ObjectOutputStream(client.getOutputStream());
                reply.writeObject("401 authentication required.");
            }
            catch (IOException e) {
                System.out.println(e);
            }
            try {
                msgFromClient = new ObjectInputStream(client.getInputStream());
                msg = (String) msgFromClient.readObject();
            }
            catch(IOException e) {
                System.out.println(e);
            }
            catch(ClassNotFoundException e){
                System.out.println(e);
            }
            try {
                if(msg.equals(passCode)){
                    reply.writeObject("Access Granted");
                }
                else {
                    reply.writeObject("404 Authentication failed, this incident will be reported.The assocaited IP address is: "+ IP);
                }
            }
            catch(IOException e) {
                System.out.println(e);
            }
            try {
                reply.close();
                msgFromClient.close();
                client.close();
            }
            catch(IOException e) {
                System.out.println(e);
            }
        }
    }
    
}
