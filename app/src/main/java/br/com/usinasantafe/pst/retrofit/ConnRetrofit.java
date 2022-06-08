package br.com.usinasantafe.pst.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConnRetrofit {

    private static ConnRetrofit instance = null;

    public static ConnRetrofit getInstance() {
        if (instance == null) {
            instance = new ConnRetrofit();
        }
        return instance;
    }

    public Retrofit conn(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return new Retrofit.Builder()
                        .baseUrl("https://www.usinasantafe.com.br/pstdev/view/")
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
    }

}
