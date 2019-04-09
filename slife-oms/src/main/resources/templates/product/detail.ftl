<html>
<head>
    <title>产品信息编辑</title>

    <link href="${rc.contextPath}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${rc.contextPath}/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${rc.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${rc.contextPath}/css/style.css?v=4.1.0" rel="stylesheet">
    <link href="${rc.contextPath}/css/slife.css" rel="stylesheet">
    <link href="${rc.contextPath}/css/plugins/select2/select2.css" rel="stylesheet">

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

    <script>
        var url = "${url}",  action = "${action}";

    </script>
    <script src="${rc.contextPath}/js/slife/slife.js"></script>
    <script src="${rc.contextPath}/js/slife/slifeform.js"></script>
    <link rel="stylesheet" type="text/css" href="${rc.contextPath}/css/plugins/jsTree/style.min.css"/>
    <script src="${rc.contextPath}/js/plugins/jsTree/jstree.min.js" type="text/javascript"></script>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">

                    <form class="form-horizontal form-bordered" id="slifeForm">
                        <input type="hidden" name="id" value="${product.id}"/>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">产品编号</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="proNo" placeholder="请输入产品编号"
                                       value="${product.proNo}" />
                            </div>
                            <label class="col-sm-2 control-label">产品名称<span class="required">*</span></label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="proName" placeholder="请输入产品标题"
                                       value="${product.proName}" required aria-required="true"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">产品描述</label>

                            <div class="col-sm-10">
                                <input name="description" type="text" class="form-control" value="${product.description}"
                                       placeholder="产品描述信息">
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="col-sm-2 control-label">计量单位</label>

                            <div class="col-sm-4">
                                <input name="proUtil" type="text" class="form-control" value="${product.proUtil}"
                                       placeholder="请输入计量单位">
                            </div>
                            <label class="col-sm-2 control-label">规格</label>

                            <div class="col-sm-4">
                                <input name="specifications" type="text" class="form-control" value="${product.specifications}"
                                       placeholder="产品规格">
                            </div>
                        </div>
                        

                        <div class="form-group">
                            <label class="col-sm-2 control-label">材料</label>
                            <div class="col-sm-4">
                                 <input name="material" type="text" class="form-control" value="${product.material}"
                                           placeholder="产品材料">
                            </div>

                            <label class="col-sm-2 control-label">型号</label>

                            <div class="col-sm-4">
                                <input name="models" type="text" class="form-control" value="${product.models}"
                                       placeholder="产品型号">
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="col-sm-2 control-label">成本价</label>

                            <div class="col-sm-4">
                                <input name="inPrice" type="text" class="form-control" value="${product.inPrice}"
                                       placeholder="成本价">
                            </div>
                            <label class="col-sm-2 control-label">销售价</label>

                            <div class="col-sm-4">
                                <input name="outPrice" type="text" class="form-control" value="${product.outPrice}"
                                       placeholder="销售价">
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
                            
                            <label class="col-sm-2 control-label">类型</label>
                            <div class="col-sm-4">
                                <select name="relation" class="form-control">
                                    <option value="1">工具</option>
                                    <option value="2">零件</option>
                                </select>
                            </div>
                        </div>
                        
                        <input type="hidden" id="categoryId" name="categoryId" class="form-control" value="${product.categoryId}">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">产品分类</label>
                            <div class="col-sm-8">
                                <input type="text" id="categoryShow" class="form-control" disabled placeholder="请选择产品分类" value="${product.categoryName}">
                                <!-- <div id="categoryShow" style="border:1px solid #e5e6e7;background-color:#fff; width: 100%; height:34px;"></div> -->
                            </div>
                            <span class="input-group-btn">
                               <button id="categorySelect" type="button" class="btn btn-default" 
                                   data-toggle="modal" data-target="#categorymodal">
                               	   <i class="fa fa-check"></i>选择
                               </button>
                            </span>
                        </div>
                        
                        <input type="hidden" id="relIds" name="relIds" class="form-control" value="${product.relIds}">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">关联产品</label>
                            <div class="col-sm-8">
                                <input type="text" id="prelShow" class="form-control" disabled placeholder="请选择关联产品"  value="${product.relNames}">
                            </div>
                            <span class="input-group-btn">
                               <button id="prelSelect" type="button" class="btn btn-default" 
                                   data-toggle="modal" data-target="#prelmodal">
                               	   <i class="fa fa-check"></i>选择
                               </button>
                            </span>
                        </div>
                        
                        <div class="form-group">
                            <label class="col-sm-2 control-label">备注</label>

                            <div class="col-sm-10">
                                <input name="remark" type="text" class="form-control" value="${product.remark}"
                                       placeholder="备注">
                            </div>
                        </div>
                        
                        <input type="hidden" name="imageURL" value="${product.imageURL}" id="imageURL"/>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">图片</label>
                            <div class="col-sm-5">
                                <div id="localImag" style="margin-left:15px;">
                                    <div class="img_box" id="imgBox">
                                		<#list product.images as image>
                                		<div id="imageShow${image_index}" style="display:inline-block">
                                        	<img style="width: 60px" src="${image}"
                                             onerror="javascript:errimg()" class="img_file img-rounded"/>

                                              <div class="img_edit_box">
                                                  <a class="img_desr" onclick="doDeleteImg('${image}',this)" href="javascript:void(0)">删除</a>
                                              </div>
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
        
        <!-- 模态框（Modal） -->
        <div class="modal fade" id="categorymodal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        	<div class="modal-dialog">
        		<div class="modal-content">
        			<div class="modal-header">
        				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
        					&times;
        				</button>
        				<h4 class="modal-title" id="myModalLabel">
        					请选择一个分类
        				</h4>
        			</div>
        			<div class="modal-body">
        				<div class="portlet box green-haze" style="padding: 15px">
                			<div class="portlet-body" id="cateTree"></div>
            			</div>
        			</div>
        		</div><!-- /.modal-content -->
        	</div><!-- /.modal -->
        </div>
        
    </div>
</div>


<script type="text/javascript">
	var count = 0

	$("#cateTree").jstree({
        "core": {
            "animation": 1,
            "themes": {
                theme: "classic",
                "dots": true,
                "icons": true
            },
            "check_callback": true,
            'data':${cateTree}
        },
        "types": {
            "default": {
                "valid_children": ["default", "file"]
            }
        },
        "plugins": ["wholerow", "state"]
    }).on("select_node.jstree", function (node, selectd) {
    	if (action='update'){
    		
    	}
    	if (!count) { // 防止新加载该页面执行下面代码
    		count++;
    		return;
    	}
        categoryid = selectd.node.id;
        categoryname = selectd.node.text;
        if (categoryid) {
            $("#categoryId").val(categoryid);
            $("#categoryShow").val(categoryname);
            $('#categorymodal').modal('hide');
        }
    });
    
    $("#rcateTree").on("loaded.jstree", function (event, data) {
        // 展开所有节点
        $('#resTree').jstree('open_all');
        // 展开指定节点
        //data.instance.open_node(1);     // 单个节点 （1 是顶层的id）
        //data.instance.open_node([1, 10]); // 多个节点 (展开多个几点只有在一次性装载后所有节点后才可行）
    });

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
     * 删除头像
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
    $("select[name=delFlag] option[value='${product.delFlag}']").attr("selected", "selected");
    $("select[name=relation] option[value='${product.relation}']").attr("selected", "selected");
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
</script>
</body>
</html>