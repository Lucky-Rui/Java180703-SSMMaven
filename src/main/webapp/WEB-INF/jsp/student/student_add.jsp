<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet"
	href="${ctx}/lib/bootstrap-3.3.7-dist/css/bootstrap.css" />
</head>
<body>
   <form id="insert_form" action="${ctx}/student/addStudent.action" method="post">
		     姓名：<input type="text" name="name"/><br/>
		     年龄：<input type="text" name="age"/><br/>
		     性别：<input type="text" name="gender"/><br/>
		     班级： <select name="banji.id">
          <option>--请选择--</option>
          <c:forEach items="${list}" var="banji">
             <option value="${banji.id}">${banji.name}</option>
          </c:forEach>
        </select>
     <br/>
     <!-- <input type="submit" value="注册"/> -->
     <input type="button" onclick="submitForm()" value="添加"/>
   </form>
   
   <script src="${ctx}/lib/jquery/jquery-1.11.1.js" type="text/javascript" charset="utf-8"></script>
   <script src="${ctx}/lib/bootstrap-3.3.7-dist/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
   <script src="${ctx}/lib/layer/layer.js" type="text/javascript" charset="utf-8"></script>
   <script src="${ctx}/js/mylayer.js" type="text/javascript" charset="utf-8"></script>
   <script type="text/javascript">
     function submitForm() {
        //console.log($("#insert_form").serialize());
        //name=55&age=5&gender=5&banjiId=1
        $.ajax({
          url : "${ctx}/student/addStudent.action",
          type : "post",
          dataType : "json",
          data : $("#insert_form").serialize(),
          success : function(data) {
             if(data) {
               mylayer.success("添加成功");
               //先得到当前iframe层的索引
               var index = parent.layer.getFrameIndex(window.name);
               setTimeout(function() {
                  parent.layer.close(index);
                  //刷新父页面
                  window.parent.location.reload();
               }, 1000);
             } else {
               mylayer.errorMsg("添加失败");
             }
          }
        });
     }
   </script>
</body>
</body>
</html>
