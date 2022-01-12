package com.example.battleship.ui.controlers;

import com.example.battleship.server.application.services.BoardApplicationService;
import com.example.battleship.server.application.services.PlayerApplicationService;
import com.example.battleship.server.domain.models.entities.BoardModel;
import com.example.battleship.server.domain.models.entities.PlayerModel;
import com.example.battleship.ui.models.GridSquare;
import com.example.battleship.ui.utils.BuilderGrid;
import com.example.battleship.ui.views.GameBoardView;


public abstract class BoardController {
    public static void showBoardGame(int idBoarGame, int playerId) {
        BoardModel board = BoardApplicationService.getGameBoard(idBoarGame);
        GameBoardView gameBoardView = new GameBoardView(board.getSize());
        PlayerModel player = PlayerApplicationService.getPlayer(playerId);

        GridSquare[][] grid = BuilderGrid.build(board.getSize(), board.getFleet(), null);
        gameBoardView.setData(grid, player.getName());
        gameBoardView.show();
    }

    public abstract void chooseFleet(int boardId, int playerId, int quantityShip);
}
