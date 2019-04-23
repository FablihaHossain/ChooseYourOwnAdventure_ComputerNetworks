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

        //Starting the game
        if(gameStatus.equals("Start Game"))
        {
            output = "Welcome to a new mystery adventure. Your character " + character.getName() +
            " is a " + character.getGender() + " lead detective that just recieved an " +
            " unusual case. Please choose which unit you would like to be a part of \n" +
            "Federal Bureau of Investigations (FBI) or New York Police Department (NYPD)";   

            //Updating Game Status
            gameStatus = "Unit Choosen";
        }
        else if(gameStatus.equals("Unit Choosen"))  //After user has choosen Unit
        {
            if(input.equals("FBI"))
            {
                output = "You and your team have been trying to track down this couple for the past week. Recent news says " +
                "that they shot up a TD Bank in Garden City, New York. You arrive at the scene and are told that stacks of " + 
                "money have been stolen, yet there were no casualties. What is your first course of action?" +
                "Interrogate witnesses (A) or Look around for Clues (B)?";
                gameStatus = "Scenario One";
            }
            else if(input.equals("NYPD"))
            {
                output = "You have been invited to join an FBI team to help track down the couple that always seem to be on the move. " +
                "Recent news says that they shot up a TD Bank in Garden City, New York. You arrive at the scene and are told that stacks of " + 
                "money have been stolen, yet there were no casualties. What is your first course of action?" +
                "Interrogate witnesses (A) or Look around for Clues (B)?";
                gameStatus = "Scenario One";
            }
            else
            {
                output = "Invalid Input";
            }
        }
        else if(gameStatus.equals("Scenario One")) //Scenario One
        {
            if(input.equals("A"))
            {
                output = "You talk to a few people. Some reported that they were so scared that they didn’t get a good look " +
                "at the two shooters. They only got bits and pieces of their looks and character. After asking around, you " +
                "gathered a preliminary profile of the two. Do you look around for more clues (A) or go back to headquarters (B)?";
                gameStatus = "Scenario Two";
            }
            else if(input.equals("B"))
            {
                output = "You walk around the bank, both inside and outside. You even walk outside the back exit, where you " +
                "were told that the couple fled from. Just when you think you couldn’t find anything, you feel something at " +
                "the bottom of your foot. You look down and see that there is a red jeweled earring on the ground. " +
                "After asking around, you conclude that belongs to the female. " +
                "Do you look around for more clues (A) or go back to headquarters (B)?";
                gameStatus = "Scenario Two";
            }
            else
            {
                output = "Invalid Input";
            }
        }
        else if(gameStatus.equals("Scenario Two"))
        {
            output = "You and your team couldn't find any other clues so you decided to go back. Once you get back to headquarters, "+
            "you go over what you know so far with your team members. The only clues found were the red jeweled earring that potentially "+
            "belongs to the female shooter, that was described by witnesses to have light brown hair and somewhat fancy clothing. " +
            "One of your team members suggest looking at the security camera footage to get more details (A), while another suggests " +
            "to look into the red earring a bit more (B). Which do you decide to go with?";
            gameStatus = "Scenario Two part 2";
        }
        else if(gameStatus.equals("Scenario Two part 2"))
        {
            if(input.equals("A") || input.equals("X") || input.equals("Y"))
            {
                if(input.equals("X") || input.equals("Y"))
                {
                    output = "Going to the jewelary leaves you at a dead end. You decide to look at the security footage\n";
                }
                output += "One of your team members requests video from TD Bank and looks through it during the time of the shooting. "+
                "It appears that they did their best to hide their faces from the camera. They entered the bank as a normal civilian, "+
                "but brought up their guns when they got the chance. You could see the woman’s face because she was wearing a hat, but " +
                "you got a small image of the man’s face. Do you continue to look into the man by digital forensics (C) or go back and " +
                "look into the red earring found earlier (B)?";
            }
            else if(input.equals("B"))
            {
                output = "The officer that suggested looking at the earring revealed that she had seen it before. It was sold in a " +
                "particular jewelry store and it was a part of a rare collection. Do you send the officer to investigate the store (X) or "+
                "do you go there yourself? (Y)";
            }
            else if(input.equals("C"))
            {
                gameStatus = "Scenario Three";
                output = "Continue? (Y) or (N)";
            }
            else
            {
                output = "Invalid Input";
            }
        }
        else if(gameStatus.equals("Scenario Three"))
        {
            if(input.equals("Y"))
            {
                output = "You send the image found of the man to your technical analyst to see if there is a match between that and a " +
                "person in the database. In the meantime, you make an announcement to the public to beware of this couple and to contact " +
                "you if they are seen anyway. When you get back, your technical analyst reveals that the image found earlier was matched with " +
                "a criminal named Clive Adams, who was already charged with robberies and suspected kidnapping. Do you look into Clive’s background "+
                "and try to find the female companion (X) or do you go to Clive’s last known address and investigate there(B)?";
            }
            else if(input.equals("X"))
            {
                output = "You run background on Clive’s family and associates throughout the last few years. Soon enough, you find " +
                "images from a party that happened about a year ago. It was a wedding party for Bonny Winterbottom and her husband, " +
                "who wasn’t Clive. But you see Clive in the background of some photos. You conclude that she might know him. Do you go " +
                "to Bonny’s last known address (A) or Clive’s last known address (B)?";
            }
            else if(input.equals("A"))
            {
                gameStatus = "Scenario Four";
            }
            else if(input.equals("B"))
            {
                output = "The last known address was Clive’s mother’s house. You arrive there and see that she is bedridden. " +
                "She has no idea where her son is and what he’s been up to. You’ve reached a dead end. Go back and investigate Clive's " +
                "background (X) or quit game (N)";
            }
            else if(input.equals("N"))
            {
                output = "ByeBye";
                gameStatus = "End Game";
            }
            else
            {
                output = "Invalid Input";
            }
        }
        else if(gameStatus.equals("Scenario Four"))
        {
            output = "You arrive at Bonny’s last known address and see her parents live there. You ask them and they tell you that "+
            "Bonny left her husband and eloped with Clive Adams. However, they have no idea of her whereabouts right now. " +
            "Do you look around the house for clues (X) or ask around the neighborhood (Y)?";

            if(input.equals("X"))
            {
                output = "\nYou look around the house of Bonny and arrive at her room. You take a look around and see that she " +
                "took a few clothes and essentials. Her mother comes in the room and tells you that Bonny also took a box of personal " +
                "items that she really liked. Other than that, you’ve reached another dead end. Do you ask around the neighborhood (A) " +
                "or go back to headquarters (Y)?";
            }
            else if (input.equals("A"))
            {
                output = "You ask people around the block, but no one has a clue where the couple could have gone. Just as you are " +
                "about to go back, someone stops you. It is a young teenager who tells you that she saw the news announcement you " +
                "broadcasted earlier. She reveals that she noticed Clive driving around the neighborhood a few times with Bonny. " +
                "You ask for the vehicle information and go back to headquarters.";
                gameStatus = "Scenario Five";
            }
            else if(input.equals("Y"))
            {
                gameStatus = "Scenario Five";
            }
            else
            {
                output += "Invalid Input";
            }
        }
        else if(gameStatus.equals("Scenario Five"))
        {
            output = "You arrive back at headquarters and get a clue about the vehicle that Clive drives. It is a 2000 red jeep " +
            "and your team managed to track the license plate. You send an immediate amber alert for the car. Soon enough, you get " +
            "word that the car was spotted somewhere near Lake George, New York. " +
            "Do you go there yourself (A) or take some team members with you (B)?";
            gameStatus = "Last Scene";
        }
        else if(gameStatus.equals("Last Scene"))
        {
            if(input.equals("A"))
            {
                output = "Going by yourself is never a good idea. You arrive at the scene and Clive immediately notices your uniform. "+
                "He pulls the trigger and drives away. You are dead. Game Over.";
                gameStatus = "End Game";
            }
            else if(input.equals("B"))
            {
                output = "Taking a few team members: You arrive at the scene with your team members. You all come up with a plan to " +
                "surround them. As soon as you do, you threaten them to come out of the car peacefully or you’ll shot. They both slowly " +
                "get out of the car, and put their hands up. You’ve finally arrested Clive Adams and Bonny Winterbottom. "+
                "Congratulations another case solved.";
                gameStatus = "End Game";
            }
            else
            {
                output = "Invalid Input";
            }
        }
        else
        {
            output = "The game is over.";
        }
        return output;
    }
}
