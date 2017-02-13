$(function(){
    //显示侧边栏导航
    showDetailNav();

    //顶部导航滚动
    topNavScroll();
})

//显示侧边栏导航
showDetailNav();
function showDetailNav(){
    var btn = $("#showSideNav"),
        shadow = $("#shadow"),
        sideNav = $("#sideNav");

    btn.on("click",function(){
        shadow.show();
        sideNav.show();
    })

    shadow.on("click",function(){
        shadow.hide();
        sideNav.hide();
    })
}

//顶部导航滚动
function topNavScroll(){
    //顶部导航
    var topNav = $("#topNav").find(".swiper-wrapper"),
        items = topNav.find(".nav-item"),
        length = items.length,
        width = items.eq(0).innerWidth(),
        active_index = topNav.find(".nav-item.z-crt").index("#topNav .nav-item");

    scroll(active_index);

    //侧边栏导航
    // var sideNav = $("#sideNav"),
    //     sideItems = sideNav.find(".item"),
    //     sideLength = sideItems.length;

    //点击顶部导航选项，导航滚动
    // items.on("click",function(){
    //     var _ts = $(this),
    //         _index = _ts.index("#topNav .nav-item");

    //     _ts.addClass("z-crt").siblings().removeClass("z-crt");

    //     //scroll(_index);
    // });

    //点击侧边栏导航，导航滚动
    // sideItems.on("click",function(){
    //     var _ts = $(this),
    //         _index = _ts.index("#sideNav .item");

    //     if(length != sideLength){
    //         return false;
    //     }
    //     //点击侧边栏导航，相应的顶部导航项高亮
    //     items.eq(_index).addClass("z-crt").siblings().removeClass("z-crt");

    //     //scroll(_index);
    // });

    function scroll(index){
        //点击前三项时，导航不滚动
        if(index <= 1){
            topNav.css({
                "transform" : "translate3d(0px,0px,0px)"
            });
        }//当项数大于2并且不是最后3项时，滚动相应的距离
        else if(index > 1 && index < length-3){
            topNav.css({
                "transform" : "translate3d(" + (-width * (index - 1)) + "px,0px,0px)"
            });
        }//当点击最后3项时，让导航滚动相同的距离
        else if(index >= length - 3){
            topNav.css({
                "transform" : "translate3d(" + (-width * (length - 3)) + "px,0px,0px)"
            });
        }
    }
}