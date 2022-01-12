package com.example.battleship.server.application.services;

import com.example.battleship.server.application.enums.MessageError;
import com.example.battleship.server.application.exceptions.UserError;
import com.example.battleship.server.domain.enums.GameType;
import com.example.battleship.server.domain.enums.TurnPlayerType;
import com.example.battleship.server.domain.enums.WinStatus;
import com.example.battleship.server.domain.models.entities.BattleCommandModel;
import com.example.battleship.server.domain.models.entities.BoardModel;
import com.example.battleship.server.domain.models.entities.GameModel;
import com.example.battleship.server.domain.models.entities.ShotModel;
import com.example.battleship.server.domain.models.value_objects.TrackedCoordinates;
import com.example.battleship.server.domain.repository.GameRepository;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class GameApplicationService {
    private static GameRepository gameRepository;

    public static void initService(GameRepository gameRepository){
        GameApplicationService.gameRepository = gameRepository;
    }

    public static GameModel startNewGame(int configBoardSize, int numberOfShips, GameType configGameType, int... idPlayers){
        int gameId = gameRepository.create(configBoardSize, numberOfShips, configGameType, idPlayers);
        return gameRepository.getGame(gameId);
    }

    public static BoardModel getPlayerBoard(int gameId, int playerId){
        return gameRepository.getBoard(gameId, playerId);
    }

    public static WinStatus executeShooting(int gameId, int playerId, int row, int column){
        GameModel game = gameRepository.getGame(gameId);
        BattleCommandModel battleCommand = gameRepository.getBattleCommand(gameId, playerId);
        TurnPlayerType turnPlayer = battleCommand.getTurnPlayer();
        int gameBoardSize = game.getConfig().getSizeBoard();

        if(game.getCurrentTurn().getCurrentPlayer() != turnPlayer){
            throw new UserError(MessageError.USER_NOT_ABLE);
        }

        if(battleCommand.getVictoryStatus() != null &&
                battleCommand.getVictoryStatus() != WinStatus.PENDING){
            throw new UserError(MessageError.USER_NOT_ABLE);
        }

        if(row< 0 || row >= gameBoardSize){
            throw new UserError(MessageError.INVALID_INT_RANGE, "row", 0, gameBoardSize);
        }

        if(column< 0 || column >= gameBoardSize){
            throw new UserError(MessageError.INVALID_INT_RANGE, "column", 0, gameBoardSize);
        }
        return gameRepository.shoot(gameId, playerId, row, column);
    }

    public static ArrayList<TrackedCoordinates> getKnownEnemyCoordinates(int gameId, int playerId) {
        ArrayList<ShotModel> myShots = gameRepository.getMyShots(gameId, playerId);
        return (ArrayList<TrackedCoordinates>) myShots.
                stream().
                map(s -> new TrackedCoordinates(s.getCoordinates(), s.getStatus())).
                collect(Collectors.toList());
    }

    public static ArrayList<TrackedCoordinates> getMyCoordinatesDiscovered(int gameId, int playerId) {
        ArrayList<ShotModel> enemyShots = gameRepository.getEnemyShots(gameId, playerId);
        return (ArrayList<TrackedCoordinates>) enemyShots.
                stream().
                map(s -> new TrackedCoordinates(s.getCoordinates(), s.getStatus())).
                collect(Collectors.toList());
    }
}
