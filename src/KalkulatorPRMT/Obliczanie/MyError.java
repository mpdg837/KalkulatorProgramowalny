package KalkulatorPRMT.Obliczanie;

public class MyError extends Exception{

    private String mess;
    public MyError(String message){
        mess = message;
    }

    @Override
    public String getMessage() {
        return mess;
    }
}
