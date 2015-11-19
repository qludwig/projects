
package com.swcguild.dvdlibraryv4.model;

import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


/**
 *
 * @author Suzanne Ludwig
 */
public class Dvd {
    
    private int id;
    
    @NotEmpty(message="You must enter a title")
    @Length(max=50, message="Title must be no more than 50 characters")
    private String title;
    
    @Length(max=50, message="Release date must be no more than 50 characters")
    private String releaseDate;
    
    @Length(max=10, message="Rating must be no more than 10 characters")
    private String mpaaRating;
    
    @Length(max=50, message="Director must be no more than 50 characters")
    private String director;
    
    @Length(max=50, message="Studio must be no more than 50 characters")
    private String studio;
    
    @Length(max=100, message="Note must be no more than 100 characters")
    private String note;
    
    @Length(max=150, message="Image source must be no more than 150 characters")
    private String imgSrc;

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

//    public Dvd(int id, String title, LocalDate releaseDate, String mpaaRating, String director, String studio, String note) {
//        this.id = id;
//        this.title = title;
//        this.releaseDate = releaseDate;
//        this.mpaaRating = mpaaRating;
//        this.director = director;
//        this.studio = studio;
//        this.note = note;
//    }
    
    public Dvd() {}
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dvd other = (Dvd) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.releaseDate, other.releaseDate)) {
            return false;
        }
        if (!Objects.equals(this.mpaaRating, other.mpaaRating)) {
            return false;
        }
        if (!Objects.equals(this.director, other.director)) {
            return false;
        }
        if (!Objects.equals(this.studio, other.studio)) {
            return false;
        }
        if (!Objects.equals(this.note, other.note)) {
            return false;
        }
        if (!Objects.equals(this.imgSrc, other.imgSrc)) {
            return false;
        }
        return true;
    }
    
}
