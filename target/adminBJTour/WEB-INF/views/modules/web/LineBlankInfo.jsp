<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>路线详情</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {

        });

        function returnBtn() {
            location.href = "${ctx}/line/lineList";
        }

        function btnSubmitClick() {
        	var lineName = $("input[name='lineName']").val();
        	if(lineName == null || lineName == ""){
         		alertx("请填写路线名称");
         		return;
        	}else{
	            $("#lineBlankInfo").submit();
        	}
        }
    </script>
</head>
<body>
<form id="lineBlankInfo" name="lineBlankInfo"action="${ctx}/line/saveLine" method="post" enctype="multipart/form-data"> 
    <sys:message content="${message}"/>
    <input type="hidden" name="lineNo" value="${lineNo}">
    <div class="container-fluid">
        <fieldset>
            <legend>新增路线信息</legend>
            <div class="row-fluid">
                <div class="span8">
                    <label>线路全称：</label>&nbsp;&nbsp;&nbsp;
                    <input name="lineName" class="input-xxlarge required" type="text" style="width:590px"/>
                </div>
                <div class="span4">
                    <label>线路简称：</label>&nbsp;&nbsp;&nbsp;
                    <input name="lineNameShort" type="text" class="input-xlarge required" style="width:250px"/>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span4">
                    <label>可预订时段：</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input name="available" type="text" class="input-medium required"/>
                </div>
                <div class="span4">
                    <label>持续时长：</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input name="duration" type="text" class="input-medium required"/>
                </div>
                <div class="span4">
                    <label>预留时间：</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input name="layoverTime" type="text" class="input-medium required"/>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span4">
                    <label>线路类型：</label>&nbsp;&nbsp;&nbsp;
                	<select id="lineTypeNo" name="lineTypeNo" class="input-xlarge required" style="width:250px">
                    <c:forEach items="${fns:getDictList('lineType')}" var="industry">
                        <option value="${industry.value}">${industry.label}</option>
                    </c:forEach>
                    </select>
                    <span class="help-inline"><font color="red">*</font></span>
                </div>
                <div class="span4">
                    <label>路线承载点：</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <select id="pickUpPoint" name="pickUpPoint" class="input-medium required">
                    <c:forEach items="${fns:getDictList('pickDropPoint')}" var="industry">
                        <option value="${industry.value}">${industry.label}</option>
                    </c:forEach>
                </select>
                </div>
                <div class="span4">
                    <label>路线结束点：</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <select id="finishPoint" name="finishPoint" class="input-medium required">
                    <c:forEach items="${fns:getDictList('pickDropPoint')}" var="industry">
                        <option value="${industry.value}">${industry.label}</option>
                    </c:forEach>
                    </select>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span4">
                    <label>建议开始时间：</label>&nbsp;&nbsp;&nbsp;&nbsp;
                    <input name="pickUpTime" type="text" class="input-medium required"/>
                </div>
                <div class="span4">
                    <label>建议结束时间：</label>&nbsp;&nbsp;&nbsp;&nbsp;
                    <input name="endTime" type="text" class="input-medium required"/>
                </div>
                <div class="span4">
                    <label>物理强度：</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <select id="phyLevel" name="phyLevel" class="input-medium required">
                    <c:forEach items="${fns:getDictList('phyLevel')}" var="industry">
                        <option value="${industry.value}">${industry.label}</option>
                    </c:forEach>
                    </select>
                </div>
            </div>
            <div class="row-fluid">
            	<div class="span3">
                    <label>适用语种：</label>&nbsp;&nbsp;&nbsp;&nbsp;
                    <input name="language" type="text" class="input-small required"/>
                </div>
                <div class="span3">
                    <label>是否定制：</label>&nbsp;&nbsp;&nbsp;
                    <input id="customizable" type="radio" name="customizable" value="1" />否
                	&nbsp;&nbsp;&nbsp;&nbsp;
                	<input id="unCustomizable" type="radio" name="customizable" value="0"/>是
                </div>
                <div class="span3">
                    <label>是否私人游：</label>&nbsp;&nbsp;&nbsp;
                    <input id="isPrivate" type="radio" name="isPrivate" value="1" />否
                	&nbsp;&nbsp;&nbsp;&nbsp;
                	<input id="notPrivate" type="radio" name="isPrivate" value="0"/>是
                </div>
                <div class="span3">
                    <label>徒步距离：</label>&nbsp;&nbsp;&nbsp;
                    <input name="hikingDis" class="input-medium required" type="text"/>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span5">
                    <label>徒步区域：</label>&nbsp;&nbsp;&nbsp;&nbsp;
                    <input name="hikingArea" type="text" class="input-xlarge required" style="width:310px"/>
                </div>
                <div class="span7">
                    <label>吸引力：</label>&nbsp;&nbsp;&nbsp;
                    <input name="attractions" type="text" class="input-xxlarge required" style="width:550px"/>
                </div>
                
            </div>
            <div class="row-fluid">
                <div class="span12">
                    <label>线路详情：</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <textarea id="detail" name="detail" rows="10" class="input-xxxlarge required" form="lineBlankInfo"></textarea>
                </div>
            	
            </div>
        </fieldset>
        <div class="text-center">
            <input id="btnSubmit" class="btn btn-primary" type="button" onclick="btnSubmitClick();" value="保存"/>&nbsp;&nbsp;
            <input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="returnBtn();"/>
        </div>
    </div>
</form>
</body>

</html>
