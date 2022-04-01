<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" +
            request.getServerPort() + request.getContextPath() + "/";
    System.out.println(basePath);
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>myTitle</title>
    <script type="text/javascript" src="js/jquery-3.6.0.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#djBtn").click(function () {
                $.ajax({
                    url: "myServlet01.do",
                    type: "get",
                    dataType: "json",
                    success: function (data) {
                        alert(data.id + " " + data.name + " " + data.age);
                    }
                })
            })
        })
    </script>
</head>

<body>

<input id="djBtn" type="button" value="获取数据">
<div id="msg" style="width:200px;height:200px;background-color:pink">

</div>

</body>
</html>
