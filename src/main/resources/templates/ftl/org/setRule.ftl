<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/admin/basic.css" type="text/css"/>
<link rel="stylesheet" href="/css/admin/index.css" type="text/css"/>
<link rel="stylesheet" href="/css/admin/table1.css" type="text/css"/>
<link rel="stylesheet" href="/css/icon.css" type="text/css"/>
<link rel="stylesheet" href="/css/zTree/zTreeStyle.css" type="text/css"/>
<script type="text/javascript" src="/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="/js/main.js"></script>
<script type="text/javascript" src="/js/tree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="/js/public/jquery.mytree.js"></script>
<script type="text/javascript">
$(function(){
	var mt;
	$("#tree").mytree({
		async_url:$("#ctx").val()+"/admin/org/treeAll",
		check_enable:true,
		check_chkboxType:{"Y":"","N":""},
		initCheckCname:"mids",
		onComplete:function(tree) {
			mt = tree;
		}
	});
	$("#setRule").click(function() {
		var nodes = mt.getCheckedNodes(true);
		if(nodes.length<=0) {
			alert("必须选择管理节点");
			return false;
		}
		var id = $("#id").val();
		var data = {};
		data["id"] = id;
		data["mids"] = new Array();
		for(var i=0;i<nodes.length;i++) {
			data["mids"].push(nodes[i].id);
		};
		$.post($("#ctx").val()+"/admin/org/setRule",data,function(data){
			alert(data.msg);
		});
	});
});
</script>
</head>
<body>
<#list mids as mid>
	<input type="hidden" class="mids" value="${mid}"/>
</#list>
<input type="hidden" id="ctx" value=""/>
<input type="hidden" id="id" value="${org.id }"/>
<div class="main_content_container" style="margin-left:20px">
	<table width="600px" cellpadding="0" cellspacing="0" align="center" id="admintable">
	<thead>
		<tr>
		<td style="padding-bottom:10px">
			设置<span class="em">[${org.name}]</span>组织机构的管理规则
			<input type="button" value="设置结束" id="setRule"/>
			<a href="/admin/org/orgs/${org.parent.id}"">返回列表</a>
		 </td>
		</tr>
	</thead>
	<tbody>
		<tr>
		<td align="center" valign=top style="border:1px solid #aaa;">
			<ul id="tree" class="ztree"></ul>
		</td>
	</tbody>
	</table>
</div>
</body>
</html>