param r;                 
param gotowka;
param max_kredyt;
param max_produkcja;
param k_staly;
param amortyzacja;
param k_jedn_zm;

var cena >=0;
var jakosc >=0;
var wolumen >=0;
var kredyt >=0;
var r_tv >=0;
var r_net >=0;
var r_magazyn >=0;

var ryzyko ;
var zysk >=0;
var caly_koszt >=0;
                                                     
maximize fun: zysk;

subject to ogr1: ryzyko <= r;
subject to ogr2: caly_koszt <= gotowka+kredyt;
subject to ogr3: jakosc <= 100;
subject to ogr10: jakosc >= 1;
subject to ogr4: wolumen <= max_produkcja;
subject to ogr5: kredyt <= max_kredyt;
subject to ogr6: cena >= k_jedn_zm;
#subject to ogr12: cena <= 25;

subject to ogr7: caly_koszt = r_tv+r_net+r_magazyn+k_jedn_zm*wolumen+k_staly+amortyzacja;

subject to ogr8: ryzyko = (wolumen/max_produkcja)*0.15*(cena/jakosc) - (0.9*r_tv+ 0.7*r_magazyn+0.4*r_net)/200000;

subject to ogr9: zysk = cena*wolumen-caly_koszt;
