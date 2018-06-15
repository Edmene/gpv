function getSplittedUrl() {
    let url = $(location).attr('href');
    let urlSplit = url.split("/");
    return urlSplit;
}

function getUrlPath(lastPath){
    let url = getSplittedUrl();
    let urlPath = "";
    for (let path in url){
        if(url[path] === lastPath){
            break;
        }
        urlPath += url[path]+"/";
    }
    return urlPath;
}