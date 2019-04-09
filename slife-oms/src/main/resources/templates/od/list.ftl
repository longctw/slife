<html>
<head>
    <title>订单明细管理</title>
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

		<!-- 数据表组件 -->
        <div class="col-md-10">
            <div class="ibox">
                <div class="ibox-body">
    
                    <div id="exampleToolbar" role="group">
    
                        <input id="categoryId" type="hidden" class="form-filter input-sm _search" name="search_start_category_id">
                        <label style="margin-left: 10px;">
                            订单标题：
                            <input type="text" class="form-filter input-sm _search" name="search_like_title">
                        </label>
                        <label style="margin-left: 10px;">
                            订单编号：
                            <input type="text" class="form-filter input-sm _search" name="search_like_order_id">
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

<script src="${rc.contextPath}/js/plugins/validate/jquery.validate.min.js"></script>
<script src="${rc.contextPath}/js/plugins/validate/messages_zh.min.js"></script>

<!-- Bootstrap table -->
<script src="${rc.contextPath}/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="${rc.contextPath}/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script src="${rc.contextPath}/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="${rc.contextPath}/js/slife/datatable.js"></script>


<script type="text/javascript">

    function getcolumns() {
        var c = [
            {
                checkbox: true
            },
            {
                field: 'orderId', // 列字段名
                title: '订单编号' // 列标题
            },
            {
                field: 'title',
                title: '订单名称'
            },
            {
                field: 'bailor',
                title: '委托人'
            },
            {
                field: 'schedule',
                title: '当前进度'
            },
            {
                field: 'createDate',
                title: '开始时间',
                align: 'center',
                formatter: function (val, row, index) {
                    var now = new Date(val);
    				return formatDateToStr(now, "yyyy-MM-dd");
                }
            },
            {
                field: 'requireDate',
                title: '要求时间',
                align: 'center',
                formatter: function (val, row, index) {
                    var now = new Date(val);
    				return formatDateToStr(now, "yyyy-MM-dd");
                }
            },
            {
                field: 'sumMoney',
                title: '总金额'
            },
            {
                field: 'cusName',
                title: '客户'
            },
            {
                field: 'cusCompany',
                title: '客户单位'
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