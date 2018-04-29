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

    <title>地址管理</title>

    <link href="../AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css">
    <link href="../AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css">

    <link href="../css/basecss/personal.css" rel="stylesheet" type="text/css">
    <link href="../css/basecss/addstyle.css" rel="stylesheet" type="text/css">
    <script src="../AmazeUI-2.4.2/assets/js/jquery.min.js" type="text/javascript"></script>
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
                        <div class="menu-hd"><a href="#" target="_top" class="h">商城首页</a></div>
                    </div>
                    <div class="topMessage my-shangcheng">
                        <div class="menu-hd MyShangcheng"><a href="#" target="_top"><i
                                class="am-icon-user am-icon-fw"></i>个人中心</a></div>
                    </div>
                    <div class="topMessage mini-cart">
                        <div class="menu-hd"><a id="mc-menu-hd" href="#" target="_top"><i
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
            <%--<li class="qc"><a href="#">闪购</a></li>--%>
            <%--<li class="qc"><a href="#">限时抢</a></li>--%>
            <%--<li class="qc"><a href="#">团购</a></li>--%>
            <%--<li class="qc last"><a href="#">大包装</a></li>--%>
        </ul>
        <%--<div class="nav-extra">--%>
            <%--<i class="am-icon-user-secret am-icon-md nav-user"></i><b></b>我的福利--%>
            <%--<i class="am-icon-angle-right" style="padding-left: 10px;"></i>--%>
        <%--</div>--%>
    </div>
</div>
<b class="line"></b>

<div class="center">
    <div class="col-main">
        <div class="main-wrap">

            <div class="user-address">
                <!--标题 -->
                <div class="am-cf am-padding">
                    <div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">地址管理</strong> /
                        <small>Address&nbsp;list</small>
                    </div>
                </div>
                <hr/>
                <ul class="am-avg-sm-1 am-avg-md-3 am-thumbnails">
                    <c:forEach var="address" items="${addressList}">
                        <li id="address${address.id}" class="user-addresslist <c:if test="${address.isDefault == 1}">defaultAddr</c:if>">
									<span class="new-option-r"><i class="am-icon-check-circle"></i>
										<c:if test="${address.isDefault == 1}">默认地址</c:if>
										<c:if test="${address.isDefault != 1}">
                                            <a addressId="${address.id}" onclick="defaultAddress(this);"><i class="am-icon-trash"></i>设为默认</a>
                                        </c:if>
									</span>
                            <p class="new-tit new-p-re">
                                <span id="name-${address.id}" class="new-txt">${address.name}</span>
                                <span id="phone-${address.id}" class="new-txt-rd2">${address.phone}</span>
                            </p>
                            <div class="new-mu_l2a new-p-re">
                                <p class="new-mu_l2cw">
                                    <span  class="title">地址：</span>
                                    <span id="province-${address.id}" province="${address.provinceCode}" class="province">${address.province}</span>
                                    <span id="city-${address.id}" city="${address.cityCode}" class="city">${address.city}</span>
                                    <span id="district-${address.id}" district="${address.districtCode}" class="district">${address.district}</span>
                                    <span id="details-${address.id}" class="street">${address.details}</span></p>
                            </div>
                            <div class="new-addr-btn">
                                <a href="javascript:void(0);" addressId="${address.id}" onclick="modifyAddress(this);"><i class="am-icon-trash"></i>编辑</a>
                                <span class="new-addr-bar">|</span>
                                <a href="javascript:void(0);" addressId="${address.id}" onclick="delAddress(this);"><i class="am-icon-trash"></i>删除</a>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
                <div class="clear"></div>
                <a class="new-abtn-type" data-am-modal="{target: '#doc-modal-1', closeViaDimmer: 0}">添加新地址</a>
                <!--例子-->
                <div class="am-modal am-modal-no-btn" id="doc-modal-1">

                    <div class="add-dress">

                        <!--标题 -->
                        <div id="addressTitle" class="am-cf am-padding">
                            <div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">新增地址</strong> /
                                <small>Add&nbsp;address</small>
                            </div>
                        </div>
                        <hr/>

                        <div class="am-u-md-12 am-u-lg-8" style="margin-top: 20px;">
                            <form id="addressForm" class="am-form am-form-horizontal" action="/person/address"
                                  method="post">
                                <input type="hidden" name="id" id="addressId" placeholder="收货人">
                                <div class="am-form-group">
                                    <label for="user-name" class="am-form-label">收货人</label>
                                    <div class="am-form-content">
                                        <input type="text" name="name" id="user-name" placeholder="收货人">
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <label for="user-phone" class="am-form-label">手机号码</label>
                                    <div class="am-form-content">
                                        <input id="user-phone" name="phone" placeholder="手机号必填" type="text">
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label class="am-form-label">所在地</label>
                                    <div class="am-form-content address">
                                        <select id="province" name="provinceCode">
                                        </select>
                                        <input id="provinceName" name="province" type="hidden">
                                        <select id="city" name="cityCode">
                                        </select>
                                        <input id="cityName" name="city" type="hidden">
                                        <select id="district" name="districtCode">
                                        </select>
                                        <input id="districtName" name="district" type="hidden">
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <label for="user-intro" class="am-form-label">详细地址</label>
                                    <div class="am-form-content">
                                        <textarea name="details" class="" rows="3" id="user-intro"
                                                  placeholder="输入详细地址"></textarea>
                                        <small>100字以内写出你的详细地址...</small>
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <div class="am-u-sm-9 am-u-sm-push-3">
                                        <button class="am-btn am-btn-danger">保存</button>
                                        <a href="javascript: void(0)" class="am-close am-btn am-btn-danger" onclick="clearAddress();" data-am-modal-close>取消</a>
                                    </div>
                                </div>
                            </form>
                        </div>

                    </div>

                </div>

            </div>

            <script type="text/javascript">
                $(document).ready(function () {
                    $(".new-option-r").click(function () {
                        $(this).parent('.user-addresslist').addClass("defaultAddr").siblings().removeClass("defaultAddr");
                    });

                    var $ww = $(window).width();
                    if ($ww > 640) {
                        $("#doc-modal-1").removeClass("am-modal am-modal-no-btn")
                    }

                })
            </script>

            <div class="clear"></div>

        </div>
        <!--底部-->
        <%--<div class="footer">--%>
            <%--<div class="footer-hd">--%>
                <%--<p>--%>
                    <%--<a href="#">恒望科技</a>--%>
                    <%--<b>|</b>--%>
                    <%--<a href="#">商城首页</a>--%>
                    <%--<b>|</b>--%>
                    <%--<a href="#">支付宝</a>--%>
                    <%--<b>|</b>--%>
                    <%--<a href="#">物流</a>--%>
                <%--</p>--%>
            <%--</div>--%>
            <%--<div class="footer-bd">--%>
                <%--<p>--%>
                    <%--<a href="#">关于恒望</a>--%>
                    <%--<a href="#">合作伙伴</a>--%>
                    <%--<a href="#">联系我们</a>--%>
                    <%--<a href="#">网站地图</a>--%>
                    <%--<em>© 2015-2025 Hengwang.com 版权所有</em>--%>
                <%--</p>--%>
            <%--</div>--%>
        <%--</div>--%>
    </div>

    <aside class="menu">
        <ul>
            <li class="person active">
                <a href="/person"><i class="am-icon-user"></i>个人中心</a>
            </li>
            <li class="person">
                <p><i class="am-icon-newspaper-o"></i>个人资料</p>
                <ul>
                    <li><a href="/person/information">个人信息</a></li>
                    <li><a href="/person/password">修改密码</a></li>
                    <li><a href="/person/address">地址管理</a></li>
                </ul>
            </li>
            <li class="person">
                <p><i class="am-icon-balance-scale"></i>我的交易</p>
                <ul>
                    <li><a href="/person/order">订单管理</a></li>
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

