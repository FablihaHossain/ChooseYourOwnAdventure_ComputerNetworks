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

    //Constructor
    public Character(String name, String gender, char genre)
    {
        this.name = name;
        this.gender = gender;
        this.genreChoosen = genre;
    }

    /**
     * getName A method that gets the name of the character object
     * @returns The name of the character
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * getGender A method that gets the gender of the character object
     * @returns The gender of the character
     */
    public String getGender()
    {
        return this.gender;
    }

    /**
     * setName A method that sets the name of the character object
     * @param The name of the character
     */
    public void setName(String n)
    {
        this.name = n;
    }    

    /**
     * setGender A method that sets the gender of the character object
     * @param The gender of the character
     */
    public void setGender(String g)
    {
        this.gender = g;
    }

    /**
     * getGenre: A method that gets the genre of the character object (choosen by user)
     * @returns The genre of the character
     */
    public char getGenre()
    {
        return this.genreChoosen;
    }

    /**
     * setGenre: A method that sets the genre of the character object (choosen by user)
     * @param The genre of the character
     */
    public void setGenre(char g)
    {
        this.genreChoosen = g;
    }

    /**
     * toString: A method that displays the attributes of the character object
     */
    public String toString()
    {
        String output = "Character Name: " + this.name + "\nCharacter Gender: " + this.gender;
        return output;
    }
}
