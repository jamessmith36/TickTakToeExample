package com.tae.james.ticktaktoeexample.logic;

import android.util.Log;

/**
 * Created by jamessmith on 19/10/2016.
 */

public class BoardLogic {

    private static boolean boardMatrix[][] = new boolean[][]{{false, false, false},
                                                             {false, false, false},
                                                             {false, false, false}};

    private static final String TAG = BoardLogic.class.getName();
    private static boolean winner;
    public BoardLogic(){
        reset();
    }

    //Update game status.
    public BoardLogic(int moveTaken){

        switch (moveTaken){
            case 0:
                boardMatrix[0][0] = true;
                break;
            case 1:
                boardMatrix[0][1] = true;
                break;
            case 2:
                boardMatrix[0][2] = true;
                break;
            case 3:
                boardMatrix[1][0] = true;
                break;
            case 4:
                boardMatrix[1][1] = true;
                break;
            case 5:
                boardMatrix[1][2] = true;
                break;
            case 6:
                boardMatrix[2][0] = true;
                break;
            case 7:
                boardMatrix[2][1] = true;
                break;
            case 8:
                boardMatrix[2][2] = true;
                break;
        }
        verifyGameStatus();
    }

    private void verifyGameStatus(){

        for(int i = 0; i < boardMatrix.length; i++){
            if((boardMatrix[i][0] == true) && (boardMatrix[i][1] == true) && (boardMatrix[i][2])){
                Log.v(TAG, "won on H");
                winner = true;
                break;
            }else{
                winner = false;
                break;
            }
        }

        for(int k = 0; k < boardMatrix.length; k++){

            if((boardMatrix[0][k] == true) && (boardMatrix[1][k] == true) && (boardMatrix[2][k])){
                Log.v(TAG, "won on V");
                winner = true;
                break;
            }else{
                winner = false;
                break;
            }
        }

        if((boardMatrix[0][0] == true) && (boardMatrix[1][1] == true) && (boardMatrix[2][2] == true)){
            Log.v(TAG, "won on D1");
            winner = true;
        }

        else if((boardMatrix[0][2] == true) && (boardMatrix[1][1] == true) && (boardMatrix[2][0] == true)){
            Log.v(TAG, "won on d2");
            winner = true;
        }else{
            winner = false;
        }
    }

    public void reset(){

        for(int i = 0; i < boardMatrix.length; i++){
            for(int k = 0; k < 4; k++){
                boardMatrix[i][k] = false;
            }
        }
    }

    public static boolean isWinner(){
        return winner;
    }
}
