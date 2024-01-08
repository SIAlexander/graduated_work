package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.CommentService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
@Tag(name = "Комментарии")
public class CommentController {

    private final CommentService commentService;
    private final CommentRepository commentRepository;

    @Operation(summary = "Получение комментариев объявления")
    @GetMapping("/{id}/comments")
    public ResponseEntity<CommentsDto> getCommentsAds(@PathVariable Integer id, Authentication authentication) {
        if (commentService.getCommentsAds(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else if (authentication.getName() != null) {
            return ResponseEntity.ok(commentService.getCommentsAds(id));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Operation(summary = "Добавление комментария к объявлению")
    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDto> setCommentAds(@PathVariable Integer id,
                                                    @RequestBody CreateOrUpdateCommentDto createCommentDto,
                                                    Authentication authentication) {
        if (authentication.getName() != null) {
            return ResponseEntity.ok(commentService.setCommentAds(id, createCommentDto, authentication));
        } else if (commentService.setCommentAds(id, createCommentDto, authentication) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Operation(summary = "Удаление комментария")
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Void> removeComment(@PathVariable Integer adId,
                                              @PathVariable Integer commentId,
                                              Authentication authentication) {
        if (authentication.getName() != null) {
            commentService.removeComment(adId, commentId);
            return ResponseEntity.ok().build();
        } else if (commentRepository.findByPk(commentId) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Operation(summary = "Обновление комментария")
    @Secured("ROLE_ADMIN")
    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Integer adId,
                                                    @PathVariable Integer commentId,
                                                    @RequestBody CreateOrUpdateCommentDto createCommentDto,
                                                    Authentication authentication) {
        if (authentication.getName() != null) {
            return ResponseEntity.ok(commentService.updateComment(adId, commentId, createCommentDto));
        } else if (commentService.updateComment(adId, commentId, createCommentDto) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
