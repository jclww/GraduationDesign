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

    <title>${spu.name}</title>

    <link href="../AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css"/>
    <link href="../AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css"/>
    <link href="../css/basic/demo.css" rel="stylesheet" type="text/css"/>
    <link type="text/css" href="../css/basecss/optstyle.css" rel="stylesheet"/>
    <link type="text/css" href="../css/basecss/style.css" rel="stylesheet"/>
    <script src="../AmazeUI-2.4.2/assets/js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/basic/quick_links.js"></script>
    <script type="text/javascript" src="../AmazeUI-2.4.2/assets/js/amazeui.js"></script>
    <script type="text/javascript" src="../js/basejs/jquery.imagezoom.min.js"></script>
    <script type="text/javascript" src="../js/basejs/jquery.flexslider.js"></script>
    <script type="text/javascript" src="../js/basejs/list.js"></script>
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
            <div class="menu-hd MyShangcheng"><a href="#" target="_top"><i class="am-icon-user am-icon-fw"></i>个人中心</a>
            </div>
        </div>
        <div class="topMessage mini-cart">
            <div class="menu-hd"><a id="mc-menu-hd" href="#" target="_top"><i
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
        <form>
            <input id="searchInput" name="index_none_header_sysc" type="text" placeholder="搜索" autocomplete="off">
            <input id="ai-topsearch" class="submit am-btn" value="搜索" index="1" type="submit">
        </form>
    </div>
</div>

