package com.ebanking.service;

import com.ebanking.entity.Role;
import com.ebanking.entity.User;
import com.ebanking.repository.UserRepository;
import com.ebanking.service.impl.CustomUserDetailsService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
@NoArgsConstructor
public class CustomUserDetailsServiceTest {

    @InjectMocks
    private UserDetailsService service = new CustomUserDetailsService();

    @Mock
    private UserRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new CustomUserDetailsService();
    }

    @Test
    public void loadUserByUsernamePositive() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role((long) 1, "Admin"));
        User user = new User((long)1, "username", "password", roles);
        BDDMockito.given(repository.findOneByUsername(any(String.class))).willReturn(user);


        UserDetails userDetails = service.loadUserByUsername("username");
        assertNotNull(userDetails);
        assertEquals(user.getUsername(), userDetails.getUsername());
    }

    @Test
    public void loadUserByUsernameNegative() {
        BDDMockito.given(repository.findOneByUsername(any(String.class))).willReturn(null);
        assertThrows(UsernameNotFoundException.class, () -> service.loadUserByUsername("username"));
    }
}