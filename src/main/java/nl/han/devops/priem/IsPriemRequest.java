package nl.han.devops.priem;

public class IsPriemRequest {
    private String number;

    private final String userName;

    public IsPriemRequest(String number, String userName) {
        this.number = number;
        this.userName = userName;
    }

    public String getNumber() {
        return number;
    }

    public String getUserName() {
        return userName;
    }
}
