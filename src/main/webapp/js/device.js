var example2;
var searchParams = encodeURI(JSON.stringify([{column: "UPDATE_TIME",type: "orderByDesc"},{column: "STATUS",type: "eq",value:1}]));
searchParams = encodeURI(JSON.stringify([
	{column: "DEVICE_CODE",type: "like", value: $("#searchCode").val()},
	{column: "DEVICE_NAME",type: "like", value: $("#searchName").val()},
	{column: "DEVICE_STATUS",type: "eq", value: $("#searchStatus").val()},
	{column: "STATUS",type: "eq",value:1},
	{column: "UPDATE_TIME",type: "orderByDesc"}
]));
function initTable() {
	 example2 = $('#example2').DataTable({
      'paging'      : true,
      'lengthChange': false,
      'searching'   : false,
      'ordering'    : false,
      'info'        : true,
      'autoWidth'   : false,
      "scrollCollapse" : true,
      "pageLength" : 10,//每页显示多少条
      "bServerSide": true,//这个用来指明是通过服务端来取数据
      "bPaginate":true,
      'ajax': {
          'url': '/device/listByPage?data='+searchParams,
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
				"targets" : [3],
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
				"targets" : [4],
				"data" : "deviceStatus",
				"render" : function(data, type, full){
					if(data == 0){
						return "不可用";
					}else if(data == 1){
						return "可用";
					}else {
						return "--";
					}
				}
			},	
			{
				"targets" : [5],
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
			"sInfo": "第 _PAGE_ 页，共 _PAGES_ 页",
			"sInfoEmpty": "第 0 页，共 0 页",
			"sInfoFiltered" : "",
			"sZeroRecords" : "未找到记录！"
			}
    })

  }

 function authInterceptor(full) {
		var btns = "";
		var updateDevice = "<div class=\"row\"> <div class=\"col-md-4\"><button style=\"width: 100%\" data-toggle=\"modal\" class=\"btn btn-default\" data-target=\"#addModal\">修改</button></div>";
	 	var deleteDevice = "<div class=\"col-md-4\"><button style=\"width: 100%\" data-toggle=\"modal\" class=\"btn btn-default\" data-target=\"#addModal\">删除</button></div></div>";
		btns = updateDevice + "  " + deleteDevice;
		return btns;
	}
  
  function query() {
	  searchParams = encodeURI(JSON.stringify([
		  {column: "DEVICE_CODE",type: "like", value: $("#searchCode").val()},
		  {column: "DEVICE_NAME",type: "like", value: $("#searchName").val()},
		  {column: "DEVICE_STATUS",type: "eq", value: $("#searchStatus").val()},
		  {column: "STATUS",type: "eq",value:1},
		  {column: "UPDATE_TIME",type: "orderByDesc"}
	  ]));
	  example2.ajax.url('/device/listByPage?data='+searchParams).load();
 	}

function insert() {
	insertParams = encodeURI(JSON.stringify(
		{"DEVICE_ID":$('#addID').val(),
		"DEVICE_CODE":$('#addCode').val(),
		"DEVICE_NAME":$('#addName').val(),
		"DEVICE_STATUS":$('#addStatus').val(),
		"STATUS":1}
	));
	$.ajax({
		url: '/device/insert?data='+insertParams,
		type: "post",
		dataType: "JSON",
		success:function (data){
			alert(data.message);
		},
		error:function (err){
			alert("添加失败！");
		}
	}),
	searchParams = encodeURI(JSON.stringify([
		{column: "DEVICE_CODE",type: "like", value: $("#searchCode").val()},
		{column: "DEVICE_NAME",type: "like", value: $('#searchName').val()},
		{column: "STATUS",type: "eq",value:1},
		{column: "UPDATE_TIME",type: "orderByDesc"}
	]));
	//example2.ajax.url('/device/listByPage?data='+searchParams).load();
}

