package com.example.nationinfo2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.text.DecimalFormat;
import java.util.Locale;

public class item_details extends AppCompatActivity {
    static DecimalFormat df = new DecimalFormat("###,###,###,###,###");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        Intent intent = getIntent();
        //tim id
        TextView ten = findViewById(R.id.TenNuoc);
        TextView danso = findViewById(R.id.DanSo);
        TextView dientich = findViewById(R.id.DienTich);
        ImageView anhCountry=findViewById(R.id.imageView);
        ImageView anhCountryMap=findViewById(R.id.imageView2);
        //tim id
        String tempName = intent.getStringExtra("name");
        Integer tempPopulation = intent.getIntExtra("population", 3);
        Double tempAreaSquare = intent.getDoubleExtra("areaInSqKm", 125);
        String tempcountryCode = intent.getStringExtra("countryCode");
        String tempcountryImgMap = intent.getStringExtra("countryImgMap");

        String anh = "http://img.geonames.org/flags/x/" + tempcountryCode.toLowerCase(Locale.ROOT) + ".gif";

        Glide.with(this) //TRUYỀN VÔ 1 IMAGEVIEW
                .asGif()
                .load(anh) //TRUYỀN VÔ 1 CHUỖI URL CỦA ẢNH
                .placeholder(R.drawable.ic_baseline_loop_24) //NẾU CHƯA LOAD ẢNH, HOẶC KO CÓ THÌ HIỆN ẢNH NÀY
                .error(R.drawable.ic_launcher_background)
                .apply(RequestOptions.overrideOf(250, 250))
                .into(anhCountry);  //TRUYỀN ẢNH LẤY TỪ URL VÀO IMAGEVIEW img_country

        Glide.with(this)
                .load(tempcountryImgMap)
                .placeholder(R.drawable.ic_baseline_loop_24)
                .error(R.drawable.ic_launcher_background)
                .into(anhCountryMap);

        ten.setText("Tên nước: "+tempName);
        danso.setText("Dân số: "+df.format(tempPopulation));
        dientich.setText("Diện tích: "+df.format(tempAreaSquare));

        Log.d("ImgMap", tempcountryImgMap);
    }
}