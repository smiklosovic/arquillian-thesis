// class to fire
public class EventToFire {}

// injecting of event used for firing
@Inject Event<EventToFire> event;

// actual  firing of the event
public void observingMethod(@Observes SomeEvent e) {
  event.fire(new EventToFire());
}
