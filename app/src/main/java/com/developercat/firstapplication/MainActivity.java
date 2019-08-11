package com.developercat.firstapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView newsList;
    private NewsAdapter newsAdapter;

    private List<NewsItem> newsItems = new ArrayList<>();

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsList = findViewById(R.id.newsList);

        databaseHelper = new DatabaseHelper(this);

        HttpGetRequest httpGetRequest = new HttpGetRequest();
        httpGetRequest.execute("http://www.hade3.com/_assets/news.aspx");
    }

    public ProgressDialog progressDialog;

    public class HttpGetRequest extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            //on main thread
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String stringUrl = strings[0];

            try {
                URL url = new URL(stringUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("GET");
                connection.setReadTimeout(30000);
                connection.setConnectTimeout(30000);
                connection.connect();

                InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
                BufferedReader reader = new BufferedReader(streamReader);

                StringBuilder stringBuilder = new StringBuilder();
                String inputLine = "";

                while ((inputLine = reader.readLine()) != null) {
                    stringBuilder.append(inputLine);
                }

                reader.close();
                streamReader.close();

                String result = stringBuilder.toString();
                return result;

            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            progressDialog.hide();

            PopulateList(result);
        }
    }

    public void PopulateList(String result) {
        try {
            JSONArray objectList = new JSONArray(result);
            for (int i = 0; i < objectList.length(); i++) {
                JSONObject item = objectList.getJSONObject(i);
                newsItems.add(new NewsItem(item.getInt("id"), item.getString("title"), item.getString("subTitle"), item.getString("text"), item.getString("image"), item.getString("imageDesc"), item.getString("time"), item.getString("publisher")));
                if (item.getBoolean("updated")) {
                    boolean updated = databaseHelper.updateNews(item.getInt("id"), item.getString("title"), item.getString("subTitle"), item.getString("text"), item.getString("image"), item.getString("imageDesc"), item.getString("time"), item.getString("publisher"));
                    if (updated) {
                        Toast.makeText(this, "خبر " + item.getInt("id") + " به روز رسانی شد", Toast.LENGTH_LONG).show();
                    }
                }
                if (item.getBoolean("expired")) {
                    int deleted = databaseHelper.deleteNews(item.getInt("id"));
                    Toast.makeText(this, deleted + " خبر حذف شد", Toast.LENGTH_LONG).show();
                }
            }

            ShowList();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void ShowList() {
        newsItems = databaseHelper.getAllNews();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        newsList.setLayoutManager(linearLayoutManager);

        newsAdapter = new NewsAdapter(this, newsItems, new NewsAdapter.OnNewsClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getApplicationContext(), NewsDetailActivity.class);
                intent.putExtra("id", newsItems.get(position).getId());
                startActivity(intent);
            }
        });
        newsList.setAdapter(newsAdapter);
    }
}
