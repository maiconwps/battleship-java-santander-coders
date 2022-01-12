package com.example.battleship.ui.workflows;

import com.example.battleship.server.domain.enums.WinStatus;
import com.example.battleship.server.domain.models.PlayerModel;
import com.example.battleship.ui.controlers.*;
import com.example.battleship.ui.config.GameConfig;

public class SingleGameWorkflow extends GameWorkflow{

    public  SingleGameWorkflow(PlayerModel player){
        this.primaryPlayer = player;
        this.secondaryPlayer = PlayerController.crateNewVirtualPlayer("Computador");
    }

    protected void initGame(){
        this.currentGame = GameController.initNewMachineGame(GameConfig.boarSize,
                this.primaryPlayer.getId(), this.secondaryPlayer.getId());

        this.boarPrimaryPlayer = GameController.getPlayerBoard(this.currentGame.getId(), this.primaryPlayer.getId());
        BoardController.showBoardGame(this.boarPrimaryPlayer.getId());
        new RealPlayerBoardController().chooseFleet(this.boarPrimaryPlayer.getId(), GameConfig.quantityShip);

        this.boardSecondaryPlayer = GameController.getPlayerBoard(this.currentGame.getId(), this.secondaryPlayer.getId());
        new VirtualPlayerBoardController().chooseFleet(this.boardSecondaryPlayer.getId(), GameConfig.quantityShip);
        BoardController.showBoardGame(this.boardSecondaryPlayer.getId());

    }

    protected WinStatus runSGame(){
        WinStatus virtualWinStatus = null;
        WinStatus primaryPlayerWinStatus = null;

        while(virtualWinStatus == null){
            RealAttackCommandController.executeAttackCommand(this.currentGame.getId(), this.primaryPlayer.getId());
            GameController.showMyFullBoard(this.currentGame.getId(), this.primaryPlayer.getId());
            virtualWinStatus = VirtualAttackCommandController.executeAttackCommand(this.currentGame.getId(), this.secondaryPlayer.getId());
        }

        switch (virtualWinStatus){
            case DEFEAT:
                primaryPlayerWinStatus = WinStatus.VICTORY;
                break;
            case VICTORY:
                primaryPlayerWinStatus = WinStatus.DEFEAT;
                break;
            case TIE:
                primaryPlayerWinStatus = WinStatus.TIE;
        }

        return primaryPlayerWinStatus;
    }
}
