package _two;

public class BinNode {
	Object elm;
	BinNode left;
	BinNode right;
	
	BinNode(Object _elm)
	{
		elm = _elm;
		left = right = null;
	}
	public Object element() 
	{
		return elm;
	}
	
	public void setElment(Object _elm)
	{
		elm = _elm;
	}
	public BinNode left()
	{
		return left;
	}
	public boolean setLeft(BinNode p)
	{
		if (left != null) {
			return false;
		} else {
			left = p;
			return true;
		}
	}
	
	public BinNode right()
	{
		return right;
	}
	public boolean setRight(BinNode p)
	{
		if (right != null) {
			return false;
		} else {
			right = p;
			return true;
		}
	}
	
	public boolean isLeaf() 
	{
		return (right == null && left == null);
	}
	
	
	
	public static BinNode readPostBinaryTree(String expr)
	{
		BinNode root = new BinNode('\0');
		Stack st = new Stack();
		for (int i = 0; i < expr.length(); i++) {
			char ch = expr.charAt(i);
			if (ch >= 'a' && ch <= 'z') {
				st.push(ch);
			} else {
				if ((char)root.elm != '\0') {
					BinNode _left = new BinNode(st.pop());
					BinNode temp = new BinNode(ch);
					temp.setLeft(_left);
					temp.setRight(root);
					root = temp;
				} else {
					root.setRight(new BinNode(st.pop()));
					root.setLeft(new BinNode(st.pop()));
					root.setElment(ch);
				}
			}
		}
		return root;
	}
	public void postOrderResult()
	{
		if (left != null) {
			left.postOrderResult();
		}
		if (right != null) {
			right.postOrderResult();
		}
		if (elm != null) {
			System.out.print(elm + " ");
		}
	}
	
	public static void main(String args[]) 
	{
		BinNode expr = readPostBinaryTree("13+64-*");
		expr.postOrderResult();
	}
}
