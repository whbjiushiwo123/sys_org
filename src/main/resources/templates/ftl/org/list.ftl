<!DOCTYPE html>
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
	var mt;
	$(function(){
		$("#tree").mytree(
			{
				async_url:"/admin/org/treeAll",
				onComplete:function(tree){
					mt=tree;
				}
			}
		);
	});
</script>
</head>
<body>
<input type="hidden" id="ctx" value=""/>
<div class="main_content_container" style="margin-left:0px">
	<table cellpadding="0" cellspacing="0">
	<tbody>
		<tr>
		<td width=230px align=left valign=top style="BORDER-RIGHT: #999999 1px dashed">
			<ul id="tree" class="ztree" style="width:230px; overflow:auto;"></ul>
		</td>
		<td width="850">
		<IFRAME ID="cc" Name="testIframe" FRAMEBORDER=0 SCROLLING=AUTO width=100%  height=600px ></IFRAME>
		</td>
		</tr>
	</tbody>
	</table>
</div>
</body>
</html>