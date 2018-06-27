<@content for="title">Perfil</@content>

<div class="message"><@flash name="message"/></div>
<main>
    <div id="user-content">
        <h1 id="user-name">${session.user}</h1>
        <nav id="user-nav">
            <#if session.accessLevel == "A">
                <@link_to controller="user" action="alter_form" id="${session.id}" class="bt-a">Alterar usuario</@link_to>
                <@link_to controller="user" method="delete" action="delete" id="${session.id}" class="bt-a">Deletar usuario</@link_to>
            <#else>
                <@link_to controller="passenger" action="show" id="${session.id}" class="bt-a">Ver Informacoes</@link_to>
                <@link_to controller="passenger" action="alter_form" id="${session.id}" class="bt-a">Alterar usuario</@link_to>
                <@link_to controller="passenger" action="list_plan" id="${session.id}" class="bt-a">Planos reservados</@link_to>
                <@link_to controller="passenger" method="delete" action="delete" id="${session.id}" class="bt-a">Deletar usuario</@link_to>
            </#if>

        </nav>
    </div>
</main>