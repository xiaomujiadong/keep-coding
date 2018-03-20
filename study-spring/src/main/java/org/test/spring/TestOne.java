package org.test.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={ "classpath:spring/applicationContext.xml" })
public class TestOne {

    @Autowired
    @Qualifier("person")
    private IGodSpring te;

    @Test
    public void test(){
//        System.out.println("test");
//        System.out.println(person);

        te.info();
    }
}

