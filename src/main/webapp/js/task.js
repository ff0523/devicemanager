var example2;
var searchParams = encodeURI(JSON.stringify([{column: "UPDATE_TIME",type: "orderByDesc"},{column: "STATUS",type: "eq",value:1}]));
 function initTable() {
	 example2 = $('#example2').DataTable({
      'paging'      : true,
      'lengthChange': false,
      'searching'   : false,
      'ordering'    : false,
      'info'        : true,
      'autoWidth'   : false,
      "scrollCollapse" : true,
      "pageLength" : 20,//每页显示多少条
      "bServerSide": true,//这个用来指明是通过服务端来取数据
      "bPaginate":true,
      'ajax': {
          'url': '/task/listByPage?data='+searchParams,
          'type' : "post",
          'dataType' : "JSON",
		  "dataSrc" : "data"
      },
     "columns" : [
    	 {"defaultContent" : ""},
    	 {"defaultContent" : ""},
    	 {"defaultContent" : ""},
    	 {"defaultContent" : ""},
    	 {"defaultContent" : ""},
		 {"defaultContent" : ""},
		 {"defaultContent" : ""},
		 {"defaultContent" : ""},
		 {"defaultContent" : ""},
		 {"defaultContent" : ""},
		 {"defaultContent" : ""}
		],
		 "fnDrawCallback":function() {
			 var api = this.api();
			 var startIndex = api.context[0]._iDisplayStart;        //获取到本页开始的条数 　
			 api.column(1).nodes().each(function (cell, i) {
				 cell.innerHTML = startIndex + i + 1;
			 })
		 },
		"columnDefs":[
			{
				"targets" : [0],
				"data" : "id",
				"render" : function(data, type, full){
					return "<input type='checkbox' class='check' id='checkOne' name='checkOne'  value='"+data+"' />";
				}
			},
			{
				"targets" : [1],
				"data" : "null"
			},
			{
				"targets" : [2],
				"data" : "experimentCode",
				"render" : function(data, type, full){
					 if(data != null){
					    return data;
					 }else {
					    return "--";
					}
				}
			},
		 {
			 "targets" : [3],
			 "data" : "deviceCode",
			 "render" : function(data, type, full){
				 if(data != null){
					 return data;
				 }else {
					 return "--";
				 }
			 }
		 },
		 {
				"targets" : [4],
				"data" : "deviceName",
				"render" : function(data, type, full){
					if(data != null){
						return data;
					}else {
						return "--";
					}
				}
			},
			{
				"targets" : [5],
				"data" : "taskStatus",
				"render" : function(data, type, full){
					if(data == 0){
						return "未开始";
					}else if(data == 1){
						return "进行中";
					}else if(data == 1){
						return "已结束";
					}else {
						return "--";
					}
				}
			},
			{
				"targets" : [6],
				"data" : "beginTime",
				"render" : function(data, type, full){
					if(data != null){
						return data;
					}else {
						return "--";
					}
				}
			},
			{
				"targets" : [7],
				"data" : "currTime",
				"render" : function(data, type, full){
					if(data != null){
						return data;
					}else {
						return "--";
					}
				}
			},
			{
				"targets" : [8],
				"data" : "taskExecutor",
				"render" : function(data, type, full){
					if(data != null){
						return data;
					}else {
						return "--";
					}
				}
			},
			{
				"targets" : [9],
				"data" : "nextExperimentCode",
				"render" : function(data, type, full){
					if(data != null){
						return data;
					}else {
						return "--";
					}
				}
			},
			{
				"targets" : [10],
				"data" : "id",
				"render" : function(data, type, full) {
					return authInterceptor(full);
				}
			} ],
		"oLanguage": { //国际化配置
			"oPaginate": {
			"sFirst": "第一页",
			"sPrevious": "上一页",
			"sNext": "下一页",
			"sLast": "最后一页"
			},
			"sInfo": "第 _START_ 页，共 _TOTAL_ 页",
			"sInfoEmpty": "第 0 页，共 0 页",
			"sInfoFiltered" : "",
			"sZeroRecords" : "未找到记录！"
			}
    })

  }

 function authInterceptor(full) {
	 var btns = "";
	 var updateDevice = "<div class=\"row\"> <div class=\"col-md-2\"><button style=\"width: 100%; height:33px;position: relative;\" data-toggle=\"modal\" class=\"btn btn-default\" data-target=\"#addModal\">修改</button></div>";
	 var deleteDevice = "<div class=\"col-md-2\"><button style=\"width: 100%; height:33px;position: relative;\" data-toggle=\"modal\" class=\"btn btn-default\" data-target=\"#addModal\">删除</button></div>";
	 var insertDevice = "<div class=\"col-md-2\"><button style=\"width: 100%; height:33px;position: relative;\" data-toggle=\"modal\" class=\"btn btn-default\" data-target=\"#addModal\">插入</button></div>";
	 var insertBatchDevice = "<div class=\"col-md-3\"><button style=\"width: 100%; height:33px;position: relative;\" data-toggle=\"modal\" class=\"btn btn-default\" data-target=\"#addModal\">批量插入</button></div>";
	 var getTaskInfo = "<div class=\"col-md-3\"><button style=\"width: 100%; height:33px;position: relative;\" data-toggle=\"modal\" class=\"btn btn-default\" data-target=\"#addModal\">详细信息</button></div></div>";
	 btns = updateDevice + deleteDevice + insertDevice + insertBatchDevice + getTaskInfo;
	 return btns;
	}
  
  function query() {
	  searchParams = encodeURI(JSON.stringify([
		  {column: "DEVICE_CODE",type: "like", value: $('#searchCode').val()},
		  {column: "DEVICE_NAME",type: "like", value: $('#searchName').val()},
		  {column: "STATUS",type: "eq",value:1},
		  {column: "UPDATE_TIME",type: "orderByDesc"}
	  ]));
	  example2.ajax.url('/task/listByPage?data='+searchParams).load();
 	}

function checkAll() {
	var nn = $("#checkAll").is(":checked"); //判断th中的checkbox是否被选中，如果被选中则nn为true，反之为false
	if (nn == true) {
		var namebox = $("input[name^='checkOne']"); //获取name值为boxs的所有input
		for (i = 0; i < namebox.length; i++) {
			namebox[i].checked = true; //js操作选中checkbox
		}
	}
	if (nn == false) {
		var namebox = $("input[name^='checkOne']");
		for (i = 0; i < namebox.length; i++) {
			namebox[i].checked = false;
		}
	}
}
