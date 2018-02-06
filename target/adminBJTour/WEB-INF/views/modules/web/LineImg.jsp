<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>路线图片</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/act/rest/diagram-viewer/js/ajaxfileupload.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
        	<c:forEach items="${LinePicList}" var="item">
				$("input:checkbox[value='${item.picNo}']").attr('checked','true');
			</c:forEach>
        });

        function returnBtn() {
            location.href = "${ctx}/line/lineList";
        }

        function btnSubmitClick() {
            $("#linePicFrom").submit();
        }
        
        function importPic() {
        	var lineNo = ${lineNo};
        	$.ajaxFileUpload({  
		        url : "${ctx}/line/importPic",  
		        type: "POST",  
		        data:{"lineNo":lineNo},
		        secureuri : false,//是否启用安全提交，默认为false  
		        fileElementId:'FileUpload',//文件选择框的id属性  
		        dataType: 'json',
		        async : false,  
		        success: function(data){
		        	alert('111');
		        	alertx(data.result);
			        window.location.reload(); 
		        } 
		    });  
		    return false;  
        }
        
        function showPic(picUrl){
        	var frontUrl = "${ctx}";
        	window.open(frontUrl.slice(0,frontUrl.length-1)+picUrl);
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/line/lineList">路线列表</a></li>
    <li><a href="${ctx}/line/form/${lineNo}">路线详情</a></li>
    <li class="active"><a href="#">路线图片</a></li>
    <li><a href="${ctx}/line/addition/${lineNo}">路线附加项</a></li>
    <li><a href="${ctx}/line/price/${lineNo}">路线价格</a></li>
</ul>

<form id="linePicFrom" action="${ctx}/line/updateLinePic/${lineNo}" method="post" enctype="multipart/form-data">
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
                    <div class="btns" style="float: right">
					 <input type="file" name="myFile" id="FileUpload" style="display:none" onchange="importPic();">
		             <a class="btn btn-primary" id="btn_uploadfile" onclick="$('#FileUpload').click();">新增图片</a>
					 </div>
                </div>
            </div>
			<div class="row-fluid">
		        <label>图片列表：</label><br/>
	         	<div id="bossDiv" style="border:1px solid #ddd; margin-right:250px; float: left">
	        		<%--<input id="checkedAll"  type="checkbox"/><label style="cursor: pointer">全选</label> --%>
	         	<table id="bossTable" border="1px" cellpadding="8" cellspacing="0">
	         		<thead>
	          		<tr>
	          			<th>选择</th>
	          			<th>图片编号</th>
	          			<th>图片名称</th>
	          			<th>是否为封面图</th>
	          			<th>预览</th>
	          		</tr>
	         		</thead>
	         		<tbody>
	         			 <c:forEach items="${allPicList}" var="pic" >
						    <input type="hidden" name="id" value="${pic.id}">
	            			<tr>
	            				<td><input id="bossTd" type="checkbox" name="picNo" value="${pic.picNo}"/></td>
								<td>${pic.picNo}</td>
								<td>${pic.picName}</td>	                    			
								<td>
									<input id="isMain" type="radio" name="isMain${pic.picNo}" <c:if test="${pic.isMain eq '1'}"> checked="checked"</c:if> value="1" />是
				                	&nbsp;&nbsp;&nbsp;
				                	<input id="isNotMain" type="radio" name="isMain${pic.picNo}" <c:if test="${pic.isMain eq '0'}"> checked="checked"</c:if> value="0"/>否
								</td>	                    			
								<td><a href="javascript:void(0)" onclick="showPic('${pic.fileUrl}');">${pic.fileUrl}</a></td>
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
