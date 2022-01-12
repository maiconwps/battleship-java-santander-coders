package com.example.battleship.server.domain.repository;

import com.example.battleship.server.domain.enums.GameType;
import com.example.battleship.server.domain.enums.WinStatus;
import com.example.battleship.server.domain.models.BattleCommandModel;
import com.example.battleship.server.domain.models.BoardModel;
import com.example.battleship.server.domain.models.GameModel;
import com.example.battleship.server.domain.models.ShotModel;

import java.util.ArrayList;

public interface GameRepository {
    GameModel getGame(int gameId);
    BoardModel getBoard(int gameId, int playerId);
    BattleCommandModel getBattleCommand(int gameId, int playerId);
    ArrayList<ShotModel> getMyShots(int gameId, int playerId);
    ArrayList<ShotModel> getEnemyShots(int gameId, int playerId);
    int create(int configBoardSize, int numberOfShips, GameType configGameType, int... idPlayers);
    WinStatus shoot(int gameId, int playerId , int row, int collum);

}
