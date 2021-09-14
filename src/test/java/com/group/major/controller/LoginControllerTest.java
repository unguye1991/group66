package com.group.major.controller;

import com.group.major.model.Role;
import com.group.major.model.User;
import com.group.major.repository.RoleRepository;
import com.group.major.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(LoginController.class)
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BCryptPasswordEncoder mockBCryptPasswordEncoder;
    @MockBean
    private UserRepository mockUserRepository;
    @MockBean
    private RoleRepository mockRoleRepository;

    @Test
    public void testLogin() throws Exception {
        // Setup

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/login")
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    public void testRegisterGet() throws Exception {
        // Setup

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/register")
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    public void testRegisterPost() throws Exception {
        // Setup
        when(mockBCryptPasswordEncoder.encode("rawPassword")).thenReturn("result");

        // Configure RoleRepository.findById(...).
        final Role role1 = new Role();
        role1.setId(0);
        role1.setName("name");
        role1.setUsers(Arrays.asList(new User()));
        final Optional<Role> role = Optional.of(role1);
        when(mockRoleRepository.findById(0)).thenReturn(role);

        when(mockUserRepository.save(new User())).thenReturn(new User());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/register")
                        .param("user", "user")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockUserRepository).save(new User());
    }

    @Test
    public void testRegisterPost_RoleRepositoryReturnsAbsent() throws Exception {
        // Setup
        when(mockBCryptPasswordEncoder.encode("rawPassword")).thenReturn("result");
        when(mockRoleRepository.findById(0)).thenReturn(Optional.empty());
        when(mockUserRepository.save(new User())).thenReturn(new User());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/register")
                        .param("user", "user")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockUserRepository).save(new User());
    }

    @Test
    public void testRegisterPost_ThrowsServletException() throws Exception {
        // Setup
        when(mockBCryptPasswordEncoder.encode("rawPassword")).thenReturn("result");

        // Configure RoleRepository.findById(...).
        final Role role1 = new Role();
        role1.setId(0);
        role1.setName("name");
        role1.setUsers(Arrays.asList(new User()));
        final Optional<Role> role = Optional.of(role1);
        when(mockRoleRepository.findById(0)).thenReturn(role);

        when(mockUserRepository.save(new User())).thenReturn(new User());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/register")
                        .param("user", "user")
                        .with(csrf())
                        .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockUserRepository).save(new User());
    }
}
