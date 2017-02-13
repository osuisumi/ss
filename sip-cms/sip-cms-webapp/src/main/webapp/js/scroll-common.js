(function ($) {
    $.fn.myScrollFn = function (options) {
        var settings = {//默认参数
            scrollBox: '#content', //滚动的内容
            pullUpIf: false, //是否上拉加载
            pullUpFn: function(){

            }, //上拉加载执行函数
            pullDownIf: false, //是否下拉刷新页面
            pullDownFn: function(){

            } //拉刷新页面执行函数
        };
        //$.extend(true, settings, options);
        var options = $.extend(true,settings,options),
            _ts = this,
            scrollBox = options.scrollBox,
            pullUpIf = options.pullUp,
            pullUpFn = options.pullUpFn,
            pullDownIf = options.pullDown,  
            pullDownFn = options.pullDownFn;
        //执行滚动
        loaded();

        var pullUp, pullUpTxt, pullUpIcon;
        var Upcount = 0;
        var loadingStep = 0; //加载状态0默认，1显示加载状态，2执行加载数据，只有当为0时才能再次加载，这是防止过快拉动刷新  

        //上拉事件  
        function pullUpAction() { 
            setTimeout(function() {
                pullUp.removeClass('loading');
                pullUpTxt.html('上拉显示更多...');
                pullUp['class'] = pullUp.attr('class');
                pullUp.attr('class', '').hide();
                myScroll.refresh();
                loadingStep = 0;
                pullUpFn();
                
            }, 1500);
        }
        //滚动刷新或加载
        function loaded() {

            pullUp = $('#pullUp');
            pullUpTxt = pullUp.find('.pullUpTxt');
            pullUpIcon = pullUp.find('.pullUpIcon');
            pullUp['class'] = pullUp.attr('class');
            pullUp.attr('class', '').hide();

            myScroll = new IScroll(scrollBox, {
                probeType: 2,
                //probeType：1对性能没有影响。在滚动事件被触发时，滚动轴是不是忙着做它的东西。probeType：2总执行滚动，除了势头，反弹过程中的事件。这类似于原生的onscroll事件。probeType：3发出的滚动事件与到的像素精度。注意，滚动被迫requestAnimationFrame（即：useTransition：假）。  
                scrollbars: true,
                //有滚动条  
                mouseWheel: true,
                //允许滑轮滚动  
                fadeScrollbars: true,
                //滚动时显示滚动条，默认影藏，并且是淡出淡入效果  
                bounce: true,
                //边界反弹  
                interactiveScrollbars: true,
                //滚动条可以拖动  
                shrinkScrollbars: 'scale',
                // 当滚动边界之外的滚动条是由少量的收缩。'clip' or 'scale'.  
                click: true,
                // 允许点击事件  
                keyBindings: true,
                //允许使用按键控制  
                momentum: true // 允许有惯性滑动  
            });
            //判断是否允许上拉加载
            if(pullUpIf){
                console.log(pullUpIf);
                //滚动时
                myScroll.on('scroll', function() {
                    if (loadingStep == 0 && !pullUp.attr('class').match('flip|loading')) {
                        if (this.y < (this.maxScrollY - 5)) {
                            //上拉刷新效果  
                            pullUp.attr('class', pullUp['class'])
                            pullUp.show();
                            myScroll.refresh();
                            pullUp.addClass('flip');
                            pullUpTxt.html('上拉加载更多...');
                            loadingStep = 1;
                        }
                    }
                
                });
            }
            //判断是否允许上拉加载
            if(pullUpIf){
                //滚动完毕  
                myScroll.on('scrollEnd', function() {
                    if (loadingStep == 1) {
                        pullUp.removeClass('flip').addClass('loading');
                        pullUpTxt.html('Loading...');
                        loadingStep = 2;
                        pullUpAction();
                    }
                });
            }
            
        } 

    };
})(jQuery);






 