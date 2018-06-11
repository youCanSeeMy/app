<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<body>
    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="userActive" style="width: 600px;height:400px;margin: 100px;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var userActiveChart = echarts.init(document.getElementById('userActive'));
        // 指定图表的配置项和数据
        var option = {
            title: {
                //标题
                text: '驰名法州App活跃用户'
            },
            tooltip: {},
            xAxis: {
                data: ["7天","15天","30天","90天","半年","一年"]
            },
            yAxis: {},
        };
        // 使用刚指定的配置项和数据显示图表。
        userActiveChart.setOption(option);

        $.ajax({
            url:"${pageContext.request.contextPath}/user/userActive",
            type:"post",
            dataType:"JSON",
            success:function (data) {
                console.log(data);
               userActiveChart.setOption({
                   series: [{
                       type: 'bar',
                       data: data
                   }]
               })
            }
        })
    </script>
</body>
