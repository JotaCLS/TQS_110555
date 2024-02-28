import org.example.Stock;
import org.example.StocksPortfolio;
import org.example.iStockmarketService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;







@ExtendWith(MockitoExtension.class)
public class totalValueTest {
    
    @Mock // mock object
    iStockmarketService stockmarket;

    @InjectMocks // classe a qual o mock vai ser injetado
    StocksPortfolio portfolio;

    @Test
    public void getTotalValue() {
        Stock stock = new Stock("Amazon", 2);
        Stock stock2 = new Stock("Google", 5);
        portfolio.addStock(stock);
        portfolio.addStock((stock2));
        when(stockmarket.lookUpPrice("Google")).thenReturn(50.0);
        when(stockmarket.lookUpPrice("Amazon")).thenReturn(100.0);
        assertEquals(450.0, portfolio.totalValue());
        verify(stockmarket).lookUpPrice("Amazon");
        verify(stockmarket).lookUpPrice("Google");
        assertThat(portfolio.totalValue(), is(450.0));

    }
}
