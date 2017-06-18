/*
 * Luis Santos
 * COP 4655
 * Project 2
 * Tic-Tac-Toe
 */

package com.cop4655.luis.project2;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //region game variables
    private Button gameBoard[][];
    private static final int BOARD_SIZE = 3;
    public static final String PLAYER_1 = "X";
    public static final String PLAYER_2 = "0";
    public static final String EMPTY = "";
    private boolean gameOver = false;
    private boolean boardFull = false;
    String currentPlayer;
    private int top = 0;
    private int center = 1;
    private int bot = 2;
    private int left = 0;
    private int mid = 1;
    private int right = 2;
    //endregion

    //region phone object variables
    private Button topLeft;
    private Button topMid;
    private Button topRight;
    private Button centerLeft;
    private Button centerMid;
    private Button centerRight;
    private Button botLeft;
    private Button botMid;
    private Button botRight;
    private TextView gameUpdateView;
    private Button newGameButton;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize game array
        gameBoard = new Button[BOARD_SIZE][BOARD_SIZE];

        //region populate array with the buttons
        gameBoard [top][left] = (Button) findViewById(R.id.topLeft);
        gameBoard [top][mid] = (Button) findViewById(R.id.topMid);
        gameBoard [top][right] = (Button) findViewById(R.id.topRight);

        gameBoard [center][left] = (Button) findViewById(R.id.centerLeft);
        gameBoard [center][mid] = (Button) findViewById(R.id.centerMid);
        gameBoard [center][right] = (Button) findViewById(R.id.centerRight);

        gameBoard [bot][left] = (Button) findViewById(R.id.botLeft);
        gameBoard [bot][mid] = (Button) findViewById(R.id.botMid);
        gameBoard [bot][right] = (Button) findViewById(R.id.botRight);
        //endregion

        gameUpdateView = (TextView) findViewById(R.id.gameUpdateView);
        newGameButton = (Button) findViewById(R.id.newGameButton);

        //region add event listeners
        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++){
                gameBoard[i][j].setText(EMPTY);
                gameBoard[i][j].setOnClickListener(new gameBoardListener(i, j));
            }// end for j
        }// end for i

        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGame();
            }
        });
        //endregion

        //region game start conditions
        currentPlayer = PLAYER_1;
        gameUpdateView.setText("Player " + PLAYER_1 + "'s Turn");
        //endregion
    }// end onCreate

    private void newGame(){
        //reset game board
        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++) {
                gameBoard[i][j].setText(EMPTY);
                gameBoard[i][j].setClickable(true);
            }
        }
        gameOver = false;

        //reset player
        setCurrentPlayer(PLAYER_1);
        gameUpdateView.setText("Player " + getCurrentPlayer() + "'s Turn");
    }// end newGame

    private void lockBoard(){
        //lock game board when it is over
        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++) {
                gameBoard[i][j].setClickable(false);
            }
        }
        gameOver = true;
    }// end lockBoard

    private void setCurrentPlayer(String cp){
        this.currentPlayer = cp;
    }//end setCurrentPlayer

    private String getCurrentPlayer(){
        return this.currentPlayer;
    }// end getCurrentPlayer

    private void recordMove(){
        checkIfGameIsOver();

        if(gameOver){
            gameUpdateView.setText(getCurrentPlayer() + " wins!");
        } else if(boardFull) {
            gameUpdateView.setText("Board full, tie game!!");
        } else {
            if (getCurrentPlayer() == PLAYER_1) {
                setCurrentPlayer(PLAYER_2);
            } else {
                setCurrentPlayer(PLAYER_1);
            }// end if

            gameUpdateView.setText("Player " + getCurrentPlayer() + "'s Turn");
        }// end if

    }// end recordMove

    private void checkIfGameIsOver(){
        //region check rows
        if(gameBoard[top][left].getText() == gameBoard[top][mid].getText()  && gameBoard[top][mid].getText()  == gameBoard[top][right].getText() ){
            if (gameBoard[top][left].getText() != EMPTY) {
                lockBoard();
            }
        }

        if(gameBoard[center][left].getText() == gameBoard[center][mid].getText()  && gameBoard[center][mid].getText()  == gameBoard[center][right].getText() ){
            if (gameBoard[center][left].getText() != EMPTY) {
                lockBoard();
            }
        }

        if(gameBoard[bot][left].getText() == gameBoard[bot][mid].getText()  && gameBoard[bot][mid].getText()  == gameBoard[bot][right].getText() ){
            if (gameBoard[bot][left].getText() != EMPTY) {
                lockBoard();
            }
        }
        //endregion
        
        //region check columns
        if(gameBoard[top][left].getText() == gameBoard[center][left].getText()  && gameBoard[center][left].getText()  == gameBoard[bot][left].getText() ){
            if (gameBoard[top][left].getText() != EMPTY) {
                lockBoard();
            }
        }

        if(gameBoard[top][mid].getText() == gameBoard[center][mid].getText()  && gameBoard[center][mid].getText()  == gameBoard[bot][mid].getText() ){
            if (gameBoard[top][mid].getText() != EMPTY) {
                lockBoard();
            }
        }

        if(gameBoard[top][right].getText() == gameBoard[center][right].getText()  && gameBoard[center][right].getText()  == gameBoard[bot][right].getText() ){
            if (gameBoard[top][right].getText() != EMPTY) {
                lockBoard();
            }
        }
        //endregion

        //region check diagnals
        if(gameBoard[top][left].getText() == gameBoard[center][mid].getText()  && gameBoard[center][mid].getText()  == gameBoard[bot][right].getText() ){
            if (gameBoard[top][left].getText() != EMPTY) {
                lockBoard();
            }
        }

        if(gameBoard[top][right].getText() == gameBoard[center][mid].getText()  && gameBoard[center][mid].getText()  == gameBoard[bot][left].getText() ){
            if (gameBoard[top][right].getText() != EMPTY) {
                lockBoard();
            }
        }
        //endregion

        //region check if board is full
        boardFull = true;
        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (gameBoard[i][j].getText() == EMPTY){
                    boardFull = false;
                    break;
                }
            }
        }
        //endregion
    }// end checkIfGameIsOver

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        //save game button text
        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++) {
                StringBuilder builder = new StringBuilder(8);
                builder.append("button").append(i).append(j);
                String thisButton = builder.toString();

                outState.putString(thisButton,gameBoard[i][j].getText().toString());
            }
        }

        //save Textview
        outState.putString("updateText", gameUpdateView.getText().toString());

        //save currentPlayer
        outState.putString("cp", getCurrentPlayer());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);

        //get game button text
        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++) {
                StringBuilder builder = new StringBuilder(8);
                builder.append("button").append(i).append(j);
                String thisButton = builder.toString();

                gameBoard[i][j].setText(savedInstanceState.getString(thisButton));
                if(gameBoard[i][j].getText().toString() != EMPTY){
                    gameBoard[i][j].setClickable(false);
                }
            }
        }

        //get Textview
        gameUpdateView.setText(savedInstanceState.getString("updateText"));

        //get currentPlayer
        setCurrentPlayer(savedInstanceState.getString("cp"));
    }

    private class gameBoardListener implements View.OnClickListener {
        int row;
        int col;

        public gameBoardListener(int row, int col){
            this.row = row;
            this.col = col;
        }// end constructor

        @Override
        public void onClick(View v){
            gameBoard[row][col].setText(getCurrentPlayer());
            gameBoard[row][col].setClickable(false);
            recordMove();
        }// end onClick

    }// end class gameBoardListener
}// end MainActivity
