package com.example.battleship.ui.views;

import com.example.battleship.ui.config.GameConfig;
import com.example.battleship.ui.enums.LanguageOption;
import com.example.battleship.ui.enums.Question;
import com.example.battleship.ui.enums.ViewQuestionType;
import com.example.battleship.ui.views.printers.LineView;
import com.example.battleship.ui.views.printers.ModalView;

import java.util.Scanner;

public class QuestionView {
    private Scanner scanner;
    private Question currentQuestion;
    private ViewQuestionType currentViewType;
    private Object[] currentArgs;

    public QuestionView(Question question, Object... args){
        this.currentQuestion = question;
        this.currentViewType = question.getDefaultView();
        this.currentArgs = args;
        this.scanner = new Scanner(System.in);
    }

    private String verifyKeyboard(String keyBoard) {
        if (keyBoard.charAt(0) == '$') {
            String shortcut = keyBoard.substring(1);
            switch (shortcut){
                case "PT":
                case "EN":
                    GameConfig.language = LanguageOption.valueOf(shortcut);
                    return this.ask();
            }
        }
        return keyBoard;
    }

    public String ask() {
        switch (this.currentViewType){
            case LINE:
                LineView.show(this.currentQuestion.getMessage(this.currentArgs));
                break;
            case MODAL:
                ModalView.show(this.currentQuestion.getMessage(this.currentArgs));
        }

        String keyBoard = this.scanner.next();
        return this.verifyKeyboard(keyBoard);
    }
}
