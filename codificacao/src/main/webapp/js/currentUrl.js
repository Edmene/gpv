function getSplittedUrl() {
    let url = $(location).attr('href');
    let urlSplit = url.split("/");
    return urlSplit;
}

function getUrlPath(lastPath, jumpHttp){
    let url = getSplittedUrl();
    let urlPath = "";
    for (let path in url){
        if(jumpHttp !== null && jumpHttp !== undefined && path === 0){
            continue;
        }
        if(url[path] === lastPath){
            break;
        }
        urlPath += url[path]+"/";
    }
    return urlPath;
}