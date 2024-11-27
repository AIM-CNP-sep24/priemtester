package nl.han.devops;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
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
public class PriemTesterIT {

    @Autowired
    private MockMvc mockMvc;

    // TODO: In integratie test moet je NIET perse alles mocken. Je kan ook de echte service gebruiken.
    @MockBean
    // @Autowired
    private PriemService priemService;
    private String inputPriem;
    private String inputNonPriem;

    @BeforeEach
    void setup() {
        // Arrange.
        // Mocking the service.
        inputPriem = mapJsonFromObject(new NumberRequest("7"));
        inputNonPriem = mapJsonFromObject(new NumberRequest("4"));

        // thenReturn werkt niet voor primitive return type als boolean, want daar kun je geen methodes op aanroepe
        // when(priemService.isPriemgetal(inputNonPriemInt).thenReturn(true));
        // Dus dan gebruik je doAnswer variant van Mockito, waarbij je het omdraait met gebruik van lambda expressie hierbij.
        doAnswer(invocation -> true).when(priemService).isPriemgetal(7);
        when(priemService.isPriemgetal(4)).thenReturn(false);
    }

    @Test
    public void testCheckCorrectIfPrimeWithInteger() throws Exception {
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
    @Disabled
    public void testCheckCorrectIfNotPrime_withInteger() throws Exception {
        // Sending request with a non-prime number
        mockMvc.perform(post("/priem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputPriem))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    @Disabled
    public void testCheckIfPrime_withPrimeBigInteger() throws Exception {
        // Mocking the service
        var input1 = new BigInteger("104729");

        // Sending request with a large prime number
        mockMvc.perform(post("/priem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(input1.toString()))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    @Disabled
    public void testCheckIfPrime_withNonPrimeBigInteger() throws Exception {
        // Mocking the service
        var input2 = new BigInteger("104728");
        // when(priemService.isPriemgetal(input2)).thenReturn(false);

        // Sending request with a large non-prime number
        mockMvc.perform(post("/priem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("" + input2))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    @Disabled
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

    @Test
    @Disabled
    public void testGetEndpoint() throws Exception {
        // Testing the GET endpoint
        mockMvc.perform(get("/priem"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, world!"));
    }

    private String mapJsonFromObject(final NumberRequest numberRequest)
    {
        String requestJson = "";

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        try
        {
            requestJson = ow.writeValueAsString(numberRequest);
        } catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }

        return requestJson;
    }
}
