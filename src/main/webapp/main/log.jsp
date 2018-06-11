<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<script type="text/javascript">
//展示所有用户
	$(function(){
        //轮播表，用可编辑的数据表格
		$("#logTable").edatagrid({
            url:"${pageContext.request.contextPath}/log/showAll",
            fit:true,
			fitColumns:true,
			pagination:true,
			singleSelect:false,
			ctrlSelect:true,
			pagination:true,
			columns:[[
			    //imgPath不需要
                {field:"id",align:"center",hidden:true},
				{field:"person",title:"操作人",align:"center",width:"25%"},
                {field:"thing",title:"操作时间",align:"center",width:"25%"},
				{field:"date",title:"操作日期",align:"center",width:"24%",
                    formatter:function(value){
                        var date=new Date(parseInt(value))
                        var y=date.getFullYear();
                        var m=date.getMonth()+1;
                        var d=date.getDate();
                        var h=date.getHours();
                        var mi = date.getMinutes();
                        var s = date.getSeconds();
                        return y+'-'+m+'-'+d;
                    }
                },
                {field:"success",title:"是否成功",align:"center",width:"25%",
                    formatter:function(value){
                       if(value=="true"){
                           return "操作成功";
                       }else {
                           return "操作失败";
                       }
                    }
                }

			]]
		})
	})

</script>

<!-- 展示所有轮播图 -->
<table  id="logTable" style="width:100%"></table>