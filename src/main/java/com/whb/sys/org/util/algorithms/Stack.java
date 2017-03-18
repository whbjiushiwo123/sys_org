package com.whb.sys.org.util.algorithms;

public class Stack {
	public int top;
	public int[] S;
	public Stack(){
		S = new int[]{};
		top = 0;;
	}
	public boolean Stack_empty(){
		return top == 0?true:false;
	}
	
	public  void Push(int x){
		top += 1;
		int [] newArray = new int[top];
		System.arraycopy(S, 0, newArray, 0, top-1);
		newArray[top-1] = x;
		S = newArray;
	}
	
	/**
	 * 判断栈是否为空
	 * 如果为空，则返回true
	 * 否则返回false
	 */
	private boolean STACK_EMPTY() {
		return top <= 0?true:false;
	}
	
	/**
	 * 栈的弹出
	 * @return
	 */
	public int Pop(){
		if(STACK_EMPTY()){
			return Integer.MIN_VALUE;
		}else{
			top -= 1;
			int [] newArray = new int[top];
			System.arraycopy(S, 0, newArray, 0, top);
			int num = S[top];
			S = newArray;
			return num;
		}
	}
	
	/**
	 * 聚合分析--栈的新操作:一次弹出多个元素 
	 * @param k 要弹出的元素个数
	 * @return
	 */
	public int[] MULTIPOP(int k){
		int num = k-1;
		int[] popArray = new int[k];
		while(!STACK_EMPTY() && k>0){
			popArray[num] = Pop();
			k -= 1;
			num -= 1;
		}
		return popArray;
	}
	
	public void Print(){
		for(int s:S){
			System.out.print(s+" ");
		}
	}
}
