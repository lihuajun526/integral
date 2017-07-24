/**
 * 获取爱奇艺视频的logo
 * @param str
 * @returns {string}
 */
function getAqyLogo(str) {
    str = str.replace("background-image:url(", "");
    str = str.replace(")", "")
    return str.replace(";", "");
}