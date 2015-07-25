package com.example.gordon.a_retrofit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.gordon.a_retrofit.api.RestClient;
import com.example.gordon.a_retrofit.response.CityResponseModel;
import com.example.gordon.a_retrofit.response.PlaceResonseModel;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.center_btn).setOnClickListener(this);
        findViewById(R.id.top_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.center_btn:
                RestClient.getInstance().getCityApiService().getCities(new Callback<CityResponseModel>() {
                    @Override
                    public void success(CityResponseModel cityResponseModel, Response response) {
                        Toast.makeText(getApplicationContext(),cityResponseModel.toString(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getApplicationContext(), error.toString(),Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.top_btn:
                RestClient.getInstance().getCityApiService().getCityPlaces("shanghai", new Callback<PlaceResonseModel>() {
                    @Override
                    public void success(PlaceResonseModel placeResonseModel, Response response) {
                        Toast.makeText(getApplicationContext(),placeResonseModel.toString(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getApplicationContext(), error.toString(),Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }
}
