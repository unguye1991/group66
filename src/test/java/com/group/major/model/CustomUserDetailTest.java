package com.group.major.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CustomUserDetailTest {

    @Mock
    private User mockUser;

    private CustomUserDetail customUserDetailUnderTest;

    @Before
    public void setUp() throws Exception {
        customUserDetailUnderTest = new CustomUserDetail(mockUser);
    }

    @Test
    public void testGetAuthorities() {
        // Setup

        // Run the test
        final Collection<? extends GrantedAuthority> result = customUserDetailUnderTest.getAuthorities();

        // Verify the results
    }

    @Test
    public void testGetUsername() {
        // Setup

        // Run the test
        final String result = customUserDetailUnderTest.getUsername();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetPassword() {
        // Setup

        // Run the test
        final String result = customUserDetailUnderTest.getPassword();

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testIsAccountNonExpired() {
        // Setup

        // Run the test
        final boolean result = customUserDetailUnderTest.isAccountNonExpired();

        // Verify the results
        assertThat(result).isTrue();
    }

    @Test
    public void testIsAccountNonLocked() {
        // Setup

        // Run the test
        final boolean result = customUserDetailUnderTest.isAccountNonLocked();

        // Verify the results
        assertThat(result).isTrue();
    }

    @Test
    public void testIsCredentialsNonExpired() {
        // Setup

        // Run the test
        final boolean result = customUserDetailUnderTest.isCredentialsNonExpired();

        // Verify the results
        assertThat(result).isTrue();
    }

    @Test
    public void testIsEnabled() {
        // Setup

        // Run the test
        final boolean result = customUserDetailUnderTest.isEnabled();

        // Verify the results
        assertThat(result).isTrue();
    }
}
