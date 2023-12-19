package huce.fit.mvvmpattern.views.fragments.library.itemPlaylist;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Playlist implements Serializable {


    //    set ảnh từ drawable nên cần resourceId
    @SerializedName("id")
    private String id;
    @SerializedName("linkPicture")
    private String imageUrl;
    @SerializedName("name")
    private String title;

    //    public Playlist(String imageUrl, String title) {
//        this.imageUrl = imageUrl;
//        this.title = title;
//    }
    public Playlist(String imageUrl, String title,String id) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}