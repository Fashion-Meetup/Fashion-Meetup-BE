package com.swyp.meetup.domain.meet;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Meet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000, nullable = false)
    private String title;

    private LocalDateTime meetTime;

    @Column(length = 50)
    private String region;

    @Column(length = 1000)
    private String address;

    private Byte maximum;

    @Column(length = 2000)
    private String introduce;

    @Enumerated(EnumType.STRING)
    private MeetStatus status;

    @Column(length = 100)
    private String image;

    private Long masterId;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meet meet = (Meet) o;
        return Objects.equals(id, meet.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
