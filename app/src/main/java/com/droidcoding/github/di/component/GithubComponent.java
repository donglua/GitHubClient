package com.droidcoding.github.di.component;

import com.droidcoding.github.di.DaggerGraph;
import com.droidcoding.github.di.module.ApiModule;
import com.droidcoding.github.di.module.DataModule;
import com.droidcoding.github.di.module.GithubModule;
import com.droidcoding.github.di.scope.ApplicationScope;
import dagger.Component;

/**
 * Created by Donglua on 16/1/22.
 */
@ApplicationScope
@Component(
    modules = {
        GithubModule.class,
        DataModule.class,
        ApiModule.class
    }
)
public interface GithubComponent extends DaggerGraph {
}
