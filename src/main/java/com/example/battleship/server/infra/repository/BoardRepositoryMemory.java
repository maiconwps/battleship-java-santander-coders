package com.example.battleship.server.infra.repository;

import com.example.battleship.server.domain.enums.ShipType;
import com.example.battleship.server.domain.models.BoardModel;
import com.example.battleship.server.domain.models.FleetShipModel;
import com.example.battleship.server.domain.models.SquareModel;
import com.example.battleship.server.domain.repository.BoardRepository;


import java.util.ArrayList;
import java.util.Optional;

public class BoardRepositoryMemory implements BoardRepository {
    private ArrayList<BoardModel> gameBoards;

    public BoardRepositoryMemory(ArrayList<BoardModel> gameBoards){
        this.gameBoards = gameBoards;
    }

    public BoardModel getBoard(int boardId) {
        Optional<BoardModel> board = this.gameBoards.
                stream().
                filter(gameBoard -> gameBoard.getId() == boardId).
                findFirst();
        if(board.isPresent()){
            return board.get();
        }else{
            throw new NullPointerException("Game board doesn't exist");
        }
    }

    @Override
    public Optional<FleetShipModel> getShip(int boardId, int row, int column) {

        return getBoard(boardId).
                getFleet().
                stream().
                filter(ship -> ship.getOrigin().equals(new SquareModel(row, column))).
                findFirst();
    }

    @Override
    public int addShip(int boardId, int row, int column, ShipType shipType) {
        BoardModel gameBoard = this.getBoard(boardId);
        ArrayList<FleetShipModel> ships = gameBoard.getFleet();
        int shipId = ships.size() + 1;
        FleetShipModel ship = new FleetShipModel(shipId, new SquareModel(row, column), shipType);
        ships.add(ship);
        return shipId;
    }

}
