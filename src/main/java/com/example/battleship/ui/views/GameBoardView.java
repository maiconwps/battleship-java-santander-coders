package com.example.battleship.ui.views;

import com.example.battleship.ui.models.GridSquare;
import com.example.battleship.ui.views.printers.ManagerView;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class GameBoardView {
    private final int size;
    private final int lengthCell = 6;
    private GridSquare[][] data;
    private String[] customize;

    public GameBoardView(int size){
        this.size = size;
        this.data = new GridSquare[size][size];
    }

    public void setData(GridSquare[][] data, String... customize){
        this.data = data;
        this.customize = customize;
    }

    private String buildRowSeparator(){
        int lengthRowChar = this.lengthCell*(this.size + 1) + 1;
        return StringUtils.rightPad("-", lengthRowChar, "-") +"\n";
    }

    private String buildColumnSeparator(){
        return "|";
    }

    private String buildTitle(){
        int lengthRowChar = this.lengthCell*(this.size + 1) + 1;
        String title = this.customize.length > 0 ? customize[0].toUpperCase() : "Player";
        return StringUtils.center(title, lengthRowChar)+"\n";
    }

    private String buildCell(String data){
        return StringUtils.center(data, this.lengthCell-1, "");
    }

    private String buildRowHeader(){
        StringBuilder rowHeader = new StringBuilder();
        rowHeader.append(buildColumnSeparator());
        rowHeader.append(buildCell(""));
        rowHeader.append(buildColumnSeparator());

        for(int row=0; row < this.size; row++ ){
            rowHeader.append(buildCell("" + row));
            rowHeader.append(buildColumnSeparator());
        }
        rowHeader.append("\n");
        return rowHeader.toString();
    }

    private String buildRowData(String[] rowData){
        StringBuilder rowsData = new StringBuilder();

        for(String data: rowData ){
            rowsData.append(buildCell(data));
            rowsData.append(buildColumnSeparator());
        }

        rowsData.append("\n");
        return rowsData.toString();
    }

    private String[] convertShipToRowData(GridSquare[] row){
        ArrayList<String> rowData = new ArrayList<>();
        Arrays.stream(row).
                forEach(r-> rowData.add(r.getMark()));
        return rowData.toArray(new String[this.size]);
    }

    public void buildView(){
        StringBuilder view = new StringBuilder();
        view.append(buildRowSeparator());
        view.append(buildTitle());
        view.append(buildRowSeparator());
        view.append(buildRowHeader());
        view.append(buildRowSeparator());

        for(int rowIndex=0; rowIndex < this.size; rowIndex++){
            GridSquare[] row = this.data[rowIndex];
            String rowPosition = "" + ((char) (rowIndex + 65));
            String[] rowData = this.convertShipToRowData(row);
            view.append(buildColumnSeparator());
            view.append(buildCell(rowPosition));
            view.append(buildColumnSeparator());
            view.append(buildRowData(rowData));
            view.append(buildRowSeparator());
        }
        ManagerView.setBackgroundView(view.toString());
    }
    public void show(){
        this.buildView();
        ManagerView.showBackgroundView();
    }
}
