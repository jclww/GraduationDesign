<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <title>搜索页面</title>

    <link href="../AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css"/>
    <link href="../AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css"/>

    <link href="../css/basic/demo.css" rel="stylesheet" type="text/css"/>

    <link href="../css/basecss/seastyle.css" rel="stylesheet" type="text/css"/>

    <script type="text/javascript" src="../js/basejs/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="../js/basejs/script.js"></script>
    <script src="../js/basejs/jquery-form.js"></script>

</head>

<body>

<!--顶部导航条 -->
<div class="am-container header">
    <ul class="message-l">
        <div class="topMessage">
            <c:if test="${user==null}">
                <div class=" menu-hd">
                    <a href="login.html?from=index" target="_top" class="h">亲，请登录</a>&nbsp;&nbsp;
                    <a href="register.html" target="_top">免费注册</a>
                </div>
            </c:if>
            <c:if test="${user!=null}">
                <div class="menu-hd">
                    欢迎您,${user.name}&nbsp;&nbsp;
                    <a href="/logout" target="_top">安全退出</a>
                </div>
            </c:if>
        </div>
    </ul>
    <ul class="message-r">
        <div class="topMessage home">
            <div class="menu-hd"><a href="/index" target="_top" class="h">商城首页</a></div>
        </div>
        <div class="topMessage my-shangcheng">
            <div class="menu-hd MyShangcheng"><a href="/person" target="_top"><i class="am-icon-user am-icon-fw"></i>个人中心</a>
            </div>
        </div>
        <div class="topMessage mini-cart">
            <div class="menu-hd"><a id="mc-menu-hd" href="/cart" target="_top"><i
                    class="am-icon-shopping-cart  am-icon-fw"></i><span>购物车</span><strong id="J_MiniCartNum"
                                                                                          class="h">0</strong></a></div>
        </div>
        <div class="topMessage favorite">
            <div class="menu-hd"><a href="#" target="_top"><i class="am-icon-heart am-icon-fw"></i><span>收藏夹</span></a>
            </div>
    </ul>
</div>

<!--悬浮搜索框-->

<div class="nav white">
    <div class="logo"><img src="../img/baseimages/logo.png"/></div>
    <div class="logoBig">
        <li><img src="../img/baseimages/logobig.png"/></li>
    </div>

    <div class="search-bar pr">
        <a name="index_none_header_sysc" href="#"></a>
        <form id="searchForm" action="/search" method="get">
            <input id="searchInput" name="name" type="text" placeholder="搜索" autocomplete="off" value="${name}">
            <input id="categoryId" type="hidden" name="categoryId"/>
            <input id="ai-topsearch" type="button" class="submit am-btn" value="搜索" index="1"
                   onclick="submitForm('#searchForm')">
        </form>
        <script type="text/javascript">
            function submitForm(id) {
                var maxCategoryId = 0;
                $(".select-result").find("dd").each(function () {
                    if (maxCategoryId < $(this).attr("category")) {
                        maxCategoryId = $(this).attr("category");
                    }
                })
                $("#categoryId").val(maxCategoryId);

                $(id).ajaxSubmit(function (data) {
                    var goodsList = JSON.parse(data);
                    $("#searchGoodsBox").empty();
                    $.each(goodsList, function (i) {
                        var goodsStr = "<li>\n" +
                            "                            <div class=\"i-pic limit\">\n" +
                            "                                <a href='/item/"+goodsList[i].spuId +"'><img src=\" " + goodsList[i].imgUrl + "\" href='/item/" + goodsList[i].spuId +"'/></a>\n" +
                            "                                <p class=\"title fl\">"+goodsList[i].spuName+"</p>\n" +
                            "                                <p class=\"price fl\">\n" +
                            "                                    <b>¥</b>\n" +
                            "                                    <strong>"+goodsList[i].price+"</strong>\n" +
                            "                                </p>\n" +
                            "                            </div>\n" +
                            "                        </li>";
                        $("#searchGoodsBox").append(goodsStr);
                    });
                })
            }
        </script>
    </div>
</div>

