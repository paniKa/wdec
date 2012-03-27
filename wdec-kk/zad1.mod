param a{1..6, 1..3};
param b{1..6};

var x {j in 1..3} >= 0;
minimize f_celu: x[3];
subject to ogr1 {i in 1..6}: sum {j in 1..3} x[j]*a[i,j] >= b[i];
