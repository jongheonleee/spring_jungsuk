package com.fastcampus.web3.test;

import com.fastcampus.web3.dto.UserDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *     // executeUpdate() : 반영된 레코드의 건수 반환
 *
 *     // 0. ds 주입 확인
 *         // ds 널이 아닌지 확인
 *
 *     // 1. 데이타 베이스 연결 획득
 *         // ds 로부터 Connection 획득
 *         // 널이 아닌지 확인
 *
 *     // 2. insert user_info
 *         // 테이블 초기화
 *         // 유저 객체 생성
 *
 *         // 테이블에 객체 주입
 *             // 커넥션 획득
 *             // sql 문 작성
 *             // pstmt 획득
 *             // 바인딩 변수 할당
 *             // 로우수 카운트
 *
 *         // 기대한 값과 비교
 *
 *     // 3. select user_info
 *         // 테이블 초기화
 *         // 유저 객체 생성
 *         // 테이블에 객체 주입
 *
 *         // 생성된 객체 조회
 *             // 커넥션 획득
 *             // sql 문 작성
 *             // pstmt 획득
 *             // 바인딩 변수 할당
 *             // 조회된 객체 반환
 *
 *         // 생성된 객체와 조회된 객체 비교
 *
 *     // 4. update user_info
 *         // 테이블 초기화
 *         // 유저 객체 생성(아이디 동일)
 *         // 테이블에 객체 주입
 *
 *         // 수정 정보 담은 객체 생성(아이디 동일)
 *         // 아이디 조회해서 수정 정보 업데이트
 *             // 커넥션 획득
 *             // sql 문 작성
 *             // pstmt 획득
 *             // 바인딩 변수 할당
 *             // 반영된 레코드의 건수 반환
 *
 *         // 기대한 결과와 반환값 비교
 *
 *
 *     // 5. delete user_info
 *         // 테이블 초기화
 *         // 객체 생성
 *         // 테이블 객체 주입
 *         // 반영된 레코드의 건수 반환, 기대값과 비교
 *
 *         // 생성된 객체 아이디 값으로 테이블 로우 삭제
 *             // 커넥션 획득
 *             // sql 문 작성
 *             // 바인딩 변수 할당
 *             // 반영된 레코드 건수 반환
 *
 *         // 기대값 비교
 *
 *     // 그외 보조 메서드
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class UserDtoTest extends TestCase {
    @Autowired
    DataSource ds;

    // executeUpdate() : 반영된 레코드의 건수 반환

    // 0. ds 주입 확인
        // ds 널이 아닌지 확인
    @Test
    public void checkDataSource() {
        assertTrue(ds != null);
        assertTrue(ds instanceof DataSource);
    }

    // 1. 데이타 베이스 연결 획득
        // ds 로부터 Connection 획득
        // 널이 아닌지 확인
    @Test
    public void checkConn() throws SQLException {
        Connection conn = ds.getConnection();
        assertTrue(conn != null);
        assertTrue(conn instanceof Connection);
    }

    // 2. insert user_info
        // 테이블 초기화
        // 유저 객체 생성

        // 테이블에 객체 주입
            // 커넥션 획득
            // sql 문 작성
            // pstmt 획득
            // 바인딩 변수 할당
            // 로우수 카운트

        // 기대한 값과 비교
    @Test
    public void insertUserTest() throws SQLException {
        cleanData();
        UserDto createdUserDTODTO = new UserDto("asdf3", "1234", "abc", "aaa@aaa.com", new java.util.Date(), "fb", new java.util.Date());
        int rowCnt = insertData(createdUserDTODTO);
        assertTrue(1 == rowCnt);
    }
    // 3. select user_info
        // 테이블 초기화
        // 유저 객체 생성
        // 테이블에 객체 주입

        // 생성된 객체 조회
            // 커넥션 획득
            // sql 문 작성
            // pstmt 획득
            // 바인딩 변수 할당
            // 조회된 객체 반환

        // 생성된 객체와 조회된 객체 비교

    // 4. update user_info
        // 테이블 초기화
        // 유저 객체 생성(아이디 동일)
        // 테이블에 객체 주입

        // 수정 정보 담은 객체 생성(아이디 동일)
        // 아이디 조회해서 수정 정보 업데이트
            // 커넥션 획득
            // sql 문 작성
            // pstmt 획득
            // 바인딩 변수 할당
            // 반영된 레코드의 건수 반환

        // 기대한 결과와 반환값 비교


    // 5. delete user_info
        // 테이블 초기화
        // 객체 생성
        // 테이블 객체 주입
        // 반영된 레코드의 건수 반환, 기대값과 비교

        // 생성된 객체 아이디 값으로 테이블 로우 삭제
            // 커넥션 획득
            // sql 문 작성
            // 바인딩 변수 할당
            // 반영된 레코드 건수 반환

        // 기대값 비교

    // 그외 보조 메서드
    private void cleanData() throws SQLException {
        // 1. DataSource로부터 DB 연결을 가져온다.
        Connection conn = ds.getConnection();

        // 2. sql문 작성
        String sql = "truncate user_info";
        PreparedStatement pstmt = conn.prepareStatement(sql); // SQL Injection 공격, 성능 향상(sql 재사용 가능하기 때문에 실행시간 빨라짐)

        // 3. sql문 실행
        pstmt.executeUpdate(); // insert, delete, update / 몇 개의 행이 영향을 받았는지 반환된다. DML에 사용 가능(select 제외)

    }
    // 테이블에 객체 주입
    // 커넥션 획득
    // sql 문 작성
    // pstmt 획득
    // 바인딩 변수 할당
    // 로우수 카운트

    // 기대한 값과 비교
    private int insertData(UserDto userDTO) throws SQLException {
        Connection conn = ds.getConnection();

        String sql = "insert into user_info values (?, ?, ?, ?, ?, ?, now())";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, userDTO.getId());
        pstmt.setString(2, userDTO.getPwd());
        pstmt.setString(3, userDTO.getName());
        pstmt.setString(4, userDTO.getEmail());
        pstmt.setDate(5, new java.sql.Date(userDTO.getBirth().getTime()));
        pstmt.setString(6, userDTO.getSns());

        return pstmt.executeUpdate();
    }
}