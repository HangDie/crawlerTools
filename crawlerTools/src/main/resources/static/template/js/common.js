//获取url中的参数
function getQueryString(key){
    var reg = new RegExp("(^|&)"+key+"=([^&]*)(&|$)");
    var result = window.location.search.substr(1).match(reg);
    return result?decodeURIComponent(result[2]):null;
}
$.ready(function () {

})

function ajaxPOST(url, data, func){
    $.ajax({
        method: 'POST',
        cache: false,
        url:  url + '?timestamp=' + $.now(),
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(data),
        success: function (data) {
            if(data.code == 0){
                //执行传入的函数
                if(func){func();}
            }else if(data.code == -100){
                layer.msg(data.msg);
            }else{
                layer.msg('操作失败，请稍候重试');
            }
        }
    });
}