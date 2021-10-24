package fr.android.tennistrackerv2.Model;

public class FormatLastMatch {

    private String name;
    private int points;

    public FormatLastMatch() {

    }

    public FormatLastMatch(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        String stringFormatLastMatch;
        if(points > 0) {
            stringFormatLastMatch = name + " ( "+points+" points )";
        } else {
            stringFormatLastMatch =  name;
        }
        return stringFormatLastMatch;
    }
}
