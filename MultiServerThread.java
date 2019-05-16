import java.net.*;
import java.io.*;
/**
 * Computer Networks Project: This MultiServerThread allows for multiple clients
 *                            to use the same server
 * @author Fabliha Hossain
 */
public class MultiServerThread extends Thread {
    private Socket socket = null;

    //Trying to establish a connection
    public MultiServerThread(Socket socket) {
        super("KKMultiServerThread");
        this.socket = socket;
    }

    public void run() { 
        try (
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //Option to read in objects
        ObjectInputStream inObject = new ObjectInputStream((socket.getInputStream()));
        ) {
            //Displaying Genre Options 
            MysteryProtocol mysteryGenre = new MysteryProtocol(); //Mystery Protocol
            HorrorProtocol horrorGenre = new HorrorProtocol(); //Horror Protocol

            //Variables to be used
            String inputLine;
            String outputLine;
            Character character;

            //while((inputLine = in.readLine()) != null || (inputLine = (Character)inObject.readObject()) != null)
            //{

            //Reading in the user's genre choice (for the rapid prototype)
            //inputLine = in.readLine();

            //Character Object
            //System.out.println("Please Create Your Character");
            character = (Character)inObject.readObject();
            System.out.println(character.toString());

            //Calling the correct protocol
            while((inputLine = in.readLine()) != null)
            {
                if(character.getGenre() == 'M') //Mystery Genre Protocol
                {
                    //Displaying on the server side what the client choose/input
                    System.out.println("User Choose: " + inputLine);
                    
                    //Calling the mystery protocol
                    outputLine = mysteryGenre.processInput(inputLine, character);
                    
                    //Sending the output to the client
                    out.println(outputLine);
                    
                    //Displaying the client's progress throughout the game
                    System.out.println();
                }
                else if(character.getGenre() == 'H') //Horror Genre Protocol
                {
                    //Displaying on the server side what the client choose/input
                    System.out.println("User Choose: " + inputLine);
                    
                    //Calling the mystery protocol
                    outputLine = horrorGenre.processInput(inputLine, character);
                    
                    //Sending the output to the client
                    out.println(outputLine);
                    
                    //Displaying the client's progress throughout the game
                    System.out.println();
                }
                else
                {
                    System.out.println("Error...");
                }
            }
            
            //Closing the socket and connection
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}