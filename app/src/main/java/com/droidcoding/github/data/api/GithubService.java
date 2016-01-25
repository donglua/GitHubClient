package com.droidcoding.github.data.api;

import com.droidcoding.github.model.RepositoriesResponse;
import retrofit2.Result;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Donglua on 16/1/21.
 */
public interface GithubService {

  @GET("search/repositories?per_page=20") //
  Observable<Result<RepositoriesResponse>> repositories( //
      @Query("q") SearchQuery query, //
      @Query("sort") Sort sort, //
      @Query("order") Order order, //
      @Query("page") int page);

}
