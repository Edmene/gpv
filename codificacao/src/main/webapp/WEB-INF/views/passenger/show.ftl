
<@content for="title">Passageiro: ${passenger.name}</@content>


<#if session.accessLevel == "A">
<@link_to class="bt-a">Voltar para passageiros</@link_to>
<#else>
<@link_to controller="user" action="profile" id="${session.id}" class="bt-a">Voltar para perfil</@link_to>
</#if>


<div class="passenger-details">
    <h1>${passenger.name}</h1>
    <strong>Sobrenome:</strong> ${passenger.surname},
    <strong>CPF:</strong> ${passenger.cpf}, <strong>RG:</strong> ${passenger.rg},
    <strong>Data de Nascimento:</strong> ${passenger.birth_date},<strong>Telefone:</strong> ${passenger.telephone}
    <strong>Email:</strong> ${passenger.email}
</div>