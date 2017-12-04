var $j=jQuery.noConflict();
var _errorMessage=[];
$j(document).ready(function(){
	 $j( "#divUpdate" ).hide();
	var sessionValue=$j('#sessionVariable').val();
	var context=$j("#context").val();
	if (sessionValue=="" || sessionValue==null) {
		window.location.href=context+"/JSP/Login.jsp";
		return false;
	}
	
	$j('a').click(function(){
		var ref=this.id
		if (ref=="new"){
			$j('#divUpdate').hide();
			$j( "#divNew" ).show();
		}
		if (ref=="update") {
			$j('#divUpdate').show();
			$j('#divNew').hide();
		}
	});
	
	$j("#Reset").click(function(){
		   $j(".Clear").val('');
	});
	
	
	var domain_value;
	
	new Ajax.Request(context+"/domain.action",{
		method:"POST",
		async: false,
		onSuccess: function(data){
			data=eval('('+data.responseText+')');
			domain_value= data;
			var opt="";
 			var len=data.length;
 			var str1,str2,str3,str4,str5,str6,str7;
 			for(var i = 0; i <len; i++) {
	 			opt="<option>"+data[i].domain_Name+"</option>";
	 			$j("#dmname1").append(opt);
	 			if(i==0) {
	 				 str1=data[0].domain_Name;
	 				 str2=data[0].DB_Type;
	 				 str3=data[0].DB_Name;
	 				 str4=data[0].host_Name;
	 				 str5=data[0].port;
	 				 str6=data[0].DBUser_Name;
	 				 str7=data[0].DB_Password;
 				}
 			}
			$j("#dtype1").val(str2);
			$j("#dbname1").val(str3);
			$j("#hostname1").val(str4);
			$j("#port1").val(str5);
			$j("#DBuser1").val(str6);
			$j("#DBpassword1").val(str7);
		}
	});
	$j("#Register").click(function(){
		var DomainName=$j("#dmname").val();
        var DbType=$j("#dtype").val();
        var DbName=$j("#dbname").val();
        var HostName=$j("#hostname").val();
        var port=$j("#port").val();
        var DbUser=$j("#DBuser").val();
        var DbPassword=$j("#DBpassword").val();
		var msg="";
		if(DomainName=="") {
			alert(_errorMessage[7]);
	   		$j("#dmname").focus();
	   		return false;
		}
		if(DbName==""){
			$j("#dbname").focus();
			alert(_errorMessage[8]);
			return false;
		}
		if(HostName=="") {
		   	$j("#hostname").focus();
			alert(_errorMessage[9]);
			return false;
		}
		if(port==""){
			$j("#port").focus();
			alert(_errorMessage[10]);
			return false;
		}
		if(DbUser=="") {
		   	$j("#DBuser").focus();
			alert(_errorMessage[11]);
			return false;
		}
		if(DbPassword==""){
			$j("#DBpassword").focus();
			alert(_errorMessage[12]);
			return false;
		}
		if(isNaN(port)){
	   		$j("#port").focus();
			alert(_errorMessage[13]);
			return false;
		}
		var parms = "domainName="+DomainName+"&dbType="+DbType+"&dbName="+DbName+
				"&hostName="+HostName+"&port="+port+"&dbUser="+DbUser+"&dbPassword="+DbPassword;
		new Ajax.Request(context+"/register.action",{
	 			method:'POST',
	 			parameters:parms,
	 			onSuccess: function(data) {
	 			data=JSON.parse(data.responseText);
	 				var result=data.response;
	 				if (data.result=="success"){
	 					alert(data.message);
	 					$j("#Load").load(context+"/JSP/DomainSettings.jsp");
				}else {
				alert(data.message);
				}
			}
		});

	});
	
	
	$j("#dmname1").change(function() {
        var index=$j(this).get(0).selectedIndex;
		$j("#dtype1").val(domain_value[index].DB_Type);
		$j("#dbname1").val(domain_value[index].DB_Name);
		$j("#hostname1").val(domain_value[index].host_Name);
		$j("#port1").val(domain_value[index].port);
		$j("#DBuser1").val(domain_value[index].DBUser_Name);
		$j("#DBpassword1").val(domain_value[index].DB_Password);
	});
	
	
	
	
	$j("#Update").click(function(){
		var DomainName=$j("#dmname1").val();
        var DbType=$j("#dtype1").val();
        var DbName=$j("#dbname1").val();
        var HostName=$j("#hostname1").val();
        var port=$j("#port1").val();
        var DbUser=$j("#DBuser1").val();
        var DbPassword=$j("#DBpassword1").val();
		if(DbName==""){
			$j("#dbname").focus();
			alert(_errorMessage[8]);
			return false;
		}
		if(HostName=="") {
		   	$j("#hostname").focus();
			alert(_errorMessage[9]);
			return false;
		}
		if(port==""){
			$j("#port").focus();
			alert(_errorMessage[10]);
			return false;
		}
		if(DbUser=="") {
		   	$j("#DBuser").focus();
			alert(_errorMessage[11]);
			return false;
		}
		if(DbPassword==""){
			$j("#DBpassword").focus();
			alert(_errorMessage[12]);
			return false;
		}
		if(isNaN(port)){
	   		$j("#port").focus();
			alert(_errorMessage[13]);
			return false;
		}
		var parms = "domainName="+DomainName+"&dbType="+DbType+"&dbName="+DbName+
					"&hostName="+HostName+"&port="+port+"&dbUser="+DbUser+"&dbPassword="+DbPassword;
		new Ajax.Request(context+"/update.action",{
	 			method:'POST',
	 			parameters:parms,
	 			onSuccess: function(data) {
	 			data=JSON.parse(data.responseText);
	 			var result=data.response;
	 				if (data.result=="success"){
	 					alert(data.message);
	 					$j("#Load").load(context+"/JSP/DomainSettings.jsp");
					}else {
					alert(data.message);
					}
				}
		});

	});
	$j("#Delete").click(function(){
		var DomainName=$j("#dmname1").val();
		var parms = "domainName="+DomainName;
		if(confirm(_errorMessage[14])) {
			new Ajax.Request(context+"/delete.action",{
    	 			method:'POST',
    	 			parameters:parms,
    	 			onSuccess: function(data) {
    	 			data=JSON.parse(data.responseText);
    	 				var result=data.response;
    	 				if (data.result=="success"){
    	 					alert(data.message);
    	 					$j("#Load").load(context+"/JSP/DomainSettings.jsp");
    	 					$j('#divNew').hide();
    	 					$j('#divUpdate').show();
						}else {
						alert(data.message);
						}
				}
    		});
		} else {
			return false;
		}
	});
});
