import java.util.Scanner;

public class Item extends Collectible
{
    private boolean isEdible;

    public boolean isEdible()
    {
        return isEdible;
    }

    //This setter sets the isEdible string to true if the String passed in equals Y, otherwise, it makes it false
    public void setEdible(String edibleString)
    {
        isEdible = edibleString.equals("Y");
    }

    //See if the NPC would eat something by judging if it edible and the strength is greater than two
    public String wouldIEat()
    {
        return "Would I eat it after I ate a Gordita Supreme with Extra Sour Cream? " + (isEdible && (getStrength().getStrength() > 2) ? "yeah, I might" : "probably not");
    }

    public String toString()
    {
        return ("The item " + getName() + " can be described as " + getDescription() + " and its edibility value is " + isEdible);
    }

    //Help the user decide if the evidence is trustworthy
    //There is a ternary operator to see if the item is edible in the room
    //There is not trustworthy scale, however. It is just the NPC telling the user that they are the ones to play the game and to decide if something is trustworthy
    @Override
    public void isThisEvidenceGoingToBeTrustworthy()
    {
        Scanner kb = new Scanner(System.in);

        System.out.println("We have to consider the relation to the people in this room and in other rooms with the items in this room."
        + "\nFirst off, if this item is edible, does this discount a person in the room? " + (isEdible ? "Probably" : "Probably not") + "." +
        "\nWell, who in the right mind would be eating something after eating a Gordita Supreme with Extra Sour Cream?" +
        "\nNext, what would this item be used for in the story line? (Please type your response below");
        String doICare = kb.nextLine();
        System.out.println("You know what? I don't really care about the fact that: \n" + doICare + "\nThat's stupid. Sorry, I am just really hungry. I really wanted that Gordita." +
                "\nI think that it us up to you to decide if the item has to do with the story. After all, your are the player, of course!");
    }
}
