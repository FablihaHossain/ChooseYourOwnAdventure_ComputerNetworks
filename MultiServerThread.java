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
            ActionProtocol actionGenre = new ActionProtocol(); //Action Protocol
            System.out.println("Mystery Adventure: Case will involve a couple that claim to be the new and improved version of Bonnie and Clyde");
            MysteryProtocol mysteryGenre = new MysteryProtocol(); //Mystery Protocol
            System.out.println("Horror Adventure Description here");
            HorrorProtocol horrorGenre = new HorrorProtocol(); //Horror Protocol

            //Variables to be used
            String inputLine;
            String outputLine;
            Character character;

            //while((inputLine = in.readLine()) != null || (inputLine = (Character)inObject.readObject()) != null)
            //{
            //Reading in the user's genre choice
            inputLine = in.readLine();

            //Character Creation
            System.out.println("Please Create Your Character");
            character = (Character)inObject.readObject();

            //Calling the correct protocol
            while((inputLine = in.readLine()) != null)
            {
                //System.out.println("Your Character Name is: " + character.getName());
                //System.out.println("Your Character Gender is: " + character.getGender());
                if(character.getGenre() == 'A')
                {
                    outputLine = actionGenre.processInput(inputLine, character);
                    //out.println(outputLine);
                }
                else if(character.getGenre() == 'M')
                {
                    outputLine = mysteryGenre.processInput(inputLine, character);
                    System.out.println(outputLine);
                    //out.println("first");
                    out.println(outputLine);
                    //out.println("last");
                    System.out.println();
                }
                else if(character.getGenre() == 'H')
                {
                    outputLine = horrorGenre.processInput(inputLine, character);
                    System.out.println(outputLine);
                    //out.println(outputLine);
                }
                else
                {
                    System.out.println("Error...");
                }

                if (inputLine.equals("Bye"))
                    break;
            }
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