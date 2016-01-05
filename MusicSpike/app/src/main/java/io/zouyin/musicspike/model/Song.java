package io.zouyin.musicspike.model;

public class Song {
    private String name;
    private String url;
    private String image;

    public Song(String image, String name, String url) {
        this.image = image;
        this.name = name;
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
