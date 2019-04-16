/**
 * Computer Networks Project: This class includes the protocol for the 
 * mystery adventure genre
 *
 * @author Fabliha Hossain
 */
public class MysteryProtocol
{
    //Attributes
    String gender;

    //Status variable 
    String gameStatus = "Start Game";

    //Method to initiate protocol
    public String processInput(String input, Character character)
    {
        String output = "";
        if(gameStatus.equals("Start Game"))
        {
            output = "Welcome to a new mystery adventure. Your character " + character.getName() +
            " is a " + character.getGender() + " lead detective that just recieved an " +
            " unusual case. Please choose which unit you would like to be a part of \n" +
            "Federal Bureau of Investigations (FBI) or New York Police Department (NYPD)";   

            //Updating Game Status
            gameStatus = "Unit Choosen";
        }
        else if(gameStatus.equals("Unit Choosen"))
        {
            //if(input.equals("FBI OR NYPD");
            output = "ENTER DESCRIPTION HERE";
        }

        return output;
    }
}
