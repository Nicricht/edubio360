package cl.edubio360.guidance.bdd;

import cl.edubio360.guidance.model.SolicitudOrientacion;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DomainSteps {
    private SolicitudOrientacion solicitud;

    @Given("una nueva solicitud de orientación")
    public void nuevaSolicitud() {
        solicitud = new SolicitudOrientacion("student@example.test", 1L, "Orientación",
                LocalDateTime.now().plusDays(1));
    }

    @Then("su estado es {string}")
    public void verificaEstado(String estado) {
        assertEquals(estado, solicitud.getEstado());
    }
}
