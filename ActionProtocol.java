
/**
 * Computer Networks Project: This class includes the protocol for the action 
 * adventure genre
 *
 * @author Fabliha Hossain
 */
public class ActionProtocol
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
