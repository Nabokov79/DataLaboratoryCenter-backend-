package ru.nabokovsg.data.mappers;

import org.mapstruct.Mapper;
import ru.nabokovsg.data.dto.remark.DocumentRemarkDto;
import ru.nabokovsg.data.dto.remark.NewDocumentRemarkDto;
import ru.nabokovsg.data.dto.remark.UpdateDocumentRemarkDto;
import ru.nabokovsg.data.models.DocumentRemark;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DocumentRemarkMapper {

    DocumentRemark mapToNewDocumentRemark(NewDocumentRemarkDto remarkDto);

    DocumentRemark mapToUpdateDocumentRemark(UpdateDocumentRemarkDto remarkDto);

    DocumentRemarkDto mapToDocumentRemarkDto(DocumentRemark remark);
}