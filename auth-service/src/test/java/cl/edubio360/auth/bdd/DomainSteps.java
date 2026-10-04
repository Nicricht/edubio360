package cl.edubio360.auth.bdd;

import cl.edubio360.auth.model.UserEntity;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DomainSteps {
    private UserEntity user;

    @Given("un usuario estudiante")
    public void usuarioEstudiante() {
        user = new UserEntity("user@example.test", "hash", "STUDENT");
    }

    @Then("el rol principal es {string}")
    public void verificaRol(String role) {
        assertEquals(role, user.getPrimaryRole());
    }
}
