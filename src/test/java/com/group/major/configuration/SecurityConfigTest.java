package com.group.major.configuration;

import com.group.major.service.CustomUserDetailService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

public class SecurityConfigTest {

    private SecurityConfig securityConfigUnderTest;

    @Before
    public void setUp() {
        securityConfigUnderTest = new SecurityConfig();
        securityConfigUnderTest.googleOAuth2SuccessHandler = mock(GoogleOAuth2SuccessHandler.class);
        securityConfigUnderTest.customUserDetailService = mock(CustomUserDetailService.class);
    }

    @Test
    public void testBCryptPasswordEncoder() {
        // Setup

        // Run the test
        final BCryptPasswordEncoder result = securityConfigUnderTest.bCryptPasswordEncoder();

        // Verify the results
    }

    @Test
    public void testConfigure3() throws Exception {
        // Setup
        final WebSecurity web = new WebSecurity(null);

        // Run the test
        securityConfigUnderTest.configure(web);

        // Verify the results
    }

    @Test
    public void testConfigure3_ThrowsException() {
        // Setup
        final WebSecurity web = new WebSecurity(null);

        // Run the test
        assertThatThrownBy(() -> securityConfigUnderTest.configure(web)).isInstanceOf(Exception.class);
    }

    @Test
    public void testConfigure1() throws Exception {
        // Setup
        final HttpSecurity http = new HttpSecurity(null, new AuthenticationManagerBuilder(null), new HashMap<>());

        // Run the test
        securityConfigUnderTest.configure(http);

        // Verify the results
    }

    @Test
    public void testConfigure1_ThrowsException() {
        // Setup
        final HttpSecurity http = new HttpSecurity(null, new AuthenticationManagerBuilder(null), new HashMap<>());

        // Run the test
        assertThatThrownBy(() -> securityConfigUnderTest.configure(http)).isInstanceOf(Exception.class);
    }

    @Test
    public void testConfigure2() throws Exception {
        // Setup
        final AuthenticationManagerBuilder auth = new AuthenticationManagerBuilder(null);

        // Run the test
        securityConfigUnderTest.configure(auth);

        // Verify the results
    }

    @Test
    public void testConfigure2_ThrowsException() {
        // Setup
        final AuthenticationManagerBuilder auth = new AuthenticationManagerBuilder(null);

        // Run the test
        assertThatThrownBy(() -> securityConfigUnderTest.configure(auth)).isInstanceOf(Exception.class);
    }
}
