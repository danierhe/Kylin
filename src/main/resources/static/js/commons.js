//ajax请求
function postData(url,data){
    var json = null;
    $.ajax({
        type:'post',
        async:false,//同步请求将会锁住浏览器，用户其他操作必须等待请求完成后才能执行
        url:url,
        data:data,
        dataType:'json',
        success:function (result) {
            json=result;
        }
    })
    return json;
}
//用户登录超时后，不能执行操作，返回首页或登录页。
$.ajaxSetup({
    //设置ajax请求结束后的执行动作
    complete:function (XMLHttpRequest,textStatus) {
        //通过XMLHttpRequest获取响应头，REDIRECT
        var redirect = XMLHttpRequest.getResponseHeader("REDIRECT");
        if(redirect=="REDIRECT"){
            var win = window;
            while (win!=win.top){
                //重定向，返回首页
                win.location.href = XMLHttpRequest.getResponseHeader("CONTEXTPATH");
            }
        }
    },
    type:'POST'
});

function href(url) {
   window.location.href = url;
}

//获得一个guid
function guid() {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
        return v.toString(16);
    });
}