</body>

<script type="text/javascript">
    var modifyAddressTitle = "<div class=\"am-fl am-cf\"><strong class=\"am-text-danger am-text-lg\">修改地址</strong> / <small>Modify&nbsp;address</small></div>\n";
    var addAddressTitle = "<div class=\"am-fl am-cf\"><strong class=\"am-text-danger am-text-lg\">新增地址</strong> / <small>Add&nbsp;address</small></div>\n";

    //获取省、市、区select节点
    var province = $("#province");
    var city = $("#city");
    var district = $("#district");
    var provinceName = $("#provinceName");
    var cityName = $("#cityName");
    var districtName = $("#districtName");
    var code = {"code": "0"};
    city.empty();
    district.empty();
    city.append("<option value='0'>选择市</option>");
    district.append("<option value='0'>选择区</option>");
    $.ajax({
        url: "/common/city", data: code, success: function (result) {
            var jsonData = JSON.parse(result);
            province.empty();
            $.each(jsonData, function (i) {
                province.append("<option value='" + jsonData[i].code + "'>" + jsonData[i].name + "</option>");
            });
        }
    });
    //选择省后，添加市（如果点“选择省”，清空市区）（如果没查到对应的市，默认填充为“选择市”）
    province.change(function () {
            //先清空市、区
            city.empty();
            district.empty();
            city.append("<option value='value'>选择市</option>");
            district.append("<option value='value'>选择区</option>");
            var p = province.find("option:selected").text();
            var value = province.find("option:selected").val();
            provinceName.val(p);
            var code = {"code": value};
            $.ajax({
                url: "/common/city",
                data: code,
                success: function (result) {
                    var jsonData = JSON.parse(result);
                    city.empty();
                    $.each(jsonData, function (i) {
                        city.append("<option value='" + jsonData[i].code + "'>" + jsonData[i].name + "</option>");
                    });
                    var c = city.find("option:selected").text();
                    cityName.val(c);
                }
            });

        }
    );
    province.click(function () {
            //先清空市、区
            city.empty();
            district.empty();
            city.append("<option value='value'>选择市</option>");
            district.append("<option value='value'>选择区</option>");
            var p = province.find("option:selected").text();
            var value = province.find("option:selected").val();
            provinceName.val(p);
            var code = {"code": value};
            $.ajax({
                url: "/common/city",
                data: code,
                success: function (result) {
                    var jsonData = JSON.parse(result);
                    city.empty();
                    $.each(jsonData, function (i) {
                        city.append("<option value='" + jsonData[i].code + "'>" + jsonData[i].name + "</option>");

                    });
                    var c = city.find("option:selected").text();
                    cityName.val(c);
                }
            });

        }
    );
    city.click(function () {
        var c = city.find("option:selected").text();
        var value = city.find("option:selected").val();
        cityName.val(c);
        var code = {"code": value};
        $.ajax({
            url: "/common/city",
            data: code,
            success: function (result) {
                var jsonData = JSON.parse(result);
                district.empty();
                $.each(jsonData, function (i) {
                    district.append("<option value='" + jsonData[i].code + "'>" + jsonData[i].name + "</option>");
                });
                var d = district.find("option:selected").text();
                districtName.val(d);
            }
        });


    });
    //选择市后，添加区（如果点“选择市”，清空区）（如果没查到对应的区，默认填充为“选择区”）
    city.change(function () {
        var c = city.find("option:selected").text();
        var value = city.find("option:selected").val();
        cityName.val(c);
        var code = {"code": value};
        $.ajax({
            url: "/common/city",
            data: code,
            success: function (result) {
                var jsonData = JSON.parse(result);
                district.empty();
                $.each(jsonData, function (i) {
                    district.append("<option value='" + jsonData[i].code + "'>" + jsonData[i].name + "</option>");
                });
                var d = district.find("option:selected").text();
                districtName.val(d);
            }
        });

    });
    district.change(function () {
        var d = district.find("option:selected").text();
        districtName.val(d);
    });
    district.click(function () {
        var d = district.find("option:selected").text();
        districtName.val(d);
    });
    function delAddress(address) {
        var addressId = $(address).attr("addressId");
        var code = {"id": addressId};
        $.ajax({
            url: "/person/address",
            data: JSON.stringify(code),
            type: "DELETE",
            dataType:"json",
            contentType : "application/json;charset=utf-8",
//            contentType : "application/x-www-form-urlencoded",
//            Form Data
//            name:
//            phone:
//        provinceCode: 140000
//        province: 山西省
//        cityCode: 140200
        success: function (result) {
                $("#address"+addressId).remove()
                alert(result)
            }
        });
    }
    function modifyAddress(address) {
        var addressId = $(address).attr("addressId");
        $("#addressTitle").empty();
        $("#addressTitle").append(modifyAddressTitle);
        $("#addressId").val(addressId);
        $("#user-name").val($("#name-"+addressId).text());
        $("#user-phone").val($("#phone-"+addressId).text());
        $("#user-intro").val($("#details-"+addressId).text());
        $("#province").val($("#province-"+addressId).attr("province"));
//        $("#city").val($("#city-"+addressId).attr("city"));
//        $("#district").val($("#district-"+addressId).attr("district"));
    }
    function clearAddress() {
        $("#addressTitle").empty();
        $("#addressTitle").append(addAddressTitle);
        $("#addressId").val("");
        $("#user-name").val("");
        $("#user-phone").val("");
        $("#user-intro").val("");
        $("#province").val("110000");
//        $("#city").val($("#city-"+addressId).attr("city"));
//        $("#district").val($("#district-"+addressId).attr("district"));
    }
    function defaultAddress(address) {
        var addressId = $(address).attr("addressId");
        var code = {"id": addressId,"isDefault":1};
        $.ajax({
            url: "/person/address",
            data: JSON.stringify(code),
            type: "PUT",
            dataType:"json",
            contentType : "application/json;charset=utf-8",
//            contentType : "application/x-www-form-urlencoded",
//            Form Data
//            name:
//            phone:
//        provinceCode: 140000
//        province: 山西省
//        cityCode: 140200
        success: function (result) {
//                $("#address"+addressId).remove()
//                alert(result)
            }
        });
    }
</script>
</html>