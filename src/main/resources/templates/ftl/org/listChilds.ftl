<!DOCTYPE html>
<html>	
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/admin/basic.css" type="text/css"/>
<link rel="stylesheet" href="/css/admin/index.css" type="text/css"/>
<link rel="stylesheet" href="/css/admin/table1.css" type="text/css"/>
<link rel="stylesheet" href="/css/icon.css" type="text/css"/>
<link href="/css/pager/pager.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="/js/main.js"></script>
<script type="text/javascript" src="/js/admin/jquery.admintable.js"></script>
<script type="text/javascript" src="/js/public/jquery.confirmdialog.js"></script>
<script type="text/javascript" src="/js/public/jquery.static.js"></script>
<script src="/js/pager/jquery.pager.js" type="text/javascript"></script> 
<script type="text/javascript">
$(function(){
	$("#admintable").admintable({showCheckbox:false});
	$("a[oper='delete']").confirmdialog();
	$("#orgTypes").change(function(){
		var href = window.location.href;
		window.location.href = $.formatHref(href)+"?typeId="+$(this).val();
	});
});
</script>
</head>
<body>
<div class="main_content_container">
	<table id="admintable" width="850" cellpadding="0" cellspacing="0">
	<thead>
	 <tr>
	    <td colspan="6" class="title">
		   <#include "/ftl/org/nav.ftl">
	    </td>
	  </tr>
	   <tr>
	    <td colspan="6" class="title">
	   	 查询组织:<span class="em">[${parent.name}]</span>
	   	<span>
	   		<select id="orgTypes" name="orgTypes">
	   			<option value="-1">筛选组织类型</option>
	   			<option value="-1">全部</option>
	   			<#list orgTypes! as ot>
		   			<#if parent.typeId ==ot.cid>
		   				<option value="${ot.cid }" selected="selected">${ot.cname }</option>
		   			</#if>
		   			<#if  parent.typeId != ot.cid >
		   				<option value="${ot.cid }">${ot.cname }</option>
		   			</#if>
	   			</#list>
	   		</select>
	   	</span>
	    </td>
	  </tr>
	  <tr>
	    <td width="20%" align="center"><strong>名称</strong></td>
	    <td width="10%" align="center"><strong>类型</strong></td>
	    <td width="10%" align="center"><strong>管理类型</strong></td>
	    <td width="20%" align="center"><strong>电话</strong></td>
	    <td width="30%" align="center"><strong>操作</strong></td>
	  </tr>
	</thead>
	<tbody>
		<#if childs??>
		<#list childs.datas as data>
			 <tr>
			    <td align="center"><a href="/admin/org/orgs/${parent.id }/${data.id }" class="color_red">${data.name }</a></td>
			    <td class="jianju10">${data.typeName }</td>
			    <td class="jianju10">
			    <#if  data.managerType== 0  >默认</#if>
			    <#if  data.managerType ==  1 >所有部门</#if>
			    <#if  data.managerType ==  2 >自定义</#if>
			    <#if  data.managerType ==  -1 >无</#if>
			    </td>
			    <td align="center">${data.phone}</td>
			    <td align="center">
			    	<#if data.typeId?? >
			    	<a href="/admin/org/orgs/${parent.id }/delete/${data.id }" class="color_red" oper="delete">删除</a>
			    	<a href="/admin/org/orgs/${parent.id }/update/${data.id }" class="color_red">修改</a>
			    	</#if>
			    	<a href="/admin/org/persons/${data.id}" class="color_red">查询人员</a>
			    	<#if data.managerType == 2  >
			    		<a href="/admin/org/setRule/${data.id }" class="color_red">设置规则</a>
			    	</#if>
			    </td>
			  </tr>
		</#list>
		</#if>
	  </tbody>
	  <tfoot>
	  <tr><td colspan="6">
	  <div id="pager" >
	  	<#include "/ftl/pager/pager.ftl">
	  </div> 
	  </td></tr>
	  </tfoot>
	</table>
</div>
</body>
</html>