<@content for="title">Add new driver</@content>

<span class="error_message"><@flash name="message"/></span>
<h1>Adicionando novo motorista</h1>


<@form action="create" method="post">

    <div class="forms-div">

        <label>Nome</label>
        <input type="text" name="name" value="${(flasher.params.name)!}" required>
        <span class="error">${(flasher.errors.name)!}</span>

        <label>Sobrenome</label>
        <input type="text" name="surname" value="${(flasher.params.surname)!}" required>
        <span class="error">${(flasher.errors.surname)!}</span>

        <label>RG</label>
        <input type="text" name="rg" value="${(flasher.params.rg)!}" required>
        <span class="error">${(flasher.errors.rg)!}</span>

        <label>CNH</label>
        <input type="text" class="cnh" name="cnh" value="${(flasher.params.cnh)!}" required>
        <span class="error">${(flasher.errors.cnh)!}</span>

        <div class="forms-buttons">
            <@link_to class="bt-a">Cancelar</@link_to>
            <input class="bt-a" type="submit" value="Adicionar novo motorista">
        </div>
    </div>

</@form>



