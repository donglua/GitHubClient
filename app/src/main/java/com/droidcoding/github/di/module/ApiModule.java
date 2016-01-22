package com.droidcoding.github.di.module;

import com.droidcoding.github.api.GithubService;
import com.droidcoding.github.api.oauth.OauthInterceptor;
import com.droidcoding.github.di.scope.ApplicationScope;
import com.google.gson.Gson;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import timber.log.Timber;

@Module(
    includes = DataModule.class
)
public final class ApiModule {
  public static final HttpUrl PRODUCTION_API_URL = HttpUrl.parse("https://api.github.com/");

  @Provides @ApplicationScope HttpUrl provideBaseUrl() {
    return PRODUCTION_API_URL;
  }

  @Provides @ApplicationScope @Named("Api") OkHttpClient provideApiClient(OkHttpClient client,
      OauthInterceptor oauthInterceptor) {
    return createApiClient(client, oauthInterceptor).build();
  }

  @Provides @ApplicationScope Retrofit provideRetrofit(HttpUrl baseUrl, @Named("Api") OkHttpClient client,
      Gson gson) {
    client.networkInterceptors()
        .add(new HttpLoggingInterceptor(message -> Timber.tag("okhttp").d(message)));
    return new Retrofit.Builder() //
        .client(client) //
        .baseUrl(baseUrl) //
        .addConverterFactory(GsonConverterFactory.create(gson)) //
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //
        .build();
  }

  @Provides @ApplicationScope GithubService provideGithubService(Retrofit retrofit) {
    return retrofit.create(GithubService.class);
  }

  static OkHttpClient.Builder createApiClient(OkHttpClient client, OauthInterceptor oauthInterceptor) {
    return client.newBuilder()
        .addInterceptor(oauthInterceptor);
  }
}