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
		   <#include "/ftl/org/nav.ftl"/>
	    </td>
	  </tr>
	  <tr>
	    <td colspan="2" class="left">修改${org.name}组织机构<span class="em">[父组织-->${parent.name }]</span></td>
	  </tr>
	</thead>
	<tbody>
		<tr>
			<td class="right">组织机构名称:</td>
			<td class="left">
				<input name="name" id="name" size="30" value="${org.name}"/>
				<errors cssClass="errorContainer" name="name"/>
			</td>
		</tr>
		<tr>
			<td class="right">组织机构类型:</td>
			<td class="left">
			<select id="typeId" name="typeId">
				<option value="-1">选择组织机构类型</sf:option>
				<#list orgTypes as orgType>
					<option items="${orgType.cname }" itemLabel="cname" value="${orgType.cid}">${orgType.cname}</option>
				</#list>
			</select>
			<errors cssClass="errorContainer" name="typeId"/>
			<input name="typeName" id="typeName" readonly="true"/>
			</td>
		</tr>
		<tr>
			<td class="right">组织机构管理类型:</td>
			<td class="left">
			<select id="managerType" name="managerType">
				<option value="-1">选择组织机构管理类型</option>
				<option value="0">子组织</option>
				<option value="1">所有</option>
				<option value="2">自定义</option>
				<option value="-1">无</option>
			</select>
			</td>
		</tr>
		<tr>
			<td class="right">组织机构地址:</td>
			<td class="left"><input name="address" id="address" size="70" value='${org.address!}'/></td>
		</tr>
		<tr>
			<td class="right">组织机构电话:</td>
			<td class="left"><input name="phone"  id="phone" size="70" value='${org.phone!}'/></td>
		</tr>
		<tr>
			<td class="right">排序号:</td>
			<td class="left"><input name="orderNum" id="orderNum" size="70" value='${org.orderNum!}'/></td>
		</tr>
		<tr>
			<td colspan="2" class="center"><input type="submit" value="修改组织机构"/></td>
		</tr>
	 </tbody>
	</table>
	</form>
</div>
</body>
</html>