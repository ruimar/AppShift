package com.example.rui.appshift;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rui on 20/02/2015.
 */

public class SoundSyncAPI {

    public final static int DEFAULT_LIMIT=1000;
    public final static int MUSICS_DEFAULT_LIMIT=1000;

    SharedPreferences userPreferences;
    Editor editor;
    Context context;

    public SoundSyncAPI(Context cont) {

        context = cont;
        userPreferences = context.getSharedPreferences("SoundSyncPrefs", 0);
        editor = userPreferences.edit();

    }

    public class User {

        String username, password, last_login, phone;
        int id;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getLast_login() {
            return last_login;
        }

        public void setLast_login(String last_login) {
            this.last_login = last_login;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public User(int i, String user, String pass, String last_log, String ph) {

            id=i;
            username = user;
            password = pass;
            last_login = last_log;
            phone = ph;

        }

    }

    public class Music {

        int source_id, views, id;
        String name, source_type;

        public int getSource_id() {
            return source_id;
        }

        public void setSource_id(int source_id) {
            this.source_id = source_id;
        }

        public int getViews() {
            return views;
        }

        public void setViews(int views) {
            this.views = views;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSource_type() {
            return source_type;
        }

        public void setSource_type(String source_type) {
            this.source_type = source_type;
        }

        public Music(int i, int source, int v, String nm, String source_t) {

            id=i;
            source_id = source;
            views = v;
            name = nm;
            source_type = source_t;

        }

    }

    public class Playlist {

        String name;
        int id;

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Playlist(int i, String nm) {

            id=i;
            name = nm;


        }

    }

