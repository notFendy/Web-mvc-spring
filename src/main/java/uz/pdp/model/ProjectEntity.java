package uz.pdp.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectEntity {
    private String projectId;
    private String projectName;
    private String projectDeveloper;
    private String originalName;

}
