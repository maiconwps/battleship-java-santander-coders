package com.example.battleship.ui.controlers;

import com.example.battleship.server.application.enums.MessageError;
import com.example.battleship.server.application.exceptions.UserError;
import com.example.battleship.server.application.services.GameApplicationService;
import com.example.battleship.ui.enums.Question;
import com.example.battleship.ui.utils.ConsoleCommand;
import com.example.battleship.ui.views.QuestionView;

public class RealAttackCommandController {
    private static final QuestionView questionnaire = new QuestionView();

    private static void choiceTargetSquare(int gameId, int playerId, Question question) {
        try {
            String position = questionnaire.ask(question.getMessage(), question.getDefaultView());
            int row = ((int) position.charAt(0)) - 65;
            int column = Integer.parseInt(position.substring(1));
            GameApplicationService.executeShooting(gameId, playerId, row, column);
        } catch (NumberFormatException e) {
            throw new UserError(MessageError.INVALID_INT_TYPE);
        }
    }
    private static void reChoiceTargetSquare(int gameId, int playerId) {
        boolean isValid = false;

        while (!isValid) {
            try {
                choiceTargetSquare(gameId, playerId, Question.AFTER_POSITION_ATTACK_CHOOSE);
                isValid = true;
            }catch (UserError newError) {
                isValid = false;
            }
        }
    }

    public static void executeAttackCommand(int gameId, int playerId) {
        try {
            choiceTargetSquare(gameId, playerId, Question.POSITION_ATTACK);
        } catch (UserError error) {
            reChoiceTargetSquare(gameId, playerId);
        }
        ConsoleCommand.clear();
    }
}
