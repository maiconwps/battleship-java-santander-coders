package com.example.battleship.ui.views.printers;

public class LineView {
    public static void show(String message){
        ManagerView.setMainView(null);
        System.out.println(message);
    }
}
