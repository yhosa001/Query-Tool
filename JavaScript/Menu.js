var $j=jQuery.noConflict();
var _domainName;
var _domainValue;
var _themecolor="";
var _index;
var _errorMessage=[];
var _context = $j("#context").val();
$j(document).ready(function(){
	var sessionValue=$j('#sessionVariable').val();
	var context=$j("#context").val();
	if (sessionValue=="" || sessionValue==null) {
		window.location.href=context+"/JSP/Login.jsp";
		return false;
	}
	$j("#Logout").click(function() {
		new Ajax.Request(context+"/clearSession.action",{
				method:'POST',
				async:false,
				onSuccess: function(data) {;
				if(data.responseText=="success"){
					window.location=context+"/JSP/Login.jsp";
				}
			}
		});
	});
	window.history.forward(-1);// to disable browser back button
	
	
	new Ajax.Request(context+"/domain.action",{
		method:'POST',
		async:false,
		onSuccess: function(data) {
			data=eval('('+data.responseText+')');
			_domainValue=data;
			var opt="";
			var value;
 			var len=data.length;
 			for(var i = 0; i <len; i++) {
	 			opt="<option class='domainOption'>"+data[i].domain_Name+"</option>";
	 			$j("#Domain").append(opt);
	 			_domainName=$j("#Domain option:selected").val();
	 			_index=$j("#Domain").get(0).selectedIndex;
	 		}
		 }
	});
	$j("#Domain").change(function() {
		_domainName=$j("#Domain option:selected").val();
		_index=$j(this).get(0).selectedIndex;
	});
	$j("#DomainSetting").click(function() {
		$j("#Load").load(context+"/JSP/DomainSettings.jsp");
	});
	
	$j("#Query").click(function() {
		//$j("#Load").load(context+"/JSP/QueryScreen.jsp");
		$j.ajax({
			  url: context+"/JSP/QueryScreen.jsp",
			  type: "POST",
			  async:false,
			  success:function(data){
			  fetchTableColumn(data);
			  }
		});
	});
});