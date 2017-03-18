package com.whb.sys.org.util.algorithms;

public class RedBlackNode<T> {
	private T value;
	private RedBlackNode<T> left;
	private RedBlackNode<T> right;
	private RedBlackNode<T> p;
	private Object nil;
	private Integer size;
	
	
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Object getNil() {
		return null;
	}
	
	private int color;
	
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
	public RedBlackNode<T> getLeft() {
		return left;
	}
	public void setLeft(RedBlackNode<T> left) {
		this.left = left;
	}
	public RedBlackNode<T> getRight() {
		return right;
	}
	public void setRight(RedBlackNode<T> right) {
		this.right = right;
	}
	public RedBlackNode<T> getP() {
		return p;
	}
	public void setP(RedBlackNode<T> p) {
		this.p = p;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	
	
}
