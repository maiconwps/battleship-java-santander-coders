package com.example.battleship.ui.controlers;

import com.example.battleship.server.application.services.GameApplicationService;
import com.example.battleship.server.domain.enums.GameType;
import com.example.battleship.server.domain.models.entities.BoardModel;
import com.example.battleship.server.domain.models.entities.GameModel;
import com.example.battleship.server.domain.models.value_objects.TrackedCoordinates;
import com.example.battleship.ui.config.GameConfig;
import com.example.battleship.ui.models.GridSquare;
import com.example.battleship.ui.utils.BuilderGrid;
import com.example.battleship.ui.views.GameBoardView;
import com.example.battleship.ui.views.printers.ManagerView;

import java.util.ArrayList;

public class GameController {
    public static GameModel initNewMachineGame(int configBoardSize, int... idPlayers) {
        return GameApplicationService.startNewGame(configBoardSize, GameConfig.quantityShip, GameType.MACHINE, idPlayers);
    }

    public static BoardModel getPlayerBoard(int gameId, int playerId) {
        return GameApplicationService.getPlayerBoard(gameId, playerId);
    }

    private static GameBoardView buildMyFullBoard (int gameId, int playerId){
        BoardModel board = GameApplicationService.getPlayerBoard(gameId, playerId);
        ArrayList<TrackedCoordinates> knownEnemyCoordinates =  GameApplicationService.
                getKnownEnemyCoordinates(gameId, playerId);
        GridSquare[][] grid = BuilderGrid.build(board.getSize(), board.getFleet(), knownEnemyCoordinates);
        GameBoardView gameBoardView = new GameBoardView(board.getSize());
        gameBoardView.setData(grid);
        return gameBoardView;
    }

    public static void showMyFullBoard (int gameId, int playerId){
        buildMyFullBoard(gameId, playerId).show();
    }

    public static void showFullBoards(int gameId, int playerId1, int playerId2){
        buildMyFullBoard(gameId, playerId2).buildView();
        ManagerView.alternateViews();
        buildMyFullBoard(gameId, playerId1).buildView();
        System.out.println(ManagerView.showInlineView());
    }

}
