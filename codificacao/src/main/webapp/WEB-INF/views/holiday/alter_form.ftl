<@content for="title">Alterar feriado</@content>

<span class="error_message"><@flash name="message"/></span>
<h1>Alterando feriado</h1>


<@form action="update" method="post">

    <div class="forms-div">
        <label>Nome</label>
        <input type="text" required class="license" name="name" value="${(holiday.name)!}">
        <span class="error">${(flasher.errors.name)!}</span>

        <label>Dia</label>
        <input type="number" class="number-input" required name="day" value="${(holiday.day)!}" min="1" max="31"">
        <span class="error">${(flasher.errors.day)!}</span>

        <label>Mes</label>
        <input type="number" class="number-input" required name="month" value="${(holiday.month)!}" min="1" max="12"">
        <span class="error">${(flasher.errors.month)!}</span>

        <input type="hidden" name="id" value="${holiday.id}">

        <div class="forms-buttons">
            <@link_to class="bt-a">Cancelar</@link_to>
            <input class="bt-a" type="submit" value="Alterar feriado">
        </div>
    </div>

</@form>



