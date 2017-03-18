package com.whb.sys.org.util.algorithms;

import java.util.Stack;

public class BinaryTree<T> {
	static int count=0;
	//二叉树的根节点
	public BinaryTree(){};

	public Node<Integer> getBinaryTree(int[] keys){
		Node<Integer> t = new Node<Integer>();
		for(int key:keys){
			t = CreatTree(t,key);
		}
		return t;
	}
	public static Node<Integer> CreatTree(Node<Integer> root,int key){
		if(root!=null&&root.getKey()==null){
			root = new Node<Integer>();
			root.setKey(key);
		}else if(root!=null && root.getKey()>key){
			if(root.getLeft()==null){
				 root.setLeft(new Node<Integer>());
				 root.getLeft().setKey(key);
			}else {
				CreatTree(root.getLeft(),key);
			}
		}else if(root!=null && root.getKey()<key){
			if(root.getRight()==null){
				 root.setRight(new Node<Integer>());
				 root.getRight().setKey(key);
			}else {
				CreatTree(root.getRight(),key);
			}
		}
		return root;
	}
	
	public static Node<Integer> CreatTreeByStack(Node<Integer> root,int key){
		if(root!=null&&root.getKey()==null){
			root = new Node<Integer>();
			root.setKey(key);
		}else if(root!=null&&root.getKey()>key){
			if(root.getLeft()==null){
				 root.setLeft(new Node<Integer>());
				 root.getLeft().setKey(key);
			}else {
				CreatTree(root.getLeft(),key);
			}
		}else if(root!=null&& root.getKey()<key){
			if(root.getRight()==null){
				 root.setRight(new Node<Integer>());
				 root.getRight().setKey(key);
			}else {
				CreatTree(root.getRight(),key);
			}
		}
		return root;
	}
	
	public static void maddin(String[] args){
		int[] keys=new int[]{27,8,9,66,646};
		/*for(int i=0;i<keys.length;i++){
			keys[i]=new Random().nextInt();
		}*/
		BinaryTree<Integer> t = new BinaryTree<Integer>();
		Node<Integer> n = t.getBinaryTree(keys);
		//nrInorder_Tree(n);

		System.out.println("!!!!-----------------");
		Node<Integer> s1 =  tree_Successor(n,9);
		tree_Insert(n,1);
		nrInorder_Tree(n);
		System.out.println("-----------------!!!!");

		System.out.println("!!!!-----------------");
		tree_Delete(n,9);
		nrInorder_Tree(n);
		System.out.println("-----------------!!!!");
		
		
		
		//Node<Integer> s = iterative_Search(n,8);
		//nrInorder_Tree(tree_Minimum(n));
		
	}
	
	public void Tree_Insert(Node<Integer> z){
		
	}
	
	/**
	 * 中序遍历递归遍历
	 * @param z
	 */
	public static void Inorder_Tree(Node<Integer> z){
		if(z!=null) {
			Inorder_Tree(z.getLeft());
			System.out.println(z.getKey());
			Inorder_Tree(z.getRight());
		}
		
	}
	/**
	 * 非递归遍历（使用栈遍历）
	 * @param z
	 */
	public static void nrInorder_Tree(Node<Integer> z){
		Stack<Node<Integer>> stack = new Stack<Node<Integer>>();
		while(z!=null||!stack.isEmpty()){
			while(z!=null){
				stack.push(z);
				z = z.getLeft();
			}
			z = stack.pop();
			System.out.println(z.getKey());//中序遍历
			z = z.getRight();
		}
	}
	
	/**
	 * 非递归遍历（使用栈遍历）
	 * @param z
	 */
	public static void nrPreOrder_Tree(Node<Integer> z){
		Stack<Node<Integer>> stack = new Stack<Node<Integer>>();
		while(z!=null||!stack.isEmpty()){
			while(z!=null){
				System.out.println(z.getKey());//先序遍历
				stack.push(z);
				z = z.getLeft();
			}
			z = stack.pop();
			z = z.getRight();
		}
	}
	
	/**
	 * 查找（递归的形式）
	 * @param x
	 * @param k
	 * @return
	 */
	public static Node<Integer> tree_Search(Node<Integer> x,Integer k){
		if(x==null || k==x.getKey()){
			return x;
		}
		if(x!=null&&k<(Integer)x.getKey()){
			return tree_Search(x.getLeft(), k);
		}else{
			return tree_Search(x.getRight(), k);
		}
	}
	
	/**
	 * 查找（非递归的形式)，可以在O(h)时间内完成（h为树高度）
	 * 所遇到的节点都是从树根向下简单路径
	 * @param x
	 * @param k
	 * @return
	 */
	public static Node<Integer> iterative_Search(Node<Integer> x,Integer k){
		Node<Integer> parent = new Node<Integer>();
		while(x!=null && !k.equals(x.getKey())){
			parent = x;
			if(k<x.getKey()){
				x = x.getLeft();
			}else if(k>x.getKey()){
				x = x.getRight();
			}
			x.setParent(parent);
		}
		return x;
	}
	
