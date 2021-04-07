package com.project.socialnews.service.posts;

import com.project.socialnews.domain.posts.Posts;
import com.project.socialnews.domain.posts.PostsRepository;
import com.project.socialnews.web.dto.PostsResponse;
import com.project.socialnews.web.dto.PostsSaveRequest;
import com.project.socialnews.web.dto.PostsUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public PostsResponse findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponse(entity);
    }

}
