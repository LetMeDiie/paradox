package kz.amixady.paradox.task.persistence.entities;


import jakarta.persistence.*;
import kz.amixady.paradox.task.enums.TaskStatus;
import lombok.*;


@Embeddable
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDetails {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TaskStatus status;
}
