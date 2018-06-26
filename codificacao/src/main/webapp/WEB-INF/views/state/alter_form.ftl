<@content for="title">Alterar estado</@content>

<span class="error_message"><@flash name="message"/></span>
<h2>Alterando estado ${state.name}</h2>


<@form action="update" method="post" name="update">

    <div class="forms-div">
        <label>Nome</label>
        <input type="text" name="name" value="${state.name}" required>
        <span class="error">${(flasher.errors.name)!}</span>

        <label>Sigla</label>
        <input type="text" name="acronym" value="${(state.acronym)!}" required>
        <span class="error">${(flasher.errors.acronym)!}</span>

        <input type="hidden" name="id" value="${state.id}">

        <div class="forms-buttons">
            <@link_to class="bt-a">Cancelar</@link_to>
            <input class="bt-a" type="submit" value="Alterar estado">
        </div>
    </div>

</@form>



