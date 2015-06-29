package br.com.robsonperassoli.todo.util;

import java.util.ArrayList;
import java.util.List;

public class Iterables {

	public static <E> List<E> makeList(Iterable<E> iter) {
	    List<E> list = new ArrayList<E>();
	    for (E item : iter) {
	        list.add(item);
	    }
	    return list;
	}

	
}
