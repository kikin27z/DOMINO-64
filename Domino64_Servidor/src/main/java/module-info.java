
module Servidor {
    requires java.logging;
    requires BusExample; //Domino64_EventBus
    requires EventoLogico;//Domino64_EventoLogico
    requires PublicadorSuscriptor;
    requires Evento;//Domino64_Evento
}
