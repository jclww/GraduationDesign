<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=0">

    <title>订单管理</title>

    <link href="../AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css">
    <link href="../AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css">

    <link href="../css/basecss/personal.css" rel="stylesheet" type="text/css">
    <link href="../css/basecss/orstyle.css" rel="stylesheet" type="text/css">

    <script src="../AmazeUI-2.4.2/assets/js/jquery.min.js"></script>
    <script src="../AmazeUI-2.4.2/assets/js/amazeui.js"></script>

</head>

<body>
<!--头 -->
<header>
    <article>
        <div class="mt-logo">
            <!--顶部导航条 -->
            <div class="am-container header">
                <ul class="message-l">
                    <div class="topMessage">
                        <div class="menu-hd">
                            欢迎您,${user.name},<a href="/person/logout">安全退出</a>
                        </div>
                    </div>
                </ul>
                <ul class="message-r">
                    <div class="topMessage home">
                        <div class="menu-hd"><a href="/index" target="_top" class="h">商城首页</a></div>
                    </div>
                    <div class="topMessage my-shangcheng">
                        <div class="menu-hd MyShangcheng"><a href="/person" target="_top"><i
                                class="am-icon-user am-icon-fw"></i>个人中心</a></div>
                    </div>
                    <div class="topMessage mini-cart">
                        <div class="menu-hd"><a id="mc-menu-hd" href="/cart" target="_top"><i
                                class="am-icon-shopping-cart  am-icon-fw"></i><span>购物车</span><strong id="J_MiniCartNum"
                                                                                                      class="h">0</strong></a>
                        </div>
                    </div>
                    <div class="topMessage favorite">
                        <div class="menu-hd"><a href="#" target="_top"><i
                                class="am-icon-heart am-icon-fw"></i><span>收藏夹</span></a></div>
                </ul>
            </div>

            <!--悬浮搜索框-->

            <div class="nav white">
                <div class="logoBig">
                    <li><img src="../img/baseimages/logobig.png"/></li>
                </div>

                <div class="search-bar pr">
                    <a name="index_none_header_sysc" href="#"></a>
                    <form>
                        <input id="searchInput" name="index_none_header_sysc" type="text" placeholder="搜索"
                               autocomplete="off">
                        <input id="ai-topsearch" class="submit am-btn" value="搜索" index="1" type="submit">
                    </form>
                </div>
            </div>

            <div class="clear"></div>
        </div>
        </div>
    </article>
</header>
<div class="nav-table">
    <div class="long-title"><span class="all-goods">全部分类</span></div>
    <div class="nav-cont">
        <ul>
            <li class="index"><a href="/index">首页</a></li>
        </ul>
    </div>
