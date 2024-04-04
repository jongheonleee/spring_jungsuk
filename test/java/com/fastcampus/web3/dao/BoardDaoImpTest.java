package com.fastcampus.web3.dao;

import com.fastcampus.web3.dto.BoardDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class BoardDaoImpTest extends TestCase {
    @Autowired
    DataSource ds;

    @Autowired
    BoardDaoImp boardDao;

    // 0. 초기 테스트 ✅
        // 0-1. 데이타 소스 DI
        // 0-2. UserDaoImp DI
    @Test
    public void checkDsDI() {
        assertTrue(ds != null);
        assertTrue(ds instanceof DataSource);
    }

    @Test
    public void checkDaoDI() {
        assertTrue(boardDao != null);
        assertTrue(boardDao instanceof BoardDaoImp);
    }

    // 1. 핵심 기능 테스트(CRUD)
        // 📌 테스트 범위 확장 -> 실패 시나리오도 테스트, 현재 테스트가 너무 일반적임, 즉 100개 추가하면 34번째 ,,, 랜덤번째 확인하는 테스트도 추가

        // 1-0. count ✅
            // 1-0-1. 데이터 1개 넣고 로우수 카운트
            // 1-0-2. 데이터 10개 넣고 로우수 카운트
            // 1-0-3. 데이터 100개 넣고 로우수 카운트
            // 1-0-4. 데이터 1000개 넣고 로우수 카운트

        // 1-1. insert ✅
            // 1-1-1. 데이터 1개 넣고 각 로우수 확인
            // 1-1-2. 데이터 10개 넣고 각 로우수 확인
            // 1-1-3. 데이터 100개 넣고 각 로우수 확인
            // 1-1-4. 데이터 1000개 넣고 각 로우수 확인

        // 1-2. select ✅
            // 1-2-0. 없는 데이터 조회
            // 1-2-1. 데이터 1개 넣고 1개 조회
            // 1-2-2. 데이터 10개 넣고 10개 조회
            // 1-2-3. 데이터 100개 넣고 100개 조회
            // 1-2-4. 데이터 1000개 넣고 1000개 조회

        // 1-3. select all ✅
            // 1-3-0. 없는 데이터 조회
            // 1-3-1. 데이터 1개 넣고 모두 조회
            // 1-3-2. 데이터 10개 넣고 모두 조회
            // 1-3-3. 데이터 100개 넣고 모두 조회
            // 1-3-4. 데이터 1000개 넣고 모두 조회

        // 1-4. update ✅
            // 1-4-0. 없는 데이터 가져와서 변경
            // 1-4-1. 테이블 데이터 1개, 1개만 변경
            // 1-4-2. 테이블 데이터 10개, 10개 변경
            // 1-4-3. 테이블 데이터 100개, 100개 변경
            // 1-4-4. 테이블 데이터 1000개, 1000개 변경

        // 1-5. delete ✅
            // 1-5-0. 없는 데이터 가져와서 삭제
            // 1-5-1. 테이블 데이터 1개, 10개 삭제
            // 1-5-2. 테이블 데이터 10개, 100개 삭제
            // 1-5-3. 테이블 데이터 100개, 1000개 삭제
            // 1-5-4. 테이블 데이터 1000개, 1000개 삭제

        // 1-6. delete all ✅
            // 1-6-0. 없는 데이터 가져와서 삭제
            // 1-6-1. 테이블 데이터 1개, 모두 삭제, 로우수 카운트
            // 1-6-2. 테이블 데이터 10개, 모두 삭제, 로우수 카운트
            // 1-6-3. 테이블 데이터 100개, 모두 삭제, 로우수 카운트
            // 1-6-4. 테이블 데이터 1000개, 모두 삭제, 로우수 카운트
    @Test
    public void countOneData() throws Exception {
        // given & when : 테이블 초기화 및 데이터 생성
        cleanDB();
        BoardDTO createdBoard = createTestData(1);
        int expectedRowCnt = 1;

        // do : 데이터 저장 및 로우수 카운트
        boardDao.insert(createdBoard);
        int actualRowCnt = boardDao.count();

        // assert(compare) : 기대값과 실제값(카운트된 로우수) 비교
        assertTrue(expectedRowCnt == actualRowCnt);

    }

    @Test
    public void countTenData() throws Exception {
        // given & when : 테이블 초기화 및 데이터 생성
        cleanDB();
        int expectedRowCnt = 10;

        // do : 데이터 저장 및 로우수 카운트
        for (int i=1; i<=expectedRowCnt; i++) {
            BoardDTO createdBoard = createTestData(i);
            boardDao.insert(createdBoard);
        }
        int actualRowCnt = boardDao.count();

        // assert(compare) : 기대값과 실제값(카운트된 로우수) 비교
        assertTrue(expectedRowCnt == actualRowCnt);

    }


    @Test
    public void countOneHundredData() throws Exception {
        // given & when : 테이블 초기화 및 데이터 생성
        cleanDB();
        int expectedRowCnt = 100;

        // do : 데이터 저장 및 로우수 카운트
        for (int i=1; i<=expectedRowCnt; i++) {
            BoardDTO createdBoard = createTestData(i);
            boardDao.insert(createdBoard);
        }
        int actualRowCnt = boardDao.count();

        // assert(compare) : 기대값과 실제값(카운트된 로우수) 비교
        assertTrue(expectedRowCnt == actualRowCnt);

    }

    @Test
    public void countOneThousandData() throws Exception {
        // given & when : 테이블 초기화 및 데이터 생성
        cleanDB();
        int expectedRowCnt = 1000;

        // do : 데이터 저장 및 로우수 카운트
        for (int i=1; i<=expectedRowCnt; i++) {
            BoardDTO createdBoard = createTestData(i);
            boardDao.insert(createdBoard);
        }
        int actualRowCnt = boardDao.count();

        // assert(compare) : 기대값과 실제값(카운트된 로우수) 비교
        assertTrue(expectedRowCnt == actualRowCnt);

    }

    @Test
    public void selectOneDataTest() throws Exception {
        // given & when : 테이블 초기화 및 데이터 생성
        cleanDB();
        BoardDTO createdBoard = createTestData(1);
        int expectedRowCnt = 1;

        // do : 데이터 저장 및 반영된 로우수 카운트
        int actualRowCnt = boardDao.insert(createdBoard);
        BoardDTO selectedBoard = boardDao.select(createdBoard.getBno());

        // assert(compare) : 기대값과 결과값 비교
        assertTrue(expectedRowCnt == actualRowCnt);

        assertTrue(createdBoard.getTitle().equals(selectedBoard.getTitle()));
        assertTrue(createdBoard.getWriter().equals(selectedBoard.getWriter()));
        assertTrue(createdBoard.getContent().equals(selectedBoard.getContent()));


    }

    @Test
    public void selectTenDataTest() throws Exception {
        // given & when : 테이블 초기화 및 데이터 생성
        // do : 데이터 저장 및 반영된 로우수 카운트
        // assert(compare) : 기대값과 결과값 비교
            // - 각 반영된 로우수 확인
            // - 전체 로우수 비교
        cleanDB();
        int expectedRowCnt = 1;
        int expectedTotalRowCnt = 10;

        for (int i=1; i<=expectedTotalRowCnt; i++) {
            BoardDTO createdBoard = createTestData(i);
            int actualRowCnt = boardDao.insert(createdBoard);
            BoardDTO selectedBoard = boardDao.select(createdBoard.getBno());

            assertTrue(expectedRowCnt == actualRowCnt);

            assertTrue(createdBoard.getTitle().equals(selectedBoard.getTitle()));
            assertTrue(createdBoard.getWriter().equals(selectedBoard.getWriter()));
            assertTrue(createdBoard.getContent().equals(selectedBoard.getContent()));
        }

        int actualTotalRowCnt = boardDao.count();
        assertTrue(expectedTotalRowCnt == actualTotalRowCnt);
    }


    @Test
    public void selectOneHundredDataTest() throws Exception {
        // given & when : 테이블 초기화 및 데이터 생성
        // do : 데이터 저장 및 반영된 로우수 카운트
        // assert(compare) : 기대값과 결과값 비교
            // - 각 반영된 로우수 확인
            // - 전체 로우수 비교
        cleanDB();
        int expectedRowCnt = 1;
        int expectedTotalRowCnt = 100;

        for (int i=1; i<=expectedTotalRowCnt; i++) {
            BoardDTO createdBoard = createTestData(i);
            int actualRowCnt = boardDao.insert(createdBoard);
            BoardDTO selectedBoard = boardDao.select(createdBoard.getBno());

            assertTrue(expectedRowCnt == actualRowCnt);

            assertTrue(createdBoard.getTitle().equals(selectedBoard.getTitle()));
            assertTrue(createdBoard.getWriter().equals(selectedBoard.getWriter()));
            assertTrue(createdBoard.getContent().equals(selectedBoard.getContent()));
        }

        int actualTotalRowCnt = boardDao.count();
        assertTrue(expectedTotalRowCnt == actualTotalRowCnt);
    }

    @Test
    public void selectOneThousandDataTest() throws Exception {
        // given & when : 테이블 초기화 및 데이터 생성
        // do : 데이터 저장 및 반영된 로우수 카운트
        // assert(compare) : 기대값과 결과값 비교
            // - 각 반영된 로우수 확인
            // - 전체 로우수 비교
        cleanDB();
        int expectedRowCnt = 1;
        int expectedTotalRowCnt = 1000;

        for (int i=1; i<=expectedTotalRowCnt; i++) {
            BoardDTO createdBoard = createTestData(i);
            int actualRowCnt = boardDao.insert(createdBoard);
            BoardDTO selectedBoard = boardDao.select(createdBoard.getBno());

            assertTrue(expectedRowCnt == actualRowCnt);

            assertTrue(createdBoard.getTitle().equals(selectedBoard.getTitle()));
            assertTrue(createdBoard.getWriter().equals(selectedBoard.getWriter()));
            assertTrue(createdBoard.getContent().equals(selectedBoard.getContent()));
        }

        int actualTotalRowCnt = boardDao.count();
        assertTrue(expectedTotalRowCnt == actualTotalRowCnt);
    }

    @Test
    public void insertOneDataTest() throws Exception {
        // given & when : 테이블 초기화 및 데이터 생성
        cleanDB();
        BoardDTO createdBoard = createTestData(1);
        int expectedRowCnt = 1;

        // do : 데이터 저장 및 적용된 로우수 카운트
        int actualRowCnt = boardDao.insert(createdBoard);

        // assert(compare) : 기대값과 결과값 비교
        assertTrue(expectedRowCnt == actualRowCnt);

    }

    @Test
    public void insertTenDataTest() throws Exception {
        // given & when : 테이블 초기화 및 데이터 생성
        // do : 데이터 저장 및 적용된 로우수 카운트
        // assert(compare) : 기대값과 결과값 비교
        cleanDB();
        int expectedRowCnt = 1;
        int expectedTotalRowCnt = 10;

        for (int i=1; i<=expectedTotalRowCnt; i++) {
            BoardDTO createdBoard = createTestData(i);
            int actualRowCnt = boardDao.insert(createdBoard);
            assertTrue(expectedRowCnt == actualRowCnt);
        }

        int actualTotalRowCnt = boardDao.count();
        assertTrue(expectedTotalRowCnt == actualTotalRowCnt);
    }

    @Test
    public void insertOneHundredDataTest() throws Exception {
        // given & when : 테이블 초기화 및 데이터 생성
        // do : 데이터 저장 및 적용된 로우수 카운트
        // assert(compare) : 기대값과 결과값 비교
        cleanDB();
        int expectedRowCnt = 1;
        int expectedTotalRowCnt = 100;

        for (int i=1; i<=expectedTotalRowCnt; i++) {
            BoardDTO createdBoard = createTestData(i);
            int actualRowCnt = boardDao.insert(createdBoard);
            assertTrue(expectedRowCnt == actualRowCnt);
        }

        int actualTotalRowCnt = boardDao.count();
        assertTrue(expectedTotalRowCnt == actualTotalRowCnt);
    }

    @Test
    public void insertOneThousandDataTest() throws Exception {
        // given & when : 테이블 초기화 및 데이터 생성
        // do : 데이터 저장 및 적용된 로우수 카운트
        // assert(compare) : 기대값과 결과값 비교
        cleanDB();
        int expectedRowCnt = 1;
        int expectedTotalRowCnt = 1000;

        for (int i=1; i<=expectedTotalRowCnt; i++) {
            BoardDTO createdBoard = createTestData(i);
            int actualRowCnt = boardDao.insert(createdBoard);
            assertTrue(expectedRowCnt == actualRowCnt);
        }

        int actualTotalRowCnt = boardDao.count();
        assertTrue(expectedTotalRowCnt == actualTotalRowCnt);
    }

    @Test
    public void selectAllForOneData() throws Exception {
        // given & when : 테이블 초기화 및 데이터 생성
        cleanDB();
        int expectedLength = 1;

        // do : 데이터 저장 및 모든 데이터 조회
        BoardDTO createdBoard = createTestData(1);
        boardDao.insert(createdBoard);
        List<BoardDTO> boardDTOS = boardDao.selectAll();
        int actualLength = boardDTOS.size();

        // assert(compare) : 기대값과 결과값 비교
        assertTrue(expectedLength == actualLength);

    }

    @Test
    public void selectAllForTenData() throws Exception {
        // given & when : 테이블 초기화 및 데이터 생성
        // do : 데이터 저장 및 모든 데이터 조회
        // assert(compare) : 기대값과 결과값 비교
        cleanDB();
        int expectedLength = 10;

        for (int i=1; i<=expectedLength; i++) {
            BoardDTO createdBoard = createTestData(i);
            boardDao.insert(createdBoard);
        }

        List<BoardDTO> boardDTOS = boardDao.selectAll();
        int actualLength = boardDTOS.size();
        assertTrue(expectedLength == actualLength);
    }

    @Test
    public void selectAllForOneHundredData() throws Exception {
        // given & when : 테이블 초기화 및 데이터 생성
        // do : 데이터 저장 및 모든 데이터 조회
        // assert(compare) : 기대값과 결과값 비교
        cleanDB();
        int expectedLength = 100;

        for (int i=1; i<=expectedLength; i++) {
            BoardDTO createdBoard = createTestData(i);
            boardDao.insert(createdBoard);
        }

        List<BoardDTO> boardDTOS = boardDao.selectAll();
        int actualLength = boardDTOS.size();
        assertTrue(expectedLength == actualLength);
    }

    @Test
    public void selectAllForOneThousandData() throws Exception {
        // given & when : 테이블 초기화 및 데이터 생성
        // do : 데이터 저장 및 모든 데이터 조회
        // assert(compare) : 기대값과 결과값 비교
        cleanDB();
        int expectedLength = 1000;

        for (int i=1; i<=expectedLength; i++) {
            BoardDTO createdBoard = createTestData(i);
            boardDao.insert(createdBoard);
        }

        List<BoardDTO> boardDTOS = boardDao.selectAll();
        int actualLength = boardDTOS.size();
        assertTrue(expectedLength == actualLength);
    }

    @Test
    public void updateOneDataTest() throws Exception {
        // given & when : 테이블 초기화 및 데이터 생성(저장용, 수정용)
        cleanDB();
        BoardDTO createdBoard = createTestData(1);
        BoardDTO createdBoardForUpdate = createUpdateTestData(1);

        // do : 데이터 저장 및 저장된 데이터 수정
        boardDao.insert(createdBoard);
        boardDao.update(createdBoardForUpdate);
        BoardDTO selectedBoard = boardDao.select(createdBoard.getBno());

        // assert(compare) : 수정용 데이터와 저장된 데이터 비교
        assertTrue(createdBoardForUpdate.getTitle().equals(selectedBoard.getTitle()));
        assertTrue(createdBoardForUpdate.getContent().equals(selectedBoard.getContent()));
        assertTrue(createdBoardForUpdate.getWriter().equals(selectedBoard.getWriter()));
    }

    @Test
    public void updateTenDataTest() throws Exception {
        // given & when : 테이블 초기화 및 데이터 생성(저장용, 수정용)
        // do : 데이터 저장 및 저장된 데이터 수정
        // assert(compare) : 수정용 데이터와 저장된 데이터 비교

        cleanDB();
        for (int i=1; i<=10; i++) {
            BoardDTO createdBoard = createTestData(i);
            BoardDTO createdBoardForUpdate = createUpdateTestData(i);

            boardDao.insert(createdBoard);
            boardDao.update(createdBoardForUpdate);
            BoardDTO selectedBoard = boardDao.select(createdBoard.getBno());

            assertTrue(createdBoardForUpdate.getTitle().equals(selectedBoard.getTitle()));
            assertTrue(createdBoardForUpdate.getContent().equals(selectedBoard.getContent()));
            assertTrue(createdBoardForUpdate.getWriter().equals(selectedBoard.getWriter()));

        }
    }

    @Test
    public void updateOneHundredDataTest() throws Exception {
        // given & when : 테이블 초기화 및 데이터 생성(저장용, 수정용)
        // do : 데이터 저장 및 저장된 데이터 수정
        // assert(compare) : 수정용 데이터와 저장된 데이터 비교

        cleanDB();
        for (int i=1; i<=100; i++) {
            BoardDTO createdBoard = createTestData(i);
            BoardDTO createdBoardForUpdate = createUpdateTestData(i);

            boardDao.insert(createdBoard);
            boardDao.update(createdBoardForUpdate);
            BoardDTO selectedBoard = boardDao.select(createdBoard.getBno());

            assertTrue(createdBoardForUpdate.getTitle().equals(selectedBoard.getTitle()));
            assertTrue(createdBoardForUpdate.getContent().equals(selectedBoard.getContent()));
            assertTrue(createdBoardForUpdate.getWriter().equals(selectedBoard.getWriter()));

        }
    }

    @Test
    public void updateOneThousandDataTest() throws Exception {
        // given & when : 테이블 초기화 및 데이터 생성(저장용, 수정용)
        // do : 데이터 저장 및 저장된 데이터 수정
        // assert(compare) : 수정용 데이터와 저장된 데이터 비교

        cleanDB();
        for (int i=1; i<=1000; i++) {
            BoardDTO createdBoard = createTestData(i);
            BoardDTO createdBoardForUpdate = createUpdateTestData(i);

            boardDao.insert(createdBoard);
            boardDao.update(createdBoardForUpdate);
            BoardDTO selectedBoard = boardDao.select(createdBoard.getBno());

            assertTrue(createdBoardForUpdate.getTitle().equals(selectedBoard.getTitle()));
            assertTrue(createdBoardForUpdate.getContent().equals(selectedBoard.getContent()));
            assertTrue(createdBoardForUpdate.getWriter().equals(selectedBoard.getWriter()));

        }
    }

    @Test
    public void deleteOneDataTest() throws Exception {
        // given & when : 테이블 초기화 및 데이터 생성
        cleanDB();
        BoardDTO createdBoard = createTestData(1);
        int expectedRowCnt = 1;

        // do : dao를 통해 데이터 저장 및 삭제
        boardDao.insert(createdBoard);
        int actualRowCnt = boardDao.delete(createdBoard.getBno());
        BoardDTO deletedBoard = boardDao.select(createdBoard.getBno());

        // assert(compare) : 적용된 로우수 및 조회했을 때 null 나오는지 확인
        assertTrue(expectedRowCnt == actualRowCnt);
        assertTrue(deletedBoard == null);

    }

    @Test
    public void deleteTenDataTest() throws Exception {
        // given & when : 테이블 초기화 및 데이터 생성
        // do : dao를 통해 데이터 저장 및 삭제
        // assert(compare) : 적용된 로우수 및 조회했을 때 null 나오는지 확인
        cleanDB();
        int expectedRowCnt = 1;

        for (int i=1; i<=10; i++) {
            BoardDTO createdBoard = createTestData(i);
            boardDao.insert(createdBoard);
            int actualRowCnt = boardDao.delete(createdBoard.getBno());
            BoardDTO deletedBoard = boardDao.select(createdBoard.getBno());
            assertTrue(expectedRowCnt == actualRowCnt);
            assertTrue(deletedBoard == null);
        }
    }

    @Test
    public void deleteOneHundredDataTest() throws Exception {
        // given & when : 테이블 초기화 및 데이터 생성
        // do : dao를 통해 데이터 저장 및 삭제
        // assert(compare) : 적용된 로우수 및 조회했을 때 null 나오는지 확인
        cleanDB();
        int expectedRowCnt = 1;

        for (int i=1; i<=100; i++) {
            BoardDTO createdBoard = createTestData(i);
            boardDao.insert(createdBoard);
            int actualRowCnt = boardDao.delete(createdBoard.getBno());
            BoardDTO deletedBoard = boardDao.select(createdBoard.getBno());
            assertTrue(expectedRowCnt == actualRowCnt);
            assertTrue(deletedBoard == null);
        }
    }

    @Test
    public void deleteOneThousandDataTest() throws Exception {
        // given & when : 테이블 초기화 및 데이터 생성
        // do : dao를 통해 데이터 저장 및 삭제
        // assert(compare) : 적용된 로우수 및 조회했을 때 null 나오는지 확인
        cleanDB();
        int expectedRowCnt = 1;

        for (int i=1; i<=1000; i++) {
            BoardDTO createdBoard = createTestData(i);
            boardDao.insert(createdBoard);
            int actualRowCnt = boardDao.delete(createdBoard.getBno());
            BoardDTO deletedBoard = boardDao.select(createdBoard.getBno());
            assertTrue(expectedRowCnt == actualRowCnt);
            assertTrue(deletedBoard == null);
        }
    }

    @Test
    public void deleteAllOneDataTest() throws Exception {
        // given & when : 테이블 초가화 및 데이터 생성
        cleanDB();
        BoardDTO createdBoard = createTestData(1);
        int expectedRowCnt = 1;
        int expectedTotalRowCnt = 0;

        // do : dao를 통해 데이터 저장 및 모든 데이터 삭제
        boardDao.insert(createdBoard);
        boardDao.deleteAll();
        int actualTotalRowCnt = boardDao.count();

        // assert(compare) : 적용된 로우수와 테이블 전체 로우수 확인
        assertTrue(expectedTotalRowCnt == actualTotalRowCnt);


    }

    @Test
    public void deleteAllTenDataTest() throws Exception {
        // given & when : 테이블 초가화 및 데이터 생성
        // do : dao를 통해 데이터 저장 및 모든 데이터 삭제
        // assert(compare) : 적용된 로우수와 테이블 전체 로우수 확인
        cleanDB();
        int expectedTotalRowCnt = 0;

        for (int i=1; i<=10; i++) {
            BoardDTO createdBoard = createTestData(i);
            boardDao.insert(createdBoard);
        }
        boardDao.deleteAll();
        int actualTotalRowCnt = boardDao.count();

        assertTrue(expectedTotalRowCnt == actualTotalRowCnt);

    }

    @Test
    public void deleteAllOneHundredDataTest() throws Exception {
        // given & when : 테이블 초가화 및 데이터 생성
        // do : dao를 통해 데이터 저장 및 모든 데이터 삭제
        // assert(compare) : 적용된 로우수와 테이블 전체 로우수 확인
        cleanDB();
        int expectedTotalRowCnt = 0;

        for (int i=1; i<=100; i++) {
            BoardDTO createdBoard = createTestData(i);
            boardDao.insert(createdBoard);
        }
        boardDao.deleteAll();
        int actualTotalRowCnt = boardDao.count();

        assertTrue(expectedTotalRowCnt == actualTotalRowCnt);
    }

    @Test
    public void deleteAllOneThousandDataTest() throws Exception {
        // given & when : 테이블 초가화 및 데이터 생성
        // do : dao를 통해 데이터 저장 및 모든 데이터 삭제
        // assert(compare) : 적용된 로우수와 테이블 전체 로우수 확인
        cleanDB();
        int expectedTotalRowCnt = 0;

        for (int i=1; i<=1000; i++) {
            BoardDTO createdBoard = createTestData(i);
            boardDao.insert(createdBoard);
        }
        boardDao.deleteAll();
        int actualTotalRowCnt = boardDao.count();

        assertTrue(expectedTotalRowCnt == actualTotalRowCnt);
    }




    // 2. 보조 메서드
    private void cleanDB() throws Exception {
        Connection conn = ds.getConnection();
        String sql = "truncate board";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.executeUpdate();
    }

    private BoardDTO createTestData(int i) {
        BoardDTO createdBoard = new BoardDTO(i, "title1", "content1", "writer1", 1, 1, null, null);
        return createdBoard;
    }

    private BoardDTO createUpdateTestData(int i) {
        BoardDTO createdBoard = new BoardDTO(i, "title2", "content2", "writer2", 1, 1, null, null);
        return createdBoard;
    }
}