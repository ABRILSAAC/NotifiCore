import org.example.notification.consumer.NotificationConsumer;
import org.example.notification.model.EventModel;
import org.example.notification.model.ReceiverModel;

void main() {
    ReceiverModel receiver = new ReceiverModel();
    //Caso 1
    EventModel event = new EventModel("RETRASO_MENOR");
    receiver.setRole("CLIENTE");
    receiver.setPhone("123456789");

    //Caso 2
    //EventModel event = new EventModel("RETRASO_CRITICO");
    //receiver.setRole("REPARTIDOR");

    //Caso 3
    //EventModel event = new EventModel("ENTREGA_FALLIDA");
    //receiver.setRole("REPARTIDOR");


    NotificationConsumer notificationConsumer = new NotificationConsumer(event, receiver);
    notificationConsumer.startNotificationFromConsumer();
}
