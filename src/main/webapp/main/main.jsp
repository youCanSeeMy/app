﻿<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>持名法州主页</title>
<link rel="stylesheet" type="text/css" href="../themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../themes/IconExtension.css">
<link rel="stylesheet" type="text/css" href="../themes/icon.css">
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="../js/datagrid-detailview.js"></script>
<script type="text/javascript" src="../js/jquery.edatagrid.js"></script>
<script src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
<script src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
<script src="${pageContext.request.contextPath}/js/china.js"></script>

<script type="text/javascript">
    $(function () {

        <!--左边导航栏-->
        $.ajax({
            url:"${pageContext.request.contextPath}/menu/showAll.json",
            type:"post",
            dataType:"JSON",
           success:function (data) {
                //菜单栏的子栏
               $.each(data,function (index,first) {
                   var c='';
                   $.each(first.children,function (cIndex,cFirst) {
                       c += "<a href='#' style='width:100%' onclick=\"addTabs('"+cFirst.title+"','"+cFirst.url+"','"+cFirst.iconCls+"')\" class='easyui-linkbutton' data-options=\"iconCls:'"+cFirst.iconCls+"'\">" + cFirst.title + "</a><br/>";
                   })
                   $("#aa").accordion("add",{
                       title:first.title,
                       selected:true,
                       iconCls:first.iconCls,
                       content:c
                   })
               })
           }
        });
    })
    function addTabs(title, href, iconCls) {
        /*
        *存在选中  不存在添加
        * */
        var flag=$("#tt").tabs("exists",title);
        if (flag){
            $("#tt").tabs("select",title);
        }else {
            $('#tt').tabs('add', {
                title: title,
                selected: true,
                iconCls: iconCls,
                href: href,
                closable: true
            });
        }


    }
</script>

</head>

<body class="easyui-layout">

    <div data-options="region:'north',split:true" style="height:60px;background-color:  #5C160C">
    	<div style="font-size: 24px;color: #FAF7F7;font-family: 楷体;font-weight: 900;width: 500px;float:left;padding-left: 20px;padding-top: 10px" >持名法州后台管理系统</div>
    	<div style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 300px;float:right;padding-top:15px">欢迎您:${sessionScope.name}&nbsp;<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改密码</a>&nbsp;&nbsp;<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-01'">退出系统</a></div>
    </div>   
    <div data-options="region:'south',split:true" style="height: 40px;background: #5C160C">
    	<div style="text-align: center;font-size:15px; color: #FAF7F7;font-family: 楷体" >&copy;百知教育 htf@zparkhr.com.cn</div>
    </div>
    <!-- 左面，操作菜单 -->
    <div data-options="region:'west',title:'导航菜单',split:true" style="width:220px;">
    	<div id="aa" class="easyui-accordion" data-options="fit:true">
		</div>
    </div>
    <!--中间栏，弹出面板-->
    <div data-options="region:'center'">
    	<div id="tt" class="easyui-tabs" data-options="fit:true,narrow:true,pill:true">   
		    <div title="主页" data-options="iconCls:'icon-neighbourhood',"  style="background-image:url(image/shouye.jpg);background-repeat: no-repeat;background-size:100% 100%;"></div>
		</div>  
    </div>
</body> 
</html>