package com.sequenceiq.cloudbreak.api.endpoint.v1.distrox.base;

public enum DistroXResponseEntries {

    HARDWARE_INFO("hardware_info"),
    EVENTS("events");

    private final String entryName;

    DistroXResponseEntries(String entryName) {
        this.entryName = entryName;
    }

    public String getEntryName() {
        return entryName;
    }
}
