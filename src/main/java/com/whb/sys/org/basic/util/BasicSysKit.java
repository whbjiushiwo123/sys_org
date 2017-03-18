package com.whb.sys.org.basic.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by WHB on 2016/7/3.
 */
public class BasicSysKit {
    /**
     * 将(33)(44)取出，存放在list
     * @param strs
     * @return
     */
    public static List<Integer> braceStr2List(String strs){
        Pattern pattern = Pattern.compile("(\\d+)");
        Matcher m = pattern.matcher(strs);
        List<Integer> list = new ArrayList<Integer>();
        while(m.find()){
            list.add(Integer.parseInt(m.group()));
        }
        return list;
    }
    
    @SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object obj){
        if(obj == null) return true;
        if(obj instanceof String ){
            if("".equals(obj)) return true;
        }
        if(obj instanceof Collection<?>){
            if(((Collection) obj).size() > 0){
                return true;
            }
        }
        return false;
    }
}
