package com.fastcampus.web3.dao;

import com.fastcampus.web3.dto.BoardDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public void inserSomeData() throws Exception {
        for (int i=1; i<=100; i++) {
            BoardDto data = createTestData(i);
            boardDao.insert(data);
        }
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

        // 1-7. incrementViewCnt ✅
            // 1-7-0. 없는 데이터 뷰 카운트 올림
            // 1-7-1. 테이블 데이터 1개, 뷰 카운트 올림, 조회한 후 카운트 확인
            // 1-7-2. 테이블 데이터 10개
                // 1-7-2-0. 모두 뷰 카운트 올림, 조회한 후 카운트 확인
                // 1-7-2-1. 랜덤 뷰 카운트 올림, 조회한 후 카운트 확인
            // 1-7-3. 테이블 데이터 100개
                // 1-7-3-0. 모두 뷰 카운트 올림, 조회한 후 카운트 확인
                // 1-7-3-1. 랜덤 뷰 카운트 올림, 조회한 후 카운트 확인
            // 1-7-4. 테이블 데이터 1000개
                // 1-7-4-0. 모두 뷰 카운트 올림, 조회한 후 카운트 확인
                // 1-7-4-1. 랜덤 뷰 카운트 올림, 조회한 후 카운트 확인

        // 1-8. selectPage ✅
            // 1-8-0. 데이터가 없는 경우, 조회, 길이 0 확인
            // 1-8-1. 데이터가 10개
                // 1-8-1-0. 조회, 길이 10 확인
                // 1-8-1-1. 잘못 조회, 예외 발생
            // 1-8-2. 데이터가 100개
                // 1-8-2-0. 조회, 길이 100 확인
                // 1-8-2-0. 잘못 조회, 예외 발생
            // 1-8-3. 데이터가 1000개
                // 1-8-3-0. 조회, 길이 1000 확인
                // 1-8-3-0. 잘못 조회, 예외 발생


    @Test
    public void countOneData() throws Exception {
        // given & when : 테이블 초기화 및 데이터 생성
        cleanDB();
        BoardDto createdBoard = createTestData(1);
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
            BoardDto createdBoard = createTestData(i);
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
            BoardDto createdBoard = createTestData(i);
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
            BoardDto createdBoard = createTestData(i);
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
        BoardDto createdBoard = createTestData(1);
        int expectedRowCnt = 1;

        // do : 데이터 저장 및 반영된 로우수 카운트
        int actualRowCnt = boardDao.insert(createdBoard);
        BoardDto selectedBoard = boardDao.select(createdBoard.getBno());

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
            BoardDto createdBoard = createTestData(i);
            int actualRowCnt = boardDao.insert(createdBoard);
            BoardDto selectedBoard = boardDao.select(createdBoard.getBno());

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
            BoardDto createdBoard = createTestData(i);
            int actualRowCnt = boardDao.insert(createdBoard);
            BoardDto selectedBoard = boardDao.select(createdBoard.getBno());

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
            BoardDto createdBoard = createTestData(i);
            int actualRowCnt = boardDao.insert(createdBoard);
            BoardDto selectedBoard = boardDao.select(createdBoard.getBno());

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
        BoardDto createdBoard = createTestData(1);
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
            BoardDto createdBoard = createTestData(i);
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
            BoardDto createdBoard = createTestData(i);
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
            BoardDto createdBoard = createTestData(i);
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
        BoardDto createdBoard = createTestData(1);
        boardDao.insert(createdBoard);
        List<BoardDto> boardDtos = boardDao.selectAll();
        int actualLength = boardDtos.size();

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
            BoardDto createdBoard = createTestData(i);
            boardDao.insert(createdBoard);
        }

        List<BoardDto> boardDtos = boardDao.selectAll();
        int actualLength = boardDtos.size();
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
            BoardDto createdBoard = createTestData(i);
            boardDao.insert(createdBoard);
        }

        List<BoardDto> boardDtos = boardDao.selectAll();
        int actualLength = boardDtos.size();
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
            BoardDto createdBoard = createTestData(i);
            boardDao.insert(createdBoard);
        }

        List<BoardDto> boardDtos = boardDao.selectAll();
        int actualLength = boardDtos.size();
        assertTrue(expectedLength == actualLength);
    }

    @Test
    public void updateOneDataTest() throws Exception {
        // given & when : 테이블 초기화 및 데이터 생성(저장용, 수정용)
        cleanDB();
        BoardDto createdBoard = createTestData(1);
        BoardDto createdBoardForUpdate = createUpdateTestData(1);

        // do : 데이터 저장 및 저장된 데이터 수정
        boardDao.insert(createdBoard);
        boardDao.update(createdBoardForUpdate);
        BoardDto selectedBoard = boardDao.select(createdBoard.getBno());

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
            BoardDto createdBoard = createTestData(i);
            BoardDto createdBoardForUpdate = createUpdateTestData(i);

            boardDao.insert(createdBoard);
            boardDao.update(createdBoardForUpdate);
            BoardDto selectedBoard = boardDao.select(createdBoard.getBno());

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
            BoardDto createdBoard = createTestData(i);
            BoardDto createdBoardForUpdate = createUpdateTestData(i);

            boardDao.insert(createdBoard);
            boardDao.update(createdBoardForUpdate);
            BoardDto selectedBoard = boardDao.select(createdBoard.getBno());

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
            BoardDto createdBoard = createTestData(i);
            BoardDto createdBoardForUpdate = createUpdateTestData(i);

            boardDao.insert(createdBoard);
            boardDao.update(createdBoardForUpdate);
            BoardDto selectedBoard = boardDao.select(createdBoard.getBno());

            assertTrue(createdBoardForUpdate.getTitle().equals(selectedBoard.getTitle()));
            assertTrue(createdBoardForUpdate.getContent().equals(selectedBoard.getContent()));
            assertTrue(createdBoardForUpdate.getWriter().equals(selectedBoard.getWriter()));

        }
    }

    @Test
    public void deleteOneDataTest() throws Exception {
        // given & when : 테이블 초기화 및 데이터 생성
        cleanDB();
        BoardDto createdBoard = createTestData(1);
        int expectedRowCnt = 1;

        // do : dao를 통해 데이터 저장 및 삭제
        boardDao.insert(createdBoard);
        int actualRowCnt = boardDao.delete(createdBoard.getBno());
        BoardDto deletedBoard = boardDao.select(createdBoard.getBno());

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
            BoardDto createdBoard = createTestData(i);
            boardDao.insert(createdBoard);
            int actualRowCnt = boardDao.delete(createdBoard.getBno());
            BoardDto deletedBoard = boardDao.select(createdBoard.getBno());
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
            BoardDto createdBoard = createTestData(i);
            boardDao.insert(createdBoard);
            int actualRowCnt = boardDao.delete(createdBoard.getBno());
            BoardDto deletedBoard = boardDao.select(createdBoard.getBno());
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
            BoardDto createdBoard = createTestData(i);
            boardDao.insert(createdBoard);
            int actualRowCnt = boardDao.delete(createdBoard.getBno());
            BoardDto deletedBoard = boardDao.select(createdBoard.getBno());
            assertTrue(expectedRowCnt == actualRowCnt);
            assertTrue(deletedBoard == null);
        }
    }

    @Test
    public void deleteAllOneDataTest() throws Exception {
        // given & when : 테이블 초가화 및 데이터 생성
        cleanDB();
        BoardDto createdBoard = createTestData(1);
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
            BoardDto createdBoard = createTestData(i);
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
            BoardDto createdBoard = createTestData(i);
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
            BoardDto createdBoard = createTestData(i);
            boardDao.insert(createdBoard);
        }
        boardDao.deleteAll();
        int actualTotalRowCnt = boardDao.count();

        assertTrue(expectedTotalRowCnt == actualTotalRowCnt);
    }

    @Test
    public void EmptyDataIncrementViewCntTest() throws Exception {
        // given & when : 테이블 비움, 0 세팅
        // do : dao를 통해 없는 데이터 뷰 카운트 올림
        // assert(compare) : 적용된 로우수가 0 인지 확인
        cleanDB();
        int expectedRowCnt = 0;
        int actualRowCnt = boardDao.incrementViewCnt(1);
        assertTrue(expectedRowCnt == actualRowCnt);

    }

    @Test
    public void OneDataIncrementViewCntTest() throws Exception  {
        // given & when : 테이블 비움, 데이터 생성 및 저장
        // do : dao를 통해 해당 데이터 뷰 카운트 올림
        // assert(compare) : 적용된 로우수가 1이고 뷰 카운트가 1인지 확인
        cleanDB();
        int expectedRowCnt = 1;
        int expectedViewCnt = 2;

        BoardDto testData = createTestData(1);

        boardDao.insert(testData);
        int actualRowCnt = boardDao.incrementViewCnt(testData.getBno());
        BoardDto target = boardDao.select(testData.getBno());

        assertTrue(expectedRowCnt == actualRowCnt);
        assertTrue(expectedViewCnt == target.getView_cnt());
    }

    @Test
    public void TenDataIncrementViewCntTest1() throws Exception  {
        // given & when : 테이블 비움, 데이터 10개 생성 및 저장
        // do : dao를 통해 모든 데이터 뷰 카운트 올림
        // assert(compare) : 각 적용된 로우수가 1이고 뷰 카운트가 1인지 확인
        cleanDB();
        int repeat = 10;
        int expectedRowCnt = 1;
        int expectedViewCnt = 2;

        for (int i=1; i<=repeat; i++) {
            BoardDto testData = createTestData(i);

            boardDao.insert(testData);
            int actualRowCnt = boardDao.incrementViewCnt(testData.getBno());
            BoardDto target = boardDao.select(testData.getBno());

            assertTrue(expectedRowCnt == actualRowCnt);
            assertTrue(expectedViewCnt == target.getView_cnt());
        }

    }

    @Test
    public void TenDataIncrementViewCntTest2() throws Exception  {
        // given & when : 테이블 비움, 데이터 10개 생성 및 저장
        // do : dao를 통해 랜덤 데이터 뷰 카운트 올림(5번 반복, 중복된 랜덤값 넘어감)
        // assert(compare) : 각 적용된 로우수가 1이고 뷰 카운트가 1인지 확인
        cleanDB();
        int repeat = 10;
        int expectedRowCnt = 1;
        int expectedViewCnt = 2;

        for (int i=1; i<=repeat; i++) {
            BoardDto testData = createTestData(i);
            boardDao.insert(testData);
        }

        for (int i=1; i<=5; i++) {
            int randomIdx = (int)(Math.random() * repeat) + 1;
            int actualRowCnt = boardDao.incrementViewCnt(randomIdx);

            BoardDto target = boardDao.select(randomIdx);
            if (target.getView_cnt() >= 2) continue;

            assertTrue(expectedRowCnt == actualRowCnt);
            assertTrue(expectedViewCnt == target.getView_cnt());
        }
    }

    @Test
    public void OneHundredDataIncrementViewCntTest1() throws Exception  {
        // given & when : 테이블 비움, 데이터 100개 생성 및 저장
        // do : dao를 통해 모든 데이터 뷰 카운트 올림
        // assert(compare) : 각 적용된 로우수가 1이고 뷰 카운트가 1인지 확인
        cleanDB();
        int repeat = 100;
        int expectedRowCnt = 1;
        int expectedViewCnt = 2;

        for (int i=1; i<=repeat; i++) {
            BoardDto testData = createTestData(i);

            boardDao.insert(testData);
            int actualRowCnt = boardDao.incrementViewCnt(testData.getBno());
            BoardDto target = boardDao.select(testData.getBno());

            assertTrue(expectedRowCnt == actualRowCnt);
            assertTrue(expectedViewCnt == target.getView_cnt());
        }

    }

    @Test
    public void OneHundredDataIncrementViewCntTest2() throws Exception  {
        // given & when : 테이블 비움, 데이터 100개 생성 및 저장
        // do : dao를 통해 랜덤 데이터 뷰 카운트 올림(5번 반복, 중복된 랜덤값 넘어감)
        // assert(compare) : 각 적용된 로우수가 1이고 뷰 카운트가 1인지 확인
        cleanDB();
        int repeat = 100;
        int expectedRowCnt = 1;
        int expectedViewCnt = 2;

        for (int i=1; i<=repeat; i++) {
            BoardDto testData = createTestData(i);
            boardDao.insert(testData);
        }

        for (int i=1; i<=5; i++) {
            int randomIdx = (int)(Math.random() * repeat) + 1;
            int actualRowCnt = boardDao.incrementViewCnt(randomIdx);
            BoardDto target = boardDao.select(randomIdx);

            if (target.getView_cnt() >= 2) continue;

            assertTrue(expectedRowCnt == actualRowCnt);
            assertTrue(expectedViewCnt == target.getView_cnt());
        }
    }

    @Test
    public void OneThousandDataIncrementViewCntTest1() throws Exception  {
        // given & when : 테이블 비움, 데이터 1000개 생성 및 저장
        // do : dao를 통해 모든 데이터 뷰 카운트 올림
        // assert(compare) : 각 적용된 로우수가 1이고 뷰 카운트가 1인지 확인
        cleanDB();
        int repeat = 1000;
        int expectedRowCnt = 1;
        int expectedViewCnt = 2;

        for (int i=1; i<=repeat; i++) {
            BoardDto testData = createTestData(i);

            boardDao.insert(testData);
            int actualRowCnt = boardDao.incrementViewCnt(testData.getBno());
            BoardDto target = boardDao.select(testData.getBno());

            assertTrue(expectedRowCnt == actualRowCnt);
            assertTrue(expectedViewCnt == target.getView_cnt());
        }

    }

    @Test
    public void OneThousandDataIncrementViewCntTest2() throws Exception  {
        // given & when : 테이블 비움, 데이터 1000개 생성 및 저장
        // do : dao를 통해 랜덤 데이터 뷰 카운트 올림(5번 반복, 중복된 랜덤값 넘어감)
        // assert(compare) : 각 적용된 로우수가 1이고 뷰 카운트가 1인지 확인
        cleanDB();
        int repeat = 1000;
        int expectedRowCnt = 1;
        int expectedViewCnt = 2;

        for (int i=1; i<=repeat; i++) {
            BoardDto testData = createTestData(i);
            boardDao.insert(testData);
        }

        for (int i=1; i<=5; i++) {
            int randomIdx = (int)(Math.random() * repeat) + 1;
            int actualRowCnt = boardDao.incrementViewCnt(randomIdx);
            BoardDto target = boardDao.select(randomIdx);

            if (target.getView_cnt() >= 2) continue;

            assertTrue(expectedRowCnt == actualRowCnt);
            assertTrue(expectedViewCnt == target.getView_cnt());
        }
    }

    @Test
    public void selectPageEmptyDataTest() throws Exception {
        // given & when : 테이블 비움
        // do : dao로 페이지 조회
        // assert(compare) : 길이 0 확인
        cleanDB();
        Map<String, Integer> map = createTestMap(0);
        int expectedLength = 0;

        List<BoardDto> boardDtos = boardDao.selectPage(map);
        int actualLength = boardDtos.size();

        assertTrue(expectedLength == actualLength);
    }

    @Test
    public void selectPageOneDataTest() throws Exception {
        // given & when : 테이블 비움, 데이터 1개 생성, 저장
        // do : dao로 페이지 조회
        // assert(compare) : 길이 1 확인
        cleanDB();
        int expectedLength = 1;
        Map<String, Integer> map = createTestMap(0);
        BoardDto testData = createTestData(1);
        boardDao.insert(testData);

        List<BoardDto> boardDtos = boardDao.selectPage(map);
        int actualLength = boardDtos.size();

        assertTrue(expectedLength == actualLength);
    }

    @Test
    public void selectPageTenDataTest1() throws Exception {
        // given & when : 테이블 비움, 데이터 10개 생성, 저장
        // do : dao로 페이지 조회
        // assert(compare) : 길이 10 확인
        cleanDB();
        int repeat = 10;
        int expectedLength = 10;
        Map<String, Integer> map = createTestMap(0);
        for (int i=1; i<=repeat; i++) {
            BoardDto testData = createTestData(i);
            boardDao.insert(testData);
        }

        List<BoardDto> boardDtos = boardDao.selectPage(map);
        int actualLength = boardDtos.size();

        assertTrue(expectedLength == actualLength);
    }

    @Test(expected = Exception.class)
    public void selectPageTenDataTest2() throws Exception {
        // given & when : 테이블 비움, 데이터 10개 생성, 저장
        // do : dao로 페이지 잘못 조회
        // assert(compare) : 길이 0 확인
        cleanDB();
        int repeat = 10;
        int expectedLength = 10;
        Map<String, Integer> map = createTestMap(-1);
        for (int i=1; i<=repeat; i++) {
            BoardDto testData = createTestData(i);
            boardDao.insert(testData);
        }

        List<BoardDto> boardDtos = boardDao.selectPage(map);
    }


    @Test
    public void selectPageOneHundredDataTest1() throws Exception {
        // given & when : 테이블 비움, 데이터 100개 생성, 저장
        // do : dao로 페이지 조회
        // assert(compare) : 길이 10 확인
        cleanDB();
        int repeat = 100;
        int expectedLength = 10;
        Map<String, Integer> map = createTestMap(0);
        for (int i=1; i<=repeat; i++) {
            BoardDto testData = createTestData(i);
            boardDao.insert(testData);
        }

        List<BoardDto> boardDtos = boardDao.selectPage(map);
        int actualLength = boardDtos.size();

        assertTrue(expectedLength == actualLength);
    }


    @Test(expected = Exception.class)
    public void selectPageOneHundredDataTest2() throws Exception {
        // given & when : 테이블 비움, 데이터 100개 생성, 저장
        // do : dao로 페이지 잘못 조회
        // assert(compare) : 길이 10 확인
        cleanDB();
        int repeat = 100;
        int expectedLength = 10;
        Map<String, Integer> map = createTestMap(-1);
        for (int i=1; i<=repeat; i++) {
            BoardDto testData = createTestData(i);
            boardDao.insert(testData);
        }

        List<BoardDto> boardDtos = boardDao.selectPage(map);
    }


    @Test
    public void selectPageOneThousandDataTest1() throws Exception {
        // given & when : 테이블 비움, 데이터 1000개 생성, 저장
        // do : dao로 페이지 조회
        // assert(compare) : 길이 10 확인
        cleanDB();
        int repeat = 1000;
        int expectedLength = 10;
        Map<String, Integer> map = createTestMap(0);
        for (int i=1; i<=repeat; i++) {
            BoardDto testData = createTestData(i);
            boardDao.insert(testData);
        }

        List<BoardDto> boardDtos = boardDao.selectPage(map);
        int actualLength = boardDtos.size();

        assertTrue(expectedLength == actualLength);
    }

    @Test(expected = Exception.class)
    public void selectPageOneThousandDataTest2() throws Exception {
        // given & when : 테이블 비움, 데이터 1000개 생성, 저장
        // do : dao로 페이지 잘못 조회
        // assert(compare) : 길이 0 확인
        cleanDB();
        int repeat = 1000;
        int expectedLength = 10;
        Map<String, Integer> map = createTestMap(-1);
        for (int i=1; i<=repeat; i++) {
            BoardDto testData = createTestData(i);
            boardDao.insert(testData);
        }

        List<BoardDto> boardDtos = boardDao.selectPage(map);
        int actualLength = boardDtos.size();

        assertTrue(expectedLength == actualLength);
    }



    // 2. 보조 메서드
    private void cleanDB() throws Exception {
        Connection conn = ds.getConnection();
        String sql = "truncate board";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.executeUpdate();
    }

    private BoardDto createTestData(int i) {
        BoardDto createdBoard = new BoardDto(i, "title1", "content1", "writer1", 1, 1, null, null);
        return createdBoard;
    }

    private BoardDto createUpdateTestData(int i) {
        BoardDto createdBoard = new BoardDto(i, "title2", "content2", "writer2", 1, 1, null, null);
        return createdBoard;
    }

    private Map<String, Integer> createTestMap(int i) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("offset", i);
        map.put("pageSize", 10);
        return map;
    }
}