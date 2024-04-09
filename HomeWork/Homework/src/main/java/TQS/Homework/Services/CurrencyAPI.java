package TQS.Homework.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import TQS.Homework.Component.RatesMapNotFoundException;
import jakarta.annotation.PostConstruct;

import java.util.HashMap;
import java.util.Map;



@Service
public class CurrencyAPI {

    public Map<String, Map<String, Double>> currencyData;

    private final RestTemplate restTemplate;

    @Autowired
    public CurrencyAPI(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostConstruct
    public void init() {
        // Inicializa o mapa de dados de câmbio ao iniciar o serviço
        updateCurrencyData();
    }

    @Cacheable("currencyData")
    public Map<String, Map<String, Double>> getLatestCurrencyData() {
        return currencyData;
    }

    @CacheEvict(value = "currencyData", allEntries = true)
    public void updateCurrencyData() {
        String apiUrl = "https://api.freecurrencyapi.com/v1/latest?apikey=fca_live_y0Ir20YdHuh6whj4MnoRNaj7Sc50lUsY6K20IEit";

        // Faz uma solicitação GET para a API e obtém a resposta como um objeto Map
        Map<String, Map<String, Double>> response = restTemplate.getForObject(apiUrl, Map.class);

        // Atualiza os dados de câmbio localmente
        currencyData = new HashMap<>();
        if (response != null){
            currencyData.put("data", response.get("data"));
        }else{
            currencyData = null;
        }
    }

    public synchronized float getExchangeRate(String currency) {
        // Verifica se o mapa de dados de câmbio está nulo ou vazio
        if (currencyData == null || currencyData.isEmpty()) {
            // Atualiza os dados de câmbio
            updateCurrencyData();
        }

        // Se os dados de câmbio ainda estiverem nulos, lança uma exceção ou retorne um valor padrão, dependendo do seu caso de uso
        if (currencyData == null) {
            throw new RuntimeException("Currency data not available");
        }

        // Obtém o mapa interno contendo as taxas de câmbio
        Map<String, Double> ratesMap = currencyData.get("data");

        // Verifica se o mapa interno foi encontrado
        if (ratesMap == null) {
            throw new RatesMapNotFoundException("Currency map not found.");
        }

        // Obtém a taxa de câmbio para a moeda especificada
        Double exchangeRateDouble = ratesMap.get(currency);

        // Verifica se a taxa de câmbio foi encontrada
        if (exchangeRateDouble == null) {
            throw new IllegalArgumentException("Specified currency not found in exchange rates.");
        }

        // Retorna a taxa de câmbio
        return exchangeRateDouble.floatValue();
    }
}
