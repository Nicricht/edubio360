package cl.edubio360.academic.bdd;

import cl.edubio360.academic.model.OfertaAcademica;
import cl.edubio360.academic.model.Sede;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DomainSteps {
    private Sede sede;

    @Given("una sede académica")
    public void unaSede() {
        sede = new Sede("Sede Centro", "Institución", "Concepción");
    }

    @When("agrego una oferta académica")
    public void agregoOferta() {
        sede.addOferta(new OfertaAcademica("Ingeniería Informática", "Presencial", "Diurna",
                new BigDecimal("3000000"), new BigDecimal("180000"), sede));
    }

    @Then("la sede contiene {int} oferta")
    public void verifica(int cantidad) {
        assertEquals(cantidad, sede.getOfertas().size());
    }
}