<div class="clear"></div>
<b class="line"></b>
<div class="listMain">

    <!--分类-->
    <div class="nav-table">
        <div class="long-title"><span class="all-goods">全部分类</span></div>
        <div class="nav-cont">
            <ul>
                <li class="index"><a href="/index">首页</a></li>
            </ul>
        </div>
    </div>
    <ol class="am-breadcrumb am-breadcrumb-slash">
        <li><a href="/index">首页</a></li>
        <li><a href="#">分类</a></li>
        <li class="am-active">内容</li>
    </ol>
    <script type="text/javascript">
        $(window).load(function () {
            $('.flexslider').flexslider({
                animation: "slide",
                start: function (slider) {
                    $('body').removeClass('loading');
                }
            });
        });
    </script>
    <div class="item-inform">
        <div class="clearfixLeft" id="clearcontent">
            <div class="box">
                <script type="text/javascript">
                    $(document).ready(function () {
                        $(".jqzoom").imagezoom();
                        $("#thumblist li a").click(function () {
                            $(this).parents("li").addClass("tb-selected").siblings().removeClass("tb-selected");
                            $(".jqzoom").attr('src', $(this).find("img").attr("mid"));
                            $(".jqzoom").attr('rel', $(this).find("img").attr("big"));
                        });
                    });
                </script>
                <div class="tb-booth tb-pic tb-s310">
                    <a href="${imgs[0].bigImgUrl}"><img src="${imgs[0].midImgUrl}" alt="细节展示放大镜特效"
                                                        rel="${imgs[0].bigImgUrl}" class="jqzoom"/></a>
                </div>
                <ul class="tb-thumb" id="thumblist">
                    <c:forEach var="img" items="${imgs}">
                        <li>
                            <div class="tb-pic tb-s40">
                                <a href="javascript:;"><img src="${img.smallImgUrl}" mid="${img.midImgUrl}"
                                                            big="${img.bigImgUrl}"></a>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="clear"></div>
        </div>

        <div class="clearfixRight">
            <!--规格属性-->
            <!--名称-->
            <div class="tb-detail-hd">
                <h1>${spu.name}</h1>
            </div>
            <div class="tb-detail-list">
                <!--价格-->
                <div class="tb-detail-price">
                    <li class="price iteminfo_price">
                        <dt>价格</dt>
                        <dd><em>¥</em><b id="price" class="sys_item_price">
                            <c:if test="${spu.priceBottom==spu.priceTop}">${spu.priceBottom}
                            </c:if>
                            <c:if test="${spu.priceBottom!=spu.priceTop}">${spu.priceBottom} ~ ${spu.priceTop}
                            </c:if>
                            <script type="application/javascript">
                                <c:if test="${spu.priceBottom==spu.priceTop}">
                                    var defaultPrice = "${spu.priceBottom}";
                                </c:if>
                                <c:if test="${spu.priceBottom!=spu.priceTop}">
                                    var defaultPrice = "${spu.priceBottom} ~ ${spu.priceTop}";
                                </c:if>
                            </script>
                        </b></dd>
                    </li>
                    <div class="clear"></div>
                </div>
                <div class="clear"></div>

                <!--各种规格-->
                <dl class="iteminfo_parameter sys_item_specpara">
                    <dt class="theme-login">
                        <div class="cart-title">可选规格<span class="am-icon-angle-right"></span></div>
                    </dt>
                    <dd>
                        <!--操作页面-->

                        <div class="theme-popover-mask"></div>

                        <div class="theme-popover">
                            <div class="theme-span"></div>
                            <div class="theme-poptit">
                                <a href="javascript:;" title="关闭" class="close">×</a>
                            </div>
                            <div class="theme-popbod dform">
                                <form id="goodsForm" class="theme-signin" name="" action="" method="post">

                                    <div class="theme-signin-left">
                                        <c:forEach var="attribute" items="${attributes}">
                                        <div class="theme-options">
                                            <div class="cart-title"
                                                 attributeId="${attribute.attributeId}">${attribute.attributeName}</div>
                                            <ul>
                                                <c:forEach var="option" items="${attribute.optionList}">
                                                    <li class="sku-line" optionId="${option.id}">${option.name}<i></i>
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                        </c:forEach>
                                        <input id="sku" name="skuId" type="hidden">
                                        <div class="theme-options">
                                            <div class="cart-title number">数量</div>
                                        </div>
                                        <input id="min" class="am-btn am-btn-default" name="" type="button" value="-"/>
                                        <input id="text_box" name="count" type="text" value="1" style="width:30px;"/>
                                        <input id="add" class="am-btn am-btn-default" name="" type="button" value="+"/>
                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                        <span id="stock" class="tb-hidden">库存<span id="stockNum" class="stock"> </span></span>
                                    </div>
                                </form>
                            </div>
                        </div>
                    <dd>

                    </dd>
                </dl>
            </div>
            <div class="clear"></div>
            <%--<div class="btn-op">--%>
            <%--<div class="btn am-btn am-btn-warning">确认</div>--%>
            <%--<div class="btn close am-btn am-btn-warning">取消</div>--%>
            <%--</div>--%>
            <script type="application/javascript">
                var skuInfo = ${skuInfos};
                var skuInfoStr = JSON.stringify(skuInfo);
                function buyNow() {
                    var id = "";
                    $("#goodsForm .selected").each(function () {
                        id += $(this).attr("optionId")+";";
                    })
                    var stockCount = $("#stockNum").text();
                    var buyNum = $("#text_box").val();
                    if(id == "" || stockCount == " ") {
                        alert("请选择规格");
                        return;
                    }
                    if(parseInt(stockCount) < parseInt(buyNum)) {
                        alert("少买点～～～");
                        return;
                    }
                    id = id.substring(0, id.length-1);
                    $(skuInfo).each(function (index, value) {
                        if(id == value.id) {
                            $("#goodsForm").attr("action","/buy");
                            $("#sku").val(value.skuId);
                            $("#goodsForm").submit();
                            return false;
                        }
                        if (index == (skuInfo.length-1)) {
                            alert("这类可能没货了～～")
                        }
                    })
                }
                function addCart() {
                    var id = "";
                    $("#goodsForm .selected").each(function () {
                        id += $(this).attr("optionId")+";";
                    })
                    var stockCount = $("#stockNum").text();
                    var buyNum = $("#text_box").val();
                    if(id == "" || stockCount == " ") {
                        alert("请选择规格");
                        return;
                    }
                    if(parseInt(stockCount) < parseInt(buyNum)) {
                        alert("少添点～～～");
                        return;
                    }
                    id = id.substring(0, id.length-1);
                    $(skuInfo).each(function (index, value) {
                        if(id == value.id) {
                            $("#goodsForm").attr("action","/cart");
                            $("#sku").val(value.skuId);
                            $("#goodsForm").submit();
                            return false;
                        }
                        if (index == (skuInfo.length-1)) {
                            alert("这类可能没货了～～")
                        }
                    })
                }
            </script>
            <div class="pay">
                <li>
                    <div class="clearfix tb-btn tb-btn-buy theme-login">
                        <a id="LikBuy" title="点此按钮到下一步确认购买信息" onclick="buyNow()" href="javascript:;">立即购买</a>
                    </div>
                </li>
                <li>
                    <div class="clearfix tb-btn tb-btn-basket theme-login">
                        <a id="LikBasket" title="加入购物车" onclick="addCart()" href="javascript:;"><i></i>加入购物车</a>
                    </div>
                </li>
            </div>
        </div>

        </form>
    </div>
