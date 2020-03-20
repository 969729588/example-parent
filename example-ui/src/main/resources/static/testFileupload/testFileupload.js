/**
 * Created by Huarf on 2020/3/15.
 */
$(function () {
    //绑定文件上传时间，可以自动上传，也可以手动调用js上传，具体见jQuery-File-Upload：
    //https://github.com/blueimp/jQuery-File-Upload
    $('#file').fileupload({
        url: getContextPath() + '/test/testFileupload',
        formData: {name: "abc", id: "123"},
        done: function (e, data) {
            console.log(e);
            console.log(data);
            console.log(data.result);
        }
    });
});