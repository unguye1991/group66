package com.group.major.dto;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductDTOTest {

    private ProductDTO productDTOUnderTest;

    @Before
    public void setUp() {
        productDTOUnderTest = new ProductDTO();
    }

    @Test
    public void testEquals() {
        // Setup

        // Run the test
        final boolean result = productDTOUnderTest.equals("o");

        // Verify the results
        assertThat(result).isTrue();
    }

    @Test
    public void testHashCode() {
        // Setup

        // Run the test
        final int result = productDTOUnderTest.hashCode();

        // Verify the results
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void testToString() {
        // Setup

        // Run the test
        final String result = productDTOUnderTest.toString();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testCanEqual() {
        // Setup

        // Run the test
        final boolean result = productDTOUnderTest.canEqual("other");

        // Verify the results
        assertThat(result).isTrue();
    }
}
