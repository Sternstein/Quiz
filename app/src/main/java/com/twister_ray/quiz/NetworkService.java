package com.twister_ray.quiz;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
  private static NetworkService mInstance;
  private static final String BASE_URL = "https://quiz.andreygagarin.buzz";
  private Retrofit mRetrofit;

  private NetworkService() {


    OkHttpClient.Builder client = new OkHttpClient.Builder();
    client.interceptors().add(new Interceptor() {
      @Override
      public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        // Настраиваем запросы
        Request request = original.newBuilder()
            .header("Accept", "application/json")
            .header("Authorization", "auth-token")
            .method(original.method(), original.body())
            .build();

        Response response = chain.proceed(request);

        return response;
      }
    });
    mRetrofit = new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client.build())
        .build();
  }

  public static NetworkService getInstance() {
    if (mInstance == null) {
      mInstance = new NetworkService();
    }
    return mInstance;
  }

  public JsonQuizApi getJsonApi() {
    return mRetrofit.create(JsonQuizApi.class);
  }
}
