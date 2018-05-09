let page = 0;// 当前页，初始值设为 0
let size = 10;// 每页条数，初始值设为 10
let total;// 总记录数
$(document).ready(function () {
    getInfo();// 获取数据
    toPage();// 进行分页
});

// 获取数据
function getInfo() {
    $.ajax({
        type: "post",
        url: "userData",
        async: false,// 设置同步请求，加载数据前锁定浏览器
        dataType: 'json',
        data: {
            "page": page, // 查询页码
            "size": size, // 每页条数
        },
        success: successFu
    });
}

// 数据请求成功
function successFu(pager) {
    //console.log(pager);
    // 1.清空原数据
    $("dataTbody").html("");
    // 2.重新赋值页码、条数、总记录数
    page = pager.bookPage.psize;
    size = pager.bookPage.pindex;
    total = pager.bookPage.pcount;
    // 3.渲染数据
    // 当前查询无数据时
    if (pager.bookPage.pcount == 0) {
        $("#dataTbody").html("<tr><td colspan='7' class='text-center'>暂无数据</td></tr>");
        return;
    }
    let text = "";
    $.each(pager.users, (i, item) => {
        text += `
            <tr>
                <th>${i + 1}</th>
                <th>${item.name}</th>
                <th>${item.username}</th>
                <th>${item.roleList[0].roleName}</th>
                <th align="center">
                        <a class="layui-btn layui-btn-primary layui-btn-xs" onclick="look(${item.id})">查看</a>
                        <a class="layui-btn layui-btn-danger layui-btn-xs" onclick="delect(${item.id})">删除</a>
                </th>
            </tr>
             `;
    });
    $("#dataTbody").html(text);
}

// 进行分页
function toPage() {
    layui.use('laypage', function () {
        let laypage = layui.laypage;
        // 调用分页
        laypage.render({
            // 分页容器的id
            elem: 'page',// *必选参数
            // 数据总数，从服务端得到
            count: total,// *必选参数
            // 每页显示的条数。laypage将会借助 count 和 size 计算出分页数。
            //size:size,
            // 起始页
            //page:Pager,
            // 连续出现的页码个数
            //groups:5,
            // 自定义每页条数的选择项
            limits: [10, 25, 50, 100],
            // 自定义首页、尾页、上一页、下一页文本
            first: '首页',
            last: '尾页',
            prev: '<em><<</em>',
            next: '<em>>></em>',
            // 自定义排版
            layout: ['count', 'prev', 'page', 'next', 'size', 'skip'],
            // 渲染数据
            jump: function (data, first) {
                // data包含了当前分页的所有参数
                page = data.curr - 1;
                size = data.limit;
                // 首次不执行
                if (!first) {
                    getInfo();
                }
            }
        });
    })
}