	
	
//	var data = [
//	      {
//	    	  "指数代码":  "000001",
//	    	  "指数名称":  "asdasd",
//	    	  "开盘点位":  "12.54",
//	    	  "昨收点位":  "1012.1",
//	    	  "当前点位":  "1112.1",
//	    	  "最高点位":  "122.1",
//	    	  "最低点位":  "1312.1",
//	    	  "成交量":  "14121",
//	    	  "成交金额":  "1512",
//	    	  "更新时间":  "15:00:00",
//	      },
//	      {
//	    	  "指数代码":  "000002",
//	    	  "指数名称":  "asdasd",
//	    	  "开盘点位":  "12.54",
//	    	  "昨收点位":  "1012.1",
//	    	  "当前点位":  "1112.1",
//	    	  "最高点位":  "122.1",
//	    	  "最低点位":  "1312.1",
//	    	  "成交量":  "14121",
//	    	  "成交金额":  "1512",
//	    	  "更新时间":  "15:00:00",
//	      },	      {
//	    	  "指数代码":  "000003",
//	    	  "指数名称":  "asdasd",
//	    	  "开盘点位":  "12.54",
//	    	  "昨收点位":  "1012.1",
//	    	  "当前点位":  "1112.1",
//	    	  "最高点位":  "122.1",
//	    	  "最低点位":  "1312.1",
//	    	  "成交量":  "14121",
//	    	  "成交金额":  "1512",
//	    	  "更新时间":  "15:00:00",
//	      }
//	]

$(document).ready(function(){
		$.ajax({
			"url": "http://localhost:8080/web/nowZhishu",
			"type":"GET",
			"dataType":"json",
			async:false,
	        "contentType": "application/json; charset=utf-8",
	        "success": function(data){
	    	    $('#marketlist').dataTable({
	    	    	data:data,
	    	    	columns:[
	    	    	    {data: '指数代码'},
	    	    	    {data: '指数名称'},
	    	    	    {data: '开盘点位'},
	    	    	    {data: '昨收点位'},
	    	    	    {data: '当前点位'},
	    	    	    {data: '最高点位'},
	    	    	    {data: '最低点位'},
	    	    	    {data: '成交量'},
	    	    	    {data: '成交金额'},
	    	    	    {data: '更新时间'},
	    	    ],
	    	    "sPaginationType": "full_numbers",
					"createdRow":function(row,data,index){
						//alert(data.涨跌幅);
						if(data.开盘点位<data.昨收点位){
							$('td',row).eq(2).css("color","green");
						}else{
							$('td',row).eq(2).css("color","red");
						}
						if(data.当前点位<data.昨收点位){
							$('td',row).eq(4).css("color","green");
						}else{
							$('td',row).eq(4).css("color","red");
						}
						if(data.最高点位<data.昨收点位){
							$('td',row).eq(5).css("color","green");
						}else{
							$('td',row).eq(5).css("color","red");
						}
						if(data.最低点位<data.昨收点位){
							$('td',row).eq(6).css("color","green");
						}else{
							$('td',row).eq(6).css("color","red");
						}

					},
	    		  "oLanguage": {
	    	          "sProcessing": "正在加载中......",
	    	          "sLengthMenu": "每页显示 _MENU_ 条记录",
	    	          "sZeroRecords": "对不起，查询不到相关数据！",
	    	          "sEmptyTable": "表中无数据存在！",
	    	          "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
	    	          "sInfoFiltered": "数据表中共为 _MAX_ 条记录",
	    	          "sSearch": "搜索",
	    	          "oPaginate": {
	    	              "sFirst": "首页",
	    	              "sPrevious": "上一页",
	    	              "sNext": "下一页",
	    	              "sLast": "末页"
	    	          }
	    	      } //多语言配置
	    	    }); 
	    	    /* Add events  这里把参数传递进去了 跳转到另外一个html页面 数据可能会存在sees里*/ 
	    		$('#marketlist tbody tr').on('click', function () {
	    			var sTitle;
	    			var nTds = $('td', this);
					var code = $(nTds[0]).text();
					window.location.href="http://localhost:8080/web/oneMarket.html?code="+code;
	    		 });
	        }
		});
	  });