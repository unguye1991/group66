package com.group.major.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {

    @Mock
    private User mockUser;

    private User userUnderTest;

    @Before
    public void setUp() throws Exception {
        userUnderTest = new User(mockUser);
    }

    @Test
    public void testEquals() {
        // Setup

        // Run the test
        final boolean result = userUnderTest.equals("o");

        // Verify the results
        assertThat(result).isTrue();
    }

    @Test
    public void testHashCode() {
        // Setup

        // Run the test
        final int result = userUnderTest.hashCode();

        // Verify the results
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void testToString() {
        // Setup

        // Run the test
        final String result = userUnderTest.toString();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testCanEqual() {
        // Setup

        // Run the test
        final boolean result = userUnderTest.canEqual("other");

        // Verify the results
        assertThat(result).isTrue();
    }
}
