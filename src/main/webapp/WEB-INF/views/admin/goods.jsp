<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品管理</title>
</head>
<body>
<form id="addGoodsForm" class="am-form am-form-horizontal" action="/admin/goods" method="post">

<%--</form>--%>
<%--<form class="am-form tjlanmu">--%>
    <div class="am-form-group">
        <div class="zuo">栏目名称：</div>
        <div class="you">
            <input type="email" class="am-input-sm" id="doc-ipt-email-1" placeholder="请输入标题">
        </div>
    </div>
    <div class="am-form-group">
        <div class="zuo">栏目关键词：</div>
        <div class="you">
            <input type="password" class="am-input-sm" id="doc-ipt-pwd-1" placeholder="请输入关键词">
        </div>
    </div>
    <div class="am-form-group am-cf">
        <div class="zuo">栏目描述：</div>
        <div class="you">
            <textarea class="" rows="2" id="doc-ta-1"></textarea>
        </div>
    </div>
    <div class="am-form-group am-cf">
        <div class="zuo">栏目图片：</div>
        <div class="you" style="height: 45px;">
            <input type="file" id="doc-ipt-file-1">
            <p class="am-form-help">请选择要上传的文件...</p>
        </div>
    </div>
    <div class="am-form-group am-cf">
        <div class="zuo">简介：</div>
        <div class="you">
            <textarea class="" rows="2" id="doc-ta-1"></textarea>
        </div>
    </div>
    <div class="am-form-group am-cf">
        <div class="zuo">状态：</div>
        <div class="you" style="margin-top: 3px;">
            <label class="am-checkbox-inline">
                <input type="checkbox" value="option1">
                显示 </label>
            <label class="am-checkbox-inline">
                <input type="checkbox" value="option2">
                隐藏 </label>
        </div>
    </div>
    <div class="am-form-group am-cf">
        <div class="you">
            <p>
                <button type="submit" class="am-btn am-btn-success am-radius">提交</button>
            </p>
        </div>
    </div>
</form>
</body>
</html>
