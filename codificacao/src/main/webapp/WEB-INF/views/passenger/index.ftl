<@content for="title">Lista de passageiros</@content>


<div class="message"><@flash name="message"/></div>



<@link_to action="new_form">Adicionar novo usuario</@link_to>

<table>
    <tr>
        <td>Nome</td>
        <td>Sobrenome</td>
        <td>CPF</td>
        <td>RG</td>
        <td>Data de Nascimento</td>
        <td>Telefone</td>
        <td>Email</td>
        <td>Editar</td>
    </tr>
<#list passengers as passenger>
    <tr>
        <td>
            <@link_to action="show" id=passenger.user_id>${passenger.name}</@link_to>
        </td>
        <td>
            ${passenger.surname}</td>
        <td>
            ${passenger.cpf}</td>
        <td>
            ${passenger.rg}</td>
        <td>
            ${passenger.birth_date}</td>
        <td>
            ${passenger.telephone}</td>
        <td>
            ${passenger.email}</td>
        <td>
            <@form  id=passenger.user_id action="delete" method="delete" html_id=passenger.user_id >
                <button type="submit">Excluir</button>
            </@form>
        </td>
    </tr>
</#list>
</table>




