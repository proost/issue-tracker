package com.project.issuetracker.web;

import com.project.issuetracker.domain.account.Account;
import com.project.issuetracker.domain.account.AccountRepository;
import com.project.issuetracker.domain.post.PostRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.*;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegistrationControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private AccountRepository accountRepository;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @After
    public void tearDown() throws Exception {
        accountRepository.deleteAll();
    }

    @Test
    public void testGetRegistration() throws Exception {
        // when
        String url = "http://localhost:" + port + "/registration";

        // then
        mvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(view().name("account/registration"));
    }

    @WithMockUser(username = "user", password = "passwd")
    @Test
    public void testRegisterAccount() throws Exception {
        // given
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("username", "tester");
        params.add("email", "tester@test.com");
        params.add("password", "test");
        params.add("teamName", "Test");

        // when
        String url = "http://localhost:" + port + "/registration";
        mvc.perform(
                    post("/registration")
                    .with(user("user").password("passwd")).with(csrf())
                    .params(params)
            ).andExpect(status().is3xxRedirection());

        // then
        Account account = accountRepository.findByEmail("tester@test.com").get();
        assertEquals("tester", account.getName());
    }
}
