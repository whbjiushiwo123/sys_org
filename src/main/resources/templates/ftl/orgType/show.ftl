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
<script type="text/javascript" src="/js/main.js"></script>
</head>
<body>
<div class="main_content_container">
	<table class="list_table" cellpadding="0" cellspacing="0">
	<thead>
	 <tr>
	    <td colspan="2" class="title">
		   <#include "/ftl/orgType/nav.ftl">
	    </td>
	  </tr>
	  <tr>
	    <td colspan="2" class="left">查询[${ot.name}]的组织机构类型</td>
	  </tr>
	</thead>
	<tbody>
		<tr>
			<td class="right" width="300">组织标识:</td>
			<td class="left">${ot.id }</td>
		</tr>
		<tr>
			<td class="right">组织名称:</td>
			<td class="left">${ot.name }</td>
		</tr>
		<tr>
			<td class="right">组织序号:</td>
			<td class="left">${ot.sn }</td>
		</tr>
	 </tbody>
	</table>
	
	<table class="list_table" cellpadding="0" cellspacing="0">
	<thead>
	  <tr>
	    <td colspan="3" class="left">子组织信息[<a href="/admin/orgTypeRule/setRule/${ot.id}" class="color_red">设置规则</a>]</td>
	  </tr>
	  <tr>
		<td class="center">子组织ID</td>
		<td class="center">子组织名称</td>
		<td class="center">子组织数量</td>
	 </tr>
	</thead>
	<tbody>
		<#list childs as cot>
			<tr>
				<td class="center">${cot.cid }</td>
				<td class="center">${cot.cname }</td>
				<td class="center">${cot.num }</td>
			</tr>
		</#list>
	 </tbody>
	</table>
</div>
</body>
</html>