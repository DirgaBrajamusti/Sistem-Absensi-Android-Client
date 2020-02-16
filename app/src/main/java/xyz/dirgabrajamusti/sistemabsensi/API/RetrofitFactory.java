package xyz.dirgabrajamusti.sistemabsensi.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    private static Retrofit retrofit = null;

    private RetrofitFactory(){}

    public static Retrofit getRetrofit(){
        if(retrofit==null){
            retrofit= new Retrofit.Builder()
                    .baseUrl("http://192.168.43.60/proyek/public/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
