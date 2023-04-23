package com.myportfolio.web.controller;

import com.myportfolio.web.domain.BoardDto;
import com.myportfolio.web.domain.PageHandler;
import com.myportfolio.web.domain.SearchCondition;
import com.myportfolio.web.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;

    @PostMapping("/modify")
    public String modify(BoardDto boardDto, SearchCondition sc, HttpSession session, Model model, RedirectAttributes rattr) {

        String writer = (String) session.getAttribute("id");
        boardDto.setWriter(writer);

        try {
            int rowCnt = boardService.modify(boardDto);

            if (rowCnt !=1)
                throw new Exception("modify error");

            rattr.addFlashAttribute("msg", "modify_ok");

            return "redirect:/board/list" + sc.getQueryString();

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("boardDto", boardDto);
            model.addAttribute("msg", "modify_error");

            return "board/board";
        }
    }

    @PostMapping("/write")
    public String write(BoardDto boardDto, HttpSession session, Model model, RedirectAttributes rattr) {
        String writer = (String) session.getAttribute("id");
        boardDto.setWriter(writer);

        try {
            int rowCnt = boardService.write(boardDto);

            if (rowCnt !=1)
                throw new Exception("write error");

            rattr.addFlashAttribute("msg", "write_ok");

            return "redirect:/board/list";

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("boardDto", boardDto);
            model.addAttribute("msg", "write_error");

            return "board/board";
        }
    }

    @GetMapping("/write")
    public String write(Model model) {
        model.addAttribute("mode", "new");

        return "board/board"; // board.jsp를 읽기와 쓰기에 사용, mode가 new일 때 쓰기
    }

    @PostMapping("/remove")
    public String remove(Integer bno,  SearchCondition sc, Model model, RedirectAttributes rattr, HttpSession session) {

        String writer = (String) session.getAttribute("id");

        try {

            int rowCnt = boardService.remove(bno, writer);

            if (rowCnt != 1)
                throw new Exception("board remove error");

            rattr.addFlashAttribute("msg", "remove_ok");

        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "remove_error");
        }

        return "redirect:/board/list" + sc.getQueryString();
    }

    @GetMapping("/read")
    public String read(Integer bno,  SearchCondition sc,  Model model, RedirectAttributes rattr) {

        try {
            BoardDto boardDto = boardService.read(bno);

            model.addAttribute("boardDto", boardDto);
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "read_error");
            return "redirect:/board/list" + sc.getQueryString();
        }

        return "board/board";
    }

    @GetMapping("/list")
    public String list(SearchCondition sc, Model model, HttpServletRequest request) {

        if (!loginCheck(request))
            return "redirect:/login/login?toURL=" + request.getRequestURL();    // 로그인을 하지 않은채 게시판을 눌렀을 때 로그인 화면으로 이동


        try {
            int totalCnt = boardService.getSearchResultCnt(sc);

            PageHandler pageHandler = new PageHandler(totalCnt, sc);


            List<BoardDto> list = boardService.getSearchResultPage(sc);

            model.addAttribute("list", list);
            model.addAttribute("ph", pageHandler);
            model.addAttribute("totalCnt", totalCnt);

            Instant startOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
            model.addAttribute("startOfToday", startOfToday.toEpochMilli());

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", "list_error");
            model.addAttribute("totalCnt", 0);
        }

        return "board/boardList";   // 게시판으로 이동
    }

    private boolean loginCheck(HttpServletRequest request) {
        // 1. 세션을 얻어서
        HttpSession session = request.getSession();
        // 세션에 아이디가 있으면 true를 반환
        return session.getAttribute("id")!=null;
    }
}
