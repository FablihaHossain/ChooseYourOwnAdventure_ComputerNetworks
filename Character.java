import java.io.Serializable;
/**
 * A class that represents a character object that will be used during the game
 *
 * @author Fabliha Hossain
 */
public class Character implements Serializable
{
    //Character Attributes
    private String name;
    private String gender;
    private char genreChoosen;
    private String[] inventory;
    private int numOfItems = 0;

    //Constructor
    public Character(String name, String gender, char genre)
    {
        this.name = name;
        this.gender = gender;
        this.genreChoosen = genre;
    }

    //A method to fill the user's inventory with intial items based on the genre choosen
    private void intialInventory(char genre)
    {
        //Filling bag based on genre choosen
        if(genre == 'A')
        {
            //to be filled later
        }
        else if(genre == 'M')
        {
            //to be filled later
        }
        else
        {
            //to be filled later
        }
    }

    //Getter and setter methods
    public String getName()
    {
        return this.name;
    }

    public String getGender()
    {
        return this.gender;
    }

    public void setName(String n)
    {
        this.name = n;
    }

    public void setGender(String g)
    {
        this.gender = g;
    }

    public char getGenre()
    {
        return this.genreChoosen;
    }
    
    public void setGenre(char g)
    {
        this.genreChoosen = g;
    }
    
    public String toString()
    {
        String output = "Character Name: " + this.name + "\nCharacter Gender: " + this.gender + "\nGenre Choosen: " +
        this.genreChoosen;
        return output;
    }
    //Method to add to inventory
    public void addInventory(String item)
    {
        inventory[numOfItems] = item;
        numOfItems++;
    }
}
