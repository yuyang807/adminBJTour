<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>路线详情</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $(":text").attr("disabled", "true");
            $("select").attr("disabled", "true");
            $("#detail").attr("disabled", "true");
            $("#btnSubmit").attr("disabled", "true");

            $(":submit").attr("disabled", "true");
            $("#btnUpdate").click(function () {
                $(":text").removeAttr("disabled");
                $("select").removeAttr("disabled");
                $("#detail").removeAttr("disabled");
                $(":submit").removeAttr("disabled");
                $("#btnSubmit").removeAttr("disabled");
             });
            
            $("#posMerchantFrom").validate({
                
            });

        });

        function returnBtn() {
            location.href = "${ctx}/line/lineList";
        }

        function btnSubmitClick() {//hqMerchantCode 获取jobx的商户号
            $("#lineFrom").submit();
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/line/lineList">路线列表</a></li>
    <li class="active"><a href="#">路线详情</a></li>
    <li><a href="${ctx}/line/pic/${lineNo}">路线图片</a></li>
    <li><a href="${ctx}/line/addition/${lineNo}">路线附加项</a></li>
    <li><a href="${ctx}/line/price/${lineNo}">路线价格</a></li>
</ul>

<from:form id="lineFrom" modelAttribute="lineDto" action="${ctx}/line/updateLine" method="post">
    <sys:message content="${message}"/>
    <from:hidden path="id"/>
    <from:hidden path="lineNo"/>
    <div class="container-fluid">
        <fieldset>
            <legend>路线信息</legend>
            <div class="row-fluid">
                <div class="span8">
                    <label>线路全称：</label>&nbsp;&nbsp;&nbsp;
                    <form:input path="lineName" type="text" class="input-xxlarge required" style="width:590px"/>
                </div>
                <div class="span4">
                    <label>线路简称：</label>&nbsp;&nbsp;&nbsp;
                    <form:input path="lineNameShort" type="text" class="input-xlarge required" style="width:250px"/>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span4">
                    <label>可预订时段：</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <form:input path="available" type="text" class="input-medium required"/>
                </div>
                <div class="span4">
                    <label>持续时长：</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <form:input path="duration" type="text" class="input-medium required"/>
                </div>
                <div class="span4">
                    <label>预留时间：</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <form:input path="layoverTime" type="text" class="input-medium required"/>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span4">
                    <label>线路类型：</label>&nbsp;&nbsp;&nbsp;
                	<select id="lineTypeNo" name="lineTypeNo" class="input-xlarge required" style="width:250px">
                    <c:forEach items="${fns:getDictList('lineType')}" var="industry">
                        <option
                                <c:if test="${industry.value eq  lineDto.lineTypeNo}">selected="selected"</c:if>
                                value="${industry.value}">${industry.label}</option>
                    </c:forEach>
                    </select>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
                <div class="span4">
                    <label>路线承载点：</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <select id="pickUpPoint" name="pickUpPoint" class="input-medium required">
                    <c:forEach items="${fns:getDictList('pickDropPoint')}" var="industry">
                        <option
                                <c:if test="${industry.value eq  lineDto.pickUpPoint}">selected="selected"</c:if>
                                value="${industry.value}">${industry.label}</option>
                    </c:forEach>
                </select>
                </div>
                <div class="span4">
                    <label>路线结束点：</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <select id="finishPoint" name="finishPoint" class="input-medium required">
                    <c:forEach items="${fns:getDictList('pickDropPoint')}" var="industry">
                        <option
                                <c:if test="${industry.value eq  lineDto.finishPoint}">selected="selected"</c:if>
                                value="${industry.value}">${industry.label}</option>
                    </c:forEach>
                    </select>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span4">
                    <label>建议开始时间：</label>&nbsp;&nbsp;&nbsp;&nbsp;
                    <form:input path="pickUpTime" type="text" class="input-medium required"/>
                </div>
                <div class="span4">
                    <label>建议结束时间：</label>&nbsp;&nbsp;&nbsp;&nbsp;
                    <form:input path="endTime" type="text" class="input-medium required"/>
                </div>
                <div class="span4">
                    <label>物理强度：</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <select id="phyLevel" name="phyLevel" class="input-medium required">
                    <c:forEach items="${fns:getDictList('phyLevel')}" var="industry">
                        <option
                                <c:if test="${industry.value eq  lineDto.phyLevel}">selected="selected"</c:if>
                                value="${industry.value}">${industry.label}</option>
                    </c:forEach>
                    </select>
                </div>
            </div>
            <div class="row-fluid">
            	<div class="span3">
                    <label>适用语种：</label>&nbsp;&nbsp;&nbsp;&nbsp;
                    <form:input path="language" type="text" class="input-small required"/>
                </div>
                <div class="span3">
                    <label>是否定制：</label>&nbsp;&nbsp;&nbsp;
                    <input id="customizable" type="radio" name="customizable" <c:if test="${lineDto.customizable eq '1'}"> checked="checked"</c:if> value="1" />否
                	&nbsp;&nbsp;&nbsp;&nbsp;
                	<input id="unCustomizable" type="radio" name="customizable" <c:if test="${lineDto.customizable eq '0'}"> checked="checked"</c:if> value="0"/>是
                </div>
                <div class="span3">
                    <label>是否私人游：</label>&nbsp;&nbsp;&nbsp;
                    <input id="isPrivate" type="radio" name="isPrivate" <c:if test="${lineDto.isPrivate eq '1'}"> checked="checked"</c:if> value="1" />否
                	&nbsp;&nbsp;&nbsp;&nbsp;
                	<input id="notPrivate" type="radio" name="isPrivate" <c:if test="${lineDto.isPrivate eq '0'}"> checked="checked"</c:if> value="0"/>是
                </div>
                <div class="span3">
                    <label>徒步距离：</label>&nbsp;&nbsp;&nbsp;
                    <%--<form:input path="hikingDis" type="text" class="input-medium required"/> --%>
                    <input name="hikingDis" class="input-medium required" type="text"
                           value="${lineDto.hikingDis}"/>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span5">
                    <label>徒步区域：</label>&nbsp;&nbsp;&nbsp;&nbsp;
                    <form:input path="hikingArea" type="text" class="input-xlarge required" style="width:310px"/>
                </div>
                <div class="span7">
                    <label>吸引力：</label>&nbsp;&nbsp;&nbsp;
                    <form:input path="attractions" type="text" class="input-xxlarge required" style="width:550px"/>
                </div>
                
            </div>
            <div class="row-fluid">
                <div class="span12">
                    <label>线路详情：</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <from:textarea id="detail" path="detail" rows="10" class="input-xxxlarge required"/>
                </div>
            	
            </div>
        </fieldset>
        <div class="text-center">
            <input id="btnUpdate" class="btn btn-primary" type="button" value="修改"/>&nbsp;&nbsp;
            <input id="btnSubmit" class="btn btn-primary" type="button" onclick="btnSubmitClick();" value="保存"/>&nbsp;&nbsp;
            <input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="returnBtn();"/>
        </div>
    </div>
</from:form>
</body>

</html>
