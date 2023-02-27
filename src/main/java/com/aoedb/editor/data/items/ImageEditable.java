package com.aoedb.editor.data.items;



public class ImageEditable extends Editable  {

    private String imagePath;
    public ImageEditable(int id) {
        super(id);
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
