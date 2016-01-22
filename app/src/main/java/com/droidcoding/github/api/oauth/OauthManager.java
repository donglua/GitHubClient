package com.droidcoding.github.api.oauth;

import android.content.Intent;
import android.net.Uri;
import com.droidcoding.github.api.IntentFactory;
import com.droidcoding.github.di.scope.ApplicationScope;
import com.f2prateek.rx.preferences.Preference;
import com.google.gson.Gson;
import java.io.IOException;
import javax.inject.Inject;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

/**
 * 参考: https://github.com/JakeWharton/u2020/blob/master/src/main/java/com/jakewharton/u2020/data/api/oauth/OauthManager.java
 */
@ApplicationScope public final class OauthManager {
  private static final String CLIENT_ID = "047ced2ab145870855dd";
  private static final String CLIENT_SECRET = "fcb6059fc7373d2c9f3aebb09ddd87284ddb7703";

  private final IntentFactory intentFactory;
  private final OkHttpClient client;
  private final Gson gson;
  private final Preference<String> accessToken;

  @Inject public OauthManager(IntentFactory intentFactory, OkHttpClient client, Gson gson,
      @AccessToken Preference<String> accessToken) {
    this.intentFactory = intentFactory;
    this.client = client;
    this.gson = gson;
    this.accessToken = accessToken;
  }

  public Intent createLoginIntent() {
    HttpUrl authorizeUrl = HttpUrl.parse("https://github.com/login/oauth/authorize") //
        .newBuilder() //
        .addQueryParameter("client_id", CLIENT_ID) //
        .build();

    return intentFactory.createUrlIntent(authorizeUrl.toString());
  }

  public void handleResult(Uri data) {
    if (data == null) return;

    String code = data.getQueryParameter("code");
    if (code == null) return;

    Timber.d("code = %s", code);

    try {
      // Trade our code for an access token.
      Request request = new Request.Builder() //
          .url("https://github.com/login/oauth/access_token") //
          .header("Accept", "application/json") //
          .post(new FormBody.Builder() //
              .add("client_id", CLIENT_ID) //
              .add("client_secret", CLIENT_SECRET) //
              .add("code", code) //
              .build()) //
          .build();

      Response response = client.newCall(request).execute();
      if (response.isSuccessful()) {
        AccessTokenResponse accessTokenResponse =
            gson.fromJson(response.body().string(), AccessTokenResponse.class);
        if (accessTokenResponse != null && accessTokenResponse.access_token != null) {
          accessToken.set(accessTokenResponse.access_token);
        }
      }
    } catch (IOException e) {
      Timber.w(e, "Failed to get access token.");
    }
  }

  private static final class AccessTokenResponse {
    public final String access_token;

    private AccessTokenResponse(String access_token, String scope) {
      this.access_token = access_token;
    }
  }
}