    public int signUp(String name, String phone, String password) throws IOException {

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://soundsync.eu1.frbit.net/users");
        try {
            List<BasicNameValuePair> nameValuePairs = new ArrayList<>(3);
            nameValuePairs.add(new BasicNameValuePair("username", name));
            nameValuePairs.add(new BasicNameValuePair("phone", phone));
            nameValuePairs.add(new BasicNameValuePair("password", password));
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpResponse response = httpClient.execute(httpPost);

            int t = response.getStatusLine().getStatusCode();
            String teste = "" + t;

            Log.e("code Sign Up", teste);

            if (response.getStatusLine().getStatusCode() == 200) {

                return 200;

            } else if (response.getStatusLine().getStatusCode() == 422) {

                return 422;

            } else if (response.getStatusLine().getStatusCode() == 400) {

                return 400;

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
        return 0;
    }

    public int signIn(String name, String password) throws IOException {

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://soundsync.eu1.frbit.net/login");
        try {
            List<BasicNameValuePair> nameValuePairs = new ArrayList<>(2);
            nameValuePairs.add(new BasicNameValuePair("username", name));
            nameValuePairs.add(new BasicNameValuePair("password", password));
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpResponse response = httpClient.execute(httpPost);

            String tt=context.getSharedPreferences("SoundSyncPrefs", 0).getString("token", "");

            Log.e("token: ", tt);

            int t = response.getStatusLine().getStatusCode();
            String teste = "" + t;

            Log.e("code Sign In", teste);

            String data = EntityUtils.toString(response.getEntity());

            editor.putString("token", data);
            editor.commit();

            if (response.getStatusLine().getStatusCode() == 200) {

                Log.e("Login: ", "sucesso!");
                return 200;

            } else if (response.getStatusLine().getStatusCode() == 403) {

                Log.e("LOGTAH", "try again");
                return 403;

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
        return 0;
    }


    public int changePassword(String new_password, String token) throws IOException {

        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            URI apiUrl =  new URI("http://soundsync.eu1.frbit.net/newpassword/?new_password="+new_password+"&token="+token );
            request.setURI(apiUrl);
            HttpResponse response = httpClient.execute(request);

            int t = response.getStatusLine().getStatusCode();
            String teste = "" + t;

            Log.e("code Change Password", teste);

            if (response.getStatusLine().getStatusCode() == 200) {

                Log.e("LOGTAH", "sucesso!");
                return 200;

            } else if (response.getStatusLine().getStatusCode() == 422) {

                Log.e("LOGTAH", "try again");
                return 422;

            } else if (response.getStatusLine().getStatusCode() == 400) {

                Log.e("LOGTAH", "try again");
                return 400;

            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int getUserInfo(String token){

        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            URI apiUrl =  new URI("http://soundsync.eu1.frbit.net/users/?token="+token );
            request.setURI(apiUrl);
            HttpResponse response = httpClient.execute(request);

            int t = response.getStatusLine().getStatusCode();
            String teste = "" + t;


            Log.e("code getUserInfo", teste);

            if (response.getStatusLine().getStatusCode() == 200) {

                String xxx = EntityUtils.toString(response.getEntity());
                JSONObject jsonObject = (JSONObject) new JSONTokener(xxx).nextValue();
                JSONObject tt=jsonObject.getJSONObject("user");
                String user=tt.getString("username");

                editor.putString("username", user);
                editor.commit();

                Log.e("LOGTAH", "sucesso!");
                return 200;

            } else if (response.getStatusLine().getStatusCode() == 422) {

                Log.e("LOGTAH", "try again");
                return 422;

            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int createPlaylist(String name, String token){

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://soundsync.eu1.frbit.net/playlists");

        try {
            List<BasicNameValuePair> nameValuePairs = new ArrayList<>(2);
            nameValuePairs.add(new BasicNameValuePair("name", name));
            nameValuePairs.add(new BasicNameValuePair("token", token));
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpResponse response = httpClient.execute(httpPost);

            int t = response.getStatusLine().getStatusCode();
            String teste = "" + t;

            Log.e("code Create Playlist", teste);

            if (response.getStatusLine().getStatusCode() == 200) {

                Log.e("Log: ", "sucesso!");
                return 200;

            } else if (response.getStatusLine().getStatusCode() == 403) {

                Log.e("LOGTAH", "try again");
                return 403;

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
        return 0;
    }

    public String searchPlaylist(int id, String token){

        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            URI apiUrl =  new URI("http://soundsync.eu1.frbit.net/playlists/"+id+"/?token="+token);
            request.setURI(apiUrl);
            HttpResponse response = httpClient.execute(request);

            int t = response.getStatusLine().getStatusCode();
            String teste = "" + t;

            Log.e("code Search Playlist", teste);

            if (response.getStatusLine().getStatusCode() == 200) {

                String xxx = EntityUtils.toString(response.getEntity());
                JSONObject jsonObject = (JSONObject) new JSONTokener(xxx).nextValue();
                JSONObject tt=jsonObject.getJSONObject("playlist");
                String name=tt.getString("name");

                return name;

            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[][] getPlaylists(int limit, String token){

        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            URI apiUrl =  new URI("http://soundsync.eu1.frbit.net/playlists/?limit="+limit+"&token="+token );
            request.setURI(apiUrl);
            HttpResponse response = httpClient.execute(request);

            int t = response.getStatusLine().getStatusCode();
            String teste = "" + t;

            Log.e("code getPlaylists", teste);

            if (response.getStatusLine().getStatusCode() == 200) {

                String xxx = EntityUtils.toString(response.getEntity());
                JSONObject jsonObject = (JSONObject) new JSONTokener(xxx).nextValue();
                JSONArray array=jsonObject.getJSONArray("playlists");

                JSONObject tt=jsonObject.getJSONObject("paginator");
                int total_count=tt.getInt("total_count");


                String[][] fin_array = new String[total_count][2];

                for(int i=0; i<total_count; i++){
                    fin_array[i][0]=array.getJSONObject(i).getInt("id")+"";
                    fin_array[i][1]=array.getJSONObject(i).getString("name");
                    Log.e(fin_array[i][0], fin_array[i][1]);
                }

                Log.e("LOGTAH", "sucesso!");
                return fin_array;

            } else if (response.getStatusLine().getStatusCode() == 422) {

                Log.e("LOGTAH", "try again");
                return null;

            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int addMusic(String name, String source_id, int source_type, String token, int playlistid){

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://soundsync.eu1.frbit.net/playlists/"+playlistid+"/musics");

        try {
            List<BasicNameValuePair> nameValuePairs = new ArrayList<>(4);
            nameValuePairs.add(new BasicNameValuePair("name", name));
            nameValuePairs.add(new BasicNameValuePair("source_id", source_id));
            nameValuePairs.add(new BasicNameValuePair("source_type", source_type+""));
            nameValuePairs.add(new BasicNameValuePair("token", token));
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpResponse response = httpClient.execute(httpPost);

            int t = response.getStatusLine().getStatusCode();
            String teste = "" + t;

            Log.e("code add music", teste);

            if (response.getStatusLine().getStatusCode() == 200) {

                Log.e("Log: ", "sucesso!");
                return 200;

            } else if (response.getStatusLine().getStatusCode() == 403) {

                Log.e("LOGTAH", "try again");
                return 403;

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
        return 0;
    }

    public String[][] getMusic(int id, String token){

        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            URI apiUrl =  new URI("http://soundsync.eu1.frbit.net/playlists/"+id+"/musics/?token="+token);
            request.setURI(apiUrl);
            HttpResponse response = httpClient.execute(request);

            int t = response.getStatusLine().getStatusCode();
            String teste = "" + t;

            Log.e("code get music", teste);

            if (response.getStatusLine().getStatusCode() == 200) {

                String xxx = EntityUtils.toString(response.getEntity());
                JSONObject jsonObject = (JSONObject) new JSONTokener(xxx).nextValue();
                JSONArray array=jsonObject.getJSONArray("musics");

                JSONObject tt=jsonObject.getJSONObject("paginator");
                int total_count=tt.getInt("total_count");

                String[][] fin_array = new String[total_count][5];

                for(int i=0; i<total_count; i++){
                    fin_array[i][0]=array.getJSONObject(i).getInt("id")+"";
                    fin_array[i][1]=array.getJSONObject(i).getString("name");
                    fin_array[i][2]=array.getJSONObject(i).getString("source_id");
                    fin_array[i][3]=array.getJSONObject( i).getString("source_type");
                    fin_array[i][4]=array.getJSONObject(i).getString("views");
                }

                return fin_array;

            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
