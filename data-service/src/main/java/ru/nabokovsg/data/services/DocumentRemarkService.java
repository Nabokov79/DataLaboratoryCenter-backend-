package ru.nabokovsg.data.services;

import ru.nabokovsg.data.dto.remark.DocumentRemarkDto;
import ru.nabokovsg.data.dto.remark.NewDocumentRemarkDto;
import ru.nabokovsg.data.dto.remark.UpdateDocumentRemarkDto;

import java.util.List;

public interface DocumentRemarkService {

    DocumentRemarkDto save(NewDocumentRemarkDto remarkDto);

    DocumentRemarkDto update(UpdateDocumentRemarkDto remarkDto);

    List<DocumentRemarkDto> getAll(Long employeeId,Long employeeDocumentId, Long employeeDrawingId);

    void delete(Long id);
}