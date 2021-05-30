package KalkulatorPRMT.Obliczanie;

public class DodatekMatematyczny {

    // Klasa stworzona dla nieobsługiwanych działań w klasie Math, lub aby ułatwić obsługę błędów przez program
    // dla działań obsługiwanych, aby ujednolicić kod.

    public static double sqrt(double liczba) throws MyError{
        if(liczba <0){
            // Aby był wyrzucany błąd MyError nie dostępny z pakietu Math.
            throw new MyError("Nie istnieją rzeczywiste pierwiastki liczb ujemnych");
        }else{
            return Math.log(liczba);
        }
    }

    public static double ln(double liczba) throws MyError{
        if(liczba <0){
            // Aby był wyrzucany błąd MyError nie dostępny z pakietu Math.
            throw new MyError("Nie istnieją rzeczywiste logarytmy liczb ujemnych");
        }else{
            return Math.log(liczba);
        }
    }

    public static double log(double liczba) throws MyError{
        if(liczba <0){
            // Aby był wyrzucany błąd MyError nie dostępny z pakietu Math.
            throw new MyError("Nie istnieją rzeczywiste logarytmy liczb ujemnych");
        }else{
            return Math.log10(liczba);
        }
    }

    public static int fact(double liczba) throws MyError{
        if(liczba == (int)liczba){
            if(liczba>=0){
                if(liczba == 0){
                    // Silna 0 jest równa 1

                    return 1;
                }else{
                    // Wykonanie algorytmu silnej.

                    int wynik = 1;

                    for(int n=1;n<=liczba;n++){
                        wynik = wynik * n;
                    }

                    return wynik;
                }
            }else{
                // Gdy liczba nie spełnia warunków.
                throw new MyError("Nie można uzyskać silnej liczby nienaturalnej");
            }
        }else{
            throw new MyError("Nie można uzyskać silnej liczby nienaturalnej");
        }

    }
}
