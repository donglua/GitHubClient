package com.droidcoding.github.data.api;

import com.droidcoding.github.model.RepositoriesResponse;
import com.droidcoding.github.model.Repository;
import com.droidcoding.github.model.User;
import retrofit2.adapter.rxjava.Result;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Donglua on 16/1/21.
 */
public interface GithubService {

  @GET("/search/repositories?per_page=20") //
  Observable<Result<RepositoriesResponse>> repositories( //
      @Query("q") SearchQuery query, //
      @Query("sort") Sort sort, //
      @Query("order") Order order, //
      @Query("page") int page);

  @GET("/user") //
  Observable<Result<User>> getUser();

  //@GET("/repos/{fullName}/git/trees/master") //
  //Observable<Result<RepositoriesResponse>> repositoryTree( //
  //    @Path("fullName") String fullName);

  @GET Observable<Result<Repository>> repository(@Url String url);
}
