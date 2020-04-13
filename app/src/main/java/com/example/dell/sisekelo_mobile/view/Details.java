package com.example.dell.sisekelo_mobile.view;

/*SISEKELO DLAMINI S1719039*/

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.dell.sisekelo_mobile.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;

public class Details extends AppCompatActivity {

    TextView textView_title, textView1, textView2, textView5,textView3,textView4;

    String category, depth, description,link,country,latitude,longitude,magnitude,date,title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_details );
        Toolbar toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        Intent intent = getIntent();
        final String data = intent.getStringExtra("info");

        String[] deepest_properties = data.trim().split("\n");
        for(int i = 0 ; i < deepest_properties.length ; i++){

            Log.d( "deep", ""+deepest_properties[i]);

        }

        category = deepest_properties[0];
        depth = deepest_properties[1]+" KM";
        description = deepest_properties[2];
        link = deepest_properties[3];
        country = deepest_properties[4];
        latitude = deepest_properties[5];
        longitude = deepest_properties[6];
        magnitude = deepest_properties[7];
        date = deepest_properties[8];
        title = deepest_properties[9];

        //textView_title = findViewById( R.id.textView_title_details );
        textView1 = findViewById( R.id.textView1 );
        textView2 = findViewById( R.id.textView2 );
        textView3 = findViewById( R.id.textView3 );
        textView4 = findViewById( R.id.textView4 );
        textView5 = findViewById( R.id.textView5 );

        try {
            URL myURL = new URL(link);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        final String mapURL = "https://www.google.com/maps/search/?api=1&query="+latitude+","+longitude;

        try {
            URL myURL2 = new URL(mapURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //textView_title.setText(country);
        textView1.setText( "Magnitude : " +magnitude + " M");
        textView2.setText( "Depth : " +depth);
        textView3.setText( "Date : " +date);
        textView4.setText( "Click here for more info" );
        textView5.setText( "Click here for map" );

        textView4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData( Uri.parse(link));
                startActivity(intent);
            }
        });

        textView5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData( Uri.parse(mapURL));
                startActivity(intent);
            }
        });



        FloatingActionButton fab = findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(Details.this, MainActivity.class);
                Details.this.startActivity(myIntent);
            }
        } );
    }

}
