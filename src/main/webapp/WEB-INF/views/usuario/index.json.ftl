[
<#list usuarios as usuario>
    {
        "id": "${usuario.id}",
        "nome": "${usuario.nome}",
        "senha": "${usuario.senha}"
    }
    <#if usuario_has_next>,</#if>
</#list>
]



