package src.com.feature.api.retrofit;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {
    @Multipart
    @POST("user/bos/scheduleJob3")
    Call<ResponseBody> postjob (@Part MultipartBody.Part titleP,
                                @Part MultipartBody.Part descriptionP,
                                @Part MultipartBody.Part durationP);
}
