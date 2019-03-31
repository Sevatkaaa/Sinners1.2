package com.sinners.controller;

import com.sinners.facade.SinFacade;
import com.sinners.sin.SinData;
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
public class SinControllerUnitTest {

    private static final String FILTER = "filter";
    private static final String SIN_TYPE = "sinType";
    private static final int SIN_WEIGHT = 6;
    private static final String SIN_DESCRIPTION = "sinDescription";
    private static final String USERNAME = "username";

    @InjectMocks
    private SinController controller;
    
    @Mock
    private SinFacade sinFacade;
    
    private UserData user;
    private SinData sinData;
    private List<SinData> sins;

    @Before
    public void setUp() {
        user = new UserData();
        user.setUsername(USERNAME);
        sinData = new SinData();
        sins = Collections.singletonList(sinData);
        when(sinFacade.getSinsForUser(USERNAME)).thenReturn(sins);
    }

    @Test
    public void shouldGetSins() {
        when(sinFacade.getSins(FILTER)).thenReturn(sins);

        List<SinData> actual = controller.getSins(FILTER);
        
        verify(sinFacade).getSins(FILTER);
        assertThat(actual).contains(sinData).hasSize(1);
    }
    
    @Test
    public void shouldAddSin() {
        controller.addSin(user, SIN_TYPE, SIN_WEIGHT, SIN_DESCRIPTION);
        
        verify(sinFacade).addSin(USERNAME, SIN_TYPE, SIN_WEIGHT, SIN_DESCRIPTION);
    }

    @Test
    public void shouldGetCurrentUserSins() {
        List<SinData> actual = controller.getCurrentUserSins(user);

        verify(sinFacade).getSinsForUser(USERNAME);
        assertThat(actual).contains(sinData).hasSize(1);
    }

    @Test
    public void shouldGetSinsForUser() {
        List<SinData> actual = controller.getSinsForUser(USERNAME);

        verify(sinFacade).getSinsForUser(USERNAME);
        assertThat(actual).contains(sinData).hasSize(1);
    }
}
