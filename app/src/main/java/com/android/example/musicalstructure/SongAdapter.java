package com.android.example.musicalstructure;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.android.example.musicalstructure.MainActivity.PACKAGE_NAME;

/**
 * Created by mike on 27/02/2018.
 */

public class SongAdapter extends ArrayAdapter<Song> {
    public SongAdapter(Activity context, ArrayList<Song> Song) {
        super(context, 0, Song);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        // CHECK IF THE VIEW IS BING REUSED AND IF NOT THEN INFLATE A NEW ONE
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // GET THE SONG AT THIS POSITION IN THE ARRAY
        Log.i("SongAdapter","You are at position " + position);
        Song currentSong = getItem(position);


        // SET SONGTITLE IN THE TEXTVIEW IN LIST_ITEM.XML BY FETCHING IT FROM THE CUSTOM ARRAY ADAPTER
        if (!currentSong.getmSongTitle().isEmpty()) {
            TextView songTitleTextView = (TextView) listItemView.findViewById(R.id.song_title);
            songTitleTextView.setText(currentSong.getmSongTitle());
        }

        // SET ARTISTNAME
        TextView artistNameTextView = (TextView) listItemView.findViewById(R.id.artist_name);
        artistNameTextView.setText(currentSong.getmArtistName());

        // SET ALBUM ART IMAGE VIEW ON LIST
        ImageView albumArtImageView = (ImageView) listItemView.findViewById(R.id.album_art);

        // ...WITH A DRAWABLES JPEG
        if (!currentSong.getmAlbumArt().isEmpty()) {
            String albumArt = PACKAGE_NAME + ":drawable/" + currentSong.getmAlbumArt();
            int id = getContext().getResources().getIdentifier(albumArt, null, null);
            albumArtImageView.setImageResource(id);
        }

        // ... OR WITH BASE64 IMAGES SO THEY CAN COME FROM THE ARRAY INSTEAD OF NEEDING TO BE IN DRAWABLES
        if (!currentSong.getmAlbumArtBase64().isEmpty()) {
            byte[] imageAsBytes = Base64.decode(currentSong.getmAlbumArtBase64().getBytes(), 0);
            albumArtImageView.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
        }

        return listItemView;
    }
}