var updateFormCheck = $('#updateForm');
$(document).ready(function () {
	$('updateFormCheck').bootstrapValidator({
		message: '输入值不合法',
		feedbackIcons: {
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {
			updateID: {
				message: '设备ID不合法',
				validators: {
					notEmpty: {
						message: '设备ID不能为空'
					},
					stringLength: {
						min: 1,
						max: 30,
						message: '请输入1到30个字符'
					},
					regexp: {
						regexp: /^[0-9]+$/,
						message: '设备ID只能由数字组成'
					}
				}
			}
			, updateCode: {
				message: '设备编号不合法',
				validators: {
					notEmpty: {
						message: '设备编号不能为空'
					},
					stringLength: {
						min: 1,
						max: 30,
						message: '请输入1到30个字符'
					},
					regexp: {
						regexp: /^[a-zA-Z0-9 \u4e00-\u9fa5 ]+$/,
						message: '设备编号只能由字母、数字和汉字组成'
					}
				}
			}, updateName: {
				validators: {
					notEmpty: {
						message: '设备名称不能为空'
					}
				}
			}
		}
	});
});
$("#updateSubmit").click(function () {
	//进行表单验证
	$('#updateFormCheck').bootstrapValidator('validate');
	var bv = updateFormCheck.data('bootstrapValidator');
	bv.validate();
	if (bv.isValid()) {
		console.log(updateFormCheck.serialize());
		//发送ajax请求
		$.ajax({
			url: 'validator.json',
			async: false,//同步，会阻塞操作
			type: 'GET',//PUT DELETE POST
			data: updateFormCheck.serialize(),
			complete: function (msg) {
				console.log('完成了');
			},
			success: function (result) {
				console.log(result);
				if (result) {
					window.location.reload();
				} else {
					$("#updateReturnMessage").hide().html('<label class="label label-danger">修改失败!</label>').show(300);
				}
			}, error: function () {
				$("#updateReturnMessage").hide().html('<label class="label label-danger">修改失败!</label>').show(300);
			}
		})
	}
});


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

$(document).ready(function() {
	$('#addForm')
		.bootstrapValidator({
			feedbackIcons: {
				valid: 'glyphicon glyphicon-ok',
				invalid: 'glyphicon glyphicon-remove',
				validating: 'glyphicon glyphicon-refresh'
			},
			fields: {
				addID: {
					message: '设备ID不合法',
					validators: {
						notEmpty: {
							message: '设备ID不能为空'
						},
						stringLength: {
							min: 1,
							max: 30,
							message: '请输入1到30个字符'
						},
						regexp: {
							regexp: /^[0-9]+$/,
							message: '设备ID只能由数字组成'
						}
					}
				}
				, addCode: {
					message: '设备编号不合法',
					validators: {
						notEmpty: {
							message: '设备编号不能为空'
						},
						stringLength: {
							min: 1,
							max: 30,
							message: '请输入1到30个字符'
						},
						regexp: {
							regexp: /^[a-zA-Z0-9 \u4e00-\u9fa5 ]+$/,
							message: '设备编号只能由字母、数字和汉字组成'
						}
					}
				}, addName: {
					validators: {
						notEmpty: {
							message: '设备名称不能为空'
						}
					}
				}
			}
		})
		.on('success.form.bv', function(e) {
			// Prevent form submission
			e.preventDefault();

			// Get the form instance
			var $form = $(e.target);

			// Get the BootstrapValidator instance
			var bv = $form.data('bootstrapValidator');

			insertParams = encodeURI(JSON.stringify(
				{"DEVICE_ID":$('#addID').val(),
					"DEVICE_CODE":$('#addCode').val(),
					"DEVICE_NAME":$('#addName').val(),
					"DEVICE_STATUS":$('#addStatus').val(),
					"STATUS":1}
			));
			$.ajax({
				url: '/device/insert?data='+insertParams,
				type: "post",
				dataType: "JSON",
				success:function (data){
					alert(data.message);
					$('#addModal').hide();
					searchParams = encodeURI(JSON.stringify([
						{column: "DEVICE_CODE",type: "like", value: $('#searchCode').val()},
						{column: "DEVICE_NAME",type: "like", value: $('#searchName').val()},
						{column: "STATUS",type: "eq",value:1},
						{column: "UPDATE_TIME",type: "orderByDesc"}
					]));
					example2.ajax.url('/device/listByPage?data='+searchParams).load();
				},
				error:function (err){
					alert("添加失败！");
				}
			})

		});
});
