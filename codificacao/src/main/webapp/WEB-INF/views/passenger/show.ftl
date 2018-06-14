
<@content for="title">Passageiro: ${passenger.name}</@content>


<#if session.acessLevel == "A">
<@link_to>Voltar para passageiros</@link_to>
<#else>
<@link_to controller="user" action="profile" id="${session.id}">Voltar para perfil</@link_to>
</#if>



<h2>Nome: "${passenger.name}"</h2>

<strong>Sobrenome:</strong> ${passenger.surname},
<strong>CPF:</strong> ${passenger.cpf}, <strong>RG:</strong> ${passenger.rg},
<strong>Data de Nascimento:</strong> ${passenger.birth_date},<strong>Telefone:</strong> ${passenger.telephone}
<strong>Email:</strong> ${passenger.email}