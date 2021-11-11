package fr.android.tennistrackerv2.Model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class Match implements Serializable {

    private ClubStats club1;
    private ClubStats club2;
    private String dateMatch;
    private String addressMatch;
    private String serialId;
    private List<Upload> uploadList;
    private Address address;

    public Match() {
    }

    public Match(String serialId, ClubStats club1, ClubStats club2, String dateMatch, Address addressMatch) {
        this.serialId = serialId;
        this.club1 = club1;
        this.club2 = club2;
        this.dateMatch = dateMatch;
        this.address = addressMatch;
    }

    public Address getAddress() {
        return address;
    }

    public void setDateMatch(String dateMatch) {
        this.dateMatch = dateMatch;
    }

    public void setAddressMatch(String addressMatch) {
        this.addressMatch = addressMatch;
    }

    public String getSerialId() {
        return serialId;
    }

    public void setSerialId(String serialId) {
        this.serialId = serialId;
    }

    public void setUploadList(List<Upload> uploadList) {
        this.uploadList = uploadList;
    }

    public ClubStats getClub1() {
        return club1;
    }

    public void setClub1(ClubStats club1) {
        this.club1 = club1;
    }

    public ClubStats getClub2() {
        return club2;
    }

    public void setClub2(ClubStats club2) {
        this.club2 = club2;
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
