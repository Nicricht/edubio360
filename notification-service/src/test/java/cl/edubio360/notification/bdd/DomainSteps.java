package cl.edubio360.notification.bdd;

import cl.edubio360.notification.model.Notificacion;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DomainSteps {
    private Notificacion notificacion;

    @Given("una notificación")
    public void nuevaNotificacion() {
        notificacion = new Notificacion("ORIENTACION", "student@example.test", "Mensaje");
    }

    @When("agrego un envío")
    public void agregoEnvio() {
        notificacion.addEnvio("EMAIL", "OK");
    }

    @Then("la notificación contiene {int} envío")
    public void verifica(int cantidad) {
        assertEquals(cantidad, notificacion.getEnvios().size());
    }
}
