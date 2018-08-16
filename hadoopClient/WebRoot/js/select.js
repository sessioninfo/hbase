//基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));
option = {
		series : [{}]
		};
//使用刚指定的配置项和数据显示图表.参数true对之前数据清空
myChart.setOption(option,true);

function queryVisual()
{
	var tableName=$("#tableName3").val();
	if(!tableName)
	{
		showSuccessOrErrorModal("表名不能为空!","error");
		return;
	}
	
	var rowKeyS=$("#rowKeyS3").val();
	if(!rowKeyS)
	{
		showSuccessOrErrorModal("主语不能为空!","error");
		return;
	}
	
	$.ajax({
		type:"post",
		async: false, //同步执行
		dataType:"json",
		traditional:true,
		url:"/hadoopClient/servlet/BaseServlet?methon=queryVisual",
		data:
		{
			"tableName":tableName,
			"rowKeyS":rowKeyS
		},
		success:function(data)
		{
			if(data.status=="success")
			{
				//console.log(data);
				//返回的nodes值
				var nodes = data.nodes;
				//返回的links值
				var links = data.links;
				
				/*//尝试遍历赋值,未成功
				var strNodes = "";
				var arrNodes = [];
				for (var i in nodes) {
					var category = nodes[i].category;
					var name = nodes[i].name;
					var value = nodes[i].value;
					var label = nodes[i].label;
					strNodes = "{category:"+category+",name:'"+name+"',value:"+value+",label:'"+label +"'},"
					arrNodes.push(strNodes);
				}
				//尝试遍历赋值,未成功
				var strLinks = "";
				var arrlinks = [];
				for (var i in links) {
					var source = links[i].source;
					var target = links[i].target;
					var weight = links[i].weight;
					var name = links[i].name;
					strLinks = "{source:'"+source+"',target:'"+target+"',weight:"+weight+",name:'"+name +"'},"
					arrlinks.push(strLinks);
				}*/
				
				myChart.hideLoading();//隐藏加载动画
				option = {
					    title : {
					    	text: '表'+tableName+'主语'+rowKeyS+'的可视化查询',
					        subtext: '数据来自Hbase',
					        x:'right',
					        y:'bottom'
					    },
					    tooltip : {
					        trigger: 'item',
					        formatter: '{a} : {b}'
					    },
					    toolbox: {
					        show : true,
					        feature : {
					            restore : {show: true},
					            magicType: {show: true, type: ['force', 'chord']},
					            saveAsImage : {show: true}
					        }
					    },
					    legend: {
					        x: 'left',
					        data:['根节点','一级主语节点','一二级谓宾节点','二级主语节点']
					    },
					    series : [
					        {
					            type:'force',
					            name : "节点",
					            ribbonType: false,
					            categories : [
					                {
					                    name: '根节点'
					                },
					                {
					                	name: '一级主语节点'
					                },
					                {
					                	name: '一二级谓宾节点'
					                },
					                {
					                	name: '二级主语节点'
					                }
					            ],
					            itemStyle: {
					                normal: {
					                    label: {
					                        show: true,
					                        textStyle: {
					                            color: '#333'
					                        }
					                    },
					                    nodeStyle : {
					                        brushType : 'both',
					                        borderColor : 'rgba(255,215,0,0.4)',
					                        borderWidth : 1
					                    },
					                    linkStyle: {
					                        type: 'curve'
					                    }
					                },
					                emphasis: {
					                    label: {
					                        show: false
					                        // textStyle: null // 默认使用全局文本样式，详见TEXTSTYLE
					                    },
					                    nodeStyle : {
					                        //r: 30
					                    },
					                    linkStyle : {}
					                }
					            },
					            useWorker: false,
					            minRadius : 15,
					            maxRadius : 25,
					            gravity: 1.1,
					            scaling: 1.1,
					            roam: 'move',
					            nodes:nodes,
					            links:links
					        }
					    ]
					};
				 	
					//var ecConfig = require('echarts/config');
					var ecConfig = echarts.config;
					function focus(param) {
					    var data = param.data;
					    var links = option.series[0].links;
					    var nodes = option.series[0].nodes;
					    if(data.source !== undefined && data.target !== undefined)
					    {
					    	//点击的是边
					        var sourceNode = nodes.filter(function (n) {return n.name == data.source})[0];
					        var targetNode = nodes.filter(function (n) {return n.name == data.target})[0];
					        console.log("选中了边 " + sourceNode.name + ' -> ' + targetNode.name + ' (' + data.weight + ')');
					    }
					    else
					    { 
					    	// 点击的是点
					        console.log("选中了" + data.name + '(' + data.value + ')');
					    }
					}
					myChart.on(ecConfig.EVENT.CLICK, focus)
					myChart.on(ecConfig.EVENT.FORCE_LAYOUT_END, function () {
					    console.log(myChart.chart.force.getPosition());
					});
					
					//使用刚指定的配置项和数据显示图表。
					myChart.setOption(option,true);
			}
			else
			{
				showSuccessOrErrorModal(data.message,"error");
			}
		},
		error:function(data)
		{
			showSuccessOrErrorModal("请求错误!","error");
		}
	});
}

function clearVisualInput()
{
	$("#tableName3").val("");
	$("#rowKeyS3").val("");
	showSuccessOrErrorModal("清空成功!","success");
}

