package com.whb.sys.org.util.algorithms;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 动态规划
 * @author WHB
 *
 */
public class DynamicProgramming {
	/**
	 * 递归形式
	 * 以价格数组p[1……n]和整数n为输入，返回长度为n的钢条的最大收益，若n=0；不可能收获任何是收益
	 * @param p 价格数组p[1……n]
	 * @param n 钢条长度 
	 * @return 最大收益
	 */
	private static int CUT_ROD(int[] p,int n){
		if(n == 0){
			return 0;
		}
		int q = Integer.MIN_VALUE;
		for(int i=0;i<n;i++){
			//切割左边的一边，只对右边剩下的长度为n-1的一段继续进行切割
			q = Math.max(q, p[i]+CUT_ROD(p,n-i-1));
		}
		return q;
	}
	
	/**
	 * 带备忘录的自顶向下法
	 * @param p
	 * @param n
	 * @return 
	 */
	public static int MEMOIZED_CUT_ROD(int[] p,int n){
		//新数组，用来记录
		int[] r = new int[n];
		for(int i=0;i<n;i++){
			r[i] = Integer.MIN_VALUE;
		}
		return MEMOIZED_CUT_ROD_AUX(p,n,r);
	}
	
	private  static int  MEMOIZED_CUT_ROD_AUX(int[] p, int n, int[] r) {
		if(n>=r.length&&r[r.length-1]>=0){
			return r[r.length-1];
		}
		if(n<r.length&&r[n]>=0){
			return r[n];
		}
		int q;
		if(n == 0){
			q = 0;
		}else{
			q = Integer.MIN_VALUE;
			for(int i=0;i<n;i++){
				q = Math.max(q, p[i]+MEMOIZED_CUT_ROD_AUX(p, n-i-1, r));
			}
		}
		
		if(n>=r.length&&r[r.length-1]>=0){
			r[r.length-1] = q;
		}
		if(n<r.length&&r[n]>=0){
			r[n] = q;
		}
		return q;
	}

	/**
	 * 自底向上求解(直接访问数组元素 保存最优解中，最优解为最优解+某个值，导致最优解变大)
	 * @param p
	 * @param n
	 * @return
	 */
	public static int BOTTOM_UP_ROD(int[] p,int n){
		//保存子问题的解
		int[] r = new int[n];
		//初始化为0，
		r[0] = 0;
		//升序求解每个规模为j的子问题
		for(int j = 0;j<n;j++){
			int q = Integer.MIN_VALUE;
			for(int i=0;i<=j;i++){
				// 直接访问数组元素r[j-i]来获得规模为j-i的子问题的解，而不必进行递归
				q = Math.max(q, p[i]+r[j-i]);
				System.out.println(j+"____"+i+"_____"+q+"_____"+p[i]+"_____"+r[j-i]);
			}
			//规模为j的子问题的解存入r[j]
			r[j] = q;
		}
		return r[n-1];
	}
	/**
	 * (直接访问数组元素 保存最优解中，最优解为最优解+某个值，导致最优解变大) 
	 * @param p
	 * @param n
	 * @param s
	 * @param r
	 * @return
	 */
	public static int EXTENDED_BOTTOM_UP_CUT_ROD(int[] p,int n,int[] s,int[] r){
		r[0] = 0;
		for(int j=1;j<=n;j++){
			int q = Integer.MIN_VALUE;
			for(int i=1;i<=j;i++){
				//直接访问数组元素r[j-i]来获得规模为j-i的子问题的解，而不必进行递归
				if(q<(p[i-1]+r[j-i])){
					q = p[i-1] +r[j-i];
					s[j-1] = i;
				}
			}
			r[j-1] = q;
		}
		return r[n-1];
	}
	
	public static void PRINT_CUT_ROD_SOLUTION(int[] p, int n){
		int[] s = new int[n];
		int[] r = new int[n];
		EXTENDED_BOTTOM_UP_CUT_ROD(p,n,s,r);
		while(n > 0){
			System.out.println(s[n-1]);
			n = n-s[n-1];
		}
	}
	
	/**
	 * 矩阵标准乘法
	 * @param A
	 * @param B
	 * @throws Exception 
	 */
	public int[][] MATRIX_MULTIPLY(int[][] A,int[][]B) throws Exception{
		int[][]C ;
		/**
		 * 矩阵相乘的条件是相容
		 * 即A的行要等于B的列
		 */
		if(A.length != B[0].length){
			throw new Exception("incompatible dimensions");
		}else{
			C = new int[A.length][B[0].length];
			for(int i=0;i<A.length;i++){
				for(int j = 0;j<B[0].length;j++){
					C[i][j] = 0;
					for(int k=0;k<A.length;k++){
						C[i][j] += A[i][k] * B[k][j];
					}
				}
			}
		}
		return C;
		
		
	}
	
	public static void  main(String[] args){
		int[] p = new int[]{1,5,8,9,10,17,17,20,24,30};
		System.out.println(BOTTOM_UP_ROD(p,4));
	}
}
