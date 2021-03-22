package com.pequla.bot.modules.commands.meme;

public class Meme {
    private final String link;
    private final String title;
    private final String image;

    public Meme(String link, String title, String image) {
        this.link = link;
        this.title = title;
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }
}
