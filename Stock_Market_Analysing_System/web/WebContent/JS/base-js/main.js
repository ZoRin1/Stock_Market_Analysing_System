

//起个别名
requirejs.config({
	paths:{
		jquery:'jquery-1.11.3.min'
	}
})

//引入模块
requirejs(['jquery','validate','backtop'],function($,validate,backtop){
	
//	new backtop.BackTop($('#backTop'),{
//		mode:'move',
//		pos:50,
//		speed:2000
//	});
//	new backtop.BackTop($('#backTop'),{
//		mode:'go',
//		dest:50,
//		pos:50,
//		speed:800
//	});
//		new backtop.BackTop($('#backTop'),{
//		mode:'go',
//		pos:10
//	});
//	jq插件的写法
	$('#backTop').backtop({
		mode:'go',
		dest:30,//返回到距离顶部的距离
		pos:60//距离顶部的距离为pos时按钮显示
	})
//	$("body").css('background-color','red');
	console.log(validate.isEqual(1,2));
//	scrollto.checkPosition($(window).height());
//	$('#backTop').on('click',scrollto.move());
//	$(window).on('scroll',scrollto.checkPosition($(window).height()));
//	var scroll=new scrollto.ScrollTo({
//		//dest,speed不写为scroll方法里的默认值
//		dest:0,//返回到距离顶部的距离
//		speed:800//返回的速度
//	});
//	//JQ工具$.proxy  第一个参数是调用的方法，第二个是this的指向 -指向实例对象
//	$('#backTop').on('click',$.proxy(scroll.move,scroll));
//	$('#backTop').on('click',$.proxy(scroll.go,scroll));

});

