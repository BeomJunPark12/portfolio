package com.myportfolio.web.controller;

import com.myportfolio.web.domain.CommentDto;
import com.myportfolio.web.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class CommentController {
    @Autowired
    CommentService commentService;

    // 댓글을 수정하는 메서드
    @PatchMapping("/comments/{cno}")
    public ResponseEntity<String> modify(@PathVariable Integer cno, @RequestBody CommentDto commentDto) {
        commentDto.setCno(cno);
        System.out.println("cno = " + cno);

        try {
            if (commentService.modify(commentDto) != 1)
                throw new Exception("Modify failed");

            return new ResponseEntity<>("modify_ok", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("modify_error", HttpStatus.BAD_REQUEST);

        }

    }

        // 댓글을 등록하는 메서드
    @PostMapping("comments")
    public ResponseEntity<String> write(@RequestBody CommentDto commentDto, Integer bno, HttpSession session) {
//        String commenter = (String) session.getAttribute("id");
        String commenter = "asdf";
        commentDto.setComment(commenter);
        commentDto.setBno(bno);
        System.out.println("commentDto = " + commentDto);

        try {
            if (commentService.write(commentDto) != 1)
                throw new Exception("Write failed");

            return new ResponseEntity<>("write_ok", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("write_error", HttpStatus.BAD_REQUEST);

        }

    }
    // 지정된 댓글을 삭제하는 메서드
    @DeleteMapping("/comments/{cno}")   // comments/1 ?bno=1
    public ResponseEntity<String> remove(@PathVariable Integer cno, Integer bno, HttpSession session) {
//        String commenter = (String) session.getAttribute("id");
        String commenter = "asdf";
        try {
            int rowCnt = commentService.remove(cno, bno, commenter);

            if (rowCnt != 1)
                throw  new Exception("Delete Failed");

            return new ResponseEntity<>("delete_ok", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("delete_error", HttpStatus.BAD_REQUEST);

        }
    }

    // 지정된 모든 게시물의 댓글을 가져오는 메서드
    @GetMapping("/comments")
    public ResponseEntity<List<CommentDto>> list(Integer bno) {
        List<CommentDto> list = null;

        try {
            list = commentService.getList(bno);
            return new ResponseEntity<List<CommentDto>>(list, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<CommentDto>>(list, HttpStatus.BAD_REQUEST);
        }
    }
}
