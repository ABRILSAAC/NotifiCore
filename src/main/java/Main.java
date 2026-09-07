import org.example.notification.consumer.NotificationConsumer;
import org.example.notification.model.EventModel;
import org.example.notification.model.ReceiverModel;

void main() {
    ReceiverModel receiver = new ReceiverModel();
    //--- Grupo A: camino feliz (regla explícita por rol) ---

    //Caso 1
        EventModel event = new EventModel("RETRASO_MENOR");
        receiver.setRole("CLIENTE");
        receiver.setPhone("123456789");

    //Caso 2
    //EventModel event = new EventModel("RETRASO_CRITICO");
    //receiver.setRole("CLIENTE");
    //receiver.setPhone("123456789");

    //Caso 3
    //EventModel event = new EventModel("RETRASO_CRITICO");
    //receiver.setRole("REPARTIDOR");
    //receiver.setDeviceId("device-001");

    //Caso 4
    //EventModel event = new EventModel("ENTREGA_FALLIDA");
    //receiver.setRole("CLIENTE");
    //receiver.setPhone("123456789");

    //Caso 5
    //EventModel event = new EventModel("ENTREGA_FALLIDA");
    //receiver.setRole("REPARTIDOR");
    //receiver.setPhone("123456789");

    //--- Grupo B: rama DEFAULT (rol no mapeado explícitamente) ---

    //Caso 6
    //EventModel event = new EventModel("RETRASO_MENOR");
    //receiver.setRole("ADMIN");
    //receiver.setEmail("admin@correo.com");

    //Caso 7
    //EventModel event = new EventModel("RETRASO_CRITICO");
    //receiver.setRole("ADMIN");
    //receiver.setEmail("admin@correo.com");

    //Caso 8
    //EventModel event = new EventModel("RETRASO_CRITICO");
    //receiver.setRole("SOPORTE");
    //receiver.setEmail("soporte@correo.com");

    //--- Grupo C: rol nulo (no se llama setRole) ---

    //Caso 9
    //EventModel event = new EventModel("RETRASO_MENOR");
    //receiver.setEmail("sinrol@correo.com");

    //Caso 10
    //EventModel event = new EventModel("ENTREGA_FALLIDA");
    //receiver.setPhone("123456789");

    //--- Grupo D: tipo de evento desconocido ---

    //Caso 11
    //EventModel event = new EventModel("EVENTO_INEXISTENTE");
    //receiver.setRole("CLIENTE");
    //receiver.setPhone("123456789");

    //--- Grupo E: falta el dato requerido por el canal resuelto (prueba isValid()) ---

    //Caso 12
    //EventModel event = new EventModel("RETRASO_MENOR");
    //receiver.setRole("CLIENTE");
    // (sin phone)

    //Caso 13
    //EventModel event = new EventModel("RETRASO_MENOR");
    //receiver.setRole("ADMIN");
    // (sin email) -> este es el caso que motivó la validación con isValid()

    //Caso 14
    //EventModel event = new EventModel("RETRASO_CRITICO");
    //receiver.setRole("REPARTIDOR");
    // (sin deviceId)

    //Caso 15
    //EventModel event = new EventModel("ENTREGA_FALLIDA");
    //receiver.setRole("CLIENTE");
    // (sin phone)


    NotificationConsumer notificationConsumer = new NotificationConsumer(event, receiver);
    notificationConsumer.dispatchNotification();
}
