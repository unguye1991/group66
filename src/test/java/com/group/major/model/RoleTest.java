package com.group.major.model;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RoleTest {

    private Role roleUnderTest;

    @Before
    public void setUp() throws Exception {
        roleUnderTest = new Role();
    }

    @Test
    public void testEquals() {
        // Setup

        // Run the test
        final boolean result = roleUnderTest.equals("o");

        // Verify the results
        assertThat(result).isTrue();
    }

    @Test
    public void testHashCode() {
        // Setup

        // Run the test
        final int result = roleUnderTest.hashCode();

        // Verify the results
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void testToString() {
        // Setup

        // Run the test
        final String result = roleUnderTest.toString();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testCanEqual() {
        // Setup

        // Run the test
        final boolean result = roleUnderTest.canEqual("other");

        // Verify the results
        assertThat(result).isTrue();
    }
}
