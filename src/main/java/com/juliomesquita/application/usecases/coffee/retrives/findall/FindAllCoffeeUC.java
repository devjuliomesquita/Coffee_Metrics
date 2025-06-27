package com.juliomesquita.application.usecases.coffee.retrives.findall;

import com.juliomesquita.application.usecases.coffee.commom.CoffeeResponse;
import com.juliomesquita.domain.commom.Pagination;
import com.juliomesquita.domain.commom.SearchQuery;
import com.juliomesquita.domain.commom.UseCase;

public abstract class FindAllCoffeeUC extends UseCase<SearchQuery, Pagination<CoffeeResponse>> {
}
