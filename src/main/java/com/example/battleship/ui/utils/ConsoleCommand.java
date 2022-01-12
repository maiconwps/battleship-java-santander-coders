package com.example.battleship.ui.utils;

public class ConsoleCommand {
    public static void clear(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
