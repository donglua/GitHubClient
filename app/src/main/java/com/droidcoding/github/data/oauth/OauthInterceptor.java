package com.droidcoding.github.data.oauth;

import com.droidcoding.github.di.scope.ApplicationScope;
import com.f2prateek.rx.preferences.Preference;
import java.io.IOException;
import javax.inject.Inject;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

@ApplicationScope public final class OauthInterceptor implements Interceptor {
  private final Preference<String> accessToken;

  @Inject public OauthInterceptor(@AccessToken Preference<String> accessToken) {
    this.accessToken = accessToken;
  }

  @Override public Response intercept(Chain chain) throws IOException {
    Request.Builder builder = chain.request().newBuilder();

    if (accessToken.isSet()) {
      builder.header("Authorization", "token " + accessToken.get());
      Timber.d("header add %s", accessToken.get());
    }

    return chain.proceed(builder.build());
  }
}