	/**
	 * 最小值，可以在O(h)时间内完成（h为树高度）
	 * 所遇到的节点都是从树根向下简单路径
	 * @param x
	 * @return
	 */
	public static Node<Integer> tree_Minimum(Node<Integer> x){
		while(x.getLeft()!=null){
			x=x.getLeft();
		}
		return x;
	}
	
	/**
	 * 最大值，可以在O(h)时间内完成（h为树高度）
	 * 所遇到的节点都是从树根向下简单路径
	 * @param x
	 * @return
	 */
	public static Node<Integer> tree_Maximum(Node<Integer> x){
		while(x.getRight()!=null){
			x=x.getRight();
		}
		return x;
	}
	
	/**
	 * 后继
	 * 按中序遍历查找后继，若所有的关键字都相同，节点x的后继是大于x.key的最小关键字 
	 * 如果右节点非空，则右节点为后继
	 * 如果右节点为空，且有一个后继，则y就是x的祖先，同时y的左节点也是x的祖先（节点也是节点本身的祖先），
	 * @param x
	 * @return
	 */
	public static Node<Integer> tree_Successor(Node<Integer> son){
		if(son!=null&&son.getRight()!=null){
			return tree_Minimum(son.getRight());
		}
		Node<Integer> y=son.getParent();
		while(y!=null&&son==y.getLeft()){
			son=y;
			y=y.getParent();
		}
		return y;
	}
	/**
	 * 查询后继
	 * @param x
	 * @param k
	 * @return
	 */
	public static Node<Integer> tree_Successor(Node<Integer> x,Integer k){
		Node<Integer> son = iterative_Search(x, k);
		Node<Integer> s = tree_Successor(son);
		return s;
	}
	
	/**
	 * 向树种插入元素
	 * 指针x记录了一条向下的简单路径，并查找要替换的输入的项z的null
	 * 该过程保持遍历指针y作为x的双亲
	 * 向左向右 取决于z.key与y.key的比较
	 * 直到x为null，此时位置就是z要插入的位置
	 * y为z的父节点
	 * @param x 被插入的树
	 * @param z 待插入的树节点
	 */
	public static void tree_Insert(Node<Integer> x,Node<Integer> z){
		Node<Integer> y = null;
		while(x!=null){
			if(y==null) y = new Node<Integer>();
			y=x;
			if(z.getKey()<y.getKey()) x=x.getLeft();
			else x=x.getRight();
		}
		z.setParent(y);
		if(y==null) x = z;
		else if(z.getKey()<y.getKey()) y.setLeft(z);
		else y.setRight(z);
	}
	
	/**
	 * 插入的运行时间为O(h)
	 * @param x
	 * @param k
	 */
	public static void tree_Insert(Node<Integer> x,int k){
		Node<Integer> z = new Node<Integer>();
		z.setKey(k);
		tree_Insert(x,z);
	}
	
	/**
	 * 二叉搜索树内移动子树，定义一个子过程TRANSPLANT,它是一棵子树替换一棵子树并成为其双亲的孩子节点
	 * 当TRANSPLANT用一棵以v为根的子树替换一棵以u为根的子树，节点u的双亲就变成为节点v的双亲，并且v成为u的双亲的孩子
	 * @param T 需要移动的二叉树
	 * @param u 被替换的子树
	 * @param v 替换的子树
	 */
	public static void transplant(Node<Integer> T,Node<Integer> u,Node<Integer> v){
		if(u.getParent() == null){
			T = v;
		}else if(u == u.getParent().getLeft()){
			u.getParent().setLeft(v);
		}else{
			u.getParent().setRight(v);
		}
		if(v != null){
			v.setParent(u.getParent());
		}
	}
	
	/**
	 * 利用TRANSPLANT过程，实现删除节点
	 * @param T
	 * @param z
	 */
	public static void  tree_Delete(Node<Integer> T,Node<Integer> z){
		if(z.getLeft()==null){
			transplant(T, z, z.getRight());
		}else if(z.getRight()==null){
			transplant(T, z, z.getLeft());
		}else{
			Node<Integer> y = tree_Minimum(z.getRight());
			if(y.getParent() != z){
				transplant(T, y, y.getRight());
				y.setRight(z.getRight());
				y.getRight().setParent(y);
			}
			transplant(T, z, y);
			y.setLeft(z.getLeft());
			y.getLeft().setParent(y);
		}
	}
	public static void  tree_Delete(Node<Integer> T,Integer key){
		Node<Integer> z = iterative_Search(T,key);
		tree_Delete(T,z);
	}
}
