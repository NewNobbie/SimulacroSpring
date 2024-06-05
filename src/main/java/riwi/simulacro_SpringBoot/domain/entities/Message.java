package riwi.simulacro_SpringBoot.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity(name = "messages")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_Sender_id",referencedColumnName = "id")
    private User senderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_Receiver_id",referencedColumnName = "id")
    private User receiverId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Message_courser_id",referencedColumnName = "id")
    private Courses coursesId;

    @Column(columnDefinition = "TEXT")
    private String messageContent;

    private LocalDate SentDate;

}
