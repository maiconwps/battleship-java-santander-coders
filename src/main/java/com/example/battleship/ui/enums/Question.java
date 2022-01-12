package com.example.battleship.ui.enums;

import com.example.battleship.ui.config.GameConfig;

import java.text.MessageFormat;
import java.util.Arrays;

public enum Question {
    POSITION_CHOOSE(ViewQuestionType.LINE,
            new QuestionTranslate("Choice the {0}º ship: ", LanguageOption.EN),
            new QuestionTranslate("Escolha o {0}º navio: ", LanguageOption.PT)),
    AFTER_INVALID_POSITION_CHOOSE(ViewQuestionType.MODAL,
            new QuestionTranslate("Invalid choose!\nPlease, choice the {0}º ship.", LanguageOption.EN),
            new QuestionTranslate("Valor inválido!\n Por favor, escolha o {0} navio.", LanguageOption.PT)),
    POSITION_ATTACK(ViewQuestionType.LINE,
            new QuestionTranslate("Choice the position attack: ", LanguageOption.EN),
            new QuestionTranslate("Escolha as coordenadas para o ataque: ", LanguageOption.PT)),
    AFTER_POSITION_ATTACK_CHOOSE(ViewQuestionType.MODAL,
            new QuestionTranslate("Invalid choose!\nPlease, choice the position\nattack.", LanguageOption.EN),
            new QuestionTranslate("Escolha inválida!\nPor favor, escolha\ncoordenadas válidas.", LanguageOption.PT)),

    GAME_WIN(ViewQuestionType.MODAL,
            new QuestionTranslate("Congratulations!\nYou won!!!", LanguageOption.EN),
            new QuestionTranslate("Vitória!!", LanguageOption.PT)),

    GAME_TIE(ViewQuestionType.MODAL,
            new QuestionTranslate("Tie!\nThat was close...", LanguageOption.EN),
            new QuestionTranslate("Empate:\nFoi por pouco.", LanguageOption.PT)),

    GAME_DEFEAT(ViewQuestionType.MODAL,
            new QuestionTranslate("Not this time...\nBetter luck next time!", LanguageOption.EN),
            new QuestionTranslate("Você perdeu!\nMais sorte na próxima...", LanguageOption.PT));


    private QuestionTranslate[] translates;
    private ViewQuestionType defaultView;

    private Question(ViewQuestionType defaultView, QuestionTranslate... translates){
        this.defaultView = defaultView;
        this.translates = translates;
    }

    public String getMessage(Object... args){
        String messageTemplate = Arrays.stream(this.translates).
                filter(t -> t.getLang() == GameConfig.language).
                findFirst().
                get().
                getMessageTemplate();

        return MessageFormat.format(messageTemplate, args);
    }

    public ViewQuestionType getDefaultView() {
        return defaultView;
    }
}


class QuestionTranslate{
    private LanguageOption lang;
    private String messageTemplate;

    public QuestionTranslate(String messageTemplate, LanguageOption lang) {
        this.lang = lang;
        this.messageTemplate = messageTemplate;
    }

    public LanguageOption getLang() {
        return lang;
    }

    public String getMessageTemplate() {
        return messageTemplate;
    }
}