</div>
<b class="line"></b>
<div class="center">
    <div class="col-main">
        <div class="main-wrap">

            <div class="user-order">

                <!--标题 -->
                <div class="am-cf am-padding">
                    <div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">订单管理</strong> /
                        <small>Order</small>
                    </div>
                </div>
                <hr/>

                <div class="am-tabs am-tabs-d2 am-margin" data-am-tabs>

                    <ul class="am-avg-sm-5 am-tabs-nav am-nav am-nav-tabs">
                        <li class="am-active"><a href="#tab1">所有订单</a></li>
                        <li><a href="#tab2">待付款</a></li>
                        <li><a href="#tab3">待发货</a></li>
                        <li><a href="#tab4">待收货</a></li>
                        <li><a href="#tab5">待评价</a></li>
                    </ul>

                    <div class="am-tabs-bd">
                        <div class="am-tab-panel am-fade am-in am-active" id="tab1">
                            <div class="order-top">
                                <div class="th th-item">
                                    <td class="td-inner">商品</td>
                                </div>
                                <div class="th th-price">
                                    <td class="td-inner">单价</td>
                                </div>
                                <div class="th th-number">
                                    <td class="td-inner">数量</td>
                                </div>
                                <div class="th th-operation">
                                    <td class="td-inner">商品操作</td>
                                </div>
                                <div class="th th-amount">
                                    <td class="td-inner">合计</td>
                                </div>
                                <div class="th th-status">
                                    <td class="td-inner">交易状态</td>
                                </div>
                                <div class="th th-change">
                                    <td class="td-inner">交易操作</td>
                                </div>
                            </div>

                            <div class="order-main">
                                <div class="order-list">

                                    <c:forEach var="orderInfo" items="${orderInfoList}">
                                        <!--待发货-->
                                        <div class="order-status2">
                                            <div class="order-title">
                                                <div class="dd-num">订单编号：<a href="javascript:;">${orderInfo.orderId}</a>
                                                </div>
                                                <span>成交时间：${orderInfo.createdAt.toLocaleString()}</span>
                                                <!--    <em>店铺：小桔灯</em>-->
                                            </div>
                                            <div class="order-content">
                                                <div class="order-left">
                                                    <c:forEach var="sku" items="${orderInfo.subOrders}">
                                                        <ul class="item-list" style="margin-bottom: 20px">
                                                            <li class="td td-item">
                                                                <div class="item-pic">
                                                                    <a href="/item/${sku.spuId}" class="J_MakePoint">
                                                                        <img src="${sku.imgUrl}"
                                                                             class="itempic J_ItemImg">
                                                                    </a>
                                                                </div>
                                                                <div class="item-info">
                                                                    <div class="item-basic-info">
                                                                        <a href="/item/${sku.spuId}">
                                                                            <p>${sku.name}</p>
                                                                            <p class="info-little">
                                                                                <c:forEach var="attribute"
                                                                                           items="${sku.attributes}">
                                                                                    ${attribute.attributeName}:${attribute.optionName}
                                                                                    <br/>
                                                                                </c:forEach>
                                                                            </p>
                                                                        </a>
                                                                    </div>
                                                                </div>
                                                            </li>
                                                            <li class="td td-price">
                                                                <div class="item-price">
                                                                    ¥ ${sku.price}
                                                                </div>
                                                            </li>
                                                            <li class="td td-number">
                                                                <div class="item-number">
                                                                    <span>×</span>${sku.count}
                                                                </div>
                                                            </li>
                                                            <li class="td td-operation">
                                                                <div class="item-operation">
                                                                    <c:if test="${orderInfo.status == 2}">
                                                                        <a href="/person/refund">退款</a>
                                                                    </c:if>
                                                                    <c:if test="${orderInfo.status == 4}">
                                                                        <a href="/person/refund">退款/退货</a>
                                                                    </c:if>
                                                                </div>
                                                            </li>
                                                        </ul>
                                                    </c:forEach>
                                                </div>
                                                <div class="order-right">
                                                    <li class="td td-amount">
                                                        <div class="item-amount">
                                                            合计：${orderInfo.sumPrice}
                                                        </div>
                                                    </li>
                                                    <div class="move-right">
                                                        <li class="td td-status">
                                                            <div class="item-status">
                                                                <p class="Mystatus">
                                                                    <c:if test="${orderInfo.status == 1}">
                                                                        等待买家付款
                                                                    </c:if>
                                                                    <c:if test="${orderInfo.status == 2}">
                                                                        买家已付款
                                                                    </c:if>
                                                                    <c:if test="${orderInfo.status == 4}">
                                                                        卖家已发货
                                                                    </c:if>
                                                                    <c:if test="${orderInfo.status == 8}">
                                                                        买家已确认收货
                                                                    </c:if>
                                                                    <c:if test="${orderInfo.status == 16}">
                                                                        订单完成
                                                                    </c:if>
                                                                </p>
                                                                <p class="order-info"><a
                                                                        href="/order/${orderInfo.orderId}">订单详情</a></p>
                                                            </div>
                                                        </li>
                                                        <li class="td td-change">
                                                            <div class="am-btn am-btn-danger anniu">
                                                                <c:if test="${orderInfo.status == 1}">
                                                                    <a style="color: white" url="/order/pay/${orderInfo.orderId}" href="javascript:;" onclick="payNow(this)">付款</a>
                                                                </c:if>
                                                                <c:if test="${orderInfo.status == 2}">
                                                                    <a style="color: white" url="/order/deliver/${orderInfo.orderId}" href="javascript:;" onclick="fhNow(this)">提醒发货</a>
                                                                </c:if>
                                                                <c:if test="${orderInfo.status == 4}">
                                                                    <a style="color: white" url="/order/receive/${orderInfo.orderId}" href="javascript:;" onclick="shNow(this)">确认收货</a>
                                                                </c:if>
                                                                <c:if test="${orderInfo.status == 8}">
                                                                    <a style="color: white" href="/order/assess/${orderInfo.orderId}">去评价</a>
                                                                </c:if>
                                                                <c:if test="${orderInfo.status == 16}">
                                                                    订单完成
                                                                </c:if>
                                                            </div>
                                                        </li>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </c:forEach>
                                </div>

                            </div>

                        </div>
                        <div class="am-tab-panel am-fade" id="tab2">

                            <div class="order-top">
                                <div class="th th-item">
                                    <td class="td-inner">商品</td>
                                </div>
                                <div class="th th-price">
                                    <td class="td-inner">单价</td>
                                </div>
                                <div class="th th-number">
                                    <td class="td-inner">数量</td>
                                </div>
                                <div class="th th-operation">
                                    <td class="td-inner">商品操作</td>
                                </div>
                                <div class="th th-amount">
                                    <td class="td-inner">合计</td>
                                </div>
                                <div class="th th-status">
                                    <td class="td-inner">交易状态</td>
                                </div>
                                <div class="th th-change">
                                    <td class="td-inner">交易操作</td>
                                </div>
                            </div>

                            <div class="order-main">
                                <c:forEach var="orderInfo" items="${orderInfoList}">
                                    <!--待付款-->
                                    <c:if test="${orderInfo.status == 1}">
                                        <div class="order-status2">
                                            <div class="order-title">
                                                <div class="dd-num">订单编号：<a href="javascript:;">${orderInfo.orderId}</a>
                                                </div>
                                                <span>成交时间：${orderInfo.createdAt.toLocaleString()}</span>
                                                <!--    <em>店铺：小桔灯</em>-->
                                            </div>
                                            <div class="order-content">
                                                <div class="order-left">
                                                    <c:forEach var="sku" items="${orderInfo.subOrders}">
                                                        <ul class="item-list" style="margin-bottom: 20px">
                                                            <li class="td td-item">
                                                                <div class="item-pic">
                                                                    <a href="/item/${sku.spuId}" class="J_MakePoint">
                                                                        <img src="${sku.imgUrl}"
                                                                             class="itempic J_ItemImg">
                                                                    </a>
                                                                </div>
                                                                <div class="item-info">
                                                                    <div class="item-basic-info">
                                                                        <a href="/item/${sku.spuId}">
                                                                            <p>${sku.name}</p>
                                                                            <p class="info-little">
                                                                                <c:forEach var="attribute"
                                                                                           items="${sku.attributes}">
                                                                                    ${attribute.attributeName}:${attribute.optionName}
                                                                                    <br/>
                                                                                </c:forEach>
                                                                            </p>
                                                                        </a>
                                                                    </div>
                                                                </div>
                                                            </li>
                                                            <li class="td td-price">
                                                                <div class="item-price">
                                                                    ¥ ${sku.price}
                                                                </div>
                                                            </li>
                                                            <li class="td td-number">
                                                                <div class="item-number">
                                                                    <span>×</span>${sku.count}
                                                                </div>
                                                            </li>
                                                            <li class="td td-operation">
                                                                <div class="item-operation">
                                                                </div>
                                                            </li>
                                                        </ul>
                                                    </c:forEach>
                                                </div>
                                                <div class="order-right">
                                                    <li class="td td-amount">
                                                        <div class="item-amount">
                                                            合计：${orderInfo.sumPrice}
                                                        </div>
                                                    </li>
                                                    <div class="move-right">
                                                        <li class="td td-status">
                                                            <div class="item-status">
                                                                <p class="Mystatus">等待买家付款</p>
                                                                <p class="order-info"><a
                                                                        href="/order/${orderInfo.orderId}">订单详情</a></p>
                                                            </div>
                                                        </li>
                                                        <li class="td td-change">
                                                            <div class="am-btn am-btn-danger anniu">
                                                                <a style="color: white" url="/order/pay/${orderInfo.orderId}" href="javascript:;" onclick="payNow(this)">付款</a>
                                                            </div>
                                                        </li>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="am-tab-panel am-fade" id="tab3">
                            <div class="order-top">
                                <div class="th th-item">
                                    <td class="td-inner">商品</td>
                                </div>
                                <div class="th th-price">
                                    <td class="td-inner">单价</td>
                                </div>
                                <div class="th th-number">
                                    <td class="td-inner">数量</td>
                                </div>
                                <div class="th th-operation">
                                    <td class="td-inner">商品操作</td>
                                </div>
                                <div class="th th-amount">
                                    <td class="td-inner">合计</td>
                                </div>
                                <div class="th th-status">
                                    <td class="td-inner">交易状态</td>
                                </div>
                                <div class="th th-change">
                                    <td class="td-inner">交易操作</td>
                                </div>
                            </div>

                            <div class="order-main">
                                <div class="order-list">
                                    <%--待发货--%>
                                    <c:forEach var="orderInfo" items="${orderInfoList}">
                                        <c:if test="${orderInfo.status == 2}">
                                            <div class="order-status2">
                                                <div class="order-title">
                                                    <div class="dd-num">订单编号：<a
                                                            href="javascript:;">${orderInfo.orderId}</a></div>
                                                    <span>成交时间：${orderInfo.createdAt.toLocaleString()}</span>
                                                    <!--    <em>店铺：小桔灯</em>-->
                                                </div>
                                                <div class="order-content">
                                                    <div class="order-left">
                                                        <c:forEach var="sku" items="${orderInfo.subOrders}">
                                                            <ul class="item-list" style="margin-bottom: 20px">
                                                                <li class="td td-item">
                                                                    <div class="item-pic">
                                                                        <a href="/item/${sku.spuId}"
                                                                           class="J_MakePoint">
                                                                            <img src="${sku.imgUrl}"
                                                                                 class="itempic J_ItemImg">
                                                                        </a>
                                                                    </div>
                                                                    <div class="item-info">
                                                                        <div class="item-basic-info">
                                                                            <a href="/item/${sku.spuId}">
                                                                                <p>${sku.name}</p>
                                                                                <p class="info-little">
                                                                                    <c:forEach var="attribute"
                                                                                               items="${sku.attributes}">
                                                                                        ${attribute.attributeName}:${attribute.optionName}
                                                                                        <br/>
                                                                                    </c:forEach>
                                                                                </p>
                                                                            </a>
                                                                        </div>
                                                                    </div>
                                                                </li>
                                                                <li class="td td-price">
                                                                    <div class="item-price">
                                                                        ¥ ${sku.price}
                                                                    </div>
                                                                </li>
                                                                <li class="td td-number">
                                                                    <div class="item-number">
                                                                        <span>×</span>${sku.count}
                                                                    </div>
                                                                </li>
                                                                <li class="td td-operation">
                                                                    <div class="item-operation">
                                                                        <a href="/order/refund/${orderInfo.orderId}">退款</a>
                                                                    </div>
                                                                </li>
                                                            </ul>
                                                        </c:forEach>
                                                    </div>
                                                    <div class="order-right">
                                                        <li class="td td-amount">
                                                            <div class="item-amount">
                                                                合计：${orderInfo.sumPrice}
                                                            </div>
                                                        </li>
                                                        <div class="move-right">
                                                            <li class="td td-status">
                                                                <div class="item-status">
                                                                    <p class="Mystatus">买家已付款</p>
                                                                    <p class="order-info"><a
                                                                            href="/order/${orderInfo.orderId}">订单详情</a>
                                                                    </p>
                                                                </div>
                                                            </li>
                                                            <li class="td td-change">
                                                                <div class="am-btn am-btn-danger anniu">
                                                                    <a style="color: white" url="/order/deliver/${orderInfo.orderId}" href="javascript:;" onclick="fhNow(this)">提醒发货</a>
                                                                </div>
                                                            </li>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        <div class="am-tab-panel am-fade" id="tab4">
                            <div class="order-top">
                                <div class="th th-item">
                                    <td class="td-inner">商品</td>
                                </div>
                                <div class="th th-price">
                                    <td class="td-inner">单价</td>
                                </div>
                                <div class="th th-number">
                                    <td class="td-inner">数量</td>
                                </div>
                                <div class="th th-operation">
                                    <td class="td-inner">商品操作</td>
                                </div>
                                <div class="th th-amount">
                                    <td class="td-inner">合计</td>
                                </div>
                                <div class="th th-status">
                                    <td class="td-inner">交易状态</td>
                                </div>
                                <div class="th th-change">
                                    <td class="td-inner">交易操作</td>
                                </div>
                            </div>

                            <div class="order-main">
                                <div class="order-list">
                                    <%--待收货--%>
                                    <c:forEach var="orderInfo" items="${orderInfoList}">
                                        <c:if test="${orderInfo.status == 4}">
                                            <div class="order-status2">
                                                <div class="order-title">
                                                    <div class="dd-num">订单编号：<a
                                                            href="javascript:;">${orderInfo.orderId}</a></div>
                                                    <span>成交时间：${orderInfo.createdAt.toLocaleString()}</span>
                                                    <!--    <em>店铺：小桔灯</em>-->
                                                </div>
                                                <div class="order-content">
                                                    <div class="order-left">
                                                        <c:forEach var="sku" items="${orderInfo.subOrders}">
                                                            <ul class="item-list" style="margin-bottom: 20px">
                                                                <li class="td td-item">
                                                                    <div class="item-pic">
                                                                        <a href="/item/${sku.spuId}"
                                                                           class="J_MakePoint">
                                                                            <img src="${sku.imgUrl}"
                                                                                 class="itempic J_ItemImg">
                                                                        </a>
                                                                    </div>
                                                                    <div class="item-info">
                                                                        <div class="item-basic-info">
                                                                            <a href="/item/${sku.spuId}">
                                                                                <p>${sku.name}</p>
                                                                                <p class="info-little">
                                                                                    <c:forEach var="attribute"
                                                                                               items="${sku.attributes}">
                                                                                        ${attribute.attributeName}:${attribute.optionName}
                                                                                        <br/>
                                                                                    </c:forEach>
                                                                                </p>
                                                                            </a>
                                                                        </div>
                                                                    </div>
                                                                </li>
                                                                <li class="td td-price">
                                                                    <div class="item-price">
                                                                        ¥ ${sku.price}
                                                                    </div>
                                                                </li>
                                                                <li class="td td-number">
                                                                    <div class="item-number">
                                                                        <span>×</span>${sku.count}
                                                                    </div>
                                                                </li>
                                                                <li class="td td-operation">
                                                                    <div class="item-operation">
                                                                        <a href="/order/refund/${orderInfo.orderId}">退款/退货</a>
                                                                    </div>
                                                                </li>
                                                            </ul>
                                                        </c:forEach>
                                                    </div>
                                                    <div class="order-right">
                                                        <li class="td td-amount">
                                                            <div class="item-amount">
                                                                合计：${orderInfo.sumPrice}
                                                            </div>
                                                        </li>
                                                        <div class="move-right">
                                                            <li class="td td-status">
                                                                <div class="item-status">
                                                                    <p class="Mystatus">卖家已发货</p>
                                                                    <p class="order-info"><a
                                                                            href="/order/${orderInfo.orderId}">订单详情</a>
                                                                    </p>
                                                                </div>
                                                            </li>
                                                            <li class="td td-change">
                                                                <div class="am-btn am-btn-danger anniu">
                                                                    <a style="color: white" url="/order/receive/${orderInfo.orderId}" href="javascript:;" onclick="shNow(this)">确认收货</a>
                                                                </div>
                                                            </li>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>

                        <div class="am-tab-panel am-fade" id="tab5">
                            <div class="order-top">
                                <div class="th th-item">
                                    <td class="td-inner">商品</td>
                                </div>
                                <div class="th th-price">
                                    <td class="td-inner">单价</td>
                                </div>
                                <div class="th th-number">
                                    <td class="td-inner">数量</td>
                                </div>
                                <div class="th th-operation">
                                    <td class="td-inner">商品操作</td>
                                </div>
                                <div class="th th-amount">
                                    <td class="td-inner">合计</td>
                                </div>
                                <div class="th th-status">
                                    <td class="td-inner">交易状态</td>
                                </div>
                                <div class="th th-change">
                                    <td class="td-inner">交易操作</td>
                                </div>
                            </div>

                            <div class="order-main">
                                <div class="order-list">
                                    <%--待评价--%>
                                    <c:forEach var="orderInfo" items="${orderInfoList}">
                                        <c:if test="${orderInfo.status == 8}">

                                            <div class="order-status2">
                                                <div class="order-title">
                                                    <div class="dd-num">订单编号：<a
                                                            href="javascript:;">${orderInfo.orderId}</a></div>
                                                    <span>成交时间：${orderInfo.createdAt.toLocaleString()}</span>
                                                    <!--    <em>店铺：小桔灯</em>-->
                                                </div>
                                                <div class="order-content">
                                                    <div class="order-left">
                                                        <c:forEach var="sku" items="${orderInfo.subOrders}">
                                                            <ul class="item-list" style="margin-bottom: 20px">
                                                                <li class="td td-item">
                                                                    <div class="item-pic">
                                                                        <a href="/item/${sku.spuId}"
                                                                           class="J_MakePoint">
                                                                            <img src="${sku.imgUrl}"
                                                                                 class="itempic J_ItemImg">
                                                                        </a>
                                                                    </div>
                                                                    <div class="item-info">
                                                                        <div class="item-basic-info">
                                                                            <a href="/item/${sku.spuId}">
                                                                                <p>${sku.name}</p>
                                                                                <p class="info-little">
                                                                                    <c:forEach var="attribute"
                                                                                               items="${sku.attributes}">
                                                                                        ${attribute.attributeName}:${attribute.optionName}
                                                                                        <br/>
                                                                                    </c:forEach>
                                                                                </p>
                                                                            </a>
                                                                        </div>
                                                                    </div>
                                                                </li>
                                                                <li class="td td-price">
                                                                    <div class="item-price">
                                                                        ¥ ${sku.price}
                                                                    </div>
                                                                </li>
                                                                <li class="td td-number">
                                                                    <div class="item-number">
                                                                        <span>×</span>${sku.count}
                                                                    </div>
                                                                </li>
                                                                <li class="td td-operation">
                                                                    <div class="item-operation">
                                                                        <a href="/order/refund/${orderInfo.orderId}">退款/退货</a>
                                                                    </div>
                                                                </li>
                                                            </ul>
                                                        </c:forEach>
                                                    </div>
                                                    <div class="order-right">
                                                        <li class="td td-amount">
                                                            <div class="item-amount">
                                                                合计：${orderInfo.sumPrice}
                                                            </div>
                                                        </li>
                                                        <div class="move-right">
                                                            <li class="td td-status">
                                                                <div class="item-status">
                                                                    <p class="Mystatus">买家已确认收货</p>
                                                                    <p class="order-info"><a
                                                                            href="/order/${orderInfo.orderId}">订单详情</a>
                                                                    </p>
                                                                </div>
                                                            </li>
                                                            <li class="td td-change">
                                                                <div class="am-btn am-btn-danger anniu">
                                                                    <a style="color: white" href="/order/assess/${orderInfo.orderId}">去评价</a>
                                                                </div>
                                                            </li>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                    </c:forEach>

                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
    <aside class="menu">
        <ul>
            <li class="person active">
                <a href="index.html"><i class="am-icon-user"></i>个人中心</a>
            </li>
            <li class="person">
                <p><i class="am-icon-newspaper-o"></i>个人资料</p>
                <ul>
                    <li><a href="information.html">个人信息</a></li>
                    <li><a href="safety.html">安全设置</a></li>
                    <li><a href="address.html">地址管理</a></li>
                    <li><a href="cardlist.html">快捷支付</a></li>
                </ul>
            </li>
            <li class="person">
                <p><i class="am-icon-balance-scale"></i>我的交易</p>
                <ul>
                    <li><a href="order.html">订单管理</a></li>
                    <li><a href="change.html">退款售后</a></li>
                    <li><a href="comment.html">评价商品</a></li>
                </ul>
            </li>
            <li class="person">
                <p><i class="am-icon-dollar"></i>我的资产</p>
                <ul>
                    <li><a href="points.html">我的积分</a></li>
                    <li><a href="coupon.html">优惠券 </a></li>
                    <li><a href="bonus.html">红包</a></li>
                    <li><a href="walletlist.html">账户余额</a></li>
                    <li><a href="bill.html">账单明细</a></li>
                </ul>
            </li>

            <li class="person">
                <p><i class="am-icon-tags"></i>我的收藏</p>
                <ul>
                    <li><a href="collection.html">收藏</a></li>
                    <li><a href="foot.html">足迹</a></li>
                </ul>
            </li>

            <li class="person">
                <p><i class="am-icon-qq"></i>在线客服</p>
                <ul>
                    <li><a href="consultation.html">商品咨询</a></li>
                    <li><a href="suggest.html">意见反馈</a></li>

                    <li><a href="news.html">我的消息</a></li>
                </ul>
            </li>
        </ul>

    </aside>
</div>
<script type="application/javascript">
    function fhNow(action) {
        $.ajax({
            url: $(action).attr("url"),
            success: function (result) {
                alert(result)
            }
        });
    }
    function payNow(action) {
        $.ajax({
            url: $(action).attr("url"),
            success: function (result) {
                alert(result)
            }
        });
    }
</script>
</body>

</html>