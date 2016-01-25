package com.droidcoding.github.di.module;

import android.app.Application;
import android.content.SharedPreferences;
import com.droidcoding.github.data.InstantDeserializer;
import com.droidcoding.github.data.api.IntentFactory;
import com.droidcoding.github.data.oauth.AccessToken;
import com.droidcoding.github.di.scope.ApplicationScope;
import com.f2prateek.rx.preferences.Preference;
import com.f2prateek.rx.preferences.RxSharedPreferences;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import java.io.File;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.threeten.bp.Instant;
import timber.log.Timber;

import static android.content.Context.MODE_PRIVATE;
import static com.jakewharton.byteunits.DecimalByteUnit.MEGABYTES;

/**
 * Created by Donglua on 16/1/21.
 */
@Module(
    includes = GithubModule.class
)
public class DataModule {

  static final int DISK_CACHE_SIZE = (int) MEGABYTES.toBytes(50);

  @Provides @ApplicationScope SharedPreferences provideSharedPreferences(Application app) {
    return app.getSharedPreferences("Github", MODE_PRIVATE);
  }

  @Provides @ApplicationScope RxSharedPreferences provideRxSharedPreferences(SharedPreferences prefs) {
    return RxSharedPreferences.create(prefs);
  }

  @Provides @ApplicationScope @AccessToken Preference<String> provideAccessToken(RxSharedPreferences prefs) {
    return prefs.getString("access-token");
  }

  @Provides @ApplicationScope IntentFactory provideIntentFactory() {
    return IntentFactory.REAL;
  }

  @Provides @ApplicationScope OkHttpClient provideOkHttpClient(Application app, HttpLoggingInterceptor loggingInterceptor) {
    return createOkHttpClient(app).addInterceptor(loggingInterceptor).build();
  }

  static OkHttpClient.Builder createOkHttpClient(Application app) {
    // Install an HTTP cache in the application cache directory.
    File cacheDir = new File(app.getCacheDir(), "http");
    Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);

    return new OkHttpClient.Builder()
        .cache(cache);
  }


  @Provides public Gson provideGson() {
    return new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .registerTypeAdapter(Instant.class, new InstantDeserializer())
        .create();
  }


  @Provides @ApplicationScope HttpLoggingInterceptor provideLoggingInterceptor() {
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> Timber.tag("OkHttp").v(message));
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    return loggingInterceptor;
  }

}
