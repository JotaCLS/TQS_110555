package TQS.Homework.Layers.Services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import TQS.Homework.Services.CurrencyAPI;

import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CurrencyAPITest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CurrencyAPI currencyAPI;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateCurrencyData() {
        // Simulando a resposta da API
        Map<String, Map<String, Double>> mockResponse = new HashMap<>();
        Map<String, Double> data = new HashMap<>();
        data.put("USD", 1.0);
        data.put("EUR", 0.85);
        mockResponse.put("data", data);

        // Simulando chamada à API
        when(restTemplate.getForObject(anyString(), eq(Map.class))).thenReturn(mockResponse);

        // Testando se os dados de câmbio são atualizados corretamente
        currencyAPI.updateCurrencyData();
        assertNotNull(currencyAPI.getLatestCurrencyData());
        assertFalse(currencyAPI.getLatestCurrencyData().isEmpty());
        assertEquals(1.0, currencyAPI.getLatestCurrencyData().get("data").get("USD"));
        assertEquals(0.85, currencyAPI.getLatestCurrencyData().get("data").get("EUR"));
    }

    @Test
    void testGetExchangeRate() {
        // Simulando a resposta da API
        Map<String, Map<String, Double>> mockResponse = new HashMap<>();
        Map<String, Double> data = new HashMap<>();
        data.put("USD", 1.0);
        data.put("EUR", 0.85);
        mockResponse.put("data", data);

        // Simulando chamada à API
        when(restTemplate.getForObject(anyString(), eq(Map.class))).thenReturn(mockResponse);

        // Testando se as taxas de câmbio são retornadas corretamente
        currencyAPI.updateCurrencyData();
        assertEquals(1.0f, currencyAPI.getExchangeRate("USD"));
        assertEquals(0.85f, currencyAPI.getExchangeRate("EUR"));
    }

    @Test
    void testExceptionWhenDataUnavailable() {
        // Simulando que os dados de câmbio não estão disponíveis
        currencyAPI.currencyData = null;

        // Verificando se uma exceção é lançada ao tentar obter as taxas de câmbio
        assertThrows(RuntimeException.class, () -> currencyAPI.getExchangeRate("USD"));
    }

    @Test
    void testExceptionWhenCurrencyNotFound() {
        // Simulando a resposta da API com apenas uma moeda disponível
        Map<String, Map<String, Double>> mockResponse = new HashMap<>();
        Map<String, Double> data = new HashMap<>();
        data.put("USD", 1.0);
        mockResponse.put("data", data);

        // Simulando chamada à API
        when(restTemplate.getForObject(anyString(), eq(Map.class))).thenReturn(mockResponse);

        // Verificando se uma exceção é lançada ao solicitar uma moeda que não está presente nos dados de câmbio
        assertThrows(IllegalArgumentException.class, () -> currencyAPI.getExchangeRate("EUR"));
    }

    @Test
    void getExchangeRate_whenRatesMapNotFound_thenThrowException() {
        // Simulando a resposta da API com um mapa de taxas de câmbio nulo
        Map<String, Map<String, Double>> mockResponse = new HashMap<>();
        mockResponse.put("data", null);

        // Simulando chamada à API
        when(restTemplate.getForObject(anyString(), eq(Map.class))).thenReturn(mockResponse);

        // Verificando se uma exceção é lançada ao tentar obter as taxas de câmbio
        assertThrows(RuntimeException.class, () -> currencyAPI.getExchangeRate("USD"));
    }

    @Test
    void getExchangeRate_whenCurrencyDataIsNull_thenThrowException() {
        // Simulando que os dados de câmbio estão nulos
        currencyAPI.currencyData = null;

        // Verificando se uma exceção é lançada ao tentar obter as taxas de câmbio
        assertThrows(RuntimeException.class, () -> currencyAPI.getExchangeRate("USD"));
    }
}
