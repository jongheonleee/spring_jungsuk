package com.fastcampus.web3.service;

import com.fastcampus.web3.dao.CommentDao;
import com.fastcampus.web3.dto.CommentDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
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
public class CommentServiceImpTest extends TestCase {

    @Autowired
    DataSource ds;
    @Autowired
    private CommentService cs;

    @Autowired
    private BoardService bs;

    @Autowired
    private CommentDao cd;


    // 0. DI 테스트 ✅
    @Test
    public void testDSDI() {
        assertTrue(ds != null);
        assertTrue(ds instanceof  DataSource);
    }

    @Test
    public void testCSDI() {
        assertTrue(cs != null);
        assertTrue(cs instanceof  CommentService);
    }

    @Test
    public void testBSDI() {
        assertTrue(bs != null);
        assertTrue(bs instanceof BoardService);
    }

    @Test
    public void testCDDI() {
        assertTrue(cd != null);
        assertTrue(cd instanceof CommentDao);
    }

    // 1. 핵심 테스트 사항
        // 1-1. remove()
            // 저장된 데이터 순서대로 다 삭제
            // 1-1-0-0. empty
            // 1-1-0-1. 1
            // 1-1-0-2. 10
            // 1-1-0-3. 100
            // 1-1-0-4. 1000

            // 저장된 데이터 중 절반 랜덤으로 선택하고 데이터 삭제
            // 1-1-1-0. empty
            // 1-1-1-1. 1


        // 1-2. write()

        // 1-3. getList()

        // 1-4. read()

        // 1-5. modify()


    // 2. 보조 메서드
    private void cleanDB() throws Exception {
        Connection conn = ds.getConnection();
        String sql = "DELETE FROM COMMENT";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.executeUpdate();
    }

    private List<CommentDTO> createData(int amount, int bno) {
        List<CommentDTO> list = new ArrayList<>();

        for (int i=1; i<=amount; i++) {
            CommentDTO commentDTO = new CommentDTO(i, bno, "writer1", "content1", null, null);
            list.add(commentDTO);
        }

        return list;
    }

    private CommentDTO createUpdateData(CommentDTO commentDTO) {
        CommentDTO updateData = new CommentDTO(commentDTO.getCno(), commentDTO.getBno(),
                commentDTO.getWriter(), "updated content", null, null);
        return updateData;
    }
}