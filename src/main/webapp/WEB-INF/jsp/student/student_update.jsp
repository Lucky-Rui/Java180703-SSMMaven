<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${ctx}/lib/bootstrap-3.3.7-dist/css/bootstrap.css" />
</head>
<body>
   <form id="update_form" action="${ctx}/student/updateStudent.action" method="post">
   ${student}
		     姓名：<input type="text" name="name" value="${student.name}"/><br/>
		     年龄：<input type="text" name="age" value="${student.age}"/><br/>
		     性别：<input type="text" name="gender" value="${student.gender}"/><br/>
		     班级： <select name="${student.banji.id}">
          <option>---------请选择班级---------</option>
			<c:forEach items="${list}" var="banji">
				<c:if test="${student.banji.id == banji.id}">
					<option value="${banji.id}" selected="selected">${banji.name}</option>
				</c:if>
				<c:if test="${student.banji.banjiId != banji.id}">
					<option value="${banji.id}">${banji.name}</option>
				</c:if>
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
          url : "${ctx}/student/updateStudent.action",
          type : "post",
          dataType : "json",
          data : $("#iupdate_form").serialize(),
          success : function(data) {
             if(data) {
               mylayer.success("修改成功");
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
</html>