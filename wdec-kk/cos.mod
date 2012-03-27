#plik model .mod
param n;

var x{1..4};
param u{1..4};
param l{1..4};

minimize f_celu:
	3*x[1] + 10^(-6) * x[1]^3 + 2*x[2] + ((2*10^(-6))/3)*x[2]^3;

#ograniczenia >=
subject to ogr_mn1: 
	x[4] - x[3] + 0.55 >= 0;
	
subject to ogr_mn2: 
	-x[4] + x[3] + 0.55 >= 0;

#ograniczenia rownosciowe
subject to ogr_row1: 
	1000*sin(-x[3] - 0.25) + 1000*sin(-x[4] - 0.25) + 894.8 - x[1]  = 0;

subject to ogr_row2: 
	1000*sin(x[3] - 0.25) + 1000*sin(x[3] - x[4] - 0.25) + 894.8 - x[2] = 0;

subject to ogr_row3: 
	1000*sin(x[4] - 0.25) + 1000*sin(x[4] - x[3] - 0.25) + 1294.8 = 0;	
	
#ograniczenia gorne i dolne
subject to ograniczenie {i in 1..4}: 
	x[i]<=u[i];

subject to ograniczenie2 {i in 1..4}: 
	x[i]>=l[i];


