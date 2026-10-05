package cl.edubio360.analytics.bdd;

import cl.edubio360.analytics.model.Metrica;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DomainSteps {
    private Metrica metrica;

    @Given("una métrica")
    public void nuevaMetrica() {
        metrica = new Metrica("arancel", "Descripción");
    }

    @When("agrego un punto de métrica")
    public void agregoPunto() {
        metrica.addPunto("Tecnología", 3000000d);
    }

    @Then("la métrica contiene {int} punto")
    public void verifica(int cantidad) {
        assertEquals(cantidad, metrica.getPuntos().size());
    }
}
