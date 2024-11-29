package nl.han.devops.priem;

public class IsPriemResponse {

    public IsPriemResponse() {}
    public IsPriemResponse(String kandidaat, boolean isPriem, String userName, String datumTijd, bericht) {
        this.kandidaat = kandidaat;
        this.isPriem = isPriem;
        this.userName = userName;
        this.datumTijd = datumTijd;
        this.bericht = bericht;
    }
    private String kandidaat;
    private boolean isPriem;
    private String userName;
    private String datumTijd;
    private String bericht;

    public String getKandidaat() {
        return kandidaat;
    }

    boolean getIsPriem() {
        return isPriem;
    }

    public String getUserName() {
        return userName;
    }

    public String getDatumTijd() {
        return datumTijd;
    }

    public String getBericht() {
        return bericht;
    }
}
