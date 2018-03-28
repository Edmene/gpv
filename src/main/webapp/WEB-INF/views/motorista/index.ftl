<@content for="title">Motorista List</@content>


<div class="message"><@flash name="message"/></div>



<@link_to action="new_form">Adicionar novo motorista</@link_to>

<table>
    <tr>
        <td>Nome</td>
        <td>Sobrenome</td>
        <td>RG</td>
        <td>CNH</td>
        <td>Edit</td>
    </tr>
<#list motoristas as motorista>
    <tr>
        <td>
            <@link_to action="show" id=motorista.id>${motorista.nome}</@link_to>
        </td>
        <td>
            ${motorista.sobrenome}</td>
        <td>
        <td>
            ${motorista.rg}</td>
        <td>
        <td>
            ${motorista.cnh}</td>
        <td>
            <@form  id=motorista.id action="delete" method="delete" html_id=motorista.id>
                <button type="submit">delete</button>
            </@form>
        </td>
    </tr>
</#list>
</table>




