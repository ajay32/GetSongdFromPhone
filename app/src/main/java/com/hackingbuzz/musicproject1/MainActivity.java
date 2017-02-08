package com.hackingbuzz.musicproject1;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button getSongs;
    private ListView songList;
    private ArrayList<Song> songsArray;
 //   private String[] STAR = {"*"};




    private Button mBtnImport;
    private ListView mListSongs;
    private LinearLayout mLinearListImportedFiles;
    private RelativeLayout mRelativeBtnImport;
    private SongListAdapter mAdapterListFile;
    private String[] STAR = {"*"};
    private ArrayList<Song> mSongList;
   // private MusicService serviceMusic;
    private Intent playIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      //  getSongs = (Button) findViewById(R.id.button);

      //  songList = (ListView) findViewById(R.id.songList);

//        getSongs.setOnClickListener(this);

        songsArray = new ArrayList<Song>();


        mBtnImport = (Button) findViewById(R.id.button);
      //  mLinearListImportedFiles = (LinearLayout) findViewById(R.id.linear_list_imported_files);
        //mRelativeBtnImport = (RelativeLayout) findViewById(R.id.relative_btn_import);
        mListSongs = (ListView) findViewById(R.id.songList);

        mBtnImport.setOnClickListener(this);
      //  mListSongs.setOnItemClickListener(this);

        mSongList = new ArrayList<Song>();
        mAdapterListFile = new SongListAdapter(this, mSongList);
        mListSongs.setAdapter(mAdapterListFile);


    }


    @Override
    public void onClick(View v) {
      /*  songsArray= listAllSongs();

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, songsArray);

        songList.setAdapter(arrayAdapter);*/

        mSongList = listAllSongs();
        mAdapterListFile.setSongsList(mSongList);
     //   mLinearListImportedFiles.setVisibility(View.VISIBLE);
     //   mRelativeBtnImport.setVisibility(View.GONE);
      //  serviceMusic.setSongList(mSongList);

    }



    private ArrayList<Song> listAllSongs() { //Fetch path to all the files from internal & external storage n store it in songList
        Cursor cursor;
        ArrayList<Song> songList = new ArrayList<Song>();
        Uri allSongsUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        if (isSdPresent()) {
         //   cursor = managedQuery(allSongsUri, STAR, selection, null, null);
             cursor = getContentResolver().query(allSongsUri, STAR, selection, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        Song song = new Song();

                        String data = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                       String[] res = data.split("\\.");
                        song.setSongName(res[0]);
                        Log.d("test",res[0] );
                        song.setSongFullPath(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
                        song.setSongId(cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID)));
                       // song.setSongFullPath(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
                        song.setSongAlbumName(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM)));
                        song.setSongUri(ContentUris.withAppendedId(
                                android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                                cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID))));
                    //    String duration = getDuration(Integer.parseInt(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION))));
                    //    song.setSongDuration(duration);

                        songList.add(song);
                    } while (cursor.moveToNext());
                    return songList;
                }
                cursor.close();
            }
        }
        return null;
    }

    //Check whether sdcard is present or not
    private static boolean isSdPresent() {
        return android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
    }
}
