package src.com.feature.api.retrofit;

public class ServiceAPI {
    // 10.0.2.2 ini adalah localhost.
    public static final String BASE_URL_API = "https://salimhartono.000webhostapp.com/";

    // Mendeklarasikan Interface BaseApiService
    public static BaseApiService getAPIService(){
        return ServiceClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
