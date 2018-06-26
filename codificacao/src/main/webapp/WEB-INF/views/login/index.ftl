<@content for="title">Login</@content>

<span class="error_message"><@flash name="message"/></span>


<@form action="login" method="post">

    <div class="forms-div">

        <label>Usuario</label>
        <input type="text" name="user" required placeholder="Digite o nome do usuario">

        <label>Senha</label>
        <input type="password" name="password" required placeholder="Digite a senha do usuario">

        <div class="forms-buttons">
            <input class="bt-a" type="submit" value="Logar" id="log-bt">
        </div>
    </div>

</@form>