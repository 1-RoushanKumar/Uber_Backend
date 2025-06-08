package com.rOushAn.cabcore.dtos;

public class RatingDto {

    private Double rating;

    public RatingDto() {
    }

    public RatingDto(Double rating) {
        this.rating = rating;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

}