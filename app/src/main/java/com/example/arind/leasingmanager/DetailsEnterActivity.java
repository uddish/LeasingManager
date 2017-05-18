package com.example.arind.leasingmanager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DetailsEnterActivity extends AppCompatActivity {

    private AutoCompleteTextView actv;
    EditText name;
    Button submit;

    public static final String TAG = "DetailsEnterActivity";

    public static String NAME;
    public static String ADDRESS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_enter);

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String sharedprefName = sharedPref.getString("name", "no_name");
        String sharedprefAddress = sharedPref.getString("address", "no_address");

        if(!sharedprefName.equals("no_name") && !sharedprefAddress.equals("no_address"))   {
            NAME = sharedprefName;
            ADDRESS = sharedprefAddress;
            startActivity(new Intent(getApplicationContext(), Home.class));
            finish();
        }


        actv = (AutoCompleteTextView) findViewById(R.id.address);
        name = (EditText) findViewById(R.id.name);
        submit = (Button) findViewById(R.id.submit_btn);

        String[] countries = getResources().getStringArray(R.array.address);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1,countries);
        actv.setAdapter(adapter);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NAME = name.getText().toString();
                ADDRESS = actv.getText().toString();
                Log.d(TAG, "onClick: " + NAME + "  " + ADDRESS);
                if(!NAME.equals("") && !ADDRESS.equals("")) {

                    SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("name", NAME);
                    editor.putString("address", ADDRESS);
//                    editor.putInt(getString(R.string.saved_high_score), newHighScore);
                    editor.commit();

                    startActivity(new Intent(getApplicationContext(), Home.class));
                }
                else    {
                    Toast.makeText(DetailsEnterActivity.this, "Please fill the details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
