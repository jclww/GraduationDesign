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
          content="width=device-width, initial-scale=1.0 ,minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <title>订单确认</title>

    <link href="../AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css"/>

    <link href="../css/basic/demo.css" rel="stylesheet" type="text/css"/>
    <link href="../css/basecss/cartstyle.css" rel="stylesheet" type="text/css"/>

    <link href="../css/basecss/jsstyle.css" rel="stylesheet" type="text/css"/>

    <script type="text/javascript" src="../js/basejs/address.js"></script>
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
        <form>
            <input id="searchInput" name="index_none_header_sysc" type="text" placeholder="搜索" autocomplete="off">
            <input id="ai-topsearch" class="submit am-btn" value="搜索" index="1" type="submit">
        </form>
    </div>
</div>

<div class="clear"></div>
<div class="concent">
    <!--地址 -->
    <form id="orderForm" action="order" method="post">
    <div class="paycont">
        <div class="address">
            <h3>确认收货地址 </h3>
            <%--<div class="control">--%>
            <%--<div class="tc-btn createAddr theme-login am-btn am-btn-danger">使用新地址</div>--%>
            <%--</div>--%>
            <div class="clear"></div>
            <ul>
                <c:forEach var="address" items="${addressList}">
                    <%--<div class="per-border"></div>--%>
                    <li addressId="${address.id}" class="user-addresslist <c:if test="${address.isDefault == 1}">defaultAddr</c:if>">

                        <div class="address-left">
                            <div class="user DefaultAddr">

										<span class="buy-address-detail">
                   <span class="buy-user">${address.name}</span>
										<span id="" class="buy-phone">${address.phone}</span>
										</span>
                            </div>
                            <div class="default-address DefaultAddr">
                                <span class="buy-line-title buy-line-title-type">收货地址：</span>
                                <span class="buy--address-detail">
								   <span class="province">${address.province}</span>
										<span class="city">${address.city}</span>
										<span class="dist">${address.district}</span>
										<span class="street">${address.details}</span>
										</span>
                                </span>
                            </div>
                            <c:if test="${address.isDefault == 1}">
                                <ins class="deftip">默认地址</ins>
                            </c:if>
                        </div>
                        <div class="clear"></div>
                    </li>

                </c:forEach>
                <input id="address" name="addressId" type="hidden">
            </ul>

            <div class="clear"></div>
        </div>
        <div class="clear"></div>

        <!--订单 -->
        <div class="concent">
            <div id="payTable">
                <h3>确认订单信息</h3>
                <div class="cart-table-th">
                    <div class="wp">

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
                        <div class="th th-oplist">
                            <div class="td-inner">配送方式</div>
                        </div>

                    </div>
                </div>
                <div class="clear"></div>

                <tr class="item-list">
                    <div class="bundle  bundle-last">
                        <div class="bundle-main">
                            <c:forEach var="goods" items="${goodsList}">
                                <ul class="item-content clearfix">
                                    <div class="pay-phone">
                                        <li class="td td-item">
                                            <div class="item-pic">
                                                <a href="/item/${goods.spuId}" class="J_MakePoint">
                                                    <img width="80" height="80"  src="${goods.imgUrl}"
                                                         class="itempic J_ItemImg"></a>
                                            </div>
                                            <div class="item-info">
                                                <div class="item-basic-info">
                                                    <a href="/item/${goods.spuId}" target="_blank"
                                                       class="item-title J_MakePoint"
                                                       data-point="tbcart.8.11">${goods.spuName}</a>
                                                </div>
                                            </div>
                                        </li>
                                        <li class="td td-info">
                                            <div class="item-props">
                                                <c:forEach var="attribute" items="${goods.attributes}">
                                                    <span class="sku-line">${attribute.attributeName}: ${attribute.optionName}</span>
                                                </c:forEach>
                                            </div>
                                        </li>
                                        <li class="td td-price">
                                            <div class="item-price price-promo-promo">
                                                <div class="price-content">
                                                    <em id="price_${goods.skuId}" class="J_Price price-now">${goods.price}</em>
                                                </div>
                                            </div>
                                        </li>
                                    </div>
                                    <li class="td td-amount">
                                        <div class="amount-wrapper ">
                                            <div class="item-amount ">
                                                <span class="phone-title">购买数量</span>
                                                <div class="sl">
                                                    <input id="${goods.skuId}" class="text_box goodsCount" name="" type="text" value="${goods.count}"
                                                           style="width:30px;" readonly="readonly"/>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="td td-sum">
                                        <div class="td-inner">
                                            <em tabindex="0" class="J_ItemSum number">${goods.totalPrice}</em>
                                        </div>
                                    </li>
                                    <li class="td td-oplist">
                                        <div class="td-inner">
                                            <span class="phone-title">配送方式</span>
                                            <div class="pay-logis">
                                                包邮
                                            </div>
                                        </div>
                                    </li>
                                    <input id="sku_${goods.skuId}" name="sku[]" type="hidden" value="${goods.skuId}:${goods.count}">
                                    <input name="shopCart" type="hidden" value="true">
                                </ul>
                            </c:forEach>
                            <div class="clear"></div>
                        </div>
                    </div>
                </tr>
                <div class="clear"></div>
            </div>
        </div>
        <div class="clear"></div>
        <div class="pay-total">
            <!--留言-->
            <div class="order-extra">
                <div class="order-user-info">
                    <div id="holyshit257" class="memo">
                        <label>买家留言：</label>
                        <input type="text" title="选填,对本次交易的说明（建议填写已经和卖家达成一致的说明）" placeholder="选填,建议填写和卖家达成一致的说明"
                               class="memo-input J_MakePoint c2c-text-default memo-close">
                        <div class="msg hidden J-msg">
                            <p class="error">最多输入500个字符</p>
                        </div>
                    </div>
                </div>

            </div>

        </div>
        <!--含运费小计 -->
        <div class="buy-point-discharge ">
            <p class="price g_price ">
                合计<span>¥</span><em class="pay-sum" id="totalGoodsPrice">0.00</em>
            </p>
        </div>
        <script type="application/javascript">
            window.onload = function () {
                countCartPrice()
                var username = $(".defaultAddr").find(".buy-user").text();
                var phone = $(".defaultAddr").find(".buy-phone").text();
                var province = $(".defaultAddr").find(".province").text();
                var city = $(".defaultAddr").find(".city").text();
                var dist = $(".defaultAddr").find(".dist").text();
                var street = $(".defaultAddr").find(".street").text();
                changeAddress(username,phone,province,city,dist,street);
            }
            function countCartPrice(){
                var totalGoodsPrice = 0.00;
                var totalCount = 0;
                $(".bundle-main ul").each(function () {
                        var count = $(this).find(".goodsCount").val()
                        var id = $(this).find(".goodsCount").attr("id")
                        var price = $("#price_"+id).text();
                        totalGoodsPrice += price * count;
                        totalCount += parseInt(count);
                })
//                $("#J_Total").text(totalGoodsPrice.toFixed(2));
                $("#totalGoodsPrice").text(totalGoodsPrice.toFixed(2));
                $("#J_ActualFee").text(totalGoodsPrice.toFixed(2));
            }
            $(".user-addresslist").click(function () {
                var username = $(this).find(".buy-user").text();
                var phone = $(this).find(".buy-phone").text();
                var province = $(this).find(".province").text();
                var city = $(this).find(".city").text();
                var dist = $(this).find(".dist").text();
                var street = $(this).find(".street").text();
                changeAddress(username,phone,province,city,dist,street);
            })
            function changeAddress(username,phone,province,city,dist,street) {
                $("#holyshit268").find(".buy-user").text(username);
                $("#holyshit268").find(".buy-phone").text(phone);
                $("#holyshit268").find(".province").text(province);
                $("#holyshit268").find(".city").text(city);
                $("#holyshit268").find(".dist").text(dist);
                $("#holyshit268").find(".street").text(street);
            }
        </script>
        <!--信息 -->
        <div class="order-go clearfix">
            <div class="pay-confirm clearfix">
                <div class="box">
                    <div tabindex="0" id="holyshit267" class="realPay"><em class="t">实付款：</em>
                        <span class="price g_price ">
                                    <span>¥</span> <em class="style-large-bold-red " id="J_ActualFee">0.00</em>
											</span>
                    </div>

                    <div id="holyshit268" class="pay-address">

                        <p class="buy-footer-address">
                            <span class="buy-line-title buy-line-title-type">寄送至：</span>
                            <span class="buy--address-detail">
								   <span class="province">湖北</span>
												<span class="city">武汉</span>
												<span class="dist">洪山</span>
												<span class="street">雄楚大道666号(中南财经政法大学)</span>
                                    </span>
                            </span>
                        </p>
                        <p class="buy-footer-address">
                            <span class="buy-line-title">收货人：</span>
                            <span class="buy-address-detail">
                                         <span class="buy-user">艾迪 </span>
												<span class="buy-phone">15871145629</span>
												</span>
                        </p>
                    </div>
                </div>

                <div id="holyshit269" class="submitOrder">
                    <div class="go-btn-wrap">
                        <a id="J_Go" href="javascript:;" onclick="submitAddForm()" class="btn-go" tabindex="0" title="点击此按钮，提交订单">提交订单</a>
                    </div>
                </div>
                <script type="application/javascript">
                    function submitAddForm() {
                        var form = $("#orderForm").html()
                        var addressId = $(".defaultAddr").attr("addressId");
                        $("#address").val(addressId);
                        var login = "请登录";
                        $("#orderForm").ajaxSubmit(function (data) {
                            if(login == data.substring(1,data.length-1)) {
                                window.location.href = "/login";
                                return;
                            }
                            $("#pay").find("#orderId").val(data.substring(1,data.length-1));
                            $('.theme-popover-mask').show();
                            $('.theme-popover-mask').height($(window).height());
                            $('#pay').slideDown(200);
                        });
                    }
                </script>
                <div class="clear"></div>
            </div>
        </div>
    </div>
    </form>
    <div class="clear"></div>
