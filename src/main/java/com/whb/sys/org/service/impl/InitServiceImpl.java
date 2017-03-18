package com.whb.sys.org.service.impl;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;

import com.whb.sys.org.model.SysException;
import com.whb.sys.org.service.IInitService;

/**
 * Created by WHB on 2016/7/3.
 */
@Service("initService")
public class InitServiceImpl implements IInitService {
    private final static Logger logger = LoggerFactory.getLogger(InitServiceImpl.class);
    @Inject
    private BeanFactory factory;
    private Element readRootElement(String filename) throws DocumentException{
        SAXReader saxReader = new SAXReader();
        try {
            System.out.println(InitServiceImpl.class.getClassLoader().getResourceAsStream("init/"+filename));
            System.out.println(InitServiceImpl.class.getClassLoader().getResource("init/"+filename));
            Document d = saxReader.read(InitServiceImpl.class.getClassLoader().getResourceAsStream("init/"+filename));
            return d.getRootElement();
        } catch (DocumentException e) {
        	logger.error(e.getMessage(),e.getCause());
        	throw e;
        }
    }

    /**
     * 初始化
     * @param filename 文件名
     */
    @SuppressWarnings("unchecked")
	public void initEntityByXml(String filename){
        try {
			Map<String,String> maps = new HashMap<String,String>();
		    Properties propFlies = new Properties();
            propFlies.load(getClass().getResourceAsStream("/init/orgs.xml"));
            Set<String> sets = propFlies.stringPropertyNames();
            maps = (Map) propFlies;
	        logger.info("需要加入的数量:{}",maps.size());
	        Element root = readRootElement(filename);
	        String pname = root.attributeValue("package");
	        List<Element> intiEntitys = root.selectNodes("/entitys/initEntity");
	        	for(Element e:intiEntitys){
		            if("1".equals(e.attributeValue("exist"))) continue;
		            String ipname = e.attributeValue("package");
		            String cname = e.attributeValue("class");
		            if(ipname!=null&&!"".equals(ipname)) {
		                cname = ipname+"."+cname;
		            } else {
		                cname = pname+"."+cname;
		            }
		            String method = e.attributeValue("method");
		            List<Element> entitys = (List<Element>)e.selectNodes("entity");
		            addElements(cname,method,entitys);
	        	}
        } catch (InstantiationException e) {
        	logger.error(e.getMessage(),e.getCause());
        } catch (IOException e) {
        	logger.error(e.getMessage(),e.getCause());
		} catch (DocumentException e) {
        	logger.error(e.getMessage(),e.getCause());
		}
    }

    public void  addElements(String cname,String method,List<Element> eneitys) throws InstantiationException {
    	try {
	    	for(Element element:eneitys){
	    		addElements(cname,method,element,null);
	        } 
    	} catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
	public void  addElements(String cname,String method,Element element,Object parent) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        try{
        	List<Attribute> attrs = (List<Attribute>)element.attributes();
            Object o = Class.forName(cname).newInstance();
            String[] ms= method.split("\\.");
            if(ms.length != 2) throw new SysException("方法格式不正确");
            String sname = ms[0];String mname = ms[1];
            for(Attribute attr:attrs){
                String value = attr.getValue();
                String name = attr.getName();
                BeanUtils.copyProperty(o,name,value);
            }
            if(parent!=null) {
                BeanUtils.copyProperty(o, "parent", parent);
            }
            Object service = factory.getBean(sname);
            Method m = service.getClass().getMethod(mname,o.getClass());
            m.invoke(service,o);
            @SuppressWarnings("unchecked")
    		List<Element> es = element.selectNodes("entity");
            for(Element ele:es) {
                addElements(cname, method, ele, o);
            }
        }catch (ClassNotFoundException e){
        	logger.error(e.getMessage(),e.getCause());
        	throw e;
        }catch ( NoSuchMethodException e){
        	logger.error(e.getMessage(),e.getCause());
        	throw e;
        }catch ( InvocationTargetException e ){
        	logger.error(e.getMessage(),e.getCause());
        	throw e;
        }catch ( IllegalAccessException e){
        	logger.error(e.getMessage(),e.getCause());
        	throw e;
        }catch (InstantiationException e) {
        	logger.error(e.getMessage(),e.getCause());
        	throw e;
		}
    	
    }
}
