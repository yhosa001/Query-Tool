<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/Domain.css">
		
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		
		<fmt:setLocale value="en"/>
		<fmt:setBundle basename="in.resources.msg_en" var="resource"/>
		<fmt:setBundle basename="in.resources.error_msg_en" var="resources"/>
		
		<title>Domain Settings Page</title>
		
		 <script type="text/javascript" src="<%=request.getContextPath()%>/JavaScript/jquery.min.js">
		 </script>
		 <script type="text/javascript" src="<%=request.getContextPath()%>/JavaScript/prototype.js">
		 </script>
		 <script type="text/javascript" src="<%=request.getContextPath()%>/JavaScript/Domain.js">
		 </script>
		 <script type="text/javascript">
			var _errorMessage=[];
			<%
				String errorKey="ERR00";
				for(int i = 1;i <16;i++){
			%>
				_errorMessage[<%=(i-1)%>] = "<fmt:message bundle="${resources}" key="<%=errorKey+String.valueOf(i)%>"/>" ;
			<%
				} 
			%>
		</script>
	</head>
	<body>
		<div class="QueryTool">
			<b>
				<font size="10" color="#94b8b8">
					<fmt:message bundle="${resource}" key="msg.label.querytool"/>
				</font>
			</b>
		</div>
		<center> 
			<i>
				<font size="8" color="#aeeaea">
					<fmt:message bundle="${resource}" key="msg.label.domainSetting"/>
				</font>
			</i>
		</center>
		<br/>
		<br/>
		<br/>
		<div class="Domainborder">
			<center>
				<a href="#" id="new" name="hyperlink" style="color:#94b8b8;font-size:20px;" >
					<fmt:message bundle="${resource}" key="msg.link.new"/> 
				</a>
				&nbsp;|
   				 <a href="#" id="update" name="hyperlink" style="color:#94b8b8;font-size:20px;">
					<fmt:message bundle="${resource}" key="msg.link.update"/>
				</a>
			</center>
			<input type="hidden" id="context" value="<%=request.getContextPath()%>"/>
			<input type="hidden" id="sessionVariable" value="${UserID}"/>
			<br/>
			<br/>
			<div id="divNew">
				<table align = "center">
					<tr>
						<td>
							<font size="4" color="#29a3a3">
								<fmt:message bundle="${resource}" key="msg.label.domainname"/>
							</font>
						</td>
						<td>
							<input type="text" name="dmname" id="dmname" class="Clear">
						</td>
					</tr>
					<tr></tr>
					<tr></tr>
					<tr>
						<td>
							<font size="4" color="#29a3a3">
								<fmt:message bundle="${resource}" key="msg.label.dbType"/></font>
						</td>
						<td>
							<input type="text" name="dtype" id="dtype" value="oracle" style="background-color: #DEDECE" readonly/>
						</td>
					</tr>
					<tr></tr>
					<tr></tr>
					<tr>
						<td>
							<font size="4" color="#29a3a3">
								<fmt:message bundle="${resource}" key="msg.label.dbSid"/></font>
						</td>
						<td>
							<input type="text" name="dbname" id="dbname" class="Clear">
						</td>
					</tr>
					<tr></tr>
					<tr></tr>
					<tr>
						<td>
							<font size="4" color="#29a3a3">
								<fmt:message bundle="${resource}" key="msg.label.hostName"/></font>
						</td>
						<td>
							<input type="text" name="hostname" id="hostname" class="Clear">
						</td>
					</tr>
					<tr></tr>
					<tr></tr>
					<tr>
						<td>
							<font size="4" color="#29a3a3">
								<fmt:message bundle="${resource}" key="msg.label.port"/></font>
						</td>
						<td>
							<input type="text" name="port" id="port" class="Clear">
						</td>
					</tr>
					<tr></tr>
					<tr></tr>
					<tr>
						<td>
							<font size="4" color="#29a3a3">
								<fmt:message bundle="${resource}" key="msg.label.Dbusername"/></font>
						</td>
						<td>
							<input type="text" name="DBuser" id="DBuser" class="Clear">
						</td>
					</tr>
					<tr></tr>
					<tr></tr>
					<tr>
						<td>
							<font size="4" color="#29a3a3">
								<fmt:message bundle="${resource}" key="msg.label.DBpassword"/></font>
						</td>
						<td>
							<input type="text" name="DBpassword" id="DBpassword" class="Clear">
						</td>
					</tr>
					<tr></tr>
					<tr></tr>
					<tr></tr>
					<tr>
						<td colspan='2' align="center">
							<input type="button" name="Register"  id="Register"
									value="<fmt:message bundle="${resource}" key="msg.button.register"/>" class="Buttons">
							<input type="reset" value="Reset" name="Reset" id="Reset"
								value="<fmt:message bundle="${resource}" key="msg.button.reset"/>" class="Buttons"/>
								&nbsp;&nbsp;
							<a href="<%=request.getContextPath()%>/JSP/Menu.jsp" id="home">
								<font color="#aeeaea">
									<fmt:message bundle="${resource}" key="msg.link.home"/>
								</font>
							</a>
						</td>
					</tr>
				</table>
			</div>
			<div id="divUpdate" style="display:none;">
				<table align="center">
					<tr>
						<td>
							<font size="4" color="#29a3a3">
								<fmt:message bundle="${resource}" key="msg.label.domainname"/>
							</font>
						</td>
						<td>
							<select  style="width:100%;height:25px;" id="dmname1" >
		  					</select>
						</td>
					</tr>
					<tr></tr>
					<tr></tr>
					<tr>
						<td>
							<font size="4" color="#29a3a3">
								<fmt:message bundle="${resource}" key="msg.label.dbType"/></font>
						</td>
						<td>
							<input type="text" name="dtype1" id="dtype1" style="background-color: #DEDECE" readonly/>
						</td>
					</tr>
					<tr></tr>
					<tr></tr>
					<tr>
						<td>
							<font size="4" color="#29a3a3">
								<fmt:message bundle="${resource}" key="msg.label.dbSid"/></font>
						</td>
						<td>
							<input type="text" name="dbname1" id="dbname1">
						</td>
					</tr>
					<tr></tr>
					<tr></tr>
					<tr>
						<td>
							<font size="4" color="#29a3a3">
								<fmt:message bundle="${resource}" key="msg.label.hostName"/></font>
						</td>
						<td>
							<input type="text" name="hostname1" id="hostname1">
						</td>
					</tr>
					<tr></tr>
					<tr></tr>
					<tr>
						<td>
							<font size="4" color="#29a3a3">
								<fmt:message bundle="${resource}" key="msg.label.port"/></font>
						</td>
						<td>
							<input type="text" name="port1" id="port1">
						</td>
					</tr>
					<tr></tr>
					<tr></tr>
					<tr>
						<td>
							<font size="4" color="#29a3a3">
								<fmt:message bundle="${resource}" key="msg.label.Dbusername"/></font>
						</td>
						<td>
							<input type="text" name="DBuser1" id="DBuser1">
						</td>
					</tr>
					<tr></tr>
					<tr></tr>
					<tr>
						<td>
							<font size="4" color="#29a3a3">
								<fmt:message bundle="${resource}" key="msg.label.DBpassword"/></font>
						</td>
						<td>
							<input type="text" name="DBpassword1" id="DBpassword1">
						</td>
					</tr>
					<tr></tr>
					<tr></tr>
					<tr></tr>
					<tr>
						<td colspan='2' align="center">
							<input type="button" name="Update" id="Update" class="Buttons"
								value="<fmt:message bundle="${resource}" key="msg.button.update"/>"/>

							<input type="button" value="Delete" name="Delete" id="Delete" class="Buttons"/>
								&nbsp;&nbsp;
							<a href="<%=request.getContextPath()%>/JSP/Menu.jsp" id="home">
								<fmt:message bundle="${resource}" key="msg.link.home"/></a>
						</td>
					</tr>
				</table>
			
			</div>
		</div>
	</body>
</html>