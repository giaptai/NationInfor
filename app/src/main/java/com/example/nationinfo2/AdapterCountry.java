package com.example.nationinfo2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.List;
import java.util.Locale;

public class AdapterCountry extends BaseAdapter {
    List<Country> countries;
    Context context;

    public AdapterCountry(List<Country> countries, Context context) {
        this.countries = countries;
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return countries.size();
    }

    @Override
    public Object getItem(int i) {
        return countries.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.item_show, null);
        RecyclerView.ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.activity_main, null);
        }

        //ánh xạ
        TextView textView = view.findViewById(R.id.text_name);
        ImageView img_country = view.findViewById(R.id.img_country);

        //gán giá trị
        Country country = countries.get(i);
        textView.setText(country.getName());

        // HÀM LẤY ẢNH TỪ URL CHUYỂN LÊN IMAGEVIEW
        Glide.with(context) //TRUYỀN VÔ 1 context
                .asGif()
                .load(country.getImg()) //TRUYỀN VÔ 1 CHUỖI URL CỦA ẢNH
                .placeholder(R.drawable.ic_baseline_loop_24) //NẾU CHƯA LOAD ẢNH, HOẶC KO CÓ THÌ HIỆN ẢNH NÀY
                .error(R.drawable.ic_launcher_background)
                .into(img_country);  //TRUYỀN ẢNH LẤY TỪ URL VÀO IMAGEVIEW img_country

        Log.d("84_Adapter", country.getImg());
        return view;
    }

}

