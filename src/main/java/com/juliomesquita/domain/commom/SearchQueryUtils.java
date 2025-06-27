package com.juliomesquita.domain.commom;

import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;

import java.util.*;

public final class SearchQueryUtils {
    private SearchQueryUtils() {
    }

    public static Sort buildSort(final SearchQuery query) {
        if (!Objects.nonNull(query.sort())) {
            return Sort.by("id").ascending();
        }
        final Sort sort = Sort.by(query.sort());
        if (query.direction().equalsIgnoreCase("asc") || Objects.nonNull(query.direction())) {
            return sort.ascending();
        }
        return sort.descending();
    }

    public static Page buildPage(final SearchQuery query) {
        final int page = Math.max(query.currentPage() - 1, 0);
        final int perPage = query.itemsPerPage() <= 0 ? 10 : query.itemsPerPage();
        return Page.of(page, perPage);
    }

    public static Map<String, Object> buildParams(final SearchQuery query) {
        if (query.terms() == null || query.terms().isEmpty()) {
            return new HashMap<>();
        }
        final Map<String, Object> params = new HashMap<>();
        final String[] filters = query.terms().trim().split(",");
        Arrays.stream(filters).forEach(filter -> {
            final String[] keyValue = filter.trim().split("=");
            if(keyValue.length == 1) {
                params.put(keyValue[0], "");
            } else {
                params.put(keyValue[0], keyValue[1]);
            }
        });

        return params;
    }

    public static StringBuilder buildQuery(final Map<String, Object> params) {
        if (params.isEmpty()) {
            return new StringBuilder();
        }

        final var entries = new ArrayList<>(params.entrySet());

        final StringBuilder whereClause = new StringBuilder();
        for (int i = 0; i < entries.size(); i++) {
            final String key = entries.get(i).getKey();

            whereClause.append(String.format("LOWER(%s) like LOWER(CONCAT('%%',:%s,'%%'))", key, key));
            if (i + 1 < entries.size()) {
                whereClause.append(" AND ");
            }
        }

        return whereClause;
    }
}
