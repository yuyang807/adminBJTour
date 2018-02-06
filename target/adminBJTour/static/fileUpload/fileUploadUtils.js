function getTotalFileSize() {
    var elements = $("input[type='file']");
    var totalSize = 0;
    $.each(elements, function (index, element) {
        var size = getFileSize(element);
        totalSize += size;
    });
    return totalSize;
}
function getFileSize(element) {
    var size = 0;
    try {
        if ($.browser.msie) { //IE旧版浏览器
            var fileMgr = new ActiveXObject("Scripting.FileSystemObject");
            var filePath = element.value;
            var fileObj = fileMgr.getFile(filePath);
            size = fileObj.size; //单位b
        } else {
            size = element.files[0].size;//单位b
        }
    } catch (e) {

    }
    return size;
}
