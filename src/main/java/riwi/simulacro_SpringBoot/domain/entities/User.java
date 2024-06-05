package riwi.simulacro_SpringBoot.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import riwi.simulacro_SpringBoot.util.enums.EnumRole;

import java.util.List;

@Entity(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true,length = 150)
    private String username;
    @Column(nullable = false,length = 150)
    private String password;
    @Column(nullable = false,unique = true,length = 150)
    private String email;
    @Column(nullable = false,length = 200)
    private String fullName;
    @Enumerated
    private EnumRole role;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            mappedBy = "user",
            fetch= FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = false
    )
    private List<Enrollment> enrollments;

   @ToString.Exclude
   @EqualsAndHashCode.Exclude
   @OneToMany(
            mappedBy = "user_id",
            fetch= FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = false
   )
    private List<Submission> submissions;

   @ToString.Exclude
   @EqualsAndHashCode.Exclude
   @OneToMany(
           mappedBy = "senderId",
           fetch= FetchType.EAGER,
           cascade = CascadeType.ALL,
           orphanRemoval = false
   )    private List<Message> messagesSender;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            mappedBy = "receiverId",
            fetch= FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = false
    )    private List<Message> messagesReceiver;


    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
            mappedBy = "user",
            fetch= FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = false
    )
    private List<Courses> courses;

}
