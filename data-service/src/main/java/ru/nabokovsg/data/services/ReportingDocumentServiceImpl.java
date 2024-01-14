package ru.nabokovsg.data.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nabokovsg.data.dto.reportingDocument.NewReportingDocumentDto;
import ru.nabokovsg.data.dto.reportingDocument.ReportingDocumentDto;
import ru.nabokovsg.data.dto.reportingDocument.UpdateReportingDocumentDto;
import ru.nabokovsg.data.exceptions.NotFoundException;
import ru.nabokovsg.data.mappers.ReportingDocumentMapper;
import ru.nabokovsg.data.models.ReportingDocument;
import ru.nabokovsg.data.models.enums.DocumentType;
import ru.nabokovsg.data.repository.ReportingDocumentRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportingDocumentServiceImpl implements ReportingDocumentService {

    private final ReportingDocumentRepository repository;
    private final ReportingDocumentMapper mapper;

    @Override
    public List<ReportingDocumentDto> save(DocumentType documentType, List<NewReportingDocumentDto> documentDto) {
        return repository.saveAll(documentDto.stream()
                                             .map(r -> {
                                                 ReportingDocument document = mapper.mapToNewReportingDocument(documentType, r);
                                                 document.setTitle(document.getTitle().toUpperCase());
                                                 return document;
                                             })
                                             .toList())
                                             .stream()
                                             .map(mapper::mapToReportingDocumentDto)
                                             .toList();
    }

    @Override
    public List<ReportingDocumentDto> update(List<UpdateReportingDocumentDto> documentsDto) {
        List<Long> ids = documentsDto.stream()
                                     .map(UpdateReportingDocumentDto::getId)
                                     .toList();
        Map<Long, ReportingDocument> documentsDb = repository.findAllById(ids)
                                                        .stream()
                                                        .collect(Collectors.toMap(ReportingDocument::getId, m -> m));
        if (!documentsDb.isEmpty()) {
            for (UpdateReportingDocumentDto documentDto : documentsDto) {
                ReportingDocument document = documentsDb.get(documentDto.getId());
                if (document != null) {
                    documentDto.setTitle(documentDto.getTitle().toUpperCase());
                    documentsDb.put(documentDto.getId(), mapper.mapToUpdateReportingDocument(document, documentDto));
                }
                return repository.saveAll(documentsDb.values()).stream()
                                                               .map(mapper::mapToReportingDocumentDto)
                                                               .toList();
            }
        }
       throw new NotFoundException(String.format("Reporting document with ids=%s not found for update", ids));
    }

    @Override
    public ReportingDocumentDto get(Long id) {
        return mapper.mapToReportingDocumentDto(
                repository.findById(id)
                          .orElseThrow(() -> new RuntimeException(String.format("ReportingDocument with id=%s", id))));
    }

    @Override
    public List<ReportingDocumentDto> getAll() {
        return repository.findAll().stream().map(mapper::mapToReportingDocumentDto).toList();
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return;
        }
        throw new NotFoundException(String.format("reporting document with id=%s not found for delete", id));
    }
}