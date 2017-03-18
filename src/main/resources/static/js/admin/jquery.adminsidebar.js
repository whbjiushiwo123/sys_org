(function($,document,window){
	//Sider变量
	var Sidebar={
			init:function(elem,opts){
				var self=this;
				self.elem=elem;
				self.$elem=$(elem);
				self.opts=$.extend({},$.fn.adminsidebar.opts,opts);
				self.initDisplay();
				self.initEvent();
			},
			initDisplay:function(){
				var self=this;
				self.$elem.addClass("sidebar_dl");
				self.$elem.find("dl").addClass("toggle");
				self.$elem.find("dl dt").addClass("toggleHeader");
				self.$elem.find("dl dd a").addClass("toggleHref");
				self.initStatus();
			},
			initStatus:function(){
				var self=this;
				self.$elem.find("dl[status='hide']").find("dd").css("display","none");
			},
			initEvent:function(){
				var self=this;
				self.$elem.find("dl dt").on("click",function(){
					/*var dlE=$(this).parent("dl");
					var status=dlE.attr("status");
					if(status=="hide"){
						dlE.attr("status","show");
						dlE.find("dd").slideDown(1000);
					}
					if(status=="show"){
						dlE.attr("status","hide");
						dlE.find("dd").slideUp(1000);
					}*/
					var dlE=$(this).parent().parent().find("dl");
					var sort=$(this).parent().attr("sort");
					$.each(dlE,function(i,n){
						console.log(sort+"ddd");
						console.log($(n).attr("sort"));
						if($(n).attr("sort")!=sort){
							$(n).attr("status","hide");
							$(n).find("dd").slideUp(1000);
						}else{
							var nowStatu=$(n).attr("status");
							if(nowStatu=="hide"){
								$(n).attr("status","show");
								$(n).find("dd").slideDown(1000);
							}else if(nowStatu=="show"){
								$(n).attr("status","hide");
								$(n).find("dd").slideUp(1000);
							}
						}
					});
				});
			}
	};
	$.fn.adminsidebar=function(opts){
		return this.each(function (){
			 var sidebar = Object.create(Sidebar);
			 sidebar.init(this,opts);
		});
	};
	//初始化的设置，有可能不用
	$.fn.adminsidebar.opts={
		
	};
})(jQuery,document,window);
