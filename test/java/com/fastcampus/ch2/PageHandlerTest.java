package com.fastcampus.ch2;


import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PageHandlerTest {

    @Test
    public void test() {
        // 11 12 13 14 15 16 17 18 19 20
        // currPage = 16
        // naviStart = 11
        // naviEnd = 20
        PageHandler ph = new PageHandler(2510, 16, 20);
        assertTrue(ph.getCurrPage() == 16);
        assertTrue(ph.getNaviStart() == 11);
        assertTrue(ph.getNaviEnd() == 20);
    }

    @Test
    public void test2() {
        // 1 2 3 4 5
        // currPage = 5
        // naviStart = 1
        // naviEnd = 5
        PageHandler ph = new PageHandler(91, 5, 20);
        assertTrue(ph.getCurrPage() == 5);
        assertTrue(ph.getNaviStart() == 1);
        assertTrue(ph.getNaviEnd() == 5);
    }
}