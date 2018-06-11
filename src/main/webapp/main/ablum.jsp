<%@ page language="java" contentType="text/html; charset=utf-8" isELIgnored="false" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
$(function () {
    //专辑树形表格初始化
    $('#ablumTable').treegrid({
        url:'${pageContext.request.contextPath}/ablum/showAll',
        fit:true,
        fitColumns:true,
        toolbar:"#ablumButton",
        idField:'id',
        treeField:'title',
        pagination:true,
        columns:[[
            {title:'标题',field:'title',width:180},
            {title:'大小',field:'size',width:180},
            {title:'播放时间',field:'duration',width:180},
            {title:'下载路径',field:'downPath',width:180},
            {title:'下载时间',field:'uploadDate',width:180},
        ]]
    });
    //自定义工具栏初始化
    //专辑详情按钮
    $("#ablumDetailButton").linkbutton({
        iconCls:"icon-detail",
        onClick:function(){
            //getSelected方法得到选中行
            var row = $("#ablumTable").treegrid("getSelected");
            if(row==null||row.size!=null){
                $.messager.alert("警告","您未选中专辑,请重新选择");
            }else{
                $("#ablumDetailDialog").dialog("open");
                //填充内容
                $("#bannerDetailForm").form("load",{
                    title:row.title,
                    count:row.count,
                    score:row.score,
                    author:row.author,
                    broadCast:row.broadCast,
                    brief:row.brief,
                    publishDate:row.publishDate
                });
                $("#coverImg").prop("src",row.coverImg);
            }
        }
    });
    //添加专辑按钮
    $("#addAblumButton").linkbutton({
        iconCls:"icon-addA",
        onClick:function(){
            $("#addAblumDialog").dialog("open");
        }
    });
    //添加章节按钮
    $("#addChapterButton").linkbutton({
        iconCls:"icon-addAb",
        type:"post",
        onClick:function(){
            //getSelected方法得到选中行
            var row = $("#ablumTable").treegrid("getSelected");
            if(row==null||row.size!=null){
                $.messager.alert("警告","您未选中专辑,请重新选择");
            }else {
                $("#addChapterDialog").dialog("open");
                $("#ablumId").val(row.id);
            }
        }
    });
    //下载音频按钮
    $("#downMusicbutton").linkbutton({
        iconCls:"icon-down",
        onClick:function(){
            var row = $("#ablumTable").treegrid("getSelected");
            location.href = "${pageContext.request.contextPath}/ablum/down?url=" + row.downPath  + "&oldName=" + row.title+".mp3";
        }
    })
})
function submit(){
    var id =  $("#ablumId").val();
    $('#addChapterForm').form('submit',{
        url:'${pageContext.request.contextPath}/ablum/addChapter',
        queryParams:{
            id:id
        },
        success:function (data) {
            $('#ablumTable').treegrid('reload');
        }

    });
}
function submit1(){
    $('#addAblumForm').form('submit',{
        url:'${pageContext.request.contextPath}/ablum/addOne',
        success:function (data) {
            $('#ablumTable').treegrid('reload');
        }
    });
}

</script>
<!-- 轮播图按钮操作 -->

<div id="ablumButton" style="padding:5px 20px;">
    <a id="ablumDetailButton">专辑详情</a>
    <a id="addAblumButton">添加专辑</a>
    <a id="addChapterButton">添加章节</a>
    <a id="downMusicbutton">下载音频</a>
