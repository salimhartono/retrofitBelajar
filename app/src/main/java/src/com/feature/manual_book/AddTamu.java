package src.com.feature.manual_book;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import src.com.feature.R;
import src.com.feature.api.retrofit.BaseApiService;
import src.com.feature.api.retrofit.ServiceAPI;

public class AddTamu extends AppCompatActivity {

    private Button btConfirm;
    private BaseApiService mApiService;
    private String idTamu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_book);

        mApiService = ServiceAPI.getAPIService();
    }

//    private void uploadImage(){
//
//        RequestBody requestId = RequestBody.create(MediaType.parse("*/*"), idTamu);
//        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), idTamu);
//        MultipartBody.Part partImage = MultipartBody.Part.createFormData("Gokerja", idTamu, requestBody);
//
//        mApiService.updateGambar(requestId, partImage)
//                .enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//                    }
//                });
//    }

    /*private void listTamu(){
        mApiService.getAllTamu()
                .enqueue(new Callback<ResponseTamu>() {
                    @Override
                    public void onResponse(Call<ResponseTamu> call, Response<ResponseTamu> response) {

                    }

                    @Override
                    public void onFailure(Call<ResponseTamu> call, Throwable t) {

                    }
                });
    }*/
}
