package com.example.doggoApp.doggoApp.domain;

public enum Status {
    ACCEPTED("Accepted"),
    REJECTED("Rejected"),
    STARTED("Started"),
    IN_PROGRESS("Adoption in progress"),
    COMPLETED("Adoption completed"),
    CANCELED("Adoption canceled");

    private final String description;

    Status(String description) {
        this.description = description;
    }


    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
