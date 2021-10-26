package fr.android.tennistrackerv2.Model;

public class ClubStats {

    private String name;
    private String imgUrl;
    private String imgUrl96;
    private Statistique statistique;

    public ClubStats() {
    }

    public ClubStats(String name, String imgUrl, String imgUrl96, Statistique statistique) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.imgUrl96 = imgUrl96;
        this.statistique = statistique;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl96() {
        return imgUrl96;
    }

    public void setImgUrl96(String imgUrl96) {
        this.imgUrl96 = imgUrl96;
    }

    public Statistique getStatistique() {
        return statistique;
    }

    public void setStatistique(Statistique statistique) {
        this.statistique = statistique;
    }
}
