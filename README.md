# KalkulatorProgramowalny
Projekt w ramach zaliczenia programowania. Kalkulator z obsługą funkcji trygonometrycznych zmiennych, pewnych stałych, instrukcji skryptowych itd. Daje to duże możliwości mimo niepozornej budowy. 
Program wspiera dodatkowo język skryptowy, wzorując się na starych kalkulatorach programowalnych z lat 90. W zamyśle kalkulator ten ma służyć do obliczeń w któych zwykly windowsowy
kalkulator jest narzędziem zbyt niewygodnym a arkusz kalkulacyjny zbyt wyrafinowanym przystosowany do przetwarzania dużej ilości danych, zaś gdy my potrzebujemy przykładowo 
podstawić pod wzór policzyć całkę itd., co jest wygodniejsze z wykorzystaniem prostego skryptu.

# Instrukcje języka skryptowego kalkulatora.

Zestaw komend do działań:

ln(zawartość) - wyciąga logarytm naturalny zawartości nawiasu

sin(zawartość) - wyciąga sinus zawartości nawiasu

cos(zawartość) - wyciąga cosinus zawartości nawiasu

tan(zawartość) - wyciąga tangens zawartości nawiasu

exp(zawartość) - wyciąga funkcje exponens zawartości nawiasu

log(zawartość) - wyciąga logarytm dziesiętny zawartości nawiasu

abs(zawartość) - wyciąga wartość bezwzględną nawiasu


sqrt(zawartość) - wyciąga pierwiastek kwadratowy zawartości nawiasu

pow2(zawartość),pow(zawartość) - wyciąga kwadrat zawartości nawiasu

pow3(zawartość) - wyciąga sześcian zawartości nawiasu

fact(zawartość) - wyciąga silnie zawartości nawiasu


if(instrukcje warunkowe) - goto (etykieta) instrukcja warunkowa odnosząca do etykiety

dim nazwa[rozmiar] - tworzenie tablicy o rozmiarze rozmiar

new - czyszczenie pamięci

goto(etykieta) - skok do etykiety

gosub(etykieta) - skok do etykiety z możliwością powrotu

:etykieta - deklarowanie etykiety

return - powrót z powrotem do miejsca ostatniego gosub()


nazwa_zmiennej = wartość - przypisanie wartości zmiennej


nazwa_tablicy[numer_indeksu] - pobranie komórki tablicy

len(nazwa_tablicy) - pobranie długości tablicy


// - komentarz

show(komunikat) - pokazanie komunikatu

inpu(komunikat) - pobranie wartości liczbowej wyświetlając komunikat

# Przykładowy skrypt

Poniżej znajduje się przykładowy skrypt tworzący szablon do obliczania funkcji kwadratowej:

<i>
a = inpu('a')

b = inpu('b')

c = inpu('c')

delta = pow2(b)-4*a*c

if(delta < 0) goto(err)

pdelta = sqrt(delta)

dim x[2]

x[0] = (-b+pdelta)/(2*a)

x[1] = (-b-pdelta)/(2*a)

if(x[0]=x[1])goto(one)

show('x1 '+x[0]+',x2 '+x[1])

goto(end)

:one

show('x '+x[0])

goto(end)

:err

show('Ujemna delta')

:end

</i>
