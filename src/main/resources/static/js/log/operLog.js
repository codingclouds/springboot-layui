/**
 * 用户管理
 */
var pageCurr;
var form;
var OPERTYPE_SET={"0":"全部","1":"新增","2":"查询","3":"删除","4":"更新"}
$(function() {
    $('.loading').animate({'width':'50%'},50); //第四个进度节点

    layui.use('table', function(){
        var table = layui.table;
        form = layui.form;

        tableIns=table.render({
            elem: '#logList',
            url:'/log/getLogList',
            method: 'post', //默认：get请求
            title: '日志操作记录',
            cellMinWidth: 80,
            page: true,
            request: {
                pageName: 'pageNum', //页码的参数名称，默认：pageNum
                limitName: 'pageSize' //每页数据量的参数名，默认：pageSize
            },
            response:{
                statusName: 'code', //数据状态的字段名称，默认：code
                statusCode: 200, //成功的状态码，默认：0
                countName: 'totals', //数据总数的字段名称，默认：count
                dataName: 'list' //数据列表的字段名称，默认：data
            },
            cols: [[
                {type:'numbers'}
                ,{field:'userName', title:'用户名',align:'center',width:'8%'}
                ,{field:'roleName', title:'角色类型',align:'center',width:'10%'}
                ,{field:'ip', title:'IP',align:'center',width:'12%'}
                ,{field:'busi', title: '操作模块',align:'center',width:'8%'}
                ,{field:'operType', title: '操作类型',align:'center',width:'8%'}
                ,{field:'method', title: '执行方法',align:'center',width:'10%'}
                ,{field:'startTime', title: '执行时间',align:'center'}
                ,{field:'endTime', title: '返回时间',align:'center'}
                ,{field:'reqData', title: '请求参数',align:'center',hide:true}
                ,{field:'respData', title: '响应参数',align:'center',hide:true}
                ,{title:'操作',align:'center', toolbar:'#optBar',width:'8%'}
            ]],
            done: function(res, curr, count){
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                //console.log(res);
                //得到当前页码
                console.log(curr);
                $("[data-field='operType']").children().each(function(){
                    if($(this).text()=='1'){
                        // $(this).html("<button type='button' class='layui-btn  layui-btn-radius layui-btn-xs'>新增</button>");
                        $(this).html("<span class='label label-success'>新增</span>");
                    }else if($(this).text()=='2'){
                        $(this).html("<span class='label label-info'>查询</span>");
                    }else if ($(this).text()=='3'){
                        $(this).html("<span class='label label-danger'>删除</span>");
                    } else if($(this).text()=='4'){
                        $(this).html("<span class='label label-warning'>更新</span>");
                    }
                });


                //得到数据总量
                //console.log(count);
                pageCurr=curr;
            }
        });

        //监听工具条
        table.on('tool(logTable)', function(obj){
            var data = obj.data;
            if(obj.event === 'del'){
                //删除
                delUser(data,data.id,data.sysUserName);
            } else if(obj.event === 'edit'){
                //详细
                openUser(data,"详细");
            }else if(obj.event === 'recover'){
                //恢复
                recoverUser(data,data.id);
            }
        });

        //监听提交
        form.on('submit(userSubmit)', function(data){
            // TODO 校验
            layer.closeAll();
            // formSubmit(data);
            return false;
        });
    });

    //搜索框
    layui.use(['form','laydate'], function(){
        var form = layui.form ,layer = layui.layer
            ,laydate = layui.laydate;
        //日期
        laydate.render({
            elem: '#startTime'
        });
        laydate.render({
            elem: '#endTime'
        });
        //TODO 数据校验
        //监听搜索框
        form.on('submit(searchSubmit)', function(data){
            //重新加载table
            load(data);
            return false;
        });
    });
    //加载下拉框
    // loadSelect();
});

//加载下拉框
function loadSelect() {
    // $('#operTypeSearch').empty();//往下拉菜单里添加元素
    for (var i = 0; i < 5; i++) {
        var option = new Option(OPERTYPE_SET[i],i);
        if(i == "0") {
            option.setAttribute("selected",'true');
        }
        $('#operTypeSearch').append(option);//往下拉菜单里添加元素
    }
}

