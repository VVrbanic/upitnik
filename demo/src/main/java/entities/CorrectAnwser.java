package entities;

public record CorrectAnwser(String anwser) {
    public CorrectAnwser(String anwser){
        this.anwser = anwser;
    }

    @Override
    public String anwser() {
        return anwser;
    }
}
