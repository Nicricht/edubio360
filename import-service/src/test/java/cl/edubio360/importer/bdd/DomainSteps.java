package cl.edubio360.importer.bdd;

import cl.edubio360.importer.model.Importacion;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DomainSteps {
    private Importacion importacion;

    @Given("una importación")
    public void nuevaImportacion() {
        importacion = new Importacion("datos.csv", "VALIDADO");
    }

    @When("agrego un error de importación")
    public void agregoError() {
        importacion.addError(2, "Fila inválida");
    }

    @Then("la importación contiene {int} error")
    public void verifica(int cantidad) {
        assertEquals(cantidad, importacion.getErrores().size());
    }
}
