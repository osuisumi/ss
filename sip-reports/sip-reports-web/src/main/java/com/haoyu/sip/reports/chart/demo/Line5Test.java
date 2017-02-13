package com.haoyu.sip.reports.chart.demo;

import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Line;


/**
 * 输出数据
 * @author Chao
 *
 */
public class Line5Test{
	
	public static String getLine5OptionString(){
		//地址:http://echarts.baidu.com/doc/example/line5.html
		GsonOption option = new GsonOption();
	    option.legend("高度(km)与气温(°C)变化关系");

	    /*这里工具图标的顺序，和页面上显示的顺序不同*/
	    option.toolbox().show(true).feature(Tool.mark, Tool.dataView, new MagicType(Magic.line, Magic.bar), Tool.restore, Tool.saveAsImage);

	    option.calculable(true);
	    option.tooltip().trigger(Trigger.axis).formatter("Temperature : <br/>{b}km : {c}°C");

	    ValueAxis valueAxis = new ValueAxis();
	    valueAxis.axisLabel().formatter("{value} °C");
	    option.xAxis(valueAxis);

	    CategoryAxis categoryAxis = new CategoryAxis();
	    categoryAxis.axisLine().onZero(false);
	    categoryAxis.axisLabel().formatter("{value} km");
	    categoryAxis.boundaryGap(false);
	    categoryAxis.data(0, 10, 20, 30, 40, 50, 60, 70, 80);
	    option.yAxis(categoryAxis);

	    Line line = new Line();
	    line.smooth(true).name("高度(km)与气温(°C)变化关系").data(15, -50, -56.5, -46.5, -22.1, -2.5, -27.7, -55.7, -76.5).itemStyle().normal().lineStyle().shadowColor("rgba(0,0,0,0.4)");
	    option.series(line);
//	    option.exportToHtml("line5.html");
//	    option.view();
	    return option.toPrettyString();
	}
	
	public static void main(String args[]){
		System.out.println(Line5Test.getLine5OptionString());
	}
}