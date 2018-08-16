/**
 * 弹窗弹出图片判断
 * @param {Object} type 类型 success/error/warning
 * @return {Object} src 返回图片链接 size 展示大小
 */
function showAlertImageFun(type)
{
	var imgUrl;
	switch (type){
		case 'success':
			imgUrl = '/hadoopClient/image/true.png';
			break;
		case 'error':
			imgUrl = '/hadoopClient/image/false.png';
			break;
		case 'fail':
			imgUrl = '/hadoopClient/image/false.png';
			break;
		default:
			break;
	}
	return {src:imgUrl,size: '120x120'};
}

//普通弹出框，content为弹出的信息
function showInfoModal(content) 
{
	swal({
		title : "",
		text : content,
		confirmButtonText : "确认",
		closeOnConfirm : true
	});
}

// 传入无参函数
// 判断型弹出框，content为弹出的信息，funt为点击确认后执行的具体方法
function showConfirmModal(content, funt) 
{
	var params = showAlertImageFun("warning");
	swal({
		title : "",
		text : content,
		imageUrl: params.src,
		imageSize: params.size,
		showCancelButton : true,
		cancelButtonText : "取消",
		confirmButtonText : "确认",
		closeOnConfirm : true
	}, function() {
		funt();
	});
}

// 传入1个参数的函数
// 判断型弹出框，content为弹出的信息，funt为点击确认后执行的具体方法，data为funt方法中的参数
function showConfirmModal(content, funt, data) 
{
	var params = showAlertImageFun("warning");
	swal({
		title : "",
		text : content,
		imageUrl: params.src,
		imageSize: params.size,
		showCancelButton : true,
		cancelButtonText : "取消",
		confirmButtonText : "确认",
		closeOnConfirm : true
	}, function() {
		funt(data);
	});
}

// 传入2个参数的函数
// 判断型弹出框，content为弹出的信息，funt为点击确认后执行的具体方法，data1，data2为funt方法中的参数
function showConfirmModal(content, funt, data1, data2) 
{
	var params = showAlertImageFun("warning");
	swal({
		title : "",
		text : content,
		imageUrl: params.src,
		imageSize: params.size,
		showCancelButton : true,
		cancelButtonText : "取消",
		confirmButtonText : "确认",
		closeOnConfirm : true
	}, function() {
		funt(data1, data2);
	});
}

// 传入3个参数的函数
// 判断型弹出框，content为弹出的信息，funt为点击确认后执行的具体方法，data1，data2，data3为funt方法中的参数
function showConfirmModal(content, funt, data1, data2, data3) 
{
	var params = showAlertImageFun("warning");
	swal({
		title : "",
		text : content,
		imageUrl: params.src,
		imageSize: params.size,
		showCancelButton : true,
		cancelButtonText : "取消",
		confirmButtonText : "确认",
		closeOnConfirm : true
	}, function() {
		funt(data1, data2, data3);
	});
}

/**
 * 成功或失败弹窗显示
 * @param {Object} content  为弹出的信息
 * @param {Object} textType 为弹出框类型 success,error,warning,info
 */
function showSuccessOrErrorModal(content,textType) 
{
	var params = showAlertImageFun(textType);
	swal({
		title : "",
		text : content,
		imageUrl: params.src,
		imageSize: params.size,
		confirmButtonText : "确认",
		closeOnConfirm : true		
	});	
}

/**
 * 成功或失败弹窗显示,有回调函数
 * @param {Object} content  为弹出的信息
 * @param {Object} textType 为弹出框类型 success,error,warning,info
 * @param {Object} funt		回调函数
 */
function showSuccessOrErrorModalBackFun(content,textType,funt) 
{
	var params = showAlertImageFun(textType);
	swal({
		title : "",
		text : content,
		imageUrl: params.src,
		imageSize: params.size,
		confirmButtonText : "确认",
		closeOnConfirm : true		
	},function() {
		funt();
	});	
}

/**
 * 成功或失败显示3秒钟后隐藏
 * @param {Object} content	为弹出的信息
 * @param {Object} textType	为弹出框类型	success,error,warning,info
 */
function showSuccessOrErrorTime(content,textType)
{
	var params = showAlertImageFun(textType);
	swal({
		title : "",
		text : content,
		imageUrl: params.src,
		imageSize: params.size,
		timer: 3000,
		confirmButtonText : "确认",
		closeOnConfirm : true		
	});	
}

/**
 * 成功或失败显示3秒钟后隐藏,有回调函数
 * @param {Object} content	为弹出的信息
 * @param {Object} textType	为弹出框类型	success,error,warning,info
 * @param {Object} funt		回调函数
 */
function showSuccessOrErrorTimeBackFun(content,textType,funt)
{
	var params = showAlertImageFun(textType);
	swal({
		title : "",
		text : content,
		imageUrl: params.src,
		imageSize: params.size,
		timer: 3000,
		confirmButtonText : "确认",
		closeOnConfirm : true		
	},function() {
		funt();
	});	
}

//传入无参函数
//判断型弹出框，content为弹出的信息，funt为点击确认后执行的具体方法，status为boolen值，true代表弹窗点击确定后关闭，false反之
function showTrueOrFalseModal(content, funt, status) 
{
	var params = showAlertImageFun("warning");
	swal({
		title : "",
		text : content,
		imageUrl: params.src,
		imageSize: params.size,
		showCancelButton : true,
		cancelButtonText : "取消",
		confirmButtonText : "确认",
		closeOnConfirm : status
	}, function() {
		funt();
	});
}

//传入1个参数的函数
//判断型弹出框，content为弹出的信息，funt为点击确认后执行的具体方法，data为funt方法中的参数，status为boolen值，true代表弹窗点击确定后关闭，false反之
function showTrueOrFalseModal(content, funt, data, status) 
{
	var params = showAlertImageFun("warning");
	swal({
		title : "",
		text : content,
		imageUrl: params.src,
		imageSize: params.size,
		showCancelButton : true,
		cancelButtonText : "取消",
		confirmButtonText : "确认",
		closeOnConfirm : status
	}, function() {
		funt(data);
	});
}

//传入2个参数的函数
//判断型弹出框，content为弹出的信息，funt为点击确认后执行的具体方法，data1，data2为funt方法中的参数，status为boolen值，true代表弹窗点击确定后关闭，false反之
function showTrueOrFalseModal(content, funt, data1, data2, status) 
{
	var params = showAlertImageFun("warning");
	swal({
		title : "",
		text : content,
		imageUrl: params.src,
		imageSize: params.size,
		showCancelButton : true,
		cancelButtonText : "取消",
		confirmButtonText : "确认",
		closeOnConfirm : status
	}, function() {
		funt(data1, data2);
	});
}

//传入3个参数的函数
//判断型弹出框，content为弹出的信息，funt为点击确认后执行的具体方法，data1，data2，data3为funt方法中的参数，status为boolen值，true代表弹窗点击确定后关闭，false反之
function showTrueOrFalseModal(content, funt, data1, data2, data3, status) 
{
	var params = showAlertImageFun("warning");
	swal({
		title : "",
		text : content,
		imageUrl: params.src,
		imageSize: params.size,
		showCancelButton : true,
		cancelButtonText : "取消",
		confirmButtonText : "确认",
		closeOnConfirm : status
	}, function() {
		funt(data1, data2, data3);
	});
}
