
<@content for="title">Passageiro: ${passenger.name}</@content>


<#if session.accessLevel == "A">
<@link_to class="bt-a">Voltar para passageiros</@link_to>
<#else>
<@link_to controller="user" action="profile" id="${session.id}" class="bt-a">Voltar para perfil</@link_to>
</#if>



<h1>${passenger.name} ${passenger.surname}</h1>
<table class="listing-table">
    <thead>
    <tr class="tr-even">
        <td>CPF</td>
        <td>RG</td>
        <td>Data de Nascimento</td>
        <td>Telefone</td>
        <td>Email</td>
    </tr>
    </thead>
    <tbody>
        <tr>
            <td class="cpf">
                ${passenger.cpf}</td>
            <td>
                ${passenger.rg}</td>
            <td>
                ${passenger.birth_date}</td>
            <td class="phone">
                ${passenger.telephone}</td>
            <td>
                ${passenger.email}</td>
        </tr>
    </tbody>
</table>