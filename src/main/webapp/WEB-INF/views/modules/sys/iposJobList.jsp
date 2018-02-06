<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>手动发送定时任务</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$.cookie('the_cookie', 'the_value', { expires: 7 }); 
			$("#btnSubmit").click(function(){
				$("#pageNo").val(1);
			});
		});
		$(function(){
				/*防刷新：检测是否存在cookie*/
	            if($.cookie("captcha")){
	                var count = $.cookie("captcha");
	                var btn = $('#jobSend');
	                btn.val(count+'秒后可重新发送').attr('disabled',true).css('cursor','not-allowed');
	                var resend = setInterval(function(){
	                    count--;
	                    if (count > 0){
	                        btn.val(count+'秒后可重新获取').attr('disabled',true);
	                        $.cookie("captcha", count, {path: '/', expires: (1/86400)*count});
	                    }else {
	                        clearInterval(resend);
	                        btn.val("发送对账单").removeClass('disabled').removeAttr('disabled');
	                    }
	                }, 1000);
	            }
				
	    		$("#jobSend").click(function(){
	            	var count = 60;
	    			var btn = $(this);
	    			var jobName = $("#jobName").val();
	            	var jobDate = $("#jobDate").val();
	            	if(jobDate == null || jobDate == ''){
	            		alert("请选择日期");
	            		return false;
	            	}
	            	var jobDateStr = jobDate.split("-");
	            	var date = new Date();
	            	var currentY = date.getFullYear();
	            	var currentM = date.getMonth()+1;
	            	var currentD = date.getDate();
	    			var currentDate = new Date(date.getFullYear()+"/"+(date.getMonth()+1)+"/"+date.getDate());
	            	var jobDateY = jobDateStr[0];
	            	var jobDateM = jobDateStr[1];
	            	var jobDateD = jobDateStr[2];
	            	if(jobDateY >= 2016){//2017
	            		if(jobDateY == 2016 && jobDateM < 12){
	            			alertx("对账日期请选择2016年12月以后的日期！");
	                		return false;
	            		}if(jobDateY > currentY){
	            			alertx("对账日期不能大于当前日期！");
	        				return false;
	            		}else{
	            			if(jobDateY == currentY && jobDateM > currentM){
	            				alertx("对账日期不能大于当前日期！");
	            				return false;
	            			}else{
	            				if(jobDateM == currentM && jobDateD >currentD){
	            					alertx("对账日期不能大于当前日期！");
	                				return false;
	            				}
	            			}
	            		}
	    			var resend = setInterval(function(){
	    				count--;
	    				if(count > 0){
	    					btn.val(count+"秒后可以重新发送");
	    					$.cookie("captcha",count, {path:'/', expires:(1/86400)*count});
	    				}else{
	    					clearInterval(resend);
	    					btn.val("发送对账单").removeAttr("disabled");
	    				}
	    			},1000);
	    			btn.attr('disabled',true);
	    			$.post(
	         				'${ctx}/iposJob/jobSendManual',
	         				{jobName:jobName,jobDate:jobDate},
	         				function(retData){
	         					if(retData == "success"){
	         						alert('手动发送任务成功');	
	         						$("#jobDate").val('');
	         					}else if (retData == "timeout"){
	         						alertx("请选择正确的对账单日期！");
	         						$("#jobDate").val('');
	         					}else{
	         						alert('手动发送任务失败，请确认后重试！');
	         						$("#jobDate").val('');
	         					}
	         				},
	         				'text'
	         			);
	            	}else{
	            		alertx("对账日期请选择2016年12月以后的日期！");
	            		return false;
	            	}
	    		})
				}); 
		 function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#iposJobList").submit();
        	return false;
        } 
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#">手动发送定时任务</a></li>
	</ul>
	<table id="" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr  align="center">
				<th>任务日期</th>
				<th>任务名称</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
				<tr  align="center">
					<td><input id="jobDate" name="jobDate" type="text" readonly="readonly"
                                maxlength="20" class="input-large Wdate required" 
                                onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/></td>
					 <td>
						 <select id="jobName" name="jobName"  class="input-large required">
							<option value="1">对账单</option>
							<option value="2">对账单汇总</option>
					 	</select>
					 </td>
					<td>
						<input id="jobSend" class="btn" type="button" value="发送对账单"/>
					</td>
				</tr>
		</tbody>
	</table>
	<div>
	<form:form id="iposJobList" modelAttribute="posJob" action="${ctx}/iposJob/findPosJob" method="get" class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li>任务时间：
            <td><form:input path="jobDate" type="text" readonly="readonly" value="${jobDate}"
                                maxlength="20" class="input-small Wdate" 
                                onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/></td>
			<td>任务类型： <form:select path="jobType" htmlEscape="false" cssClass="input-small" >
			   				<form:option value="">所有</form:option>
							<form:option value="POS">线下通道</form:option>
							<form:option value="PAY">线上通道</form:option>
							<form:option value="qblife">钱包生活</form:option>
						</form:select>
			<td>任务状态：<form:select path="jobState"  htmlEscape="false" cssClass="input-small" >
							<form:option value="">所有</form:option>
							<form:option value="1">创建</form:option>
							<form:option value="2">执行中</form:option>
							<form:option value="3">成功</form:option>
							<form:option value="4">失败</form:option>
						</form:select>
			<%-- <td>支付商户号：<form:input path="queryMerchantCode"  type="text" class="input-small" /></td>
			<td>商户号：<form:input path="merchantCode"  type="text" class="input-small" /></td> --%>
        </li>
         <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="搜索"/></li>
        <li class="clearfix"></li>
    </ul>
</form:form>
		<table class="table table-striped table-bordered table-condensed">		
			<thead>
				<tr  align="center">
					<th>任务日期</th>
					<th>任务名称</th>
					 <th>任务类型</th>
					<!--<th>支付商户号</th> -->
					<th>统计状态</th>
					<th>任务状态</th>
					<th>更新时间</th>
				</tr>
			</thead>
			<tbody class="tbody_name">
				 <c:forEach items="${page.list}" var="posJob" varStatus="status">
					<tr  align="center">
						<td>
						${posJob.jobDate}
							<%-- <fmt:formatDate value="${posJob.jobDate}" pattern="yyyy-MM-dd"/> --%>
	                    </td>
						 <td>
						 	${posJob.jobName}
						 </td>
	                     <td>
							${fns:getDictLabel(posJob.jobType,"job_type","")}
						</td>
	                   <%-- <td>
							 ${posJob.queryMerchantCode}
						</td> --%>
						<td>${fns:getDictLabel(posJob.collectState,"job_collect_state","")}</td>
						<td>
							${fns:getDictLabel(posJob.jobState,"job_state","")}
						</td>
						<td>
							<fmt:formatDate value="${posJob.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
					
					</tr>
					</c:forEach>
			</tbody>
		</table>
	</div>
	 <div class="pagination">${page}</div> 
</body>
</html>