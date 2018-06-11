<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<body>
    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="userMap" style="width: 600px;height:400px;margin: 100px;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var userMapChart = echarts.init(document.getElementById('userMap'));

        var goEasy = new GoEasy({
            appkey: "my_appkey"
        });
        goEasy.subscribe({
            channel: "my_channel",
            onMessage: function (message) {
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
                            data: data
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
                            data: data
                        }
                    ]
                };

            }
        });






                // 使用刚指定的配置项和数据显示图表。
        userMapChart.setOption(option);
    </script>
</body>
