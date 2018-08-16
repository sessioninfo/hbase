function clearInput1()
{
	$("#tableName1").val("");
	showSuccessOrErrorModal("清空成功!","success");
}

function dropTableByTableName()
{
	var tableName = $("#tableName1").val();
	if(!tableName)
	{
		showSuccessOrErrorModal("表名不能为空!","error");
		return;
	}
	
	$.ajax({
		type : "post",
		dataType : "json",
		traditional:true,
		url : "/hadoopClient/servlet/BaseServlet?methon=dropTableByTableName",
		data :
		{
			"tableName":tableName,
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
	$("#rowkey2").val("");
	showSuccessOrErrorModal("清空成功!","success");
}

function deleteByRowkey()
{
	var tableName = $("#tableName2").val();
	if(!tableName)
	{
		showSuccessOrErrorModal("表名不能为空!","error");
		return;
	}
	
	var rowkey = $("#rowkey2").val();
	if(!rowkey)
	{
		showSuccessOrErrorModal("rowkey不能为空!","error");
		return;
	}
	
	$.ajax({
		type : "post",
		dataType : "json",
		traditional:true,
		url : "/hadoopClient/servlet/BaseServlet?methon=deleteByRowkey",
		data :
		{
			"tableName":tableName,
			"rowkey":rowkey
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


