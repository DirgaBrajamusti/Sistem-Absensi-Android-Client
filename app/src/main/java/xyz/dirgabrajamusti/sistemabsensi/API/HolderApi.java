package xyz.dirgabrajamusti.sistemabsensi.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import xyz.dirgabrajamusti.sistemabsensi.Model.ResponseAbsensi;
import xyz.dirgabrajamusti.sistemabsensi.Model.ResponseKelas;
import xyz.dirgabrajamusti.sistemabsensi.Model.ResponseLogin;

public interface HolderApi {

    @FormUrlEncoded
    @POST("login")
    Call<ResponseLogin> loginRequest(@Field("email") String email,
                                     @Field("password") String password);

    @FormUrlEncoded
    @POST("register")
    Call<ResponseLogin> registerRequest(@Field("name") String name,
                                        @Field("email") String email,
                                        @Field("password") String password,
                                        @Field("c_password") String c_password);

    @GET("data-kelas")
    Call<ResponseAbsensi> getAbsensi();
}
