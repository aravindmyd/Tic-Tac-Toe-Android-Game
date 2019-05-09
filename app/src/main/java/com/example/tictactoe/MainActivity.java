package com.example.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Global variables

    int activePlayer = 0; // 1 for red and 0 for yellow
    int[] gameState = {2,2,2,2,2,2,2,2,2}; //Later we can update the states of the player. Intially 2 means unplayed.
    int [][] winningCombinations ={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{2,4,6},{0,4,8}}; // Total winnings position
    boolean isPlayable = true;


    public void animation(View view){

        ImageView counter = (ImageView)view;
        //counter.setTranslationY(-2000f);

        int tappedCounter = Integer.parseInt(counter.getTag().toString());


        if(gameState[tappedCounter] == 2 &&  isPlayable) {

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

            // checking winning positions

            for(int[] winningPosition: winningCombinations){

                if(gameState[winningPosition[0]]== gameState[winningPosition[1]]&&
                gameState[winningPosition[1]] == gameState[winningPosition[2]]&&
                gameState[winningPosition[0]]!=2
                ){
                    isPlayable=false;

                    String winnerMsg = "Red has won! ";

                    if(gameState[winningPosition[0]] ==0) {
                         winnerMsg = "Yellow has won! ";

                    }

                    TextView winnerTextView = findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winnerMsg);
                    // Making the winner Layout visible

                    LinearLayout playAgainLayout = findViewById(R.id.PlayAgainLayout);
                    playAgainLayout.setVisibility(View.VISIBLE);

                }
                else {
                    boolean isGameOver = true;

                    for(int gameIndex:gameState){

                        if(gameIndex == 2) {
                            isGameOver = false;
                            break;
                        }
                    }

                    if(isGameOver){

                        TextView winnerTextView = findViewById(R.id.winnerTextView);
                        winnerTextView.setText("Its a Draw. Play one More!");

                        LinearLayout playAgainLayout = findViewById(R.id.PlayAgainLayout);
                        playAgainLayout.setVisibility(View.VISIBLE);
                    }
            }

        }


        }

    }

    public  void playAgain(View view){
        isPlayable = true;

        // Resetting Game State
        for(int i =0;i<gameState.length;i++){
            gameState[i] = 2;

        }
        // Hide the winner Layout
        LinearLayout playAgainLayout = findViewById(R.id.PlayAgainLayout);
        playAgainLayout.setVisibility(View.INVISIBLE);

        //Resetting the red and yellow coins in the grid Layout
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for(int i =0; i<gridLayout.getChildCount();i++){

            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
