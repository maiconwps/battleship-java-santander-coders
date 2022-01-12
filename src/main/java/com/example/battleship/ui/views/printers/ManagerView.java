package com.example.battleship.ui.views.printers;

import com.example.battleship.ui.enums.BackgroundType;
import com.example.battleship.ui.enums.ViewType;
import org.apache.commons.lang3.StringUtils;


public class ManagerView {
    private static ViewType viewType = ViewType.ONLY_MAIN;
    private static BackgroundType backgroundType = BackgroundType.ONLY_SCREEN1;
    private static String mainView;
    private static String backgroundView;
    private static String contentScreen1;
    private static String contentScreen2;

    public static void setMainView(String mainView) {
        ManagerView.mainView = mainView;
    }

    public static void setToScreen1(String backgroundView) {
        ManagerView.contentScreen1 = backgroundView;
    }

    public static void showMergeViews(){
       viewType = ViewType.MERGED;
       show();
    }

    public static void showBackgroundView(){
        viewType = ViewType.ONLY_BACKGROUND;
        show();
    }

    public static void showInlineScreens(){
        backgroundType = BackgroundType.INLINE;
        show();
    }

    public static void alternateScreens(){
        String temp = contentScreen1;
        contentScreen1 = contentScreen2;
        contentScreen2 = temp;
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

    public static void setToInlineScreens(){
        String[] contentScreenLines1 = contentScreen1.split("\n");
        String[] contentScreenLines2 = contentScreen2.split("\n");
        StringBuilder mergeLines = new StringBuilder();
        int pad = 4;

        for(int i=0; i<contentScreenLines2.length; i++){
            String mergeLine;

            if( i < contentScreenLines1.length && !contentScreenLines1[i].isEmpty()){
                mergeLine = contentScreenLines1[i] + StringUtils.repeat(" ", pad) + contentScreenLines2[i];
            }else{
                mergeLine = contentScreenLines2[i];
            }
            mergeLines.append(mergeLine + "\n");
        }

        backgroundView  = mergeLines.toString();
        backgroundType = BackgroundType.INLINE;
    }

    public static void show(){
        String view = "";

        switch (backgroundType){
            case ONLY_SCREEN1:
                backgroundView = contentScreen1;
                break;
            case INLINE:
                setToInlineScreens();
        }

        switch (viewType){
            case ONLY_MAIN:
                view = mainView;
                break;
            case MERGED:
                view = mergeViews();
                break;
            case ONLY_BACKGROUND:
                view = backgroundView;
        }
        System.out.println(view);
    }
}
