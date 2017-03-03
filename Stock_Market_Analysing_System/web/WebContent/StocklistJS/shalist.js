
	function shadata(){
		$.ajax({
			"url": "http://localhost:8080/web/getStockList?code=sha",
			"type":"GET",
			"dataType":"json",
//		async:false,
//		data:{"code":"sha"},
			"contentType": "application/json; charset=utf-8",
			"success": function(data){
				$('#sha').dataTable({
					destroy: true,
					data:data,
					columns:[
						{data: '股票代码'},
						{data: '股票名称'},
						{data: '行业名称'},
						{data: '开盘价'},
						{data: '昨收'},
						{data:'当前价格'},
						{data: '今日最高价'},
						{data: '今日最低价'},
						{data: '竞买价'},
						{data: '竞卖价'},
						{data: '成交量'},
						{data: '成交金额'},
						{data: '更新时间'},
					],
					"sPaginationType": "full_numbers",
//		    "sScrollY":"700px",
					"aLengthMenu":[20],
					"createdRow":function(row,data,index){
						//alert(data.涨跌幅);
						if(data.开盘价<data.昨收){
							$('td',row).eq(3).css("color","green");
						}else{
							$('td',row).eq(3).css("color","red");
						}
						if(data.当前价格<data.昨收){
							$('td',row).eq(5).css("color","green");
						}else{
							$('td',row).eq(5).css("color","red");
						}
						if(data.今日最高价<data.昨收){
							$('td',row).eq(6).css("color","green");
						}else{
							$('td',row).eq(6).css("color","red");
						}
						if(data.今日最低价<data.昨收){
							$('td',row).eq(7).css("color","green");
						}else{
							$('td',row).eq(7).css("color","red");
						}
						if(data.竞买价<data.昨收){
							$('td',row).eq(8).css("color","green");
						}else{
							$('td',row).eq(8).css("color","red");
						}
						if(data.竞卖价<data.昨收){
							$('td',row).eq(9).css("color","green");
						}else{
							$('td',row).eq(9).css("color","red");
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
				$("body").delegate('#sha tbody tr', 'click', function () {
					var nTds = $('td', this);
					var code = $(nTds[0]).text();
					window.location.href="http://localhost:8080/web/oneStock.html?code="+code;
				});
			}
		});
	}


//
//	"render":function(data,type,full,callback){
// 		if(data<0){
// 			 $('td', row).eq(0).css('color','#fff')
// 		}else{
// 			
// 		}
// 	}