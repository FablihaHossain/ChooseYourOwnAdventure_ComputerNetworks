PROJECT TITLE: Choose Your Own Adventure
PURPOSE OF PROJECT: This network application will simulate a game where a user
can create a character and create a story for that character based on their choices.
VERSION: Spring 2019
AUTHOR: Fabliha Hossain

Minimalist Version:
1. The user will choose one out of three genres 
Action => Synopsis: The user’s character got dragged into this 
Mystery	=> Synopsis: The user’s character is a detective that just received an unusual case
Horror => Synopsis: The user’s character wakes up to find themselves in an abandoned war camp with no clue how they got there

2. The user will be able to choose certain attributes of their character
Name
Gender

How to Use/Play:
1. The Client will first choose which genre they prefer
(For the rapid prototype, they can only choose Mystery to continue)
2. The Client will then choose the name and gender of their character.
3. The Server asks the client if they are ready to play, and the client should type in "Yes"
4. The game begins with the synopsis and asks the client for their first choice
Note: They must input the their choice in the format shown in parentheses. For ex: Continue? (Y) or (N) they must type in either 'Y' or 'N' (Case sensitive for now) otherwise their will be an error message. If error message is shown, they can try again by simply typing in the right input.
5. They continue with the scenarios until they reach the end of the game (or if they quit in the middle). 

Enhancements:
A. Graphical User Interface in Java when the user chooses the options using buttons on the application
