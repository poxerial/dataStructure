package _two;

import java.io.*;
import java.util.Scanner;

public class Stack {
	class Link{
		Object elm;
		Link tail;
		
		Link(Object _elm, Link _tail){
			elm = _elm;
			tail = _tail;
		}
	}
	private Link top;
	
	public Stack() { top = null; }	
	public void clear() { top = null; }
	public void push(Object _elm) { top = new Link(_elm, top); }
	public boolean isEmpty() { return top == null;}
	public Object pop() {
		if (isEmpty()) { return null; }//no element can be popped
		Object r = top.elm;
		top = top.tail;
		return r;
	}
	public Object topValue() {
		if (isEmpty()) { return null; }//no top element can be returned
		return top.elm;
	}
	
	public static boolean testPalindromeByRecursion(String s) {
		if (s.charAt(0) == s.charAt(s.length() - 1)) {
			if (s.length() <= 2) {return true;}
			else {return testPalindromeByRecursion(s.substring(1, s.length() - 1));}
		} else {return false;}
	}
	
	public static boolean testPalindromeByStack(String s) {
		Stack temp = new Stack();
		int i = 0;
		for (; i < s.length() / 2; i++) {
			temp.push(s.charAt(i));
		}
		for (i = s.length() - i; i < s.length(); i++) {
			if ((char)temp.pop() != s.charAt(i)) {return false;}
		}
		return true;
	}
	
	public static boolean mazeTravelByRecursive(StringBuffer rst, StringBuffer maze, int edge,int start_index, int dest_index) {
		int index = start_index;
		if (index - edge >= 0) {
			if (maze.charAt(index - edge) == '\u25CB') {
				maze.delete(index, index + 1);
				maze.insert(index, '\u2605');
				boolean b = mazeTravel(rst, maze, edge, index - edge, dest_index);
				if (b) {
					return b;
				}
			}
		}
		if (index + 1 < maze.length() && (index + 1) % edge != 0) {
			if (maze.charAt(index + 1) == '\u25CB') {
				maze.delete(index, index + 1);
				maze.insert(index, '\u2605');
				boolean b = mazeTravel(rst, maze, edge, index + 1, dest_index);
				if (b) {
					return b;
				}
			}
		}
		if (index + edge < maze.length()) {
			if (maze.charAt(index + edge) == '\u25CB') {
				maze.delete(index, index + 1);
				maze.insert(index, '\u2605');
				boolean b = mazeTravel(rst, maze, edge, index + edge, dest_index);
				if (b) {
					return b;
				}
			}
		}
		if (index - 1 > 0 && index % edge != 0) {
			if (maze.charAt(index - 1) == '\u25CB') {
				maze.delete(index, index + 1);
				maze.insert(index, '\u2605');
				boolean b = mazeTravel(rst, maze, edge, index - 1, dest_index);
				if (b) {
					return b;
				}
			}
		}
		return false;
	}
	public static boolean mazeTravel(StringBuffer rst, StringBuffer maze, int edge, int index, int dest_index) {
		if (index == dest_index) {
			return true;
		} else {
			int y = index / edge;
			int x = index % edge;
			boolean b = mazeTravelByRecursive(rst, maze, edge, index, dest_index);
			if (b) {
				rst.insert(0, "(" + x + "," + y + ") ");
			}
			return b;
		}
	}
	
	public static boolean mazeTravelByStack(StringBuffer rst, StringBuffer maze, int edge, int start_index, int dest_index) {
		Stack st = new Stack();
		int index = start_index;
		while (true) {
			if (index == dest_index) {
				st.pop();
				while (!st.isEmpty()) {
					int i = (int)st.pop();
					int y = i / edge;
					int x = i % edge;
					rst.insert(0, "(" + x + "," + y + ") ");
				}
				return true;
			}
			if (index - edge >= 0) {
				if (maze.charAt(index - edge) == '\u25CB') {
					index -= edge;
					processPace(maze, st, index);
					continue;
				}
			}
			if (index + 1 < maze.length() && (index + 1) % edge != 0) {
				if (maze.charAt(index + 1) == '\u25CB') {
					index++;
					processPace(maze, st, index);
					continue;
				}
			}
			if (index + edge < maze.length()) {
				if (maze.charAt(index + edge) == '\u25CB') {
					index += edge;
					processPace(maze, st, index);
					continue;
				}
			}
			if (index - 1 > 0 && index % edge != 0) {
				if (maze.charAt(index - 1) == '\u25CB') {
					index--;
					processPace(maze, st, index);
					continue;
				}
			}
			if (!st.isEmpty()) {
				index = (int)st.pop();
			} else {
				return false;
			}
		}
	}
	public static void processPace(StringBuffer maze, Stack st, int index) {
			maze.delete(index, index + 1);
			maze.insert(index, '\u2605');
			st.push(index); 
	}
	
	public static void radixSort1(Scanner sc) {
		
	}
	public static void main(String args[]) {
		try {
			InputStream in = new FileInputStream("maze4.txt");
			Scanner sc = new Scanner(in);
			int edge = sc.nextInt();
			int begin_x = sc.nextInt();
			int begin_y = sc.nextInt();
			int dest_x = sc.nextInt();
			int dest_y = sc.nextInt();
			String mazestr = new String();
			while (sc.hasNext()) {
				mazestr = mazestr + sc.next();
			}
			StringBuffer maze = new StringBuffer(mazestr);
			StringBuffer rst = new StringBuffer();
			if(mazeTravelByStack(rst, maze, edge, begin_y * edge + begin_x, dest_y * edge + dest_x)) {
				System.out.print("(" + begin_x + "," + begin_y + ") " + rst + "(" + dest_x + "," + dest_y + ")");
			} else {
				System.out.println("no path");
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
