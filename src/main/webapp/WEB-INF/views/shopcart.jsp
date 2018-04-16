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

    <title>购物车页面</title>

    <link href="../AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css"/>
    <link href="../css/basic/demo.css" rel="stylesheet" type="text/css"/>
    <link href="../css/basecss/cartstyle.css" rel="stylesheet" type="text/css"/>
    <link href="../css/basecss/optstyle.css" rel="stylesheet" type="text/css"/>

    <script type="text/javascript" src="../js/basejs/jquery.js"></script>

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

<!--购物车 -->
<div class="concent">
    <div id="cartTable">
        <div class="cart-table-th">
            <div class="wp">
                <div class="th th-chk">
                    <div id="J_SelectAll1" class="select-all J_SelectAll">

                    </div>
                </div>
                <div class="th th-item">
                    <div class="td-inner">商品信息</div>
                </div>
                <div class="th th-price">
                    <div class="td-inner">单价</div>
                </div>
                <div class="th th-amount">
                    <div class="td-inner">数量</div>
                </div>
                <div class="th th-sum">
                    <div class="td-inner">金额</div>
                </div>
                <div class="th th-op">
                    <div class="td-inner">操作</div>
                </div>
            </div>
        </div>
        <div class="clear"></div>
<script type="application/javascript">
    var a = "${goodsList}";
