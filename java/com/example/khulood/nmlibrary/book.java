package com.example.khulood.nmlibrary;


import java.io.Serializable;

public class book implements Serializable {
    private String Title;
    private String Author;
    private byte[] Image;
    private String Publisher;
    private String Publish_date;
    private String ISBN;
    private String Description;
    private String Issue;

    public book() {
    }

    public book(String title, String author, byte[] image, String publisher, String publish_date, String isbn, String description, String issue) {
        Title = title;
        Author = author;
        Image = image;
        Publisher = publisher;
        Publish_date = publish_date;
        ISBN = isbn;
        Description = description;
        Issue = issue;

    }


    public String getTitle() {
        return Title;
    }

    public String getAuthor() {
        return Author;
    }

    public byte[] getImage() {
        return Image;
    }

    public String getPublisher() { return Publisher; }

    public String getPublish_date() { return Publish_date; }

    public String getISBN() { return ISBN; }

    public String getDescription() { return Description; }

    public String getIssue() {
        return Issue;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public void setImage(byte[] image) {
        Image = image;
    }

    public void setPublisher(String publisher) { Publisher = publisher; }

    public void setPublish_date(String publish_date) { Publish_date = publish_date; }

    public void setISBN(String isbn) { ISBN = isbn; }

    public void setDescription(String description) { Description = description; }

    public void setIssue(String issue) {
        Issue = issue;
    }

}
