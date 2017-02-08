package com.hackingbuzz.musicproject1;

import android.net.Uri;

/**
 * Created by AviHacker on 7/2/2017.
 */
public class Song {
    private String mSongName, mSongAlbumName , mSongFullPath , mSongDuration ;
    private Uri mSongUri;
    private int mSongId;

    public Song(){ }
    public Song(String name , int id , String album_name , String full_path , String duration , Uri songuri ){
        this.mSongName = name;
        this.mSongId = id;
        this.mSongAlbumName = album_name;
        this.mSongFullPath = full_path;
        this.mSongDuration = duration;
        this.mSongUri = songuri;
    }
    public String getSongName() {
        return mSongName;
    }

    public void setSongName(String mSongName) {
        this.mSongName = mSongName;
    }

    public String getSongFullPath() {
        return mSongFullPath;
    }

    public void setSongFullPath(String mSongFullPath) {
        this.mSongFullPath = mSongFullPath;
    }

    public String getSongAlbumName() {
        return mSongAlbumName;
    }

    public void setSongAlbumName(String mSongAlbumName) {
        this.mSongAlbumName = mSongAlbumName;
    }

    public String getSongDuration() {
        return mSongDuration;
    }

    public void setSongDuration(String mSongDuration) {
        this.mSongDuration = mSongDuration;
    }

    public int getSongId() {
        return mSongId;
    }

    public void setSongId(int mSongId) {
        this.mSongId = mSongId;
    }

    public void setSongUri(Uri uri){ this.mSongUri = uri; }

    public Uri getSongUri(){
        return this.mSongUri;
    }
}
