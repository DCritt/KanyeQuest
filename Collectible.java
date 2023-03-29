public abstract class Collectible
{
    private EvidenceStrength strength;
    private String name;
    private String description;

    //This method is here to help instantiate the Enums. They are final, and they are important to be instantiated here orelse they would be null.
    //It takes a given index (passed in through mine and Evan's InputLogic and puts it to an enum value).
    public void setStrengthEnum(int i)
    {
        switch (i)
        {
            case 0 -> {strength = EvidenceStrength.IRRELEVANT;}
            case 1 -> {strength = EvidenceStrength.WEAK;}
            case 2 -> {strength = EvidenceStrength.CIRCUMSTANTIAL;}
            case 3 -> {strength = EvidenceStrength.STRONG;}
            case 4 -> {strength = EvidenceStrength.CONCLUSIVE;}
        }
    }

    protected EvidenceStrength getStrength()
    { return strength; }

    protected void setStrength(int s)
    { strength.setStrength(s); }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public abstract void isThisEvidenceGoingToBeTrustworthy();
}
