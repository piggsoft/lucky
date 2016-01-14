var utils = require("./utils");

var session = null;

var check = function() {
    if(!window || !window.sessionStorage) {
        session = new CookieImpl();
    } else {
        session = new SessionStorageImpl();
    }
};

function SessionStorageImpl() {
    this.put = function(key, value) {
        window.sessionStorage.setItem(key, utils.isString(value) ? value : JSON.stringify(value));
    };
    this.get = function(key) {
        window.sessionStorage.getItem(key);
    };
    this.remove = function(key) {
        window.sessionStorage.removeItem(key);
    };
    this.clear = function() {
        window.sessionStorage.clear();
    };
}

function CookieImpl() {
    this.put = function(key, value) {
        utils.setCookie(key, value);
    };
    this.get = function(key) {
        utils.getCookie(key);
    };
    this.remove = function(key) {
        utils.deleteCookie(key);
    };
    this.clear = function() {
        console.log("unsupport action");
    };
}

check();



module.exports = {
    put: session.put,
    get: session.get,
    remove: session.remove,
    clear: session.clear
};
