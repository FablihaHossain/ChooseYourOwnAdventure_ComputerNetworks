
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
    //The host name and port number that the client connects to in order to play the game
    //Note: This is here so that we can call the playGame method in GUI without passing parameters
    static String hostName;
    static int portNumber;

    //Keeping Track of Client Input
    String clientInput;

    //Keeping Track of Server Output
    String serverOutput;

    //Genre the User Chooses
    String genre;

    //User's character object
    Character character;

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
     * exchange string messages through the terminal
     */
    public void playGamePrototype()
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
            String input; //Reading in the client's input
            while((input = stdIn.readLine()) != null)
            {
                //Sending the client input to the server
                out.println(input);
                //System.out.println("sent to server");

                //Reading the output from server
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
        //return output;
    }

    /**
     * playGame The Method that implements the GUI enhancements (or tries to)
     * @param: A character object
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
            //System.out.println("sent to server:");

            //Playing the game
            System.out.println("\nPress Any Key to Continue");
            //serverOutput = "Ready to Play the game?";
            //Getting the client input
            String input = getInput();
            while(input != null)
            {
                //Sending the client's input to server
                out.println(input);

                //Getting the server's output response
                String output = in.readLine();
                serverOutput = output;
                System.out.println(output);

                //Getting the next client input
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
        root.setStyle("-fx-font-size: 20"); 

        //Grid Pane to organize
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        //Instruction Label
        Label instruction = new Label("\tWelcome to the Game! \nLet's Create Your Character:");
        instruction.setFont(new Font("Helvetica", 20));

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

        //Choice between the two Genres
        ComboBox<String> genres = new ComboBox<String>();
        genres.getItems().addAll("Mystery", "Horror");
        genres.valueProperty().addListener(
            (ObservableValue<? extends String> ov, String oldValue, String newValue) -> 
            {
                if(newValue.equals("Mystery"))
                {
                    genre = "Mystery";
                }
                else if(newValue.equals("Horror"))
                {
                    genre = "Horror";
                }
                else
                {
                    genre = "Mystery"; //Default if no genre choosen
                }
            }   
        );

        //Genre of Choice
        Label genreLabel = new Label("Which Genre Would You Like to Play?:");
        genres.setValue("Choose Genre");

        grid.add(genreLabel, 0, 8);
        grid.add(genres, 0, 9);

        //Create Character Button
        Button createCharacter = new Button("Create Your Character");
        grid.add(createCharacter, 0, 10);

        createCharacter.setOnAction(
            (ActionEvent event) ->
            {
                //Clearing the grid
                grid.getChildren().clear();

                //Creating the character
                String charName = nameField.getText();
                String charGender;
                if(male.isSelected())
                {
                    charGender = "Male";
                }
                else if(female.isSelected())
                {
                    charGender = "Female";
                }
                else
                {
                    charGender = "Gender Queer";
                }
                char g = genre.charAt(0);
                //Creating character object
                Character character = new Character(charName, charGender, g);

                //Character Summary
                instruction.setText("Welcome To The " + genre + " Adventure!\n" + character.toString());
                root.setStyle("-fx-font-size: 20");

                Label ready = new Label("Ready to Play?");
                grid.add(ready, 0, 1);

                // TextField field = new TextField();
                // grid.add(field, 0, 3);

                Button yesButton = new Button("YES!");
                grid.add(yesButton, 1, 1);
                yesButton.setOnAction(
                    (ActionEvent event2) ->
                    {
                        //clientInput = field.getText();

                        //Playing the game
                        playGame(character);

                        instruction.setText(serverOutput);
                        //grid.add(field, 0, 2);
                        // if(serverOutput != null && clientInput != null)
                        // {
                        // instruction.setText(serverOutput);
                        // clientInput = field.getText();
                        // }
                    }
                ); 
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

        quit.setOnAction( (ActionEvent event) -> System.exit(0));

        //About This Program
        MenuItem program = new MenuItem("About this program");
        about.getItems().add(program);

        program.setAccelerator(
            (new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN)));

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

    /**
     * getHostName A method that returns the host name of the client server connection
     */
    public static String getHostName()
    {
        return hostName;
    }

    /**
     * getPortNumber A method that returns the port number of the client server connection
     */
    public static int getPortNumber()
    {
        return portNumber;
    }

    /**
     * getInput: A method that uses the Scanner object to get the client's input
     */    
    public String getInput()
    {
        Scanner scan = new Scanner(System.in);
        clientInput = scan.next();
        return clientInput;
    }

    /**
     * setInput: A method that sends the client input
     */
    public void setInput(String s)
    {
        clientInput = s;
    }
}

