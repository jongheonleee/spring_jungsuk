package com.fastcampus.web3.dao;

import com.fastcampus.web3.dao.TmpUserDaoImp;
import com.fastcampus.web3.dto.TmpUserDTO;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class TmpUserDTODaoImpTestDTO extends TestCase {

    @Autowired
    DataSource ds;

    @Autowired
    TmpUserDaoImp tmpUserDaoImp;

    @Test
    public void test() throws SQLException {
        cleanData();
        TmpUserDTO tu = new TmpUserDTO("1", "aa", "aa");
        insertData(tu);

        TmpUserDTO selectedUser = tmpUserDaoImp.selectUser("1");
        System.out.println(selectedUser);

  }

    private int insertData(TmpUserDTO user) throws SQLException {
        Connection conn = ds.getConnection();

        String sql = "insert into user_test values (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, user.getId());
        pstmt.setString(2, user.getUser_pwd());
        pstmt.setString(3, user.getUser_name());

        return pstmt.executeUpdate();
    }

    private void cleanData() throws SQLException {
        // 1. DataSource로부터 DB 연결을 가져온다.
        Connection conn = ds.getConnection();

        // 2. sql문 작성
        String sql = "truncate user_test";
        PreparedStatement pstmt = conn.prepareStatement(sql); // SQL Injection 공격, 성능 향상(sql 재사용 가능하기 때문에 실행시간 빨라짐)

        // 3. sql문 실행
        pstmt.executeUpdate(); // insert, delete, update / 몇 개의 행이 영향을 받았는지 반환된다. DML에 사용 가능(select 제외)

    }


}