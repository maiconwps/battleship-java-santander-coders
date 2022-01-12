package com.example.battleship.ui.controlers;

import com.example.battleship.server.application.exceptions.UserError;
import com.example.battleship.server.application.services.BoardApplicationService;
import com.example.battleship.server.domain.enums.ShipType;
import com.example.battleship.server.domain.models.entities.BoardModel;

public class VirtualPlayerBoardController extends BoardController {
    private void addPositionShip(int boardId, int boardSize) {
        int row = (int) (Math.random() * boardSize);
        int column = (int) (Math.random() * boardSize);
        BoardApplicationService.addShip(boardId, row, column, ShipType.SUBMARINE);
    }

    @Override
    public void chooseFleet(int boardId, int playerId, int quantityShip) {
        BoardModel board = BoardApplicationService.getGameBoard(boardId);
        boolean isValidValues;
        for (int i = 1; i <= quantityShip; i++) {
            isValidValues = false;
            while (!isValidValues)
            try {
                this.addPositionShip(boardId, board.getSize());
                isValidValues = true;
            } catch (UserError error) {
                isValidValues = false;
            }
        }
    }
}
