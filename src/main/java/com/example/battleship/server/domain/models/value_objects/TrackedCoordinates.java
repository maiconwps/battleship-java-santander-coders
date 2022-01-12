package com.example.battleship.server.domain.models.value_objects;

import com.example.battleship.server.domain.enums.ShotStatus;
import com.example.battleship.server.domain.models.value_objects.SquareModel;

public class TrackedCoordinates {
    SquareModel coordinates;
    ShotStatus trackingStatus;

    public TrackedCoordinates(SquareModel coordinates, ShotStatus trackingStatus) {
        this.coordinates = coordinates;
        this.trackingStatus = trackingStatus;
    }

    public SquareModel getCoordinates() {
        return coordinates;
    }

    public ShotStatus getTrackingStatus() {
        return trackingStatus;
    }
}
