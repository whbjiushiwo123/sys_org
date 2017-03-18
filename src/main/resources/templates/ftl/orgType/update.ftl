<!DOCTYPE html>
<html>	
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改组织机构</title>
<link rel="stylesheet" href="/css/admin/basic.css" type="text/css"/>
<link rel="stylesheet" href="/css/admin/index.css" type="text/css"/>
<link rel="stylesheet" href="/css/admin/table1.css" type="text/css"/>
<link rel="stylesheet" href="/css/icon.css" type="text/css"/>

</head>
<body>
<div class="main_content_container">
	<form  id="adminForm" method="post">
	<table class="list_table" cellpadding="0" cellspacing="0">
	<thead>
	 <tr>
	    <td colspan="2" class="title">
		   <#include "/ftl/orgType/nav.ftl">
	    </td>
	  </tr>
	  <tr>
	    <td colspan="2">组织机构类型修改-->${ot.id}</td>
	  </tr>
	</thead>
	<tbody>
		<tr>
			<td align="right">组织名称:</td>
			<td align="left"><input type="text" name="name" value="${ot.name}" /></td>
		</tr>
		<tr>
			<td align="right">组织序号:</td>
			<td align="left"><input type="text" name="sn" value="${ot.sn}" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit" value="修改组织机构类型"/></td>
		</tr>
	 </tbody>
	</form>
	</table>
</div>
</body>

</html>