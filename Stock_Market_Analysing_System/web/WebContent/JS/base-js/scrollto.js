



//define定义模块
define(['jquery'],function($){
//	opts是传入参数
//  面向对象
//	第一个是空对象,第二个是默认参数,第三个是用户传入参数(含义是将默认参数翻盖保存到空对象里，并将空对象返回给this.opts属性)
		function ScrollTo(opts){
			this.opts = $.extend({},ScrollTo.DEFAULTS,opts);
			this.$el=$('html,body');
		}
//		提供的子方法move,获取传入参数dest,speed
		ScrollTo.prototype.move=function(){
			var opts=this.opts,
				dest=opts.dest;
			if($(window).scrollTop() != dest){
				if(!this.$el.is(':animated')){
					this.$el.animate({
						scrollTop:dest
					},opts.speed);
				}
			}
		}
//		提供的子方法go,只获取传入参数dest
		ScrollTo.prototype.go=function(){
			var dest=this.opts.dest;
			if($(window).scrollTop() != dest){
				this.$el.scrollTop(dest);
			}
		}
//		默认参数
		ScrollTo.DEFAULTS={
			dest:0,
			speed:800
		};
		return{
			ScrollTo:ScrollTo
		}
//	return{
//		move:function(){
////			alert("213132")
//			$("html,body").animate({
//				scrollTop:0
//			},800);
//		},
//		checkPosition:function(pos){
//			if($(window).scrollTop()>pos){
//				$('#backTop').fadeIn();
//			}else{
//				$("#backTop").fadeOut();
//			}
//		}
//	}
})