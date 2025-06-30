package com.juliomesquita.application.usecases.grinding.retrive.findall;

import com.juliomesquita.application.usecases.grinding.commom.GrindingResponse;
import com.juliomesquita.domain.commom.Pagination;
import com.juliomesquita.domain.commom.SearchQuery;
import com.juliomesquita.domain.commom.UseCase;

public abstract class FindAllGrindingUC extends UseCase<SearchQuery, Pagination<GrindingResponse>> {
}
