package com.android.example.musicalstructure;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SongDetail extends AppCompatActivity {

    private MediaPlayer myPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_detail);

        // DETECT WHICH SONG WAS CLICKED
//        SongAdapter song = (SongAdapter) parent.getItemAtPosition(position);

        TextView sSongTitle = (TextView) findViewById(R.id.details_song_title);
        sSongTitle.setText("Information about the song goes here...");

        Toolbar myToolbar = findViewById(R.id.my_toolbar);

        // If using ActionBarActivity
//        setSupportActionBar(myToolbar);       // MAKES THE APP BAR TEXT BLACK
//        myToolbar.setTitle("Song Title Goes here...");


        // If not using ActionBarActivity

        // BACK BUTTON
        // see https://stackoverflow.com/questions/26651602/display-back-arrow-on-toolbar-android
        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp));

        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG","Back pressed...");
                Intent songIntent = new Intent(SongDetail.this, MainActivity.class);
                startActivity(songIntent);
            }
        });


        // UNBUNDLE PARCEL
        Bundle data = getIntent().getExtras();
        Song song = (Song) data.getParcelable("song");

        Toast.makeText(this, "The song is " + song.getmSongTitle(), Toast.LENGTH_SHORT).show();



        // SET THE TITLE IN HTML SO CAN SET THE COLOR EASILY
        String sTitle = "Title goes here...";
        sTitle = song.getmSongTitle();
//        sTitle = getIntent().getExtras().getParcelable("song_title");
        myToolbar.setTitle(Html.fromHtml("<font color='#ffffff'>" + sTitle + "</font>"));
        myToolbar.inflateMenu(R.menu.menu);


        // MEDIA PLAYER
        myPlayer = MediaPlayer.create(this, R.raw.bleat_soundbible_893851569);

        Button playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public final void onClick(View view){
                myPlayer.start();
            }
        });

        Button pauseButton = findViewById(R.id.pauseButton);
        pauseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                myPlayer.pause();
            }
        });



        // ALERT ME WHEN SONG IS FINISHED
        myPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Toast.makeText(SongDetail.this, "I'm done!", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
