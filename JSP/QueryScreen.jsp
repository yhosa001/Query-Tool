<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Query Wizard</title>
		<fmt:setLocale value="en"/>
		<fmt:setBundle basename="in.resources.msg_en" var="resource"/>
		<fmt:setBundle basename="in.resources.error_msg_en" var="resources"/>
		<title>Select screen</title>
		<script type="text/javascript" src="<%=request.getContextPath()%>/JavaScript/jquery.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/JavaScript/prototype.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/JavaScript/Queryscreen.js">
	    </script>
	    <script type="text/javascript">
				var _errorMessage=[];
				<%
					String errorKey="ERR00";
					for(int i = 1;i <30;i++){
				%>
					_errorMessage[<%=(i-1)%>] = "<fmt:message bundle="${resources}" key="<%=errorKey+String.valueOf(i)%>"/>" ;
				<%
					}
				%>
		</script>
	</head>
	<body>
		<form method="post" name="Format" id="format" action="<%=request.getContextPath()%>/download.action">
			<a href="<%=request.getContextPath()%>/JSP/Menu.jsp" style = "float: right;">
				<img src ="<%=request.getContextPath()%>/Image/Home_Icon.png" width="65" height="65"/>
				
			</a>
			<br>
			<b>	
				<font size="4" color="#66cc99">
					<label for="headdinglabel">
					</label>			
				</font>
			</b>
			
			<b>
				<font size="6" color="#66cc99" style='float: right;'>
					<fmt:message bundle="${resource}" key="msg.label.queryWizard"/>
				</font>
			</b>

			<center>
				<i>
					<font size="6" color="#66cc99">
						<fmt:message bundle="${resource}" key="msg.label.domainname"/><label for='myvalue'></label>
					</font>
				</i>
			</center>
			<input type="hidden" name="DomainName" id="domainName" value="">
			<table border='0' align="center" width="100%" height="5px" bgcolor="#008080">
				<tr></tr>
			</table>
			
			<br />
			
			
			<input type="hidden" id="context" value="<%=request.getContextPath()%>" />
			<input type="hidden" id="sessionVariable" value="${UserID}"/>
			
			
			
			<div id="selectScreen" style="display:;">
				<table align= "center" width="80%">
					<tr>
						<td>
							<font size="5" color = "#66cc99">
								<fmt:message bundle="${resource}" key="msg.label.tables"/>
							</font>
							<div style = "border: 1px solid #008080; width:200px; overflow: auto; 
								height: 300px; cursor: pointer" class="list" id="tables">
								<ul class='list' id='tablelist' style='list-style-type: none;padding-left:20px;color:#66cc99; font-size:18px'></ul>
							</div>
						</td>
						<td></td>
						<td></td>
						<td>
							<font  size="5" color = "#66cc99">
								<fmt:message bundle="${resource}" key="msg.label.coulmns"/>
							</font>
							<div style = "border: 1px solid #008080; width:200px; overflow: auto; 
								height: 300px; cursor: pointer; font-size:20px" id="columns">
								<ul class='collist' id='columnlist' style='list-style-type: none;padding-left:20px;color:#66cc99; font-size:18px'></ul>
							</div>
							<div id="dtype" style="visibility: hidden">
							</div>
						</td>
						<td></td>
						<td></td>
						<td>
							<input type="button" name="Select" value=">>" id="select" style="color: #FFFFFF; background-color: #008080">
						</td>
						<td></td>
						<td></td>
						<td>
							<font  size="5" color = "#66cc99">
								<fmt:message bundle="${resource}" key="msg.label.displayColumns"/>
							</font>
							<div style = "border: 1px solid #008080; width:200px; overflow: auto; 
								height: 300px; cursor: pointer" id="display">
								<ul class='selectedList' id='displayColumn' style='list-style-type: none; padding-left:20px;color:#66cc99; font-size:18px'></ul>
							</div>
						</td>
					</tr>
					
				</table>
				<br/>
				<br/>
				<table align="center">
					<tr align = "center">
						<td></td>
						<td></td>
						<td></td>
	
						<td >
							<input type ="button" name ="Clear" value="<fmt:message bundle="${resource}" key="msg.button.clear"/>" id="clear"
								style="color: #66cc99; background-color: #008080; font-size:20px" />
							<input type="button" name="ClearAll" value="<fmt:message bundle="${resource}" key="msg.button.clearAll"/>" id="clearAll"
								style="color: #66cc99; background-color: #008080; font-size:20px" />
						</td>
					</tr>
				</table>
				<br/>		
				<center>
					<input type="button" name="Next" value="<fmt:message bundle="${resource}" key="msg.button.next"/>" id="next"
								style="color: #66cc99; background-color: #008080; font-size:20px" />
				</center>
			</div>
			
			
			
			
			
			<div id="joinScreen" style="display:none;">
				<table align="center">
					<tr>
						<td>
							<font size="5" color = "#66cc99">
								<fmt:message bundle="${resource}" key="msg.label.table1"/>
							</font>
							<div style = "border: 1px solid #008080; width:200px; overflow: auto; 
								height: 300px; cursor: pointer" class="table1" id="table1">
								<ul class='tablelist1' id='tablelist1' style='list-style-type: none; padding-left:20px;color:#66cc99; font-size:18px;'></ul>
							</div>
						</td>
						<td></td>
						<td></td>
						<td>
							<font  size="5" color = "#66cc99">
								<fmt:message bundle="${resource}" key="msg.label.coulmns"/>
							</font>
							<div style = "border: 1px solid #008080; width:200px; overflow: auto; 
								height: 300px; cursor: pointer; font-size:20px" id="columns1">
								<ul class='columnlist1' id='columnlist1' style='list-style-type: none ;padding-left:20px;color:#66cc99; font-size:18px;'></ul>
							</div>
							<div id="dtype1" style="visibility: hidden">
							</div>
						</td>
						<td></td>
						<td></td>
						<td>
							<input type="button" name="Join" value="<fmt:message bundle="${resource}" key="msg.button.join"/>" id="join" 
							style="color: #66cc99; background-color: #008080;font-size:20px;"/>
						</td>
						<td></td>
						<td></td>
						<td>
							<font size="5" color = "#66cc99">
								<fmt:message bundle="${resource}" key="msg.label.table2"/>
							</font>
							<div style = "border: 1px solid #008080; width:200px; overflow: auto; 
								height: 300px; cursor: pointer" class="table2" id="table2">
								<ul class='tablelist2' id='tablelist2' style='list-style-type: none;padding-left:20px; color:#66cc99; font-size:18px;'></ul>
							</div>
						</td>
						<td></td>
						<td></td>
						<td>
							<font  size="5" color = "#66cc99">
								<fmt:message bundle="${resource}" key="msg.label.coulmns"/>
							</font>
							<div style = "border: 1px solid #008080; width:200px; overflow: auto; 
								height: 300px; cursor: pointer; font-size:20px" id="columns2">
								<ul class='columnlist2' id='columnlist2' style='list-style-type: none;padding-left:20px;color:#66cc99; font-size:18px;'></ul>
							</div>
							<div id="dtype2" style="visibility: hidden">
							</div>
						</td>
					</tr>
				</table>
				
				<table width = "73%" align="center">
					<tr>
						<td>
							<font size="5" color = "#66cc99">
								<fmt:message bundle="${resource}" key="msg.label.joinCondition"/>
							</font>
							<div style = "border: 1px solid #008080; width:750px; overflow: auto; 
								height: 100px;" id="joinCondition" class="joinCondition">
								<ul class='joinlist' id='joinlist' style='list-style-type: none;padding-left:20px;color:#66cc99; font-size:18px;'></ul>
							</div>
						</td>
						<td>
							<input type="button" name="ClearJoins" value="<fmt:message bundle="${resource}" key="msg.button.clearAll"/>" id="clearJoins" 
							style="color: #66cc99; background-color: #008080;font-size:20px;"/>
						</td>
					</tr>
					<tr height="20px">
					</tr>
					<tr>
						<td align="center">
							<input type="button" name="Previous" value="<fmt:message bundle="${resource}" key="msg.button.previous"/>" id="previous" 
							style="color: #66cc99; background-color: #008080;font-size:20px;"/>
							&nbsp; &nbsp;
							<input type="button" name="Next1" value="<fmt:message bundle="${resource}" key="msg.button.next"/>" id="next1" 
							style="color: #66cc99; background-color: #008080;font-size:20px;"/>
						</td>
					</tr>
				
				</table>
			</div>
		
		
		
		
		
			<div id="filterScreen" style="display:none;">
				<table align="center" width = "80%">
					<tr>
						<td>
							<font size="5" color = "#66cc99">
								<fmt:message bundle="${resource}" key="msg.label.tables"/>
							</font>
							<div style = "border: 1px solid #008080; width:200px; overflow: auto; 
								height: 300px; cursor: pointer"  id="table1" class="table1">
								<ul class='filterTablelist' id='filterTablelist' style='list-style-type: none;padding-left:20px;color:#66cc99; font-size:18px;'></ul>
							</div>
						</td>
						<td></td>
						<td></td>
						<td>
							<font size="5" color = "#66cc99">
								<fmt:message bundle="${resource}" key="msg.label.coulmns"/>
							</font>
							<div style = "border: 1px solid #008080; width:200px; overflow: auto; 
								height: 300px; cursor: pointer"  id="filtercolumns1">
								<ul class='filtercolumnlist' id='filtercolumnlist' style='list-style-type: none;padding-left:20px;color:#66cc99; font-size:18px;'></ul>
							</div>
							<div id="filterdtype1" style="visibility: hidden"></div>
						</td>
						<td width="50%" align="center">
							<table>
								<tr>
									<td align="center">
										<div id="radio">
											<input type="radio" name="AndOr" id="andOr" value="AND" checked="checked" /> 
											<font size="4" color="#66cc99">
												<label>AND</label>
											</font>
	
											<input type="radio" name="AndOr" id="andOr" value="OR"/>
										 	<font size="4" color="#66cc99"> 
												<label>OR</label>
											</font>
										</div>
									</td>
								</tr>
								<tr style="height:25px;">
								</tr>
								<tr >
									<td>
										<font size="4" color="#66cc99">
											<label>Operator</label>
										</font>
										<select name="Operator" id="operator">
											<option value="BETWEEN" selected>BETWEEN</option>
											<option value="NOT BETWEEN">NOT BETWEEN</option>
											<option value="=">=</option>
											<option value="!=">!=</option>
											<option value=">=">>=</option>
											<option value=">">></option>
											<option value="< "><</option>
											<option value="Like">Like</option>
											<option value="<="><=</option>
											<option value="IN">IN</option>
											<option value="NOT IN">NOT IN</option>
											<option value="IS NULL">IS NULL</option>
											<option value="IS NOT NULL">IS NOT NULL</option>
										</select>
									</td>
								</tr>
								<tr style="height:25px;">
								</tr>
								<tr>
									<td>
										<div id="divValue1">
											<font size="4" color="#66cc99">
												<fmt:message bundle="${resource}" key="msg.label.value1"/>
											</font>
											<input type="text" id="value1" name="Value1" />
										</div>
									</td>
								</tr>
								<tr style="height:25px;">
								</tr>
								<tr>	
									<td>
										<div id="divValue2">
											<font size="4" color="#66cc99">
												<fmt:message bundle="${resource}" key="msg.label.value2"/>
											</font>
											<input type="text" id="value2" name="value2" />
										</div>
									</td>
								</tr>
								<tr style="height:25px;">
								</tr>
								<tr>
									<td align="center">
										<input type ="button" name ="Set" value="<fmt:message bundle="${resource}" key="msg.button.set"/>" id="set"
											style="color: #66cc99; background-color: #008080; font-size:20px;cursor: pointer" />
									</td>
								</tr>
							</table>
						</td>
					</tr>					
				</table>
				<table width = "73%" align="center">
					<tr>
						<td>
							<font size="5" color = "#66cc99">
								<fmt:message bundle="${resource}" key="msg.label.filterConditions"/>
							</font>
							<div style = "border: 1px solid #008080; width:900px; overflow: auto; 
								height: 100px;" id="filterCondi" class="filterCondi">
								<ul class='filterCondition' id='filterCondition' style='list-style-type: none;padding-left:20px;color:#66cc99; font-size:18px;'></ul>
							</div>
						</td>
						<td>
							<input type="button" name="ClearFilter" value="<fmt:message bundle="${resource}" key="msg.button.clearAll"/>" id="clearFilter" 
							style="color: #66cc99; background-color: #008080;font-size:20px;"/>
						</td>
					</tr>
					<tr height="20px">
					</tr>
					<tr>
						<td align="center">
							<input type="button" name="Previous1" value="<fmt:message bundle="${resource}" key="msg.button.previous"/>" id="previous1" 
							style="color: #66cc99; background-color: #008080;font-size:20px;"/>
							&nbsp; &nbsp;
							<input type="button" name="Next2" value="<fmt:message bundle="${resource}" key="msg.button.next"/>" id="next2" 
							style="color: #66cc99; background-color: #008080;font-size:20px;"/>
						</td>
					</tr>				
				</table>
			</div>
			
			
			
			<div id ="queryExecuteScreen" style="display:none;">
				<table align="center">
					<tr>
						<td>
							<font size="5" color = "#66cc99">
								<fmt:message bundle="${resource}" key="msg.label.query"/>
							</font>
							<div>
								<textarea name="Query" id='query' cols="125" rows="20" style="background-color:#c2d6d6;">
						    	</textarea>
					    	</div>
						</td>
					</tr>
					<tr style="height:20px">
					</tr>
					<tr>
						<td align="center">
							<input type="radio" name="Format" id="format" value="HTML" checked="checked" /> 
							<font size="4" color="#66cc99">
								<label>HTML</label>
							</font>
							
							<input type="radio" name="Format" id="format" value="CSV" /> 
							<font size="4" color="#66cc99">
								<label>CSV</label>
							</font>
							
							<input type="radio" name="Format" id="format" value="TEXT" /> 
							<font size="4" color="#66cc99">
								<label>TEXT</label>
							</font>
						</td>
					</tr>
					<tr style="height:20px">
					</tr>
					<tr>
						<td align="center">
							<input type="button" name="Previous2" value="<fmt:message bundle="${resource}" key="msg.button.previous"/>" id="previous2" 
							style="color: #66cc99; background-color: #008080;font-size:20px;"/>
						
							<input type="button" name="Execute" value="<fmt:message bundle="${resource}" key="msg.button.execute"/>" id="execute" 
							style="color: #66cc99; background-color: #008080;font-size:20px;"/>
						
							<input type="button" name="Count" value="<fmt:message bundle="${resource}" key="msg.button.count"/>" id="count" 
							style="color: #66cc99; background-color: #008080;font-size:20px;"/>
						</td>						
					</tr>
				</table>
				<input type="hidden" id="queryHidden" name="Query" value="" />
			</div>
			
			
			
			
			<div id="resultPage" style="display:none;">
				<a href="#" id="queryBuilder" name="HyperLink">
					<font size="5" color = "#66cc99" style="float:right;">
						<fmt:message bundle="${resource}" key="msg.link.queryBuilder"/>
					</font>			
				</a>
				<br/>
				<br/>
				<div align="right">
					<a href="#" id="previousPage" name="HyperLink">
						<font  size="5" color = "#66cc99">
							<fmt:message bundle="${resource}" key="msg.link.previous"/>
						</font>
						<font size="5" color = "#66cc99">
							<label for='Records'></label>
						</font>			
					</a>
					<a href="#" id="nextPage" name="HyperLink">
						<font  size="5" color = "#66cc99">
							<fmt:message bundle="${resource}" key="msg.link.next"/>
						</font>
					</a>
				</div>
				<table id="output" class="paginated" align="center" border="1px">
				</table>
				<br/>
				<br/>
				<center>
					<input type="radio" name="Export" id="export" value="CSV" /> 
					<font size="4" color="#66cc99">
						<label>CSV</label>
					</font>
					
					<input type="radio" name="Export" id="export" value="TEXT" /> 
					<font size="4" color="#66cc99">
						<label>TEXT</label>
					</font>
					<input type="button" name="Download" value="Download" id="download" 
							style="color: #66cc99; background-color: #008080;font-size:20px;"/>	
				</center>
			</div>
			
		</form>
	
	</body>
</html>