package nl.han.devops;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/** Deze Integratie test is bedoeld als ECHTE integratie test, zonder mocks.
 * Hierbij wordt de applicatie volledig opgestart en wordt de service aangeroepen.
 */
@SpringBootTest
class PriemTesterIT {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private String inputPriem;
    private String inputNonPriem;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        // Hardcoded JSON input in plaats van gebruik van ObjectMapper.
        // Voor 'cyclomatic complexity' van 1 in plaats van 2, zoals test code idealiter heeft (Mark Seemann).
        // > "Tests, then, should have a cyclomatic complexity of 1. In other words, you write (test) code
        // with a cyclomatic complexity of 1 in order to test code with a higher cyclomatic complexity."
        // Bronnen:
        // - https://blog.ploeh.dk/2018/11/12/what-to-test-and-not-to-test/#7db262526ca2459b813c0fda4f1e6999
        // - https://blog.ploeh.dk/2019/12/09/put-cyclomatic-complexity-to-good-use/

        inputPriem = "{\"number\":\"7\"}";
        inputNonPriem = "{\"number\":\"4\"}";
    }

    @Test
    void contextLoads() {
        // Test to ensure the application context loads successfully
    }

    @Test
    void testCheckCorrectIfPrimeWithInteger() throws Exception {
        mockMvc.perform(post("/priem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputPriem))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void testCheckCorrectIfNotPrime_withInteger() throws Exception {
        mockMvc.perform(post("/priem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputNonPriem))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    public void testCheckIfPrime_withPrimeBigInteger() throws Exception {
        mockMvc.perform(post("/priem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputPriem))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void testInvalidInput() throws Exception {
        String input = "not_a_number";

        mockMvc.perform(post("/priem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\"" + input + "\""))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid input: Not a valid number"));
    }
}