package com.sulsul.api.application.adapter.out.persistence.sample.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    /*
    * 테이블의 컬럼명
    *
    * 위의 Question 엔티티에서 작성일시에 해당하는 createDate 속성의 실제 테이블의 컬럼명은 create_date가 된다.
    * 즉 createDate처럼 대소문자 형태의 카멜케이스(Camel Case) 이름은 create_date 처럼 모두 소문자로 변경되고 언더바(_)로 단어가 구분되어 실제 테이블 컬럼명이 된다.
    * */

    /*
    * @Id
    *
    * 고유 번호 id 속성에 적용한 @Id 애너테이션은 id 속성을 기본 키로 지정한다.
    * 기본 키로 지정하면 이제 id 속성의 값은 데이터베이스에 저장할 때 동일한 값으로 저장할 수 없다.
    * 고유 번호를 기본 키로 한 이유는 고유 번호는 엔티티에서 각 데이터를 구분하는 유효한 값으로 중복되면 안 되기 때문이다.
    * 데이터베이스에서는 id와 같은 특징을 가진 속성을 기본 키(primary key)라고 한다.
    *
    * @GeneratedValue
    *
    * 해당 애너테이션을 적용하면 데이터를 저장할 때 해당 속성에 값을 따로 세팅하지 않아도 1씩 자동으로 증가하여 저장된다.
    * strategy는 고유번호를 생성하는 옵션으로 GenerationType.IDENTITY는 해당 컬럼만의 독립적인 시퀀스를 생성하여 번호를 증가시킬 때 사용한다.
    *
    * strategy 옵션을 생략할 경우에 @GeneratedValue 애너테이션이 지정된 컬럼들이 모두 동일한 시퀀스로 번호를 생성하기 때문에 일정한 순서의 고유번호를 가질수 없게 된다.
    * 이러한 이유로 보통 GenerationType.IDENTITY를 많이 사용한다.
    * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /*
    * @Column
    *
    * 엔티티의 속성은 테이블의 컬럼명과 일치하는데 컬럼의 세부 설정을 위해 @Column 애너테이션을 사용한다. length는 컬럼의 길이를 설정할때 사용하고 columnDefinition은 컬럼의 속성을 정의할 때 사용한다. columnDefinition = "TEXT"은 "내용"처럼 글자 수를 제한할 수 없는 경우에 사용한다.
    *
    * 엔티티의 속성은 @Column 애너테이션을 사용하지 않더라도 테이블 컬럼으로 인식한다.
    * 테이블 컬럼으로 인식하고 싶지 않은 경우에만 @Transient 애너테이션을 사용한다
    * */
    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    /*
    * @OneToMany
    *
    * Answer 엔티티 객체로 구성된 answerList를 속성으로 추가하고 @OneToMany 애너테이션을 설정했다.
    * Question 에서 답변을 참조하려면 question.getAnswerList()를 호출하면 된다.
    * @OneToMany 애너테이션에 사용된 mappedBy는 참조 엔티티의 속성명을 의미한다. 즉, Answer 엔티티에서 Question 엔티티를 참조한 속성명 question을 mappedBy에 전달해야 한다.
    *
    * cascade = CascadeType.REMOVE
    *
    * 질문 하나에는 여러 개의 답변이 작성될 수 있다.
    * 이때 질문을 삭제하면 그에 달린 답변들도 모두 함께 삭제하기 위해서 @OneToMany의 속성으로 cascade = CascadeType.REMOVE를 사용했다.
    * */
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;

}
