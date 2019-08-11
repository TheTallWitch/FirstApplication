package com.developercat.firstapplication;

public class NewsItem {
    private int id;
    private String title;
    private String subTitle;
    private String text;
    private String image;
    private String imageDescription;
    private String time;
    private String publisher;

    public NewsItem(int id, String title, String subTitle, String text, String image, String imageDescription, String time, String publisher) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.text = text;
        this.image = image;
        this.imageDescription = imageDescription;
        this.time = time;
        this.publisher = publisher;
    }

    public int getId() {return id;}
    public String getImage() {return image;}
    public String getTitle() {return title;}
    public String getSubTitle() {return subTitle;}
    public String getText() {return text;}
    public String getImageDescription() {return imageDescription;}
    public String getTime() {return time;}
    public String getPublisher() {return publisher;}

}
