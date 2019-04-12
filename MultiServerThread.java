import java.net.*;
import java.io.*;
/**
 * Computer Networks Project: This MultiServerThread allows for multiple clients
 *                            to use the same server
 * @author Fabliha Hossain
 */
public class MultiServerThread extends Thread {
    private Socket socket = null;

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
            System.out.println("Action Adventure Description here");
            System.out.println("Mystery Adventure Description here");
            System.out.println("Horror Adventure Description here");

            //Variables to be used
            String inputLine;
            String outputLine;
            Character character;

            
            //while((inputLine = in.readLine()) != null || (inputLine = (Character)inObject.readObject()) != null)
            //{
            inputLine = in.readLine();

            //Character Creation
            System.out.println("Please Create Your Character");
            character = (Character)inObject.readObject();

            //Calling the correct protocol
            if(inputLine.equals("Action"))
            {
                ActionProtocol actionGenre = new ActionProtocol();
                outputLine = actionGenre.processInput(inputLine, character);
                System.out.println(outputLine);
                //out.println(outputLine);
            }
            else if(inputLine.equals("Mystery"))
            {
                MysteryProtocol mysteryGenre = new MysteryProtocol();
                outputLine = mysteryGenre.processInput(inputLine, character);
                System.out.println(outputLine);
                //out.println(outputLine);
            }
            else if(inputLine.equals("Horror"))
            {
                HorrorProtocol horrorGenre = new HorrorProtocol();
                outputLine = horrorGenre.processInput(inputLine, character);
                System.out.println(outputLine);
                //out.println(outputLine);
            }
            else
            {
                System.out.println("Error...");
            }

            // if (inputLine.equals("Bye"))
            // break;
            //}
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