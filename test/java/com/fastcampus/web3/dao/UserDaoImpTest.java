package com.fastcampus.web3.dao;


import com.fastcampus.web3.dto.UserDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
import javax.sql.DataSource;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



// AC 등록, root-context.xml은 설정 파일
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class UserDaoImpTest extends TestCase {
    @Autowired
    DataSource ds;

    // 메서드 하나마다 conn이 있음, 단순 CRUD 뿐만 아니라 여러개 쿼리문도 넣어보기
    @Autowired
    UserDaoImp userDao;


    // 0. 초기 테스트 ✅
        // 0-1. 데이타 소스 DI
        // 0-2. UserDaoImp DI

    // 📌 질문 : ds, userDao를 매개변수로 넣어줄 순 없을까?
    @Test
    public void checkDsDI() {
        assertTrue(ds != null);
        assertTrue(ds instanceof DataSource);
    }

    @Test
    public void checkDaoDI() {
        assertTrue(userDao != null);
        assertTrue(userDao instanceof  UserDaoImp);
    }

    // 1. 핵심 기능 테스트(CRUD), 테스트 범위 확장

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
            // 1-3-1. 데이터 1개 넣고 1개 조회
            // 1-3-2. 데이터 10개 넣고 10개 조회
            // 1-3-3. 데이터 100개 넣고 100개 조회
            // 1-3-4. 데이터 1000개 넣고 1000개 조회

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

    // 📌 질문 : 시나리오가 같은 테스트를 재사용할 순 없을까? 예를 들어서, 같은 시나리오를 메서드로 정해놓고 매개변수로 값만 넣어주는 방식
    @Test
    public void countOneData() throws Exception {
        // given & when : 테이블 초기화 및 데이터 1개 추가
        cleanDB();
        UserDTO createdUser = new UserDTO("1", "1234", "testUser1", "testEmail1", new java.sql.Date(new Date(24).getTime()), "fn", new java.sql.Date(new Date(24).getTime()));
        int expectedRowCnt = 1;

        // do : dao로 로우수 카운트
        userDao.insert(createdUser);
        int actualRowCnt = userDao.count();

        // assert(compare) : 기대 결과와 실제 결과 비교
        assertTrue(expectedRowCnt == actualRowCnt);
    }

    @Test
    public void countTenData() throws Exception {
        // given & when : 테이블 초기화 및 데이터 10개 추가
        cleanDB();
        int expectedRowCnt = 10;

        for (int i=1; i<=expectedRowCnt; i++) {
            UserDTO createdUser = new UserDTO(""+i, "1234", "testUser1", "testEmail1", new java.sql.Date(new Date(24).getTime()), "fn", new java.sql.Date(new Date(24).getTime()));
            userDao.insert(createdUser);
        }

        // do : dao로 로우수 카운트
        int actualRowCnt = userDao.count();
        System.out.println(actualRowCnt);

        // assert(compare) : 기대 결과와 실제 결과 비교
        assertTrue(expectedRowCnt == actualRowCnt);
    }


    @Test
    public void countOneHundredData() throws Exception {
        // given & when : 테이블 초기화 및 데이터 100개 추가
        cleanDB();
        int expectedRowCnt = 100;

        for (int i=1; i<=expectedRowCnt; i++) {
            UserDTO createdUser = new UserDTO(""+i, "1234", "testUser1", "testEmail1", new java.sql.Date(new Date(24).getTime()), "fn", new java.sql.Date(new Date(24).getTime()));
            userDao.insert(createdUser);
        }

        // do : dao로 로우수 카운트
        int actualRowCnt = userDao.count();

        // assert(compare) : 기대 결과와 실제 결과 비교
        assertTrue(expectedRowCnt == actualRowCnt);
    }

    @Test
    public void countOneThousandData() throws Exception {
        // given & when : 테이블 초기화 및 데이터 1000개 추가
        cleanDB();
        int expectedRowCnt = 1000;

        for (int i=1; i<=expectedRowCnt; i++) {
            UserDTO createdUser = new UserDTO(""+i, "1234", "testUser1", "testEmail1", new java.sql.Date(new Date(24).getTime()), "fn", new java.sql.Date(new Date(24).getTime()));
            userDao.insert(createdUser);
        }

        // do : dao로 로우수 카운트
        int actualRowCnt = userDao.count();

        // assert(compare) : 기대 결과와 실제 결과 비교
        assertTrue(expectedRowCnt == actualRowCnt);
    }



    @Test
    public void insertUserTestForOneTime() throws Exception {
        // given & when : 테이블 초기화 및 데이터 생성
        cleanDB();
        UserDTO userDTO = new UserDTO("1", "1234", "testUser1", "testEmail1", new java.sql.Date(new Date(24).getTime()), "fn", new java.sql.Date(new Date(24).getTime()));
        int expectedRowCnt = 1;

        // do : dao를 통해 데이터 저장
        int actualRowCnt = userDao.insert(userDTO);

        // assert(compare) : 기대값과 실제값 비교
        assertTrue(expectedRowCnt == actualRowCnt);
    }



    @Test
    public void insertUserTestForTenTimes() throws Exception {
        // 밑에 과정 10번 반복
            // given & when : 테이블 초기화 및 데이터 생성
            // do : dao를 통해 데이터 저장
            // assert(compare) : 기대값과 실제값 비교
        cleanDB();

        for (int i=1; i<=10; i++) {
            UserDTO userDTO = new UserDTO(""+i, "1234", "testUser"+i, "testEmail"+i, new java.sql.Date(new Date(24).getTime()), "fn", new java.sql.Date(new Date(24).getTime()));
            int expectedRowCnt = 1;
            int actualRowCnt = userDao.insert(userDTO);
            assertTrue(expectedRowCnt == actualRowCnt);
        }

    }

    @Test
    public void insertUserTestForOneHundredTimes() throws Exception {
        // 밑에 과정 100번 반복
            // given & when : 테이블 초기화 및 데이터 생성
            // do : dao를 통해 데이터 저장
            // assert(compare) : 기대값과 실제값 비교
        cleanDB();

        for (int i=1; i<=100; i++) {
            UserDTO userDTO = new UserDTO(""+i, "1234", "testUser"+i, "testEmail"+i, new java.sql.Date(new Date(24).getTime()), "fn", new java.sql.Date(new Date(24).getTime()));
            int expectedRowCnt = 1;
            int actualRowCnt = userDao.insert(userDTO);
            assertTrue(expectedRowCnt == actualRowCnt);
        }
    }

    @Test
    public void insertUserTestForOneThousandTimes() throws Exception {
        // 밑에 과정 1000번 반복
            // given & when : 테이블 초기화 및 데이터 생성
            // do : dao를 통해 데이터 저장
            // assert(compare) : 기대값과 실제값 비교
        cleanDB();

        for (int i=1; i<=1000; i++) {
            UserDTO userDTO = new UserDTO(""+i, "1234", "testUser"+i, "testEmail"+i, new java.sql.Date(new Date(24).getTime()), "fn", new java.sql.Date(new Date(24).getTime()));
            int expectedRowCnt = 1;
            int actualRowCnt = userDao.insert(userDTO);
            assertTrue(expectedRowCnt == actualRowCnt);
        }
    }

    @Test
    public void selectNotFoundUserTest() throws Exception {
        // given & when : 빈 테이블
        // do : 없는데이터 조회
        // assert(compare) : null 조회
        cleanDB();
        UserDTO selectedUser = userDao.select("1");
        assertTrue(selectedUser == null);

    }


    @Test
    public void selectUserTestForOneTime() throws Exception {
        // given & when :
        cleanDB();
        UserDTO createdUser = new UserDTO("1", "1234", "testUser"+1, "testEmail"+1, null, "fn", null);
        insertDataByJdbc(createdUser);

        // do : dao를 통해서 데이터 조회(id)
        UserDTO selectedUser = userDao.select("1");

        // assert(compare) : 내가 생성한 객체의 iv와 DB에서 조회된 iv 비교
        assertTrue(selectedUser.getId().equals(createdUser.getId()));
        assertTrue(selectedUser.getName().equals(createdUser.getName()));
        assertTrue(selectedUser.getEmail().equals(createdUser.getEmail()));
    }

    @Test
    public void selectUserTestForTenTimes() throws Exception {
        // 밑에 과정을 10번 반복
            // given & when : 테이블 비우고 필요 데이터 넣음
            // do : dao를 통해서 데이터 조회(id)
            // assert(compare) : 내가 생성한 객체의 iv와 DB에서 조회된 iv 비교
        cleanDB();

        for (int i=1; i<=10; i++) {
            UserDTO createdUser = new UserDTO(""+i, "1234", "testUser"+i, "testEmail"+i, null, "fn", null);
            insertDataByJdbc(createdUser);

            UserDTO selectedUser = userDao.select(""+i);

            assertTrue(selectedUser.getId().equals(createdUser.getId()));
            assertTrue(selectedUser.getName().equals(createdUser.getName()));
            assertTrue(selectedUser.getEmail().equals(createdUser.getEmail()));
        }
    }

    @Test
    public void selectUserTestForOneHundredTimes() throws Exception {
        // 밑에 과정을 100번 반복
            // given & when : 테이블 비우고 필요 데이터 넣음
            // do : dao를 통해서 데이터 조회(id)
            // assert(compare) : 내가 생성한 객체의 iv와 DB에서 조회된 iv 비교
        cleanDB();

        for (int i=1; i<=100; i++) {
            UserDTO createdUser = new UserDTO(""+i, "1234", "testUser"+i, "testEmail"+i, null, "fn", null);
            insertDataByJdbc(createdUser);

            UserDTO selectedUser = userDao.select(""+i);

            assertTrue(selectedUser.getId().equals(createdUser.getId()));
            assertTrue(selectedUser.getName().equals(createdUser.getName()));
            assertTrue(selectedUser.getEmail().equals(createdUser.getEmail()));
        }
    }

    @Test
    public void selectUserTestForOneThousandTimes() throws Exception {
        // 밑에 과정을 1000번 반복
            // given & when : 테이블 비우고 필요 데이터 넣음
            // do : dao를 통해서 데이터 조회(id)
            // assert(compare) : 내가 생성한 객체의 iv와 DB에서 조회된 iv 비교
        cleanDB();

        for (int i=1; i<=1000; i++) {
            UserDTO createdUser = new UserDTO(""+i, "1234", "testUser"+i, "testEmail"+i, null, "fn", null);
            insertDataByJdbc(createdUser);

            UserDTO selectedUser = userDao.select(""+i);

            assertTrue(selectedUser.getId().equals(createdUser.getId()));
            assertTrue(selectedUser.getName().equals(createdUser.getName()));
            assertTrue(selectedUser.getEmail().equals(createdUser.getEmail()));
        }
    }

    @Test
    public void updateUserTestForOneTime() throws Exception {
        // given & when : 테이블 초기화 및 데이터 생성(저장용 데이터, 수정용 데이터)
        cleanDB();
        UserDTO createdUser = new UserDTO("1", "1234", "testUser1", "testEmail1", new java.sql.Date(new Date(24).getTime()), "fn", new java.sql.Date(new Date(24).getTime()));
        UserDTO createdUserForUpdate = new UserDTO("1", "12345", "testUser12", "testEmail12", new java.sql.Date(new Date(24).getTime()), "fn", new java.sql.Date(new Date(24).getTime()));
        int expectedRowCnt = 1;

        // do : dao를 통해 저장용 데이터 저장 및 수정용 데이터 내용으로 수정
        userDao.insert(createdUser);
        int actualRowCnt = userDao.update(createdUserForUpdate);
        UserDTO selectedUser = userDao.select(""+1);

        // assert(compare) : 예상 결과와 실제 결과 비교
        assertTrue(expectedRowCnt == actualRowCnt);

        assertTrue(selectedUser.getPwd().equals(createdUserForUpdate.getPwd()));
        assertTrue(selectedUser.getName().equals(createdUserForUpdate.getName()));
        assertTrue(selectedUser.getEmail().equals(createdUserForUpdate.getEmail()));

    }

    @Test
    public void selectAllTestForOneUser() throws Exception {
        // given & when : 테이블 초기화 및 데이터 생성
        cleanDB();
        UserDTO createdUser = new UserDTO("1", "1234", "testUser1", "testEmail1", new java.sql.Date(new Date(24).getTime()), "fn", new java.sql.Date(new Date(24).getTime()));
        int expectedLength = 1;

        // do : dao를 통해 모든 데이터 조회
        userDao.insert(createdUser);
        List<UserDTO> userDTOS = userDao.selectAll();
        int actualLength = userDTOS.size();

        // assert(compare) : 기대값과 실제값 결과 비교
        assertTrue(expectedLength == actualLength);

    }

    @Test
    public void selectAllTestForTenUser() throws Exception {
        // given & when : 테이블 초기화 및 데이터 생성
        cleanDB();
        int expectedLength = 10;
        for (int i=1; i<=expectedLength; i++) {
            UserDTO createdUser = new UserDTO(""+i, "1234", "testUser1", "testEmail1", new java.sql.Date(new Date(24).getTime()), "fn", new java.sql.Date(new Date(24).getTime()));
            userDao.insert(createdUser);
        }

        // do : dao를 통해 모든 데이터 조회
        List<UserDTO> userDTOS = userDao.selectAll();
        int actualLength = userDTOS.size();

        // assert(compare) : 기대값과 실제값 비교
        assertTrue(expectedLength == actualLength);
    }


    @Test
    public void selectAllTestForOneHundredUser() throws Exception {
        // given & when : 테이블 초기화 및 데이터 생성
        cleanDB();
        int expectedLength = 100;

        for (int i=1; i<=expectedLength; i++) {
            UserDTO createdUser = new UserDTO(""+i, "1234", "testUser1", "testEmail1", new java.sql.Date(new Date(24).getTime()), "fn", new java.sql.Date(new Date(24).getTime()));
            userDao.insert(createdUser);
        }

        // do : dao를 통해 모든 데이터 조회
        List<UserDTO> userDTOS = userDao.selectAll();
        int actualLength = userDTOS.size();

        // assert(compare) : 기대값과 실제값 비교
        assertTrue(expectedLength == actualLength);

    }

    @Test
    public void selectAllTestForOneThousandUser() throws Exception {
        // given & when : 테이블 초기화 및 데이터 생성
        cleanDB();
        int expectedLength = 1000;

        for (int i=1; i<=expectedLength; i++) {
            UserDTO createdUser = new UserDTO(""+i, "1234", "testUser1", "testEmail1", new java.sql.Date(new Date(24).getTime()), "fn", new java.sql.Date(new Date(24).getTime()));
            userDao.insert(createdUser);
        }

        // do : dao를 통해 모든 데이터 조회
        List<UserDTO> userDTOS = userDao.selectAll();
        int actualLength = userDTOS.size();

        // assert(compare) : 기대값과 실제값 비교
        assertTrue(expectedLength == actualLength);
    }

    @Test

    public void updateUserTestForTenTimes() throws Exception {
        // 밑에 과정 10번 반복
            // given & when : 테이블 초기화 및 데이터 생성(저장용 데이터, 수정용 데이터)
            // do : dao를 통해 저장용 데이터 저장 및 수정용 데이터 내용으로 수정
            // assert(compare) : 예상 결과와 실제 결과 비교
        cleanDB();

        for (int i=1; i<=10; i++) {
            UserDTO createdUser = new UserDTO("" + i, "1234", "testUser"+i, "testEmail"+i, new java.sql.Date(new Date(24).getTime()), "fn", new java.sql.Date(new Date(24).getTime()));
            UserDTO createdUserForUpdate = new UserDTO("" + i, "12345", "testUser12", "testEmail12", new java.sql.Date(new Date(24).getTime()), "fn", new java.sql.Date(new Date(24).getTime()));

            int expectedRowCnt = 1;

            userDao.insert(createdUser);
            int actualRowCnt = userDao.update(createdUserForUpdate);
            UserDTO selectedUser = userDao.select(""+i);

            assertTrue(expectedRowCnt == actualRowCnt);

            assertTrue(selectedUser.getPwd().equals(createdUserForUpdate.getPwd()));
            assertTrue(selectedUser.getName().equals(createdUserForUpdate.getName()));
            assertTrue(selectedUser.getEmail().equals(createdUserForUpdate.getEmail()));
        }
    }

    @Test
    public void updateUserTestForOneHundredTimes() throws Exception {
        // 밑에 과정 100번 반복
            // given & when : 테이블 초기화 및 데이터 생성(저장용 데이터, 수정용 데이터)
            // do : dao를 통해 저장용 데이터 저장 및 수정용 데이터 내용으로 수정
            // assert(compare) : 예상 결과와 실제 결과 비교
        cleanDB();

        for (int i=1; i<=100; i++) {
            UserDTO createdUser = new UserDTO("" + i, "1234", "testUser"+i, "testEmail"+i, new java.sql.Date(new Date(24).getTime()), "fn", new java.sql.Date(new Date(24).getTime()));
            UserDTO createdUserForUpdate = new UserDTO("" + i, "12345", "testUser12", "testEmail12", new java.sql.Date(new Date(24).getTime()), "fn", new java.sql.Date(new Date(24).getTime()));

            int expectedRowCnt = 1;

            userDao.insert(createdUser);
            int actualRowCnt = userDao.update(createdUserForUpdate);
            UserDTO selectedUser = userDao.select(""+i);

            assertTrue(expectedRowCnt == actualRowCnt);

            assertTrue(selectedUser.getPwd().equals(createdUserForUpdate.getPwd()));
            assertTrue(selectedUser.getName().equals(createdUserForUpdate.getName()));
            assertTrue(selectedUser.getEmail().equals(createdUserForUpdate.getEmail()));
        }
    }

    @Test
    public void updateUserTestForOneThousandTimes() throws Exception {
        // 밑에 과정 1000번 반복
            // given & when : 테이블 초기화 및 데이터 생성(저장용 데이터, 수정용 데이터)
            // do : dao를 통해 저장용 데이터 저장 및 수정용 데이터 내용으로 수정
            // assert(compare) : 예상 결과와 실제 결과 비교
        cleanDB();

        for (int i=1; i<=1000; i++) {
            UserDTO createdUser = new UserDTO("" + i, "1234", "testUser"+i, "testEmail"+i, new java.sql.Date(new Date(24).getTime()), "fn", new java.sql.Date(new Date(24).getTime()));
            UserDTO createdUserForUpdate = new UserDTO("" + i, "12345", "testUser12", "testEmail12", new java.sql.Date(new Date(24).getTime()), "fn", new java.sql.Date(new Date(24).getTime()));

            int expectedRowCnt = 1;

            userDao.insert(createdUser);
            int actualRowCnt = userDao.update(createdUserForUpdate);
            UserDTO selectedUser = userDao.select("" + i);

            assertTrue(expectedRowCnt == actualRowCnt);

            assertTrue(selectedUser.getPwd().equals(createdUserForUpdate.getPwd()));
            assertTrue(selectedUser.getName().equals(createdUserForUpdate.getName()));
            assertTrue(selectedUser.getEmail().equals(createdUserForUpdate.getEmail()));
        }
    }

    @Test
    public void deleteUserTestForOneTime() throws Exception {
        // given & when : 테이블 초기화 및 데이터 생성
        cleanDB();
        UserDTO createdUser = new UserDTO(""+1, "1234", "testUser"+1, "testEmail"+1, null, "fn", null);
        int expectedRowCnt = 1;

        // do : dao를 통해 저장한 데이터 조회후 삭제
        userDao.insert(createdUser);
        int actualRowCnt = userDao.delete(createdUser.getId());
        UserDTO selectedUser = userDao.select(createdUser.getId());

        // assert(compare) : 예상 결과와 실제 결과 비교
        assertTrue(expectedRowCnt == actualRowCnt);
        assertTrue(selectedUser == null);
    }

    @Test
    public void deleteUserTestForTenTimes() throws Exception {
        // 밑에 과정 10번 반복
            // given & when : 테이블 초기화 및 데이터 생성
            // do : dao를 통해 저장한 데이터 조회후 삭제
            // assert(compare) : 예상 결과와 실제 결과 비교
        cleanDB();

        for (int i=1; i<=10; i++) {
            UserDTO createdUser = new UserDTO(""+i, "1234", "testUser"+i, "testEmail"+i, null, "fn", null);
            int expectedRowCnt = 1;

            // do : dao를 통해 저장한 데이터 조회후 삭제
            userDao.insert(createdUser);
            int actualRowCnt = userDao.delete(createdUser.getId());
            UserDTO selectedUser = userDao.select(createdUser.getId());

            // assert(compare) : 예상 결과와 실제 결과 비교
            assertTrue(expectedRowCnt == actualRowCnt);
            assertTrue(selectedUser == null);
        }

    }

    @Test
    public void deleteUserTestForOneHundredTimes() throws Exception {
        // 밑에 과정 100번 반복
            // given & when : 테이블 초기화 및 데이터 생성
            // do : dao를 통해 저장한 데이터 조회후 삭제
            // assert(compare) : 예상 결과와 실제 결과 비교
        cleanDB();

        for (int i=1; i<=100; i++) {
            UserDTO createdUser = new UserDTO(""+i, "1234", "testUser"+i, "testEmail"+i, null, "fn", null);
            int expectedRowCnt = 1;

            // do : dao를 통해 저장한 데이터 조회후 삭제
            userDao.insert(createdUser);
            int actualRowCnt = userDao.delete(createdUser.getId());
            UserDTO selectedUser = userDao.select(createdUser.getId());

            // assert(compare) : 예상 결과와 실제 결과 비교
            assertTrue(expectedRowCnt == actualRowCnt);
            assertTrue(selectedUser == null);
        }
    }

    @Test
    public void deleteUserTestForOneThousandTimes() throws Exception {
        // 밑에 과정 1000번 반복
            // given & when : 테이블 초기화 및 데이터 생성
            // do : dao를 통해 저장한 데이터 조회후 삭제
            // assert(compare) : 예상 결과와 실제 결과 비교
        cleanDB();

        for (int i=1; i<=1000; i++) {
            UserDTO createdUser = new UserDTO(""+i, "1234", "testUser"+i, "testEmail"+i, null, "fn", null);
            int expectedRowCnt = 1;

            // do : dao를 통해 저장한 데이터 조회후 삭제
            userDao.insert(createdUser);
            int actualRowCnt = userDao.delete(createdUser.getId());
            UserDTO selectedUser = userDao.select(createdUser.getId());

            // assert(compare) : 예상 결과와 실제 결과 비교
            assertTrue(expectedRowCnt == actualRowCnt);
            assertTrue(selectedUser == null);
        }
    }

    @Test
    public void deleteAllTestForOneUser() throws Exception {
        // given & when : 테이블 초기화 및 데이터 생성
        cleanDB();
        UserDTO createdUser = new UserDTO("1", "1234", "testUser1", "testEmail1", new java.sql.Date(new Date(24).getTime()), "fn", new java.sql.Date(new Date(24).getTime()));
        int expectedRowCnt = 1;

        // do : dao를 통해 모든 데이터 조회
        userDao.insert(createdUser);
        int actualRowCnt = userDao.deleteAll();

        // assert(compare) : 기대값과 실제값 결과 비교
        assertTrue(expectedRowCnt == actualRowCnt);

    }

    @Test
    public void deleteAllTestForTenUser() throws Exception {
        // given & when : 테이블 초기화 및 데이터 생성
        cleanDB();
        int expectedRowCnt = 10;
        for (int i=1; i<=expectedRowCnt; i++) {
            UserDTO createdUser = new UserDTO(""+i, "1234", "testUser1", "testEmail1", new java.sql.Date(new Date(24).getTime()), "fn", new java.sql.Date(new Date(24).getTime()));
            userDao.insert(createdUser);
        }

        // do : dao를 통해 모든 데이터 조회
        int actualRowCnt = userDao.deleteAll();

        // assert(compare) : 기대값과 실제값 비교
        assertTrue(expectedRowCnt == actualRowCnt);
    }


    @Test
    public void deleteAllTestForOneHundredUser() throws Exception {
        // given & when : 테이블 초기화 및 데이터 생성
        cleanDB();
        int expectedRowCnt = 100;
        for (int i=1; i<=expectedRowCnt; i++) {
            UserDTO createdUser = new UserDTO(""+i, "1234", "testUser1", "testEmail1", new java.sql.Date(new Date(24).getTime()), "fn", new java.sql.Date(new Date(24).getTime()));
            userDao.insert(createdUser);
        }

        // do : dao를 통해 모든 데이터 조회
        int actualRowCnt = userDao.deleteAll();

        // assert(compare) : 기대값과 실제값 비교
        assertTrue(expectedRowCnt == actualRowCnt);

    }

    @Test
    public void deleteAllTestForOneThousandUser() throws Exception {
        // given & when : 테이블 초기화 및 데이터 생성
        cleanDB();
        int expectedRowCnt = 1000;
        for (int i=1; i<=expectedRowCnt; i++) {
            UserDTO createdUser = new UserDTO(""+i, "1234", "testUser1", "testEmail1", new java.sql.Date(new Date(24).getTime()), "fn", new java.sql.Date(new Date(24).getTime()));
            userDao.insert(createdUser);
        }

        // do : dao를 통해 모든 데이터 조회
        int actualRowCnt = userDao.deleteAll();

        // assert(compare) : 기대값과 실제값 비교
        assertTrue(expectedRowCnt == actualRowCnt);
    }


    // 2. 보조 메서드

    private void cleanDB() throws Exception {
        Connection conn = ds.getConnection();
        String sql = "truncate user_info";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.executeUpdate();

    }

    public int insertDataByJdbc(UserDTO userDTO) throws Exception {
        String sql = "insert into user_info values (?, ?, ?, ?, now(), ?, now())";
        Connection conn = ds.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, userDTO.getId());
        pstmt.setString(2, userDTO.getPwd());
        pstmt.setString(3, userDTO.getName());
        pstmt.setString(4, userDTO.getEmail());
        pstmt.setString(5, userDTO.getSns());

        return pstmt.executeUpdate();
    }
}