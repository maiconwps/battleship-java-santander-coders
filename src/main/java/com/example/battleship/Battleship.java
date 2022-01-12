package com.example.battleship;


import com.example.battleship.server.BattleshipServer;
import com.example.battleship.ui.BattleshipClient;


import java.io.IOException;

public class Battleship {

    public static void main(String[] args) throws IOException {
        BattleshipServer.initServer();
        BattleshipClient newGame = new BattleshipClient();
        newGame.playSinglePlayerGame();
    }

}
