package com.fastcampus.web3.service;

import com.fastcampus.web3.dao.BoardDaoImp;
import com.fastcampus.web3.dto.BoardDTO;
import java.util.List;
import javax.sql.DataSource;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class BoardServiceImpTest extends TestCase {
    @Autowired
    DataSource ds;
    @Autowired
    BoardServiceImp boardService;

    @Autowired
    BoardDaoImp boardDao;

    // 0. DI 테스트 ✅
    @Test
    public void testDSDI() {
        assertTrue(ds != null);
        assertTrue(ds instanceof DataSource);
    }

    @Test
    public void testBSDI() {
        assertTrue(boardService != null);
        assertTrue(boardService instanceof BoardServiceImp);
    }

    // 1. 핵심 기능 테스트
        // 1-0. getCount() : 전체 게시글 카운트 ✅
            // 1-0-0. 게시글이 없는 경우 -> 0
            // 1-0-1. 게시글이 1개인 경우 -> 1
            // 1-0-2. 게시글이 10개인 경우 -> 10
            // 1-0-3. 게시글이 100개인 경우 -> 100
            // 1-0-4. 게시글이 1000개인 경우 -> 1000

        // 1-1. remove() : 특정 번호 게시글 삭제 ✅
            // 1-1-0. 존재하지 않는 번호 게시글 삭제하는 경우 -> 0
            // 1-1-1. 게시글 1개, 해당 게시글 삭제 -> 1
            // 1-1-2. 게시글 10개
                // 1-1-2-0. 랜덤번째 게시글 삭제 -> 각 1, 내용 비교(5번 반복)
                // 1-1-2-1. 여러개 게시글 삭제 -> 각 1, 내용 비교(5번 반복)

            // 1-1-3. 게시글 100개
                // 1-1-3-0. 랜덤번째 게시글 삭제 -> 각 1, 내용 비교(5번 반복)
                // 1-1-3-1. 여러개 게시글 삭제 -> 각 1, 내용 비교(5번 반복)

            // 1-1-4. 게시글 1000개
                // 1-1-4-0. 랜덤번째 게시글 삭제 -> 각 1, 내용 비교(5번 반복)
                // 1-1-4-1. 여러개 게시글 삭제 -> 각 1, 내용 비교(5번 반복)

        // 1-2. write() : 게시글 등록  ✅
            // 1-2-0. 게시글을 null을 준 경우 -> Error
            // 1-2-1. 게시글 1개 등록하는 경우 -> 1
            // 1-2-2. 게시글 10개 등록하는 경우 -> 10
            // 1-2-3. 게시글 100개 등록하는 경우 -> 100
            // 1-2-4. 게시글 1000개 등록하는 경우 -> 1000

        // 1-3. getList() : 게시글(BoardDTO) 리스트로 가져옴 ✅
            // 1-3-0. 게시글이 없는 경우 -> 사이즈 0
            // 1-3-1. 게시글이 1개 -> 사이즈 1
            // 1-3-1. 게시글이 10개인 경우 -> 사이즈 10
            // 1-3-2. 게시글이 100개인 경우 -> 사이즈 100
            // 1-3-3. 게시글이 1000개인 경우 -> 사이즈 1000

        // 1-4. read() : 게시글 읽기 ✅
            // 1-4-0. 존재하지 않는 게시글 읽는 경우 -> null

            // 1-4-1. 게시글 10개
                // 1-4-1-0. 랜덤번째 게시글 읽는 경우 -> 내용 비교(5번 반복)
                // 1-4-1-1. 여러개 게시글 읽는 경우 -> 내용 비교

            // 1-4-2. 게시글이 100개
                // 1-4-2-0. 랜덤번째 게시글 읽는 경우 -> 내용 비교(5번 반복)
                // 1-4-2-1. 여러개 게시글 읽는 경우 -> 내용 비교

            // 1-4-3. 게시글이 1000개
                // 1-4-3-0. 랜덤번째 게시글 읽는 경우 -> 내용 비교(5번 반복)
                // 1-4-3-1. 여러개 게시글 읽는 경우 -> 내용 비교

        // 1-5. modify() : 게시글 수정 ✅
            // 1-5-0. 존재하지 않는 게시글 수정하는 경우 -> 0

            // 1-5-1. 게시글 10개
                // 1-5-1-0. 랜덤번째 게시글 수정 경우 -> 내용 비교(5번 반복)
                // 1-5-1-1. 여러개 게시글 수정 경우 -> 내용 비교

            // 1-5-2. 게시글이 100개
                // 1-5-2-0. 랜덤번째 게시글 수정 경우 -> 내용 비교(5번 반복)
                // 1-5-2-1. 여러개 게시글 수정 경우 -> 내용 비교

            // 1-5-3. 게시글이 1000개
                // 1-5-3-0. 랜덤번째 게시글 수정 경우 -> 내용 비교(5번 반복)
                // 1-5-3-1. 여러개 게시글 수정 경우 -> 내용 비교

    @Test
    public void getCountEmptyTest() throws Exception {
        // given & when : 테이블 비움
        // do : service통해서 테이블 로우수 계산
        // assert(compare) : 예측 로우수와 실제 로우수 비교
        cleanDB();
        int expectedTotalRowCnt = 0;

        int actualTotalRowCnt = boardService.getCount();

        assertTrue(expectedTotalRowCnt == actualTotalRowCnt);
    }

    @Test
    public void getCountOneDataTest() throws Exception {
        // given & when : 데이블 비우고 데이터 1개 생성
        // do : service통해서 테이블 로우수 계산
        // assert(compare) : 예측 로우수와 실제 로우수 비교
        cleanDB();
        int expectedTotalRowCnt = 1;
        BoardDTO createdBoard = createData(1);

        boardService.write(createdBoard);
        int actualTotalRowCnt = boardService.getCount();

        assertTrue(expectedTotalRowCnt == actualTotalRowCnt);
    }

    @Test
    public void getCountTenDataTest() throws Exception {
        // given & when : 데이블 비우고 데이터 10개 생성
        // do : service통해서 테이블 로우수 계산
        // assert(compare) : 예측 로우수와 실제 로우수 비교
        cleanDB();
        int expectedTotalRowCnt = 10;
        for (int i=1; i<=expectedTotalRowCnt; i++) {
            BoardDTO createdBoard = createData(i);
            boardService.write(createdBoard);
        }

        int actualTotalRowCnt = boardService.getCount();

        assertTrue(expectedTotalRowCnt == actualTotalRowCnt);
    }

    @Test
    public void getCountOneHundredDataTest() throws Exception {
        // given & when : 데이블 비우고 데이터 100개 생성
        // do : service통해서 테이블 로우수 계산
        // assert(compare) : 예측 로우수와 실제 로우수 비교
        cleanDB();
        int expectedTotalRowCnt = 100;
        for (int i=1; i<=expectedTotalRowCnt; i++) {
            BoardDTO createdBoard = createData(i);
            boardService.write(createdBoard);
        }

        int actualTotalRowCnt = boardService.getCount();

        assertTrue(expectedTotalRowCnt == actualTotalRowCnt);
    }
    @Test
    public void getCountOneThousandDataTest() throws Exception {
        // given & when : 데이블 비우고 데이터 1000개 생성
        // do : service통해서 테이블 로우수 계산
        // assert(compare) : 예측 로우수와 실제 로우수 비교
        cleanDB();
        int expectedTotalRowCnt = 1000;
        for (int i=1; i<=expectedTotalRowCnt; i++) {
            BoardDTO createdBoard = createData(i);
            boardService.write(createdBoard);
        }

        int actualTotalRowCnt = boardService.getCount();

        assertTrue(expectedTotalRowCnt == actualTotalRowCnt);
    }



    @Test(expected = Exception.class)
    public void writeEmptyDataTest() throws Exception {
        // given & when : 테이블 비우기
        // do : service 통해서 null 데이터 넣기
        // assert(compare) : 예외 발생
        cleanDB();
        boardService.write(null);
    }

    @Test
    public void writeOneDataTest() throws Exception {
        // given & when : 테이블 비우고 데이터 1개 생성
        // do : service를 통해 데이터 넣기
        // assert(compare) : 예측값과 실제값 비교
        cleanDB();
        BoardDTO createdBoard = createData(1);
        int expectedRowCnt = 1;
        int actualRowCnt = boardService.write(createdBoard);
        assertTrue(expectedRowCnt == actualRowCnt);
    }


    @Test
    public void writeTenDataTest() throws Exception {
        // given & when : 테이블 비우고 데이터 10개 생성
        // do : service를 통해 데이터 넣기
        // assert(compare) : 예측값과 실제값 비교
        cleanDB();
        int expectedRowCnt =1;
        int expectedTotalRowCnt =10;
        for (int i=1; i<=expectedTotalRowCnt; i++) {
            BoardDTO createdBoard = createData(i);
            int actualRowCnt = boardService.write(createdBoard);
            assertTrue(expectedRowCnt == actualRowCnt);
        }
        int actualTotalRowCnt = boardService.getCount();
        assertTrue(expectedTotalRowCnt == actualTotalRowCnt);
    }


    @Test
    public void writeOneHundredDataTest() throws Exception {
        // given & when : 테이블 비우고 데이터 100개 생성
        // do : service를 통해 데이터 넣기
        // assert(compare) : 예측값과 실제값 비교
        cleanDB();
        int expectedRowCnt =1;
        int expectedTotalRowCnt =100;
        for (int i=1; i<=expectedTotalRowCnt; i++) {
            BoardDTO createdBoard = createData(i);
            int actualRowCnt = boardService.write(createdBoard);
            assertTrue(expectedRowCnt == actualRowCnt);
        }
        int actualTotalRowCnt = boardService.getCount();
        assertTrue(expectedTotalRowCnt == actualTotalRowCnt);
    }

    @Test
    public void writeOneThousandDataTest() throws Exception {
        // given & when : 테이블 비우고 데이터 1000개 생성
        // do : service를 통해 데이터 넣기
        // assert(compare) : 예측값과 실제값 비교
        cleanDB();
        int expectedRowCnt =1;
        int expectedTotalRowCnt =1000;
        for (int i=1; i<=expectedTotalRowCnt; i++) {
            BoardDTO createdBoard = createData(i);
            int actualRowCnt = boardService.write(createdBoard);
            assertTrue(expectedRowCnt == actualRowCnt);
        }
        int actualTotalRowCnt = boardService.getCount();
        assertTrue(expectedTotalRowCnt == actualTotalRowCnt);
    }

    @Test
    public void getListEmptyTest() throws Exception {
        // given & when : 테이블 비움
        // do : service를 통해서 목록 조회
        // assert(compare) : 리스트 사이즈가 0인지 확인
        cleanDB();
        int expectedLength = 0;

        List<BoardDTO> boardDTOS = boardService.getList();
        int actualLength = boardDTOS.size();

        assertTrue(expectedLength == actualLength);
    }

    @Test
    public void getListOneDataTest() throws Exception {
        // given & when : 테이블 비우고 데이터 생성
        // do : 데이터 저장하고 service를 통해서 목록 조회
        // assert(compare) : 리스트 사이즈가 1인지 확인
        cleanDB();
        int expectedLength = 1;
        BoardDTO createdBoard = createData(1);
        boardService.write(createdBoard);

        List<BoardDTO> boardDTOS = boardService.getList();
        int actualLength = boardDTOS.size();

        assertTrue(expectedLength == actualLength);
    }

    @Test
    public void getListTenDataTest() throws Exception {
        // given & when : 테이블 비우고 데이터 생성
        // do : 데이터 저장하고 service를 통해서 목록 조회
        // assert(compare) : 리스트 사이즈가 10인지 확인
        cleanDB();
        int expectedLength = 10;
        for (int i=1; i<=expectedLength; i++) {
            BoardDTO createdBoard = createData(i);
            boardService.write(createdBoard);
        }

        List<BoardDTO> boardDTOS = boardService.getList();
        int actualLength = boardDTOS.size();

        assertTrue(expectedLength == actualLength);
    }

    @Test
    public void getListOneHundredDataTest() throws Exception {
        // given & when : 테이블 비우고 데이터 생성
        // do : 데이터 저장하고 service를 통해서 목록 조회
        // assert(compare) : 리스트 사이즈가 100인지 확인
        cleanDB();
        int expectedLength = 100;
        for (int i=1; i<=expectedLength; i++) {
            BoardDTO createdBoard = createData(i);
            boardService.write(createdBoard);
        }

        List<BoardDTO> boardDTOS = boardService.getList();
        int actualLength = boardDTOS.size();

        assertTrue(expectedLength == actualLength);
    }

    @Test
    public void getListOneThousandDataTest() throws Exception {
        // given & when : 테이블 비우고 데이터 생성
        // do : 데이터 저장하고 service를 통해서 목록 조회
        // assert(compare) : 리스트 사이즈가 1000인지 확인
        cleanDB();
        int expectedLength = 1000;
        for (int i=1; i<=expectedLength; i++) {
            BoardDTO createdBoard = createData(i);
            boardService.write(createdBoard);
        }

        List<BoardDTO> boardDTOS = boardService.getList();
        int actualLength = boardDTOS.size();

        assertTrue(expectedLength == actualLength);
    }

    @Test
    public void readEmptyDataTest() throws Exception {
        // given & when : 테이블 비움
        // do : service로 데이터 조회
        // assert(compare) : null인지 확인
        cleanDB();
        BoardDTO target = boardService.read(1);
        assertTrue(target == null);
    }

    @Test
    public void readOneDataTest() throws Exception {
        // given & when : 테이블 비우고 데이터 1개 생성 및 저장
        // do : service로 데이터 조회
        // assert(compare) : 서로 같은 데이터인지 확인
        cleanDB();
        BoardDTO createdData = createData(1);
        boardService.write(createdData);

        BoardDTO target = boardService.read(createdData.getBno());

        assertTrue(createdData.getWriter().equals(target.getWriter()));
        assertTrue(createdData.getTitle().equals(target.getTitle()));
        assertTrue(createdData.getContent().equals(target.getContent()));

    }

    @Test
    public void readTenDataTest() throws Exception {
        // given & when : 테이블 비우고 데이터 10개 생성 및 저장
        // do : service로 각 데이터 조회
        // assert(compare) : 각각 서로 같은 데이터인지 확인
        cleanDB();
        for (int i=1; i<=10; i++) {
            BoardDTO createdData = createData(i);
            boardService.write(createdData);

            BoardDTO target = boardService.read(createdData.getBno());

            assertTrue(createdData.getWriter().equals(target.getWriter()));
            assertTrue(createdData.getTitle().equals(target.getTitle()));
            assertTrue(createdData.getContent().equals(target.getContent()));
        }

    }

    @Test
    public void readTenDataTest2() throws Exception {
        // given & when : 테이블 비우고 데이터 10개 생성 및 저장
        // do : service로 각 데이터 조회
        // assert(compare) : 각각 서로 같은 데이터인지 확인
        cleanDB();
        for (int i=1; i<=10; i++) {
            BoardDTO createdData = createData(i);
            boardService.write(createdData);
        }

        for (int i=1; i<=5; i++) {
            int randomIdx = (int)(Math.random() * 10) + 1;
            BoardDTO createdData = createData(randomIdx);
            BoardDTO target = boardService.read(randomIdx);

            assertTrue(createdData.getWriter().equals(target.getWriter()));
            assertTrue(createdData.getTitle().equals(target.getTitle()));
            assertTrue(createdData.getContent().equals(target.getContent()));

        }

    }

    @Test
    public void readOneHundredDataTest() throws Exception {
        // given & when : 테이블 비우고 데이터 100개 생성 및 저장
        // do : service로 각 데이터 조회
        // assert(compare) : 각각 서로 같은 데이터인지 확인
        cleanDB();
        for (int i=1; i<=100; i++) {
            BoardDTO createdData = createData(i);
            boardService.write(createdData);

            BoardDTO target = boardService.read(createdData.getBno());

            assertTrue(createdData.getWriter().equals(target.getWriter()));
            assertTrue(createdData.getTitle().equals(target.getTitle()));
            assertTrue(createdData.getContent().equals(target.getContent()));
        }
    }

    @Test
    public void readOneHundredDataTest2() throws Exception {
        // given & when : 테이블 비우고 데이터 100개 생성 및 저장
        // do : service로 각 데이터 조회
        // assert(compare) : 각각 서로 같은 데이터인지 확인
        cleanDB();
        for (int i=1; i<=100; i++) {
            BoardDTO createdData = createData(i);
            boardService.write(createdData);
        }

        for (int i=1; i<=5; i++) {
            int randomIdx = (int)(Math.random() * 100) + 1;
            BoardDTO createdData = createData(randomIdx);
            BoardDTO target = boardService.read(randomIdx);

            assertTrue(createdData.getWriter().equals(target.getWriter()));
            assertTrue(createdData.getTitle().equals(target.getTitle()));
            assertTrue(createdData.getContent().equals(target.getContent()));

        }
    }

    @Test
    public void readOneThousandDataTest() throws Exception {
        // given & when : 테이블 비우고 데이터 1000개 생성 및 저장
        // do : service로 각 데이터 조회
        // assert(compare) : 각각 서로 같은 데이터인지 확인
        cleanDB();
        for (int i=1; i<=1000; i++) {
            BoardDTO createdData = createData(i);
            boardService.write(createdData);

            BoardDTO target = boardService.read(createdData.getBno());

            assertTrue(createdData.getWriter().equals(target.getWriter()));
            assertTrue(createdData.getTitle().equals(target.getTitle()));
            assertTrue(createdData.getContent().equals(target.getContent()));
        }
    }

    @Test
    public void readOneThousandDataTest2() throws Exception {
        // given & when : 테이블 비우고 데이터 1000개 생성 및 저장
        // do : service로 각 데이터 조회
        // assert(compare) : 각각 서로 같은 데이터인지 확인
        cleanDB();
        for (int i=1; i<=1000; i++) {
            BoardDTO createdData = createData(i);
            boardService.write(createdData);
        }

        for (int i=1; i<=5; i++) {
            int randomIdx = (int)(Math.random() * 1000) + 1;
            BoardDTO createdData = createData(randomIdx);
            BoardDTO target = boardService.read(randomIdx);

            assertTrue(createdData.getWriter().equals(target.getWriter()));
            assertTrue(createdData.getTitle().equals(target.getTitle()));
            assertTrue(createdData.getContent().equals(target.getContent()));

        }
    }

    @Test
    public void removeEmptyDataTest() throws Exception {
        // given & when : 테이블 비움
        // do : service를 통해서 데이터 삭제
        // assert(compare) : 0 확인
        cleanDB();
        int expectedRowCnt = 0;
        int actualRowCnt = boardService.remove(1);
        assertTrue(expectedRowCnt == actualRowCnt);

    }

    @Test
    public void removeOneDataTest() throws Exception {
        // given & when : 테이블 비움, 데이터 1개 생성 및 저장
        // do : service를 통해 데이터 삭제 및 조회
        // assert(compare) : 적용된 로우수 1, 전체 테이블 로우수 0인지 확인, 조회된 데이터 null 확인
        cleanDB();
        int expectedRowCnt = 1;
        BoardDTO createdData = createData(1);
        boardService.write(createdData);

        int actualRowCnt = boardService.remove(createdData.getBno());
        BoardDTO deletedData = boardService.read(createdData.getBno());

        assertTrue(expectedRowCnt == actualRowCnt);
        assertTrue(deletedData == null);
    }

    @Test
    public void removeTenDataTest() throws Exception {
        // given & when : 테이블 비움, 데이터 10개 생성 및 저장
        // do : service를 통해 데이터 모두 삭제 및 조회
        // assert(compare) : 각 적용된 로우수 1, 전체 테이블 로우수 0인지 확인, 조회된 데이터 null 확인
        cleanDB();
        int expectedRowCnt = 1;
        int expectedTotalRowCnt = 0;

        for (int i=1; i<=10; i++) {
            BoardDTO createdData = createData(i);
            boardService.write(createdData);

            int actualRowCnt = boardService.remove(createdData.getBno());
            BoardDTO deletedData = boardService.read(createdData.getBno());

            assertTrue(expectedRowCnt == actualRowCnt);
            assertTrue(deletedData == null);
        }

        int actualTotalRowCnt = boardService.getCount();
        assertTrue(expectedTotalRowCnt == actualTotalRowCnt);

    }

    @Test
    public void removeTenDataTest2() throws Exception {
        // given & when : 테이블 비움, 데이터 10개 생성 및 저장
        // do : service를 통해 랜덤 데이터 삭제 및 조회
        // assert(compare) : 각 적용된 로우수 1, 전체 테이블 로우수 -1인지 확인, 조회된 데이터 null 확인
        cleanDB();
        int expectedRowCnt = 1;

        for (int i=1; i<=10; i++) {
            BoardDTO createdData = createData(i);
            boardService.write(createdData);
        }

        for (int i=0; i<5; i++) {
            int randomIdx = (int)(Math.random() * 10) + 1;

            if (boardService.read(randomIdx) == null) continue;

            BoardDTO createdData = createData(randomIdx);
            int actualRowCnt = boardService.remove(createdData.getBno());
            BoardDTO target = boardService.read(createdData.getBno());
            assertTrue(expectedRowCnt == actualRowCnt);
            assertTrue(target == null);
        }
    }

    @Test
    public void removeOneHundredDataTest() throws Exception {
        // given & when : 테이블 비움, 데이터 100개 생성 및 저장
        // do : service를 통해 모든 데이터 삭제 및 조회
        // assert(compare) : 각 적용된 로우수 1, 전체 테이블 로우수 0 인지 확인, 조회된 데이터 null 확인
        cleanDB();
        int expectedRowCnt = 1;
        int expectedTotalRowCnt = 0;

        for (int i=1; i<=100; i++) {
            BoardDTO createdData = createData(i);
            boardService.write(createdData);

            int actualRowCnt = boardService.remove(createdData.getBno());
            BoardDTO deletedData = boardService.read(createdData.getBno());

            assertTrue(expectedRowCnt == actualRowCnt);
            assertTrue(deletedData == null);
        }

        int actualTotalRowCnt = boardService.getCount();
        assertTrue(expectedTotalRowCnt == actualTotalRowCnt);
    }

    @Test
    public void removeOneHundredDataTest2() throws Exception {
        // given & when : 테이블 비움, 데이터 100개 생성 및 저장
        // do : service를 통해 랜덤 데이터 삭제 및 조회
        // assert(compare) : 각 적용된 로우수 1, 전체 테이블 로우수 -1인지 확인, 조회된 데이터 null 확인
        cleanDB();
        int expectedRowCnt = 1;

        for (int i=1; i<=100; i++) {
            BoardDTO createdData = createData(i);
            boardService.write(createdData);
        }

        for (int i=0; i<5; i++) {
            int randomIdx = (int)(Math.random() * 100) + 1;

            if (boardService.read(randomIdx) == null) continue;

            BoardDTO createdData = createData(randomIdx);
            int actualRowCnt = boardService.remove(createdData.getBno());
            BoardDTO target = boardService.read(createdData.getBno());
            assertTrue(expectedRowCnt == actualRowCnt);
            assertTrue(target == null);
        }
    }

    @Test
    public void removeOneThousandDataTest() throws Exception {
        // given & when : 테이블 비움, 데이터 1000개 생성 및 저장
        // do : service를 통해 모든 데이터 삭제 및 조회
        // assert(compare) : 각 적용된 로우수 1, 전체 테이블 로우수 0 인지 확인, 조회된 데이터 null 확인
        cleanDB();
        int expectedRowCnt = 1;
        int expectedTotalRowCnt = 0;

        for (int i=1; i<=1000; i++) {
            BoardDTO createdData = createData(i);
            boardService.write(createdData);

            int actualRowCnt = boardService.remove(createdData.getBno());
            BoardDTO deletedData = boardService.read(createdData.getBno());

            assertTrue(expectedRowCnt == actualRowCnt);
            assertTrue(deletedData == null);
        }

        int actualTotalRowCnt = boardService.getCount();
        assertTrue(expectedTotalRowCnt == actualTotalRowCnt);
    }

    @Test
    public void removeOneThousandDataTest2() throws Exception {
        // given & when : 테이블 비움, 데이터 1000개 생성 및 저장
        // do : service를 통해 랜덤 데이터 삭제 및 조회
        // assert(compare) : 각 적용된 로우수 1, 전체 테이블 로우수 -1인지 확인, 조회된 데이터 null 확인
        cleanDB();
        int expectedRowCnt = 1;

        for (int i=1; i<=1000; i++) {
            BoardDTO createdData = createData(i);
            boardService.write(createdData);
        }

        for (int i=0; i<5; i++) {
            int randomIdx = (int)(Math.random() * 1000) + 1;

            if (boardService.read(randomIdx) == null) continue;

            BoardDTO createdData = createData(randomIdx);
            int actualRowCnt = boardService.remove(createdData.getBno());
            BoardDTO target = boardService.read(createdData.getBno());
            assertTrue(expectedRowCnt == actualRowCnt);
            assertTrue(target == null);
        }
    }

    @Test
    public void modifyEmptyDataTest() throws Exception {
        // given & when : 테이블 비움 수정용 데이터 생성
        // do :service 통해서 데이터 수정
        // assert(compare) : 0 확인
        cleanDB();
        BoardDTO updateData = createUpdateData(1);
        int expectedRowCnt = 0;

        int actualRowCnt = boardService.modify(updateData);

        assertTrue(expectedRowCnt == actualRowCnt);

    }

    @Test
    public void modifyOneDataTest() throws Exception {
        // given & when : 테이블 비움 데이터 생성(저장용, 수정용)
        // do : service 통해서 데이터 수정
        // assert(compare) : 조회된 데이터와 수정용 데이터 내용 비교
        cleanDB();
        BoardDTO createdData = createData(1);
        BoardDTO updateData = createUpdateData(createdData.getBno());
        int expectedRowCnt = 1;

        boardService.write(createdData);
        int actualRowCnt = boardService.modify(updateData);
        BoardDTO target = boardService.read(createdData.getBno());

        assertTrue(expectedRowCnt == actualRowCnt);

        assertTrue(updateData.getTitle().equals(target.getTitle()));
        assertTrue(updateData.getWriter().equals(target.getWriter()));
        assertTrue(updateData.getContent().equals(target.getContent()));

    }

    @Test
    public void modifyTenDataTest() throws Exception {
        // given & when : 테이블 비움, 데이터 생성(저장용 10개, 수정용)
        // do : service 통해서 모든 데이터 수정
        // assert(compare) : 조회된 데이터와 수정용 데이터 내용 비교

        cleanDB();
        for (int i=1; i<=10; i++) {
            BoardDTO createdData = createData(i);
            BoardDTO updateData = createUpdateData(createdData.getBno());
            int expectedRowCnt = 1;

            boardService.write(createdData);
            int actualRowCnt = boardService.modify(updateData);
            BoardDTO target = boardService.read(createdData.getBno());

            assertTrue(expectedRowCnt == actualRowCnt);

            assertTrue(updateData.getTitle().equals(target.getTitle()));
            assertTrue(updateData.getWriter().equals(target.getWriter()));
            assertTrue(updateData.getContent().equals(target.getContent()));
        }
    }

    @Test
    public void modifyTenDataTest2() throws Exception {
        // given & when : 테이블 비움, 데이터 생성(저장용 10개, 수저용)
        // do : service 통해서 랜덤 데이터 수정
        // assert(compare) : 조회된 데이터와 수정용 데이터 내용 비교
        cleanDB();
        int expectedRowCnt = 1;
        for (int i=1; i<=10; i++) {
            BoardDTO createdData = createData(i);
            boardService.write(createdData);
        }

        for (int i=0; i<5; i++) {
            int randomIdx = (int)(Math.random() * 10) + 1;
            BoardDTO updateData = createUpdateData(randomIdx);
            int actualRowCnt = boardService.modify(updateData);
            BoardDTO target = boardService.read(randomIdx);

            assertTrue(expectedRowCnt == actualRowCnt);

            assertTrue(updateData.getTitle().equals(target.getTitle()));
            assertTrue(updateData.getWriter().equals(target.getWriter()));
            assertTrue(updateData.getContent().equals(target.getContent()));

        }
    }

    @Test
    public void modifyOneHundredDataTest() throws Exception {
        // given & when : 테이블 비움, 데이터 생성(저장용 100개, 수정용)
        // do : service 통해서 모든 데이터 수정
        // assert(compare) : 조회된 데이터와 수정용 데이터 내용 비교

        cleanDB();
        for (int i=1; i<=100; i++) {
            BoardDTO createdData = createData(i);
            BoardDTO updateData = createUpdateData(createdData.getBno());
            int expectedRowCnt = 1;

            boardService.write(createdData);
            int actualRowCnt = boardService.modify(updateData);
            BoardDTO target = boardService.read(createdData.getBno());

            assertTrue(expectedRowCnt == actualRowCnt);

            assertTrue(updateData.getTitle().equals(target.getTitle()));
            assertTrue(updateData.getWriter().equals(target.getWriter()));
            assertTrue(updateData.getContent().equals(target.getContent()));
        }
    }

    @Test
    public void modifyOneHundredDataTest2() throws Exception {
        // given & when : 테이블 비움, 데이터 생성(저장용 100개, 수저용)
        // do : service 통해서 랜덤 데이터 수정
        // assert(compare) : 조회된 데이터와 수정용 데이터 내용 비교

        cleanDB();
        int expectedRowCnt = 1;
        for (int i=1; i<=100; i++) {
            BoardDTO createdData = createData(i);
            boardService.write(createdData);
        }

        for (int i=0; i<5; i++) {
            int randomIdx = (int)(Math.random() * 100) + 1;
            BoardDTO updateData = createUpdateData(randomIdx);
            int actualRowCnt = boardService.modify(updateData);
            BoardDTO target = boardService.read(randomIdx);

            assertTrue(expectedRowCnt == actualRowCnt);

            assertTrue(updateData.getTitle().equals(target.getTitle()));
            assertTrue(updateData.getWriter().equals(target.getWriter()));
            assertTrue(updateData.getContent().equals(target.getContent()));

        }
    }

    @Test
    public void modifyOneThousandDataTest() throws Exception {
        // given & when : 테이블 비움, 데이터 생성(저장용 1000개, 수정용)
        // do : service 통해서 모든 데이터 수정
        // assert(compare) : 조회된 데이터와 수정용 데이터 내용 비교

        cleanDB();
        for (int i=1; i<=1000; i++) {
            BoardDTO createdData = createData(i);
            BoardDTO updateData = createUpdateData(createdData.getBno());
            int expectedRowCnt = 1;

            boardService.write(createdData);
            int actualRowCnt = boardService.modify(updateData);
            BoardDTO target = boardService.read(createdData.getBno());

            assertTrue(expectedRowCnt == actualRowCnt);

            assertTrue(updateData.getTitle().equals(target.getTitle()));
            assertTrue(updateData.getWriter().equals(target.getWriter()));
            assertTrue(updateData.getContent().equals(target.getContent()));
        }
    }

    @Test
    public void modifyOneThousandDataTest2() throws Exception {
        // given & when : 테이블 비움, 데이터 생성(저장용 1000개, 수저용)
        // do : service 통해서 랜덤 데이터 수정
        // assert(compare) : 조회된 데이터와 수정용 데이터 내용 비교

        cleanDB();
        int expectedRowCnt = 1;
        for (int i=1; i<=1000; i++) {
            BoardDTO createdData = createData(i);
            boardService.write(createdData);
        }

        for (int i=0; i<5; i++) {
            int randomIdx = (int)(Math.random() * 1000) + 1;
            BoardDTO updateData = createUpdateData(randomIdx);

            int actualRowCnt = boardService.modify(updateData);
            BoardDTO target = boardService.read(randomIdx);

            assertTrue(expectedRowCnt == actualRowCnt);

            assertTrue(updateData.getTitle().equals(target.getTitle()));
            assertTrue(updateData.getWriter().equals(target.getWriter()));
            assertTrue(updateData.getContent().equals(target.getContent()));

        }
    }


    // 2. 보조 메서드
    private void cleanDB() throws Exception {
        boardDao.deleteAll();
    }

    private void insertData(int amount) throws Exception {
        for (int i=1; i<=amount; i++) {
            BoardDTO createdData = createData(i);
            boardDao.insert(createdData);
        }
    }

    private BoardDTO createData(int i) {
        BoardDTO createdBoard = new BoardDTO(i, "title1", "content1", "writer1", 1, 1, null, null);
        return createdBoard;
    }


    private BoardDTO createUpdateData(int i) {
        BoardDTO createdBoard = new BoardDTO(i, "title2", "content2", "writer2", 1, 1, null, null);
        return createdBoard;
    }

}