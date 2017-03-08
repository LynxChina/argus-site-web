!function ($) {

    console.log("hello world");

    $('#affix_nav').affix({
        offset: {
            top: function () {
                return window.width <= 980 ? 50 : 40
            }, bottom: 50
        }
    });

    $("#wangdao").popover({title: 'xxx的王道', content: "业务：预订"});


}(jQuery)