<div class="clear"></div>
<b class="line"></b>
<div class="search">
    <div class="search-list">
        <div class="nav-table">
            <div class="long-title"><span class="all-goods">全部分类</span></div>
            <div class="nav-cont">
                <ul>
                    <li class="index"><a href="/index">首页</a></li>
                </ul>
            </div>
        </div>


        <div class="am-g am-g-fixed">
            <div class="am-u-sm-12 am-u-md-12">
                <div class="theme-popover">
                    <ul class="select">
                        <p class="title font-normal">
                            <%--<span class="fl">松子</span>--%>
                            <%--<span class="total fl">搜索到<strong id="keyNum" class="num">0</strong>件相关商品</span>--%>
                        </p>
                        <%--<div class="clear"></div>--%>
                        <li class="select-result">
                            <dl>
                                <dt>已选</dt>
                                <dd class="select-no"></dd>
                                <p class="eliminateCriteria">清除</p>
                            </dl>
                        </li>
                        <div class="clear"></div>
                        <li class="select-list">
                            <dl id="select1">
                                <dt class="am-badge am-round">一级分类</dt>

                                <div class="dd-conent">
                                    <dd class="select-all selected"><a href="javascript:;">全部</a></dd>
                                    <c:forEach var="category" items="${categoryList}">
                                        <dd category="${category.id}"><a href="javascript:;" category="${category.id}">${category.name}</a></dd>
                                    </c:forEach>
                                </div>
                            </dl>
                        </li>
                        <li class="select-list">
                            <dl id="select2">
                                <%--<dt class="am-badge am-round">二级分类</dt>--%>
                                <%--<div class="dd-conent">--%>
                                    <%--<dd class="select-all selected"><a href="#">全部</a></dd>--%>
                                    <%--<dd><a href="#">东北松子</a></dd>--%>
                                    <%--<dd><a href="#">巴西松子</a></dd>--%>
                                    <%--<dd><a href="#">夏威夷果</a></dd>--%>
                                    <%--<dd><a href="#">松子</a></dd>--%>
                                <%--</div>--%>
                            </dl>
                        </li>
                        <li class="select-list">
                            <dl id="select3">
                                <%--<dt class="am-badge am-round">选购热点</dt>--%>
                                <%--<div class="dd-conent">--%>
                                    <%--<dd class="select-all selected"><a href="#">全部</a></dd>--%>
                                    <%--<dd><a href="#">手剥松子</a></dd>--%>
                                    <%--<dd><a href="#">薄壳松子</a></dd>--%>
                                    <%--<dd><a href="#">进口零食</a></dd>--%>
                                    <%--<dd><a href="#">有机零食</a></dd>--%>
                                <%--</div>--%>
                            </dl>
                        </li>

                    </ul>
                    <div class="clear"></div>
                </div>
                <div class="search-content">
                    <%--<div class="sort">--%>
                        <%--<li class="first"><a title="综合">综合排序</a></li>--%>
                        <%--<li><a title="销量">销量排序</a></li>--%>
                        <%--<li><a title="价格">价格优先</a></li>--%>
                        <%--<li class="big"><a title="评价" href="#">评价为主</a></li>--%>
                    <%--</div>--%>
                    <%--<div class="clear"></div>--%>

                    <ul id="searchGoodsBox" class="am-avg-sm-2 am-avg-md-3 am-avg-lg-4 boxes">
                        <%----%>
                    </ul>
                </div>
                <div class="clear"></div>
                <!--分页 -->
                <%--<ul class="am-pagination am-pagination-right">--%>
                    <%--<li class="am-disabled"><a href="#">&laquo;</a></li>--%>
                    <%--<li class="am-active"><a href="#">1</a></li>--%>
                    <%--<li><a href="#">2</a></li>--%>
                    <%--<li><a href="#">3</a></li>--%>
                    <%--<li><a href="#">4</a></li>--%>
                    <%--<li><a href="#">5</a></li>--%>
                    <%--<li><a href="#">&raquo;</a></li>--%>
                <%--</ul>--%>

            </div>
        </div>
    </div>

</div>

<!--引导 -->
<div class="navCir">
    <li><a href="/index"><i class="am-icon-home "></i>首页</a></li>
    <%--<li><a href="sort.html"><i class="am-icon-list"></i>分类</a></li>--%>
    <li><a href="/cart"><i class="am-icon-shopping-basket"></i>购物车</a></li>
    <li><a href="/person"><i class="am-icon-user"></i>我的</a></li>
</div>

