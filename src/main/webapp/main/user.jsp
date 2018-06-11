<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<script type="text/javascript">
//展示所有用户
	$(function(){
        //轮播表，用可编辑的数据表格
		$("#userTable").edatagrid({
            url:"${pageContext.request.contextPath}/user/showAll",
            saveUrl:"${pageContext.request.contextPath}/user/update",
            updateUrl:"${pageContext.request.contextPath}/user/update",
            fit:true,
			fitColumns:true,
			pagination:true,
			singleSelect:false,
			ctrlSelect:true,
			pagination:true,
			view:detailview,
			detailFormatter:function(rowIndex,rowData){
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src=" ' + rowData.headPic + '" style="height:280px;width:950px"></td>' +
                    '<td style="border:0">' +
                    '<p>Status: ' + rowData.status + '</p>' +
                    '</td>' +
                    '</tr></table>';
            },
			columns:[[
                {field:"id",align:"center",hidden:true},
				{field:"phoneNum",title:"电话号码",align:"center",width:"9%"},
                {field:"username",title:"用户名",align:"center",width:"9%"},
				{field:"password",title:"密码",align:"center",width:"9%"},
                {field:"dharmaName",title:"法名",align:"center",width:"9%"},
                {field:"province",title:"省份",align:"center",width:"9%"},
                {field:"city",title:"城市",align:"center",width:"9%"},
                {field:"sex",title:"性别",align:"center",width:"9%"},
                {field:"sign",title:"标志",align:"center",width:"9%"},
                {field:"headPic",title:"头像",align:"center",width:"9%"},
				{field:"status",title:"状态",align:"center",width:"9%",
                    editor:"text",
                    option:{required:true}
                },
                {field:"date",title:"注册日期",align:"center",width:"9%",
                    formatter:function(value){
                        var date=new Date(parseInt(value))
                        var y=date.getFullYear();
                        var m=date.getMonth()+1;
                        var d=date.getDate();
                        return y+'-'+m+'-'+d;
                    }
				}

			]],	
			toolbar:"#userButton"
		})
		//自定义工具栏初始化
		//修改用户
		$("#updateUserButton").linkbutton({
			iconCls:"icon-edit",
				text:"修改",
				onClick:function(){
					//getSelections方法得到所有选中行
					var row = $("#userTable").edatagrid("getSelected");
					if(row.length==0){
						$.messager.alert("警告","您还未选择数据");
					}else{
					    $("#userTable").edatagrid("editRow",$("#userTable").edatagrid("getRowIndex"),row);
                        $('#userTable').edatagrid('saveRow');
                    }
				}
		});
        //保存用户
        $("#saveUserButton").linkbutton({
            iconCls:"icon-save",
            text:"保存",
            onClick:function(){
                $("#userTable").edatagrid("saveRow");
            }
        });
		//导出用户表按钮
        $("#exportUserButton").linkbutton({
            iconCls:"icon_export",
            onClick:function(){
				$("#exportUserDialog").dialog("open");
            }
        });
        //树形表单中button点击事件
		$("#combotreeButton").click(function () {
		    //得到树形下拉框的输入的文本
			var text = $("#exportUserCombotree").combotree("getText");
			//得到组件数组值
			var value = $("#exportUserCombotree").combotree("getValues");
            //树形表单提交事件
            $("#exportUserForm").form('submit',{
                queryParams:{"titles":text,"fileds":value},
                url:"${pageContext.request.contextPath}/user/exportUser"
            })
        });
		//导入用户表按钮
        $("#importUserButton").linkbutton({
            iconCls:"icon_import",
            onClick:function(){
                $("#importUserDialog").dialog("open");
            }
        });

        $("#exportButton").click(function () {
            $("#importUserForm").form('submit',{
                url:"${pageContext.request.contextPath}/user/importUser",
				success:function (data) {
                    $("#importUserDialog").dialog("close");
                    $("#userTable").edatagrid("reload");
                }
            })
        });

    });
</script>
<!-- 用户按钮操作 -->
<div id="userButton" style="padding:5px 20px;">
		<a id="updateUserButton">更新用户</a>
		<a id="saveUserButton">保存修改</a>
		<a id="exportUserButton">导出用户表</a>
		<a id="importUserButton">导入用户表</a>
</div>  
<!-- 展示所有用户 -->
<table  id="userTable" style="width:100%"></table>

<!--导出用户表弹出框-->
<div id="exportUserDialog" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"
	 data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
		<form id ="exportUserForm" method="post" style="margin: 30px;">
			<select id="exportUserCombotree" class="easyui-combotree" style="width:200px;"
					data-options="url:'${pageContext.request.contextPath}/json/combotree.json',required:true,checkbox:true,
					onlyLeafCheck:true,multiple:true">
			</select>
			<a id="combotreeButton" class="easyui-linkbutton" data-options="iconCls:'icon_import'">确认导出</a>
		</form>
</div>
<!--导入用户表弹出框-->
<div id="importUserDialog" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"
	 data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
	<form id ="importUserForm" method="post" style="margin: 30px;">
		<input class="easyui-filebox" style="width:300px" id="importUserFile" name="file">
		<br/><br/>
		<center><a id="exportButton" class="easyui-linkbutton" data-options="iconCls:'icon_export'">确认导入</a></center>
	</form>
</div>
