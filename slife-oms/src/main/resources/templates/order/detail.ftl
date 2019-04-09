<html>
<head>
    <title>订单信息编辑</title>

    <link href="${rc.contextPath}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${rc.contextPath}/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${rc.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${rc.contextPath}/css/style.css?v=4.1.0" rel="stylesheet">
    <link href="${rc.contextPath}/css/slife.css" rel="stylesheet">
    <link href="${rc.contextPath}/css/plugins/select2/select2.css" rel="stylesheet">
    <link href="${rc.contextPath}/css/plugins/datepicker/datepicker3.css" rel="stylesheet">

    <link href="${rc.contextPath}/css/plugins/dropzone/dropzone.css" rel="stylesheet">
    <!-- 全局js -->
    <script src="${rc.contextPath}/js/jquery.min.js?v=2.1.4"></script>
    <script src="${rc.contextPath}/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${rc.contextPath}/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${rc.contextPath}/js/plugins/validate/messages_zh.min.js"></script>
    <script src="${rc.contextPath}/js/plugins/layer/layer.min.js"></script>
    <script src="${rc.contextPath}/js/jquery.form.js"></script>

    <script src="${rc.contextPath}/js/plugins/select2/select2.min.js"></script>
    <script src="${rc.contextPath}/js/plugins/dropzone/dropzone.min.js"></script>
	<script src="${rc.contextPath}/js/plugins/datepicker/bootstrap-datepicker.js"></script>
    <script>
        var url = "${url}",  action = "${action}";
		var process = "${process}";
    </script>
    <script src="${rc.contextPath}/js/slife/slife.js"></script>
    <script src="${rc.contextPath}/js/slife/slifeform.js"></script>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                	<#if process == 'todo'>
					<label style="margin-left: 100px;">
                            <button class="btn btn-success" onclick="check('${order.id}')">
                                <i class="glyphicon glyphicon-ok" aria-hidden="true"></i>确定该订单
                            </button>
                    </label>
                    </#if>
                    <form class="form-horizontal form-bordered" id="slifeForm">
                        <input type="hidden" name="id" value="${order.id}"/>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">订单编号</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="orderId" placeholder="请输入订单编号"
                                       value="${order.orderId}" />
                            </div>
                            <label class="col-sm-2 control-label">订单标题<span class="required">*</span></label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="title" placeholder="请输入订单标题"
                                       value="${order.title}" required aria-required="true"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">委托人</label>

                            <div class="col-sm-4">
                                <input name="bailor" type="text" class="form-control" value="${order.bailor}"
                                       placeholder="请输入委托人">
                            </div>
                            <label class="col-sm-2 control-label">要求时间</label>
                            <div class="col-sm-4">
                                 <input name="requireDate" id="requireDate" type="text" class="form-control dateinput" value="${order.requireDate?string('yyyy-MM-dd')}"
                                           placeholder="请选择预计完成时间">
                            </div>
                        </div>
                        

                        <div class="form-group">
                        	
                            <label class="col-sm-2 control-label">订单金额</label>
                            <div class="col-sm-4">
                                 <input name="sumMoney" type="text" class="form-control" value="${order.sumMoney}"
                                           placeholder="请输入订单金额">
                            </div>
                            <label class="col-sm-2 control-label">客户姓名</label>
                            <div class="col-sm-4">
                                 <input name="cusName" type="text" class="form-control" value="${order.cusName}"
                                           placeholder="请输入客户姓名">
                            </div>

                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">客户单位</label>
                            <div class="col-sm-4">
                                 <input name="cusCompany" type="text" class="form-control" value="${order.cusCompany}"
                                           placeholder="请输入客户单位">
                            </div>
                            <label class="col-sm-2 control-label">客户电话</label>

                            <div class="col-sm-4">
                                <input name="cusPhone" type="text" class="form-control" value="${order.cusPhone}"
                                       placeholder="客户电话">
                            </div>
                        	
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">客户地址</label>

                            <div class="col-sm-10">
                                <input name="cusAddress" type="text" class="form-control" value="${order.cusAddress}"
                                       placeholder="请输入客户地址">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">客户描述</label>

                            <div class="col-sm-10">
                                <input name="cusDesc" type="text" class="form-control" value="${order.cusDesc}"
                                       placeholder="客户描述">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">备注</label>

                            <div class="col-sm-10">
                                <input name="remark" type="text" class="form-control" value="${order.remark}"
                                       placeholder="备注">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">状态</label>
                            <div class="col-sm-4">
                                <select name="delFlag" class="form-control">
                                    <option value="Y">启用</option>
                                    <option value="N">禁用</option>
                                </select>
                            </div>
                        </div>
                        
                        
                        <input type="hidden" name="image" value="${order.image}" id="imageURL"/>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">图片</label>
                            <div class="col-sm-5">
                                <div id="localImag" style="margin-left:15px;">
                                    <div class="img_box" id="imgBox">
                                		<#list order.images as img>
                                		<div id="imageShow${img_index}" style="display:inline-block">
                                        	<img style="width: 60px" src="${img}"
                                             onerror="javascript:errimg()" class="img_file img-rounded"/>
											 <#if action != 'detail'>
                                             <div class="img_edit_box">
                                                 <a class="img_desr" onclick="doDeleteImg('${img}',this)" href="javascript:void(0)">删除</a>
                                             </div>
                                             </#if>
                                        </div>
                                		</#list>
                                    </div>
                                </div>
                        	<#if action !='detail'>
                                <div id="mydropzone" class="dropzone"></div>
                        	</#if>
                            </div>
                        </div>

                    <#if action !='detail'>
                        <div class="form-actions fluid">
                            <div class="col-md-offset-3 col-md-9">
                                <button type="submit" class="btn green">保存</button>
                            </div>
                        </div>
                    </#if>
                    </form>
                </div>
            </div>
        </div>
        
    </div>
