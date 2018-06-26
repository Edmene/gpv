<@content for="title">Adicionar estado</@content>

<span class="error_message"><@flash name="message"/></span>
<h1>Adicionando novo estado</h1>


<@form action="create" method="post">

    <div class="forms-div">
        <label>Nome</label>
        <input type="text" name="name" value="${(flasher.params.name)!}" required>
        <span class="error">${(flasher.errors.name)!}</span>

        <label>Sigla</label>
        <input type="text" name="acronym" value="${(flasher.params.acronym)!}" required>
        <span class="error">${(flasher.errors.acronym)!}</span>

        <div class="forms-buttons">
            <@link_to class="bt-a">Cancelar</@link_to>
            <input class="bt-a" type="submit" value="Adicionar novo estado">
        </div>
    </div>

</@form>



