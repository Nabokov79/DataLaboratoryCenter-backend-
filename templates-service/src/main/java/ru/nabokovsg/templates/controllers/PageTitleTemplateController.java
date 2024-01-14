package ru.nabokovsg.templates.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nabokovsg.templates.dto.pageTitle.NewPageTitleTemplateDto;
import ru.nabokovsg.templates.dto.pageTitle.PageTitleTemplateDto;
import ru.nabokovsg.templates.dto.pageTitle.UpdatePageTitleTemplateDto;
import ru.nabokovsg.templates.services.PageTitleTemplateService;
@RestController
@RequestMapping(
        value = "/template/title/page",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
@Tag(name="Шаблон титульного листа",
        description="API для работы с данными шаблона титульного листа")
public class PageTitleTemplateController {

    private final PageTitleTemplateService service;

    @Operation(summary = "Данные титульного листа")
    @PostMapping
    public ResponseEntity<PageTitleTemplateDto> save(@RequestBody @Valid NewPageTitleTemplateDto pageTitleDto) {
        return ResponseEntity.ok().body(service.save(pageTitleDto));
    }

    @Operation(summary = "Изменение данных титульного листа")
    @PatchMapping
    public ResponseEntity<PageTitleTemplateDto> update(@RequestBody @Valid UpdatePageTitleTemplateDto pageTitleDto) {
        return ResponseEntity.ok().body(service.update(pageTitleDto));
    }
}