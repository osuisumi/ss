报表组件sip-reports
===================================
根据使用场景不同，分为2个组件模块

  
报表组件（图形类）sip-reports-chart
-----------------------------------
图形类报表核心使用[百度ECharts](http://echarts.baidu.com/)，基于[ZRender](http://ecomfe.github.io/zrender),一个canvase类库
封装工具类使用[ECharts-Java](http://git.oschina.net/free/ECharts)，开源组件

	本组件目的：
	1.提供页面图表展示统计数据，提供图表图片下载功能
	2.将静态资源打包到jar中，项目中再无需拷贝js，css文件(Servlet3特性),当时这样的话就无法使用nginx来加速了，按需使用


报表组件（表格类）sip-reports-table
-----------------------------------
表格类报表核心使用[DynamicJasper](http://dynamicjasper.com/),基于[JasperReports](http://community.jaspersoft.com/project/jasperreports-library)

	本组件目的：
	1.提供页面表格展示统计数据，提供表格各种格式下载，打印功能
	1.引入必要的包
	3.封装一些json bean，通用方法


报表组件示例项目 sip-reports-web
-----------------------------------
使用命令 tomcat7:run 来运行

	本组件目的：
	1.展示Demo
	1.展示Api	