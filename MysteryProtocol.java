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
    String gameStatus = "Not Started";

    //Method to initiate protocol
    public String processInput(String input)
    {
        String output = "";

        //Character Creation
        if(gameStatus.equals("Not Started"))
        {
            output = "Please Create Your Character";
            gameStatus = "CharacterCreated";
        }
        return output;
    }
}
