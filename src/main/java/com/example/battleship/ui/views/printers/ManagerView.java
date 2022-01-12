package com.example.battleship.ui.views.printers;

import com.example.battleship.ui.enums.ViewQuestionType;
import com.example.battleship.ui.enums.ViewType;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class ManagerView {
    private static ViewType viewType = ViewType.ONLY_MAIN;
    private static String mainView;
    private static String backgroundView;

    public static void setMainView(String mainView) {
        ManagerView.mainView = mainView;
    }

    public static void setBackgroundView(String backgroundView) {
        ManagerView.backgroundView = backgroundView;
    }

    public static void showMergeViews(){
        if(mainView == null){
            System.out.println(backgroundView);
        }else{
            System.out.println(mergeViews());
        }
    }

    public static void showBackgroundView(){
        System.out.println(backgroundView);
    }

    public static void alternateViews(){
        String temp = mainView;
        mainView = backgroundView;
        backgroundView = temp;
    }

    private static String mergerLines(String mainLine, String backgroundLine, int pad){
        String trimmedMainLine = mainLine.trim();
        int indexInit = mainLine.indexOf(trimmedMainLine.charAt(0));
        int indexEnd = mainLine.lastIndexOf(trimmedMainLine.charAt(trimmedMainLine.length() - 1));
        String padString = StringUtils.repeat(" ", pad);
        String mainContent = padString + mainLine.substring(indexInit,indexEnd + 1) + padString;

        return StringUtils.overlay(backgroundLine, mainContent, indexInit - pad, indexEnd +1 + pad);
    }

    private static String mergeViews(){
        String[] mainLines = mainView.split("\n");
        String[] backgroundLines = backgroundView.split("\n");
        StringBuilder mergeLines = new StringBuilder();

        for(int i=0; i<backgroundLines.length; i++){
            String mergeLine;

            if( i < mainLines.length && !mainLines[i].isEmpty()){
                mergeLine = mergerLines(mainLines[i], backgroundLines[i], 4);
            }else{
                mergeLine = backgroundLines[i];
            }
            mergeLines.append(mergeLine + "\n");
        }

        return mergeLines.toString() ;
    }

    public static String showInlineView(){
        String[] mainLines = mainView.split("\n");
        String[] backgroundLines = backgroundView.split("\n");
        StringBuilder mergeLines = new StringBuilder();
        int pad = 4;

        for(int i=0; i<backgroundLines.length; i++){
            String mergeLine;

            if( i < mainLines.length && !mainLines[i].isEmpty()){
                mergeLine = mainLines[i] + StringUtils.repeat(" ", pad) + backgroundLines[i];
            }else{
                mergeLine = backgroundLines[i];
            }
            mergeLines.append(mergeLine + "\n");
        }

        return mergeLines.toString() ;
    }
}
