<html>
<head>
    <title>产品管理</title>
    <link rel="stylesheet" type="text/css" href="${rc.contextPath}/css/plugins/jsTree/style.min.css"/>
    <link href="${rc.contextPath}/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${rc.contextPath}/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="${rc.contextPath}/css/animate.css" rel="stylesheet">
    <style>
        #category_edit_table .control-label {
            text-align: left !important;
        }

        #category_edit_table input, #category_edit_tacategoryselect {
            margin-left: -45px;
        }

        #category_edit_table .row {
            margin-top: 15px !important;
        }

    </style>
    <script>
        var url = "${url}";
        var preUri = "${preUri}";
    </script>
</head>


<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">

    <div class="row">

		<!-- 资源树 组件 -->
        <div class="col-md-2">
            <div class="portlet box green-haze" style="padding: 15px">
                <div class="portlet-body" id="resTree"></div>
            </div>
        </div>

		<!-- 数据表组件 -->
        <div class="col-md-10">
            <div class="ibox">
                <div class="ibox-body">
    
                    <div id="exampleToolbar" role="group">
    
                        <input id="categoryId" type="hidden" class="form-filter input-sm _search" name="search_start_category_id">
                        <label style="margin-left: 10px;">
                            产品名称：
                            <input type="text" class="form-filter input-sm _search" name="search_like_pro_name">
                        </label>
                        <label style="margin-left: 10px;">
                            产品编号：
                            <input type="text" class="form-filter input-sm _search" name="search_like_pro_no">
                        </label>
                        <label style="margin-left: 10px;">
                            类型：
                            <select name="search_eq_relation" class="form-filter input-sm _search">
                                <option value="">--请选择--</option>
                                <option value="1">产品</option>
                                <option value="2">零件</option>
                             </select>
                        </label>
    
                        <label style="margin-left: 10px;">
                            <button class="btn btn-success" onclick="re_load()">
                                <i class="fa fa-search" aria-hidden="true"></i>查询
                            </button>
                            <button type="button" class="btn  btn-primary" onclick="reset()">
                                <i class="fa fa-circle-thin" aria-hidden="true"></i>重置
                            </button>
                            <button type="button" class="btn  btn-danger" onclick="batch_remove()">
                                <i class="fa fa-trash" aria-hidden="true"></i>删除
                            </button>
                            <button  type="button" class="btn  btn-info" onclick="dt_insert()">
                                <i class="fa fa-plus-square" aria-hidden="true"></i>添加
                            </button>
                            <!-- <button  type="button" class="btn  btn-info" onclick="dt_explort_buttont()">
                                <i class="fa fa-plus-square" aria-hidden="true"></i>导出
                            </button>-->
                        </label>
    
                    </div>
    
                    <table id="exampleTable" data-mobile-responsive="true">
                    </table>
    
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${rc.contextPath}/js/plugins/jsTree/jstree.min.js" type="text/javascript"></script>
<script src="${rc.contextPath}/js/plugins/validate/jquery.validate.min.js"></script>
<script src="${rc.contextPath}/js/plugins/validate/messages_zh.min.js"></script>

<!-- Bootstrap table -->
<script src="${rc.contextPath}/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="${rc.contextPath}/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script src="${rc.contextPath}/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="${rc.contextPath}/js/slife/datatable.js"></script>


<script type="text/javascript">

    $("#resTree").jstree({
        "core": {
            "animation": 1,
            "themes": {
                theme: "classic",
                "dots": true,
                "icons": true
            },
            "check_callback": true,
            'data':${categoryTree}
        },
        "types": {
            "default": {
                "valid_children": ["default", "file"]
            }
        },
        "plugins": ["wholerow"]
    }).on("select_node.jstree", function (node, selectd) {
        categoryid = selectd.node.id;
        if (categoryid) {
            $("#categoryId").val(categoryid);
            re_load();
        }
    });

    $("#resTree").on("loaded.jstree", function (event, data) {
        // 展开所有节点
        $('#resTree').jstree('open_all');
        // 展开指定节点
        //data.instance.open_node(1);     // 单个节点 （1 是顶层的id）
        //data.instance.open_node([1, 10]); // 多个节点 (展开多个几点只有在一次性装载后所有节点后才可行）
    });
    
    function getcolumns() {
        var c = [
            {
                checkbox: true
            },
            {
                field: 'proNo', // 列字段名
                title: '产品编号' // 列标题
            },
            {
                field: 'proName',
                title: '产品名称'
            },
            {
                field: 'proUtil',
                title: '单位'
            },
            {
                field: 'specifications',
                title: '规格'
            },
            {
                field: 'material',
                title: '材料'
            },
            {
                field: 'models',
                title: '型号'
            },
            {
                field: 'categoryName',
                title: '分类'
            },
            {
                field : 'relation',
                title : '类型',
                align : 'center',
                formatter : function(value, row, index) {
                    return value == '1' ? '产品' : '零件';
                }
            },
            {
                field: 'remark',
                title: '备注'
            },

            {
                title: '操作',
                field: 'id',
                align: 'center',
                formatter: function (value, row, index) {

                    return dt_edit_button(row)+dt_detail_button(row)+dt_delete_button(row);
                }
            }];

        return c;
    }
	load_data( getcolumns(), {"createDate": "desc"});

	function dt_explort_buttont() {
        location.href=url + "exportUserList";
    }
    

</script>
</body>

</html>