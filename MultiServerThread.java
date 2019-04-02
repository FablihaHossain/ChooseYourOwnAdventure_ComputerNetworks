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
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                    socket.getInputStream()));
        ) {
            String inputLine;
            String outputLine;

            //Calling the correct protocol
            while((inputLine = in.readLine()) != null)
            {
                if(inputLine.equals("Action"))
                {
                    ActionProtocol actionGenre = new ActionProtocol();
                    outputLine = actionGenre.processInput(inputLine);
                    out.println(outputLine);
                }
                else if(inputLine.equals("Mystery"))
                {
                    MysteryProtocol mysteryGenre = new MysteryProtocol();
                    outputLine = mysteryGenre.processInput(inputLine);
                    out.println(outputLine);
                }
                else if(inputLine.equals("Horror"))
                {
                    HorrorProtocol horrorGenre = new HorrorProtocol();
                    outputLine = horrorGenre.processInput(inputLine);
                    out.println(outputLine);
                }

                if (inputLine.equals("Bye"))
                    break;
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}