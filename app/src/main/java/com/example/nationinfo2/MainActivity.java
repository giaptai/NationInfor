package com.example.nationinfo2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ImageView imgOk;
    EditText searchView;
    List<Country> arrayList;
    AdapterCountry adapter;
    static String JSON_URL = "https://run.mocky.io/v3/73969b2b-bd1c-4db6-88cc-aac98fc6e587";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //tim theo id
        listView = (ListView) findViewById(R.id.lst_view);// tìm id ListView
        imgOk = (ImageView) findViewById(R.id.img_country);
        searchView = findViewById(R.id.searchName);
        arrayList = new ArrayList<>();

        // chạy cái nay trước
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
        new docJSON().execute(JSON_URL);
        Log.d("FIRST: ", arrayList.toString());
//            }
//        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, item_details.class);

                intent.putExtra("name", (String) arrayList.get(i).getName());
                intent.putExtra("population", (Integer) arrayList.get(i).getPopulation());
                intent.putExtra("areaInSqKm", (Double) arrayList.get(i).getAreaInSqKm());
                intent.putExtra("countryCode", (String) arrayList.get(i).getNameID());
                intent.putExtra("countryImgMap", (String) arrayList.get(i).getImgMap());

                startActivity(intent);
            }
        });

        //Bắt sự kiện tìm kiếm
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                String tempS = searchView.getText().toString();
                List<Country> arraytemp = new ArrayList<>();
                if (tempS.isEmpty()) { // nếu rỗng thì chạy lại hàm lấy dữ liệu để arraylist có mảng như ban đầu
                     new docJSON().execute(JSON_URL);
                } else {
                    for (Country country : arrayList) {
                        if ((country.getName().toLowerCase(Locale.ROOT)).contains(tempS.toLowerCase(Locale.ROOT))) {
                            arraytemp.add(country);
                        }
                    }
                }
                arrayList = arraytemp;
                Log.d("arrayList", arrayList.toString());
                AdapterCountry adapter2 = new AdapterCountry(arraytemp, MainActivity.this);
                listView.setAdapter(adapter2);
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    protected class docJSON extends AsyncTask<String, Integer, String> {
        // roi mới chạy cái này
        @Override
        protected String doInBackground(String... params) {
            Log.d("SSS", DocNoiDungWeb(JSON_URL));
            return DocNoiDungWeb(JSON_URL);
        }

        // doc json tu url truoc
        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("geonames");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject countryName = jsonArray.getJSONObject(i);
//ss
                    String ten = countryName.getString("countryName");
                    Integer danso = countryName.getInt("population");
                    Double dientich = countryName.getDouble("areaInSqKm");
                    String tenCode = countryName.getString("countryCode");
                    String anh = "http://img.geonames.org/flags/x/" +tenCode.toLowerCase(Locale.ROOT)+".gif" ;
                    String anhMap = "http://img.geonames.org/img/country/250/" +tenCode+".png" ;
//ggg
                    Country country = new Country(anh, ten, tenCode, danso, dientich, anhMap);
                    arrayList.add(country);
                }
                adapter = new AdapterCountry(arrayList, MainActivity.this);
                listView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    // lấy nội dụng tư web
    protected String DocNoiDungWeb(String theURL) {
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(theURL); // khởi kiểu dữ liệu URL
            URLConnection httpURLConnection = url.openConnection(); //tạo kết nối đến trang website cần lấy dữ liệu
            // tao biến kiểu dữ liệu InputStream để lấy dữ liệu
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "/n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
