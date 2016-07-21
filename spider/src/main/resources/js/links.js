/**
 * 获取中信特惠商户
 * @param str
 * @returns {string}
 */
function zxLink(str) {
    return "http://creditcard.ecitic.com/ecitic/index.jsp?vendor_id=" + str;
}

function gfLink(str) {
    var strs = str.split("=http");
    return "http" + strs[1];
}