<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jQueryajax请求使用json格式的数据</title>
</head>
<script type="text/javascript" src="js/jquery-3.6.0.js"></script>
<script type="text/javascript">
    $(function (){
        $("#btn").click(function () {
            //获取dom的value值
            var proid = $("#proid").val();
            //发起ajax请求
            $.ajax({
                    async: true,
                    url: "queryjson",
                    data: {"proid": proid},
                    dataType: "json",
                    type: "get",
                    success: function (response) {
                        $("#proname").val(response.name);
                        $("#projiancheng").val(response.jiancheng);
                        $("#proshenghui").val(response.shenghui);
                    }
                }
            );
        });
    })

</script>
<body>
<p>asfafaajax请求使用json格式的数据</p>
<table>
    <tr>
        <td>省份编号：</td>
        <td>
            <input type="text" id="proid">
            <input type="button" value="搜索" id="btn">
        </td>
    </tr>
    <tr>
        <td>省份名称：</td>
        <td>
            <input type="text" id="proname">
        </td>
    </tr>
    <tr>
        <td>省份简称：</td>
        <td>
            <input type="text" id="projiancheng">
        </td>
    </tr>
    <tr>
        <td>省会名称：</td>
        <td>
            <input type="text" id="proshenghui">
        </td>
    </tr>
</table>
</body>
</html>
