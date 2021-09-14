package com.group.major.service;

import com.group.major.model.User;
import com.group.major.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomUserDetailServiceTest {

    private CustomUserDetailService customUserDetailServiceUnderTest;

    @Before
    public void setUp() throws Exception {
        customUserDetailServiceUnderTest = new CustomUserDetailService();
        customUserDetailServiceUnderTest.userRepository = mock(UserRepository.class);
    }

    @Test
    public void testLoadUserByUsername() {
        // Setup
        when(customUserDetailServiceUnderTest.userRepository.findUserByEmail("email")).thenReturn(Optional.of(new User()));

        // Run the test
        final UserDetails result = customUserDetailServiceUnderTest.loadUserByUsername("email");

        // Verify the results
    }

    @Test
    public void testLoadUserByUsername_UserRepositoryReturnsAbsent() {
        // Setup
        when(customUserDetailServiceUnderTest.userRepository.findUserByEmail("email")).thenReturn(Optional.empty());

        // Run the test
        final UserDetails result = customUserDetailServiceUnderTest.loadUserByUsername("email");

        // Verify the results
    }

    @Test
    public void testLoadUserByUsername_ThrowsUsernameNotFoundException() {
        // Setup
        when(customUserDetailServiceUnderTest.userRepository.findUserByEmail("email")).thenReturn(Optional.of(new User()));

        // Run the test
        assertThatThrownBy(() -> customUserDetailServiceUnderTest.loadUserByUsername("email")).isInstanceOf(UsernameNotFoundException.class);
    }
}
