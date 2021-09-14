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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@WebMvcTest(AdminController.class)
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService mockCategoryService;
    @MockBean
    private ProductService mockProductService;

    @Test
    public void testAdminHome() throws Exception {
        // Setup

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/admin")
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    public void testGetCat() throws Exception {
        // Setup

        // Configure CategoryService.getAllCategory(...).
        final Category category = new Category();
        category.setId(0);
        category.setName("name");
        final List<Category> categories = Arrays.asList(category);
        when(mockCategoryService.getAllCategory()).thenReturn(categories);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/admin/categories")
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    public void testGetCat_CategoryServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockCategoryService.getAllCategory()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/admin/categories")
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    public void testGetCatAdd() throws Exception {
        // Setup

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/admin/categories/add")
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    public void testPostCatAdd() throws Exception {
        // Setup

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/admin/categories/add")
                        .param("category", "category")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockCategoryService).addCategory(new Category());
    }

    @Test
    public void testDeleteCat() throws Exception {
        // Setup

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/admin/categories/delete/{id}", 0)
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockCategoryService).removeCategoryById(0);
    }

    @Test
    public void testUpdateCat() throws Exception {
        // Setup

        // Configure CategoryService.getCategoryById(...).
        final Category category1 = new Category();
        category1.setId(0);
        category1.setName("name");
        final Optional<Category> category = Optional.of(category1);
        when(mockCategoryService.getCategoryById(0)).thenReturn(category);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/admin/categories/update/{id}", 0)
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    public void testUpdateCat_CategoryServiceReturnsAbsent() throws Exception {
        // Setup
        when(mockCategoryService.getCategoryById(0)).thenReturn(Optional.empty());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/admin/categories/update/{id}", 0)
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    public void testProduct() throws Exception {
        // Setup

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
        final MockHttpServletResponse response = mockMvc.perform(get("/admin/products")
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    public void testProduct_ProductServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockProductService.getAllProduct()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/admin/products")
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    public void testProductAddGet() throws Exception {
        // Setup

        // Configure CategoryService.getAllCategory(...).
        final Category category = new Category();
        category.setId(0);
        category.setName("name");
        final List<Category> categories = Arrays.asList(category);
        when(mockCategoryService.getAllCategory()).thenReturn(categories);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/admin/products/add")
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    public void testProductAddGet_CategoryServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockCategoryService.getAllCategory()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/admin/products/add")
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    public void testProductAddPost() throws Exception {
        // Setup

        // Configure CategoryService.getCategoryById(...).
        final Category category1 = new Category();
        category1.setId(0);
        category1.setName("name");
        final Optional<Category> category = Optional.of(category1);
        when(mockCategoryService.getCategoryById(0)).thenReturn(category);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(multipart("/admin/products/add")
                        .file(new MockMultipartFile("productImage", "originalFilename", MediaType.APPLICATION_JSON_VALUE, "content".getBytes()))
                        .param("imgName", "imgName")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockProductService).addProduct(new Product());
    }

    @Test
    public void testProductAddPost_CategoryServiceReturnsAbsent() throws Exception {
        // Setup
        when(mockCategoryService.getCategoryById(0)).thenReturn(Optional.empty());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(multipart("/admin/products/add")
                        .file(new MockMultipartFile("productImage", "originalFilename", MediaType.APPLICATION_JSON_VALUE, "content".getBytes()))
                        .param("imgName", "imgName")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockProductService).addProduct(new Product());
    }

    @Test
    public void testProductAddPost_ThrowsIOException() throws Exception {
        // Setup

        // Configure CategoryService.getCategoryById(...).
        final Category category1 = new Category();
        category1.setId(0);
        category1.setName("name");
        final Optional<Category> category = Optional.of(category1);
        when(mockCategoryService.getCategoryById(0)).thenReturn(category);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(multipart("/admin/products/add")
                        .file(new MockMultipartFile("productImage", "originalFilename", MediaType.APPLICATION_JSON_VALUE, "content".getBytes()))
                        .param("imgName", "imgName")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockProductService).addProduct(new Product());
    }

    @Test
    public void testDeleteProduct() throws Exception {
        // Setup

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/admin/product/delete/{id}", 0)
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockProductService).removeProductById(0L);
    }

    @Test
    public void testUpdateProductGet() throws Exception {
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

        // Configure CategoryService.getAllCategory(...).
        final Category category1 = new Category();
        category1.setId(0);
        category1.setName("name");
        final List<Category> categories = Arrays.asList(category1);
        when(mockCategoryService.getAllCategory()).thenReturn(categories);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/admin/product/update/{id}", 0)
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    public void testUpdateProductGet_ProductServiceReturnsAbsent() throws Exception {
        // Setup
        when(mockProductService.getProductById(0L)).thenReturn(Optional.empty());

        // Configure CategoryService.getAllCategory(...).
        final Category category = new Category();
        category.setId(0);
        category.setName("name");
        final List<Category> categories = Arrays.asList(category);
        when(mockCategoryService.getAllCategory()).thenReturn(categories);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/admin/product/update/{id}", 0)
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    public void testUpdateProductGet_CategoryServiceReturnsNoItems() throws Exception {
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

        when(mockCategoryService.getAllCategory()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/admin/product/update/{id}", 0)
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
