package com.fastcampus.web3.test;

import com.fastcampus.web3.dto.TmpUserDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.sql.DataSource;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// 📌 구현 완료되면, 테스트 코드 강화, 매개변수 전달받아서 여러번 테스트 할 수 있게 만들기
//    그럴려면, junit5로 테스트 코드 돌려야함
@DisplayName("spring이랑 DB 연결 테스트")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class DBConnectionTestForUserTestDTO extends TestCase {

    @Autowired
    DataSource ds;

    @Test
    @DisplayName("@AutoWired 주입 테스트")
    public void main() throws Exception {
        Connection conn = ds.getConnection();
        assertTrue(conn != null);
    }

    @Test
    @DisplayName("데이타 소스 연동 태스트")
    public void connectDataSourceTest() throws Exception {
        // 스키마의 이름(springbasic)이 다른 경우 알맞게 변경
        String DB_URL = "jdbc:mysql://localhost:3306/spring_basic?useUnicode=true&characterEncoding=utf8";

        // DB의 userid와 pwd를 알맞게 변경
        String DB_USER = "root";
        String DB_PASSWORD = "12341234";
        String DB_DRIVER = "com.mysql.jdbc.Driver";

        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(DB_DRIVER);
        ds.setUrl(DB_URL);
        ds.setUsername(DB_USER);
        ds.setPassword(DB_PASSWORD);

        Connection conn = ds.getConnection(); // 데이터베이스의 연결을 얻는다.
        System.out.println("conn = " + conn);
    }

    @Test
    @DisplayName("test_user -> insert")
    public void insertUserTest() throws Exception {
        deleteAll();
        TmpUserDto user = new TmpUserDto("1", "aa", "aa");
        int rowCnt = insertUser(user);
        assertTrue(rowCnt == 1);
    }

    @Test
    @DisplayName("test_user -> select")
    public void selectUserTest() throws Exception {
        deleteAll();
        TmpUserDto user = new TmpUserDto("1", "1234", "dw");

        int rowCnt = insertUser(user);

        assertTrue(1 == rowCnt);
        assertTrue(user.getId().equals("1"));

    }

    @Test
    @DisplayName("test_user -> update")
    public void updateUserTest() throws Exception  {
        deleteAll();

        TmpUserDto user = new TmpUserDto("1", "asdf", "1234");
        insertUser(user);

        TmpUserDto updatedUser = new TmpUserDto("1", "asdf2", "1234");
        updateUser(updatedUser);
        TmpUserDto user2 = selectUser(updatedUser.getId());

        System.out.println("user.toString().equals(user2.toString()) = " + user.toString().equals(user2.toString()));
        System.out.println("updatedUser.toString() = " + updatedUser);
        System.out.println("user2.toString() = " + user2);

        assertTrue(updatedUser.toString().equals(user2.toString()));
    }

    @Test
    @DisplayName("test_user -> delete")
    public void deleteUserTest() throws Exception {
        deleteAll();
        int rowCnt = deleteUser("1");
        assertTrue(rowCnt == 0);

        TmpUserDto user = new TmpUserDto("1", "asdf", "1234");
        rowCnt = insertUser(user);
        assertTrue(rowCnt == 1);

        rowCnt = deleteUser(user.getId());
        assertTrue(rowCnt == 1);
        assertTrue(selectUser(user.getId()) == null);
    }

    public int insertUser(TmpUserDto user) throws Exception {
        Connection conn = ds.getConnection();

        String sql = "insert into user_test values (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, user.getId());
        pstmt.setString(2, user.getUser_name());
        pstmt.setString(3, user.getUser_pwd());

        return pstmt.executeUpdate();
    }

    private void deleteAll() throws Exception {
        // 1. DataSource로부터 DB 연결을 가져온다.
        Connection conn = ds.getConnection();

        // 2. sql문 작성
        String sql = "truncate user_test";
        PreparedStatement pstmt = conn.prepareStatement(sql); // SQL Injection 공격, 성능 향상(sql 재사용 가능하기 때문에 실행시간 빨라짐)

        // 3. sql문 실행
        pstmt.executeUpdate(); // insert, delete, update / 몇 개의 행이 영향을 받았는지 반환된다. DML에 사용 가능(select 제외)
    }


    public TmpUserDto selectUser(String id) throws Exception {
        Connection conn = ds.getConnection();

        String sql = "select * from user_test where id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql); // SQL Injection 공격, 성능 향상(sql 재사용 가능하기 때문에 실행시간 빨라짐)
        pstmt.setString(1, id);

        ResultSet rs = pstmt.executeQuery(); // select

        // 쿼리 결과가 있으면 값을 채워서 반환한다.
        if (rs.next()) {
            TmpUserDto user = new TmpUserDto();
            user.setId(rs.getString(1));
//            user.setPwd(rs.getString(2));
//            user.setName(rs.getString(3));
            return user;
        }

        return null;
    }

    // id에 해당하는 user 삭제
    public int deleteUser(String id) throws Exception {
        Connection conn = ds.getConnection();

        String sql = "delete from user_test where id = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);

        return pstmt.executeUpdate(); // insert, update, delete
    }

    public int updateUser(TmpUserDto user) throws Exception {
        Connection conn = ds.getConnection();

        String sql = "update user_test set user_name = ?, user_pwd = ? where id = ?";
        PreparedStatement pstms = conn.prepareStatement(sql);

//        pstms.setString(1, user.getPwd());
//        pstms.setString(2, user.getName());
        pstms.setString(3, user.getId());

        return pstms.executeUpdate();
    }

}