<@content for="title">Simple Web App</@content>

<span class="error_message"><@flash name="message"/></span>

<#if (session.user) ??>
<p>Logged in as: ${session.user}</p>
<#else>
<p>Not logged</p>
</#if>

<h2>Simple ActiveWeb application</h2>

<ul>
    <li><@link_to controller="user">Usuarios CRUD example</@link_to></li>
    <li><@link_to controller="driver">Motoristas CRUD example</@link_to></li>
    <li><@link_to controller="state">Estados</@link_to></li>
    <li><@link_to controller="city">Cidade</@link_to></li>
    <li><@link_to controller="address">Endereco</@link_to></li>
    <li><@link_to controller="holiday">Feriado</@link_to></li>
    <li><@link_to controller="vehicle">Veiculo</@link_to></li>
    <li><@link_to controller="destination">Destino</@link_to></li>
    <li><@link_to controller="passenger">Cadastro de cliente</@link_to></li>
    <li><@link_to controller="plan">Planos</@link_to></li>
    <li><@link_to controller="stop">Paradas</@link_to></li>
    <li><@link_to controller="login">Login</@link_to></li>
    <li><@link_to controller="login" action="logout">Logout</@link_to></li>
    <li><@link_to controller="reservation">Reservation</@link_to></li>
    <li><a href="user.xml" >Books XML Service example<a></li>
    <li><a href="user.json" >Books JSON Service example<a></li>
    <li><@link_to controller="greeting">Dependency injection example</@link_to></li>
</ul>

