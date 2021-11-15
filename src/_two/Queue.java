package _two;

import java.util.*;
import java.io.*;

public class Queue {
	class Link{
		Object elm;
		Link tail;
		
		Link(Object _elm){
			elm = _elm;
			tail = null;
		}
	}
	private Link head;
	private Link end;
	
	Queue(){
		head = end = null;
	}
	public void enqueue(Object _elm){
		if (isEmpty()) {
			head = end = new Link(_elm);
		} else {
			head.tail = new Link(_elm);
			head = head.tail;
		}
	}
	public Object dequeue() {
		if (isEmpty()) {
			return null;
		}
		Object temp = end.elm;
		end = end.tail;
		head = end == null ? null : head;
		return temp;
	}
	public boolean isEmpty() {
		return head == null;
	}
	public static void radixSort1(Scanner sc) {
		Queue temp = new Queue();
		int max = 0;
		while (sc.hasNextInt()) {
			int k = sc.nextInt();
			max = max > Math.abs(k) ? max : Math.abs(k);
			temp.enqueue(k);
		}
		if (max == 0) {
			while (!temp.isEmpty()) {
				System.out.print(temp.dequeue() + " ");
			}
		}
		Queue bucket[] = new Queue[10];
		for (int i = 0; i < 10; i++) {
			bucket[i] = new Queue();
		}
	    for (int i = 1; max / i >= 1; i *= 10) {
	    	while (!temp.isEmpty()) {
	    		int t = (int)temp.dequeue();
	    		bucket[(t / i) % 10].enqueue(t);
	    	}
	    	for (int j = 0; j < 10; j++) {
	    		while(!bucket[j].isEmpty()) {
	    			temp.enqueue(bucket[j].dequeue());
	    		}
	    	}
	    }
	    while (!temp.isEmpty()) {
	    	System.out.print(temp.dequeue() + " ");
	    }
	}
	
	public static void radixSort2(Scanner sc) {
		Queue temp = new Queue();
		String s = sc.next();
		int width = s.length();
		temp.enqueue(s);
		while (sc.hasNext()) {
			temp.enqueue(sc.next());
		}
		Queue bucket[] = new Queue[52];
		for (int i = 0; i < 52; i++) {
			bucket[i] = new Queue();
		}
	    for (int i = width - 1; i >= 0; i--) {
	    	while (!temp.isEmpty()) {
	    		String t = (String)temp.dequeue();
	    		char ch = t.charAt(i);
	    		bucket[ch >= '\u0061' ? ch - '\u0061' + 26 : ch - '\u0041'].enqueue(t);
	    	}
	    	for (int j = 0; j < 52; j++) {
	    		while(!bucket[j].isEmpty()) {
	    			temp.enqueue(bucket[j].dequeue());
	    		}
	    	}
	    }
	    while (!temp.isEmpty()) {
	    	System.out.print(temp.dequeue() + " ");
	    }
	}
	
	public static void radixSort3(Scanner sc) {
		Queue temp = new Queue();
		int maxWidth = 0;
		while (sc.hasNext()) {
			String s = sc.next();
			maxWidth = maxWidth > s.length() ? maxWidth : s.length();
			temp.enqueue(s);
		}
		Queue bucket[] = new Queue[53];
		for (int i = 0; i < 53; i++) {
			bucket[i] = new Queue();
		}
	    for (int i = 1; i <= maxWidth; i++) {
	    	while (!temp.isEmpty()) {
	    		String t = (String)temp.dequeue();
	    		if (i <= t.length()) {
		    		char ch = t.charAt(t.length() - i);
		    		bucket[ch >= '\u0061' ? ch - '\u0061' + 26 : ch - '\u0041'].enqueue(t);
	    		} else {
	    			bucket[52].enqueue(t);
	    		}
	    		
	    	}
	    	for (int j = 0; j < 53; j++) {
	    		while(!bucket[j].isEmpty()) {
	    			temp.enqueue(bucket[j].dequeue());
	    		}
	    	}
	    }
	    while (!temp.isEmpty()) {
	    	System.out.print(temp.dequeue() + " ");
	    }
	}
	
	public static void main(String args[]) {
		try {
			Scanner sc = new Scanner(new FileInputStream("radixSort3.txt"));
			radixSort3(sc);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
