package com.fastcampus.web3.controller;
import com.fastcampus.web3.domain.PageHandler;
import com.fastcampus.web3.dto.BoardDTO;
import com.fastcampus.web3.service.BoardService;
import java.rmi.server.ExportException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    public String boardList(Integer currPage, Integer pageSize, HttpServletRequest request, HttpSession session, Model model) {
        // 세션 확인
        // 페이지 핸들러 계산
        // 에러 처리
        if (!isSession(session)) {
            return "redirect:/login/login?toURL="+request.getServletPath();
        }

        currPage = (currPage == null || currPage == 0) ? 1 : currPage;
        pageSize = (pageSize == null || pageSize == 0) ? 10 : pageSize;

        try {
            int totalCnt = boardService.getCount();
            System.out.println(currPage + ", " + pageSize);
            List<BoardDTO> list = boardService.getPage(currPage, pageSize);
            PageHandler ph = new PageHandler(currPage, totalCnt);
            model.addAttribute("ph", ph);
            model.addAttribute("list", list);
            return "/web3/boardList";
        } catch (Exception e) {
            // 예외 처리
        }
        return "/web3/boardList";
    }

    private boolean isSession(HttpSession session) {
        return "asdf".equals(session.getAttribute("id"));
    }


    @GetMapping("/read")
    public String read(Integer bno, Model model) throws Exception {
        try {
            BoardDTO boardDTO = boardService.read(bno);
            System.out.println(boardDTO.getView_cnt());
            model.addAttribute("boardDTO", boardDTO);
        } catch (Exception e) {
            // 예외 처리
        }

        return "/web3/boardDetail";
    }

    @GetMapping("/remove")
    public String remove(Integer bno) {
        try {
            int rowCnt = boardService.remove(bno);
            if (rowCnt != 1) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("삭제되지 않음");
        }

        return "redirect:/board/list";
    }

    @PostMapping("/remove")
    public String remove(BoardDTO boardDTO) {
        try {
            int rowCnt = boardService.remove(boardDTO.getBno());
            if (rowCnt != 1) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("삭제 실패");
        }

        return "redirect:/board/list";
    }


    @GetMapping("/write")
    public String writeForm(HttpServletRequest request, HttpSession session) {
        if (!isSession(session)) {
            System.out.println(request.getServletPath());
            return "redirect:/login/login?toURL="+request.getServletPath();
        }

        return "/web3/boardForm";
    }

    @PostMapping("/write")
    public String write(BoardDTO boardDTO) {
        try {
            int rowCnt = boardService.write(boardDTO);
            if (rowCnt != 1) {
                throw new Exception();
            }
        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
        }
        return "redirect:/board/list";
    }

    @GetMapping("/modify")
    public String modifyForm(Integer bno, Model model) {
        System.out.println("modifyPage");
        return "redirect:/board/list";
    }

    @PostMapping("/modify")
    public String modify(BoardDTO boardDTO) {
        try {
            int rowCnt = boardService.modify(boardDTO);
            if (rowCnt != 1) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("수정에 실패했습니다.");
        }
        return "redirect:/board/list";
    }
}
