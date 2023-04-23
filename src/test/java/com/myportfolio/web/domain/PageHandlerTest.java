//package com.myportfolio.web.domain;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import static org.junit.Assert.*;
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
//public class PageHandlerTest {
//
//    @Test
//    public void test() {
//        PageHandler ph = new PageHandler(250, );
//        ph.print();
//        System.out.println("ph = " + ph);
//        assertTrue(ph.getTotalPage()==25);
//        assertTrue(ph.getBeginPage()==11);
//        assertTrue(ph.getEndPage()==20);
//    }
//
//    @Test
//    public void test2() {
//        PageHandler ph = new PageHandler(277, 28);
//        ph.print();
//        System.out.println("ph = " + ph);
//        assertTrue(ph.getTotalPage()==28);
//        assertTrue(ph.getBeginPage()==21);
//        assertTrue(ph.getEndPage()==28);
//    }
//}