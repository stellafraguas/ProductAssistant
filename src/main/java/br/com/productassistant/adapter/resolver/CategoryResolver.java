package br.com.productassistant.adapter.resolver;

import br.com.productassistant.entity.Category;

public interface CategoryResolver {

    String resolveDisplayNameById(Long categoryId);

    Category resolveCategoryById(Long categoryId);
}
