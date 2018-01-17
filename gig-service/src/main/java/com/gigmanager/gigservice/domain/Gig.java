package com.gigmanager.gigservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
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

    @NotNull
    @Field("start_date")
    private ZonedDateTime startDate;

    @Field("is_cancelled")
    private Boolean isCancelled;

    @NotNull
    @Field("ticket_price")
    private BigDecimal ticketPrice;

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

    public Gig name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public Gig startDate(ZonedDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
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

    public Gig ticketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
        return this;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
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
            ", startDate='" + getStartDate() + "'" +
            ", isCancelled='" + isIsCancelled() + "'" +
            ", ticketPrice=" + getTicketPrice() +
            "}";
    }
}
