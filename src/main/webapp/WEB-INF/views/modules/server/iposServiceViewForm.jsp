<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>终端录入</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {

        });
    </script>
</head>
<body>
<form:form name="addTerminalFrom" modelAttribute="posServiceInfo" action="${ctx}/iposService/modifyService"
           method="post" class="form-horizontal">
    <div class="control-group">
        <label class="control-label">服务编码:</label>

        <div class="controls">
            <input name="bizCode" maxlength="50" class="required" value="${posServiceInfo.bizCode}"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">服务名称:</label>

        <div class="controls">
            <input name="bizName" htmlEscape="false" maxlength="50" class="required" value="${posServiceInfo.bizName}"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">时间范围:</label>

        <div class="controls">
            <input name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
                   value="<fmt:formatDate value='${posServiceInfo.beginDate}' pattern='yyyy-MM-dd'/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
            --
            <input name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
                   value="<fmt:formatDate value='${posServiceInfo.endDate}' pattern='yyyy-MM-dd'/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">允许交易:</label>

        <div class="controls">
            <form:select path="allowTrade" class="input-medium required">
                <form:option value="1">允许交易</form:option>
                <form:option value="2">不允许交易</form:option>
            </form:select>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">业务系统:</label>

        <div class="controls">
            <form:select path="bizSystem" class="input-medium required">
                <form:option value="2">智能POS</form:option>
                <form:option value="1">手机MPOS</form:option>
            </form:select>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">业务类型:</label>

        <div class="controls">
            <form:select path="bizType" class="input-medium required">
                <form:option value="1">T+1</form:option>
                <from:option value="3">微信</from:option>
                <form:option value="4">钱包生活</form:option>
                <from:option value="5">T+0</from:option>
            </form:select>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">备注:</label>

        <div class="controls">
            <textarea name="remarks" rows="3">${posServiceInfo.remarks}</textarea>
        </div>
    </div>
    <div class="text-center">
        <from:hidden path="id"/>
        <shiro:hasPermission name="ipos:sysserver:modify">
            <input id="btn" class="btn btn-primary" type="submit" value="保存"/>&nbsp;&nbsp;
        </shiro:hasPermission>
        <input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="self.location=document.referrer;"/>
    </div>
</form:form>
</body>
</html>
