<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>线路管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnSubmit").click(function(){
				$("#pageNo").val(1);
			})
		});
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#iposMerchantList").submit();
        	return false;
        }
		function resetForm(){
			$(".input-medium").val("");
			$("#s2id_belongType span.select2-chosen").text("全部");
			$("#s2id_delFlag span.select2-chosen").text("全部");
			$("#belongAll").attr("selected","selected");
			$("#flagFine").attr("selected","selected");
		}
	</script>
</head>
<body>
	<form:form id="lineList" modelAttribute="TLineDto"  action="${ctx}/line/listLine" method="post" class="breadcrumb form-search">
	  	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>线路编号：</label>
				<form:input path="lineNo" htmlEscape="false" maxlength="12" class="input-mini"/>
			</li>
			<li><label>线路名称：</label>
			    <form:input path="lineName" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>线路类型：</label>
	            <form:select path="lineTypeNo" htmlEscape="false" class="input-xlarge">
	                <form:option value="" htmlEscape="false">所有</form:option>
	                <form:options items="${fns:getDictList('lineType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
	            </form:select>
        	</li>
			<li class="btns" style="float: right"><a href="${ctx}/line/rediectLineBlankInfo" class="btn btn-primary">新增</a></li>
			<li class="btns" style="float: right"><input id="btnreset" class="btn btn-primary" type="button" onclick="resetForm()"value="重置"/></li>
			<li class="btns" style="float: right"><input id="btnSubmit" class="btn btn-primary" type="submit" value="搜索"/></li>
			<li class="clearfix"></li>
		</ul>
		
		<sys:message content="${message}"/>
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>线路编号</th>
				<th>线路简称</th>
				<th>线路类型</th>
				<th>可预订时段</th>
				<th>持续时长</th>
				<th>是否私人游</th>
				<th>线路承载点</th>
				<th>结束点</th>
				<th>建议开始时间</th>
				<th>结束时间</th>
				<th>可定制</th>
				<th>预留时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody class="tbody_name">
		 <c:forEach items="${page.list}" var="lineDto" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td>${lineDto.lineNo}</td>
			    <td>${lineDto.lineNameShort}</td>
				<td>
					${fns:getDictLabel(lineDto.lineTypeNo,"lineType","")}
				</td>
				<td>${lineDto.available}</td>
				<td>${lineDto.duration}</td>
				<td>
					<c:choose>
						<c:when test="${lineDto.isPrivate == 0 }">
							是
						</c:when>
						<c:when test="${lineDto.isPrivate == 1 }">
							否 
						</c:when>
					</c:choose>
				</td> 
				<td>
					<c:choose>
						<c:when test="${lineDto.pickUpPoint == 0 }">
							旅馆
						</c:when>
						<c:when test="${lineDto.pickUpPoint == 1 }">
							机场
						</c:when>
						<c:when test="${lineDto.pickUpPoint == 2 }">
							自定义 
						</c:when>
					</c:choose>
				</td> 
				<td>
					<c:choose>
						<c:when test="${lineDto.finishPoint == 0 }">
							旅馆
						</c:when>
						<c:when test="${lineDto.finishPoint == 1 }">
							机场
						</c:when>
						<c:when test="${lineDto.finishPoint == 2 }">
							自定义
						</c:when>
					</c:choose>
				</td> 
				<td>${lineDto.pickUpTime}</td>
				<td>${lineDto.endTime}</td>
				<td>
					<c:choose>
						<c:when test="${lineDto.isPrivate == 0 }">
							可以
						</c:when>
						<c:when test="${lineDto.isPrivate == 1 }">
							不可以
						</c:when>
					</c:choose>
				</td>
				<td>${lineDto.layoverTime}</td>
				<td>
    				<a href="${ctx}/line/form/${lineDto.lineNo}">详情</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>