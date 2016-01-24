package com.droidcoding.github.api;

import com.droidcoding.github.model.Order;
import com.droidcoding.github.model.RepositoriesResponse;
import com.droidcoding.github.model.SearchQuery;
import com.droidcoding.github.model.Sort;
import retrofit2.Result;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Donglua on 16/1/21.
 */
public interface GithubService {

  @GET("search/repositories") //
  Observable<Result<RepositoriesResponse>> repositories( //
      @Query("q") SearchQuery query, //
      @Query("sort") Sort sort, //
      @Query("order") Order order);

}
