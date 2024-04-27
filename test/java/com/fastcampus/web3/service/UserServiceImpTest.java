package com.fastcampus.web3.service;

import com.fastcampus.web3.dao.UserDao;
import com.fastcampus.web3.dto.UserDto;
import java.util.List;
import javax.sql.DataSource;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// ✅ 모든 테스트 통과
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class UserServiceImpTest extends TestCase {

    @Autowired
    DataSource ds;
    @Autowired
    private UserService service;
    @Autowired
    private UserDao dao;

    // 0. 초기 테스트 - DI
    @Test
    public void 초기테스트() {
        assertTrue(ds != null);
        assertTrue(ds instanceof DataSource);

        assertTrue(service != null);
        assertTrue(service instanceof UserService);

        assertTrue(dao != null);
        assertTrue(dao instanceof UserDao);
    }

    // 1. 핵심 기능
        // CRUD
        // 1-0. 유저수 카운트
            // 1-0-0. 0명
            // 1-0-1. 1명
            // 1-0-2. 10명
            // 1-0-3. 100명
            // 1-0-4. 1000명
    @Test
    public void 유저_0_카운트() throws Exception {
        // given & when : 유저 0
        // do : service로 카운트
        // assert(compare) : 0
        cleanDB();
        int actualCnt = service.getCount();
        assertTrue(0 == actualCnt);

    }

    @Test
    public void 유저_1_카운트() throws Exception {
        // given & when : 유저 1
        // do : service 카운트
        // assert(compare) : 1
        cleanDB();
        insertData(1);
        int actualCnt = service.getCount();
        assertTrue(1 == actualCnt);
    }

    @Test
    public void 유저_10_카운트() throws Exception {
        // given & when : 유저 10
        // do : service 카운트
        // assert(compare) : 10
        cleanDB();
        insertData(10);
        int actualCnt = service.getCount();
        assertTrue(10 == actualCnt);

    }

    @Test
    public void 유저_100_카운트() throws Exception  {
        // given & when : 유저 100
        // do : service 카운트
        // assert(compare) : 100
        cleanDB();
        insertData(100);
        int actualCnt = service.getCount();
        assertTrue(100 == actualCnt);
    }

    @Test
    public void 유저_1000_카운트() throws Exception {
        // given & when : 유저 1000
        // do : service 카운트
        // assert(compare) : 1000
        cleanDB();
        insertData(1000);
        int actualCnt = service.getCount();
        assertTrue(1000 == actualCnt);
    }

        // 1-1. 특정 유저 조회
            // 1-1-0. 해당 아이디가 없는 경우, null
            // 1-1-1. 1명 중에 1명 찾음
            // 1-1-2. 10명 중에 1명 찾음
            // 1-1-3. 100명 중에 15명 찾음
            // 1-1-4. 1000명 중에 25명 찾음
    @Test
    public void 유저_0_조회1() throws Exception {
        // given & when : 유저 0
        // do : service로 없는 id 조회
        // assert(compare) : userDTO 내부 null
        cleanDB();
        UserDto found = service.find("1");
        assertTrue(null == found);
    }

    @Test
    public void 유저_1_조회1() throws Exception {
        // given & when : 유저 1
        // do : service로 id 조회
        // assert(compare) : 조회된 dto와 생성한 dto 내용 비교
        cleanDB();
        UserDto created = createData(1);
        dao.insert(created);

        UserDto found = service.find("1");

        assertTrue(created.equals(found));
    }

    @Test
    public void 유저_10_조회1() throws Exception {
        // given & when : 유저 10
        // do : service로 id 조회(1명)
        // assert(compare) : 조회된 dto와 생성한 dto 내용 비교
        cleanDB();
        int size = 10;
        int checkCnt = 1;
        for (int i=1; i<=size; i++) {
            UserDto created = createData(i);
            dao.insert(created);

            if (i <= checkCnt) {
                UserDto found = service.find(String.valueOf(i));
                assertTrue(created.equals(found));
            }
        }
    }

    @Test
    public void 유저_100_조회1() throws Exception {
        // given & when : 유저 100
        // do : service로 id 조회(15명)
        // assert(compare) : 조회된 dto와 생성한 dto 내용 비교
        cleanDB();
        int size = 100;
        int checkCnt = 15;
        for (int i=1; i<=size; i++) {
            UserDto created = createData(i);
            dao.insert(created);

            if (i <= checkCnt) {
                UserDto found = service.find(String.valueOf(i));
                assertTrue(created.equals(found));
            }
        }
    }

    @Test
    public void 유저_1000_조회1() throws Exception {
        // given & when : 유저 1000
        // do : service로 id 조회(25명)
        // assert(compare) : 조회된 dto와 생성한 dto 내용 비교
        cleanDB();
        int size = 1000;
        int checkCnt = 25;
        for (int i=1; i<=size; i++) {
            UserDto created = createData(i);
            dao.insert(created);

            if (i <= checkCnt) {
                UserDto found = service.find(String.valueOf(i));
                assertTrue(created.equals(found));
            }
        }
    }
        // 1-2. 모든 유저 조회
            // 1-2-0. 0명
            // 1-2-1. 1명
            // 1-2-2. 10명
            // 1-2-3. 100명
            // 1-2-4. 1000명
    @Test
    public void 유저_0_조회2() throws Exception {
        // given & when : 유저 0
        // do : service로 모든 유저 조회
        // assert(compare) : 길이 0
        cleanDB();
        List<UserDto> users = service.findAll();
        int actualLength = users.size();
        assertTrue(0 == actualLength);
    }

    @Test
    public void 유저_1_조회2() throws Exception {
        // given & when : 유저 1
        // do : service로 모든 유저 조회
        // assert(compare) : 길이 1
        cleanDB();
        insertData(1);

        List<UserDto> users = service.findAll();
        int actualLength = users.size();

        assertTrue(1 == actualLength);
    }

    @Test
    public void 유저_10_조회2() throws Exception {
        // given & when : 유저 10
        // do : service로 모든 유저 조회
        // assert(compare) : 길이 10
        cleanDB();
        insertData(10);

        List<UserDto> users = service.findAll();
        int actualLength = users.size();

        assertTrue(10 == actualLength);
    }

    @Test
    public void 유저_100_조회2() throws Exception {
        // given & when : 유저 100
        // do : service로 모든 유저 조회
        // assert(compare) : 길이 100
        cleanDB();
        insertData(100);

        List<UserDto> users = service.findAll();
        int actualLength = users.size();

        assertTrue(100 == actualLength);
    }

    @Test
    public void 유저_1000_조회2() throws Exception {
        // given & when : 유저 1000
        // do : service로 모든 유저 조회
        // assert(compare) : 길이 1000
        cleanDB();
        insertData(1000);

        List<UserDto> users = service.findAll();
        int actualLength = users.size();

        assertTrue(1000 == actualLength);
    }

        // 1-3. 특정 유저 삭제
            // 1-3-0. 해당 아이디가 없는 경우, null
            // 1-3-1. 1명 중에 1명 삭제
            // 1-3-2. 10명 중에 1명 삭제
            // 1-3-3. 100명 중에 15명 삭제
            // 1-3-4. 1000명 중에 25명 삭제
    @Test
    public void 유저_0_삭제1() throws Exception {
        // given & when : 유저 0
        // do : service로 특정 유저 삭제(1명)
        // assert(compare) : 길이 0, 적용로우수 0
        cleanDB();

        int rowCnt = service.remove("1");
        int totalCnt = service.getCount();

        assertTrue(0 == rowCnt);
        assertTrue(0 == totalCnt);
    }

    @Test
    public void 유저_1_삭제1() throws Exception {
        // given & when : 유저 1
        // do : service로 특정 유저 삭제(1명)
        // assert(compare) : 길이 0, 적용로우수 1
        cleanDB();
        insertData(1);

        int rowCnt = service.remove("1");
        int totalCnt = service.getCount();

        assertTrue(0 == totalCnt);
        assertTrue(1 == rowCnt);
    }

    @Test
    public void 유저_10_삭제1() throws Exception {
        // given & when : 유저 10
        // do : service로 특정 유저 삭제(1명)
        // assert(compare) : 길이 9, 적용로우수 1
        cleanDB();
        insertData(10);

        int rowCnt = service.remove("1");
        int totalCnt = service.getCount();

        assertTrue(9 == totalCnt);
        assertTrue(1 == rowCnt);
    }

    @Test
    public void 유저_100_삭제1() throws Exception {
        // given & when : 유저 100
        // do : service로 특정 유저 삭제(15명)
        // assert(compare) : 길이 85, 적용로우수 1 15번
        cleanDB();
        int size = 100;
        int cnt = 15;
        insertData(size);

        for (int i=1; i<=cnt; i++) {
            int rowCnt = service.remove(String.valueOf(i));
            assertTrue(1 == rowCnt);
        }
        int totalCnt = service.getCount();

        assertTrue(size-cnt == totalCnt);

    }

    @Test
    public void 유저_1000_삭제1() throws Exception {
        // given & when : 유저 1000
        // do : service로 특정 유저 삭제(25명)
        // assert(compare) : 길이 975, 적용로우수 25
        cleanDB();
        int size = 1000;
        int cnt = 25;
        insertData(size);

        for (int i=1; i<=cnt; i++) {
            int rowCnt = service.remove(String.valueOf(i));
            assertTrue(1 == rowCnt);
        }
        int totalCnt = service.getCount();

        assertTrue(size-cnt == totalCnt);
    }

        // 1-4. 모든 유저 삭제
            // 1-4-0. 0명
            // 1-4-1. 1명
            // 1-4-2. 10명
            // 1-4-3. 100명
            // 1-4-4. 1000명

    @Test
    public void 유저_0_삭제2() throws Exception {
        // given & when : 유저 0
        // do : service로 모든 유저 삭제
        // assert(compare) : 길이 0, 적용로우수 0
        cleanDB();

        int rowCnt = service.removeAll();
        int totalCnt = service.getCount();

        assertTrue(0 == totalCnt);
        assertTrue(0 == rowCnt);
    }

    @Test
    public void 유저_1_삭제2() throws Exception {
        // given & when : 유저 1
        // do : service로 모든 유저 삭제
        // assert(compare) : 길이 0, 적용로우수 1
        cleanDB();
        int size = 1;
        insertData(size);

        int rowCnt = service.removeAll();
        int totalCnt = service.getCount();

        assertTrue(0 == totalCnt);
        assertTrue(size == rowCnt);
    }

    @Test
    public void 유저_10_삭제2() throws Exception {
        // given & when : 유저 10
        // do : service로 모든 유저 삭제
        // assert(compare) : 길이 0, 적용로우수 10
        cleanDB();
        int size = 10;
        insertData(size);

        int rowCnt = service.removeAll();
        int totalCnt = service.getCount();

        assertTrue(0 == totalCnt);
        assertTrue(size == rowCnt);
    }

    @Test
    public void 유저_100_삭제2() throws Exception {
        // given & when : 유저 100
        // do : service로 모든 유저 삭제
        // assert(compare) : 길이 0, 적용로우수 100
        cleanDB();
        int size = 100;
        insertData(size);

        int rowCnt = service.removeAll();
        int totalCnt = service.getCount();

        assertTrue(0 == totalCnt);
        assertTrue(size == rowCnt);
    }

    @Test
    public void 유저_1000_삭제2() throws Exception {
        // given & when : 유저 1000
        // do : service로 모든 유저 삭제
        // assert(compare) : 길이 0, 적용로우수 1000
        cleanDB();
        int size = 1000;
        insertData(size);

        int rowCnt = service.removeAll();
        int totalCnt = service.getCount();

        assertTrue(0 == totalCnt);
        assertTrue(size == rowCnt);
    }
        // 1-5. 특정 유저 수정
            // 1-3-0. 해당 아이디가 없는 경우, null
            // 1-3-1. 1명 중에 1명 수정
            // 1-3-2. 10명 중에 1명 수정
            // 1-3-3. 100명 중에 15명 수정
            // 1-3-4. 1000명 중에 25명 수정

    @Test
    public void 유저_0_수정() throws Exception {
        // given & when : 유저 0
        // do : service로 없는 유저 수정
        // assert(compare) : 적용로우수 0
        cleanDB();
        UserDto updated = createUpdateData(1);
        int rowCnt = service.update(updated);
        assertTrue(0 == rowCnt);
    }

    @Test
    public void 유저_1_수정() throws Exception {
        // given & when : 유저 1
        // do : service로 없는 유저 수정
        // assert(compare) : 적용로우수 1, 내용비교
        cleanDB();
        int size = 1;
        insertData(size);
        UserDto updated = createUpdateData(1);

        int rowCnt = service.update(updated);
        UserDto found = service.find(String.valueOf(1));

        assertTrue(1 == rowCnt);
        assertTrue(updated.equals(found));
    }

    @Test
    public void 유저_10_수정() throws Exception {
        // given & when : 유저 10
        // do : service로 없는 유저 수정
        // assert(compare) : 적용로우수 1, 내용비교
        cleanDB();
        int size = 10;
        int cnt = 1;
        insertData(size);

        for (int i=1; i<=cnt; i++) {
            UserDto updated = createUpdateData(i);
            int rowCnt = service.update(updated);
            assertTrue(1 == rowCnt);
            UserDto found = service.find(updated.getId());
            assertTrue(updated.equals(found));
        }
    }

    @Test
    public void 유저_100_수정() throws Exception {
        // given & when : 유저 100
        // do : service로 없는 유저 수정
        // assert(compare) : 적용로우수 1, 내용비교
        cleanDB();
        int size = 100;
        int cnt = 15;
        insertData(size);

        for (int i=1; i<=cnt; i++) {
            UserDto updated = createUpdateData(i);

            int rowCnt = service.update(updated);
            assertTrue(1 == rowCnt);

            UserDto found = service.find(updated.getId());
            assertTrue(updated.equals(found));
        }
    }

    @Test
    public void 유저_1000_수정() throws Exception {
        // given & when : 유저 1000
        // do : service로 없는 유저 수정
        // assert(compare) : 적용로우수 25, 내용비교
        cleanDB();
        int size = 1000;
        int cnt = 25;
        insertData(size);

        for (int i=1; i<=cnt; i++) {
            UserDto updated = createUpdateData(i);

            int rowCnt = service.update(updated);
            assertTrue(1 == rowCnt);

            UserDto found = service.find(updated.getId());
            assertTrue(updated.equals(found));
        }
    }



    // 2. 보조 메서드
    private void cleanDB() throws Exception {
        dao.deleteAll();
    }

    private void insertData(int amount) throws Exception {
        for (int i=1; i<=amount; i++) {
            UserDto user = createData(i);
            dao.insert(user);
        }
    }

    private UserDto createData(int i) {
        UserDto user = new UserDto(String.valueOf(i), "1234", "user"+i, "user@mail.com", null, null, null);
        return user;
    }


    private UserDto createUpdateData(int i) {
        UserDto user = new UserDto(String.valueOf(i), "1234", "updatedUser"+i, "user@mail.com", null, null, null);
        return user;
    }
}