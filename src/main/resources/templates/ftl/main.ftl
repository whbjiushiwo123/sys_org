<!DOCTYPE html>
<html>	
<head>
<title>后台管理页面测试</title>
<script type="text/javascript" src="/js/jquery-1.11.0.min.js"></script>
<link rel="stylesheet" href="/css/admin/basic.css" type="text/css"/>
<link rel="stylesheet" href="/css/admin/index.css" type="text/css"/>
<link rel="stylesheet" href="/css/admin/icon.css" type="text/css"/>
<link href='/css/admin/table1.css' rel='stylesheet' type='text/css' />
<script type="text/javascript" src="/js/main.js"></script>

</head>
<frameset rows="10%,*,10%" border="0" frameborder="0" framespacing="0">
	<frame src="${basePath}/ftl/include/top.ftl"/>
	<frameset cols="20%,*">
		<frame src="${basePath}/ftl/include/left.ftl"/>
		<frame name="content" src="${basePath}/ftl/include/table.ftl"/>
	</frameset>
	<frame src="${basePath}/ftl/include/bottom.ftl"/>
</frameset>
</html>
