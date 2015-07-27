package com.example.gordon.a_retrofit.ui;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gordon.a_retrofit.LocationEntity;
import com.example.gordon.a_retrofit.R;
import com.example.gordon.a_retrofit.api.RestClient;
import com.example.gordon.a_retrofit.response.CityResonseModel;
import com.example.gordon.a_retrofit.response.CountyResponseModel;
import com.example.gordon.a_retrofit.response.ProvinceResponseModel;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by gordon on 2015/7/25.
 */
public class PopChooser extends Dialog implements View.OnClickListener {
    private Context context;
    private PopMenuLevel level;
    private LocationEntity locationEntity;
    private PopChooserListener listener;

    public PopChooser(Context context, PopMenuLevel level, LocationEntity locationEntity) {
        super(context);
        this.context = context;
        this.level = level;
        this.locationEntity = locationEntity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_pop_chooser);
        configureWindowFeature();
    }

    public PopChooser setListener(PopChooserListener listener){
        this.listener = listener;
        return this;
    }

    private void configureWindowFeature() {
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        getWindow().getAttributes().windowAnimations = R.style.PopChooserAnimation;
    }

    @Override
    protected void onStart() {
        super.onStart();
        switch (level) {
            case LEVEL1:
                level1Job();
                break;
            case LEVEL2:
                level2Job(locationEntity.getProvince());
                break;
            case LEVEL3:
                level3Job(locationEntity.getProvince(),locationEntity.getCity());
                break;
        }
    }

    private void level1Job() {
        ((TextView)findViewById(R.id.tv)).setText("请选择省份");
        RestClient.getInstance().getCityApiService().getProvinces(new Callback<ProvinceResponseModel>() {
            @Override
            public void success(final ProvinceResponseModel provinceResponseModel, Response response) {
                ((ListView) findViewById(R.id.lv)).setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return provinceResponseModel.getProvinces().size();
                    }

                    @Override
                    public Object getItem(int i) {
                        return null;
                    }

                    @Override
                    public long getItemId(int i) {
                        return i;
                    }

                    @Override
                    public View getView(int i, View view, ViewGroup viewGroup) {
                        View root = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);
                        ((TextView) root.findViewById(R.id.center)).setText(provinceResponseModel.getProvinces().get(i));
                        root.setOnClickListener(PopChooser.this);
                        return root;
                    }
                });
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private void level2Job(String province) {
        ((TextView)findViewById(R.id.tv)).setText("请选择城市");
        RestClient.getInstance().getCityApiService().getCities(province, new Callback<CityResonseModel>() {
            @Override
            public void success(final CityResonseModel cityResonseModel, Response response) {
                ((ListView) findViewById(R.id.lv)).setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return cityResonseModel.getCities().size();
                    }

                    @Override
                    public Object getItem(int i) {
                        return null;
                    }

                    @Override
                    public long getItemId(int i) {
                        return i;
                    }

                    @Override
                    public View getView(int i, View view, ViewGroup viewGroup) {
                        View root = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);
                        ((TextView) root.findViewById(R.id.center)).setText(cityResonseModel.getCities().get(i));
                        root.setOnClickListener(PopChooser.this);
                        return root;
                    }
                });
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private void level3Job(String province,String city) {
        ((TextView)findViewById(R.id.tv)).setText("请选择县份");
        RestClient.getInstance().getCityApiService().getCounties(province, city, new Callback<CountyResponseModel>() {
            @Override
            public void success(final CountyResponseModel countyResponseModel, Response response) {
                ((ListView) findViewById(R.id.lv)).setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return countyResponseModel.getCounties().size();
                    }

                    @Override
                    public Object getItem(int i) {
                        return null;
                    }

                    @Override
                    public long getItemId(int i) {
                        return i;
                    }

                    @Override
                    public View getView(int i, View view, ViewGroup viewGroup) {
                        View root = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);
                        ((TextView) root.findViewById(R.id.center)).setText(countyResponseModel.getCounties().get(i));
                        root.setOnClickListener(PopChooser.this);
                        return root;
                    }
                });
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        dismiss();
        if (this.level == PopMenuLevel.LEVEL1) {
            this.locationEntity.setPrivince(((TextView) view.findViewById(R.id.center)).getText().toString());
            new PopChooser(context, PopMenuLevel.LEVEL2, this.locationEntity).setListener(listener).show();
        }
        if (this.level == PopMenuLevel.LEVEL2){
            this.locationEntity.setCity(((TextView) view.findViewById(R.id.center)).getText().toString());
            new PopChooser(context, PopMenuLevel.LEVEL3, this.locationEntity).setListener(listener).show();
        }
        if (this.level == PopMenuLevel.LEVEL3){
            this.locationEntity.setCounty(((TextView) view.findViewById(R.id.center)).getText().toString());
            if(listener!=null) listener.finish(locationEntity);
        }
    }

    public enum PopMenuLevel {
        LEVEL1,
        LEVEL2,
        LEVEL3
    }

    public interface PopChooserListener{
        void finish(LocationEntity locationEntity);
    }
}
