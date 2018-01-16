package com.gigmanager.gigservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.gigmanager.gigservice.domain.enumeration.Genre;

/**
 * A Band.
 */
@Document(collection = "band")
public class Band implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;

    @NotNull
    @Size(min = 1, max = 100)
    @Field("name")
    private String name;

    @NotNull
    @Field("music_genre")
    private Genre musicGenre;

    @Field("origin")
    private String origin;

    @Pattern(regexp = "^(?:http(s)?:\\/\\/)?[\\w.-]+(?:\\.[\\w\\.-]+)+[\\w\\-\\._~:/?#[\\]@!\\$&'\\(\\)\\*\\+,;=.]+$")
    @Field("site_url")
    private String siteUrl;

    @NotNull
    @Field("date_of_formation")
    private LocalDate dateOfFormation;

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

    public Genre getMusicGenre() {
        return musicGenre;
    }

    public Band musicGenre(Genre musicGenre) {
        this.musicGenre = musicGenre;
        return this;
    }

    public void setMusicGenre(Genre musicGenre) {
        this.musicGenre = musicGenre;
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

    public String getSiteUrl() {
        return siteUrl;
    }

    public Band siteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
        return this;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
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
            ", musicGenre='" + getMusicGenre() + "'" +
            ", origin='" + getOrigin() + "'" +
            ", siteUrl='" + getSiteUrl() + "'" +
            ", dateOfFormation='" + getDateOfFormation() + "'" +
            "}";
    }
}
