<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<body>
    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="userMap" style="width: 600px;height:400px;margin: 100px;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var userMapChart = echarts.init(document.getElementById('userMap'));
        // 指定图表的配置项和数据
        option = {
            title: {
                text: '持名法州APP用户分布图',
                subtext: '2017年6月15日 最新数据',
                left: 'center'
            },
            tooltip: {
                trigger: 'item'
            },
            // 说明
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['男', '女']
            },
            visualMap: {
                min: 0,
                max: 2500,
                left: 'left',
                top: 'bottom',
                text: ['高', '低'],           // 文本，默认为数值文本
                calculable: true
            },
            // 工具箱
            toolbox: {
                show: true,
                orient: 'vertical',
                left: 'right',
                top: 'center',
                feature: {
                    dataView: {readOnly: false},
                    restore: {},
                    saveAsImage: {}
                }
            },
            series: [
                {
                    name: '男',
                    type: 'map',
                    mapType: 'china',
                    roam: false,
                    label: {
                        normal: {
                            show: true
                        },
                        emphasis: {
                            show: true
                        }
                    },
                    data: []
                },
                {
                    name: '女',
                    type: 'map',
                    mapType: 'china',
                    label: {
                        normal: {
                            show: true
                        },
                        emphasis: {
                            show: true
                        }
                    },
                    data: []
                }
            ]
        };
        // 使用刚指定的配置项和数据显示图表。
        userMapChart.setOption(option);

        $.ajax({
            url:"${pageContext.request.contextPath}/user/userMap",
            data:{"sex":"男"},
            type:"post",
            dataType:"JSON",
            success:function (data) {
                console.log(data);
                userMapChart.setOption({
                    series: [
                        {
                            name: '男',
                            type: 'map',
                            mapType: 'china',
                            roam: false,
                            label: {
                                normal: {
                                    show: false
                                },
                                emphasis: {
                                    show: true
                                }
                            },
                            data:data
                        }
                    ]
                })
            }
        })
        $.ajax({
            url:"${pageContext.request.contextPath}/user/userMap",
            data:{"sex":"女"},
            type:"post",
            dataType:"JSON",
            success:function (data) {
                console.log(data);
                userMapChart.setOption({
                    series: [
                        {
                            name: '女',
                            type: 'map',
                            mapType: 'china',
                            roam: false,
                            label: {
                                normal: {
                                    show: false
                                },
                                emphasis: {
                                    show: true
                                }
                            },
                            data:data
                        }
                    ]
                })
            }
        })

        <%--$(function () {--%>
            <%--$.post("${pageContext.request.contextPath}/user/userMap?sex='男'", function (data) {--%>
                <%--console.log(data);--%>
                <%--userMapChart.setOption({--%>
                    <%--series: [{--%>
                        <%--// 根据名字对应到相应的系列--%>
                        <%--name: '男',--%>
                        <%--data: data--%>
                    <%--}]--%>
                <%--});--%>
            <%--}, "json");--%>

            <%--$.post("${pageContext.request.contextPath}/user/userMap?sex='女'", function (data) {--%>
                <%--console.log(data);--%>
                <%--userMapChart.setOption({--%>
                    <%--series: [{--%>
                        <%--// 根据名字对应到相应的系列--%>
                        <%--name: '女',--%>
                        <%--data: data--%>
                    <%--}]--%>
                <%--});--%>
            <%--}, "json");--%>
        <%--});--%>
    </script>
</body>
