
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

import java.io.*;
import java.net.*;
/**
 * Computer Networks Project: The client class represents the user playing the
 *                            game and passes their choices onto the server
 *                            There is also the GUI enhancements
 * @author Fabliha Hossain
 */
public class Client extends Application //One client class for each user
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

        playGame(hostName, portNumber);
        //launch(args); //Lauching the GUI application
    }
    public static void playGame(String hostName, int portNumber)
    {
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
                // System.out.println("sent to server");
                String output = in.readLine();
                System.out.println(output);
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
        Label instruction = new Label("\t\tWelcome to the Game! \nPlease Choose A Genre Below:");
        instruction.setFont(new Font("Helvetica", 30));

        //Horror Button
        Button horrorButton = new Button("Horror");
        grid.add(horrorButton, 1, 4);

        horrorButton.setOnAction(
            (ActionEvent event) ->
            {
                //Removing the buttons and pictures from the main scene
                grid.getChildren().clear();

                //Updating the instruction Label
                instruction.setText("Welcome to the Horror Adventure");
                root.setStyle("-fx-font-size: 15");
            }
        );
        //Mystery Button
        Button mysteryButton = new Button("Mystery");
        grid.add(mysteryButton, 2, 2);

        //Creating the Mystery Story
        mysteryButton.setOnAction(
            (ActionEvent event) ->
            {
                //Removing the buttons and pictures from the main scene
                grid.getChildren().clear();

                //Updating the instruction Label
                instruction.setText("Welcome to the Mystery Adventure");
                root.setStyle("-fx-font-size: 15");
            }
        );
        //Menu Bar
        MenuBar menuBar = new MenuBar();
        root.setTop(menuBar);

        //File
        Menu file = new Menu("File");
        menuBar.getMenus().add(file);

        //About
        Menu about = new Menu("About");
        menuBar.getMenus().add(about);

        //Quit Functionality
        MenuItem quit = new MenuItem("Quit Program");
        file.getItems().add(quit);

        quit.setAccelerator(
            (new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN)));

        //quit.setGraphic( new ImageView( new Image("icons/door_out.png")));
        quit.setOnAction( (ActionEvent event) -> System.exit(0));

        //About This Program
        MenuItem program = new MenuItem("About this program");
        about.getItems().add(program);

        program.setAccelerator(
            (new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN)));

        //program.setGraphic( new ImageView( new Image("icons/information.png")));

        program.setOnAction(
            (ActionEvent event) ->
            {
                Alert info = new Alert(AlertType.INFORMATION);
                info.setTitle("About This Program");
                info.setHeaderText(null);

                //Adding Icons
                Stage alertStage = (Stage) info.getDialogPane().getScene().getWindow();
                // alertStage.getIcons().add( new Image("icons/key_A.png") );

                //Adding Content
                info.setContentText("This program simulates a Choose Your Own Adventure Game" +
                    "\nThe Two Possible Genres: \n\tHorror\n\tMystery" +
                    "\n\nCreated by Fabliha Hossain");
                info.showAndWait();
            }
        );

        centerBox.getChildren().addAll(instruction, grid);
        mainStage.show();
    }
}

