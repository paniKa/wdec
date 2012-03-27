param r;                 
param gotowka;
param max_kredyt;
param max_produkcja;
param k_staly;
param amortyzacja;
param zadluzenie;

var cena >=0, <=30;
var jakosc >=0;
var wolumen >=0;

var r_tv >=0;
var r_net >=0;
var r_magazyn >=0;

var kredyt >=0;

var k_jedn_zm >=0;
var ryzyko >=0;
var zysk >=0;
var caly_koszt >=0;
                                                     
maximize fun: zysk;

subject to ogr1: ryzyko = r;
subject to ogr2: caly_koszt <= gotowka+kredyt;
subject to ogr3: jakosc <= 100;
subject to ogr10: jakosc >= 10;
subject to ogr4: wolumen <= max_produkcja;
subject to ogr5: kredyt <= max_kredyt;
subject to ogr6: cena >= k_jedn_zm;

subject to ogr7: caly_koszt = r_tv+r_net+r_magazyn+k_jedn_zm*wolumen + amortyzacja + k_staly + zadluzenie;

#subject to ogr7: caly_koszt = r_tv+r_net+r_magazyn+k_jedn_zm*wolumen + amortyzacja + k_staly;

subject to ogr8: ryzyko = (-0.01 * ((-cena) - 55 / 2) * ((-cena) - 55 / 2) - ((-cena) - 5) * (100 / 45)) / 50 - (0.8 * r_tv + 0.7 * r_magazyn + 0.5 *r_net) / 200000 ;

subject to ogr9: zysk = cena*wolumen-caly_koszt+kredyt +gotowka;

subject to ogr12: k_jedn_zm = 0.15827136*jakosc-0.8322906 + 7.9067*10^-6*wolumen+7-188042*7.9067*10^-6;
