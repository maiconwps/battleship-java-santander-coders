package com.example.battleship.ui.workflows;

import com.example.battleship.server.domain.enums.WinStatus;
import com.example.battleship.server.domain.models.BoardModel;
import com.example.battleship.server.domain.models.GameModel;
import com.example.battleship.server.domain.models.PlayerModel;
import com.example.battleship.ui.controlers.GameController;
import com.example.battleship.ui.enums.Question;
import com.example.battleship.ui.views.FinalGameView;

public abstract class GameWorkflow {
    protected PlayerModel primaryPlayer;
    protected PlayerModel secondaryPlayer;

    protected GameModel currentGame;

    protected BoardModel boarPrimaryPlayer;
    protected BoardModel boardSecondaryPlayer;

    protected abstract  void initGame();

    private void endGame(WinStatus primaryPlayerWinStatus){
        switch (primaryPlayerWinStatus){
            case VICTORY:
                FinalGameView.show(Question.GAME_WIN.getMessage());
                break;
            case DEFEAT:
                FinalGameView.show(Question.GAME_DEFEAT.getMessage());
                break;
            case TIE:
                FinalGameView.show(Question.GAME_TIE.getMessage());
        }

        GameController.showFullBoards(this.currentGame.getId(), this.secondaryPlayer.getId(), this.primaryPlayer.getId());
    }

    protected abstract WinStatus runSGame();

    public  void play(){
        this.initGame();
        WinStatus status = this.runSGame();
        this.endGame(status);
    };
}
