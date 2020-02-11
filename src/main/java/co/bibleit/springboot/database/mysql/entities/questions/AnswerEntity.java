package co.bibleit.springboot.database.mysql.entities.questions;

import javax.persistence.*;

@Entity
@Table(name="answers")
public class AnswerEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="answer")
    private String answer;

    @OneToOne(mappedBy="answerEntity", cascade=CascadeType.ALL)
    private QuestionEntity questionEntity;

    public AnswerEntity() {
    }

    public AnswerEntity(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public QuestionEntity getQuestionEntity() {
        return questionEntity;
    }

    public void setQuestionEntity(QuestionEntity questionEntity) {
        this.questionEntity = questionEntity;
    }

    @Override
    public String toString() {
        return "AnswerEntity{" +
                "id=" + id +
                ", answer='" + answer + '\'' +
                '}';
    }
}