package com.whb.sys.org.util.algorithms;

import java.util.Arrays;

/**
 * 摊还分析
 * 对栈新增加操作---MULTIPOP(S,k)
 * 删除栈S的栈顶的k个元素
 * 如果S中的元素少于k，则弹出全部的元素
 * @author WHB
 *
 */
public class AmorizedAnalysis {
	public static void main(String[] args){
		int[] A = new int [8];
		for(int i=0;i<32;i++){
			INCREMENT(A);
		}
		System.out.println("<<<<<<<<<<<<<<<<<<<<<");
		
		Stack s = new Stack();
		s.Push(3);
		s.Push(4);
		s.Push(5);
		s.Print();
		System.out.println();
		System.out.println("-------"+s.top+"------");
		System.out.println(s.Pop());
		System.out.println("-------"+s.top+"------");
		System.out.println(s.Pop());
		System.out.println("--------"+s.top+"-----");
		System.out.println(s.Pop());
		System.out.println("--------"+s.top+"-----");
		s.Print();
		IsertTable t = new IsertTable();
		TABLE_INSERT(t,5);
		System.out.println(t.getNum());
		TABLE_INSERT(t,7);
		System.out.println(t.getNum());
	}

	/**
	 * 二进制计数器递增
	 * @param A
	 */
	public static void INCREMENT(int[] A){
		int i = 0;
		while(i < A.length && A[i] == 1){
			A[i] = 0;
			i += 1;
		}
		if(i<A.length){
			A[i] = 1;
		}
		for(int a:A){
			System.out.print(a+"_");
		}
		System.out.println("^^^^^^^^^^");
	}
	
	/**
	 * 表的扩张
	 * @param t
	 * @param x
	 */
	public static void TABLE_INSERT(IsertTable t,int x){
		if(t.getSize() == 0){
			t.setTable(new int[1]);
			t.setSize(1);
		}
		if(t.getNum() == t.getSize()){
			int[] newTable = new int[2*t.getSize()];
			System.arraycopy(t.getTable(), 0,newTable,0,t.getSize());
			t.setTable(null);
			t.setTable(newTable);
			t.setSize(2*t.getSize());
		}
		t.getTable()[t.getNum()] = x;
		t.setNum(t.getNum()+1);
	}
}

