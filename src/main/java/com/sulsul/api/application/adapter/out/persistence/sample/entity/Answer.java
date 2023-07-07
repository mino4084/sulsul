package com.sulsul.api.application.adapter.out.persistence.sample.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    /*
    * @ManyToOne
    *
    * 답변은 하나의 질문에 여러개가 달릴 수 있는 구조이다. 따라서 답변은 Many(많은 것)가 되고 질문은 One(하나)이 된다.
    * @ManyToOne은 부모 자식 관계를 갖는 구조에서 사용한다. 여기서 부모는 Question, 자식은 Answer라고 할 수 있다.
    * */
    @ManyToOne
    private Question question;
}
