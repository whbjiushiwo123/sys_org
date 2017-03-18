package com.whb.sys.org.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 元注解
 * 用于注解其他注解
 * 共有4个：
 * @Target 说明了Annotation所修饰的对象的范围（被描述的注解可用于什么地方）
 * @Retention 
 * @Documented 
 * @Inherited
 * 自定义注解
 * @author WHB
 *
 */
@Target(ElementType.TYPE)
public @interface MyAnnotatin {
	/**
	 * 数据表名称注解，默认值为类名
	 * @return
	 */
	public String tableName() default "className";
}


