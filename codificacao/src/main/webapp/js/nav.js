function showLinks(imgArrow) {
    let linksBar = document.getElementById("links-bar");
    if(linksBar === undefined || linksBar === null){
        linksBar = document.getElementById("links-bar-show");
        linksBar.id = "links-bar";
        imgArrow.id = "arrow-img-dow"
    }
    else {
        linksBar.id = "links-bar-show";
        imgArrow.id = "arrow-img-up";
    }

}