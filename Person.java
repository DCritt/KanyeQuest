import java.util.Scanner;

public class Person extends Collectible
{
    private String dialogue;

    public String getDialogue() { return dialogue; }

    public void setDialogue(String dialogue) { this.dialogue = dialogue; }

    //See if the person's alibi is trustworthy
    //Use the ternary operator to see if the strength of the person's info is important. The trustworthiness is just opposite to evidence strength
    public String doITrust() {return "Do I trust this person's alibi? " + (getStrength().getStrength() >= 2 ? "I don't think so" : "Maybe");}

    //See if the evidence is going to be trustworthy by making a scale from one to ten. Get the trust score by getting the evidence strength of the person
    //Then the user inputs if they know of any evidence related to the person
    //The code returns the trustworthiness rating
    @Override
    public void isThisEvidenceGoingToBeTrustworthy()
    {
        int trustScore = 0;
        trustScore += (getStrength().getStrength() * 2);
        Scanner kb = new Scanner(System.in);
        System.out.println("Well," + doITrust() + "\nIs there any evidence related to them in this room or other rooms? (Type yes or no)");
        String relativeEvidence = kb.nextLine();
        trustScore += relativeEvidence.equalsIgnoreCase("Yes") ? 2 : 0;
        System.out.println("If I was to rate it on a scale from trustworthy to untrustworthy, I'd give it a solid " + trustScore + "/10.");

    }
}
