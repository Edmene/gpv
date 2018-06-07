<header class="header">
    <a href="${context_path}"><img src="${context_path}/images/home.svg" id="home-img"></a>

    <div class="log-div">
        <#if (session.user) ??>
            <@link_to controller="login" action="logout"><img src="${context_path}/images/login.svg" id="log-img-out"></@link_to>
            <strong>${session.user}</strong>
        <#else>
            <@link_to controller="login"><img src="${context_path}/images/login.svg" id="log-img"></@link_to>
        </#if>
    </div>
</header>

