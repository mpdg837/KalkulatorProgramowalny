package KalkulatorPRMT.Obliczanie.Warunki;

public class Skok {

    private String ciag;

    public Skok(String ciag){
        this.ciag = ciag;
    }

    public String analizuj(){
        char[] znaki = ciag.toCharArray();

        StringBuilder build = new StringBuilder();
        StringBuilder etykieta = new StringBuilder();
        StringBuilder command = new StringBuilder();

        int poziomzag = 0;

        boolean waruneko= false;

        for(char c : znaki){

            switch (c+""){
                case "("->{
                    poziomzag++;
                    if(poziomzag!=1) {
                        build.append(c);
                    }
                }
                case ")"->{
                    poziomzag--;
                    if(poziomzag>0){

                        build.append(c);
                    }
                }
                default -> {
                    if(poziomzag>0){
                        build.append(c);
                    }
                }
            }
        }

        return build.toString();
    }
}
