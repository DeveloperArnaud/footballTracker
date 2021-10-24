package fr.android.tennistrackerv2.Model;

public class Format {

    private int advantage;
    private int nbJeux;
    private String tieBreak;


    public Format() {

    }

    public Format(int advantage, int nbJeux, String tieBreak) {
        this.advantage = advantage;
        this.nbJeux = nbJeux;
        this.tieBreak = tieBreak;
    }

    public int getAdvantage() {
        return advantage;
    }

    public int getNbJeux() {
        return nbJeux;
    }

    public String getTieBreak() {
        return tieBreak;
    }

    @Override
    public String toString() {
        String stringMatch;
        if(advantage > 0 ) {
            stringMatch = "2 sets, " + nbJeux + " jeux, Avantage, TB "+ tieBreak;
        } else {
            stringMatch = "2 sets, " + nbJeux + " jeux, Pas d'avantage, TB "+ tieBreak;
        }
        return stringMatch;
    }
}
