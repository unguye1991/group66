package com.group.major.model;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoryTest {

    private Category categoryUnderTest;

    @Before
    public void setUp() {
        categoryUnderTest = new Category();
    }

    @Test
    public void testEquals() {
        // Setup

        // Run the test
        final boolean result = categoryUnderTest.equals("o");

        // Verify the results
        assertThat(result).isTrue();
    }

    @Test
    public void testHashCode() {
        // Setup

        // Run the test
        final int result = categoryUnderTest.hashCode();

        // Verify the results
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void testToString() {
        // Setup

        // Run the test
        final String result = categoryUnderTest.toString();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testCanEqual() {
        // Setup

        // Run the test
        final boolean result = categoryUnderTest.canEqual("other");

        // Verify the results
        assertThat(result).isTrue();
    }
}
