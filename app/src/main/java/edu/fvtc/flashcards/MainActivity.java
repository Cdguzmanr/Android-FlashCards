package edu.fvtc.flashcards;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    public static final String TAG = "MainActivity";

    State[] states = {
            new State("Wisconsin", "Milwaukee"),
            new State("Minesota", "St. Paul"),
            new State("Ohio", "Columbus")
    };

    int[] imgs = {R.drawable.wisconsin,
                  R.drawable.minnesota,
                  R.drawable.ohio
    };

    int cardNo = 0;
    boolean isFront = true;
    ImageView imgCard;
    TextView tvCard;

    GestureDetector gestureDetector;

    // One of those things I have to remember!!!!
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){
        return gestureDetector.onTouchEvent(motionEvent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgCard = findViewById(R.id.imageView);
        tvCard = findViewById(R.id.textView);

        imgCard.setVisibility(View.VISIBLE);
        imgCard.setImageResource(imgs[cardNo]);
        tvCard.setText(states[cardNo].getName());

        gestureDetector = new GestureDetector(this,this);
        Log.d(TAG, "onCreate: Complete");
    }

    @Override
    public boolean onDown(@NonNull MotionEvent e) {
        Log.d(TAG, "onDown: ");
        return false;
    }

    @Override
    public void onShowPress(@NonNull MotionEvent e) {
        Log.d(TAG, "onShowPress: ");

    }

    @Override
    public boolean onSingleTapUp(@NonNull MotionEvent e) {
        Log.d(TAG, "onSingleTapUp: ");

        String message;

        try {
            if (isFront) {
                // go to the back
                message = "Got to back";
                imgCard.setVisibility(View.INVISIBLE);
                tvCard.setText(states[cardNo].getCapital());
            }else {
                // go to front
                message = "Got to front";
                imgCard.setVisibility(View.VISIBLE);
                imgCard.setImageResource(imgs[cardNo]);
                tvCard.setText(states[cardNo].getName());
            }

            isFront = !isFront; // better that creating "if (true) change to false"
            Log.d(TAG, "onSingleTapUp: " + message);

        } catch (Exception ex){
            Log.e(TAG, "onSingleTapUp: " + ex.getMessage() );
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean onScroll(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float distanceX, float distanceY) {
        Log.d(TAG, "onScroll: ");
        return false;
    }

    @Override
    public void onLongPress(@NonNull MotionEvent e) {
        Log.d(TAG, "onLongPress: ");

    }

    @Override
    public boolean onFling(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
        Log.d(TAG, "onFling: ");
        return false;
    }
}