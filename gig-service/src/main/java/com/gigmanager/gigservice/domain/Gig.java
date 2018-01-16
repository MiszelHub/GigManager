package com.gigmanager.gigservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
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
    @Field("start_date")
    private LocalDate startDate;

    @Field("name")
    private String name;

    @Field("is_cancelled")
    private Boolean isCancelled;

    @Size(max = 225)
    @Field("description")
    private String description;

    @NotNull
    @Field("ticket_price")
    private BigDecimal ticketPrice;

    @NotNull
    @Field("stage_name")
    private String stageName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Gig startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
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

    public String getDescription() {
        return description;
    }

    public Gig description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getStageName() {
        return stageName;
    }

    public Gig stageName(String stageName) {
        this.stageName = stageName;
        return this;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
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
            ", startDate='" + getStartDate() + "'" +
            ", name='" + getName() + "'" +
            ", isCancelled='" + isIsCancelled() + "'" +
            ", description='" + getDescription() + "'" +
            ", ticketPrice='" + getTicketPrice() + "'" +
            ", stageName='" + getStageName() + "'" +
            "}";
    }
}
