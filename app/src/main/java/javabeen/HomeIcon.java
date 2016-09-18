package javabeen;

import android.graphics.Bitmap;

/**
 * Created by liang on 2016/9/17.
 */

public class HomeIcon {
    private Bitmap picture;
    private String text;

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public HomeIcon(Bitmap picture, String text) {
        this.picture = picture;
        this.text = text;
    }
}
