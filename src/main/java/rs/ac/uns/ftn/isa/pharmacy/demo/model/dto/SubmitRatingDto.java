package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.io.Serializable;

public class SubmitRatingDto implements Serializable {
    private long id;
    private int rating;

    public SubmitRatingDto() {
    }

    public SubmitRatingDto(long id, int rating) {
        this.id = id;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
