package com.gigmanager.bandservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.gigmanager.bandservice.domain.enumeration.MusicGenre;

/**
 * A Band.
 */
@Document(collection = "band")
public class Band implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Size(max = 100)
    @Field("name")
    private String name;

    @Field("origin")
    private String origin;

    @NotNull
    @Field("genre")
    private MusicGenre genre;

    @NotNull
    @Field("date_of_formation")
    private LocalDate dateOfFormation;

    @Size(max = 512)
    @Field("bio")
    private String bio;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Band name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public Band origin(String origin) {
        this.origin = origin;
        return this;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public MusicGenre getGenre() {
        return genre;
    }

    public Band genre(MusicGenre genre) {
        this.genre = genre;
        return this;
    }

    public void setGenre(MusicGenre genre) {
        this.genre = genre;
    }

    public LocalDate getDateOfFormation() {
        return dateOfFormation;
    }

    public Band dateOfFormation(LocalDate dateOfFormation) {
        this.dateOfFormation = dateOfFormation;
        return this;
    }

    public void setDateOfFormation(LocalDate dateOfFormation) {
        this.dateOfFormation = dateOfFormation;
    }

    public String getBio() {
        return bio;
    }

    public Band bio(String bio) {
        this.bio = bio;
        return this;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Band band = (Band) o;
        if (band.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), band.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Band{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", origin='" + getOrigin() + "'" +
            ", genre='" + getGenre() + "'" +
            ", dateOfFormation='" + getDateOfFormation() + "'" +
            ", bio='" + getBio() + "'" +
            "}";
    }
}
