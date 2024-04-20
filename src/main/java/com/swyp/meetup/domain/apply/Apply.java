package com.swyp.meetup.domain.apply;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Apply {
    @EmbeddedId
    private ApplyId id;
}
