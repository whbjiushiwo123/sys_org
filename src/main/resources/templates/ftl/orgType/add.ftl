<!DOCTYPE html>
<html>	
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/admin/basic.css" type="text/css"/>
<link rel="stylesheet" href="/css/admin/index.css" type="text/css"/>
<link rel="stylesheet" href="/css/admin/table1.css" type="text/css"/>
<link rel="stylesheet" href="/css/icon.css" type="text/css"/>
<script type="text/javascript" src="/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="/js/jquery.validate.js"></script>
<script type="text/javascript" src="/js/main.js"></script>
<script type="text/javascript" src="/js/public/jquery.sysvalidate.js"></script>
<script type="text/javascript">
$(function(){
	$("#adminForm").sysvalidate();
});
</script>
</head>
<body>
<div class="main_content_container">
<form id="adminForm" method="post">
	<table class="list_table" cellpadding="0" cellspacing="0">
	<thead>
	 <tr>
	    <td colspan="2" class="title">
		   <#include "/ftl/orgType/nav.ftl">
	    </td>
	  </tr>
	  <tr>
	    <td colspan="2" class="left">组织机构类型添加</td>
	  </tr>
	</thead>
	<tbody>
		<tr>
			<td class="right">组织名称:</td>
			<td class="left"><input type="text" name="name"/></td>
		</tr>
		<tr>
			<td class="right">组织序号:</td>
			<td class="left"><input type="text" name="sn"/></td>
		</tr>
		<tr>
			<td colspan="2" class="center"><input type="submit" value="添加组织机构类型"/></td>
		</tr>
	 </tbody>
	</table>
	</form>
</div>
</body>
</html>