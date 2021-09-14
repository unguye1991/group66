package com.group.major.controller;

import com.group.major.model.Category;
import com.group.major.model.Product;
import com.group.major.service.CategoryService;
import com.group.major.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService mockCategoryService;
    @MockBean
    private ProductService mockProductService;

    @Test
    public void testHome() throws Exception {
        // Setup

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/home")
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    public void testShop() throws Exception {
        // Setup

        // Configure CategoryService.getAllCategory(...).
        final Category category = new Category();
        category.setId(0);
        category.setName("name");
        final List<Category> categories = Arrays.asList(category);
        when(mockCategoryService.getAllCategory()).thenReturn(categories);

        // Configure ProductService.getAllProduct(...).
        final Product product = new Product();
        product.setId(0L);
        product.setName("name");
        final Category category1 = new Category();
        category1.setId(0);
        category1.setName("name");
        product.setCategory(category1);
        product.setPrice(0.0);
        product.setWeight(0.0);
        product.setDescription("description");
        product.setImageName("imageName");
        final List<Product> products = Arrays.asList(product);
        when(mockProductService.getAllProduct()).thenReturn(products);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/shop")
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    public void testShop_CategoryServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockCategoryService.getAllCategory()).thenReturn(Collections.emptyList());

        // Configure ProductService.getAllProduct(...).
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
        final List<Product> products = Arrays.asList(product);
        when(mockProductService.getAllProduct()).thenReturn(products);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/shop")
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    public void testShop_ProductServiceReturnsNoItems() throws Exception {
        // Setup

        // Configure CategoryService.getAllCategory(...).
        final Category category = new Category();
        category.setId(0);
        category.setName("name");
        final List<Category> categories = Arrays.asList(category);
        when(mockCategoryService.getAllCategory()).thenReturn(categories);

        when(mockProductService.getAllProduct()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/shop")
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    public void testShopByCategory() throws Exception {
        // Setup

        // Configure CategoryService.getAllCategory(...).
        final Category category = new Category();
        category.setId(0);
        category.setName("name");
        final List<Category> categories = Arrays.asList(category);
        when(mockCategoryService.getAllCategory()).thenReturn(categories);

        // Configure ProductService.getAllProductsByCategoryId(...).
        final Product product = new Product();
        product.setId(0L);
        product.setName("name");
        final Category category1 = new Category();
        category1.setId(0);
        category1.setName("name");
        product.setCategory(category1);
        product.setPrice(0.0);
        product.setWeight(0.0);
        product.setDescription("description");
        product.setImageName("imageName");
        final List<Product> products = Arrays.asList(product);
        when(mockProductService.getAllProductsByCategoryId(0)).thenReturn(products);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/shop/category/{id}", 0)
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    public void testShopByCategory_CategoryServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockCategoryService.getAllCategory()).thenReturn(Collections.emptyList());

        // Configure ProductService.getAllProductsByCategoryId(...).
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
        final List<Product> products = Arrays.asList(product);
        when(mockProductService.getAllProductsByCategoryId(0)).thenReturn(products);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/shop/category/{id}", 0)
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    public void testShopByCategory_ProductServiceReturnsNoItems() throws Exception {
        // Setup

        // Configure CategoryService.getAllCategory(...).
        final Category category = new Category();
        category.setId(0);
        category.setName("name");
        final List<Category> categories = Arrays.asList(category);
        when(mockCategoryService.getAllCategory()).thenReturn(categories);

        when(mockProductService.getAllProductsByCategoryId(0)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/shop/category/{id}", 0)
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    public void testViewProduct() throws Exception {
        // Setup

        // Configure ProductService.getProductById(...).
        final Product product1 = new Product();
        product1.setId(0L);
        product1.setName("name");
        final Category category = new Category();
        category.setId(0);
        category.setName("name");
        product1.setCategory(category);
        product1.setPrice(0.0);
        product1.setWeight(0.0);
        product1.setDescription("description");
        product1.setImageName("imageName");
        final Optional<Product> product = Optional.of(product1);
        when(mockProductService.getProductById(0L)).thenReturn(product);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/shop/viewproduct/{id}", 0)
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    public void testViewProduct_ProductServiceReturnsAbsent() throws Exception {
        // Setup
        when(mockProductService.getProductById(0L)).thenReturn(Optional.empty());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/shop/viewproduct/{id}", 0)
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
