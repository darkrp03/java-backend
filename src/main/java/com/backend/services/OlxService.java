package com.backend.services;

import com.backend.models.OlxItem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class OlxService {
    private final String baseUrl;
    private final ExcelService excelService;

    public OlxService() {
        this.baseUrl = "https://www.olx.ua";
        this.excelService = new ExcelService();
    }

    public List<OlxItem> getItemsFromCategory(String url, boolean save) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements cards = doc.select("div[data-testid=\"l-card\"]");

        List<OlxItem> items = new ArrayList<>();

        for (Element card : cards) {
            String title = Objects.requireNonNull(card.selectFirst("a > h4")).text();
            String href = Objects.requireNonNull(card.selectFirst("a")).attr("href");
            String priceLine = Objects.requireNonNull(card.selectFirst("p[data-testid=\"ad-price\"]"))
                        .text()
                        .replace("грн.", "")
                        .replace("Договірна", "")
                        .replace(" ", "");

            if (priceLine.isEmpty()) {
                continue;
            }

            href = this.baseUrl + href;
            double price = Double.parseDouble(priceLine);

            OlxItem item = new OlxItem(title, price, href);
            items.add(item);
        }

        if (save) {
            String filepath = "olx_items.xlsx";
            this.excelService.writeOlxItemsToExcel(items, filepath);
        }

        return items;
    }
}
