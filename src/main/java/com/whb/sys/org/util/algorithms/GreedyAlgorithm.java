package com.whb.sys.org.util.algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 贪心算法
 * @author WHB
 *
 */
public class GreedyAlgorithm {
	static Set<Object> am = new HashSet<Object>();
	/**
	 * 活动选择问题
	 * 默认活动已经按结束时间单调递增排序好（s，f是按照结束时间单调递增）
	 * @param s 活动开始时间数组
	 * @param f 活动结束时间数组 ，默认为严格递增
	 * @param k 下标指示要求解的子问题Sk
	 * @param n 问题规模
	 */
	public static Set<Object> RECURSIVE_ACTIVITY_SELECTOR(int[] s,int[] f,int k,int n){
		int m = k + 1;
		while(m <= n && s[m] < f[k]){
			m += 1;
		}
		if(m <= n){
			int[] a = new int[]{s[m],f[m]};
			am.add(a);
			Set<Object> Sk  = RECURSIVE_ACTIVITY_SELECTOR(s,f,m,n);
			if(Sk != null){
				am.addAll(Sk);
			}
			return am;
		}else return null;
	}
	
	public static void main(String [] args){
		int[] s = new int[] {0,1,2,1,3,0,5,3,5,6,8,8,2,12};
		//f为结束时间，默认为严格递增
		int[] f = new int[] {0,2,3,4,5,6,7,9,9,10,11,12,14,16};
		Set<Object> l = RECURSIVE_ACTIVITY_SELECTOR(s,f,0,s.length-1);
		Iterator<Object> interator = l.iterator();
		while (interator.hasNext()){
			 
			int[] show = (int[]) interator.next();
			System.out.println("开始时间："+show[0]+",结束时间："+show[1]);
		}
		System.out.println("---------------------------");
		Set<int[]> l1 = GREEDY__ACTIVITY_SELECTOR(s,f);
		Iterator<int[]> interator1 = l1.iterator();
		while (interator1.hasNext()){
			int[] show1 = (int[]) interator1.next();
			System.out.println("开始时间："+show1[0]+",结束时间："+show1[1]);
		}
	}
	
	/**
	 * 跌代的贪心算法
	 * @param s
	 * @param f 为结束时间，默认为严格递增
	 * @return
	 */
	public static Set<int[]> GREEDY__ACTIVITY_SELECTOR(int[] s,int[] f){
		int n = s.length;
		Set<int[]> A = new HashSet<int[]>();
		A.add(new int[]{s[1],f[1]});
		int k = 1;
		for(int m = 2;m<n;m++){
			if(s[m] >= f[k]){
				A.add(new int[]{s[m],f[m]});
				k = m;
			}
		}
		return A;
	}
}
