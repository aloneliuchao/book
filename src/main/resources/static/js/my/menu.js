$(function(){
    $('#menu a').on('click',function(){
        var aText = $(this).text();
        var url = '';
        if('用户管理' == aText){
            url = '../admin/user/';
        }else if('投票设置' == aText){
            url = "/voteSetup/";
        }else if('工作总结上传' == aText){
            url = "/updateFilePage"
        }else if('详细查询' == aText){
            url = '/detailedQueryPage';
        }
        window.location = url;

    })


});

