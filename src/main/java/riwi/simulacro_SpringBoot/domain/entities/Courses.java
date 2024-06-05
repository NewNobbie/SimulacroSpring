package riwi.simulacro_SpringBoot.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


//3 
@Entity(name= "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String course_name;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    private User user;

    @OneToMany(
            mappedBy = "courses",
            fetch= FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Lesson> lessons;


    @OneToMany(
            mappedBy = "coursesId",
            fetch= FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Message> messages;

    @OneToMany(
            mappedBy = "course",
            fetch= FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Enrollment> enrollments;
}
