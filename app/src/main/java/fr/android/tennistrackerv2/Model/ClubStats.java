package fr.android.tennistrackerv2.Model;

import java.io.Serializable;

public class ClubStats implements Serializable {

    private int id;
    private int id_stats;
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

    public ClubStats(int id, String name, String imgUrl, int id_stats) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.id_stats = id_stats;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getId_stats() {
        return id_stats;
    }

    public void setId_stats(int id_stats) {
        this.id_stats = id_stats;
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

    @Override
    public String toString() {
        return "ClubStats{" +
                "id=" + id +
                ", id_stats=" + id_stats +
                ", name='" + name + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", imgUrl96='" + imgUrl96 + '\'' +
                ", statistique=" + statistique +
                '}';
    }
}
