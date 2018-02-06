<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>路线图片</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
        	<c:forEach items="${lineAddList}" var="item">
				$("input:checkbox[value='${item.addNo}']").attr('checked','true');
			</c:forEach>
        });

        function returnBtn() {
            location.href = "${ctx}/line/lineList";
        }

        function btnSubmitClick() {
            $("#lineAddFrom").submit();
        }

		function addAddition(url){
			$.jBox("iframe:"+url, {
				title: "新增附加项",
				width: 670,
				height: 380,
				showScrolling : false,
				iframeScrolling: 'no',
				buttons: { '返回': true },
				closed: function () {
					window.location.href='${ctx}/line/addition/${lineNo}';
				}
			});
		}
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/line/lineList">路线列表</a></li>
    <li><a href="${ctx}/line/form/${lineNo}">路线详情</a></li>
    <li><a href="${ctx}/line/img/${lineNo}">路线图片</a></li>
    <li class="active"><a href="#">路线附加项</a></li>
    <li><a href="${ctx}/line/price/${lineNo}">路线价格</a></li>
</ul>

<form id="lineAddFrom" action="${ctx}/line/updateLineAdd/${lineNo}" method="post" enctype="multipart/form-data">
    <sys:message content="${message}"/>
    <input type="hidden" name="lineNo" value="${lineNo}">
    <div class="container-fluid">
        <fieldset>
            <div class="row-fluid">
                <div class="span10">
                    <label>线路全称：</label>&nbsp;&nbsp;&nbsp;
                    ${lineName}
                </div>
                <div class="span2">
                    <a href="#" onclick="addAddition('${ctx}/line/ForwardAddAddition')" class="btn btn-primary">新增附加项</a>
                </div>
            </div>
			<div class="row-fluid">
		        <label>附加项列表：</label><br/>
	         	<div id="bossDiv" style="border:1px solid #ddd; margin-right:250px; float: left">
	         	<table id="bossTable" border="1px" cellpadding="8" cellspacing="0">
	         		<thead>
	          		<tr>
	          			<th>选择</th>
	          			<th>附加项编号</th>
	          			<th>附加项名称</th>
	          			<th>操作</th>
	          		</tr>
	         		</thead>
	         		<tbody>
	         			 <c:forEach items="${allAddList}" var="add" >
						    <input type="hidden" name="id" value="${add.id}">
	            			<tr>
	            				<td><input id="bossTd" type="checkbox" name="addNo" value="${add.addNo}"/></td>
								<td>${add.addNo}</td>
								<td>
									<input name="addValue${add.addNo}" class="input-medium required" type="text" 
										value="${add.addValue}"/></td>	                    			
								<td>
									<a href="${ctx}/line/addition/del/${lineNo}/${add.addNo}">删除</a>
								</td>	                    			
	            			</tr>
	         			 </c:forEach> 
	         		</tbody>
	         	</table>
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
