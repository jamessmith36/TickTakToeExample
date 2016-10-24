package com.tae.james.ticktaktoeexample.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.tae.james.ticktaktoeexample.logic.BoardLogic;

/**
 * Created by jamessmith on 18/10/2016.
 */

public class DrawCanvas extends View{

    private static int[][] drawBoard = new int[][]{{190, 165, 190, 670},
                                                   {365, 165, 365, 670},
                                                   {65, 300, 500, 300},
                                                   {65, 490, 500, 490}};

    private static int[][] playerElements = new int[][]{{90, 200, 0},{280, 200, 0},{455, 200, 0},
                                                        {90, 390, 0},{270, 390, 0},{455, 390, 0},
                                                        {90, 580, 0},{270, 580, 0},{455, 580, 0}};


    private Paint paint = new Paint();
    private int player1 = Color.RED, player2 = Color.BLACK, turn;
    private static final String TAG = DrawCanvas.class.getName();

    public DrawCanvas(Context context) {
        super(context);
    }

    public DrawCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.v(TAG, "onMeasure has been invoked");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.v(TAG, "onDraw has been invoked");
        paint.setColor(Color.BLUE);

        //Draw board.
        for(int k = 0; k < drawBoard.length; k++) {
            canvas.drawLine(drawBoard[k][0], drawBoard[k][1], drawBoard[k][2], drawBoard[k][3], paint);
        }

        for(int i = 0; i < playerElements.length; i++) {
            //draw elements once segment has been touched. X for player 1, and O for player 2.
            if (playerElements[i][2] == 1) {
                Log.v(TAG, "draw cross");
                paint.setColor(player1);
                //Draw X.
                canvas.drawLine(playerElements[i][0], playerElements[i][1], playerElements[i][0] + 60, playerElements[i][1] + 60, paint);
                canvas.drawLine(playerElements[i][0] + 60, playerElements[i][1], playerElements[i][0], playerElements[i][1] + 60, paint);
            } else if(playerElements[i][2] == 2){
                Log.v(TAG, "draw circle");
                paint.setColor(player2);
                //Draw O.
                paint.setStyle(Paint.Style.STROKE);//Don't fill circle.
                canvas.drawCircle(playerElements[i][0], playerElements[i][1], 65, paint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        double touchLeft = event.getX();
        double touchRight = touchLeft + 70;
        double touchTop = event.getY();
        double touchBottom = touchTop + 70;
        double objectLeft;
        double objectRight;
        double objectTop;
        double objectBottom;

        if(BoardLogic.isWinner()){
            String player;

            if(turn == 0){
                player = "Player 1";
            }else{
                player = "Player 2";
            }

            Toast.makeText(getContext(),  player + " has won", Toast.LENGTH_LONG).show();
            return false;
        }

        for(int i = 0; i < playerElements.length; i++) {
            objectLeft = playerElements[i][0];
            objectRight = objectLeft + 70;
            objectTop = playerElements[i][1];
            objectBottom = objectTop + 70;

            if ((touchLeft <= objectRight) && (touchRight >= objectLeft)) {
                if ((touchTop <= objectBottom) && (touchBottom >= objectTop)) {
                    Log.v(TAG, "got touch");
                    if((turn == 0) && (playerElements[i][2] != 1)){
                        new BoardLogic(i);
                        playerElements[i][2] = 1;
                        turn = 1;
                        invalidate();
                        return true;
                    }else if((turn == 1) && (playerElements[i][2] != 2)){
                        new BoardLogic(i);
                        playerElements[i][2] = 2;
                        turn = 0;
                        invalidate();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void resetBoard(){

        for(int i = 0; i < playerElements.length; i++){
            playerElements[i][2] = 0;
        }

        new BoardLogic();
        invalidate();
    }
}