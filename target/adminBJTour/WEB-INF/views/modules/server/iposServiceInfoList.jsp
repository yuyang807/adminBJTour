<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>服务管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
        });
        function page(n, s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
    </script>
</head>
<body>
<form:form id="searchForm" modelAttribute="posServiceInfo" action="${ctx}/iposService/list" method="post"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>业务系统：</label>
                <%--<form:input path="merchantCode" htmlEscape="false" maxlength="32" class="input-medium"/>--%>
            <form:select path="bizSystem" cssClass="input-medium">
                <form:option value="">请选择</form:option>
                <form:option value="2">智能POS</form:option>
                <form:option value="1">手机MPOS</form:option>
            </form:select>
        </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="搜索"/></li>
        <shiro:hasPermission name="ipos:sysserver:add">
            <li class="btns"><a href="${ctx}/iposService/addFrom" class="btn btn-primary">新增</a></li>
        </shiro:hasPermission>
        <li class="clearfix"></li>
    </ul>
    <sys:message content="${message}"/>
</form:form>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>序号</th>
        <th>服务编号</th>
        <th>服务名称</th>
        <th>业务系统</th>
        <th>是否允许交易</th>
        <th>开始时间</th>
        <th>结束时间</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="sevrice" varStatus="status">
        <tr>
            <td>${status.count}</td>
            <td>${sevrice.bizCode}</td>
            <td>${sevrice.bizName}</td>
            <td>
                <c:choose>
                    <c:when test="${sevrice.bizSystem eq '2'}">
                        智能POS
                    </c:when>
                    <c:otherwise>
                        手机MPOS
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${sevrice.allowTrade eq '1'}">
                        允许交易
                    </c:when>
                    <c:otherwise>
                        不允许交易
                    </c:otherwise>
                </c:choose>
            </td>
            <td><fmt:formatDate value="${sevrice.beginDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td><fmt:formatDate value="${sevrice.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td>
                <a href="${ctx}/iposService/viewFrom/${sevrice.id}">修改</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>