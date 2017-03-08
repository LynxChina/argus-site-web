$(document).ready(function () {

    var AJAX_ROUTER = {
        "upload": "more/fileUpload.do"
    };

    $(".btn-upload").click(function () {
        //开始上传文件时显示一个图片,文件上传完成将图片隐藏
        //$("#loading").ajaxStart(function(){$(this).show();}).ajaxComplete(function(){$(this).hide();});
        //执行上传文件操作的函数
        $.ajaxFileUpload({
            //处理文件上传操作的服务器端地址(可以传参数,已亲测可用)
            url: AJAX_ROUTER["upload"],
            secureuri: false,                           //是否启用安全提交,默认为false
            fileElementId: 'myBlogImage',               //文件选择框的id属性
            dataType: 'json',                           //服务器返回的格式,可以是json或xml等
            success: function (data, status) {            //服务器响应成功时的处理函数
                data = data.replace(/<pre.*?>/g, '');  //ajaxFileUpload会对服务器响应回来的text内容加上<pre style="....">text</pre>前后缀
                data = data.replace(/<PRE.*?>/g, '');
                data = data.replace("<PRE>", '');
                data = data.replace("</PRE>", '');
                data = data.replace("<pre>", '');
                data = data.replace("</pre>", '');     //本例中设定上传文件完毕后,服务端会返回给前台[0`filepath]
                if (data.substring(0, 1) == 0) {         //0表示上传成功(后跟上传后的文件路径),1表示失败(后跟失败描述)
                    $("img[id='uploadImage']").attr("src", data.substring(2));
                    $('#result').html("图片上传成功<br/>");
                } else {
                    $('#result').html('图片上传失败，请重试！！');
                }
            },
            error: function (data, status, e) { //服务器响应失败时的处理函数
                $('#result').html('图片上传失败，请重试！！');
            }
        });
    });

});