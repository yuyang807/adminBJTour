<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>新增附加项</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {

        });

        function addAddition() {
            $.ajax({
                type: "POST",
                url: "${ctx}/line/saveAddition",
                data: $('#addAddition').serialize(),// 你的formid
                async: false,
                error: function (request) {
                    alert("Connection error");
                },
                success: function (data) {
                    alert(data);
                    window.parent.window.jBox.close();
                }
            });
        }
    </script>
</head>
<body>
<div id="container" style="min-width:680px;height:390px; width:880px;"><!--//container为整个body的wrap层//-->
    <div class="main">
        <div class="clear"></div>
        <div class="my_card account_con" style="margin: 10px 0 0 10px; width:740px; float:left;">
            <!--新增银行卡-->
            <div class="new_card" style="width: 680px;">
                <form id="addAddition" action="${ctx}/line/saveAddition" method="post"
                      enctype="multipart/form-data">
                    <input type="hidden" id="addNo" name="addNo" value="${addNo}"/>

                    <div class="form_team"><label>附加项内容：</label>&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="text" id="addValue" name="addValue" class="input-xxlarge required"/>
                    </div>
                    <div class="form_team"><label>&nbsp;</label>
                        <input type="button" value="提交" onclick="addAddition()"/>
                    </div>
                </form>
                <div class="cl"></div>
            </div>
        </div>
    </div>

</div>
</body>
</html>