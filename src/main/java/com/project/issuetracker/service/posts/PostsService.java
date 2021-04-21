package com.project.issuetracker.service.posts;

import com.project.issuetracker.domain.posts.Posts;
import com.project.issuetracker.domain.posts.PostsRepository;
import com.project.issuetracker.web.dto.PostsListResponse;
import com.project.issuetracker.web.dto.PostsResponse;
import com.project.issuetracker.web.dto.PostsSaveRequest;
import com.project.issuetracker.web.dto.PostsUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequest request) {
        return postsRepository.save(request.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequest request) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        posts.update(request.getTitle(), request.getContent());

        return id;
    }

    @Transactional
    public PostsResponse findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponse(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponse> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts);
    }
}
