package com.example.pairs;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    ArrayList<ImageView> cardViews = new ArrayList<>();
    ArrayList<Card> cardList = new ArrayList<>();

    int[] pictureIds = {R.drawable.lisa_img, R.drawable.jisoo_img, R.drawable.jennie_img, R.drawable.bp_ot4_1, R.drawable.rose_img, R.drawable.bp_ot4_2};

    TextView matchCounterText;
    Button btnRestart;
    Card firstCard = null;
    ImageView firstCardView = null;
    int totalMatches = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matchCounterText = findViewById(R.id.textview_foundmatches);
        btnRestart = findViewById(R.id.reset_btn);

        for (int i = 1; i <= 12; i++) {
            int id = getResources().getIdentifier("image" + i, "id", getPackageName());
            ImageView img = findViewById(id);
            cardViews.add(img);
        }
        startNewGame();
    }

    @Override
    protected void onStart() {
        super.onStart();

        btnRestart.setOnClickListener(v -> {
            startNewGame();
            handleCardClicks();
        });
        handleCardClicks();
    }

    private void startNewGame() {
        totalMatches = 0;
        firstCard = null;
        firstCardView = null;
        cardList.clear();

        for (int pic : pictureIds) {
            cardList.add(new Card(pic));
            cardList.add(new Card(pic));
        }

        Collections.shuffle(cardList);

        for (int i = 0; i < cardViews.size(); i++) {
            ImageView imgView = cardViews.get(i);
            imgView.setImageResource(R.drawable.bp_logo);
            imgView.setEnabled(true);

            Card card = cardList.get(i);
            card.isMatched = false;
            card.isFlipped = false;
        }
        updateMatch();
    }

    private void handleCardClicks() {
        for (int i = 0; i < cardViews.size(); i++) {
            ImageView currentCardView = cardViews.get(i);
            Card currentCard = cardList.get(i);

            currentCardView.setOnClickListener(v -> {
                if (currentCard.isMatched || currentCard.isFlipped) return;

                currentCardView.setImageResource(currentCard.imageResId);
                currentCard.isFlipped = true;

                if (firstCard == null) {
                    firstCard = currentCard;
                    firstCardView = currentCardView;
                } else {
                    if (firstCard.imageResId == currentCard.imageResId) {
                        firstCard.isMatched = true;
                        currentCard.isMatched = true;
                        totalMatches++;
                        updateMatch();

                        firstCard = null;
                        firstCardView = null;
                    } else {
                        new Handler().postDelayed(() -> {
                            currentCardView.setImageResource(R.drawable.bp_logo);
                            firstCardView.setImageResource(R.drawable.bp_logo);
                            currentCard.isFlipped = false;
                            firstCard.isFlipped = false;

                            firstCard = null;
                            firstCardView = null;
                        }, 800);
                    }
                }
            });
        }
    }

    private void updateMatch() {
        if (totalMatches == pictureIds.length) {
            matchCounterText.setText("You did it! All pairs matched!");
        } else {
            matchCounterText.setText("Match Count: " + totalMatches);
        }
    }
}