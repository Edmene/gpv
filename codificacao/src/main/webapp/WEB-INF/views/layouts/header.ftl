<header class="header">
    <a href="${context_path}"><img src="${context_path}/images/home.svg" id="home-img"></a>

    <#if (session.user) ??>
        <@link_to controller="login" action="logout"><img src="${context_path}/images/login.svg" id="log-img"></@link_to>
    <#else>
        <@link_to controller="login"><img src="${context_path}/images/login.svg" id="log-img"></@link_to>
    </#if>
</header>

