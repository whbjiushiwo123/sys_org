<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/admin/basic.css" type="text/css"/>
<link rel="stylesheet" href="/css/admin/index.css" type="text/css"/>
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
		   <#include "/ftl/position/nav.ftl"/>
	    </td>
	  </tr>
	  <tr>
	    <td width="20%" align="center"><strong>岗位标识</strong></td>
	    <td width="25%" align="center"><strong>岗位名称</strong></td>
	    <td width="25%" align="center"><strong>岗位编号</strong></td>
	    <td width="20%" align="center"><strong>操作</strong></td>
	  </tr>
	</thead>
	<tbody>
		<#list datas as data>
			<tr>
			    <td align="center">${data.id }</td>
			    <td class="jianju10">${data.name }</td>
			    <td align="center">${data.sn }</td>
			    <td align="center">
			    	<a href="delete/${data.id }" class="color_red" oper="delete">删除</a>
			    	<a href="update/${data.id }" class="color_red">修改</a>
			    </td>
			  </tr>
		</#list>
	
	  </tbody>
	</table>
</div>
</body>
</html>