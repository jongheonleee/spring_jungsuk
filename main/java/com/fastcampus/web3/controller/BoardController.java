package com.fastcampus.web3.controller;


import com.fastcampus.web3.domain.PageHandler;
import com.fastcampus.web3.dto.BoardDTO;
import com.fastcampus.web3.service.BoardService;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    // read
        // board/read?bno=번호

    // 📌 remove
        // board/remove 여기서는 post 방식, 나는 get으로 구현함

    // write
        // board/write (GET) : 게시물 작성폼 보여줌
        // board/write (POST) : 게시물 저장

    // modify
        // board/modify?bno=번호 (GET) : 수정할 게시물 조회
        // board/modify (POST) :  게시물 수정
    @GetMapping("/list")
    public String boardList(Integer page, Integer pageSize, HttpServletRequest request, HttpSession session, Model model)
            throws Exception {
        // 세션 확인
        // 페이지 핸들러 계산
        // 에러 처리
        List<BoardDTO> list = boardService.getList();
        System.out.println(boardService.getCount());
        model.addAttribute("list", list);
        return "/web3/boardList";
    }


    @GetMapping("/read")
    public String read(Integer bno) {
        System.out.println("readPage");
        return "";
    }

    @GetMapping("/remove")
    public String remove(Integer bno) {
        System.out.println("removePage");
        return "";
    }

//    @PostMapping("/remove")
//    public String remove(BoardDTO boardDTO) {
//
//        return "";
//    }

    @GetMapping("/write")
    public String writeForm() {
        System.out.println("writeFormPage");
        return "/web3/boardForm";
    }

    @PostMapping("/write")
    public String write(BoardDTO boardDTO, Model model) {
        try {
            int rowCnt = boardService.write(boardDTO);
            System.out.println(rowCnt);
            System.out.println(boardDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/board/list";
    }

    @GetMapping("/modify")
    public String modifyForm(Integer bno) {
        System.out.println("modifyPage");
        return "";
    }

    @PostMapping("/modify")
    public String modify(BoardDTO boardDTO) {
        System.out.println("modifyPage2");
        return "";
    }
}
