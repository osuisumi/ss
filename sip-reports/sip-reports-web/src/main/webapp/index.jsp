<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>ECharts · Home</title>
</head>

<body>

<p>
	<a href="js/echarts/index.html" target="_blank">→ ECharts演示</a>
</p>

<p>
    <!-- ECharts单文件引入 -->
    <script src="<%=request.getContextPath() %>/js/echarts/build/dist/echarts-all.js"></script>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="height:400px"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts图表
        var myChart = echarts.init(document.getElementById('main')); 
        
        var option = {
            tooltip: {
                show: true
            },
            legend: {
                data:['销量']
            },
            toolbox: {
                show : true,
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    magicType : {show: true, type: ['line', 'bar']},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            xAxis : [
                {
                    type : 'category',
                    data : ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    "name":"销量",
                    "type":"bar",
                    "data":[5, 20, 40, 10, 10, 20]
                }
            ]
        };

        // 为echarts对象加载数据 
        myChart.setOption(option); 
    </script>
</p>
上图表源码如下：
<textarea rows="10" style="width:99%">
<div id="main" style="height:400px"></div>
<!-- ECharts单文件引入 -->
<script src="/js/echarts/build/dist/echarts-all.js"></script>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts图表
    var myChart = echarts.init(document.getElementById('main')); 
    
    var option = {
        tooltip: {
            show: true
        },
        legend: {
            data:['销量']
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
                data : ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                "name":"销量",
                "type":"bar",
                "data":[5, 20, 40, 10, 10, 20]
            }
        ]
    };

    // 为echarts对象加载数据 
    myChart.setOption(option); 
</script>
</textarea>

<div id="mainJava" style="height:400px"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts图表
    var myChartJava = echarts.init(document.getElementById('mainJava')); 
    
    var optionJava = <%=com.haoyu.sip.reports.chart.demo.Line5Test.getLine5OptionString()%>

    // 为echarts对象加载数据 
    myChartJava.setOption(optionJava); 
</script>
上图表源码如下：
<textarea rows="10" style="width:99%">
<!-- ECharts单文件引入 -->
<script src="/js/echarts/build/dist/echarts-all.js"></script>
<div id="mainJava" style="height:400px"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts图表
    var myChartJava = echarts.init(document.getElementById('mainJava')); 
    
    var option = &lt;%=com.haoyu.sip.reports.chart.demo.Line5Test.getLine5OptionString();%&gt;

    // 为echarts对象加载数据 
    myChartJava.setOption(option); 
</script>
<div id="main" style="height:400px"></div>

</textarea>
<p>
	<a href="js/echarts/index.html" target="_blank">→ DynamicReports演示</a>
</p>

</body>
</html>