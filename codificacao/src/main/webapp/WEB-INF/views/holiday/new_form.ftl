<@content for="title">Adicionar novo feriado</@content>

<span class="error_message"><@flash name="message"/></span>
<h2>Adicionando novo feriado</h2>


<@form action="create" method="post">

    <div class="forms-div">
        <label>Nome</label>
        <input type="text" required class="license" name="name" value="${(flasher.params.name)!}">
        <span class="error">${(flasher.errors.name)!}</span>

        <label>Dia</label>
        <input type="number" class="number-input" required name="day" value="${(flasher.params.day)!}" min="1" max="31"">
        <span class="error">${(flasher.errors.day)!}</span>

        <label>Mes</label>
        <input type="number" class="number-input" required name="month" value="${(flasher.params.month)!}" min="1" max="12"">
        <span class="error">${(flasher.errors.month)!}</span>

        <div class="forms-buttons">
            <@link_to class="bt-a">Cancelar</@link_to>
            <input class="bt-a" type="submit" value="Adicionar novo feriado">
        </div>
    </div>
</@form>