//提交表单
// function formSubmit(obj){
//     $.ajax({
//         type: "POST",
//         data: $("#userForm").serialize(),
//         url: "/user/setUser",
//         success: function (data) {
//             if (data.code == 1) {
//                 layer.alert(data.msg,function(){
//                     layer.closeAll();
//                     load(obj);
//                 });
//             } else {
//                 layer.alert(data.msg);
//             }
//         },
//         error: function () {
//             layer.alert("操作请求错误，请您稍后再试",function(){
//                 layer.closeAll();
//                 //加载load方法
//                 load(obj);//自定义
//             });
//         }
//     });
// }

//开通用户
// function addUser(){
//     openUser(null,"开通用户");
// }
function openUser(data,title){
    $('#operType').empty();//往下拉菜单里添加元素
    if(data==null || data==""){
        $("#id").val("");
    }else{
        $("#id").val(data.id);
        $("#userName").val(data.userName);
        $("#roleName").val(data.roleName);
        $("#ip").val(data.ip);
        $("#busi").val(data.busi);
        for (var i = 1; i <= 5; i++) {
            var option = new Option(OPERTYPE_SET[i],i);
            if(data.operType == i) {
                option.setAttribute("selected",'true');
            }
            $('#operType').append(option);//往下拉菜单里添加元素
        }
        form.render('select'); //这个很重要

        $("#method").val(data.method);
        $("#reqTime").val(data.startTime);
        $("#respTime").val(data.endTime);
        $('#reqData').html(jsonShowFn(data.reqData, null, 2))   //json为要展示到页面的数据
        $('#respData').html(jsonShowFn(data.respData, null, 2))   //json为要展示到页面的数据

    }
    var pageNum = $(".layui-laypage-skip").find("input").val();
    $("#pageNum").val(pageNum);

    layer.open({
        type:1,
        title: title,
        fixed:false,
        resize :false,
        shadeClose: true,
        area: ['700px','600px'],
        content:$('#logDetail'),
        end:function(){
            cleanUser();
        }
    });
}

function delUser(obj,id,name) {
    var currentUser=$("#currentUser").html();
    if(null!=id){
        if(currentUser==id){
            layer.alert("对不起，您不能执行删除自己的操作！");
        }else{
            layer.confirm('您确定要删除'+name+'用户吗？', {
                btn: ['确认','返回'] //按钮
            }, function(){
                $.post("/user/updateUserStatus",{"id":id,"status":0},function(data){
                    if (data.code == 1) {
                        layer.alert(data.msg,function(){
                            layer.closeAll();
                            load(obj);
                        });
                    } else {
                        layer.alert(data.msg);
                    }
                });
            }, function(){
                layer.closeAll();
            });
        }
    }
}
//恢复
function recoverUser(obj,id) {
    if(null!=id){
        layer.confirm('您确定要恢复吗？', {
            btn: ['确认','返回'] //按钮
        }, function(){
            $.post("/user/updateUserStatus",{"id":id,"status":1},function(data){
                if (data.code == 1) {
                    layer.alert(data.msg,function(){
                        layer.closeAll();
                        load(obj);
                    });
                } else {
                    layer.alert(data.msg);
                }
            });
        }, function(){
            layer.closeAll();
        });
    }
}

function load(obj){
    //重新加载table
    tableIns.reload({
        where: obj.field
        , page: {
            // curr: pageCurr //从当前页码开始
            curr: 1 //从第一页码开始
        }
    });
}

function cleanUser(){
    $("#username").val("");
    $("#mobile").val("");
    $("#password").val("");
    $('#roleId').html("");
}

// $(document).ready(function(){
//     //加载下拉框
//     loadSelect();
//     $('.loading').animate({'width':'100%'},50); //第四个进度节点
//     $('.loading').fadeOut();
// });

function jsonShowFn(json){
    if (typeof json != 'string') {
        json = JSON.stringify(json, undefined, 2);
    }
    json = json.replace(/&/g, '&').replace(/</g, '<').replace(/>/g, '>');
    return json.replace(/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g, function(match) {
        var cls = 'number';
        if (/^"/.test(match)) {
            if (/:$/.test(match)) {
                cls = 'key';
            } else {
                cls = 'string';
            }
        } else if (/true|false/.test(match)) {
            cls = 'boolean';
        } else if (/null/.test(match)) {
            cls = 'null';
        }
        return '<span class="' + cls + '">' + match + '</span>';
    });
}
