<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023/2/2
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
    <script src="<%=request.getContextPath()%>/js/jquery-3.6.0.min.js"></script>
    <script>
        /**
         * ${param.id} 等价于request.getParamter(“name”) 是服务器从页面或者客户端获取信息的方法
         */
        const id = ${param.id};
        console.log(id);

        /**
         * 准备就绪函数
         */
        $(function (){
            $.ajax({
                url:"<%=request.getContextPath()%>/champion",
                data:{m:"getHero"},
                type:"post",
                dataType:"json",
                success:function(resp){
                    console.log(resp)
                    for (var i = 0;i<resp.length;i++){
                        $("#pid").append("<option value='"+resp[i].id+"'>"+resp[i].name+"</option>");
                    }
                },
                async:false // 取消异步
            });

            $.ajax({
                url:"<%=request.getContextPath()%>/champion",
                data:{id:id,m:"queryById"},
                type:"post",
                dataType:"json",
                success:function (resp){
                    // console.log(resp);
                    $("#id").val(resp.id);
                    $("#name").val(resp.name);
                    $("#pid").val(resp.hero.id)
                    $("#nickname").val(resp.nickname);
                    $(".star[value="+resp.star+"]").attr("checked",true);
                }
            });

            $("#btn").click(function() {
                $.ajax({
                    url:"<%=request.getContextPath()%>/champion?m=update",
                    data:$("#f").serialize(),
                    dataType:"json",
                    type:"post",
                    success:function (resp){
                        if(resp){
                            location = "<%=request.getContextPath()%>/champion?m=query";
                        }
                    }
                });
            });
        })
    </script>
<body>
<form id="f">
    <table>
    <tr>
        <td>姓名</td>
        <td>
            <input type="hidden" name="id" id="id">
            <input type="text" name="name" id="name">
        </td>
    </tr>
    <tr>
        <td>星级</td>
        <td>
            <input type="radio" name="star" class="star" value="1">一星
            <input type="radio" name="star" class="star" value="2">二星
            <input type="radio" name="star" class="star" value="3">三星
        </td>
    </tr>
    <tr>
        <td>外号</td>
        <td>
            <input type="text" name="nickname" id="nickname">
        </td>
    </tr>
    <tr>
        <td>职业</td>
        <td>
            <select name="pid" id="pid">
                <option>请选择</option>
            </select>
        </td>
    </tr>
    <tr>
        <td>
            <input type="button" value="修改" id="btn">
        </td>
    </tr>
    </table>
</form>
</body>
</html>
