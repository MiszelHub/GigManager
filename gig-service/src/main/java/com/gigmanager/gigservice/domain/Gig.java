package com.gigmanager.gigservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * A Gig.
 */
@Document(collection = "gig")
public class Gig implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("name")
    private String name;

    @Field("is_cancelled")
    private Boolean isCancelled;

    @NotNull
    @Field("ticket_price")
    private BigDecimal ticketPrice;

    @NotNull
    @Field("start_date")
    private LocalDate startDate;

    @NotNull
    @Field("start_time")
    private LocalTime startTime;

    //@Field("user_name")
    private String userName;

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

    public void setName(String name) {
        this.name = name;
    }

    public Gig name(String name) {
        this.name = name;
        return this;
    }

    public Boolean isIsCancelled() {
        return isCancelled;
    }

    public Gig isCancelled(Boolean isCancelled) {
        this.isCancelled = isCancelled;
        return this;
    }

    public void setIsCancelled(Boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Gig ticketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
        return this;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Gig startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public Gig startTime(LocalTime startTime) {
        this.startTime = startTime;
        return this;
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
        Gig gig = (Gig) o;
        if (gig.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), gig.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Gig{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", isCancelled='" + isIsCancelled() + "'" +
            ", ticketPrice=" + getTicketPrice() +
            ", startDate='" + getStartDate() + "'" +
            ", startTime='" + getStartTime() + "'" +
            "}";
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
