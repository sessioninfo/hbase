function createTable()
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
	
	$.ajax({
		type : "post",
		dataType : "json",
		traditional:true,
		url : "/hadoopClient/servlet/BaseServlet?methon=createTable",
		data :
		{
			"tableName":tableName,
			"familyName":familyName
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
	showSuccessOrErrorModal("清空成功!","success");
}
