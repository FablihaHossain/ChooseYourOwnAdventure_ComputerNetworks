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
            //Option to read from the keyboard
            BufferedReader stdIn =
                new BufferedReader(new InputStreamReader(System.in));

            //Option to send objects
            ObjectOutputStream outObject = new ObjectOutputStream(kkSocket.getOutputStream());

            //Displaying the options for the client    
            System.out.println("Welcome to the Game!\nPlease Choose a Genre Below");
            System.out.println("Action Adventure\nHorror Adventure\nMystery Adventure");

            //Initial Character Object
            Character character = new Character(null, null, 'G');

            //Reading in Client's choice
            String genreChoice = stdIn.readLine();
            if(genreChoice != null)
            {
                //Confirming client's choice
                System.out.println("You choose: " + genreChoice);

                //Sending the choice to client
                out.println(genreChoice);
                System.out.println("sent to server");
            }

            //Genre has been choosen and now character must be created
            //Client chooses certain attributes of their character
            System.out.println("Please Input Your Character's Name");
            String name = stdIn.readLine();

            System.out.println("Please Choose Your Character's Gender: Female, Male, or Genderqueer");
            String gender = stdIn.readLine();

            //Updating the character object
            character.setName(name);
            character.setGender(gender);
            character.setGenre(genreChoice.charAt(0));
            
            //Sending the character object to the server
            outObject.writeObject(character);
            
            //Playing the game
            System.out.println("Ready to Play the game?");
            String input;
            while((input = stdIn.readLine()) != null)
            {
                out.println(input);
                System.out.println("sent to server");
            }
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

