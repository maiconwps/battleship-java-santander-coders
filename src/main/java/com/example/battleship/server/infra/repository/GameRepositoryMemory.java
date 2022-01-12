package com.example.battleship.server.infra.repository;

import com.example.battleship.server.domain.enums.ShotStatus;
import com.example.battleship.server.domain.enums.GameType;
import com.example.battleship.server.domain.enums.TurnPlayerType;
import com.example.battleship.server.domain.enums.WinStatus;
import com.example.battleship.server.domain.models.entities.*;
import com.example.battleship.server.domain.models.value_objects.ConfigModel;
import com.example.battleship.server.domain.models.value_objects.SquareModel;
import com.example.battleship.server.domain.repository.GameRepository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class GameRepositoryMemory implements GameRepository {
    private ArrayList<GameModel> games;
    private ArrayList<BattleCommandModel> battleCommands;
    private ArrayList<BoardModel> gameBoards;
    private ArrayList<ShotModel> shots;

    public GameRepositoryMemory(ArrayList<GameModel> games,
                                ArrayList<BattleCommandModel> battleCommands,
                                ArrayList<BoardModel> gameBoards,
                                ArrayList<ShotModel> commands){
        this.games = games;
        this.battleCommands = battleCommands;
        this.gameBoards = gameBoards;
        this.shots = commands;
    }

    @Override
    public GameModel getGame(int gameId) {
        Optional<GameModel> game = this.games.
                stream().
                filter(g -> g.getId() == gameId).
                findFirst();
        if(game.isPresent()){
            return game.get();
        }else{
            throw new NullPointerException("Game doesn't exist");
        }
    }

    @Override
    public BoardModel getBoard(int gameId, int playerId) {
        int battleCommandId = this.battleCommands.
                stream().
                filter(bc -> bc.getGameId() == gameId && bc.getPlayerId() == playerId).
                findFirst().
                get().
                getId();

        return this.gameBoards.stream().
                filter(board -> board.getBattleCommandId() == battleCommandId).
                findFirst().get();
    }

    @Override
    public BattleCommandModel getBattleCommand(int gameId, int playerId) {
        return this.battleCommands.
                stream().
                filter(bc -> bc.getGameId() == gameId && bc.getPlayerId() == playerId).
                findFirst().
                get();
    }

    @Override
    public ArrayList<ShotModel> getMyShots(int gameId, int playerId) {
        return (ArrayList<ShotModel>) this.shots.
                stream().
                filter(s -> s.getBattleId() == this.getBattleCommand(gameId, playerId).getId()).
                collect(Collectors.toList());
    }

    @Override
    public ArrayList<ShotModel> getEnemyShots(int gameId, int playerId) {
        int enemyBattleCommandId = this.battleCommands.
                stream().
                filter(bc -> bc.getGameId() == gameId && bc.getPlayerId() != playerId).
                findFirst().
                get().
                getId();

        return (ArrayList<ShotModel>) this.shots.
                stream().
                filter(s -> s.getBattleId() == enemyBattleCommandId).
                collect(Collectors.toList());
    }

    @Override
    public int create(int configBoardSize, int numberOfShips, GameType configGameType, int... idPlayers) {
        int idGame = this.games.size() + 1;
        ConfigModel config = new ConfigModel(configBoardSize, numberOfShips, configGameType);

        for(int i =0; i < 2; i++){
            int idBattleCommand = this.battleCommands.size() + 1;
            TurnPlayerType playerType = i == 0? TurnPlayerType.PRIMARY: TurnPlayerType.SECONDARY;
            BattleCommandModel battleCommand = new BattleCommandModel(idBattleCommand,idPlayers[i], idGame, playerType);
            this.battleCommands.add(battleCommand);

            int idGameBoard = this.gameBoards.size() + 1;
            BoardModel gameBoard = new BoardModel(
                    idGameBoard,
                    configBoardSize,
                    idBattleCommand);
            this.gameBoards.add(gameBoard);
        }

        GameModel game = new GameModel(idGame, config);
        this.games.add(game);
        return idGame;
    }

    @Override
    public WinStatus shoot(int gameId, int playerId, int row, int collum) {
        SquareModel coordinates = new SquareModel(row, collum);
        BattleCommandModel battleCommand = this.getBattleCommand(gameId, playerId);


        BattleCommandModel opposingBattleCommand = this.battleCommands.
                stream().
                filter(bc -> bc.getPlayerId() != playerId && bc.getGameId() == gameId).
                findFirst().
                get();

        BoardModel opposingBoard = this.gameBoards.
                stream().
                filter(b -> b.getBattleCommandId() == opposingBattleCommand.getId()).
                findFirst().
                get();

        Optional<FleetShipModel> positionedShip = opposingBoard.
                getFleet().
                stream().
                filter(ps -> ps.getOrigin().equals(coordinates)).
                findFirst();

        ShotStatus shotStatus = positionedShip.isPresent() ? ShotStatus.HIT : ShotStatus.MISS;

        int commandId = this.shots.size() + 1;
        ShotModel command = new ShotModel(commandId, battleCommand.getId(), new SquareModel(row, collum), shotStatus);
        this.shots.add(command);

        GameModel game =  this.getGame(gameId);
        this.updateWinStatus(battleCommand, opposingBattleCommand, game);
        return battleCommand.getVictoryStatus();
    }

    private void updateWinStatus(BattleCommandModel myCommand,
                                 BattleCommandModel opposingCommand,
                                 GameModel game){
        int totalSuccessfulCommands = (int) this.shots.
                stream().
                filter(s -> s.getBattleId() == myCommand.getId() && s.getStatus() == ShotStatus.HIT).
                count();

        switch (game.getCurrentTurn().getCurrentPlayer()){
            case PRIMARY:
                if(totalSuccessfulCommands == game.getConfig().getNumberOfShips()) {
                    myCommand.setVictoryStatus(WinStatus.PENDING);
                    opposingCommand.setVictoryStatus(WinStatus.PENDING);
                }
                game.nextRound();
                break;
            case SECONDARY:
                if(totalSuccessfulCommands == game.getConfig().getNumberOfShips()){
                    if(myCommand.getVictoryStatus() == WinStatus.PENDING) {
                        myCommand.setVictoryStatus(WinStatus.TIE);
                        opposingCommand.setVictoryStatus(WinStatus.TIE);
                    }else{
                        myCommand.setVictoryStatus(WinStatus.VICTORY);
                        opposingCommand.setVictoryStatus(WinStatus.DEFEAT);
                    }
                }else{
                    if(myCommand.getVictoryStatus() == WinStatus.PENDING){
                        myCommand.setVictoryStatus(WinStatus.DEFEAT);
                        opposingCommand.setVictoryStatus(WinStatus.VICTORY);
                    }else{
                        game.nextRound();
                    }
        }

        }
    }
}
