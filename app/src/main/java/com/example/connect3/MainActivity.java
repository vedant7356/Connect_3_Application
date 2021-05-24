package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int activePlayer=0;

    boolean gameisActive=true;

    int [] gamestate= {2,2,2,2,2,2,2,2,2};
    int [] [] winningPositions ={{0,1,2},{3,4,5},{6,7,8}, {0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropIn(View view){
        ImageView counter= (ImageView) view;
        System.out.println(counter.getTag().toString());
        int tappedCounter= Integer.parseInt((counter.getTag().toString()));

        if(gamestate[tappedCounter]==2 && gameisActive) {
            gamestate[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);


            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;

            }

            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            for (int [] winningPosition : winningPositions){
                if(gamestate[winningPosition[0]]==gamestate[winningPosition[1]]&&
                        gamestate[winningPosition[1]]==gamestate[winningPosition[2]]&&
                        gamestate[winningPosition[0]] !=2){

                    gameisActive=false;

                    String winner="Red";

                    if(gamestate[winningPosition[0]]==0){
                        winner="Yellow";
                    }

                    Toast.makeText(getApplicationContext(),"You have Won",Toast.LENGTH_SHORT).show();

                    TextView winnermessage=(TextView)findViewById(R.id.winnerMessage);
                    winnermessage.setText(winner+" Wins!!");

                    LinearLayout linearLayout=(LinearLayout)findViewById(R.id.playAgainLayout);
                    linearLayout.setVisibility(View.VISIBLE);

                }else{
                    boolean gameIsOver=true;
                    for(int counterState : gamestate){
                        if (counterState==2) gameIsOver=false;
                    }
                    if(gameIsOver) {
                        TextView winnermessage=(TextView)findViewById(R.id.winnerMessage);
                        winnermessage.setText(" It's a DRAW");

                        LinearLayout linearLayout=(LinearLayout)findViewById(R.id.playAgainLayout);
                        linearLayout.setVisibility(View.VISIBLE);
                    }
                }

            }
        }
    }

    public void playAgain(View view){

        gameisActive=true;
        LinearLayout layout=(LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
         activePlayer=0;
         for(int i=0; i<gamestate.length; i++){
             gamestate[i]=2;
         }

        GridLayout gridLayout=(GridLayout)findViewById(R.id.gridLayout);
         for(int i =0; i<gridLayout.getChildCount(); i++){
             ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
         }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}