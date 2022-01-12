package com.example.battleship.ui.controlers;

import com.example.battleship.server.application.exceptions.UserError;
import com.example.battleship.server.application.services.GameApplicationService;
import com.example.battleship.server.domain.enums.WinStatus;
import com.example.battleship.server.domain.models.entities.BoardModel;


public class VirtualAttackCommandController {
    private static WinStatus attackRandomShip(int gameId, int playerId) {
        BoardModel board = GameApplicationService.getPlayerBoard(gameId, playerId);
        int boardSize = board.getSize();
        int row = (int) (Math.random() * boardSize);
        int column = (int) (Math.random() * boardSize);
        return GameApplicationService.executeShooting(gameId, playerId, row, column);
    }

    public static WinStatus executeAttackCommand(int gameId, int playerId) {
        while (true)
            try {
                return attackRandomShip(gameId, playerId);
            } catch (UserError error) {
                System.out.println("error: "+error.getMessage());
            }
    }
}
