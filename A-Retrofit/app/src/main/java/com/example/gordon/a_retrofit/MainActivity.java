package com.example.gordon.a_retrofit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.gordon.a_retrofit.api.RestClient;
import com.example.gordon.a_retrofit.response.CityResonseModel;
import com.example.gordon.a_retrofit.response.ProvinceResponseModel;
import com.example.gordon.a_retrofit.ui.PopChooser;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends Activity implements View.OnClickListener{

    private LocationEntity locationEntity = new LocationEntity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.center_btn).setOnClickListener(this);
        findViewById(R.id.top_btn).setOnClickListener(this);
        findViewById(R.id.bottom_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.center_btn:
                RestClient.getInstance().getCityApiService().getProvinces(new Callback<ProvinceResponseModel>() {
                    @Override
                    public void success(ProvinceResponseModel cityResponseModel, Response response) {
                        Toast.makeText(getApplicationContext(),cityResponseModel.toString(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getApplicationContext(), error.toString(),Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.top_btn:
                RestClient.getInstance().getCityApiService().getCities("上海市", new Callback<CityResonseModel>() {
                    @Override
                    public void success(CityResonseModel cityResonseModel, Response response) {
                        Toast.makeText(getApplicationContext(), cityResonseModel.toString(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getApplicationContext(), error.toString(),Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.bottom_btn:
                new PopChooser(MainActivity.this, PopChooser.PopMenuLevel.LEVEL1,locationEntity).setListener(new PopChooser.PopChooserListener() {
                    @Override
                    public void finish(LocationEntity locationEntity) {
                        Toast.makeText(getApplicationContext(), "you have choosed -> " + locationEntity.toString(),Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
        }
    }
}
