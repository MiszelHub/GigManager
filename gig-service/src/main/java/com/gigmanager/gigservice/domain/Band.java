package com.gigmanager.gigservice.domain;

import java.io.Serializable;

public class Band implements Serializable {
    private String id;
    private String bandName;
    private boolean isGigHost;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public boolean isGigHost() {
        return isGigHost;
    }

    public void setGigHost(boolean gigHost) {
        isGigHost = gigHost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Band band = (Band) o;

        return id.equals(band.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
