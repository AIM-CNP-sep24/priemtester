package nl.han.devops;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigInteger;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PriemController.class)
public class PriemControllerIT {

    @Autowired
    private MockMvc mockMvc;

    // TODO: In integratie test moet je NIET perse alles mocken. Je kan ook de echte service gebruiken..
    // Hiervoor hebben we een aparte test PriemTesterIT met de @SpringBootTest annotatie in plaats van @WebMvcTest.
    @MockBean
    // @Autowired
    private CustomPriemService priemService;

    private String inputPriem;
    private String inputNonPriem;

    @BeforeEach
    void setup() throws JsonProcessingException {
        // Arrange.
        // Mocking the service.
        inputPriem = mapJsonFromObject(new NumberRequest("7"));
        inputNonPriem = mapJsonFromObject(new NumberRequest("4"));

        // thenReturn werkt niet voor primitive return type als boolean, want daar kun je geen methodes op aanroepen.
        // when(priemService.isPriemgetal(inputNonPriemInt).thenReturn(true));

        // Dus dan gebruik je doAnswer variant van Mockito, waarbij je het omdraait met gebruik van lambda expressie hierbij.
        doAnswer(invocation -> true).when(priemService).isPriemgetal(7);
        when(priemService.isPriemgetal(4)).thenReturn(false);
    }

    @Test
    void testCheckCorrectIfPrimeWithInteger() throws Exception {
        // Act.
        // Sending request with a prime number
        System.out.println("inputPriem: " + inputPriem);
        mockMvc.perform(post("/priem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputPriem))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void testCheckCorrectIfNotPrime_withInteger() throws Exception {
        // Sending request with a non-prime number
        mockMvc.perform(post("/priem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputNonPriem))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    public void testCheckIfPrime_withPrimeBigInteger() throws Exception {
        var input1 = new BigInteger("104729");

        // Sending request with a large prime number
        mockMvc.perform(post("/priem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputPriem))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void testCheckIfPrime_withNonPrimeBigInteger() throws Exception {
        // Sending request with a large non-prime number
        mockMvc.perform(post("/priem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputNonPriem))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    public void testInvalidInput() throws Exception {
        // Arrange
        String input = "not_a_number";

        // Act and Assert
        mockMvc.perform(post("/priem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\"" + input + "\"")) // Wrap the input in quotes to mimic JSON string
                .andExpect(status().isBadRequest()) // Assert 400 status
                .andExpect(content().string("Invalid input: Not a valid number")); // Assert error message
    }

    private String mapJsonFromObject(final NumberRequest numberRequest) throws JsonProcessingException {
        var mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        var ow = mapper.writer().withDefaultPrettyPrinter();

        var requestJson = ow.writeValueAsString(numberRequest);
        return requestJson;
    }
}
