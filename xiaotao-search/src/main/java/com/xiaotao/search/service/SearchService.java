package com.xiaotao.search.service;

import com.xiaotao.search.pojo.SearchResult;

public interface SearchService {

	SearchResult search(String queryString, int page, int rows) throws Exception;
}
