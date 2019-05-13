import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.net.*;
import java.io.*;
/**
 * Computer Networks Project: The server class follows the particular protocol based on client's choice
 * @author Fabliha Hossain
 */
//Should support multiple users
public class Server
{
    //Overriding Abstract method for Application Class
    public static void main(String[] args) 
    {
        try
        {
            if (args.length != 1) {
                System.err.println("Usage: java MultiServer <port number>");
                System.exit(1);
            }
            int portNumber = Integer.parseInt(args[0]);
            boolean listening = true;

            try (ServerSocket serverSocket = new ServerSocket(portNumber)) { 
                while (listening) {
                    new MultiServerThread(serverSocket.accept()).start();
                }
            } catch (IOException e) {
                System.err.println("Could not listen on port " + portNumber);
                System.exit(-1);
            }
        }
        catch (Exception error)
        {
            error.printStackTrace();
        }
        finally
        {
            System.exit(0);
        }
    }
}
