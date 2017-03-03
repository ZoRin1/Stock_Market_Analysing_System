//define定义模块
define(['jquery','scrollto'],function($,scrollto){
//	面向对象写法,el(传入按钮对象)，opts(可选参数)
	function BackTop(el,opts){
//	第一个是空对象,第二个是默认参数,第三个是用户传入参数(含义是将默认参数翻盖保存到空对象里，并将空对象返回给this.opts属性)
		this.opts=$.extend({}, BackTop.DEFAULTS, opts);
//	保存传递过来的对象el,并将它转化为jq对象
		this.$el=$(el);
//	实例化一下scrollto下的构造函数scrollTo,并设置获取值
		this.scroll=new scrollto.ScrollTo({
			dest:this.opts.dest,
			speed:this.opts.speed
		})
		this._checkPosition();
//		_move和_checkPosition前面的'_'表示是内部方法（不支持api调用）,外部调用不了
		if(this.opts.mode=='move'){
			this.$el.on('click',$.proxy(this._move,this));
		}else{
			this.$el.on('click',$.proxy(this._go,this));
		}
		
		$(window).on('scroll',$.proxy(this._checkPosition,this));
	};
//	默认调用的方法
	BackTop.DEFAULTS={
//		默认方法模块
		mode:'move',//默认点击按钮使用的方法
		dest:0,//默认滚动条滚动到距离顶部的距离
		pos:$(window).height(),//默认滚动到一屏后显示返回按钮
		speed:800//默认点击按钮滚动的速度
	};
//	添加内部move方法
	BackTop.prototype._move=function(){
		this.scroll.move();
	};
//	添加内部go方法
	BackTop.prototype._go=function(){
		this.scroll.go();
	};
//	添加内部checkPosition方法
	BackTop.prototype._checkPosition=function(){
		var $el=this.$el;
		if($(window).scrollTop()>this.opts.pos){
//			滚动条距离顶部距离大于传入的设置距离底部距离参数值,则按钮(this.$el)显示
//			$el.fadeIn();
			$el.css('display','block');//修复默认闪现返回顶部按钮，在css中默认返回顶部按钮是none,滚动到相应位置时block，但是原fadein方法是inline
		}else{
			$el.fadeOut();
		}
	};
//	添加jq插件的调用写法
	$.fn.extend({
		backtop:function(opts){
//			this 指代的是$('#backTop')元素  
//			若选择器元素是多个  如class则:
		return this.each(function(){
			new BackTop(this,opts);
		})
			
		}
	})
	return{
		BackTop:BackTop
	}
})