</script>
        <tr class="item-list">
            <div class="bundle  bundle-last ">
                <div class="clear"></div>
                <div class="bundle-main">
                    <c:forEach var="goods" items="${goodsList}">
                    <ul id="goods_${goods.cartId}" class="item-content clearfix">
                        <li class="td td-chk">
                            <div class="cart-checkbox ">
                                <input class="check" id="J_CheckBox_${goods.skuId}" name="items[]" value="${goods.skuId}"
                                       type="checkbox">
                                <label for="J_CheckBox_${goods.skuId}"></label>
                            </div>
                            <script type="application/javascript">
                                $(".check").change(function() {
                                    countCartPrice();
                                });
                            </script>
                        </li>
                        <li class="td td-item">
                            <div class="item-pic">
                                <a href="/item/${goods.spuId}" target="_blank" data-title="${goods.spuName}"
                                   class="J_MakePoint" data-point="tbcart.8.12">
                                    <img width="80" height="80" src="${goods.imgUrl}" class="itempic J_ItemImg"></a>
                            </div>
                            <div class="item-info">
                                <div class="item-basic-info">
                                    <a href="/item/${goods.spuId}" target="_blank" title="${goods.spuName}"
                                       class="item-title J_MakePoint" data-point="tbcart.8.11">${goods.spuName}</a>
                                    <p style="color: red">库存:${goods.stock}</p>
                                </div>
                            </div>
                        </li>
                        <li class="td td-info">
                            <div class="item-props item-props-can">
                                <c:forEach var="attribute" items="${goods.attributes}">
                                <span class="sku-line">${attribute.attributeName}: ${attribute.optionName}</span>
                                </c:forEach>
                                <%--<span tabindex="0" class="btn-edit-sku theme-login">修改</span>--%>
                                <%--<i class="theme-login am-icon-sort-desc"></i>--%>
                            </div>
                        </li>
                        <li class="td td-price">
                            <div class="item-price price-promo-promo">
                                <div class="price-content">
                                    <div class="price-line">
                                        <em id="price_${goods.skuId}" class="J_Price price-now" tabindex="0">${goods.price}</em>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li class="td td-amount">
                            <div class="amount-wrapper ">
                                <div class="item-amount ">
                                    <div class="sl">
                                        <input id="minCount" class="minCount am-btn" name="" type="button" value="-"/>
                                        <input id="${goods.skuId}" maxNum="${goods.stock}" class="text_box goodsCount" name="" type="text" value="${goods.count}" style="width:30px;"/>
                                        <input id="addCount" class="addCount am-btn" name="" type="button" value="+"/>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <script type="application/javascript">

                            $(function(){
                                $(".addCount").click(function(){
                                    var t=$(this).parent().find('input[class*=text_box]');
                                    t.val(parseInt(t.val())+1)
                                    if(parseInt(t.val())>t.attr("maxNum")){
                                        t.val(t.attr("maxNum"));
                                    }
                                    changePrice($(t).attr("id"), t.val());
                                })
                                $(".minCount").click(function(){
                                    var t=$(this).parent().find('input[class*=text_box]');
                                    t.val(parseInt(t.val())-1)
                                    if(parseInt(t.val())<1){
                                        t.val(1);
                                    }
                                    changePrice($(t).attr("id"), t.val());
                                })
                            })
                            $(".goodsCount").change(function () {
                                if($(this).val()>$(this).attr("maxNum")){
                                    $(this).val(($(this).attr("maxNum")));
                                }
                                changePrice($(this).attr("id"), $(this).val());
                            })
                            function changePrice(id, count) {
                                $("#totalPrice_"+id).text(($("#price_"+id).text() * count).toFixed(2))
                                countCartPrice();
                            }
                            function countCartPrice(){
                                var totalGoodsPrice = 0.00;
                                var totalCount = 0;
                                $(".bundle-main ul").each(function () {
                                    if ($(this).find($("input[type='checkbox']")).is(':checked')) {
                                        var count = $(this).find(".goodsCount").val()
                                        var id = $(this).find(".goodsCount").attr("id")
                                        var price = $("#price_"+id).text();
                                        totalGoodsPrice += price * count;
                                        totalCount += parseInt(count);
                                    }
                                })
                                $("#J_Total").text(totalGoodsPrice.toFixed(2));
                                $("#J_SelectedItemsCount").text(totalCount);

                            }
                        </script>
                        <li class="td td-sum">
                            <div class="td-inner">
                                <em id="totalPrice_${goods.skuId}" tabindex="0" class="J_ItemSum number">${goods.totalPrice}</em>
                            </div>
                        </li>
                        <li class="td td-op">
                            <div class="td-inner">
                                <a title="移入收藏夹" class="btn-fav" href="#">
                                    移入收藏夹</a>
                                <a href="javascript:;" data-point-url="#" class="delete" id="${goods.cartId}" onclick="deleteGoods(this)">
                                    删除</a>
                            </div>
                        </li>
                        <script type="application/javascript">
                            function deleteGoods(del) {
                                var id = $(del).attr("id");
                                $.ajax({
                                    url: '/cart/'+id,
                                    type: 'DELETE',
                                    success: function(result) {
                                        $("#goods_"+id).remove();
                                        countCartPrice();
                                    }
                                });
                            }
                        </script>
                    </ul>
                    </c:forEach>

                </div>
            </div>
        </tr>
    </div>
    <div class="clear"></div>

    <div class="float-bar-wrapper">
        <div id="J_SelectAll2" class="select-all J_SelectAll">
            <div class="cart-checkbox">
                <input class="check-all check" id="J_SelectAllCbx2" name="select-all" value="true" type="checkbox">
                <label for="J_SelectAllCbx2"></label>
                <script type="application/javascript">
                    $("#J_SelectAllCbx2").click(function () {
                        if (($(".bundle-main input[type='checkbox']")).is(':checked')) {

                            $(".check").attr("checked", false);
                            countCartPrice();
                        } else {
                            $(".check").attr("checked", true);
                            countCartPrice();
                        }
                    })
//                    $(".check").change(function() {
//                        countCartPrice();
//                    });
                </script>
            </div>
            <span>全选</span>
        </div>
        <%--<div class="operations">--%>
            <%--<a href="#" hidefocus="true" class="deleteAll">删除</a>--%>
            <%--<a href="#" hidefocus="true" class="J_BatchFav">移入收藏夹</a>--%>
        <%--</div>--%>
        <div class="float-bar-right">
            <div class="amount-sum">
                <span class="txt">已选商品</span>
                <em id="J_SelectedItemsCount">0</em><span class="txt">件</span>
                <div class="arrow-box">
                    <span class="selected-items-arrow"></span>
                    <span class="arrow"></span>
                </div>
            </div>
            <div class="price-sum">
                <span class="txt">合计:</span>
                <strong class="price">¥<em id="J_Total">0.00</em></strong>
            </div>
            <div class="btn-area">
                <a href="javascript:;" onclick="buy()" id="J_Go" class="submit-btn submit-btn-disabled" aria-label="请注意如果没有选择宝贝，将无法结算">
                    <span>结&nbsp;算</span></a>
            </div>
            <script type="application/javascript">
