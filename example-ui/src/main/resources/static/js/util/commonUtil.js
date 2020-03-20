/**
 * Created by Huarf on 2020/3/6.
 * 常用工具方法
 *
 */

/**
 * 是否为空
 * @param val
 * @returns {boolean}
 */
function isValid(val) {
    if(val!='' && val!=null && typeof(val)!="undefined" && val!=0)
        return true;
    else
        return false;
}