<@content for="title">Alterar motorista</@content>

<span class="error_message"><@flash name="message"/></span>
<h2>Alterando motorista</h2>


<@form action="update" method="post" name="update">

    <div class="forms-div">

        <label>Nome</label>
        <input type="text" name="name" value="${(driver.name)!}" required>
        <span class="error">${(flasher.errors.name)!}</span>

        <label>Sobrenome</label>
        <input type="text" name="surname" value="${(driver.surname)!}" required>
        <span class="error">${(flasher.errors.surname)!}</span>

        <label>RG</label>
        <input type="text" name="rg" value="${(driver.rg)!}" required>
        <span class="error">${(flasher.errors.rg)!}</span>

        <label>CNH</label>
        <input type="text" class="cnh" name="cnh" value="${(driver.cnh)!}" required>
        <span class="error">${(flasher.errors.cnh)!}</span>

        <input type="hidden" name="id" value="${driver.id}">

        <div class="forms-buttons">
            <@link_to class="bt-a">Cancelar</@link_to>
            <input class="bt-a" type="submit" value="Alterar motorista">
        </div>
    </div>

</@form>