//                var totalGoodsPrice = 0.00;
//                var totalCount = 0;
//                $(".bundle-main ul").each(function () {
//                    if ($(this).find($("input[type='checkbox']")).is(':checked')) {
//                        var count = $(this).find(".goodsCount").val()
//                        var id = $(this).find(".goodsCount").attr("id")
//                        var price = $("#price_"+id).text();
//                        totalGoodsPrice += price * count;
//                        totalCount += parseInt(count);
//                    }
//                })
//                $("#J_Total").text(totalGoodsPrice.toFixed(2));
//                $("#J_SelectedItemsCount").text(totalCount);
                function buy() {
                    var totalCount = $("#J_SelectedItemsCount").text();
                    if (totalCount == 0) {
                        alert("请选择商品结算")
                        return;
                    }
                    var objectArr = [];
                    $(".bundle-main ul").each(function () {
                    if ($(this).find($("input[type='checkbox']")).is(':checked')) {
                        var object = {};
                        var sku = $(this).find(".goodsCount").attr("id")
                        var count = $(this).find(".goodsCount").val();
                        object.skuId = parseInt(sku);
                        object.count = parseInt(count);
                        objectArr.push(object);
                     }
                    })
                    window.location.href = "/buy?param="+JSON.stringify(objectArr)+"&shopCart=true";
                }
            </script>
        </div>

    </div>

</div>

<!--操作页面-->

<div class="theme-popover-mask"></div>

<div class="theme-popover">
    <div class="theme-span"></div>
    <div class="theme-poptit h-title">
        <a href="javascript:;" title="关闭" class="close">×</a>
    </div>
    <div class="theme-popbod dform">
        <form class="theme-signin" name="loginform" action="" method="post">

            <div class="theme-signin-left">

                <li class="theme-options">
                    <div class="cart-title">颜色：</div>
                    <ul>
                        <li class="sku-line selected">12#川南玛瑙<i></i></li>
                        <li class="sku-line">10#蜜橘色+17#樱花粉<i></i></li>
                    </ul>
                </li>
                <li class="theme-options">
                    <div class="cart-title">包装：</div>
                    <ul>
                        <li class="sku-line selected">包装：裸装<i></i></li>
                        <li class="sku-line">两支手袋装（送彩带）<i></i></li>
                    </ul>
                </li>
                <div class="theme-options">
                    <div class="cart-title number">数量</div>
                    <dd>
                        <input class="min am-btn am-btn-default" name="" type="button" value="-"/>
                        <input class="text_box" name="" type="text" value="1" style="width:30px;"/>
                        <input class="add am-btn am-btn-default" name="" type="button" value="+"/>
                        <span class="tb-hidden">库存<span class="stock">1000</span>件</span>
                    </dd>

                </div>
                <div class="clear"></div>
                <div class="btn-op">
                    <div class="btn am-btn am-btn-warning">确认</div>
                    <div class="btn close am-btn am-btn-warning">取消</div>
                </div>

            </div>
            <div class="theme-signin-right">
                <div class="img-info">
                    <img src="../img/baseimages/kouhong.jpg_80x80.jpg"/>
                </div>
                <div class="text-info">
                    <span class="J_Price price-now">¥39.00</span>
                    <span id="Stock" class="tb-hidden">库存<span class="stock">1000</span>件</span>
                </div>
            </div>

        </form>
    </div>
</div>
<!--引导 -->
<div class="navCir">
    <li><a href="home.html"><i class="am-icon-home "></i>首页</a></li>
    <li><a href="sort.html"><i class="am-icon-list"></i>分类</a></li>
    <li class="active"><a href="shopcart.html"><i class="am-icon-shopping-basket"></i>购物车</a></li>
    <li><a href="../person/index.html"><i class="am-icon-user"></i>我的</a></li>
</div>
</body>

</html>