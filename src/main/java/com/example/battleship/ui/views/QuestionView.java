package com.example.battleship.ui.views;

import com.example.battleship.ui.enums.ViewQuestionType;
import com.example.battleship.ui.views.printers.LineView;
import com.example.battleship.ui.views.printers.ModalView;

import java.util.Scanner;

public class QuestionView {
    private Scanner scanner;

    public QuestionView() {
        this.scanner = new Scanner(System.in);
    }

    private void verifyKeyboard(String keyBoard) {
        if (keyBoard.charAt(0) == '$') {
            System.out.println("Deu errado");
        }
    }

    public String ask(String question, ViewQuestionType viewType) {
        switch (viewType) {
            case LINE:
                LineView.show(question);
                break;
            case MODAL:
                ModalView.show(question);
        }

        String keyBoard = this.scanner.next();
        this.verifyKeyboard(keyBoard);
        return keyBoard;
    }
}
