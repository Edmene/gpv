<@content for="title">Motorista List</@content>


<div class="message"><@flash name="message"/></div>



<@link_to action="new_form">Adicionar novo driver</@link_to>

<table>
    <tr>
        <td>Nome</td>
        <td>Sobrenome</td>
        <td>RG</td>
        <td>CNH</td>
        <td>Edit</td>
    </tr>
<#list drivers as driver>
    <tr>
        <td>
            <@link_to action="show" id=driver.id>${driver.name}</@link_to>
        </td>
        <td>
            ${driver.surname}</td>
        <td>
            ${driver.rg}</td>
        <td>
            ${driver.cnh}</td>
        <td>
            <@form  id=driver.id action="delete" method="delete" html_id=driver.id>
                <button type="submit">delete</button>
            </@form>
        </td>
    </tr>
</#list>
</table>



