package br.com.robsonperassoli.todo.util;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class IterablesTest {

    @Test
    public void testMakeList(){
        Iterables iterablesUtil = new Iterables();

        Iterable<Integer> iterable = Arrays.asList(1,2,3,4);
        List<Integer> resultList = iterablesUtil.makeList(iterable);

        assertEquals("The first integer should be 1", Integer.valueOf(1), resultList.get(0));
        assertEquals("The first integer should be 4", Integer.valueOf(4), resultList.get(3));
    }
}
