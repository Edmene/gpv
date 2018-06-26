<header class="header">
    <nav class="main-nav">
        <a href="${context_path}"><img src="${context_path}/images/home.svg" id="home-img"></a>

        <#if (session.user) ??>
            <div class="log-div">
                <div class="profile-div">
                    <@link_to controller="user" action="profile"><img src="${context_path}/images/user_close.svg" id="user-img"></@link_to>
                    <strong>${session.user}</strong>
                    <img src="${context_path}/images/arrow.svg" id="arrow-img-dow" onclick="showLinks(this)">
                </div>
            </div>
                <@link_to controller="login" action="logout"><img src="${context_path}/images/logout.svg" id="log-img-out"></@link_to>
            <#else>
                <@link_to controller="login"><img src="${context_path}/images/login.svg" id="log-img"></@link_to>
            </#if>
    </nav>
    <#if session.id??>
        <nav id="links-bar">
            <ul class="ul-nav">
                <li class="li-nav"><@link_to controller="user" class="a-nav">Usuarios</@link_to></li>
                <li class="li-nav"><@link_to controller="passenger" class="a-nav">Clientes</@link_to></li>

                <li class="li-nav"><@link_to controller="driver" class="a-nav">Motoristas</@link_to></li>
                <li class="li-nav"><@link_to controller="vehicle" class="a-nav">Veiculos</@link_to></li>

                <li class="li-nav"><@link_to controller="state" class="a-nav">Estados</@link_to></li>
                <li class="li-nav"><@link_to controller="city" class="a-nav">Cidades</@link_to></li>
                <li class="li-nav"><@link_to controller="address" class="a-nav">Enderecos</@link_to></li>
                <li class="li-nav"><@link_to controller="destination" class="a-nav">Destinos</@link_to></li>
                <li class="li-nav"><@link_to controller="stop" class="a-nav">Paradas</@link_to></li>

                <li class="li-nav"><@link_to controller="plan" class="a-nav">Planos</@link_to></li>

                <li class="li-nav"><@link_to controller="reservation" class="a-nav">Reservas</@link_to></li>

                <li class="li-nav"><@link_to controller="holiday" class="a-nav">Feriados</@link_to></li>
            </ul>
        </nav>
    </#if>
</header>

