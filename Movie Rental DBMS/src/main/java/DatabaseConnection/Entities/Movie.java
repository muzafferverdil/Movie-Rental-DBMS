package DatabaseConnection.Entities;

import java.util.Date;

public class Movie{

    private int id;
    private String name;
    private double imdb;
    private Date relDate;
    private String picture;

    public Movie(String name, double imdb, Date relDate, String picture, int id) {
        this.name = name;
        this.imdb = imdb;
        this.relDate = relDate;
        this.picture = picture;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getImdb() {
        return imdb;
    }

    public void setImdb(double imdb) {
        this.imdb = imdb;
    }

    public Date getRelDate() {
        return relDate;
    }

    public void setRelDate(Date relDate) {
        this.relDate = relDate;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String path) {
        this.picture = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
