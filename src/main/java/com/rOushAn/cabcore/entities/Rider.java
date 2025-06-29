package com.rOushAn.cabcore.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(indexes = {
        @Index(name = "idx_rider_user", columnList = "user_id")
})
public class Rider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    @NotNull(message = "User must be associated with the rider")
    private User user;

    @NotNull(message = "Rating is required")
    @Min(value = 0, message = "Rating cannot be less than 0")
    @Max(value = 5, message = "Rating cannot be more than 5")
    private Double rating;

    private Rider(RiderBuilder builder) {
        this.id = builder.id;
        this.user = builder.user;
        this.rating = builder.rating;
    }

    public Rider() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public static class RiderBuilder {
        private Long id;
        private User user;
        private Double rating;

        public RiderBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public RiderBuilder setUser(User user) {
            this.user = user;
            return this;
        }

        public RiderBuilder setRating(Double rating) {
            this.rating = rating;
            return this;
        }

        public Rider build() {
            return new Rider(this);
        }
    }

    @Override
    public String toString() {
        return "Rider{" +
               "id=" + id +
               ", user=" + user +
               ", rating=" + rating +
               '}';
    }
}