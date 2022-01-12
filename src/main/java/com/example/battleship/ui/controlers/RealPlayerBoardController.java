package com.example.battleship.ui.controlers;

import com.example.battleship.server.application.enums.MessageError;
import com.example.battleship.server.application.exceptions.UserError;
import com.example.battleship.server.application.services.BoardApplicationService;
import com.example.battleship.server.domain.enums.ShipType;
import com.example.battleship.ui.enums.Question;
import com.example.battleship.ui.utils.ConsoleCommand;
import com.example.battleship.ui.views.QuestionView;


public class RealPlayerBoardController extends BoardController {
    private void chooseSquare(int boardId, Question question, int index) {
        try {
            String position = new QuestionView(question, index).ask();
            int row = ((int) position.charAt(0)) - 65;
            int column = Integer.parseInt(position.substring(1));
            BoardApplicationService.addShip(boardId, row, column, ShipType.SUBMARINE);
        } catch (NumberFormatException e) {
            throw new UserError(MessageError.INVALID_INT_TYPE);
        }
    }
    private void reChooseSquare(int boardId, int index) {
        boolean isValid = false;

        while (!isValid) {
            try {
                chooseSquare(boardId, Question.AFTER_INVALID_POSITION_CHOOSE, index);
                isValid = true;
            }catch (UserError newError) {
                isValid = false;
            }
        }
    }
    public void chooseFleet(int boardId, int quantityShip) {
        for (int i = 1; i <= quantityShip; i++) {
            try {
                this.chooseSquare(boardId, Question.POSITION_CHOOSE, i);
            } catch (UserError error) {
               this.reChooseSquare(boardId, i);
            }
            ConsoleCommand.clear();
            showBoardGame(boardId);
        }
    }
}
