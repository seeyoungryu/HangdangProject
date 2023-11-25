package com.example.hangdang.controller;

import com.example.hangdang.dto.CommentRequestDto;
import com.example.hangdang.security.UserDetailsImpl;
import com.example.hangdang.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/goods/{goodsId}/comment")
    public ResponseEntity<String> registerComment(@PathVariable Long goodsId, @RequestBody CommentRequestDto commentRequestdto,
                                                  @AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        try{
            commentService.registerComment(goodsId, commentRequestdto, userDetails.getUser());
        } catch (Exception e){
            return new ResponseEntity<>("댓글을 등록하는 데에 실패하였습니다.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("댓글이 등록되었습니다.", HttpStatus.OK);
    }

    @DeleteMapping("/goods/{goodsId}/comment/{commentsId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long goodsId, @PathVariable Long commentsId,
                                              @AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        try{
            commentService.deleteComment(goodsId, commentsId, userDetails.getUser());
        } catch (Exception e){
            return new ResponseEntity<>("댓글을 삭제하는 데에 실패하였습니다.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("댓글이 삭제되었습니다.", HttpStatus.OK);
    }

    @PostMapping("/goods/{goodsId}/like")
    public ResponseEntity<String> likeGoods(@PathVariable Long goodsId, @AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        try{

        } catch (Exception e){
            return new ResponseEntity<>("좋아요를 누르는 데에 실패하였습니다.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("좋아요를 등록하였습니다.", HttpStatus.OK);
    }

    @DeleteMapping("/goods/{goodsId}/dislike")
    public ResponseEntity<String> undolikeGoods(@PathVariable Long goodsId, @AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        try{

        } catch (Exception e){
            return new ResponseEntity<>("좋아요를 취소하는 데에 실패하였습니다.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("좋아요를 취소하였습니다.", HttpStatus.OK);
    }
}
