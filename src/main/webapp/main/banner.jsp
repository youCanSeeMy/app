<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<script type="text/javascript">
//展示所有用户
	$(function(){
        //轮播表，用可编辑的数据表格
		$("#showAllTable").edatagrid({
            url:"${pageContext.request.contextPath}/banner/showAll",
            saveUrl:"${pageContext.request.contextPath}/banner/update.do",
            updateUrl:"${pageContext.request.contextPath}/banner/update.do",
            destroyUrl:"${pageContext.request.contextPath}/banner/delete.do",
            fit:true,
			fitColumns:true,
			pagination:true,
			singleSelect:false,
			ctrlSelect:true,
			pagination:true,
			view:detailview,
			detailFormatter:function(rowIndex,rowData){
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src=" ' + rowData.imgPath + '" style="height:280px;width:950px"></td>' +
                    '<td style="border:0">' +
                    '<p>Attribute: ' + rowData.describle + '</p>' +
                    '<p>Status: ' + rowData.status + '</p>' +
                    '</td>' +
                    '</tr></table>';
            },
			columns:[[
			    //imgPath不需要
                {field:"id",align:"center",hidden:true},
				{field:"title",title:"标题",align:"center",width:"19%"},
                {field:"imgPath",align:"center",hidden:true},
				{field:"describle",title:"描述",align:"center",width:"19%"},
				{field:"status",title:"是否展示",align:"center",width:"19%",
                    editor:"text",
                    option:{required:true}
                },
                {field:"date",title:"日期",align:"center",width:"19%"}

			]],	
			toolbar:"#bannerButton"
		})
		//自定义工具栏初始化
		//修改轮播图
		$("#updateButton").linkbutton({
			iconCls:"icon-banner1",
				text:"修改",
				onClick:function(){
					//getSelections方法得到所有选中行
					var row = $("#showAllTable").edatagrid("getSelected");
					if(row.length==0){
						$.messager.alert("警告","您还未选择数据");
					}else{
					    $("#showAllTable").edatagrid("editRow",$("#showAllTable").edatagrid("getRowIndex"),row);
                        $('#showAllTable').edatagrid('saveRow');
                    }
				}
		});
		//添加轮播图
		$("#addButton").linkbutton({
			iconCls:"icon-add",
				text:"添加",
				href:"${pageContext.request.contextPath}/register",
				onClick:function(){
					$("#addBannerDialog").dialog("open");
				}	
		});
		//删除轮播图
		$("#deleteButton").linkbutton({
			iconCls:"icon-remove",
				text:"删除",
				type:"post",
				onClick:function(){
					var rows = $("#showAllTable").datagrid("getSelections");
					if(rows.length==0){
						$.messager.alert("警告","您还未选择数据");
					}else{
                        $("#showAllTable").edatagrid("destroyRow");
					}

                }
		});
        //保存轮播图
        $("#saveButton").linkbutton({
            iconCls:"icon-save",
            text:"保存",
            onClick:function(){
                $("#showAllTable").edatagrid("saveRow");
            }
        });
        function DateTimeFormatter(value) {
            if (value == undefined) {
                return "";
            }
            /*json格式时间转js时间格式*/
            value = value.substr(1, value.length - 2);
            var obj = eval('(' + "{Date: new " + value + "}" + ')');
            var dateValue = obj["Date"];
            if (dateValue.getFullYear() < 1900) {
                return "";
            }

            return dateValue.format("yyyy-MM-dd");
        }
	});
//表单提交函数
function submit() {
    $("#addBannerForm").form('submit',{
        url:"${pageContext.request.contextPath}/banner/add"
    })
}
</script>

<!-- 轮播图按钮操作 -->
<div id="bannerButton" style="padding:5px 20px;">
		<a id="updateButton">更新</a>
		<a id="addButton">添加</a>
		<a id="deleteButton">删除</a>
		<a id="saveButton">保存</a>
</div>  
<!-- 展示所有轮播图 -->
<table  id="showAllTable" style="width:100%"></table>
<!--	添加弹出框 -->
<div id="addBannerDialog" class="easyui-dialog" title="添加轮播图" style="width:400px;height:200px;"
	 data-options="iconCls:'icon-add1',resizable:true,modal:true,closed:true,buttons:[{
				text:'保存',
				handler:function(){
				    submit();
				    $('#addBannerDialog').dialog('close');
				    $('#showAllTable').edatagrid('reload');
				}
			},{
				text:'关闭',
				handler:function(){
				    $('#addBannerDialog').dialog('close')
                }
			}]">
	<form id="addBannerForm" method="post" enctype="multipart/form-data">
		<div>
			<label for="title">标题:</label>
			<input id="title" class="easyui-validatebox" type="text" name="title" data-options="required:true" />
		</div>
		<div>
			<label for="describle">描述:</label>
			<input id="describle" class="easyui-validatebox" type="text" name="describle" data-options="required:true" />
		</div>
		<div>
			<label for="addBannerSelect">描述:</label>
			<select id="addBannerSelect" class="easyui-combobox" name="status" style="width:200px;">
				<option value="">是否展示</option>
				<option value="y">展示</option>
				<option value="n">不展示</option>
			</select>
		</div>
		<input class="easyui-filebox"  name="img">
	</form>
</div>
