package com.docmanager.docmanagerbackend.attachedDocument;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachedDocumentDto {
    private Integer attachedDocumentId;
    private String docName;
    private String path;
}
