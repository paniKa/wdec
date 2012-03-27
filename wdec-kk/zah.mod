# liczba zada� w przedsi�wzi�ciu
set n;

# liczba prockow w przedsi�wzi�ciu
set m;

# nominalny czas trwania zada�
param p{j in n};


# --- zmienne -----------------------------------------------------------------

# czy zadanie jest wykonywane na danym procesorze
var v{l in m,j in n} binary;

#ilosc czasu na l-tym procesorze
var t{l in m,j in n} >= 0;


#czasy trwania planow elementarnych
var y{i in n} >= 0;

# czas ca�kowity przedsi�wzi�cia
var k >= 0;

# --- funkcja celu ------------------------------------------------------------

minimize czasCalkowity:
			k;

# --- ograniczenia ------------------------------------------------------------

subject to czasTrwania:
	k = sum {i in n}y[i];

subject to caleZadanie{l in m}:
	sum{j in n} v[l,j] <= 1;

subject to naJednymProc{j in n}:
	sum{l in m} v[l,j] <= 1;
	
subject to minCzasTrwaniaZadan{j in n}:
	sum{l in m} t[l,j]/p[j] = 1;

