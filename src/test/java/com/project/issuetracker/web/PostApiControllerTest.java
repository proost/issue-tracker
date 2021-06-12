package com.project.issuetracker.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.issuetracker.domain.account.Account;
import com.project.issuetracker.domain.account.AccountRepository;
import com.project.issuetracker.domain.account.Role;
import com.project.issuetracker.domain.post.Post;
import com.project.issuetracker.domain.post.PostRepository;
import com.project.issuetracker.web.dto.post.PostSaveRequest;
import com.project.issuetracker.web.dto.post.PostUpdateRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AccountRepository accountRepository;

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

    @After
    public void tearDown() throws Exception {
        postRepository.deleteAll();
        accountRepository.deleteAll();
    }

    @WithMockUser(username = "user", password = "passwd")
    @Test
    public void enrollPosts() throws Exception {
        //given
        Account savedAccount = accountRepository.save(Account.builder()
                .email("tester@test.com")
                .password("test")
                .team("test")
                .name("tester")
                .role(Role.USER)
                .build()
        );

        String title = "title";
        String content = "content";
        PostSaveRequest request = PostSaveRequest.builder()
                                                    .title(title)
                                                    .content(content)
                                                    .build();

        session.setAttribute("accountId", savedAccount.getId());

        String url = "http://localhost:" + port + "/api/v1/posts";

        //when
        mvc.perform(post(url)
                .with(user("user").password("passwd"))
                .with(csrf())
                .session(session)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request))
        )
                .andExpect(status().isOk());

        //then
        List<Post> all = postRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @WithMockUser(username = "user", password = "passwd")
    @Test
    public void updatePosts() throws Exception {
        //given
        Account savedAccount = accountRepository.save(Account.builder()
                .email("tester@test.com")
                .password("test")
                .team("test")
                .name("tester")
                .role(Role.USER)
                .build()
        );

        Post savedPost = postRepository.save(
                Post.builder()
                .title("title")
                .content("content")
                .author(savedAccount)
                .build()
        );

        Long updateId = savedPost.getId();
        String expectedTitle = "title2";
        String expectedContent = "Content2";

        PostUpdateRequest request = PostUpdateRequest.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        session.setAttribute("accountId", savedAccount.getId());

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;

        HttpEntity<PostUpdateRequest> requestEntity = new HttpEntity<>(request);

        // when
        mvc.perform(put(url)
                .with(user("user").password("passwd"))
                .with(csrf())
                .session(session)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request))
        )
                .andExpect(status().isOk());


        // then
        List<Post> all = postRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
    }
}
