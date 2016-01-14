var $ = require("jquery");

var checkActionResult = function (opts) {

};

var action = function (opts) {
    $.ajax({
        url: opts.url,
        data: opts.data,
        dataType: 'json',
        success: function (data) {
            checkActionResult.call(data, opts);
        },
        error: function () {
            if (opts.error) {
                opts.error.apply(this, this.arguments);
            }
        }
    });
};

var getParams = function () {
    var params = {};
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        // If first entry with this name
        if (typeof params[pair[0]] === "undefined") {
            params[pair[0]] = decodeURIComponent(pair[1]);
            // If second entry with this name
        } else if (typeof params[pair[0]] === "string") {
            var arr = [params[pair[0]], decodeURIComponent(pair[1])];
            params[pair[0]] = arr;
            // If third or later entry with this name
        } else {
            params[pair[0]].push(decodeURIComponent(pair[1]));
        }
    }
    return params;
};

var generateUrl = function (opts) {
    var url = opts.url;
    var path = opts.path;
    var data = opts.data;
    var _data = $.serialize(data);
    return url + path + "?" + _data;
};

var isString = function (obj) {
    return isNotEmpty(obj) && Object.prototype.toString.call(obj) === "[object String]";
};

var isEmpty = function (obj) {
    return !!obj;
};

var isNotEmpty = function (obj) {
    return !isEmpty(obj);
};

var getCookie = function (c_name) {
    if (document.cookie.length > 0) {　　//先查询cookie是否为空，为空就return ""
        var c_start = document.cookie.indexOf(c_name + "=");　//通过String对象的indexOf()来检查这个cookie是否存在，不存在就为 -1　　
        if (c_start != -1) {
            c_start = c_start + c_name.length + 1;　　//最后这个+1其实就是表示"="号啦，这样就获取到了cookie值的开始位置
            var c_end = document.cookie.indexOf(";", c_start);　//其实我刚看见indexOf()第二个参数的时候猛然有点晕，后来想起来表示指定的开始索引的位置...这句是为了得到值的结束位置。因为需要考虑是否是最后一项，所以通过";"号是否存在来判断
            if (c_end == -1) {
                c_end = document.cookie.length;
            }
            return unescape(document.cookie.substring(c_start, c_end));　//通过substring()得到了值。想了解unescape()得先知道escape()是做什么的，都是很重要的基础，想了解的可以搜索下，在文章结尾处也会进行讲解cookie编码细节
        }
    }
    return ""
};

var setCookie = function (c_name, value, expiredays) {
    var exdate = new Date();
    exdate.setDate(exdate.getDate() + expiredays);
    document.cookie = c_name + "=" + escape(value) + ";path=/" + ((expiredays == null) ? "" : ";expires=" + exdate.toGMTString());
};

var deleteCookie = function (c_name) {
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var value = getCookie(c_name);
    if (value != null) {
        document.cookie = c_name + "=" + value + ";path=/;expires=" + exp.toGMTString();
    }
};


module.exports = {
    action: action,
    isString: isString,
    isEmpty: isEmpty,
    isNotEmpty: isNotEmpty,
    getParams: getParams,
    generateUrl: generateUrl,
    browser: {
        userAgent: navigator.userAgent.toLowerCase(),
        isAndroid: Boolean(navigator.userAgent.match(/android/ig)),
        isIphone: Boolean(navigator.userAgent.match(/iphone|ipod/ig)),
        isIpad: Boolean(navigator.userAgent.match(/ipad/ig)),
        isWeixin: Boolean(navigator.userAgent.match(/MicroMessenger/ig))
    },
    getCookie: getCookie,
    setCookie: setCookie,
    deleteCookie: deleteCookie
};