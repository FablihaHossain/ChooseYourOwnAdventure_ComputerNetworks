import java.io.*;
import java.net.*;
/**
 * Computer Networks Project: The client class represents the user playing the
 *                            game and passes their choices onto the server
 * @author Fabliha Hossain
 */
public class Client //One client class for each user
{
    public static void main(String args[]) throws IOException
    {
        if (args.length != 2) {
            System.err.println(
                "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }
        //Client is going to initiate a connection request to the server's 
        //IP address and port
        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try (
        Socket kkSocket = new Socket(hostName, portNumber);
        PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(kkSocket.getInputStream()));
        ) {
            System.out.println("Welcome to the Game!\nPlease Choose a Genre Below");
            System.out.println("Action Adventure\nHorror Adventure\nMystery Adventure");
            String genreChoice = in.readLine();
            while (true);
        }
        catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        }
    }
}

