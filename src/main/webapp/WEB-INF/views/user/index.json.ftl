[
<#list usuarios as user>
    {
        "id": "${user.id}",
        "nome": "${user.nome}",
        "senha": "${user.senha}"
    }
    <#if usuario_has_next>,</#if>
</#list>
]



