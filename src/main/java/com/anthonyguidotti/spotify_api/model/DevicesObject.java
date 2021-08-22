package com.anthonyguidotti.spotify_api.model;

import java.util.List;

public class DevicesObject {
    private List<DeviceObject> devices;

    public List<DeviceObject> getDevices() {
        return devices;
    }

    public void setDevices(List<DeviceObject> devices) {
        this.devices = devices;
    }
}
