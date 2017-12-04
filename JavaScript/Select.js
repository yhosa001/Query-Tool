var $j=jQuery.noConflict();
var _joinColumn1;
var _joinColumn2;
function fetchTableColumn(JSPPage){
	var context = $j("#context").val();
	var params = "domainName="+_domainName;
	new Ajax.Request(context+"/connection.action",{
		method:'POST',
		parameters:params,
		onSuccess: function(data) {
			data=eval('('+data.responseText+')');
			if(data.result == "sessionTimeout"){
				window.location=context+"/JSP/Login.jsp";
				return false;
			}
			if(data.result == "error"){
				alert(data.message);
				window.location=context+"/JSP/Menu.jsp";
				return false;
			} else {
				$j("#Load").html(JSPPage);
				$j('#joinScreen,#filterScreen,#queryExecuteScreen,#resultPage,#radio').hide();
				$j("label[for='headdinglabel']").text("Display Columns");
				$j("label[for='myvalue']").text(_domainName);
				$j("#domainName").val(_domainName);
				_domainValue=data;
				var opt="";
	 			var len=data.length;
	 			for(var i = 0; i <len; i++) {
	 				opt="<li>"+data[i].tables+"</li>";
		 			$j("#tablelist").append(opt);
					$j("#tablelist1").append(opt);
					$j("#tablelist2").append(opt);
					$j("#filterTablelist").append(opt);
					$j( ".list li" ).first().css( "background-color", "#ffffff" );
					$j( ".tablelist1 li" ).first().css( "background-color", "#ffffff" );
					$j( ".tablelist2 li" ).first().css( "background-color", "#ffffff" );
					$j( ".filterTablelist li" ).first().css( "background-color", "#ffffff" );
		 			if(i==0) {
		 				table_Name=data[i].tables;
		 				var params="tableName="+table_Name+"&domainName="+_domainName;
		 				new Ajax.Request(context+"/getColumns.action",{
		 					method:'POST',
							parameters:params,
							onSuccess: function(data) {
								data=eval('('+data.responseText+')');
								_domainValue=data;
								var col="";
								var dtype="";
					 			var len=data.length;
					 			for(var i = 0; i <len; i++) {
					 				col="<li id="+data[i].type+">"+table_Name+"."+data[i].columns+"</li>";
						 			dtype="<input type='hidden' id="+data[i].columns+" value="+data[i].type+"/>";
						 			$j("#dtype").append(dtype);
						 			$j("#dtype1").append(dtype);
						 			$j("#dtype2").append(dtype);
						 			//$j("#filterdtype").append(dtype);
						 			$j("#columnlist").append(col);
						 			$j("#columnlist1").append(col);
						 			$j("#columnlist2").append(col);
						 			$j("#filtercolumnlist").append(col);
						 			$j( ".columnlist1 li" ).first().css( "background-color", "#ffffff" );
						 			$j( ".columnlist2 li" ).first().css( "background-color", "#ffffff" );
						 			//Join_Column1=$j('ul#columnlist1 li:first').text();
						 			//Join_Column2=$j('ul#columnlist2 li:first').text();
						 			_joinColumn1=$j('ul#columnlist1 li:first').text();
						 			_joinColumn2=$j('ul#columnlist1 li:first').text();
					 			}
							}
		 				});
		 			}
	 			}
				
			}
		}
	});
	var _selectedColumn;
	var _columnSelected;
	var table_Name;
	var _filterColumn;
	var _resultValues;
	var _operator;
	var index;
	var _tables=[];
	var lists= [];
	var _query;
	var _columnsLength;
	$j('#Load').on('click','.list li',function(){
		var table = jQuery(this).text();
		$j(".list li").css('background',"transparent");
		$j(this).css('background-color',"#ffffff");
		$j('#columnlist').empty();
		var id="tablelist";
		fetchColums(table,id);
	});
	$j('#Load').on('click','.collist li',function(){
		var column = jQuery(this).text();
		_selectedColumn=column;
		$j(".collist li").css('background',"transparent");
		$j(this).css('background-color',"#ffffff");
	});
	$j('#Load').on('click','#select',function(){
		if(_selectedColumn==null){
			alert("Please Select The Column");
		} else {
			var selectedColumn="<li>"+_selectedColumn+"</li>"
			$j("#displayColumn").append(selectedColumn);
			_columnSelected=_selectedColumn;
			index =$j('.selectedList li').last().index();
			$j(".selectedList li").css('background',"transparent")
			$j('.selectedList li').last().css( "background-color", "#ffffff" );
		}
	});
	$j('#Load').on('click','.selectedList li',function(){
		var column = jQuery(this).text();
		index = $j(this).index();
		var type=column.split(".").pop();
		_columnSelected=column;
		$j(".selectedList li").css('background',"transparent")
		$j(this).css('background-color',"#ffffff")
	});
	$j('#Load').on('click','#clearAll',function(){
		$j('#displayColumn').empty();
	});
	$j('#Load').on('click','#clear',function(){
		$j("#displayColumn li").eq(index).remove();
		$j('.selectedList li').last().css( "background-color", "#ffffff" );
		index =$j('.selectedList li').last().index();
	});
	$j('#Load').on('click','#next',function(){
	    if($j('#displayColumn li').length == 0) {
	    	alert("Please select columns to display");
	    } else {
    		$j("label[for='headdinglabel']").text("Join condition");
			$j('#selectScreen').hide();
			$j('#filterScreen').hide();
			$j('#queryExecuteScreen').hide();
			$j('#resultPage').hide();
			$j('#joinScreen').show();
			$j('#text').val("join conditions");
		}
	});
	$j('#Load').on('click','.filterTablelist li',function(){
		var table = jQuery(this).text();
		$j(".filterTablelist li").css('background',"transparent")
		$j(this).css('background-color',"#ffffff")
		$j('#filtercolumnlist').empty();
		id="filterTablelist";
		fetchColums(table,id)
	});
	$j('#Load').on('click','.tablelist1 li',function(){
		var table = jQuery(this).text();
		$j(".tablelist1 li").css('background',"transparent")
		$j(this).css('background-color',"#ffffff")
		$j('#columnlist1').empty();
		id="tablelist1";
		fetchColums(table,id);
	});
	$j('#Load').on('click','.tablelist2 li',function(){
		var table = jQuery(this).text();
		$j(".tablelist2 li").css('background',"transparent")
		$j(this).css('background-color',"#ffffff")
		$j('#columnlist2').empty();
		id="tablelist2";
		fetchColums(table,id)
	});
	$j('#Load').on('click','.columnlist1 li',function(){
		var column = jQuery(this).text();
		_joinColumn1=column;
		$j(".columnlist1 li").css('background',"transparent")
		$j(this).css('background-color',"#ffffff")
	});
	$j('#Load').on('click','.columnlist2 li',function(){
		var column = jQuery(this).text();
		_joinColumn2=column;
		$j(".columnlist2 li").css('background',"transparent")
		$j(this).css('background-color',"#ffffff")
	});
	$j('#Load').on('click','#previous',function(){
		$j("label[for='headdinglabel']").text("Display Columns");
		$j('#joinScreen').hide();
		$j('#filterScreen').hide();
		$j('#queryExecuteScreen').hide();
		$j('#resultPage').hide();
		$j('#selectScreen').show();
	});
	$j('#Load').on('click','#join',function(){
		var join_Column1_Type=_joinColumn1.split(".").pop();
		var join_Column2_Type=_joinColumn2.split(".").pop();
		var leftcolumn=$j("#" + join_Column1_Type).val();
		var rightcolumn=$j("#" + join_Column2_Type).val();
		if(leftcolumn==rightcolumn){
			var condition;
			condition="<li>"+_joinColumn1+"="+_joinColumn2+"</li>";
			$j("#joinlist").append(condition);
		} else {
			alert(_errorMessage[22]);
		}
	});
	$j('#Load').on('click','#clearJoins',function(){
		$j("#joinlist").empty();
	});

	$j('#Load').on('click','#next1',function(){
		$j("label[for='headdinglabel']").text("Filter condition");
		$j('#selectScreen').hide();
		$j('#joinScreen').hide();
		$j('#queryExecuteScreen').hide();
		$j('#resultPage').hide();
		$j('#filterScreen').show();
		$j('#text').val("Filter conditions");
	});
	$j('#Load').on('click','#previous1',function(){
		$j("label[for='headdinglabel']").text("Join condition");
		$j('#joinScreen').show();
		$j('#filterScreen').hide();
		$j('#selectScreen').hide();
		$j('#resultPage').hide();
		$j('#queryExecuteScreen').hide();
	});
	$j('#Load').on('click','.filtercolumnlist li',function(){
		var column = jQuery(this).text();
		_filterColumn=column;
		$j(".filtercolumnlist li").css('background',"transparent")
		$j(this).css('background-color',"#ffffff")
	});
	
	$j('#Load').on('change','#operator',function(){
		_operator= $j('#operator :selected').text();
		if(_operator=="BETWEEN" || _operator=="NOT BETWEEN") {
			$j("#divValue1").show();
			$j("#divValue2").show();
		} else if(_operator=="IS NULL" || _operator=="IS NOT NULL") {
			$j("#divValue1").hide();
			$j("#divValue2").hide();
		} else{
			$j("#divValue1").show();
			$j("#divValue2").hide();
		}
	});
	$j('#Load').on('click','#set',function(){
		$j(".filtercolumnlist li").css('background',"transparent")
		if (_filterColumn==null) {
			alert("Select the Column to Set the filter condition");
		}
		_operator= $j('#operator :selected').text();
		var filterColumnType=_filterColumn.split(".").pop();
		var datatype=$j("#" + filterColumnType).val();
		var value1=$j("#value1").val();
		var value2=$j("#value2").val();
		if (datatype!="NUMBER/"){
			value1="'"+value1+"'";
			value2="'"+value2+"'";
		}
		var concat =$j("input[name=AndOr]:checked").val();
		var condition;
		if (_operator=="BETWEEN" || _operator=="NOT BETWEEN") {
			if (value1=="" || value2=="") {
				alert(_errorMessage[23]);
				return false;
			}else {
				if($j('#filtercondition li').length == 0) {
				    condition="<li>"+_filterColumn+" "+_operator+" "+value1+" and "+value2+"</li>";
					$j("#filtercondition").append(condition);
					_filterColumn=null;
					$j('#value1').attr("value", "");
					$j('#value2').attr("value", "");
				}else {
					condition="<li>"+" "+concat+" "+_filterColumn+" "+_operator+" "+value1+" and "+value2+"</li>";
					$j("#filtercondition").append(condition);
					_filterColumn=null;
					$j('#value1').attr("value", "");
					$j('#value2').attr("value", "");
				}
			}
		}else if(_operator=="IS NULL" || _operator=="IS NOT NULL"){
			if($j('#filtercondition li').length == 0) {
				condition="<li>"+_filterColumn+" "+_operator+"</li>";
				$j("#filtercondition").append(condition);
			}else {
				condition="<li>"+" "+concat+" "+_filterColumn+" "+_operator+"</li>";
				$j("#filtercondition").append(condition);
			}
		}else {
			if (value1=="") {
				alert("Please Enter the Value");
				return false;
			}
			if (_operator=="IN" ||_operator=="NOT IN") {
						value1="("+value1+")";
			}
			if($j('#filtercondition li').length == 0) {
					condition="<li>"+_filterColumn+" "+_operator+" "+value1+"</li>";
					$j("#filtercondition").append(condition);
					_filterColumn=null;
					$j('#value1').attr("value", "");
			} else {
					condition="<li>"+" "+concat+" "+_filterColumn+" "+_operator+" "+value1+"</li>";
					$j("#filtercondition").append(condition);
					_filterColumn=null;
					$j('#value1').attr("value", "");
			}
		}
		if($j('#filtercondition li').length == 0) {
			$j("#radio").hide();
		} else {
			$j("#radio").show();
		}
	});
	$j('#Load').on('click','#clearFilter',function(){
		$j("#filtercondition").empty();
		$j("#radio").hide();
	});
	$j('#Load').on('click','#next2',function(){

	    $j("label[for='headdinglabel']").text("Output Screen");
		var displayColumns = $j(".selectedList li");
		var joinColumns=$j(".joinlist li");
		var filterColumns=$j(".filtercondition li");
		var displayList;
		var table;
		var split;
		var selectTables = [];
		var displayColumn = [];
		var joincondition = [];
		var filtercondition = [];
		var joinDiff = [];
		var joinTables = [];
		var filterTables = [];
	/* Select Screen */
		displayColumns.each(function(index) {
   			displayList = $j( this ).text();
   			displayColumn.push(displayList);
   			split=displayList.split(".");
   			table=split[0];
   			selectTables.push(table);
		});
		_columnsLength=(displayColumn.length);
		var singleSelect=selectTables.filter(function(itm,i,selectTables){
    		return i==selectTables.indexOf(itm);
		});
	/* Join Screen */
		joinColumns.each(function(index) {
   			displayList = $j( this ).text();
   			if($j('.filtercondition li').length == 0) {
   				if (index==0){
    				joincondition.push(displayList);
    				split=displayList.split("=");
	    			table1=split[0];
	    			table2=split[1];
	    			table1=table1.split(".");
	    			table2=table2.split(".");
	    			table1=table1[0];
	    			table2=table2[0];
	    			joinTables.push(table1);
	    			joinTables.push(table2);
    			} else {
    				Joinvalue=" AND "+displayList;
    				joincondition.push(Joinvalue);
    				split=displayList.split("=");
	    			table1=split[0];
	    			table2=split[1];
	    			table1=table1.split(".");
	    			table2=table2.split(".");
	    			table1=table1[0];
	    			table2=table2[0];
	    			joinTables.push(table1);
	    			joinTables.push(table2);
    			}
   			 } else {
    			Joinvalue=" AND "+displayList;
    			joincondition.push(Joinvalue);
    			split=displayList.split("=");
    			table1=split[0];
    			table2=split[1];
    			table1=table1.split(".");
    			table2=table2.split(".");
    			table1=table1[0];
    			table2=table2[0];
    			joinTables.push(table1);
    			joinTables.push(table2);
    		}
		});
		var fromTables=joinTables.filter(function(itm,i,joinTables){
    		return i==joinTables.indexOf(itm);
		});
	/* FilterScreen*/
		filterColumns.each(function(index) {
   			displayList = $j( this ).text();
   			filtercondition.push(displayList);
   			if (index==0) {
    			split=displayList.split(".");
    			table=split[0];
    			filterTables.push(table);
   			} else {
   				split=displayList.split(" ");
   				table=split[2];
   				split=table.split(".");
   				table=split[0];
   				filterTables.push(table);
   			}
		});
		var wherecondition=filterTables.filter(function(itm,i,filterTables){
    		return i==filterTables.indexOf(itm);
		});
		/* Without Joins */
		if (singleSelect.length == 1 && wherecondition.length ==0) {
			/* Without Joins */
			if($j('.joinlist li').length == 0) {
				_query="Select "+displayColumn+"\n  From "+singleSelect;
				_tables=singleSelect;
			}else{
      			/*Query="Select "+displayColumn+"\n  From "+singleSelect;*/
      			var diffSingle = [];
    					jQuery.grep(singleSelect, function(el) {
    					 if (jQuery.inArray(el, fromTables) == -1) diffSingle.push(el);
			        });
			 $j.each(diffSingle, function( index, value ) {
							fromTables.push(value);
						});
      			 _query="Select "+displayColumn+"\n From "+fromTables+" Where "+filtercondition.join("")+" "+joincondition.join("");
      			_tables=fromTables;
      		}
       	}  else if(singleSelect.length == 1 && wherecondition.length == 1) {
				var diffSingle1 = [];
				jQuery.grep(singleSelect, function(el) {
					if (jQuery.inArray(el, wherecondition) == -1) diffSingle1.push(el);
		        });
		        if(diffSingle1.length==0 && ($j('.joinlist li').length == 0)){
					_query="Select "+displayColumn+"\n From "+singleSelect+" Where "+filtercondition.join("");
					_tables=singleSelect;
				} else {
					$j.each(wherecondition, function( index, value ) {
							singleSelect.push(value);
			       });
			  		var diffSingle = [];
	 				jQuery.grep(singleSelect, function(el) {
	 					if (jQuery.inArray(el, fromTables) == -1) diffSingle.push(el);
	       			});
	 				$j.each(diffSingle, function( index, value ) {
						fromTables.push(value);
					});
					if(diffSingle.length==0){
   			 				_query="Select "+displayColumn+"\n From "+fromTables+" Where "+filtercondition.join("")+" "+joincondition.join("");
   							_tables=fromTables;
	   				} else {
							alert("( "+diffSingle+" ) : These tables must be join in the join condition ");
							return false;
						}
				}
		}	else {
        		jQuery.grep(selectTables, function(el) {
        			if (jQuery.inArray(el, joinTables) == -1) joinDiff.push(el);
				});
				if($j('#filtercondition li').length != 0) {
					jQuery.grep(filterTables, function(el) {
		        		if (jQuery.inArray(el, joinTables) == -1) joinDiff.push(el);
					});
				}
				var unique=joinDiff.filter(function(itm,i,joinDiff){
    				return i==joinDiff.indexOf(itm);
			    });
				if(unique.length != 0){
					alert("( "+unique+" ) : These tables must be joined in join conditions");
					return false;
	       	    }
	       	 	_query="Select "+displayColumn+"\n From "+fromTables+" Where "+filtercondition.join("")+" "+joincondition.join("");
				 _tables=fromTables;
		}
	/*get the join and Select*/
		$j("#queryHidden").val(_query);
		$j("#query").empty();
   		$j("#query").val(_query);
		$j('#filterScreen,#selectScreen,#joinScreen').hide();
        $j('#queryExecuteScreen').show();	
	});
	$j('#Load').on('click','#previous2',function(){
		$j("label[for='headdinglabel']").text("Filter condition");
		$j("#query").empty();
		$j('#filterScreen').show();
		$j('#selectScreen,#joinScreen,#queryExecuteScreen').hide();
	});
	
	var recordsPerPage;
	var displayRecords;
	var totalNumOfRecords;
	var start = 0;
	var end = 1;
	var initial =1;
	$j('#Load').on('click','#execute',function(){
		var option = $j("input[name=Format]:checked").val();
		if(option=="CSV" || option=="TEXT"){
			$j("#format").submit();
		}
		if(option=="HTML"){
			var query = encodeURIComponent($j("#query").val());
			if(!/Select/i.test(query)){
				alert("Query will be executed for Select Statement only")
			}
			var params = "query="+query+"&domainName="+_domainName+"&tables="+_tables;
			new Ajax.Request(context+"/execute.action",{
				method:'POST',
				parameters:params,
				async:false,
				onSuccess: function(data){
					alert("success");
				}
			});
		}
		
	});000000	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	function fetchColums(table,id){
		table_Name=table;
		var context=$j("#context").val();
		var params="tableName="+table_Name+"&domainName="+_domainName;
		new Ajax.Request(context+"/getColumns.action",{
			method:'POST',
			async:false,
			parameters:params,
			onSuccess: function(data) {
				data=eval('('+data.responseText+')');
				var col="";
				var dtype="";
				var len=data.length;
				for(var i = 0; i <len; i++) {
		 			col="<li>"+table_Name+"."+data[i].columns+"</li>";
		 			dtype="<input type='hidden' id="+data[i].columns+" value="+data[i].type+"/>";
		 			if(id=="tablelist") {
			 			$j("#dtype").append(dtype);
			 			$j("#columnlist").append(col);
			 		}
		 			if(id=="tablelist1") {
			 			$j("#dtype1").append(dtype);
			 			$j("#columnlist1").append(col);
			 			$j( ".columnlist1 li" ).first().css( "background-color", "#ffffff" );
			 			_joinColumn1=$j('ul#columnlist1 li:first').text();
			 			var x;
			 		}
			 		if(id=="tablelist2") {
			 			$j("#dtype2").append(dtype);
			 			$j("#columnlist2").append(col);
			 			$j( ".columnlist2 li" ).first().css( "background-color", "#ffffff" );
			 			_joinColumn2=$j('ul#columnlist2 li:first').text();
			 		}
			 		if(id=="filterTablelist") {
			 			$j("#filterdtype").append(dtype);
			 			$j("#filtercolumnlist").append(col);
			 		}
				}
			}
		});
	}

}