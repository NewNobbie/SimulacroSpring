package riwi.simulacro_SpringBoot.domain.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "lesson")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100,nullable = false)
    private String  lessonTitle;
    @Column(columnDefinition = "TEXT",nullable = false)
    private String  content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="courses_id",referencedColumnName = "id")
    private Courses courses;

    // 23.4
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            mappedBy = "lesson",
            fetch= FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = false
    )
    private List<Assignment> assignement;
}
