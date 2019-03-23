package com.sinners.controller;

import com.sinners.facade.UserFacade;
import com.sinners.user.UserData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerUnitTest {

    private static final String NAME = "name";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    private static final String CODE = "code";

    @InjectMocks
    private UserController controller;
    
    @Mock
    private UserFacade userFacade;
    
    private UserData userData;
    private List<UserData> users;

    @Before
    public void setUp() {
        userData = new UserData();
        users = Collections.singletonList(userData);
    }

    @Test
    public void shouldGetAllUsers() {
        when(userFacade.getAllUsers()).thenReturn(users);

        List<UserData> actual = controller.getAllUsers();
        
        verify(userFacade).getAllUsers();
        assertThat(actual).contains(userData).hasSize(1);
    }

    @Test
    public void shouldGetUser() {
        when(userFacade.getUserByName(NAME)).thenReturn(userData);

        UserData actual = controller.getUser(NAME);
        
        verify(userFacade).getUserByName(NAME);
        assertThat(actual).isEqualTo(userData);
    }

    @Test
    public void shouldAddUser() {
        controller.addUser(NAME, PASSWORD, PASSWORD, EMAIL);
        
        verify(userFacade).addUser(NAME, PASSWORD, PASSWORD, EMAIL);
    }

    @Test
    public void shouldActivateUser() {
        controller.activateUser(CODE);
        
        verify(userFacade).activateUser(CODE);
    }
}
