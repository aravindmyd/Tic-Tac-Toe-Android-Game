package com.example.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    // Global variables

    int activePlayer = 0; // 1 for red and 0 for yellow
    int[] gameState = {2,2,2,2,2,2,2,2,2}; //Later we can update the states of the player. Intially 2 means unplayed.


    public void animation(View view){

        ImageView counter = (ImageView)view;
        //counter.setTranslationY(-2000f);

        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        System.out.println(tappedCounter+" is clicked!");


        if(gameState[tappedCounter] == 2) {

            // Checking if the button is already clicked and prevents clicking more than one time.

            //Update the state of the game

            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-2000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {

                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(2000f).rotation(360).setDuration(360);
        }

    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
