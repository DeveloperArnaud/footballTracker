package fr.android.tennistrackerv2.Model;

public class Upload {

    private String name;
    private String imgUrl;

    public Upload() {

    }

    public Upload(String name, String imgUrl) {
        if(name.trim().equals("")) {
            name = "No name";
        }
        this.name = name;
        this.imgUrl = imgUrl;
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
}
