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
 * Computer Networks Project: The server class includes the GUI code and follows
 *                            the particular protocol based on client's choice
 * @author Fabliha Hossain
 */
//Should support multiple users
public class Server //extends Application
{
    //A log file that records the user's choices throughout the game
    File logFile = new File("logFile");

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
                    //launch(args); //Lauching the GUI application
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

    // //Where the application will be presented in GUI
    // public void start(Stage mainStage)
    // {
        // //Setting up the basic application
        // mainStage.setTitle("Choose Your Own Adventure");
        // VBox centerBox = new VBox();
        // centerBox.setPadding( new Insets(16) );
        // centerBox.setSpacing(16);
        // centerBox.setAlignment( Pos.CENTER );

        // BorderPane root = new BorderPane();
        // Scene mainScene = new Scene(root, 600, 600);
        // mainStage.setScene(mainScene);

        // ScrollPane pane = new ScrollPane();
        // pane.setPrefSize(600,600);

        // root.setCenter(centerBox);
        // root.setStyle("-fx-font-size: 25"); 

        // //Grid Pane to organize
        // GridPane grid = new GridPane();
        // grid.setHgap(10);
        // grid.setVgap(10);
        // grid.setAlignment(Pos.CENTER);

        // //Instruction Label
        // Label instruction = new Label("\t\tWelcome! \nPlease Choose A Genre Below:");
        // instruction.setFont(new Font("Helvetica", 30));

        // //Menu Bar
        // MenuBar menuBar = new MenuBar();
        // root.setTop(menuBar);

        // //File
        // Menu file = new Menu("File");
        // menuBar.getMenus().add(file);

        // //About
        // Menu about = new Menu("About");
        // menuBar.getMenus().add(about);

        // //Quit Functionality
        // MenuItem quit = new MenuItem("Quit Program");
        // file.getItems().add(quit);

        // quit.setAccelerator(
            // (new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN)));

        // //quit.setGraphic( new ImageView( new Image("icons/door_out.png")));
        // quit.setOnAction( (ActionEvent event) -> System.exit(0));

        // //About This Program
        // MenuItem program = new MenuItem("About this program");
        // about.getItems().add(program);

        // program.setAccelerator(
            // (new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN)));

        // //program.setGraphic( new ImageView( new Image("icons/information.png")));

        // program.setOnAction(
            // (ActionEvent event) ->
            // {
                // Alert info = new Alert(AlertType.INFORMATION);
                // info.setTitle("About This Program");
                // info.setHeaderText(null);

                // //Adding Icons
                // Stage alertStage = (Stage) info.getDialogPane().getScene().getWindow();
               // // alertStage.getIcons().add( new Image("icons/key_A.png") );

                // //Adding Content
                // info.setContentText("This program simulates a Choose Your Own Adventure Game" +
                    // "\nThe Three Possible Genres: \n\tAction\n\tHorror\n\tMystery" +
                    // "\n\nCreated by Fabliha Hossain");
                // info.showAndWait();
            // }
        // );

        // mainStage.show();
    // }
}
