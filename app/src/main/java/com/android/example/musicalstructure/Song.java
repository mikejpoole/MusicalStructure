package com.android.example.musicalstructure;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mike on 27/02/2018.
 */

public class Song implements Parcelable {
    private String mSongTitle;
    private String mArtistName;
    private String mAlbumArt;
    private String mAlbumArtBase64;

    // CONSTRUCTOR
    public Song(String songTitle, String artistName, String albumArt, String albumArtBase64) {
        mSongTitle = songTitle;
        mArtistName = artistName;
        mAlbumArt = albumArt;
        mAlbumArtBase64 = albumArtBase64;
    }


    // GETTER AND SETTER METHODS
    public String getmSongTitle() {
        return mSongTitle;
    }

    public String getmArtistName() {
        return mArtistName;
    }

    public String getmAlbumArt() {
        return mAlbumArt;
    }

    public String getmAlbumArtBase64() {
        return mAlbumArtBase64;
    }


    // PARCELLING
    public Song (Parcel in){
        String[] data = new String[4];

        in.readStringArray(data);
        mSongTitle = data[0];
        mArtistName = data[1];
        mAlbumArt = data[2];
        mAlbumArtBase64 = data[3];
    }


    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {mSongTitle, mArtistName, mAlbumArt, mAlbumArtBase64});
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        public Song[] newArray(int size) {
            return new Song[size];
        }
    };



}
