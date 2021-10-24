package fr.android.tennistrackerv2.Model;

import java.io.Serializable;

public class Club implements Serializable {

    private String name;
    private String imgUrl;
    private String imgUrl96;


    public Club() {
    }

    public Club(String name, String imgUrl, String imgUrl96) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.imgUrl96 = imgUrl96;
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

    public void setImgUrl(String imgClub) {
        this.imgUrl = imgClub;
    }

    public String getImgUrl96() {
        return imgUrl96;
    }

    public void setImgUrl96(String imgUrl96) {
        this.imgUrl96 = imgUrl96;
    }

    @Override
    public String toString() {
        return name;
    }
}
