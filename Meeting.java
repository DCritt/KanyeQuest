public class Meeting
{

   //this method is the entire body of this class
   public String callMeeting(Inventory inv, Diary dia)
   {
      // total just acts as a measure of the total strength of the evidence gathered
      int total = 0;
      //evidence can come in the form of objects picked up or diary entries,
      // so we iterate through both the inventory and diary of the player and
      //tally the total evidence strength
      for(Item i: inv.getElements())
      {
        total += i.getStrength().getStrength();
      }
      for(Integer i : dia.getStrengths())
      {
         total += i;
      }

      //if you've gathered enough evidence, you win
      //the 18 represents the threshold for whether enough evidence has been gathered
      // it could have been a variable if we had wanted to adjust the difficulty,
      // but we just decided to hard-code it
      if (total >= 18)
      {
         System.out.println("You win. The person who stole your Gordita Supreme was indeed was Kylie.");
         return "win";
      }
      //otherwise, you lose. don't flap your jaw if you can't back it up
      else
      {
         System.out.println("Better luck next time. Play again to see who actually stole your Gordita Supreme.");
         return "loss";
      }
   }
}
