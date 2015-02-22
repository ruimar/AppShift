package com.example.rui.appshift;


import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    SoundSyncAPI conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conn= new SoundSyncAPI(getApplicationContext());

        //new signUpTask().execute();
        //new signInTask().execute();
        //new changePasswordTask().execute();
        //new getUserInfoTask().execute();
        new createPlaylistTask().execute();
        new searchPlaylistTask().execute();
        //new getPlaylistsTask().execute();
        //new addMusicTask().execute();
        //new getMusicTask().execute();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class signUpTask extends AsyncTask<Void, Integer, Long> {
        protected Long doInBackground(Void... params) {

            try {
                conn.signUp("teste", "99999", "123");
            } catch (IOException e) {
                e.printStackTrace();
            }

            return 0l;
        }

        protected void onProgressUpdate(Integer... progress) {
            //setProgressPercent(progress[0]);
        }

        protected void onPostExecute(Long result) {
            //showDialog("Downloaded " + result + " bytes");
        }
    }

    private class signInTask extends AsyncTask<Void, Integer, Long> {

        protected Long doInBackground(Void... params) {
            try {
                conn.signIn("teste", "123");
            } catch (IOException e) {
                e.printStackTrace();
            }

            return 0l;
        }

        protected void onProgressUpdate(Integer... progress) {
            //setProgressPercent(progress[0]);
        }

        protected void onPostExecute(Long result) {
            //showDialog("Downloaded " + result + " bytes");
        }
    }

    private class changePasswordTask extends AsyncTask<Void, Integer, Long> {

        protected Long doInBackground(Void... params) {
            try {
                conn.changePassword("qwerty", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXUyJ9.eyJzdWIiOjEsImlzcyI6Imh0dHA6XC9cL3NvdW5kc3luYy5ldTEuZnJiaXQubmV0XC9sb2dpbiIsImlhdCI6IjE0MjQ1ODk3NTEiLCJleHAiOiIxNDI0Njc2MTUxIiwibmJmIjoiMTQyNDU4OTc1MSIsImp0aSI6ImZhZTc4N2RhNGY2Yjg0YzdkMjgwODcwZmQ2YWVhOWE4In0.MGI5OWVmNzM0YjRkMTkwZmFlNjA5YjdkM2IzMTYwNTQ1ZDVmZDY3MGI1NTk3YjZkNTE3MTZhYjg0ODNjMDI2Yw");
            } catch (IOException e) {
                e.printStackTrace();
            }

            return 0l;
        }

        protected void onProgressUpdate(Integer... progress) {
            //setProgressPercent(progress[0]);
        }

        protected void onPostExecute(Long result) {
            //showDialog("Downloaded " + result + " bytes");
        }
    }

    private class getUserInfoTask extends AsyncTask<Void, Integer, Long> {

        protected Long doInBackground(Void... params) {
            conn.getUserInfo("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXUyJ9.eyJzdWIiOjEsImlzcyI6Imh0dHA6XC9cL3NvdW5kc3luYy5ldTEuZnJiaXQubmV0XC9sb2dpbiIsImlhdCI6IjE0MjQ1ODk3NTEiLCJleHAiOiIxNDI0Njc2MTUxIiwibmJmIjoiMTQyNDU4OTc1MSIsImp0aSI6ImZhZTc4N2RhNGY2Yjg0YzdkMjgwODcwZmQ2YWVhOWE4In0.MGI5OWVmNzM0YjRkMTkwZmFlNjA5YjdkM2IzMTYwNTQ1ZDVmZDY3MGI1NTk3YjZkNTE3MTZhYjg0ODNjMDI2Yw");
            return 0l;
        }

        protected void onProgressUpdate(Integer... progress) {
            //setProgressPercent(progress[0]);
        }

        protected void onPostExecute(Long result) {
            //showDialog("Downloaded " + result + " bytes");
        }
    }

    private class createPlaylistTask extends AsyncTask<Void, Integer, Long> {

        protected Long doInBackground(Void... params) {
            conn.createPlaylist("leguidao","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXUyJ9.eyJzdWIiOjEsImlzcyI6Imh0dHA6XC9cL3NvdW5kc3luYy5ldTEuZnJiaXQubmV0XC9sb2dpbiIsImlhdCI6IjE0MjQ1ODk3NTEiLCJleHAiOiIxNDI0Njc2MTUxIiwibmJmIjoiMTQyNDU4OTc1MSIsImp0aSI6ImZhZTc4N2RhNGY2Yjg0YzdkMjgwODcwZmQ2YWVhOWE4In0.MGI5OWVmNzM0YjRkMTkwZmFlNjA5YjdkM2IzMTYwNTQ1ZDVmZDY3MGI1NTk3YjZkNTE3MTZhYjg0ODNjMDI2Yw");
            return 0l;
        }

        protected void onProgressUpdate(Integer... progress) {
            //setProgressPercent(progress[0]);
        }

        protected void onPostExecute(Long result) {
            //showDialog("Downloaded " + result + " bytes");
        }
    }

    private class searchPlaylistTask extends AsyncTask<Void, Integer, Long> {

        protected Long doInBackground(Void... params) {
            conn.searchPlaylist(3,"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXUyJ9.eyJzdWIiOjEsImlzcyI6Imh0dHA6XC9cL3NvdW5kc3luYy5ldTEuZnJiaXQubmV0XC9sb2dpbiIsImlhdCI6IjE0MjQ1NTAwMjQiLCJleHAiOiIxNDI0NjM2NDI0IiwibmJmIjoiMTQyNDU1MDAyNCIsImp0aSI6IjA1MTU4MTk2OTU4NDYzNTJhOWY2ODlmNjNhNDk5NGMwIn0.ZGJmMDU5MmViYWI2MjFiNjhiOTI2Yzk3MDFhMDk5ZTQ4NTFjNTIyZmYzMTdjMThmZDUyNmI3MTFlMmYxNzJhZg");
            return 0l;
        }

        protected void onProgressUpdate(Integer... progress) {
            //setProgressPercent(progress[0]);
        }

        protected void onPostExecute(Long result) {
            //showDialog("Downloaded " + result + " bytes");
        }
    }

    private class getPlaylistsTask extends AsyncTask<Void, Integer, Long> {

        protected Long doInBackground(Void... params) {
            conn.getPlaylists(SoundSyncAPI.DEFAULT_LIMIT,"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXUyJ9.eyJzdWIiOjEsImlzcyI6Imh0dHA6XC9cL3NvdW5kc3luYy5ldTEuZnJiaXQubmV0XC9sb2dpbiIsImlhdCI6IjE0MjQ1NTAwMjQiLCJleHAiOiIxNDI0NjM2NDI0IiwibmJmIjoiMTQyNDU1MDAyNCIsImp0aSI6IjA1MTU4MTk2OTU4NDYzNTJhOWY2ODlmNjNhNDk5NGMwIn0.ZGJmMDU5MmViYWI2MjFiNjhiOTI2Yzk3MDFhMDk5ZTQ4NTFjNTIyZmYzMTdjMThmZDUyNmI3MTFlMmYxNzJhZg");
            return 0l;
        }

        protected void onProgressUpdate(Integer... progress) {
            //setProgressPercent(progress[0]);
        }

        protected void onPostExecute(Long result) {
            //showDialog("Downloaded " + result + " bytes");
        }
    }

    private class addMusicTask extends AsyncTask<Void, Integer, Long> {

        protected Long doInBackground(Void... params) {
            conn.addMusic("hgsdfgu","3092170491",2,"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXUyJ9.eyJzdWIiOjEsImlzcyI6Imh0dHA6XC9cL3NvdW5kc3luYy5ldTEuZnJiaXQubmV0XC9sb2dpbiIsImlhdCI6IjE0MjQ1NzcyMjEiLCJleHAiOiIxNDI0NjYzNjIxIiwibmJmIjoiMTQyNDU3NzIyMSIsImp0aSI6IjM1ZDliMTc5NmNkZThiOGVhMzFhNTdiZDAwMGY0OTIzIn0.MzVkYTMyNjI5MjI3ZDUyNzY5MTQzNTMwYTA2ZWQ4YTQ3MzhiZjMxYWQ2YmEzMDYwMjYwMWZkMGJmODQ4ZjViMw",1);
            return 0l;
        }

        protected void onProgressUpdate(Integer... progress) {
            //setProgressPercent(progress[0]);
        }

        protected void onPostExecute(Long result) {
            //showDialog("Downloaded " + result + " bytes");
        }
    }

    private class getMusicTask extends AsyncTask<Void, Integer, Long> {

        protected Long doInBackground(Void... params) {
            conn.getMusic(1,"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXUyJ9.eyJzdWIiOjEsImlzcyI6Imh0dHA6XC9cL3NvdW5kc3luYy5ldTEuZnJiaXQubmV0XC9sb2dpbiIsImlhdCI6IjE0MjQ1NzkwMDQiLCJleHAiOiIxNDI0NjY1NDA0IiwibmJmIjoiMTQyNDU3OTAwNCIsImp0aSI6IjIzMjU5ZGI4OTlkZDczY2Y5ZjExYzUwYTk0OWMxNDU3In0.NzNhMWRjNDhkYjU0NTk4OGE4NzMyN2RmOTNlYTUzNGVkOTljNzhiNzZjZDk0N2RhYTZiNDFmZDdlODhhYTU1Nw");
            return 0l;
        }

        protected void onProgressUpdate(Integer... progress) {
            //setProgressPercent(progress[0]);
        }

        protected void onPostExecute(Long result) {
            //showDialog("Downloaded " + result + " bytes");
        }
    }


}