</div>


<script type="text/javascript">
	
    function  cusFunction() {
        console.info("提交之前，最后执行自定义的函数");
    }
    
    /**
     * 错误图片的默认处理
     */
    function errimg() {
        layer.alert('图片上传失败，请重试或联系管理员！');
    }


    /**
     * 删除图片
     */
    function doDeleteImg(url, obj) {
        var urls = $("#imageURL").val();
        layer.confirm('确定要删除该图片吗？', {
            btn: ['确定', '取消']
        }, function () {
        	console.log($(obj).html());
            $("#imageURL").val(exclude(urls, url));
            $(obj).parent().parent().remove();
            layer.closeAll('dialog');
        })
    }
    
    function exclude(urls, url){
    	
    	var newUrls = "";
    	var urlArr = urls.split(",");
    	var index = urlArr.indexOf(url);
    	urlArr.splice(index, 1);
    	return urlArr.join(',')
    }

    <#if action !='insert'>
    $("select[name=delFlag] option[value='${order.delFlag}']").attr("selected", "selected");
    </#if>

    var form = $('#slifeForm');
    var error = $('.alert-danger', form);
    form.validate({
        errorElement: 'span',
        errorClass: 'error',
        focusInvalid: true,
        rules: {
            proName: {
                required: true
            },
            active: {
                required: true
            }
        }
    });
	
	<#if action != 'detail'>
    Dropzone.autoDiscover = false;
    var myDropzone = new Dropzone("div#mydropzone", {
        url: "/file/upload/avatar",
        filesizeBase: 1024,//定义字节算法 默认1000
        maxFiles: 5,//最大文件数量
        maxFilesize: 100, //MB
        fallback: function () {
            layer.alert('暂不支持您的浏览器上传!');
        },
        uploadMultiple: false,
        addRemoveLinks: true,
        dictFileTooBig: '您的文件超过' + 100 + 'MB!',
        dictInvalidInputType: '不支持您上传的类型',
        dictMaxFilesExceeded: '您的文件超过1个!',
        init: function () {
            this.on('queuecomplete', function (files) {
                // layer.alert('上传成功');
            });
            this.on('success', function (uploadimfo, result) {
                var urls = $("#imageURL").val();
                if (!urls){
                	urls += result.message[0].s_url;
                }else{
                	urls += ("," + result.message[0].s_url);
                }
                $("#imageURL").val(urls);
                $("#imgshowdiv").attr('src', result.message[0].s_url);
                layer.alert('上传成功');
            });
            this.on('error', function (a, errorMessage, result) {
                if (!result) {
                    layer.alert(result.error || '上传失败');
                }
            });
            this.on('maxfilesreached', function () {
                //this.removeAllFiles(true);
                layer.alert('文件数量超出限制');
            });
            this.on('removedfile', function () {
                $("#photo").val("${sysUser.photo}");
                $("#imgshowdiv").attr('src', " {sysUser.photo}");
                layer.alert('删除成功');
            });
        }
    });
	</#if>
    
    $(function(){
    	//加载日期控件
		var option = {
			language : "zh-CN",
			format : 'yyyy-mm-dd',
			autoclose : true,// 选中之后自动隐藏日期选择框
			clearBtn : true// 清除按钮
		};
		$('.dateinput').each(function(){
			$(this).datepicker(option);
		});
		
		if(action == 'detail'){
			$(".form-control").each(function(){
				$(this).attr('disabled' , 'disabled');
			});
		}
    });
    
    /**
     * 订单明细按钮
     * @returns {*}
     */
    function dt_orderdetail_button(row) {
        var detailO = '<a class="btn btn-success btn-sm" href="#" title="详情"  mce_href="#" onclick="dt_detail(\'' + row.id + '\')"><i class="fa fa-info-circle"></i></a> ';
        return detailO;
    }
    
    
    /**
     * 打开订单明细框
     */
    function dt_insert() {
        dt_action("订单明细", "orderdetail");
    }
    
    function check(id){
    	layer.confirm('确定要审核该订单吗？', {
            btn: ['确定', '取消']
        }, function () {
            $.ajax({
                type: "GET",  //提交方式
                url: url + "todo/check/" + id, // 服务器数据的加载地址
                dataType: "json",
                contentType: "application/json; charset=utf-8",//(可以)
                success: function (result) {//返回数据根据结果进行相应的处理
        			alert("审核成功");
                }
            });
            layer.closeAll('dialog');
        })
    }
</script>
</body>
</html>