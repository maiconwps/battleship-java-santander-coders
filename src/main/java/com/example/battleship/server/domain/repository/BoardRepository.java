package com.example.battleship.server.domain.repository;

import com.example.battleship.server.domain.enums.ShipType;
import com.example.battleship.server.domain.models.BoardModel;
import com.example.battleship.server.domain.models.FleetShipModel;

import java.util.Optional;

public interface BoardRepository {
    BoardModel getBoard(int boardId);
    Optional<FleetShipModel> getShip(int boardId, int row, int column);
    int addShip(int boardId, int row, int column, ShipType shipType);
}
