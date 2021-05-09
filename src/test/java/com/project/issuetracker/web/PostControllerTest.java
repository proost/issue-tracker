package com.project.issuetracker.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@ActiveProfiles("prod")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;
    private MockHttpSession session;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        session = new MockHttpSession();
        session.setAttribute("username", "tester");
    }

    @WithMockUser(username = "user", password = "passwd")
    @Test
    public void loadMain() throws Exception {
        //when
        String url = "http://localhost:" + port + "/posts";


        //then
        mvc.perform(get(url).with(user("user").password("passwd"))
                .session(session)
        )
                .andExpect(status().isOk())
                .andExpect(view().name("post/posts"));
    }


    @WithMockUser(username = "user", password = "passwd")
    @Test
    public void loadPostSave() throws Exception {
        //when
        String url = "http://localhost:" + port + "/posts/save";


        //then
        mvc.perform(get(url).with(user("user").password("passwd"))
                .session(session)
        )
                .andExpect(status().isOk())
                .andExpect(view().name("post/post-save"));
    }
}