</div>
<table id="ablumTable"></table>
<!--	添加弹出框 -->
<!--   专辑详情弹出框  -->
<div id="ablumDetailDialog" class="easyui-dialog" title="专辑详情" style="width:400px;height:300px;"
     data-options="iconCls:'icon-add1',resizable:true,modal:true,closed:true,buttons:[{
                text:'关闭',
                handler:function(){
                    $('#ablumDetailDialog').dialog('close')
                }
			}]">
    <form id="bannerDetailForm" method="post">
        <div>
            <label for="title">标题:</label>
            <input id="title" class="easyui-textbox" type="text" name="title" disabled="disabled"/>
        </div>
        <div>
            <label for="coverImg">封面:</label>
            <img src="" id="coverImg"  style="width: 50px;height:50px;">
        </div>
        <div>
            <label for="count">章节数</label>
            <input id="count" class="easyui-textbox" type="text" name="count" disabled="disabled"/>
        </div>
        <div>
            <label for="score">评价:</label>
            <input id="score" class="easyui-textbox" type="text" name="score" disabled="disabled" />
        </div>
        <div>
            <label for="author">作者:</label>
            <input id="author" class="easyui-textbox" type="text" name="author" disabled="disabled" />
        </div>
        <div>
            <label for="broadCast">播音者:</label>
            <input id="broadCast" class="easyui-textbox" type="text" name="broadCast" disabled="disabled" />
        </div>
        <div>
            <label for="brief">简介:</label>
            <input id="brief" class="easyui-textbox" type="text" name="brief" disabled="disabled" />
        </div>
        <div    style="margin:10px 0px;">
            <label for="publishDate" >出版日期:</label>
            <input class="easyui-textbox" name="publishDate" id="publishDate" disabled="disabled"/>
        </div>
    </form>
</div>

<!--添加专辑弹出框-->
<div id="addAblumDialog" class="easyui-dialog" title="添加专辑" style="width:400px;height:300px;"
     data-options="iconCls:'icon-add1',resizable:true,modal:true,closed:true,buttons:[{
				text:'保存',
				handler:function(){
				    submit1();
				    $('#addAblumDialog').dialog('close');
				}
			},{
				text:'关闭',
				handler:function(){
				    $('#addAblumDialog').dialog('close')
                }
			}]">
    <form id="addAblumForm" method="post" enctype="multipart/form-data">
        <div>
            <label for="title1">标题:</label>
            <input id="title1" class="easyui-textbox" type="text" name="title" data-options="required:true" />
        </div>
        <div>
            <label for="coverImg1">上传封面</label>
            <input id = "coverImg1" class="easyui-filebox"  name="ablumFile">
        </div>
        <div>
            <label for="count1">章节数</label>
            <input id="count1" class="easyui-textbox" type="text" name="count" data-options="required:true" />
        </div>
        <div>
            <label for="score1">评价:</label>
            <input id="score1" class="easyui-textbox" type="text" name="score" data-options="required:true" />
        </div>
        <div>
            <label for="author1">作者:</label>
            <input id="author1" class="easyui-textbox" type="text" name="author" data-options="required:true" />
        </div>
        <div>
            <label for="broadCast1">播音者:</label>
            <input id="broadCast1" class="easyui-textbox" type="text" name="broadCast" data-options="required:true" />
        </div>
        <div>
            <label for="brief1">简介:</label>
            <input id="brief1" class="easyui-textbox" type="text" name="brief" data-options="required:true" />
        </div>
        <%--<div    style="margin:10px 0px;">--%>
            <%--<label for="publishDate1" >出版日期:</label>--%>
            <%--<input class="easyui-datebox" name="publishDate" id="publishDate1"/>--%>
        <%--</div>--%>
    </form>
</div>

<!--添加章节弹出框 -->
<div id="addChapterDialog" class="easyui-dialog" title="添加章节" style="width:400px;height:300px;"
     data-options="iconCls:'icon-add1',resizable:true,modal:true,closed:true,buttons:[{
				text:'保存',
				handler:function(){
				    submit();

				    $('#addChapterDialog').dialog('close');

				}
			},{
				text:'关闭',
				handler:function(){
				    $('#addChapterDialog').dialog('close')
                }
			}]">
    <form id="addChapterForm" method="post" enctype="multipart/form-data">
        <div>
            <input type="text" name="aid" id="ablumId"/>
        </div>
        <div>
            <label>上传文件:</label>
            <input class="easyui-filebox" type="text" name="music" />
        </div>
    </form>
</div>
