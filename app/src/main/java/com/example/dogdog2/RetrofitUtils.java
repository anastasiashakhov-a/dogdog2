package com.example.dogdog2;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {

    private static final String LOGIN = "ck_308e395971dfebb42b76b354d4d61d0c47d0ffa1";
    private static final String PASSWORD = "cs_5305963859de9185f76d2074f33d88e8d278e0d7";


    public static LoveAndPetsApi getApi(){
        return setupClient().create(LoveAndPetsApi.class);
    }

    public static Retrofit setupClient() {


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .authenticator((route, response) -> {
                    Request request = response.request();
                    if (request.header("Authorization") != null)
                        // Логин и пароль неверны
                        return null;
                    return request.newBuilder()
                            .header("Authorization", Credentials.basic(LOGIN, PASSWORD))
                            .build();
                })
                .build();

        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://loveandpets.ru/wp-json/wc/v3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;

    }
}
