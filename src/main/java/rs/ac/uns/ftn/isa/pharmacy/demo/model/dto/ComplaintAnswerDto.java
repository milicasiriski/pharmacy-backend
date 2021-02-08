package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

public class ComplaintAnswerDto {

    Long id;
    String answerText;

    public ComplaintAnswerDto(Long id, String answerText) {
        this.id = id;
        this.answerText = answerText;
    }

    public ComplaintAnswerDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    @Override
    public String toString() {
        return "ComplaintAnswerDto{" +
                "id=" + id +
                ", answerText='" + answerText + '\'' +
                '}';
    }
}
