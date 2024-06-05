package riwi.simulacro_SpringBoot.domain.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

// 23
@Entity(name = "assignment")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100,nullable = false)
    private String  assignmentTitle;
    @Column(columnDefinition = "TEXT",nullable = false)
    private String  description;
    @Column(nullable = false)
    private LocalDateTime dueDate;

    //23.1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="lesson_id",referencedColumnName = "id")
    private Lesson lesson;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            mappedBy = "assignment_id",
            fetch= FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = false
    )    private List<Submission> assignments;

}
