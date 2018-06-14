<header class="header">
    <nav class="main-nav">
        <a href="${context_path}"><img src="${context_path}/images/home.svg" id="home-img"></a>

        <div class="log-div">
            <#if (session.user) ??>
                <@link_to controller="login" action="logout"><img src="${context_path}/images/logout.svg" id="log-img-out"></@link_to>
                <div class="profile-div">
                    <@link_to controller="user" action="profile"><img src="${context_path}/images/user_close.svg" id="user-img"></@link_to>
                    <strong>${session.user}</strong>
                </div>
            <#else>
                <@link_to controller="login"><img src="${context_path}/images/login.svg" id="log-img"></@link_to>
            </#if>
        </div>
    </nav>
</header>

