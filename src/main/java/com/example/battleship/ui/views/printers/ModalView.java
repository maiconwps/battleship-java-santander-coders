package com.example.battleship.ui.views.printers;

import com.example.battleship.ui.config.GameConfig;
import org.apache.commons.lang3.StringUtils;

public class ModalView {
    static int modalWidth = 30;
    static int screenWidth = 60;
    static int screenHeight = (2 + GameConfig.boarSize) * 2 - 1;

    private static String createLineModal(String content, String borderString){
        return StringUtils.center(
                StringUtils.rightPad(borderString, modalWidth, content) + borderString, screenWidth) + "\n";
    }

    private static String createVerticalSpace(int size){
        return StringUtils.repeat("\n", size);
    }

    public static void show(String message){
        String modal = "";
        String[] lines = message.split("\n");
        int upSpace = (screenHeight -lines.length) / 2;

        modal += createVerticalSpace(upSpace);
        modal += createLineModal("-", "+") ;

        for(String line: lines){
            modal += createLineModal(StringUtils.center(line, modalWidth), "|") ;
        };

        modal += createLineModal("-", "+") ;
        modal += createVerticalSpace(screenHeight - upSpace);

        ManagerView.setMainView(modal);
        ManagerView.showMergeViews();
    }
}
