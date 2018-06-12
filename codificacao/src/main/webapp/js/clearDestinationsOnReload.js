
$(document).ready(function () {
    resetForms();
});

function resetForms() {
    let input = document.getElementById("item");
    input.value = "";

    let selectState = document.getElementById("select_state");
    selectState.selectedIndex = 0;
}