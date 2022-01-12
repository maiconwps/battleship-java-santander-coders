package com.example.battleship.server.application.services;

import com.example.battleship.server.application.enums.MessageError;
import com.example.battleship.server.application.exceptions.UserError;
import com.example.battleship.server.domain.enums.ShipType;
import com.example.battleship.server.domain.models.BoardModel;
import com.example.battleship.server.domain.repository.BoardRepository;

public class BoardApplicationService {
    private static BoardRepository boardRepository;

    public static void initService(BoardRepository boardRepository){
        BoardApplicationService.boardRepository = boardRepository;
    }

    public static BoardModel getGameBoard(int boardId){
        return boardRepository.getBoard(boardId);
    }

    public static BoardModel addShip(int boardId, int row, int column, ShipType shipType){
        BoardModel gameBoard = boardRepository.getBoard(boardId);
        int gameBoardSize = gameBoard.getSize();

        if(row< 0 || row >= gameBoardSize){
            throw new UserError(MessageError.INVALID_INT_RANGE, "row", 0, gameBoardSize);
        }

        if(column< 0 || column >= gameBoardSize){
            throw new UserError(MessageError.INVALID_INT_RANGE, "column", 0, gameBoardSize);
        }
        
        if(boardRepository.getShip(boardId, row, column).isPresent()){
            throw new UserError(MessageError.DUPLICATE_DATA, "positioned ship");
        }

        boardRepository.addShip(boardId, row, column, shipType);
        return boardRepository.getBoard(boardId);
    }
}
