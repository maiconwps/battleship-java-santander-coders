package com.example.battleship.ui.utils;

import com.example.battleship.server.domain.models.FleetShipModel;
import com.example.battleship.server.domain.models.TrackedCoordinates;
import com.example.battleship.ui.models.GridSquare;

import java.util.ArrayList;

public class BuilderGrid {
    public static GridSquare[][] build(int boardSize,
                                       ArrayList<FleetShipModel> ships,
                                       ArrayList<TrackedCoordinates> coordinates){
        GridSquare[][] visibleBoard = new GridSquare[boardSize][boardSize];

        for(int i=0; i<boardSize; i++){
            for(int j=0; j<boardSize; j++){
                visibleBoard[i][j] = new GridSquare();
            }
        }

        ships.stream().forEach(
                s -> visibleBoard[s.getOrigin().getRow()][s.getOrigin().getColumn()]
                        .setShipStatus(s.getStatus())
        );

        if (coordinates != null){
            coordinates.stream().forEach(
                    c -> visibleBoard[c.getCoordinates().getRow()][c.getCoordinates().getColumn()]
                            .setShotStatus(c.getTrackingStatus())
            );
        }

        return visibleBoard;

    }
}
