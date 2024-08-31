package kucoinApi;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StreamApiExamples {
    public List<TickerData> getTicker() {
        return given().
                contentType(ContentType.JSON)
                .when()
                .get("https://api.kucoin.com/api/v1/market/allTickers")
                .then()
                .extract().jsonPath().getList("data.ticker", TickerData.class);
    }

    @Test
    void checkCrypto() {
        List<TickerData> usdTickers = getTicker().stream().filter(x -> x.getSymbol().endsWith("USDT")).toList();
        usdTickers.stream().allMatch(x -> x.getSymbol().endsWith("USDT"));
    }

    @Test
    void sortHighToLow() {
        List<TickerData> highToLow = getTicker().stream().filter(x -> x.getSymbol().endsWith("USDT")).sorted(new Comparator<TickerData>() {
            @Override
            public int compare(TickerData o1, TickerData o2) {
                return o2.getChangeRate().compareTo(o1.getChangeRate());
            }
        }).toList();
        List<TickerData> top10 = highToLow.stream().limit(10)
                .toList();
        Assertions.assertEquals(top10.getFirst().getSymbol(), "GTT-USDT");
    }

    @Test
    void sortLowToHigh() {
        List<TickerData> lowToHigh = getTicker().stream().filter(x -> x.getSymbol().endsWith("USDT"))
                .sorted(new TickerComparatorLow()).limit(10)
                .toList();
        Assertions.assertEquals(lowToHigh.getFirst().getSymbol(), "KICKS-USDT");
    }

    @Test
    void map() {
        // Map<String, Float> usd = new HashMap<>();
        // List<String> lowerCases = getTicker().stream().map(x -> x.getSymbol().toLowerCase()).toList();
        //getTicker().forEach(x -> usd.put(x.getSymbol(), Float.parseFloat(x.getChangeRate())));
        List<TickerShort> shortList = new ArrayList<>();
        getTicker().forEach(x -> shortList.add(new TickerShort(x.getSymbol(), Float.parseFloat(x.getChangeRate()))));
        Assertions.assertEquals(shortList.get(1).getName(), "HLG-USDT");
    }
}
