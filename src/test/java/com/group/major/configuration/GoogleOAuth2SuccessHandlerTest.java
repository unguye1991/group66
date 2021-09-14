package com.group.major.configuration;

import com.group.major.model.Role;
import com.group.major.model.User;
import com.group.major.repository.RoleRepository;
import com.group.major.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class GoogleOAuth2SuccessHandlerTest {

    private GoogleOAuth2SuccessHandler googleOAuth2SuccessHandlerUnderTest;

    @Before
    public void setUp() {
        googleOAuth2SuccessHandlerUnderTest = new GoogleOAuth2SuccessHandler();
        googleOAuth2SuccessHandlerUnderTest.roleRepository = mock(RoleRepository.class);
        googleOAuth2SuccessHandlerUnderTest.userRepository = mock(UserRepository.class);
    }

    @Test
    public void testOnAuthenticationSuccess() throws Exception {
        // Setup
        final HttpServletRequest httpServletRequest = new MockHttpServletRequest();
        final HttpServletResponse httpServletResponse = new MockHttpServletResponse();
        final Authentication authentication = null;
        when(googleOAuth2SuccessHandlerUnderTest.userRepository.findUserByEmail("email")).thenReturn(Optional.of(new User()));

        // Configure RoleRepository.findById(...).
        final Role role1 = new Role();
        role1.setId(0);
        role1.setName("name");
        role1.setUsers(Arrays.asList(new User()));
        final Optional<Role> role = Optional.of(role1);
        when(googleOAuth2SuccessHandlerUnderTest.roleRepository.findById(0)).thenReturn(role);

        when(googleOAuth2SuccessHandlerUnderTest.userRepository.save(new User())).thenReturn(new User());

        // Run the test
        googleOAuth2SuccessHandlerUnderTest.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);

        // Verify the results
        verify(googleOAuth2SuccessHandlerUnderTest.userRepository).save(new User());
    }

    @Test
    public void testOnAuthenticationSuccess_UserRepositoryFindUserByEmailReturnsAbsent() throws Exception {
        // Setup
        final HttpServletRequest httpServletRequest = new MockHttpServletRequest();
        final HttpServletResponse httpServletResponse = new MockHttpServletResponse();
        final Authentication authentication = null;
        when(googleOAuth2SuccessHandlerUnderTest.userRepository.findUserByEmail("email")).thenReturn(Optional.empty());

        // Configure RoleRepository.findById(...).
        final Role role1 = new Role();
        role1.setId(0);
        role1.setName("name");
        role1.setUsers(Arrays.asList(new User()));
        final Optional<Role> role = Optional.of(role1);
        when(googleOAuth2SuccessHandlerUnderTest.roleRepository.findById(0)).thenReturn(role);

        when(googleOAuth2SuccessHandlerUnderTest.userRepository.save(new User())).thenReturn(new User());

        // Run the test
        googleOAuth2SuccessHandlerUnderTest.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);

        // Verify the results
        verify(googleOAuth2SuccessHandlerUnderTest.userRepository).save(new User());
    }

    @Test
    public void testOnAuthenticationSuccess_RoleRepositoryReturnsAbsent() throws Exception {
        // Setup
        final HttpServletRequest httpServletRequest = new MockHttpServletRequest();
        final HttpServletResponse httpServletResponse = new MockHttpServletResponse();
        final Authentication authentication = null;
        when(googleOAuth2SuccessHandlerUnderTest.userRepository.findUserByEmail("email")).thenReturn(Optional.of(new User()));
        when(googleOAuth2SuccessHandlerUnderTest.roleRepository.findById(0)).thenReturn(Optional.empty());
        when(googleOAuth2SuccessHandlerUnderTest.userRepository.save(new User())).thenReturn(new User());

        // Run the test
        googleOAuth2SuccessHandlerUnderTest.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);

        // Verify the results
        verify(googleOAuth2SuccessHandlerUnderTest.userRepository).save(new User());
    }

    @Test
    public void testOnAuthenticationSuccess_ThrowsIOException() {
        // Setup
        final HttpServletRequest httpServletRequest = new MockHttpServletRequest();
        final HttpServletResponse httpServletResponse = new MockHttpServletResponse();
        final Authentication authentication = null;
        when(googleOAuth2SuccessHandlerUnderTest.userRepository.findUserByEmail("email")).thenReturn(Optional.of(new User()));

        // Configure RoleRepository.findById(...).
        final Role role1 = new Role();
        role1.setId(0);
        role1.setName("name");
        role1.setUsers(Arrays.asList(new User()));
        final Optional<Role> role = Optional.of(role1);
        when(googleOAuth2SuccessHandlerUnderTest.roleRepository.findById(0)).thenReturn(role);

        when(googleOAuth2SuccessHandlerUnderTest.userRepository.save(new User())).thenReturn(new User());

        // Run the test
        assertThatThrownBy(() -> googleOAuth2SuccessHandlerUnderTest.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication)).isInstanceOf(IOException.class);
        verify(googleOAuth2SuccessHandlerUnderTest.userRepository).save(new User());
    }

    @Test
    public void testOnAuthenticationSuccess_ThrowsServletException() {
        // Setup
        final HttpServletRequest httpServletRequest = new MockHttpServletRequest();
        final HttpServletResponse httpServletResponse = new MockHttpServletResponse();
        final Authentication authentication = null;
        when(googleOAuth2SuccessHandlerUnderTest.userRepository.findUserByEmail("email")).thenReturn(Optional.of(new User()));

        // Configure RoleRepository.findById(...).
        final Role role1 = new Role();
        role1.setId(0);
        role1.setName("name");
        role1.setUsers(Arrays.asList(new User()));
        final Optional<Role> role = Optional.of(role1);
        when(googleOAuth2SuccessHandlerUnderTest.roleRepository.findById(0)).thenReturn(role);

        when(googleOAuth2SuccessHandlerUnderTest.userRepository.save(new User())).thenReturn(new User());

        // Run the test
        assertThatThrownBy(() -> googleOAuth2SuccessHandlerUnderTest.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication)).isInstanceOf(ServletException.class);
        verify(googleOAuth2SuccessHandlerUnderTest.userRepository).save(new User());
    }
}
