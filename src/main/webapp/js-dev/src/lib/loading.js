require("./css/loading.css");
var loadingHtml = require("./loading.html");
var $ = require("jquery");
$(function() {

    $("body").append(loadingHtml);
});

module.exports.loading = {};