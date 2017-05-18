package com.example.arind.leasingmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONObject;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {

    private static final String TAG = "MessageActivity";
    EditText message;
    Button send;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseUser user;
    int disId;
    ArrayList<POJO> messageList;
    AnnouncementAdapter mAdapter;
    Object jsonObject;

    POJO model;
    AVLoadingIndicatorView av;

    RecyclerView recyclerView;

    POJO details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        Intent i = getIntent();
        final String discussionId = i.getStringExtra("discussion_id");

        av = (AVLoadingIndicatorView) findViewById(R.id.avi);
        av.show();

        messageList = new ArrayList<>();

        if (discussionId.equals("one")) {
            disId = 101;
        } else if (discussionId.equals("two")) {
            disId = 102;
        } else if (discussionId.equals("three")) {
            disId = 103;
        }

        message = (EditText) findViewById(R.id.message);
        send = (Button) findViewById(R.id.send_btn);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

            mAdapter = new AnnouncementAdapter(messageList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MessageActivity.this);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(mAdapter);

        details = new POJO();

        //Retrieving data
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                av.hide();
                if (dataSnapshot != null && dataSnapshot.getValue() != null) {
                    model = new POJO();
                    model = (POJO) dataSnapshot.getValue(POJO.class);
                    Log.d(TAG, "onChildAdded:  " + dataSnapshot.child("address_id").getValue() + " " + Home.ADDRESS_ID);
                    if(dataSnapshot.child("discussion_id").getValue().equals(String.valueOf(disId))
                            && dataSnapshot.child("address_id").getValue().equals(String.valueOf(Home.ADDRESS_ID))) {
                        messageList.add(model);

                    }
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long timeStamp = System.currentTimeMillis() / 1000;
                final String key = timeStamp.toString();
                POJO obj;
                if(Login.UID!= null) {
                    obj = new POJO(Login.UID, String.valueOf(Home.ADDRESS_ID), String.valueOf(disId), message.getText().toString(), DetailsEnterActivity.NAME);
                    reference.child(key).setValue(obj);
                }
                else    {
                    Toast.makeText(MessageActivity.this, "Connection error...", Toast.LENGTH_SHORT).show();
                }
                message.setText("");
            }
        });

    }
}
