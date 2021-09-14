package com.group.major.model;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {

    private Product productUnderTest;

    @Before
    public void setUp() throws Exception {
        productUnderTest = new Product();
    }

    @Test
    public void testEquals() {
        // Setup

        // Run the test
        final boolean result = productUnderTest.equals("o");

        // Verify the results
        assertThat(result).isTrue();
    }

    @Test
    public void testHashCode() {
        // Setup

        // Run the test
        final int result = productUnderTest.hashCode();

        // Verify the results
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void testToString() {
        // Setup

        // Run the test
        final String result = productUnderTest.toString();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testCanEqual() {
        // Setup

        // Run the test
        final boolean result = productUnderTest.canEqual("other");

        // Verify the results
        assertThat(result).isTrue();
    }
}
