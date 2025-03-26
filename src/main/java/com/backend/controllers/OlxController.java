package com.backend.controllers;

import com.backend.models.OlxItem;
import com.backend.services.OlxService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
public class OlxController {
    private final OlxService olxService;

    public OlxController() {
        olxService = new OlxService();
    }

    @GetMapping("/olx/parse")
    public ResponseEntity<List<OlxItem>> getOlxItems(
            @RequestParam(name = "url", required = true) String url,
            @RequestParam(name = "save", required = false, defaultValue = "false") String saveLine
    ) throws IOException {
        if (Objects.equals(url, "") || !url.contains("olx")) {
            return ResponseEntity.badRequest().build();
        }

        boolean save = Boolean.parseBoolean(saveLine);
        List<OlxItem> items = this.olxService.getItemsFromCategory(url, save);

        return ResponseEntity.ok(items);
    }
}
