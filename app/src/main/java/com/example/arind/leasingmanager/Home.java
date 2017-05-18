package com.example.arind.leasingmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;

public class Home extends AppCompatActivity {

    private static final String TAG = "Home";
    CardView card1, card2, card3;
    public static int discussion_id;
    public static int ADDRESS_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        card1 = (CardView) findViewById(R.id.card1);
        card2 = (CardView) findViewById(R.id.card2);
        card3 = (CardView) findViewById(R.id.card3);

        if(DetailsEnterActivity.ADDRESS.equals("449 Palo Verde Road, Gainesville, FL")) {
            ADDRESS_ID = 449;
        }
        else if(DetailsEnterActivity.ADDRESS.equals("6731 Thompson Street, Gainesville, FL")) {
            ADDRESS_ID = 6731;
        }
        else if(DetailsEnterActivity.ADDRESS.equals("8771 Thomas Boulevard, Orlando, FL")) {
            ADDRESS_ID = 8771;
        }
        else if(DetailsEnterActivity.ADDRESS.equals("1234 Verano Place, Orlando, FL")) {
            ADDRESS_ID = 1234;
        }

        Log.d(TAG, "*******UID******* " + Login.UID);

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discussion_id = 100;
                Intent i = new Intent(getApplicationContext(), MessageActivity.class);
                i.putExtra("discussion_id", "one");
                startActivity(i);
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discussion_id = 101;
                Intent i = new Intent(getApplicationContext(), MessageActivity.class);
                i.putExtra("discussion_id", "two");
                startActivity(i);
            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                discussion_id = 102;
                Intent i = new Intent(getApplicationContext(), MessageActivity.class);
                i.putExtra("discussion_id", "three");
                startActivity(i);
            }
        });
    }
}
