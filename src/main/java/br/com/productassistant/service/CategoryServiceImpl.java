package br.com.productassistant.service;

import br.com.productassistant.dto.response.CategoryResponseDTO;
import br.com.productassistant.entity.CategoryView;
import br.com.productassistant.mapper.CategoryMapper;
import br.com.productassistant.repository.CategoryViewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private final CategoryViewRepository categoryViewRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryViewRepository categoryViewRepository, CategoryMapper categoryMapper) {
        this.categoryViewRepository = categoryViewRepository;
        this.categoryMapper = categoryMapper;
    }


    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        List<CategoryView> allCategories = categoryViewRepository.findAll();
        return allCategories.stream().map(categoryMapper::toDTO).toList();
    }
}
