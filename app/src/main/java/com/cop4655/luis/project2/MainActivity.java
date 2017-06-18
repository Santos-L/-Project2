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

    //game variables
    private Button gameBoard[][];
    private static final int BOARD_SIZE = 3;
    public static final String PLAYER_1 = "X";
    public static final String PLAYER_2 = "0";
    public static final String EMPTY = "";
    private boolean gameOver = false;
    private boolean playerOneTurn = true;
    private int top = 0;
    private int center = 1;
    private int bot = 2;
    private int left = 0;
    private int mid = 1;
    private int right = 2;

    //phone object variables
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
                gameBoard[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Button btn = (Button)v;
                        if(btn.getText().equals(EMPTY) ){
                            btn.setText(PLAYER_2);
                            btn.setClickable(false);
                        }
                    }
                });
            }// end for j
        }// end for i

        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGame();
            }
        });
        //endregion
        
    }// end onCreate

    private void newGame(){
        for (int i = 0; i < BOARD_SIZE; i++){
            for (int j = 0; j < BOARD_SIZE; j++) {
                gameBoard[i][j].setText(EMPTY);
                gameBoard[i][j].setClickable(true);
                gameOver = false;
            }
        }
    }// end newGame

}// end MainActivity
