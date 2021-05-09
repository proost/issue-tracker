package com.project.issuetracker.service.post;

import com.project.issuetracker.domain.account.Account;
import com.project.issuetracker.domain.account.AccountRepository;
import com.project.issuetracker.domain.post.Post;
import com.project.issuetracker.domain.post.PostRepository;
import com.project.issuetracker.web.dto.post.PostListResponse;
import com.project.issuetracker.web.dto.post.PostResponse;
import com.project.issuetracker.web.dto.post.PostSaveRequest;
import com.project.issuetracker.web.dto.post.PostUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public void save(final PostSaveRequest request, final long accountId) {
        final Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalStateException("세션이 만료되었습니다."));

        final Post post = Post.builder()
                .content(request.getContent())
                .title(request.getTitle())
                .author(account)
                .build();

        postRepository.save(post);
    }

    @Transactional
    public void update(final long id, final PostUpdateRequest request, final long requestAccountId) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        Assert.state(post.getAuthor().getId() == requestAccountId, "요청을 수행할 수 있는 권한이 없습니다.");

        post.update(request.getTitle(), request.getContent());
    }

    @Transactional
    public PostResponse findById(final Long id) {
        Post entity = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostResponse(entity);
    }

    @Transactional(readOnly = true)
    public List<PostListResponse> findAllDesc() {
        return postRepository.findAllDesc().stream()
                .map(PostListResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(final long id, final long requestAccountId) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        Assert.state(post.getAuthor().getId() == requestAccountId, "요청을 수행할 수 있는 권한이 없습니다.");

        postRepository.delete(post);
    }
}
