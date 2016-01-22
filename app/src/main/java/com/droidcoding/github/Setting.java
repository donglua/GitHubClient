package com.droidcoding.github;

/**
 * Created by Donglua on 16/1/20.
 */
public interface Setting {
  String AUTHORIZE_URL = "https://github.com/login/oauth/authorize";
  String CLIENT_ID = "047ced2ab145870855dd";
  String CLIENT_SECRET = "fcb6059fc7373d2c9f3aebb09ddd87284ddb7703";
  String AUTHORIZATION_CALLBACK_URL= "http://blog.droidcoding.com";

  String AUTHORIZE_URL_PARAMS =  AUTHORIZE_URL +
      "?" + "client_id=" + CLIENT_ID +
      "&" + "redirect_uri=" + AUTHORIZATION_CALLBACK_URL +
      "&" + "scope=" + "repo, user";

}
