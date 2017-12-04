var $jQ = jQuery.noConflict();
//var _errorMessage = [];
$jQ(document).ready(function(){
	$jQ("#loading").hide();
	$jQ("#Login").click(function(){
		var userid = $jQ("#userid").val();
		var password = $jQ("#password").val();
		var valid = true;
		var msg = "";
		if(userid == ""){
			$jQ("#userid").focus();
			alert(_errorMessage[0]);
			return false;
		}
		if(password==""){
			$jQ("#password").focus();
			alert(_errorMessage[1]);
			return false;
		}
		var context = $jQ("#context").val();
		//var context = "http://localhost:8090/ToolForQueries";
		$jQ("#loading").show();
		var params = "userid="+userid+"&password="+password;
		new Ajax.Request(context+"/login.action",{
	 			method:'POST',
	 			parameters:params,
	 			onSuccess: function(data) {
	 			$jQ("#loading").hide();
	 			data=JSON.parse(data.responseText);
	 				var result=data.response;
	 				if (data.result=="success"){
	 					window.location=context+"/JSP/Menu.jsp";
				}else {
					alert(data.message);
				}
		   }
	});

});
});