<!--菜单 -->
<div class=tip>
    <div id="sidebar">
        <div id="wrap">
            <div id="prof" class="item ">
                <a href="# ">
                    <span class="setting "></span>
                </a>
                <div class="ibar_login_box status_login ">
                    <div class="avatar_box ">
                        <c:if test="${user==null}">
                            <p class="avatar_imgbox "><img src="../img/baseimages/no-img_mid_.jpg "/></p>
                            <ul class="user_info ">
                                <li>还没有登录呢～</li>
                            </ul>
                        </c:if>
                        <c:if test="${user!=null}">
                            <p class="avatar_imgbox "><img src="${user.avatarUrl}"/></p>
                            <ul class="user_info ">
                                <li>${user.aliasName}</li>
                            </ul>
                        </c:if>
                    </div>
                    <div class="login_btnbox ">
                        <a href="# " class="login_order ">我的订单</a>
                        <a href="# " class="login_favorite ">我的收藏</a>
                    </div>
                    <i class="icon_arrow_white "></i>
                </div>

            </div>
            <div id="shopCart" class="item">
                <a href="#">
                    <span class="message"></span>
                </a>
                <p>
                    购物车
                </p>
                <p class="cart_num">0</p>
            </div>
            <div id="asset" class="item">
                <a href="#">
                    <span class="view"></span>
                </a>
                <div class="mp_tooltip">
                    我的资产
                    <i class="icon_arrow_right_black"></i>
                </div>
            </div>

            <div id="foot" class="item">
                <a href="#">
                    <span class="zuji"></span>
                </a>
                <div class="mp_tooltip">
                    我的足迹
                    <i class="icon_arrow_right_black"></i>
                </div>
            </div>

            <div id="brand" class="item">
                <a href="#">
                    <span class="wdsc"><img src="../img/baseimages/wdsc.png"/></span>
                </a>
                <div class="mp_tooltip">
                    我的收藏
                    <i class="icon_arrow_right_black"></i>
                </div>
            </div>

            <div id="broadcast" class="item">
                <a href="#">
                    <span class="chongzhi"><img src="../img/baseimages/chongzhi.png"/></span>
                </a>
                <div class="mp_tooltip">
                    我要充值
                    <i class="icon_arrow_right_black"></i>
                </div>
            </div>

            <div class="quick_toggle">
                <li class="qtitem">
                    <a href="#"><span class="kfzx"></span></a>
                    <div class="mp_tooltip">客服中心<i class="icon_arrow_right_black"></i></div>
                </li>
                <!--二维码 -->
                <li class="qtitem">
                    <a href="#none"><span class="mpbtn_qrcode"></span></a>
                    <div class="mp_qrcode" style="display:none;"><img src="../img/baseimages/weixin_code_145.png"/><i
                            class="icon_arrow_white"></i></div>
                </li>
                <li class="qtitem">
                    <a href="#top" class="return_top"><span class="top"></span></a>
                </li>
            </div>

            <!--回到顶部 -->
            <div id="quick_links_pop" class="quick_links_pop hide"></div>

        </div>

    </div>
    <div id="prof-content" class="nav-content">
        <div class="nav-con-close">
            <i class="am-icon-angle-right am-icon-fw"></i>
        </div>
        <div>
            我
        </div>
    </div>
    <div id="shopCart-content" class="nav-content">
        <div class="nav-con-close">
            <i class="am-icon-angle-right am-icon-fw"></i>
        </div>
        <div>
            购物车
        </div>
    </div>
    <div id="asset-content" class="nav-content">
        <div class="nav-con-close">
            <i class="am-icon-angle-right am-icon-fw"></i>
        </div>
        <div>
            资产
        </div>

        <div class="ia-head-list">
            <a href="#" target="_blank" class="pl">
                <div class="num">0</div>
                <div class="text">优惠券</div>
            </a>
            <a href="#" target="_blank" class="pl">
                <div class="num">0</div>
                <div class="text">红包</div>
            </a>
            <a href="#" target="_blank" class="pl money">
                <div class="num">￥0</div>
                <div class="text">余额</div>
            </a>
        </div>

    </div>
    <div id="foot-content" class="nav-content">
        <div class="nav-con-close">
            <i class="am-icon-angle-right am-icon-fw"></i>
        </div>
        <div>
            足迹
        </div>
    </div>
    <div id="brand-content" class="nav-content">
        <div class="nav-con-close">
            <i class="am-icon-angle-right am-icon-fw"></i>
        </div>
        <div>
            收藏
        </div>
    </div>
    <div id="broadcast-content" class="nav-content">
        <div class="nav-con-close">
            <i class="am-icon-angle-right am-icon-fw"></i>
        </div>
        <div>
            充值
        </div>
    </div>
</div>
<!--<script>-->
<!--window.jQuery || document.write('<script src="basic/js/jquery-1.9.min.js"><\/script>');-->
<!--</script>-->
<script type="text/javascript" src="../js/basic/quick_links.js"></script>

<div class="theme-popover-mask"></div>

</body>

</html>