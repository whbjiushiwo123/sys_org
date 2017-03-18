(function($,window,document){
	var MyTree={
			init:function(elem,opts){
				var self=this;
				self.elem=elem;
				self.$elem=$(elem);
				self.opts=$.extend({},$.fn.mytree.opts,opts);
				self.topts=parseOpts(self.opts);
				self.mytree=$.fn.zTree.init(self.$elem,self.topts);
				self.checkExpand();
				self.bindClick();
				self.bindEvent();
			},
			checkExpand:function(){
				var self=this;
				if(self.opts.expandAll){
					self.mytree.setting.callback.onAsyncSuccess=function(){
						self.mytree.expandAll(tree);//全部展开
						
					}
				}
			},
			bindClick:function(){
				var self=this;
				self.mytree.setting.callback.onClick=function(event,treeId,treeNode){
					$(self.opts.srcElement).attr("src",self.opts.rootHref+"/"+treeNode.id);
				}
			},
			bindEvent:function(){
				var self=this;
				if(typeof self.opts.onComplete==="function"&&self.opts.onComplete){
					self.opts.onComplete.call(self.elem,self.mytree);
				}
			}
	};
	function parseOpts(opts){
		var topts={};
		for(var key in opts){
			var index=key.indexOf("_");
			if(index>0){
				var k=key.substr(0,index);
				var v=key.substr(index+1);
				if(!topts[k]) topts[k]={};
				topts[k][v]=opts[key]
			}else{
				topts[key]=opts[key];
			}
		}
		return topts; 
	}
	$.fn.mytree=function(opts){
		return this.each(function(){
			var mt = Object.create(MyTree)
			mt.init(this,opts);
		});
	};
	$.fn.mytree.opts={
			data_simpleData:{
				enable:true,
				idKey:"id",
				pIdKey:"pid",
				rootPId:-1
			},
			view_dbClickExpand:false,
			view_selectedMultil:false,
			async_enable:true,
			async_url:"treeAll",
			srcElement:"#cc",
			rootHref:"orgs",
			expandAll:true,      
			dataType: "text",
			async_dataFilter: function (treeId, parentNode, childNodes) {
               return childNodes.orgTree;
            },//不加这个，则显示为undefined
		 	onComplete:null
	}
})(jQuery,window,document)