</div>
</div>

</div>
<div class="theme-popover-mask"></div>
<div id="pay" class="theme-popover" style="height:150px; margin-bottom:-100px">

    <!--标题 -->
    <div class="am-cf am-padding">
        <div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">现在支付</strong> /
            <small>Pay Now</small>
        </div>
    </div>
    <hr/>

    <div class="am-u-md-12">
        <form id="payForm" class="am-form am-form-horizontal" action="/pay" method="post">

            <input type="hidden" id="orderId" orderId="" name="orderId" value="">
            <div class="am-form-group theme-poptit">
                <div class="am-u-sm-9 am-u-sm-push-3">
                    <div class="am-btn am-btn-danger" onclick="payNow()">支付</div>
                    <div class="am-btn am-btn-danger close">稍后支付</div>
                </div>
            </div>
        </form>

        <script type="application/javascript">
            function payNow() {
                $("#payForm").ajaxSubmit(function (data) {
                    if("请登录" == data.substring(1,data.length-1)) {
                        window.location.href = "/login";
                        return;
                    }
                    $(document.body).css("overflow","visible");
                    $('.theme-login').removeClass("selected");
                    $('.item-props-can').removeClass("selected");
                    $('.theme-popover-mask').hide();
                    $('.theme-popover').slideUp(200);

                    $("#payResult").find("#resultMsg").text(data.substring(1,data.length-1));
                    $('.theme-popover-mask').show();
                    $('.theme-popover-mask').height($(window).height());
                    $('#payResult').slideDown(200);
                })
            }
        </script>
    </div>
</div>
<div id="payResult" class="theme-popover" style="height:150px; margin-bottom:-100px">

    <!--标题 -->
    <div class="am-cf am-padding">
        <div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">支付结果</strong> /
            <small>Pay Result</small>
        </div>
    </div>
    <hr/>

    <div class="am-u-md-12">
        <form id="resultForm" class="am-form am-form-horizontal" action="/person" method="get">
            <p id="resultMsg">你怎么能看到我？？？？？？</p>
            <div class="am-form-group theme-poptit">
                <div class="am-u-sm-9 am-u-sm-push-3">
                    <div class="am-btn am-btn-danger" onclick="resultSubmit()">我知道了</div>
                </div>
            </div>
        </form>
        <script type="application/javascript">
            function resultSubmit() {
                $("#resultForm").submit();
            }
        </script>
    </div>
</div>
<div class="clear"></div>
</body>

</html>