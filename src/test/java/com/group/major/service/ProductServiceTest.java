package com.group.major.service;

import com.group.major.model.Category;
import com.group.major.model.Product;
import com.group.major.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    private ProductService productServiceUnderTest;

    @Before
    public void setUp() throws Exception {
        productServiceUnderTest = new ProductService();
        productServiceUnderTest.productRepository = mock(ProductRepository.class);
    }

    @Test
    public void testGetAllProduct() {
        // Setup
        final Product product = new Product();
        product.setId(0L);
        product.setName("name");
        final Category category = new Category();
        category.setId(0);
        category.setName("name");
        product.setCategory(category);
        product.setPrice(0.0);
        product.setWeight(0.0);
        product.setDescription("description");
        product.setImageName("imageName");
        final List<Product> expectedResult = Arrays.asList(product);

        // Configure ProductRepository.findAll(...).
        final Product product1 = new Product();
        product1.setId(0L);
        product1.setName("name");
        final Category category1 = new Category();
        category1.setId(0);
        category1.setName("name");
        product1.setCategory(category1);
        product1.setPrice(0.0);
        product1.setWeight(0.0);
        product1.setDescription("description");
        product1.setImageName("imageName");
        final List<Product> products = Arrays.asList(product1);
        when(productServiceUnderTest.productRepository.findAll()).thenReturn(products);

        // Run the test
        final List<Product> result = productServiceUnderTest.getAllProduct();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testGetAllProduct_ProductRepositoryReturnsNoItems() {
        // Setup
        final Product product = new Product();
        product.setId(0L);
        product.setName("name");
        final Category category = new Category();
        category.setId(0);
        category.setName("name");
        product.setCategory(category);
        product.setPrice(0.0);
        product.setWeight(0.0);
        product.setDescription("description");
        product.setImageName("imageName");
        final List<Product> expectedResult = Arrays.asList(product);
        when(productServiceUnderTest.productRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Product> result = productServiceUnderTest.getAllProduct();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testAddProduct() {
        // Setup
        final Product product = new Product();
        product.setId(0L);
        product.setName("name");
        final Category category = new Category();
        category.setId(0);
        category.setName("name");
        product.setCategory(category);
        product.setPrice(0.0);
        product.setWeight(0.0);
        product.setDescription("description");
        product.setImageName("imageName");

        // Configure ProductRepository.save(...).
        final Product product1 = new Product();
        product1.setId(0L);
        product1.setName("name");
        final Category category1 = new Category();
        category1.setId(0);
        category1.setName("name");
        product1.setCategory(category1);
        product1.setPrice(0.0);
        product1.setWeight(0.0);
        product1.setDescription("description");
        product1.setImageName("imageName");
        when(productServiceUnderTest.productRepository.save(new Product())).thenReturn(product1);

        // Run the test
        productServiceUnderTest.addProduct(product);

        // Verify the results
        verify(productServiceUnderTest.productRepository).save(new Product());
    }

    @Test
    public void testRemoveProductById() {
        // Setup

        // Run the test
        productServiceUnderTest.removeProductById(0L);

        // Verify the results
        verify(productServiceUnderTest.productRepository).deleteById(0L);
    }

    @Test
    public void testGetProductById() {
        // Setup
        final Product product = new Product();
        product.setId(0L);
        product.setName("name");
        final Category category = new Category();
        category.setId(0);
        category.setName("name");
        product.setCategory(category);
        product.setPrice(0.0);
        product.setWeight(0.0);
        product.setDescription("description");
        product.setImageName("imageName");
        final Optional<Product> expectedResult = Optional.of(product);

        // Configure ProductRepository.findById(...).
        final Product product2 = new Product();
        product2.setId(0L);
        product2.setName("name");
        final Category category1 = new Category();
        category1.setId(0);
        category1.setName("name");
        product2.setCategory(category1);
        product2.setPrice(0.0);
        product2.setWeight(0.0);
        product2.setDescription("description");
        product2.setImageName("imageName");
        final Optional<Product> product1 = Optional.of(product2);
        when(productServiceUnderTest.productRepository.findById(0L)).thenReturn(product1);

        // Run the test
        final Optional<Product> result = productServiceUnderTest.getProductById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testGetProductById_ProductRepositoryReturnsAbsent() {
        // Setup
        final Product product = new Product();
        product.setId(0L);
        product.setName("name");
        final Category category = new Category();
        category.setId(0);
        category.setName("name");
        product.setCategory(category);
        product.setPrice(0.0);
        product.setWeight(0.0);
        product.setDescription("description");
        product.setImageName("imageName");
        final Optional<Product> expectedResult = Optional.of(product);
        when(productServiceUnderTest.productRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final Optional<Product> result = productServiceUnderTest.getProductById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testGetAllProductsByCategoryId() {
        // Setup
        final Product product = new Product();
        product.setId(0L);
        product.setName("name");
        final Category category = new Category();
        category.setId(0);
        category.setName("name");
        product.setCategory(category);
        product.setPrice(0.0);
        product.setWeight(0.0);
        product.setDescription("description");
        product.setImageName("imageName");
        final List<Product> expectedResult = Arrays.asList(product);

        // Configure ProductRepository.findAllByCategory_Id(...).
        final Product product1 = new Product();
        product1.setId(0L);
        product1.setName("name");
        final Category category1 = new Category();
        category1.setId(0);
        category1.setName("name");
        product1.setCategory(category1);
        product1.setPrice(0.0);
        product1.setWeight(0.0);
        product1.setDescription("description");
        product1.setImageName("imageName");
        final List<Product> products = Arrays.asList(product1);
        when(productServiceUnderTest.productRepository.findAllByCategory_Id(0)).thenReturn(products);

        // Run the test
        final List<Product> result = productServiceUnderTest.getAllProductsByCategoryId(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testGetAllProductsByCategoryId_ProductRepositoryReturnsNoItems() {
        // Setup
        final Product product = new Product();
        product.setId(0L);
        product.setName("name");
        final Category category = new Category();
        category.setId(0);
        category.setName("name");
        product.setCategory(category);
        product.setPrice(0.0);
        product.setWeight(0.0);
        product.setDescription("description");
        product.setImageName("imageName");
        final List<Product> expectedResult = Arrays.asList(product);
        when(productServiceUnderTest.productRepository.findAllByCategory_Id(0)).thenReturn(Collections.emptyList());

        // Run the test
        final List<Product> result = productServiceUnderTest.getAllProductsByCategoryId(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
