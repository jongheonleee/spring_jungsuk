package com.fastcampus.web3.dao;

import com.fastcampus.web3.dto.CommentDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.sql.DataSource;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class CommentDaoImpTest extends TestCase {

    @Autowired
    DataSource ds;

    @Autowired
    CommentDao dao;

    // 0. test setting
    @Test
    public void testDSDI() {
        assertTrue(ds != null);
        assertTrue(ds instanceof DataSource);
    }

    @Test
    public void testDAODI() {
        assertTrue(dao != null);
        assertTrue(dao instanceof CommentDao);
    }

    // 1-0. countAll ✅
        // 1-0-0. size = 0
        // 1-0-1. size = 10
        // 1-0-2. size = 100
        // 1-0-3. size = 1000

    @Test
    public void countAllEmptyDataTest() throws Exception {
        // given & when : clean table
        // do : count all data by dao
        // assert(compare) : compare 0 and count
        cleanDB();
        int actualCnt = dao.countAll();
        assertTrue(0 == actualCnt);
    }

    @Test
    public void countAllTenDataTest() throws Exception {
        // given & when : clean table, insert 10 data
        // do : count all data by dao
        // assert(compare) : compare 10 and count
        cleanDB();
        int size = 10;
        List<CommentDTO> datas = createData(size, 1);
        for (CommentDTO data : datas) {
            dao.insert(data);
        }

        int actualCnt = dao.countAll();
        assertTrue(size == actualCnt);
    }

    @Test
    public void countAllOneHundredDataTest() throws Exception {
        // given & when : clean table, insert 100 data
        // do : count all data by dao
        // assert(compare) : compare 100 and count
        cleanDB();
        int size = 100;
        List<CommentDTO> datas = createData(size, 1);
        for (CommentDTO data : datas) {
            dao.insert(data);
        }

        int actualCnt = dao.countAll();
        assertTrue(size == actualCnt);

    }
    @Test
    public void countAllOneThousandDataTest() throws Exception {
        // given & when : clean table, insert 1000 data
        // do : count all data by dao
        // assert(compare) : compare 1000 and count
        cleanDB();
        int size = 1000;
        List<CommentDTO> datas = createData(size, 1);
        for (CommentDTO data : datas) {
            dao.insert(data);
        }

        int actualCnt = dao.countAll();
        assertTrue(size == actualCnt);

    }

    // 1-1. count(bno) ✅
        // 1-1-0. size = 0
        // 1-1-1. size = 1
        // 1-1-2. size = 10, bno = 1, bno = 2
            // bno(1) => 5, bno(2) => 5
        // 1-1-3. size = 100, bno = 1, bno = 2
            // bno(1) => 50, bno(2) => 50
        // 1-1-4. size = 1000, bno = 1, bno = 2
            // bno(1) => 500, bno(2) => 500

    @Test
    public void countEmptyDataTest() throws Exception {
        // given & when : clean table
        // do : count data by dao
        // assert(compare) :  compare 0 and count
        cleanDB();
        int actualCnt = dao.count(1);
        assertTrue(0 == actualCnt);
    }

    @Test
    public void countOneDataTest() throws Exception {
        // given & when : clean table, insert data
        // do : count data by dao
        // assert(compare) : compare 1 and count
        cleanDB();
        int bno = 1;
        int size = 1;
        List<CommentDTO> datas = createData(size, bno);
        for (CommentDTO data : datas) {
            dao.insert(data);
        }

        int actualCnt = dao.count(bno);
        assertTrue(size == actualCnt);
    }


    @Test
    public void countTenDataTest() throws Exception {
        // given & when : clean table, insert 10 data, insert half of them into bno = 1, otherwise insert them into bno = 2
        // do : count data in bno = 1 by dao
        // assert(compare) : compare 5 and count
        cleanDB();
        int bno1 = 1;
        int bno2 = 2;
        int size = 10;
        List<CommentDTO> datas1 = createData(size/2, bno1);
        List<CommentDTO> datas2 = createData(size/2, bno2);
        List<CommentDTO> datas = Stream.concat(datas1.stream(), datas2.stream()).collect(Collectors.toList());
        for (CommentDTO data : datas) {
            dao.insert(data);
        }

        int actualCnt1 = dao.count(bno1);
        int actualCnt2 = dao.count(bno2);
        assertTrue(size/2 == actualCnt1);
        assertTrue(size/2 == actualCnt2);
    }

    @Test
    public void countOneHundredDataTest() throws Exception {
        // given & when : clean table, insert 100 data, insert half of them into bno = 1, otherwise insert them into bno = 2
        // do : count data in bno = 1 by dao
        // assert(compare) : compare 50 and count
        cleanDB();
        int bno1 = 1;
        int bno2 = 2;
        int size = 100;
        List<CommentDTO> datas1 = createData(size/2, bno1);
        List<CommentDTO> datas2 = createData(size/2, bno2);
        List<CommentDTO> datas = Stream.concat(datas1.stream(), datas2.stream()).collect(Collectors.toList());
        for (CommentDTO data : datas) {
            dao.insert(data);
        }

        int actualCnt1 = dao.count(bno1);
        int actualCnt2 = dao.count(bno2);
        assertTrue(size/2 == actualCnt1);
        assertTrue(size/2 == actualCnt2);
    }

    @Test
    public void countOneThousandDataTest() throws Exception {
        // given & when : clean table, insert 1000 data, insert half of them into bno = 1, otherwise insert them into bno = 2
        // do : count data in bno = 1 by dao
        // assert(compare) : compare 500 and count
        cleanDB();
        int bno1 = 1;
        int bno2 = 2;
        int size = 1000;
        List<CommentDTO> datas1 = createData(size/2, bno1);
        List<CommentDTO> datas2 = createData(size/2, bno2);
        List<CommentDTO> datas = Stream.concat(datas1.stream(), datas2.stream()).collect(Collectors.toList());
        for (CommentDTO data : datas) {
            dao.insert(data);
        }

        int actualCnt1 = dao.count(bno1);
        int actualCnt2 = dao.count(bno2);
        assertTrue(size/2 == actualCnt1);
        assertTrue(size/2 == actualCnt2);
    }

    // 1-1. select ✅
        // 1-1-0. empty
        // 1-1-1. 1
        // 1-1-2. 10
        // 1-1-3. 100
        // 1-1-4. 1000
    @Test
    public void selectEmptyDataTest() throws Exception {
        // given & when : clean table
        // do : select the data from table by dao
        // assert(compare) : compare null and data which found by dao
        cleanDB();
        List<CommentDTO> datas = createData(1, 1);
        CommentDTO data = datas.get(0);
        CommentDTO selectedData = dao.select(data);
        assertTrue(null == selectedData);
    }

    @Test
    public void selectOneDataTest() throws Exception {
        // given & when : clean table, insert 1 data
        // do : select the data from table by dao
        // assert(compare) : compare inserted data and selected data which found by dao
        cleanDB();
        List<CommentDTO> datas = createData(1, 1);
        for (CommentDTO data : datas) {
            dao.insert(data);
        }

        for (CommentDTO data : datas) {
            CommentDTO selectedData = dao.select(data);
            assertTrue(data.getContent().equals(selectedData.getContent()));
            assertTrue(data.getWriter().equals(selectedData.getWriter()));
        }
    }

    @Test
    public void selectTenDataTest() throws Exception {
        // given & when : clean table, insert 10 data
        // do : select the data from table by dao
        // assert(compare) : compare inserted data and selected data which found by dao 10 times
        cleanDB();
        int size = 10;
        List<CommentDTO> datas = createData(size, 1);
        for (CommentDTO data : datas) {
            dao.insert(data);
        }

        for (CommentDTO data : datas) {
            CommentDTO selectedData = dao.select(data);
            assertTrue(data.getContent().equals(selectedData.getContent()));
            assertTrue(data.getWriter().equals(selectedData.getWriter()));
        }

    }

    @Test
    public void selectOneHundredDataTest() throws Exception {
        // given & when : clean table, insert 100 data
        // do : select the data from table by dao
        // assert(compare) : compare inserted data and selected data which found by dao 100 times
        cleanDB();
        int size = 100;
        List<CommentDTO> datas = createData(size, 1);
        for (CommentDTO data : datas) {
            dao.insert(data);
        }

        for (CommentDTO data : datas) {
            CommentDTO selectedData = dao.select(data);
            assertTrue(data.getContent().equals(selectedData.getContent()));
            assertTrue(data.getWriter().equals(selectedData.getWriter()));
        }
    }


    @Test
    public void selectOneThousandDataTest() throws Exception {
        // given & when : clean table, insert 1000 data
        // do : select the data from table by dao
        // assert(compare) : compare inserted data and selected data which found by dao 1000 times
        cleanDB();
        int size = 1000;
        List<CommentDTO> datas = createData(size, 1);
        for (CommentDTO data : datas) {
            dao.insert(data);
        }

        for (CommentDTO data : datas) {
            CommentDTO selectedData = dao.select(data);
            assertTrue(data.getContent().equals(selectedData.getContent()));
            assertTrue(data.getWriter().equals(selectedData.getWriter()));
        }
    }


    // 1-2. selectAll ✅
        // 1-2-0. empty
        // 1-2-1. 1
        // 1-2-2. 10
        // 1-2-3. 100
        // 1-2-4. 1000
    @Test
    public void selectAllEmptyDataTest() throws Exception {
        // given & when : clean table
        // do : select all data from table by dao
        // assert(compare) : compare 0 and size of list
        cleanDB();
        int bno = 1;
        int size = 0;
        List<CommentDTO> datas = dao.selectAll(bno);
        assertTrue(size == datas.size());

    }

    @Test
    public void selectAllOneDataTest() throws Exception {
        // given & when : clean table, insert 1 data
        // do : select all data from table by dao
        // assert(compare) : compare 1 and size of list
        cleanDB();
        int bno = 1;
        int size = 1;
        List<CommentDTO> createdDatas = createData(size, bno);
        for (CommentDTO data : createdDatas) {
            dao.insert(data);
        }

        List<CommentDTO> selectedDatas = dao.selectAll(bno);
        assertTrue(size == selectedDatas.size());
    }

    @Test
    public void selectAllTenDataTest() throws Exception {
        // given & when : clean table, insert 10 data
        // do : select all data from table by dao
        // assert(compare) : compare 10 and size of list
        cleanDB();
        int bno = 1;
        int size = 10;
        List<CommentDTO> createdDatas = createData(size, bno);
        for (CommentDTO data : createdDatas) {
            dao.insert(data);
        }

        List<CommentDTO> selectedDatas = dao.selectAll(bno);
        assertTrue(size == selectedDatas.size());
    }

    @Test
    public void selectAllOneHundredDataTest() throws Exception {
        // given & when : clean table, insert 100 data
        // do : select all data from table by dao
        // assert(compare) : compare 100 and size of list
        cleanDB();
        int bno = 1;
        int size = 100;
        List<CommentDTO> createdDatas = createData(size, bno);
        for (CommentDTO data : createdDatas) {
            dao.insert(data);
        }

        List<CommentDTO> selectedDatas = dao.selectAll(bno);
        assertTrue(size == selectedDatas.size());
    }


    @Test
    public void selectAllOneThousandDataTest() throws Exception {
        // given & when : clean table, insert 1000 data
        // do : select all data from table by dao
        // assert(compare) : compare 1000 and size of list
        cleanDB();
        int bno = 1;
        int size = 1000;
        List<CommentDTO> createdDatas = createData(size, bno);
        for (CommentDTO data : createdDatas) {
            dao.insert(data);
        }

        List<CommentDTO> selectedDatas = dao.selectAll(bno);
        assertTrue(size == selectedDatas.size());
    }



    // 1-3. insert ✅
        // 1-3-0. 1
        // 1-3-1. 10
        // 1-3-2. 100
        // 1-3-3. 1000

    @Test
    public void insertOneDataTest() throws Exception {
        // given & when : clean table
        // do : insert one data into table by dao
        // assert(compare) : compare 1 and applied row count, 1 and total row count
        cleanDB();
        List<CommentDTO> datas = createData(1, 1);

        for (CommentDTO data : datas) {
            int actualRowCnt = dao.insert(data);
            assertTrue(1 == actualRowCnt);
        }

        int totalCnt = dao.countAll();
        assertTrue(1 == totalCnt);
    }

    @Test
    public void insertTenDataTest() throws Exception {
        // given & when : clean table
        // do : insert ten datas into table by dao
        // assert(compare) : compares 1 and applied row cnt, 10 and total row count
        cleanDB();
        int size = 10;
        List<CommentDTO> datas = createData(size, 1);

        for (CommentDTO data : datas) {
            int actualRowCnt = dao.insert(data);
            assertTrue(1 == actualRowCnt);
        }

        int totalRowCnt = dao.countAll();
        assertTrue(size == totalRowCnt);
    }
    @Test
    public void insertOneHundredDataTest() throws Exception {
        // given & when : clean table
        // do :
        // assert(compare) :
        cleanDB();
        int size = 100;
        List<CommentDTO> datas = createData(size, 1);

        for (CommentDTO data : datas) {
            int actualRowCnt = dao.insert(data);
            assertTrue(1 == actualRowCnt);
        }

        int totalRowCnt = dao.countAll();
        assertTrue(size == totalRowCnt);
    }
    @Test
    public void insertOneThousandDataTest() throws Exception {
        // given & when :
        // do :
        // assert(compare) :
        cleanDB();
        int size = 1000;
        List<CommentDTO> datas = createData(size, 1);

        for (CommentDTO data : datas) {
            int actualRowCnt = dao.insert(data);
            assertTrue(1 == actualRowCnt);
        }

        int totalRowCnt = dao.countAll();
        assertTrue(size == totalRowCnt);
    }


    // 1-4. update ✅
        // 1-4-0. empty

        // 1-4-1. 1

        // 1-4-2. 10
            // 1-4-2-0. all data
            // 1-4-2-1. random 5 data

        // 1-4-3. 100
            // 1-4-3-0. all data
            // 1-4-3-1. random 25 data

        // 1-4-4. 1000
            // 1-4-4-0. all data
            // 1-4-4-1. random 50 data

    @Test
    public void updateEmptyDataTest() throws Exception {
        // given & when : clean table
        // do : try updating the data by dao
        // assert(compare) : compare 0 and applied row count
        cleanDB();
        List<CommentDTO> datas = createData(1, 1);
        for (CommentDTO data : datas) {
            CommentDTO updateData = createUpdateData(data);
            int actualRowCnt = dao.update(updateData);
            assertTrue(0 == actualRowCnt);
        }
    }

    @Test
    public void updateOneDataTest() throws Exception {
        // given & when : clean table, insert 1 data
        // do : try updating that data by dao
        // assert(compare) : compare 1 and applied row count
        cleanDB();
        int size = 1;
        List<CommentDTO> datas = createData(size, 1);
        for (CommentDTO data : datas) {
            dao.insert(data);
            CommentDTO updateData = createUpdateData(data);
            int actualRowCnt = dao.update(updateData);
            assertTrue(1 == actualRowCnt);

            CommentDTO selectedData = dao.select(updateData);
            assertTrue(updateData.getWriter().equals(selectedData.getWriter()));
            assertTrue(updateData.getContent().equals(selectedData.getContent()));
        }
    }

    @Test
    public void updateTenDataTest1() throws Exception {
        // given & when : clean table, insert 10 data
        // do : try updating all data by dao
        // assert(compare) : compare 0 and applied row count and check whether those all filed were same 10 times
        cleanDB();
        int size = 10;
        List<CommentDTO> datas = createData(size, 1);
        for (CommentDTO data : datas) {
            dao.insert(data);
            CommentDTO updateData = createUpdateData(data);
            int actualRowCnt = dao.update(updateData);
            assertTrue(1 == actualRowCnt);

            CommentDTO selectedData = dao.select(updateData);
            assertTrue(updateData.getWriter().equals(selectedData.getWriter()));
            assertTrue(updateData.getContent().equals(selectedData.getContent()));
        }
    }

    @Test
    public void updateTenDataTest2() throws Exception {
        // given & when : clean table, insert 10 data, pick up random index from 1 to 10
        // do : try updating the data by dao
        // assert(compare) : compare 1 and applied row count and check whether those all filed were same 5 times
        cleanDB();
        int size = 10;
        int bno = 1;
        List<CommentDTO> datas = createData(size, bno);
        for (CommentDTO data : datas) {
            dao.insert(data);
        }

        for (int i=1; i<=5; i++) {
            int randomIdx = (int) (Math.random() * size);
            CommentDTO target = datas.get(randomIdx);
            CommentDTO updateData = createUpdateData(target);

            int actualRowCnt = dao.update(updateData);
            assertTrue(1 == actualRowCnt);

            CommentDTO selectedData = dao.select(target);
            assertTrue(updateData.getWriter().equals(selectedData.getWriter()));
            assertTrue(updateData.getContent().equals(selectedData.getContent()));
        }

    }

    @Test
    public void updateOneHundredDataTest1() throws Exception {
        // given & when : clean table, insert 100 data
        // do : try updating all data by dao
        // assert(compare) : compare 0 and applied row count and check whether those all filed were same 100 times
        cleanDB();
        int size = 100;
        List<CommentDTO> datas = createData(size, 1);
        for (CommentDTO data : datas) {
            dao.insert(data);
            CommentDTO updateData = createUpdateData(data);
            int actualRowCnt = dao.update(updateData);
            assertTrue(1 == actualRowCnt);

            CommentDTO selectedData = dao.select(updateData);
            assertTrue(updateData.getWriter().equals(selectedData.getWriter()));
            assertTrue(updateData.getContent().equals(selectedData.getContent()));
        }
    }

    @Test
    public void updateOneHundredDataTest2() throws Exception {
        // given & when : clean table, insert 100 data, pick up random index from 1 to 100 on 25 times
        // do : try updating the data by dao
        // assert(compare) : compare 1 and applied row count and check whether those all filed were same 25 times
        cleanDB();
        int size = 100;
        int bno = 1;
        List<CommentDTO> datas = createData(size, bno);
        for (CommentDTO data : datas) {
            dao.insert(data);
        }

        for (int i=1; i<=25; i++) {
            int randomIdx = (int) (Math.random() * size);
            CommentDTO target = datas.get(randomIdx);
            CommentDTO updateData = createUpdateData(target);

            int actualRowCnt = dao.update(updateData);
            assertTrue(1 == actualRowCnt);

            CommentDTO selectedData = dao.select(target);
            assertTrue(updateData.getWriter().equals(selectedData.getWriter()));
            assertTrue(updateData.getContent().equals(selectedData.getContent()));
        }
    }
    @Test
    public void updateOneThousandDataTest1() throws Exception {
        // given & when : clean table, insert 1000 data
        // do : try updating all data by dao
        // assert(compare) : compare 0 and applied row count and check whether those all filed were same 1000 times
        cleanDB();
        int size = 1000;
        List<CommentDTO> datas = createData(size, 1);
        for (CommentDTO data : datas) {
            dao.insert(data);
            CommentDTO updateData = createUpdateData(data);
            int actualRowCnt = dao.update(updateData);
            assertTrue(1 == actualRowCnt);

            CommentDTO selectedData = dao.select(updateData);
            assertTrue(updateData.getWriter().equals(selectedData.getWriter()));
            assertTrue(updateData.getContent().equals(selectedData.getContent()));
        }
    }

    @Test
    public void updateOneThousandDataTest2() throws Exception {
        // given & when : clean table, insert 1000 data, pick up random index from 1 to 1000 on 25 times
        // do : try updating the data by dao
        // assert(compare) : compare 1 and applied row count and check whether those all filed were same 25 times
        cleanDB();
        int size = 1000;
        int bno = 1;
        List<CommentDTO> datas = createData(size, bno);
        for (CommentDTO data : datas) {
            dao.insert(data);
        }

        for (int i=1; i<=50; i++) {
            int randomIdx = (int) (Math.random() * size);
            CommentDTO target = datas.get(randomIdx);
            CommentDTO updateData = createUpdateData(target);

            int actualRowCnt = dao.update(updateData);
            assertTrue(1 == actualRowCnt);

            CommentDTO selectedData = dao.select(target);
            assertTrue(updateData.getWriter().equals(selectedData.getWriter()));
            assertTrue(updateData.getContent().equals(selectedData.getContent()));
        }
    }

    // 1-5. delete ✅
        // 1-5-0. empty

        // 1-5-1. 1

        // 1-5-2. 10
            // pick up 2 random idx, and delete, check

        // 1-5-3. 100
            // pick up 20 random idx, and delete, check

        // 1-5-4. 1000
            // pick up 200 random idx, and delete, check
    @Test
    public void deleteEmptyDataTest() throws Exception {
        // given & when : clean table
        // do : delete by dao
        // assert(compare) : compare between 0 and actual applied row count
        cleanDB();
        List<CommentDTO> datas = createData(1, 1);
        CommentDTO testData = datas.get(0);

        int actualRowCnt = dao.delete(testData);
        assertTrue(0 == actualRowCnt);
    }

    @Test
    public void deleteOneDataTest() throws Exception {
        // given & when : clean table, insert 1 data
        // do : delete that data by dao
        // assert(compare) : compare between 1 and actual applied row count, 0 and total length of elements in table
        cleanDB();
        List<CommentDTO> datas = createData(1, 1);
        for (CommentDTO data : datas) {
            dao.insert(data);
            int actualRowCnt = dao.delete(data);
            assertTrue(1 == actualRowCnt);
        }

        int totalRowCnt = dao.count(1);
        assertTrue(0 == totalRowCnt);
    }

    @Test
    public void deleteTenDataTest() throws Exception {
        // given & when : clean table, insert 10 data
        // do : delete those data by dao
        // assert(compare) : compare between 1 and actual applied row count, 0 and total length of elements in table
        cleanDB();
        List<CommentDTO> datas = createData(10, 1);
        for (CommentDTO data : datas) {
            dao.insert(data);
            int actualRowCnt = dao.delete(data);
            assertTrue(1 == actualRowCnt);
        }

        int totalRowCnt = dao.count(1);
        assertTrue(0 == totalRowCnt);
    }

    @Test
    public void deleteTenDataTest2() throws Exception {
        // given & when : clean table, insert 10 data
        // do : pick up random index, delete the data which is on table by dao, repeat that work 5 times
        // assert(compare) : compare between 1 and actual applied row count, 0 and total length of elements in table
        cleanDB();
        int size = 10;
        List<CommentDTO> datas = createData(size, 1);
        for (CommentDTO data : datas) {
            dao.insert(data);
        }

        Set<Integer> set = new HashSet<>();
        for (;;) {
            if (set.size() == size/2) break;

            int randomIdx = (int)(Math.random() * size);
            if (set.contains(randomIdx)) continue;

            set.add(randomIdx);
            CommentDTO data = datas.get(randomIdx);
            int actualRowCnt = dao.delete(data);
            assertTrue(1 == actualRowCnt);
        }

        int totalRowCnt = dao.count(1);
        assertTrue(size/2 == totalRowCnt);
    }

    @Test
    public void deleteOneHundredDataTest() throws Exception {
        // given & when : clean table, insert 100 data
        // do : delete those data by dao
        // assert(compare) : compare between 1 and actual applied row count, 0 and total length of elements in table
        cleanDB();
        List<CommentDTO> datas = createData(100, 1);
        for (CommentDTO data : datas) {
            dao.insert(data);
            int actualRowCnt = dao.delete(data);
            assertTrue(1 == actualRowCnt);
        }

        int totalRowCnt = dao.count(1);
        assertTrue(0 == totalRowCnt);
    }

    @Test
    public void deleteOneHundredDataTest2() throws Exception {
        // given & when : clean table, insert 100 data
        // do : pick up random index, delete the data which is on table by dao, repeat that work 50 times
        // assert(compare) : compare between 1 and actual applied row count, 0 and total length of elements in table
        cleanDB();
        int size = 100;
        List<CommentDTO> datas = createData(size, 1);
        for (CommentDTO data : datas) {
            dao.insert(data);
        }

        Set<Integer> set = new HashSet<>();
        for (;;) {
            if (set.size() == size/2) break;

            int randomIdx = (int)(Math.random() * size);
            if (set.contains(randomIdx)) continue;

            set.add(randomIdx);
            CommentDTO data = datas.get(randomIdx);
            int actualRowCnt = dao.delete(data);
            assertTrue(1 == actualRowCnt);
        }

        int totalRowCnt = dao.count(1);
        assertTrue(size/2 == totalRowCnt);
    }

    @Test
    public void deleteOneThousandDataTest() throws Exception {
        // given & when : clean table, insert 1000 data
        // do : delete those data by dao
        // assert(compare) : compare between 1 and actual applied row count, 50 and total length of elements in table
        cleanDB();
        List<CommentDTO> datas = createData(1000, 1);
        for (CommentDTO data : datas) {
            dao.insert(data);
            int actualRowCnt = dao.delete(data);
            assertTrue(1 == actualRowCnt);
        }

        int totalRowCnt = dao.count(1);
        assertTrue(0 == totalRowCnt);
    }

    @Test
    public void deleteOneThousandDataTest2() throws Exception {
        // given & when : clean table, insert 1000 data
        // do : pick up random index, delete the data which is on table by dao, repeat that work 500 times
        // assert(compare) : compare between 1 and actual applied row count, 500 and total length of elements in table
        cleanDB();
        int size = 1000;
        List<CommentDTO> datas = createData(size, 1);
        for (CommentDTO data : datas) {
            dao.insert(data);
        }

        Set<Integer> set = new HashSet<>();
        for (;;) {
            if (set.size() == size/2) break;

            int randomIdx = (int)(Math.random() * size);
            if (set.contains(randomIdx)) continue;

            set.add(randomIdx);
            CommentDTO data = datas.get(randomIdx);
            int actualRowCnt = dao.delete(data);
            assertTrue(1 == actualRowCnt);
        }

        int totalRowCnt = dao.count(1);
        System.out.println(totalRowCnt);
        assertTrue(size/2 == totalRowCnt);
    }

    // 1-6. deleteAll
        // 1-6-0. empty
        // 1-6-1. 1
        // 1-6-2. 10
        // 1-6-3. 100
        // 1-6-4. 1000

    @Test
    public void deleteAllEmptyData() throws Exception {
        // given & when : clean table
        // do : delete all by dao
        // assert(compare) : compare between 0 and total row cnt
        cleanDB();
        int bno = 1;
        int appliedRowCnt = dao.deleteAll(bno);
        int actualTotalRowCnt = dao.count(bno);
        assertTrue(0 == appliedRowCnt);
        assertTrue(0 == actualTotalRowCnt);
    }

    @Test
    public void deleteAllOneData() throws Exception {
        // given & when : clean table, insert 1 data
        // do : delete all by dao
        // assert(compare) : compare between 0 and total row cnt
        cleanDB();
        int bno = 1;
        int size =1;
        List<CommentDTO> datas = createData(size, bno);

        for (CommentDTO data : datas) {
            dao.insert(data);
        }

        int actualAppliedRowCnt = dao.deleteAll(bno);
        int actualTotalRowCnt = dao.count(bno);

        assertTrue(size == actualAppliedRowCnt);
        assertTrue(0 == actualTotalRowCnt);
    }

    @Test
    public void deleteAllTenData() throws Exception {
        // given & when : clean table, insert 10 data
        // do : delete all by dao
        // assert(compare) : compare between 0 and total row cnt
        cleanDB();
        int bno = 1;
        int size = 10;
        List<CommentDTO> datas = createData(size, bno);

        for (CommentDTO data : datas) {
            dao.insert(data);
        }

        int actualAppliedRowCnt = dao.deleteAll(bno);
        int actualTotalRowCnt = dao.count(bno);

        assertTrue(size == actualAppliedRowCnt);
        assertTrue(0 == actualTotalRowCnt);
    }

    @Test
    public void deleteAllOneHundredData() throws Exception {
        // given & when : clean table, insert 100 data
        // do : delete all by dao
        // assert(compare) : compare between 0 and total row cnt
        cleanDB();
        int bno = 1;
        int size = 100;
        List<CommentDTO> datas = createData(size, bno);

        for (CommentDTO data : datas) {
            dao.insert(data);
        }

        int actualAppliedRowCnt = dao.deleteAll(bno);
        int actualTotalRowCnt = dao.count(bno);

        assertTrue(size == actualAppliedRowCnt);
        assertTrue(0 == actualTotalRowCnt);
    }

    @Test
    public void deleteAllOneThousandData() throws Exception {
        // given & when : clean table, insert 1000 data
        // do : delete all by dao
        // assert(compare) : compare between 0 and total row cnt
        cleanDB();
        int bno = 1;
        int size = 1000;
        List<CommentDTO> datas = createData(size, bno);

        for (CommentDTO data : datas) {
            dao.insert(data);
        }

        int actualAppliedRowCnt = dao.deleteAll(bno);
        int actualTotalRowCnt = dao.count(bno);

        assertTrue(size == actualAppliedRowCnt);
        assertTrue(0 == actualTotalRowCnt);
    }






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