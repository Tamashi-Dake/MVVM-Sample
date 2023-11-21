package huce.fit.mvvmpattern.views.fragments.library.itemFavorite;

public class Favorite {

//    set ảnh từ drawable nên cần resourceId
    private int resourceId;
private String title;

    public Favorite(int resourceId, String title) {
        this.resourceId = resourceId;
        this.title = title;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
