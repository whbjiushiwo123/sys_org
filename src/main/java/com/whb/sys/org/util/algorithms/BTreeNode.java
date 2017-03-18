package com.whb.sys.org.util.algorithms;

/**
 * B树节点
 * @author WHB
 *
 */
public class BTreeNode {
	/**
	 * 存储在当前节点的关键字个数
	 */
	private int n;
	
	/**
	 * 关键字
	 */
	private int[] key;
	
	/**
	 * 是否为叶节点：是则为true，否则为false
	 */
	private boolean leaf;
	
	/**
	 * 子节点
	 */
	private BTreeNode[] children;
	
	/**
	 * 叶节点的高度
	 */
	private int h;
	
	/**
	 * 最小度数
	 */
	private int t;

	
	
	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getT() {
		return t;
	}

	public void setT(int t) {
		this.t = t;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int[] getKey() {
		return key;
	}

	public void setKey(int[] key) {
		this.key = key;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public BTreeNode[] getChildren() {
		return children;
	}

	public void setChildren(BTreeNode[] children) {
		this.children = children;
	}
	
	
}
