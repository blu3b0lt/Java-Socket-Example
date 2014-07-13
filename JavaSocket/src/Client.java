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

package client;
import java.io.*;
import java.net.*;

/**
 *
 * @author blu3b0lt
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
        // TODO code application logic hereem.out
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        int portNumber;
        if(args.length == 1) {
            portNumber = Integer.parseInt(args[0]);
        }
        else {
            portNumber = 2500;
        }
        ObjectInputStream reply = null;
        ObjectOutputStream request = null;
        System.out.println("Trying to connect to "+host.getHostName()+" on port "+portNumber);
        socket = new Socket(host.getHostName(), portNumber);
        String passCode = null;
        String response;
        reply = new ObjectInputStream(socket.getInputStream());
        response = reply.readObject().toString();
        System.out.println(response);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter password for authentication: ");
        passCode = br.readLine();
        request = new ObjectOutputStream(socket.getOutputStream());
        request.writeObject(passCode);
        response = reply.readObject().toString();
        System.out.println(response);
        request.close();
        reply.close();
        socket.close();
    }
    
}
