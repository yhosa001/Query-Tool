<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/Menu.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/PopUp.css">
	<head>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/CSS/Menu.css">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
			<fmt:setLocale value="en"/>
			<fmt:setBundle basename="in.resources.msg_en" var="resource"/>
			<fmt:setBundle basename="in.resources.error_msg_en" var="resources"/>
		<title>
			Menu Page
		</title>
		<script type="text/javascript" src="<%=request.getContextPath()%>/JavaScript/jquery.min.js"></script>
		<script type="text/javascript"src="<%=request.getContextPath()%>/JavaScript/prototype.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/JavaScript/Menu.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/JavaScript/Select.js"></script>
	    <script type="text/javascript" src="<%=request.getContextPath()%>/JavaScript/History.js"></script>
	    <script type="text/javascript">
		    var _errorMessage = [];
			<% 
				String errorKey = "ERR00";
				for(int i=1; i<22; i++){
					
			%>
		
					_errorMessage[<%=(i-1)%>] = "<fmt:message bundle = "${resources}" key = "<%=errorKey+String.valueOf(i)%>"/>";
			
			<%
				}
			%>
		</script>
	</head>
	<body background="<%=request.getContextPath()%>/Image/Menu_background.png">
		<div id = "Load">
			<p style='float: right;'>
				<img src="<%=request.getContextPath()%>/Image/Logout_Icon.png" name="logout"
					id="Logout" width="40" height="40" alt="LogOut" />
				<font color="#94b8b8">
					<fmt:message bundle="${resource}" key="msg.label.logout"/>
				</font>
			</p>
		
			<div>
				<br>
				<br>
				<h1 align="center" style=" color:#94b8b8; font-size: 65px">
					<fmt:message bundle="${resource}" key="msg.label.menu"/>
				</h1>		
				<marquee behavior="alternate" scrollamount="5">
					<h2>Welcome ${UserID}</h2>	
				</marquee>
			</div>
			<center>
				<label>
					<font color="#94b8b8" size="5">
						<fmt:message bundle="${resource}" key="msg.label.selectDomain"/>
					</font>
				</label>
				<select style="width: 15%; height: 25px;" id="Domain"></select>
			</center>
			<input type="hidden" id="context" value="<%=request.getContextPath()%>" />
			<input type="hidden" id="sessionVariable" value="${UserID}"/>
			<br>
			<br>
			<br>
			<br>
			<table align="center">
				<tr>
					<td>
						<a href="#Setting-box" class="Setting-window" title="Settings"  style="color:#94b8b8;"> 
							<img src="<%=request.getContextPath()%>/Image/Settings_Icon.png" id="Settings" class="icons"
							width="75" height="75"/>
							Settings
						</a>
					</td>
					<td>
						<a href="#" class="Query" name="hyperlink"  title="Query Wizard" style="color:#94b8b8;"> 
							<img src="<%=request.getContextPath()%>/Image/QueryWizard_Icon.png" class="icons"
								id="Query" width="75" height="75" />
							Query Wizard
						</a>
					</td>
					<td>
						<a href="#" name="hyperlink" title="History"  style="color:#94b8b8;"> 
							<img src="<%=request.getContextPath()%>/Image/History_Icon.png" id="History" class="icons"
								width="75" height="75" />
							History
						</a>
					</td>
					<td>
						<a href="#" name="hyperlink" title="Domain Settings"  style="color:#94b8b8;"> 
							<img src="<%=request.getContextPath()%>/Image/DomainSetting_Icon.png" id="DomainSetting" class="icons"
								width="75" height="75" />
							Domain Settings 
						</a>
					</td>
				</tr>
			</table>	
		</div>
			<!--  
			
			<div id="Setting-box" class= "Setting-Popup">
				<label style="color:white;">
					Settings
				</label>
				
				<img alt="." src="<%=request.getContextPath()%>/Image/Settings_Icon.png" title="Settings"
					width ="50px" height="50px"/>
				<input type="hidden" id="context" value="<%=request.getContextPath()%>" />
			    <a href="#" class="Close">
			    	<img src="<%=request.getContextPath()%>\Image\Close_Icon.png" width="25px"
					height="25px" style='float: right;' class="Btn_Close" title="Close Window" alt="Close" />
				</a>
			</div>
			
			
			-->
			
	</body>
	
</html>