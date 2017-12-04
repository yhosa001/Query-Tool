<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/Login.css">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
			<fmt:setLocale value="en"/>
			<fmt:setBundle basename="in.resources.msg_en" var="resource"/>
			<fmt:setBundle basename="in.resources.error_msg_en" var="resources"/>
					
		<title>Login Page</title>
		
		<script type="text/javascript" src="<%=request.getContextPath()%>/JavaScript/jquery.min.js">
		</script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/JavaScript/prototype.js">
		</script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/JavaScript/Login.js">
		</script>
		
		<script type="text/javascript">
			var _errorMessage = [];
			<% 
				String errorKey = "ERR00";
				for(int i=1; i<3; i++){
					
			%>
		
					_errorMessage[<%=(i-1)%>] = "<fmt:message bundle = "${resources}" key = "<%=errorKey+String.valueOf(i)%>"/>";
			
			<%
				}
			%>
		</script>
	</head>
	<body>
		<s:form action="login" method ="post" id ="_form" name ="_form">
			<table align="center" id="head" >
				<tr style= "height:20px; width:100%;">
				</tr>
				<tr>
					<td align="center">
						<font color="white" size ="14"><i>	Query</i></font>
					</td>
					<td align="center">
						<font color="white" size ="14"><i>	Tool</i></font>
					</td>
				</tr>
			</table>	
			<input type="hidden" id="context" value="<%=request.getContextPath()%>"/>
			<div class = "loginDiv" align="center">
				<table align="center">
					<tr style= "height:90px;">
						<td colspan="2" align="center">
							<label class="loginLabel" style="color:white;font-size:170%;">
								<b><i>Login</i></b>
							</label>
						</td>
					</tr>
					<tr style= "height:50px;">
						<td>
							<font size="4" color="white"><fmt:message bundle="${resource}" key="msg.label.userId"></fmt:message>
							</font>
						</td>
						<td>
							<input type="text" name="userid" id="userid">
						</td>
					</tr>
					<tr style= "height:50px;">
						<td>
							<font size="4" color="white"><fmt:message bundle="${resource}" key="msg.label.password"></fmt:message></font>
						</td>
						<td>
							<input type="password" name="password" id="password">
						</td>
					</tr>
					<tr style= "height:50px;">
						<td colspan='2' align="center">
							<input type="button" name="Login" value="<fmt:message bundle="${resource}" key="msg.button.login"></fmt:message>"
								 id="Login" class="loginButtons" style="cursor:pointer;"/>
							<input type="reset" value="<fmt:message bundle="${resource}" key="msg.button.reset"></fmt:message>"
								name="Reset" class="loginButtons" style="cursor:pointer;"/>
				    	</td>
					</tr>
					<tr style= "height:50px;">
						<td colspan='2' align="center"><a href="<%=request.getContextPath()%>/JSP/ChangePassword.jsp">
							<font color=" #CCD9F7" size="5"><fmt:message bundle="${resource}" key="msg.link.changepassword"/></font></a>
						</td>
					</tr>
				</table>
			</div>
			<center>
				<div id="footer">
					<img src="<%=request.getContextPath()%>/Image/Loading.gif" id="loading" width="100" height="75" />
				</div>
			</center>
		</s:form>
	</body>
</html>