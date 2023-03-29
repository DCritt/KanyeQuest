public enum EvidenceStrength
{
    //technically we could have just used an integer, but we needed an Enum somewhere
    IRRELEVANT(0),
    WEAK(1),
    CIRCUMSTANTIAL(2),
    STRONG(3),
    CONCLUSIVE(4);

    private int strength;

    EvidenceStrength(int s)
    {
        strength = s;
    }

    public int getStrength()
    {
        return strength;
    }

    public void setStrength(int strength)
    {
        this.strength = strength;
    }
}
