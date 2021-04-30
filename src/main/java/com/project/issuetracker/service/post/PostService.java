package com.project.issuetracker.service.post;

import com.project.issuetracker.domain.post.Post;
import com.project.issuetracker.domain.post.PostRepository;
import com.project.issuetracker.web.dto.post.PostListResponse;
import com.project.issuetracker.web.dto.post.PostResponse;
import com.project.issuetracker.web.dto.post.PostSaveRequest;
import com.project.issuetracker.web.dto.post.PostUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Long save(PostSaveRequest request) {
        return postRepository.save(request.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostUpdateRequest request) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        post.update(request.getTitle(), request.getContent());

        return id;
    }

    @Transactional
    public PostResponse findById(Long id) {
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
    public void delete(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postRepository.delete(post);
    }
}
