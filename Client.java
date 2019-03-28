import java.io.*;
import java.net.*;
public class Client //One client class for each user
{
    public static void main(String args[]) throws IOException
    {
         if (args.length != 2) {
            System.err.println(
                "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }
         //Client is going to initiate a connection request to the server's IP address and port
        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);
        
        try (
            Socket kkSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(kkSocket.getInputStream()));
        ) {
            BufferedReader stdIn =
                new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromUser;
            boolean start = false;
            String clientName = "";
            //Client recieves message from the server
            while ((fromServer = in.readLine()) != null) {
                
                if(start == false)
                {
                    System.out.println("What is your name?");
                    clientName = stdIn.readLine();
                    start = true;
                }
                
                System.out.println("Server: " + fromServer); //Printing Initial message from server
                if (fromServer.equals("Bye"))
                    break;
                
                //Reading a response from the user/client
                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    System.out.println(clientName + ": " + fromUser);
                    out.println(clientName + ":" + fromUser); //Sends response to the server
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        }
    }
}
