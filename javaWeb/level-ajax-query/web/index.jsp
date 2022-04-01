<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>省市级联查询</title>
    <script type="text/javascript" src="js/jquery-3.6.0.js"></script>
    <script type="text/javascript">
        $(function () {
            //在页面的dom对象都加载完成之后发起ajax请求（不用点击loaddata）
            loadDataAjax();
            $("#btnLoad").click(function () {
              loadDataAjax();
            })
            //给省份的select绑定一个change事件，当select中的内容变化的时候，触发事件
            $("#province").change(function () {
                //获取选中列表框里的值
                var obj= $("#province>option:selected");
                var provinceId = obj.val();
                //通过ajax请求获取省份对应的所有城市
                $.post("queryCity",{proid:provinceId},callback,"json");
            })
        })
        function callback (response) {
            //删除旧的数据
            $("#city").empty();
            //追加option
            $.each(response, function (i, n) {
                //获取select这个dom对象
                $("#city").append("<option value='" + n.id + "'>" + n.name + "</option>");
            })
        }
        //ajax封装
        function loadDataAjax(){
            $.ajax({
                url: "queryProvince",
                type: "get",
                success: function (response) {
                    //删除旧的数据
                    $("#province").empty();
                    //追加option
                    $.each(response, function (i, n) {
                        //获取select这个dom对象
                        $("#province").append("<option value='" + n.id + "'>" + n.name + "</option>");
                    })
                }
            })
        }
    </script>
</head>
<body>
<p>省市级联查询，使用ajax</p>
<div>
    <table border="1px" cellpadding="0" cellspacing="0">
        <tr>
            <td>省份：</td>
            <td>
                <select id="province">
                    <option value="0">请选择...</option>
                </select>
            </td>
            <td>
                <input type="button" value="load data ..." id="btnLoad">
            </td>
        </tr>
        <tr>
            <td>城市：</td>
            <td>
                <select id="city">
                    <option value="0">请选择...</option>
                </select>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
