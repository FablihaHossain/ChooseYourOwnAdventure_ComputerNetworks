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
//TO BE DONE LATER
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
            launch(args);
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
    
    //Where the application will be 
    public void start(Stage mainStage)
    {
        mainStage.setTitle("Choose Your Own Adventure");
        mainStage.show();
    }
}
