
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
	$("#typeId").change(function(){
		var val = $(this).val();
		var txt = $(this).find("option:selected").html();
		$("#typeName").val(txt);
	});
});
</script>
</head>
<body>
<div class="main_content_container">
<form modelAttribute="org" id="adminForm" method="post">
	<table class="list_table" cellpadding="0" cellspacing="0">
	<thead>
	 <tr>
	    <td colspan="2" class="title">
		    <#include "/ftl/org/nav.ftl">
	    </td>
	  </tr>
	  <tr>
	    <td colspan="2" class="left">组织机构添加<span class="em">[父组织-->${parent.name }]</span><input type="hidden" name="pid" value="${parent.id }"/></td>
	  </tr>
	</thead>
	<tbody>
		<tr>
			<td class="right">组织机构名称:</td>
			<td class="left"><input name="name" id ="name" size="30"/><errors cssClass="errorContainer" path="name"/></td>
		</tr>
		<tr>
			<td class="right">组织机构类型:</td>
			<td class="left">
			<select name="typeId" id="typeId">
				<option value="-1">选择组织机构类型</option>
				<#list orgTypes as orgType>
					<option value="${orgType.cid }">${orgType.cname}</option>
				</#list>
			</select>
			<errors cssClass="errorContainer" path="typeId"/>
			<input name="typeName" id="typeName" readonly="true"/>
			</td>
		</tr>
		<tr>
			<td class="right">组织机构管理类型:</td>
			<td class="left">
			<select id="managerType" name="managerType">
				<#if managerTypes?? && managerTypes?size gt 0>
					<#list managerTypes as key , value>
						<option value='${key}'>${value}</option>
					</#list>
				</#if>
			</select>
			</td>
		</tr>
		<tr>
			<td class="right">组织机构地址:</td>
			<td class="left"><input name="address" id="address" size="70"/></td>
		</tr>
		<tr>
			<td class="right">组织机构电话:</td>
			<td class="left"><input name="phone" id="phone" size="70"/></td>
		</tr>
		<tr>
			<td colspan="2" class="center"><input type="submit" value="添加组织机构"/></td>
		</tr>
	 </tbody>
	</table>
	<form>
</div>
</body>
</html>