package com.ebanking.service;

import com.ebanking.entity.User;
import com.ebanking.repository.UserRepository;
import com.ebanking.service.UserService;
import com.ebanking.service.impl.UserServiceImpl;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
@NoArgsConstructor
public class UserServiceTest {

    @InjectMocks
    protected UserService service = new UserServiceImpl();

    @Mock
    protected UserRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new UserServiceImpl();
    }

    @Test
    public void findAllPositive() {
        List<User> expected = new ArrayList<>();
        expected.add(new User());
        expected.add(new User());

        when(repository.findAll()).thenReturn(expected);

        Iterable<User> actual = service.findAll();
        Iterator<User> iterator = actual.iterator();

        assertEquals(expected.get(0), iterator.next());
        assertEquals(expected.get(1), iterator.next());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void findAllNegative() {
        Iterable<User> actual = service.findAll();

        assertNotNull(actual);

        verify(repository, times(1)).findAll();
    }

    @Test
    public void findPositive() {
        User expected = new User();

        when(repository.findOne((long) 1)).thenReturn(expected);

        User actual = service.find((long) 1);

        assertEquals(expected, actual);
        verify(repository, times(1)).findOne((long) 1);
    }

    @Test
    public void findNegative() {
        User actual = service.find((long) 1);

        assertNull(actual);
        verify(repository, times(1)).findOne((long) 1);
    }

    @Test
    public void savePositive() {
        User expected = new User();

        BDDMockito.given(repository.save(any(User.class))).willReturn(expected);

        User actual = service.save(expected);

        assertEquals(expected, actual);
        verify(repository, times(1)).save(expected);
    }

    @Test
    public void saveNegative(){
        User user = new User();
       User actual = service.save(user);
       assertNull(actual);
       verify(repository, times(1)).save(user);
    }

    @Test
    public void updatePositive() {
        User expected = new User();

        BDDMockito.given(repository.save(any(User.class))).willReturn(expected);

        User actual = service.save(expected);

        assertEquals(expected, actual);
        verify(repository, times(1)).save(expected);
    }

    @Test
    public void updateNegative() {
        User user = new User();
        User actual = service.save(user);
        assertNull(actual);
        verify(repository, times(1)).save(user);
    }

    @Test
    public void deletePositive() {
        ///TODO:
    }

    void bindRole() {
    }

    void deleteRole() {
    }
}