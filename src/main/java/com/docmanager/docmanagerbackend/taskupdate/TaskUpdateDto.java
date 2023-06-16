package com.docmanager.docmanagerbackend.taskupdate;

import com.docmanager.docmanagerbackend.attachedDocument.AttachedDocumentDto;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskUpdateDto {
    private Integer taskUpdateId;
    private String message;
    private Date postDate;
    private AttachedDocumentDto attachedDocument;
}
