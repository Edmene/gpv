<@content for="title">Inicio</@content>

<span class="message"><@flash name="message"/></span>


<main class="main-center">
    <section class="main-row">
        <h1>Bem Vindo ao Sistema de Gerenciamento de Planos de Viagens</h1>
        <p>Para acessar a aplicação realize login</p>
        <menu class="centered-menu">
            <@link_to controller="login" class="bt-a">Login</@link_to>
            <@link_to controller="passenger_creation" action="new_form" class="bt-a">Novo usuário</@link_to>
        </menu>
    </section>
</main>



