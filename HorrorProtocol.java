/**
 * Computer Networks Project: This class includes the protocol for the horror 
 * adventure genre
 *
 * @author Fabliha Hossain
 */
public class HorrorProtocol
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
            output = "Welcome to a new horror adventure. You wake up, with a massive headache, to find yourself " +
            "in an abandoned war camp with no clue how you got there. You look around and don’t see anyone else around. " +
            "But you hear this strange tapping sound. "+
            "Do you go find the source of the sound (A) or try to find a way out of the camp (B)?";

            //Updating Game Status
            gameStatus = "Scenario One";
        }
        else if(gameStatus.equals("Scenario One")) //Scenario One
        {
            if(input.equals("A"))
            {
                output = "You get up and walk slowly towards the sound. As you walk, you notice that several  " +
                "different tents with many different items. You notice two weapons that you can obtain: " +
                "a sword (A) or pick ax (B). Which do you take??";
                gameStatus = "Scenario Two";
            }
            else if(input.equals("B"))
            {
                output = "You get up and walk around the camp, trying to find an exit. You notice several different " + 
                "tents, some are burned down to the ground, while others are still standing. After walking around, you " +
                "can’t seem to locate a particular way out. You decide to locate the source of the tapping sound, but " +
                "you hesitate. You then notice two weapons: a sword (A) or pick ax (B). Which do you take?";
                gameStatus = "Scenario Two";
            }
            else
            {
                output = "Invalid Input";
            }
        }
        else if(gameStatus.equals("Scenario Two"))
        {
            output = "You take your weapon and continue towards the noise. The closer you get, the louder your heart " +
            "starts beating in your chest. You can’t shake the fact that someone or something is watching you. You try to " +
            "be aware of your surroundings. Do you call out to see if someone is there (A) or remain quiet (B)?";
            gameStatus = "Scenario Two part 2";
        }
        else if(gameStatus.equals("Scenario Two part 2"))
        {
            if(input.equals("A")|| input.equals("C"))
            {
                output = "“Who’s there?” You call out. You look around and try to keep your breathing low. You tighten " +
                "your grip on your weapon and ask again. Suddenly, you hear a faint voice saying “Hello…” " +
                "Press anything to continue";
                gameStatus = "Scenario Three";
            }
            else if(input.equals("B"))
            {
                output = "You decide to remain silent as you continue towards the sound. The next thing you hear is a " +
                "loud crunch, as if someone had stepped on a tree branch nearby. You give your weapon a steady grip. " +
                "Do you walk towards the tree noise (A) or stay where you are and call out (C)?";
            }
            else
            {
                output = "Invalid Input";
            }
        }
        else if(gameStatus.equals("Scenario Three"))
        {
            output = "After a few seconds, you hear that voice again, but a little louder this time. Do you walk " +
            "towards the voice (A) or ask them to come to you (B)?";
            gameStatus = "Scenario Three part 2";
        }
        else if(gameStatus.equals("Scenario Three part 2"))
        {
            if(input.equals("A"))
            {
                output = "You walk slowly towards that voice, while keeping a tight grip on your weapon. You ask, " +
                "“Who are you?”. A few seconds pass, and you hear the voice respond to you, “I’m Casper…” At first " +
                "glance, he looks transparent and terrifying. Do you run away (A) or stay and ask him questions (B)?";
                gameStatus = "Scenario Four";
            }
            else if(input.equals("B"))
            {
                output = "You tell the voice, “Who are you? Reveal yourself!”. A few seconds later, a slim figure " +
                "comes out behind a tree and say, “I’m Casper…”. You take a good look at Casper and notice that he " +
                "looks pretty transparent and terrifying. Do you swing your weapon at him (A) or stay and talk to him (B)?";
                gameStatus = "Scenario Four";
            }
            else
            {
                output = "Invalid Input";
            }   
        }
        else if(gameStatus.equals("Scenario Four"))
        {
            if(input.equals("A"))
            {
                output = "Casper has instilled fear in you. You decide to swing your weapon at him and run the other " +
                "direction. You don’t even look back to see the damage, and you continue running until you are short of " +
                "breath. As you are catching your breath, you feel a presence around you. You jump up and look behind to " +
                "find Casper, unharmed. Do you swing your weapon again (C) or talk to him (B)?";
            }
            else if(input.equals("B"))
            {
                output = "“Who… what are you?” you ask Casper. He takes a second before saying, “I’m Sergeant Casper Ramos. " +
                "I used to fight for the American army before I… before I died”. You blink as you realize what he is trying to " +
                "tell you. You slowly reach out your hand to try to touch him. Then, you jump as you realize that your hand " +
                "goes right through him. You shake your head and slap yourself. It can’t be! You must be dreaming!  " +
                "Casper can’t be a ghost. Casper notices your condition and says, “Don’t worry, I’m not going to hurt you.” " +
                "“Then what are you going to do?” “I want to help you, I just need you to trust me.” " +
                "Do you trust Casper (A) or remain skeptical (B)? ";
                gameStatus = "Scenario Five";
            }
            else if(input.equals("C"))
            {
                output = "You swing your weapon again at him, but this time notice that it literally goes right " +
                "through him. “Please stop,” Casper says, “I don’t want to hurt you.” You look at him confused, " +
                "you put your weapon down and try to talk to him. Press (B) to continue";
                gameStatus = "Scenario Four";
            }
            else
            {
                output = "Invalid Input";
            }   
        }
        else if(gameStatus.equals("Scenario Five"))
        {
            if(input.equals("A"))
            {
                output = "You decide to trust Casper blindly as you follow him down the path, towards this dark tent. " +
                "Your heart starts beating in your chest louder and louder. You ask him where you guys are going, but " +
                "he gives you no response. You are about to ask again when you realize that he has stopped. He slowly turns " +
                "around, and you notice blood dripping from his eyes. You widen your eyes, drop your weapon, and start " +
                "to run the other direction. You don’t get far, however, because Casper teleports right in front of you. " +
                "The next thing you know, you are on the ground and running short of breath. In a few seconds, you die. " +
                "Moral of the story: Don’t trust blindly. Game over";
                gameStatus = "End Game";
            }
            else if(input.equals("B"))
            {
                output = "“Why should I trust you?” you ask. Casper proceeds to tell you his story. He was young lad who was " +
                "just trying to serve his country. He had always been a loner in life, but the members of his platoon made him " +
                "feel like family. That was, until their camp had been attacked by the enemy all of a sudden. This resulted in " +
                "his death. You ask him, “Why do you want to help me?”. He responds, “Because I see myself in you. And I’d like you " + 
                "to live for me.” You take a deep breath and say, “Okay, what do you want me to do?” “First promise me,” " +
                "he says, “that you will live” “I promise,” you say. Casper nods his head and tells you to close your " +
                "eyes and count to 5. You close them slowly. One… two... three… four…five. You open your eyes, and " +
                "realize that you are back in your room. You slowly get up and look around the room until you see the " +
                "mirror in your bedroom, with a note that says, “Live for me -Casper”";
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
