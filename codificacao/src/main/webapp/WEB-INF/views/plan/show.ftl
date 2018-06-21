
<@content for="title">Plano: ${plan.id}</@content>



<@link_to>Retornar a listagem de planos</@link_to>



<strong>Disponivel por: "${plan.availability_condition}"</strong>,
<strong>Preco da passagem:</strong> ${plan.ticket_price}
<strong>Valor diario:</strong> ${plan.daily_value}
<strong>Numero de reservas disponiveis:</strong> ${plan.available_reservations}

<@link_to controller="availability" action="plan" id=plan.id>Disponibilidades de um plano</@link_to>
<@link_to controller="reservation" action="list" id=plan.id>Passageiros do plano</@link_to>