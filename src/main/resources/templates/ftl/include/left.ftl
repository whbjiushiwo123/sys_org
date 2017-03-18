<!DOCTYPE html>
<html>	
<head>
<style type="text/css">
	.s{
		border-spacing:0px;
		border:1px solid #999;
		font-size:12px;
	}
	.s td{
		border-right:1px solid #999;
		border-bottom:1px solid #999;
		padding:10px 4px;
	}
	thead.headBg{
	background:#621;
	color:#fff;
	}
	tr.evenBg{
		background:#bbb;
	}
	tr.hoverBg{
		background:#484;
		color:#fff;
	}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/admin/basic.css" type="text/css"/>
<link rel="stylesheet" href="/css/admin/index.css" type="text/css"/>
<link rel="stylesheet" href="/css/admin/icon.css" type="text/css"/>
<script type="text/javascript" src="/js/jquery-1.11.0.min.js"></script>
<script src='/js/main.js'></script>
<script type="text/javascript" src="/js/admin/jquery.adminsidebar.js"></script>
<script src='/js/show.jquery.js'></script>

<script type="text/javascript">
	var $j = jQuery.noConflict();
	$j(function() {
		$j("#sidebar").adminsidebar();
		$j("#s").mytable({
			width:80,
			onComplete:function(mt,elem){
				console.log(mt);
			},
			theadBg:true,
			onTdClick:function(event){
				var mt = event.data.mt;
				console.log(mt.options.width);
				console.log($j(this).html());
			}
		});
	});
</script>
</head>
<body>
<div id="sidebar">
	<dl sort="1" status="show">
		<dt>组织机构管理</dt>
		<dd><a href="/admin/orgType/orgTypes" target="content"><span class="icon16 icon-user-info"></span>组织机构类型</a></dd>
		<dd><a href="/admin/org/orgs" target="content"><span class="icon16 icon-security"></span>组织机构管理</a></dd>
		<dd><a href="/admin/position/positions" target="content"><span class="icon16 icon-security"></span>岗位管理</a></dd>
	</dl>
	<dl sort="2" status="hide">
		<dt>学校部门管理</dt>
		<dd><a href="/admin/classroom/classrooms" target="content"><span class="icon16 icon-user-grey"></span>班级管理</a></dd>
		<dd><a href="table2.html" target="content"><span class="icon16 icon-role"></span>专业管理</a></dd>
		<dd><a href="table2.html" target="content"><span class="icon16 icon-users"></span>班级管理</a></dd>
	</dl>
	<dl sort="3"  status="hide">
		<dt>用户管理</dt>
		<dd><a href="table2.html" target="content"><span class="icon16 icon-user-grey"></span>用户管理</a></dd>
		<dd><a href="table2.html" target="content"><span class="icon16 icon-role"></span>角色管理</a></dd>
		<dd><a href="table2.html" target="content"><span class="icon16 icon-users"></span>用户组管理</a></dd>
	</dl>
	<dl sort="4" status="hide">
		<dt>业务管理</dt>
		<dd><a href="table2.html" target="content"><span class="icon16 icon-settings"></span>管理员设置</a></dd>
		<dd><a href="table2.html" target="content"><span class="icon16 icon-role"></span>角色管理</a></dd>
		<dd><a href="table2.html" target="content"><span class="icon16 icon-static"></span>静态化管理</a></dd>
	</dl>
</div>
</body>
</html>