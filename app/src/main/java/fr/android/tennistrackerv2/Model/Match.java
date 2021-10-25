package fr.android.tennistrackerv2.Model;

import java.sql.Date;
import java.util.List;

public class Match {

    private Club club1;
    private Club club2;
    private String dateMatch;
    private String addressMatch;
    private List<Upload> uploadList;

    public Match() {
    }

    public Match(Club club1, Club club2, String dateMatch, String addressMatch) {
        this.club1 = club1;
        this.club2 = club2;
        this.dateMatch = dateMatch;
        this.addressMatch = addressMatch;
    }

    public Club getClub1() {
        return club1;
    }

    public Club getClub2() {
        return club2;
    }

    public String getDateMatch() {
        return dateMatch;
    }

    public String getAddressMatch() {
        return addressMatch;
    }

    public List<Upload> getUploadList() {
        return uploadList;
    }

    @Override
    public String toString() {
        return "Match{" +
                "club1=" + club1 +
                ", club2=" + club2 +
                ", dateMatch='" + dateMatch + '\'' +
                ", addressMatch='" + addressMatch + '\'' +
                ", uploadList=" + uploadList +
                '}';
    }
}
