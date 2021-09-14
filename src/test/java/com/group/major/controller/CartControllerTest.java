package com.group.major.controller;

import com.group.major.model.Category;
import com.group.major.model.Product;
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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(CartController.class)
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService mockProductService;

    @Test
    public void testAddToCart() throws Exception {
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
        final MockHttpServletResponse response = mockMvc.perform(get("/addToCart/{id}", 0)
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    public void testAddToCart_ProductServiceReturnsAbsent() throws Exception {
        // Setup
        when(mockProductService.getProductById(0L)).thenReturn(Optional.empty());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/addToCart/{id}", 0)
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    public void testCartGet() throws Exception {
        // Setup

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/cart")
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    public void testCartItemRemove() throws Exception {
        // Setup

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/cart/removeItem/{index}", 0)
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    public void testCheckout() throws Exception {
        // Setup

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/checkout")
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
