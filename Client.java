
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

import javax.swing.JOptionPane;
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
    //A log file that records the user's choices throughout the game
    File logFile = new File("logFile");

    String clientInput;
    //The host name and port number that the client connects to in order to play the game
    //Note: This is here so that we can call the playGame method in GUI without passing parameters
    static String hostName;
    static int portNumber;
    public static void main(String args[]) throws IOException
    {
        if (args.length != 2) {
            System.err.println(
                "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }
        //Client is going to initiate a connection request to the server's 
        //IP address and port
        hostName = args[0];
        portNumber = Integer.parseInt(args[1]);

        //Lauching the GUI application
        launch(args); 
    }

    /**
     * playGamePrototype The method that has the rapid prototype code where the client and server simply
     * exchange messages
     */
    public void playGamePrototype()
    {
        //Output value to display on the GUI application
        //String output = "Play game";
        String hostName = getHostName();
        int portNumber = getPortNumber();
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
                //return output;
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
        //return output;
    }

    /**
     * playGame The Method that implements the GUI enhancements
     * User is able to play the game, and save their progress through the log files
     */
    public void playGame(Character character)
    {
        //Getting the host name and port number of the server
        String hostName = getHostName();
        int portNumber = getPortNumber();

        try (
        Socket kkSocket = new Socket(hostName, portNumber);
        PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(kkSocket.getInputStream()));
        ) {
            //Option to send objects
            ObjectOutputStream outObject = new ObjectOutputStream(kkSocket.getOutputStream());

            //Option to read from the keyboard
            BufferedReader stdIn =
                new BufferedReader(new InputStreamReader(System.in));

            //Sending the character object to the server
            System.out.println(character.toString());
            outObject.reset();
            outObject.writeObject(character);
            System.out.println("sent to server:");

            //Playing the game
            //System.out.println("Ready to Play the game?");
            String input = clientInput;
            //while(!(input = getInput()).equals(null))
            while(input != null)
            {
                out.println(input);
                String output = in.readLine();
                System.out.println(output);
                input = getInput();
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

    public String getInput()
    {
        //Scanner scan = new Scanner(System.in);
        //clientInput = scan.next();
        return clientInput;
    }

    public void setInput(String s)
    {
        clientInput = s;
    }

    /**
     * The Graphical User Interface Application code
     */
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
        Label instruction = new Label("\tWelcome to the Game! \nLet's Create Your Character:");
        instruction.setFont(new Font("Helvetica", 25));

        //Character Name in Text Field
        Label characterName = new Label("Enter Your Character's Name:");
        TextField nameField = new TextField();

        grid.add(characterName, 0, 1);
        grid.add(nameField, 0, 2);

        //Character Gender in Radio Button
        ToggleGroup gender = new ToggleGroup();
        RadioButton male = new RadioButton("Male");
        RadioButton female = new RadioButton("Female");
        RadioButton genderQueer = new RadioButton("Gender Queer");

        male.setToggleGroup(gender);
        female.setToggleGroup(gender);
        genderQueer.setToggleGroup(gender);

        //An HBox to add it the application
        HBox mOrF = new HBox(male, female, genderQueer);
        grid.add(mOrF, 0, 4);

        //Horror Button
        Button horrorButton = new Button("Horror");
        grid.add(horrorButton, 0, 9);

        horrorButton.setOnAction(
            (ActionEvent event) ->
            {
                //Removing the buttons and pictures from the main scene
                grid.getChildren().clear();

                //Creating the character
                String charName = nameField.getText();  //SHOULD HAVE SCENARIO IN CASE LEFT BLANK
                System.out.println("charName = " + charName);
                String charGender;
                if(male.isSelected())
                {
                    charGender = "male";
                }
                else if(female.isSelected())
                {
                    charGender = "female";
                }
                else
                {
                    charGender = "gender queer";
                }

                //Creating character object
                Character character = new Character(charName, charGender, 'H');

                //Updating the instruction Label
                instruction.setText("Welcome to the Horror Adventure");
                root.setStyle("-fx-font-size: 15");  

                //Playing the game
                playGame(character);
            }
        );
        //Mystery Button
        Button mysteryButton = new Button("Mystery");
        grid.add(mysteryButton, 0, 8);

        //Creating the Mystery Story
        System.out.println("Before pressing button");
        mysteryButton.setOnAction(
            (ActionEvent event) ->
            {
                System.out.println("After pressing button");
                //Removing the buttons and pictures from the main scene
                grid.getChildren().clear();

                //Creating the character
                String charName = nameField.getText();  //SHOULD HAVE SCENARIO IN CASE LEFT BLANK
                System.out.println("charName = " + charName);
                String charGender;
                if(male.isSelected())
                {
                    charGender = "male";
                }
                else if(female.isSelected())
                {
                    charGender = "female";
                }
                else
                {
                    charGender = "gender queer";
                }

                //Creating character object
                Character character = new Character(charName, charGender, 'M');

                //Updating the instruction Label
                instruction.setText("Welcome to the Mystery Adventure. Ready to Play?");
                root.setStyle("-fx-font-size: 15");

                TextField field = new TextField();
                grid.add(field, 0, 2);

                Button enter = new Button("Enter");
                grid.add(enter, 1, 6);
                enter.setOnAction(
                    (ActionEvent event2) ->
                    {
                        setInput(field.getText());
                    }
                ); 

                //Playing the game
                playGame(character);
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

    public static String getHostName()
    {
        return hostName;
    }

    public static int getPortNumber()
    {
        return portNumber;
    }
}

