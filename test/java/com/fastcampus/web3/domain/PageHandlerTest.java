package com.fastcampus.web3.domain;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// 📌 콘솔에서 찍어서 확인하는 방식 개선
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class PageHandlerTest extends TestCase {

    @Test
    public void test1() {
        PageHandler ph = createPH(3, 200);
        ph.print();
    }

    @Test
    public void test2() {
        PageHandler ph = createPH(50, 500);
        ph.print();
    }

    @Test
    public void test3() {
        PageHandler ph = createPH(56, 560);
        ph.print();
    }


    private PageHandler createPH(int currPage, int totalCnt) {
        return new PageHandler(currPage, totalCnt);
    }
}