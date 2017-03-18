<!DOCTYPE html>
<html>	
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/admin/basic.css" type="text/css"/>
<link rel="stylesheet" href="/resources/css/admin/index.css" type="text/css"/>
<link rel="stylesheet" href="/css/admin/table1.css" type="text/css"/>
<link rel="stylesheet" href="/css/icon.css" type="text/css"/>
<script type="text/javascript" src="/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="/js/main.js"></script>
<script type="text/javascript" src="/js/admin/jquery.admintable.js"></script>
<script type="text/javascript" src="/js/public/jquery.confirmdialog.js"></script>
<script type="text/javascript">
$(function(){
	$("#admintable").admintable({showCheckbox:false});
	$("a[oper='delete']").confirmdialog();
});
</script>
</head>
<body>
<div class="main_content_container">
	<table id="admintable" cellpadding="0" cellspacing="0">
	<thead>
	 <tr>
	    <td colspan="6" class="title">
		   <#include "/ftl/orgType/nav.ftl">
	    </td>
	  </tr>
	  <tr>
	    <td width="20%" align="center"><strong>标识</strong></td>
	    <td width="25%" align="center"><strong>名称</strong></td>
	    <td width="25%" align="center"><strong>编号</strong></td>
	    <td width="20%" align="center"><strong>操作</strong></td>
	  </tr>
	</thead>
	<tbody>
		<#list orgTypes as data>
			 <tr>
			    <td align="center">${data.id }</td>
			    <td class="jianju10"><a href="${data.id }" class="color_red">${data.name }</a></td>
			    <td align="center">${data.sn }</td>
			    <td align="center">
			    	<a href="/admin/orgType/delete/${data.id }" class="color_red" oper="delete">删除</a>
			    	<a href="/admin/orgType/update/${data.id }" class="color_red">修改</a>
			    	<a href="/admin/orgTypeRule/setRule/${data.id }" class="color_red">设置规则</a>
			    </td>
			  </tr>
		</#list>
	  </tbody>
	</table>
</div>
</body>
</html>