<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>路线价钱</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
			
        });

        function returnBtn() {
            location.href = "${ctx}/line/lineList";
        }

        function btnSubmitClick() {
            $("#linePriceFrom").submit();
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/line/lineList">路线列表</a></li>
    <li><a href="${ctx}/line/form/${lineNo}">路线详情</a></li>
    <li><a href="${ctx}/line/pic/${lineNo}">路线图片</a></li>
    <li><a href="${ctx}/line/addition/${lineNo}">路线附加项</a></li>
    <li class="active"><a href="#">路线价格</a></li>
</ul>

<from:form id="linePriceFrom" modelAttribute="tLinePriceDto" action="${ctx}/line/updateLinePrice" method="post">
    <sys:message content="${message}"/>
    <input type="hidden" name="lineNo" value="${lineNo}">
    <input type="hidden" name="id" value="${id}">
    <div class="container-fluid">
        <fieldset>
            <div class="row-fluid">
                <div class="span12">
                    <label>线路全称：</label>&nbsp;&nbsp;&nbsp;
                    ${lineName}
                </div>
            </div>
			<div class="row-fluid">
                <div class="span2">
                    <label>1人：</label>&nbsp;&nbsp;&nbsp;
                    <form:input path="oneP" type="text" class="input-mini required"/>
                </div>
                <div class="span2">
                    <label>2人：</label>&nbsp;&nbsp;&nbsp;
                    <form:input path="twoP" type="text" class="input-mini required"/>
                </div>
                <div class="span2">
                    <label>3人：</label>&nbsp;&nbsp;&nbsp;
                    <form:input path="threeP" type="text" class="input-mini required"/>
                </div>
                <div class="span2">
                    <label>4人：</label>&nbsp;&nbsp;&nbsp;
                    <form:input path="fourP" type="text" class="input-mini required"/>
                </div>
                <div class="span2">
                    <label>5人：</label>&nbsp;&nbsp;&nbsp;&nbsp;
                    <form:input path="fiveP" type="text" class="input-mini required"/>
                </div>
            </div>
			<div class="row-fluid">
                <div class="span2">
                    <label>6人：</label>&nbsp;&nbsp;&nbsp;
                    <form:input path="sixP" type="text" class="input-mini required"/>
                </div>
                <div class="span2">
                    <label>7人：</label>&nbsp;&nbsp;&nbsp;
                    <form:input path="sevenP" type="text" class="input-mini required"/>
                </div>
                <div class="span2">
                    <label>8人：</label>&nbsp;&nbsp;&nbsp;
                    <form:input path="eightP" type="text" class="input-mini required"/>
                </div>
                <div class="span2">
                    <label>9人：</label>&nbsp;&nbsp;&nbsp;
                    <form:input path="nineP" type="text" class="input-mini required"/>
                </div>
                <div class="span2">
                    <label>10人：</label>&nbsp;&nbsp;&nbsp;
                    <form:input path="tenP" type="text" class="input-mini required"/>
                </div>
            </div>
        </fieldset>
        <div class="text-center">
            <input id="btnSubmit" class="btn btn-primary" type="button" onclick="btnSubmitClick();" value="保存"/>&nbsp;&nbsp;
            <input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="returnBtn();"/>
        </div>
    </div>
</from:form>
</body>

</html>