</div>

</dd>
</dl>
<div class="clear"></div>
</div>

<%--<div class="pay">--%>
    <%--<li>--%>
        <%--<div class="clearfix tb-btn tb-btn-buy theme-login">--%>
            <%--<a id="LikBuy" title="点此按钮到下一步确认购买信息" href="#">立即购买</a>--%>
        <%--</div>--%>
    <%--</li>--%>
    <%--<li>--%>
        <%--<div class="clearfix tb-btn tb-btn-basket theme-login">--%>
            <%--<a id="LikBasket" title="加入购物车" href="#"><i></i>加入购物车</a>--%>
        <%--</div>--%>
    <%--</li>--%>
<%--</div>--%>

</div>

<div class="clear"></div>

</div>

<!-- introduce-->

<div class="introduce">
    <div class="browse">
        <div class="mc">
            <ul>
                <div class="mt">
                    <h2>看了又看</h2>
                </div>

                <li class="first">
                    <div class="p-img">
                        <a href="#"> <img class="" src="../img/baseimages/browse1.jpg"> </a>
                    </div>
                    <div class="p-name"><a href="#">
                        【三只松鼠_开口松子】零食坚果特产炒货东北红松子原味
                    </a>
                    </div>
                    <div class="p-price"><strong>￥35.90</strong></div>
                </li>
                <li>
                    <div class="p-img">
                        <a href="#"> <img class="" src="../img/baseimages/browse1.jpg"> </a>
                    </div>
                    <div class="p-name"><a href="#">
                        【三只松鼠_开口松子】零食坚果特产炒货东北红松子原味
                    </a>
                    </div>
                    <div class="p-price"><strong>￥35.90</strong></div>
                </li>
                <li>
                    <div class="p-img">
                        <a href="#"> <img class="" src="../img/baseimages/browse1.jpg"> </a>
                    </div>
                    <div class="p-name"><a href="#">
                        【三只松鼠_开口松子】零食坚果特产炒货东北红松子原味
                    </a>
                    </div>
                    <div class="p-price"><strong>￥35.90</strong></div>
                </li>
                <li>
                    <div class="p-img">
                        <a href="#"> <img class="" src="../img/baseimages/browse1.jpg"> </a>
                    </div>
                    <div class="p-name"><a href="#">
                        【三只松鼠_开口松子】零食坚果特产炒货东北红松子原味
                    </a>
                    </div>
                    <div class="p-price"><strong>￥35.90</strong></div>
                </li>
                <li>
                    <div class="p-img">
                        <a href="#"> <img class="" src="../img/baseimages/browse1.jpg"> </a>
                    </div>
                    <div class="p-name"><a href="#">
                        【三只松鼠_开口松子218g】零食坚果特产炒货东北红松子原味
                    </a>
                    </div>
                    <div class="p-price"><strong>￥35.90</strong></div>
                </li>

            </ul>
        </div>
    </div>
    <div class="introduceMain">
        <div class="am-tabs" data-am-tabs>
            <ul class="am-avg-sm-3 am-tabs-nav am-nav am-nav-tabs">
                <li class="am-active">
                    <a href="#">

                        <span class="index-needs-dt-txt">宝贝详情</span></a>

                </li>

                <li>
                    <a href="#">

                        <span class="index-needs-dt-txt">全部评价</span></a>

                </li>
            </ul>

            <div class="am-tabs-bd">

                <div class="am-tab-panel am-fade am-in am-active">
                    <div class="J_Brand">

                        <div class="attr-list-hd tm-clear">
                            <h4>产品参数：</h4></div>
                        <div class="clear"></div>
                        <ul id="J_AttrUL">
                            <script type="application/javascript">
                                var details = "${spu.details}";
                                var detailsArr = details.split(";");
                                $.each(detailsArr, function (index, text) {
                                    $("#J_AttrUL").append("<li >" + text + "</li>")
                                })
                            </script>
                        </ul>
                        <div class="clear"></div>
                    </div>

                    <div class="details">
                        <div class="attr-list-hd after-market-hd">
                            <h4>商品细节</h4>
                        </div>
                        <div class="twlistNews">
                            <c:forEach var="detail" items="${details}">
                                <img src="${detail.imgUrl}"/>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="clear"></div>

                </div>

                <div class="am-tab-panel am-fade">

                    <div class="actor-new">
                        <div class="rate">
                            <strong>100<span>%</span></strong><br> <span>好评度</span>
                        </div>
                        <dl>
                            <dt>买家印象</dt>
                            <dd class="p-bfc">
                                <q class="comm-tags"><span>味道不错</span><em>(2177)</em></q>
                                <q class="comm-tags"><span>颗粒饱满</span><em>(1860)</em></q>
                                <q class="comm-tags"><span>口感好</span><em>(1823)</em></q>
                                <q class="comm-tags"><span>商品不错</span><em>(1689)</em></q>
                                <q class="comm-tags"><span>香脆可口</span><em>(1488)</em></q>
                                <q class="comm-tags"><span>个个开口</span><em>(1392)</em></q>
                                <q class="comm-tags"><span>价格便宜</span><em>(1119)</em></q>
                                <q class="comm-tags"><span>特价买的</span><em>(865)</em></q>
                                <q class="comm-tags"><span>皮很薄</span><em>(831)</em></q>
                            </dd>
                        </dl>
                    </div>
                    <div class="clear"></div>
                    <div class="tb-r-filter-bar">
                        <ul class=" tb-taglist am-avg-sm-4">
                            <li class="tb-taglist-li tb-taglist-li-current">
                                <div class="comment-info">
                                    <span>全部评价</span>
                                    <span class="tb-tbcr-num">(32)</span>
                                </div>
                            </li>

                            <li class="tb-taglist-li tb-taglist-li-1">
                                <div class="comment-info">
                                    <span>好评</span>
                                    <span class="tb-tbcr-num">(32)</span>
                                </div>
                            </li>

                            <li class="tb-taglist-li tb-taglist-li-0">
                                <div class="comment-info">
                                    <span>中评</span>
                                    <span class="tb-tbcr-num">(32)</span>
                                </div>
                            </li>

                            <li class="tb-taglist-li tb-taglist-li--1">
                                <div class="comment-info">
                                    <span>差评</span>
                                    <span class="tb-tbcr-num">(32)</span>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <div class="clear"></div>

                    <ul class="am-comments-list am-comments-list-flip">
                        <li class="am-comment">
                            <!-- 评论容器 -->
                            <a href="">
                                <img class="am-comment-avatar" src="../img/baseimages/hwbn40x40.jpg"/>
                                <!-- 评论者头像 -->
                            </a>

                            <div class="am-comment-main">
                                <!-- 评论内容容器 -->
                                <header class="am-comment-hd">
                                    <!--<h3 class="am-comment-title">评论标题</h3>-->
                                    <div class="am-comment-meta">
                                        <!-- 评论元数据 -->
                                        <a href="#link-to-user" class="am-comment-author">b***1 (匿名)</a>
                                        <!-- 评论者 -->
                                        评论于
                                        <time datetime="">2015年11月02日 17:46</time>
                                    </div>
                                </header>

                                <div class="am-comment-bd">
                                    <div class="tb-rev-item " data-id="255776406962">
                                        <div class="J_TbcRate_ReviewContent tb-tbcr-content ">
                                            摸起来丝滑柔软，不厚，没色差，颜色好看！买这个衣服还接到诈骗电话，我很好奇他们是怎么知道我买了这件衣服，并且还知道我的电话的！
                                        </div>
                                        <div class="tb-r-act-bar">
                                            颜色分类：柠檬黄&nbsp;&nbsp;尺码：S
                                        </div>
                                    </div>

                                </div>
                                <!-- 评论内容 -->
                            </div>
                        </li>
                        <li class="am-comment">
                            <!-- 评论容器 -->
                            <a href="">
                                <img class="am-comment-avatar" src="../img/baseimages/hwbn40x40.jpg"/>
                                <!-- 评论者头像 -->
                            </a>

                            <div class="am-comment-main">
                                <!-- 评论内容容器 -->
                                <header class="am-comment-hd">
                                    <!--<h3 class="am-comment-title">评论标题</h3>-->
                                    <div class="am-comment-meta">
                                        <!-- 评论元数据 -->
                                        <a href="#link-to-user" class="am-comment-author">l***4 (匿名)</a>
                                        <!-- 评论者 -->
                                        评论于
                                        <time datetime="">2015年10月28日 11:33</time>
                                    </div>
                                </header>

                                <div class="am-comment-bd">
                                    <div class="tb-rev-item " data-id="255095758792">
                                        <div class="J_TbcRate_ReviewContent tb-tbcr-content ">
                                            没有色差，很暖和……美美的
                                        </div>
                                        <div class="tb-r-act-bar">
                                            颜色分类：蓝调灰&nbsp;&nbsp;尺码：2XL
                                        </div>
                                    </div>

                                </div>
                                <!-- 评论内容 -->
                            </div>
                        </li>
                        <li class="am-comment">
                            <!-- 评论容器 -->
                            <a href="">
                                <img class="am-comment-avatar" src="../img/baseimages/hwbn40x40.jpg"/>
                                <!-- 评论者头像 -->
                            </a>

                            <div class="am-comment-main">
                                <!-- 评论内容容器 -->
                                <header class="am-comment-hd">
                                    <!--<h3 class="am-comment-title">评论标题</h3>-->
                                    <div class="am-comment-meta">
                                        <!-- 评论元数据 -->
                                        <a href="#link-to-user" class="am-comment-author">b***1 (匿名)</a>
                                        <!-- 评论者 -->
                                        评论于
                                        <time datetime="">2015年11月02日 17:46</time>
                                    </div>
                                </header>

                                <div class="am-comment-bd">
                                    <div class="tb-rev-item " data-id="255776406962">
                                        <div class="J_TbcRate_ReviewContent tb-tbcr-content ">
                                            摸起来丝滑柔软，不厚，没色差，颜色好看！买这个衣服还接到诈骗电话，我很好奇他们是怎么知道我买了这件衣服，并且还知道我的电话的！
                                        </div>
                                        <div class="tb-r-act-bar">
                                            颜色分类：柠檬黄&nbsp;&nbsp;尺码：S
                                        </div>
                                    </div>

                                </div>
                                <!-- 评论内容 -->
                            </div>
                        </li>
                        <li class="am-comment">
                            <!-- 评论容器 -->
                            <a href="">
                                <img class="am-comment-avatar" src="../img/baseimages/hwbn40x40.jpg"/>
                                <!-- 评论者头像 -->
                            </a>

                            <div class="am-comment-main">
                                <!-- 评论内容容器 -->
                                <header class="am-comment-hd">
                                    <!--<h3 class="am-comment-title">评论标题</h3>-->
                                    <div class="am-comment-meta">
                                        <!-- 评论元数据 -->
                                        <a href="#link-to-user" class="am-comment-author">h***n (匿名)</a>
                                        <!-- 评论者 -->
                                        评论于
                                        <time datetime="">2015年11月25日 12:48</time>
                                    </div>
                                </header>

                                <div class="am-comment-bd">
                                    <div class="tb-rev-item " data-id="258040417670">
                                        <div class="J_TbcRate_ReviewContent tb-tbcr-content ">
                                            式样不错，初冬穿
                                        </div>
                                        <div class="tb-r-act-bar">
                                            颜色分类：柠檬黄&nbsp;&nbsp;尺码：L
                                        </div>
                                    </div>
                                </div>
                                <!-- 评论内容 -->
                            </div>
                        </li>

                        <li class="am-comment">
                            <!-- 评论容器 -->
                            <a href="">
                                <img class="am-comment-avatar" src="../img/baseimages/hwbn40x40.jpg"/>
                                <!-- 评论者头像 -->
                            </a>

                            <div class="am-comment-main">
                                <!-- 评论内容容器 -->
                                <header class="am-comment-hd">
                                    <!--<h3 class="am-comment-title">评论标题</h3>-->
                                    <div class="am-comment-meta">
                                        <!-- 评论元数据 -->
                                        <a href="#link-to-user" class="am-comment-author">b***1 (匿名)</a>
                                        <!-- 评论者 -->
                                        评论于
                                        <time datetime="">2015年11月02日 17:46</time>
                                    </div>
                                </header>

                                <div class="am-comment-bd">
                                    <div class="tb-rev-item " data-id="255776406962">
                                        <div class="J_TbcRate_ReviewContent tb-tbcr-content ">
                                            摸起来丝滑柔软，不厚，没色差，颜色好看！买这个衣服还接到诈骗电话，我很好奇他们是怎么知道我买了这件衣服，并且还知道我的电话的！
                                        </div>
                                        <div class="tb-r-act-bar">
                                            颜色分类：柠檬黄&nbsp;&nbsp;尺码：S
                                        </div>
                                    </div>

                                </div>
                                <!-- 评论内容 -->
                            </div>
                        </li>
                        <li class="am-comment">
                            <!-- 评论容器 -->
                            <a href="">
                                <img class="am-comment-avatar" src="../img/baseimages/hwbn40x40.jpg"/>
                                <!-- 评论者头像 -->
                            </a>

                            <div class="am-comment-main">
                                <!-- 评论内容容器 -->
                                <header class="am-comment-hd">
                                    <!--<h3 class="am-comment-title">评论标题</h3>-->
                                    <div class="am-comment-meta">
                                        <!-- 评论元数据 -->
                                        <a href="#link-to-user" class="am-comment-author">l***4 (匿名)</a>
                                        <!-- 评论者 -->
                                        评论于
                                        <time datetime="">2015年10月28日 11:33</time>
                                    </div>
                                </header>

                                <div class="am-comment-bd">
                                    <div class="tb-rev-item " data-id="255095758792">
                                        <div class="J_TbcRate_ReviewContent tb-tbcr-content ">
                                            没有色差，很暖和……美美的
                                        </div>
                                        <div class="tb-r-act-bar">
                                            颜色分类：蓝调灰&nbsp;&nbsp;尺码：2XL
                                        </div>
                                    </div>

                                </div>
                                <!-- 评论内容 -->
                            </div>
                        </li>
                        <li class="am-comment">
                            <!-- 评论容器 -->
                            <a href="">
                                <img class="am-comment-avatar" src="../img/baseimages/hwbn40x40.jpg"/>
                                <!-- 评论者头像 -->
                            </a>

                            <div class="am-comment-main">
                                <!-- 评论内容容器 -->
                                <header class="am-comment-hd">
                                    <!--<h3 class="am-comment-title">评论标题</h3>-->
                                    <div class="am-comment-meta">
                                        <!-- 评论元数据 -->
                                        <a href="#link-to-user" class="am-comment-author">b***1 (匿名)</a>
                                        <!-- 评论者 -->
                                        评论于
                                        <time datetime="">2015年11月02日 17:46</time>
                                    </div>
                                </header>

                                <div class="am-comment-bd">
                                    <div class="tb-rev-item " data-id="255776406962">
                                        <div class="J_TbcRate_ReviewContent tb-tbcr-content ">
                                            摸起来丝滑柔软，不厚，没色差，颜色好看！买这个衣服还接到诈骗电话，我很好奇他们是怎么知道我买了这件衣服，并且还知道我的电话的！
                                        </div>
                                        <div class="tb-r-act-bar">
                                            颜色分类：柠檬黄&nbsp;&nbsp;尺码：S
                                        </div>
                                    </div>

                                </div>
                                <!-- 评论内容 -->
                            </div>
                        </li>
                        <li class="am-comment">
                            <!-- 评论容器 -->
                            <a href="">
                                <img class="am-comment-avatar" src="../img/baseimages/hwbn40x40.jpg"/>
                                <!-- 评论者头像 -->
                            </a>

                            <div class="am-comment-main">
                                <!-- 评论内容容器 -->
                                <header class="am-comment-hd">
                                    <!--<h3 class="am-comment-title">评论标题</h3>-->
                                    <div class="am-comment-meta">
                                        <!-- 评论元数据 -->
                                        <a href="#link-to-user" class="am-comment-author">h***n (匿名)</a>
                                        <!-- 评论者 -->
                                        评论于
                                        <time datetime="">2015年11月25日 12:48</time>
                                    </div>
                                </header>

                                <div class="am-comment-bd">
                                    <div class="tb-rev-item " data-id="258040417670">
                                        <div class="J_TbcRate_ReviewContent tb-tbcr-content ">
                                            式样不错，初冬穿
                                        </div>
                                        <div class="tb-r-act-bar">
                                            颜色分类：柠檬黄&nbsp;&nbsp;尺码：L
                                        </div>
                                    </div>
                                </div>
                                <!-- 评论内容 -->
                            </div>
                        </li>
                        <li class="am-comment">
                            <!-- 评论容器 -->
                            <a href="">
                                <img class="am-comment-avatar" src="../img/baseimages/hwbn40x40.jpg"/>
                                <!-- 评论者头像 -->
                            </a>

                            <div class="am-comment-main">
                                <!-- 评论内容容器 -->
                                <header class="am-comment-hd">
                                    <!--<h3 class="am-comment-title">评论标题</h3>-->
                                    <div class="am-comment-meta">
                                        <!-- 评论元数据 -->
                                        <a href="#link-to-user" class="am-comment-author">b***1 (匿名)</a>
                                        <!-- 评论者 -->
                                        评论于
                                        <time datetime="">2015年11月02日 17:46</time>
                                    </div>
                                </header>

                                <div class="am-comment-bd">
                                    <div class="tb-rev-item " data-id="255776406962">
                                        <div class="J_TbcRate_ReviewContent tb-tbcr-content ">
                                            摸起来丝滑柔软，不厚，没色差，颜色好看！买这个衣服还接到诈骗电话，我很好奇他们是怎么知道我买了这件衣服，并且还知道我的电话的！
                                        </div>
                                        <div class="tb-r-act-bar">
                                            颜色分类：柠檬黄&nbsp;&nbsp;尺码：S
                                        </div>
                                    </div>

                                </div>
                                <!-- 评论内容 -->
                            </div>
                        </li>
                        <li class="am-comment">
                            <!-- 评论容器 -->
                            <a href="">
                                <img class="am-comment-avatar" src="../img/baseimages/hwbn40x40.jpg"/>
                                <!-- 评论者头像 -->
                            </a>

                            <div class="am-comment-main">
                                <!-- 评论内容容器 -->
                                <header class="am-comment-hd">
                                    <!--<h3 class="am-comment-title">评论标题</h3>-->
                                    <div class="am-comment-meta">
                                        <!-- 评论元数据 -->
                                        <a href="#link-to-user" class="am-comment-author">l***4 (匿名)</a>
                                        <!-- 评论者 -->
                                        评论于
                                        <time datetime="">2015年10月28日 11:33</time>
                                    </div>
                                </header>

                                <div class="am-comment-bd">
                                    <div class="tb-rev-item " data-id="255095758792">
                                        <div class="J_TbcRate_ReviewContent tb-tbcr-content ">
                                            没有色差，很暖和……美美的
                                        </div>
                                        <div class="tb-r-act-bar">
                                            颜色分类：蓝调灰&nbsp;&nbsp;尺码：2XL
                                        </div>
                                    </div>

                                </div>
                                <!-- 评论内容 -->
                            </div>
                        </li>
                        <li class="am-comment">
                            <!-- 评论容器 -->
                            <a href="">
                                <img class="am-comment-avatar" src="../img/baseimages/hwbn40x40.jpg"/>
                                <!-- 评论者头像 -->
                            </a>

                            <div class="am-comment-main">
                                <!-- 评论内容容器 -->
                                <header class="am-comment-hd">
                                    <!--<h3 class="am-comment-title">评论标题</h3>-->
                                    <div class="am-comment-meta">
                                        <!-- 评论元数据 -->
                                        <a href="#link-to-user" class="am-comment-author">b***1 (匿名)</a>
                                        <!-- 评论者 -->
                                        评论于
                                        <time datetime="">2015年11月02日 17:46</time>
                                    </div>
                                </header>

                                <div class="am-comment-bd">
                                    <div class="tb-rev-item " data-id="255776406962">
                                        <div class="J_TbcRate_ReviewContent tb-tbcr-content ">
                                            摸起来丝滑柔软，不厚，没色差，颜色好看！买这个衣服还接到诈骗电话，我很好奇他们是怎么知道我买了这件衣服，并且还知道我的电话的！
                                        </div>
                                        <div class="tb-r-act-bar">
                                            颜色分类：柠檬黄&nbsp;&nbsp;尺码：S
                                        </div>
                                    </div>

                                </div>
                                <!-- 评论内容 -->
                            </div>
                        </li>
                        <li class="am-comment">
                            <!-- 评论容器 -->
                            <a href="">
                                <img class="am-comment-avatar" src="../img/baseimages/hwbn40x40.jpg"/>
                                <!-- 评论者头像 -->
                            </a>

                            <div class="am-comment-main">
                                <!-- 评论内容容器 -->
                                <header class="am-comment-hd">
                                    <!--<h3 class="am-comment-title">评论标题</h3>-->
                                    <div class="am-comment-meta">
                                        <!-- 评论元数据 -->
                                        <a href="#link-to-user" class="am-comment-author">h***n (匿名)</a>
                                        <!-- 评论者 -->
                                        评论于
                                        <time datetime="">2015年11月25日 12:48</time>
                                    </div>
                                </header>

                                <div class="am-comment-bd">
                                    <div class="tb-rev-item " data-id="258040417670">
                                        <div class="J_TbcRate_ReviewContent tb-tbcr-content ">
                                            式样不错，初冬穿
                                        </div>
                                        <div class="tb-r-act-bar">
                                            颜色分类：柠檬黄&nbsp;&nbsp;尺码：L
                                        </div>
                                    </div>
                                </div>
                                <!-- 评论内容 -->
                            </div>
                        </li>

                    </ul>

                    <div class="clear"></div>

                    <!--分页 -->
                    <ul class="am-pagination am-pagination-right">
                        <li class="am-disabled"><a href="#">&laquo;</a></li>
                        <li class="am-active"><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">5</a></li>
                        <li><a href="#">&raquo;</a></li>
                    </ul>
                    <div class="clear"></div>

                    <div class="tb-reviewsft">
                        <div class="tb-rate-alert type-attention">购买前请查看该商品的 <a href="#" target="_blank">购物保障</a>，明确您的售后保障权益。
                        </div>
                    </div>

                </div>
            </div>

        </div>

        <div class="clear"></div>
    </div>

</div>
</div>
<!--菜单 -->
<div class=tip>
    <div id="sidebar">
        <div id="wrap">
            <div id="prof" class="item">
                <a href="#">
                    <span class="setting"></span>
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

</body>

</html>