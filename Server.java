import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.effect.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.beans.property.*;
import javafx.beans.value.*;
import javafx.event.*; 
import javafx.animation.*;
import javafx.geometry.*;
import java.io.*;
import java.util.*;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.net.*;
import java.io.*;
/**
 * Computer Networks Project: This is a network application that simulates a 
 *                            create your own adventure game.
 * @author Fabliha Hossain
 */
//Should support multiple users
public class Server extends Application
{
    //A log file that records the user's choices throughout the game
    File logFile = new File("logFile");

    //Overriding Abstract method for Application Class
    public static void main(String[] args) 
    {
        try
        {
            if (args.length != 1) {
                System.err.println("Usage: java KKMultiServer <port number>");
                System.exit(1);
            }

            int portNumber = Integer.parseInt(args[0]);
            boolean listening = true;

            try (ServerSocket serverSocket = new ServerSocket(portNumber)) { 
                while (listening) {
                    new MultiThreadServer(serverSocket.accept()).start();
                    launch(args);
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

    //Where the application will be presented in GUI
    public void start(Stage mainStage)
    {
        //Setting up the basic application
        mainStage.setTitle("Choose Your Own Adventure");
        VBox centerBox = new VBox();
        centerBox.setPadding( new Insets(16) );
        centerBox.setSpacing(16);
        centerBox.setAlignment( Pos.CENTER );

        BorderPane root = new BorderPane();
        Scene mainScene = new Scene(root, 600, 600);
        mainStage.setScene(mainScene);

        ScrollPane pane = new ScrollPane();
        pane.setPrefSize(600,600);

        root.setCenter(centerBox);
        root.setStyle("-fx-font-size: 25"); 
        
        //Grid Pane to organize
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        //Instruction Label
        Label instruction = new Label("\t\tWelcome! \nPlease Choose A Genre Below:");
        instruction.setFont(new Font("Helvetica", 25));
        
        mainStage.show();
    }
}
