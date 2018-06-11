<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>首页</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css">   
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/icon.css"> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/image.css">  
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/locale/easyui-lang-zh_CN.js"></script> 
<!-- 对话框初始化 -->
<script type="text/javascript">
$(function(){
	//用户更新对话框初始化
	$("#updateDialog").dialog({
		title:'更新用户',
		width:250,
		height:250,
		cache:false,
		closed:true,
		href:"${pageContext.request.contextPath}/front/update.jsp",
		onLoad:function(){
			//数据回显
			var user = $("#updateDialog").data("user");
			$("#updateForm").form("load",user);
			if(user.sex=="男"){
				$("#sex").switchbutton({
					checked:true,
				})
			}else{
				$("#sex").switchbutton({
					checked:false,
				})
			}
			
		},
		buttons:[{
			text:"更新",
			handler:function(){
				$("#updateForm").submit();
			}
		}]
	})  
	
	//用户注册对话框初始化
	$("#registerDialog").dialog({    
	    title: '用户注册',    
	    width: 250,    
	    height: 250, 
	    cache:false, 
	    closed:true,
	    href:"${pageContext.request.contextPath}/front/register.jsp", 
	    buttons:[{
	    	text:"注册",
	    	handler:function(){
	    		$("#registerForm").submit();	
	    	}
	    },
	    {
	    	text:"取消",
	    	handler:function(){
	    		$("#registerDialog").dialog("close");
	    	}
	    }
	    ]  
	});
	
	//用户登录对话框初始化
		$("#loginDialog").dialog({    
		    title: '用户登录',    
		    width: 250,    
		    cache:false,
		    height: 150,  
		    closed:true,
		    href:"${pageContext.request.contextPath}/front/login.jsp",  
		    closed: true,    
		    cache: false,       
		    modal: true, 
		    buttons:[{
		    	text:"登录",
		    	handler:function(){
		    		$("#loginForm").submit();
		    	}
		    }]
	    });
})
</script> 	
<!-- 弹出对话框 -->
<script type="text/javascript">
	//用户登录检测
	$(function(){
		$("#login").click(function(){
			$("#loginDialog").dialog("open");
		})
	})
	//用户注册
	$(function(){
		$("#register").click(function(){
				$("#registerDialog").dialog("open");
		})
	})
</script>
<!-- 区域初始化 -->
<script type="text/javascript">
	$(function(){
		//选项卡初始化
		$("#contentTabs").tabs({
			fit:true,
			narrow:true
		})
	})

</script>


</head>
<body class="easyui-layout">  
		<!-- 上面，标题栏 --> 
	    <div	data-options="region:'north',spit:false" style="height:5%;">
	    	<div class="easyui-layout" data-options="fit:true,border:false">
	    		<!-- 左边，标题：后台管理系统 -->
	    		<div data-options="region:'west',border:false" style="width:80%;padding-left:2%">
	    			<b style="line-height:20px">后台管理系统</b>
	    		</div>
	    		<div data-options="region:'east',border:false"	style="width:20%;">
	    			<a style="line-height:20px;text-decoration:none" id="login">登录</a>
	    		</div>  
	    	</div>
	    </div>   
	    <!-- 左面，操作菜单 -->
		<div data-options="region:'west',title:'导航菜单',split:false,href:'left.jsp'" style="width:15%;"></div>   
		<!-- 中间，内容 -->
	    <div data-options="region:'center'">
	    	<!-- 用于存放选项卡 -->
	    	<div id="contentTabs"></div>
		</div> 
		<!-- 下面，底部 --> 
	    <div	data-options="region:'south',spit:false" style="height:10%;padding-left:5%;"></div>     
		<!-- 注册对话框 -->
		<div id="registerDialog"></div>
		<!-- 登录对话框 -->
		<div id="loginDialog"></div>
		<!-- 更新对话框 -->
		<div id="updateDialog"></div> 
</body>  
</html>