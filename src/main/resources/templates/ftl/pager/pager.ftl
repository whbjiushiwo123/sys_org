<script type="text/javascript" language="javascript">
$(document).ready(function() {
$("#pager").pager({ pagenumber: 1, pagecount: 15, buttonClickCallback: PageClick });
});
PageClick = function(pageclickednumber) {
$("#pager").pager({ pagenumber: pageclickednumber, pagecount: 15, buttonClickCallback: PageClick
});
$("#result").html("必优博客 jQuery分页器 当前第" + pageclickednumber + "页");
}
</script> 