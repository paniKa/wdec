param A{1..3, 1..3};
param b{1..3};
param C{1..3};
param a{1..2};
param r{1..2};
param Epsilon;
param Beta;
param Gamma;

var z{1..2};
var zz;
var y{1..2};
var x{1..3, 1..3};

maximize f_celu: zz + Epsilon*sum{i in 1..2} z[i];

subject to o{i in 1..2}: zz <= z[i];
subject to o1{i in 1..2}: z[i] <= Beta*((y[i]-a[i])/(a[i]-r[i]))+1;
subject to o2{i in 1..2}: z[i] <= ((y[i]-a[i])/(a[i]-r[i]));
subject to o3{i in 1..2}: z[i] <= Gamma*((y[i]-a[i])/(a[i]-r[i]));

subject to o4: y[1] >= sum{i in 1..3} A[1,i]*x[1,i];
subject to o5: y[1] >= sum{i in 1..3} A[2,i]*x[2,i];
subject to o6: y[1] >= sum{i in 1..3} A[3,i]*x[3,i];

subject to w: -y[2] <= -sum{i in 1..3, j in 1..3}C[i]*x[i,j];

subject to sztuki{j in 1..3}: sum{i in 1..3} x[i,j] = b[j];

subject to o7{i in 1..3, j in 1..3}: x[j,i] >=0;
