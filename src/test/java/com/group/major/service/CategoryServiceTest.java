package com.group.major.service;

import com.group.major.model.Category;
import com.group.major.repository.CategoryRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CategoryServiceTest {

    private CategoryService categoryServiceUnderTest;

    @Before
    public void setUp() throws Exception {
        categoryServiceUnderTest = new CategoryService();
        categoryServiceUnderTest.categoryRepository = mock(CategoryRepository.class);
    }

    @Test
    public void testGetAllCategory() {
        // Setup
        final Category category = new Category();
        category.setId(0);
        category.setName("name");
        final List<Category> expectedResult = Arrays.asList(category);

        // Configure CategoryRepository.findAll(...).
        final Category category1 = new Category();
        category1.setId(0);
        category1.setName("name");
        final List<Category> categories = Arrays.asList(category1);
        when(categoryServiceUnderTest.categoryRepository.findAll()).thenReturn(categories);

        // Run the test
        final List<Category> result = categoryServiceUnderTest.getAllCategory();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testGetAllCategory_CategoryRepositoryReturnsNoItems() {
        // Setup
        final Category category = new Category();
        category.setId(0);
        category.setName("name");
        final List<Category> expectedResult = Arrays.asList(category);
        when(categoryServiceUnderTest.categoryRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Category> result = categoryServiceUnderTest.getAllCategory();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testAddCategory() {
        // Setup
        final Category category = new Category();
        category.setId(0);
        category.setName("name");

        // Configure CategoryRepository.save(...).
        final Category category1 = new Category();
        category1.setId(0);
        category1.setName("name");
        when(categoryServiceUnderTest.categoryRepository.save(new Category())).thenReturn(category1);

        // Run the test
        categoryServiceUnderTest.addCategory(category);

        // Verify the results
        verify(categoryServiceUnderTest.categoryRepository).save(new Category());
    }

    @Test
    public void testRemoveCategoryById() {
        // Setup

        // Run the test
        categoryServiceUnderTest.removeCategoryById(0);

        // Verify the results
        verify(categoryServiceUnderTest.categoryRepository).deleteById(0);
    }

    @Test
    public void testGetCategoryById() {
        // Setup
        final Category category = new Category();
        category.setId(0);
        category.setName("name");
        final Optional<Category> expectedResult = Optional.of(category);

        // Configure CategoryRepository.findById(...).
        final Category category2 = new Category();
        category2.setId(0);
        category2.setName("name");
        final Optional<Category> category1 = Optional.of(category2);
        when(categoryServiceUnderTest.categoryRepository.findById(0)).thenReturn(category1);

        // Run the test
        final Optional<Category> result = categoryServiceUnderTest.getCategoryById(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testGetCategoryById_CategoryRepositoryReturnsAbsent() {
        // Setup
        final Category category = new Category();
        category.setId(0);
        category.setName("name");
        final Optional<Category> expectedResult = Optional.of(category);
        when(categoryServiceUnderTest.categoryRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        final Optional<Category> result = categoryServiceUnderTest.getCategoryById(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
