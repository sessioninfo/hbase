function insertData()
{
	var tableName = $("#tableName").val();
	if(!tableName)
	{
		showSuccessOrErrorModal("表名不能为空!","error");
		return;
	}
	
	var familyName = $("#familyName").val();
	if(!familyName)
	{
		showSuccessOrErrorModal("列族不能为空!","error");
		return;
	}
	
	var rowKeyS = $("#rowKeyS").val();
	if(!rowKeyS)
	{
		showSuccessOrErrorModal("主语不能为空!","error");
		return;
	}
	
	var colValP = $("#colValP").val();
	if(!colValP)
	{
		showSuccessOrErrorModal("谓语不能为空!","error");
		return;
	}
	
	var colValO = $("#colValO").val();
	if(!colValO)
	{
		showSuccessOrErrorModal("宾语不能为空!","error");
		return;
	}
	
	$.ajax({
		type : "post",
		dataType : "json",
		traditional:true,
		url : "/hadoopClient/servlet/BaseServlet?methon=insertData",
		data :
		{
			"tableName":tableName,
			"familyName":familyName,
			"rowKeyS":rowKeyS,
			"colValP":colValP,
			"colValO":colValO
		},
		success : function(data)
		{
			if(data.status=="success")
			{
				showSuccessOrErrorModal(data.message,"success");
			}
			else
			{
				showSuccessOrErrorModal(data.message,"error");
			}
		},
		error:function(data)
		{
			
		}
		});
}

function clearInput()
{
	$("#tableName").val("");
	$("#familyName").val("");
	$("#rowKeyS").val("");
	$("#colValP").val("");
	$("#colValO").val("");
	showSuccessOrErrorModal("清空成功!","success");
}

function importFile()
{
	var tableName = $("#tableName2").val();
	if(!tableName)
	{
		showSuccessOrErrorModal("表名不能为空!","error");
		return;
	}
	
	var familyName = $("#familyName2").val();
	if(!familyName)
	{
		showSuccessOrErrorModal("列族不能为空!","error");
		return;
	}
	
	var filePath = $("#filePath2").val();
	if(!filePath)
	{
		showSuccessOrErrorModal("txt文件路径不能为空!","error");
		return;
	}
	
	$.ajax({
		type : "post",
		dataType : "json",
		traditional:true,
		url : "/hadoopClient/servlet/BaseServlet?methon=importFile",
		data :
		{
			"tableName":tableName,
			"familyName":familyName,
			"filePath":filePath
		},
		success : function(data)
		{
			if(data.status=="success")
			{
				showSuccessOrErrorModal(data.message,"success");
			}
			else
			{
				showSuccessOrErrorModal(data.message,"error");
			}
		},
		error:function(data)
		{
			
		}
		});
}

function clearInput2()
{
	$("#tableName2").val("");
	$("#familyName2").val("");
	$("#filePath2").val("");
	showSuccessOrErrorModal("清空成功!","success");
}
