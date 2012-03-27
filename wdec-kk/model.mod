#plik model .mod
param n;

var x{1..4};

param A{1..2,1..4};
param b{1..2};
param beq{1..3};
param u{1..4};
param l{1..4};

minimize f_celu:
	3*x[1] + 10^(-6) * x[1]^3 + 2*x[2] + ((2*10^(-6))/3)*x[2]^3;

#ograniczenia >=
subject to ogr_mn {i in 1..2}: 
	sum{j in 1..4} A[i, j]*x[j] >= b[i];

#ograniczenia rownosciowe
subject to ogr_row1: 
	1000*sin(-x[3] - 0.25) + 1000*sin(-x[4] - 0.25) - x[1]  = beq[1];

subject to ogr_row2: 
	1000*sin(x[3] - 0.25) + 1000*sin(x[3] - x[4] - 0.25) - x[2] = beq[2];

subject to ogr_row3: 
	1000*sin(x[4] - 0.25) + 1000*sin(x[4] - x[3] - 0.25) = beq[3];	
	
#ograniczenia gorne i dolne
subject to ograniczenie {i in 1..4}: 
	x[i]<=u[i];

subject to ograniczenie2 {i in 1..4}: 
	x[i]>=l[i];

