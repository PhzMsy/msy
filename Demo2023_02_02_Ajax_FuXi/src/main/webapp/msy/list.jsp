<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023/2/2
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script src="<%=request.getContextPath()%>/js/jquery-3.6.0.min.js"></script>
    <script>
        /**
         * 准备就绪函数
         */
        $(function (){
            /**
             * 修改
             */
            $(".update").click(function(){
                let id =$(this).next().val();
                location = "msy/update.jsp?id="+id;
            })
            $(".delete").click(function(){
                let id =$(this).prev().val();
                $.ajax({
                    url:"<%=request.getContextPath()%>/champion?m=delete&id="+id,
                    dataType:"json",
                    success:function (resp){
                        if (resp){
                            location.reload();
                        }
                    }

                })
            })
        })
    </script>
</head>
<body>
    <table>
        <tr>
            <td></td>
            <td>编号</td>
            <td>角色名</td>
            <td>星级</td>
            <td>外号</td>
            <td>职业</td>
            <td></td>
        </tr>
        <c:forEach var="h" items="${list}">
            <tr>
                <td></td>
                <td>${h.id}</td>
                <td>${h.name}</td>
                <td>${h.star}</td>
                <td>${h.nickname}</td>
                <td>${h.hero.name}</td>
                <td>
                    <input type="button" class="update" value="修改">
                    <input type="hidden" value="${h.id}">
                    <input type="button" class="delete" value="删除">
                </td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
