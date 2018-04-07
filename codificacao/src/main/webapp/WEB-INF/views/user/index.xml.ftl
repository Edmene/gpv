<books>
<#list users as user>
    <book>
        <id>${user.id}</id>
        <title>${user.name}</title>
        <author>${user.type}</author>
    </book>
</#list>
</books>



