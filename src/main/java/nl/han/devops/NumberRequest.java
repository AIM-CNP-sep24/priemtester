package nl.han.devops;

public class NumberRequest {
    public NumberRequest() {}
    public NumberRequest(String number) {
        this.number = number;
    }
    private String number;

    public String getNumber() {
        return number;
    }
}
