#!/usr/bin/python

from monedero import monedero


cartera = monedero()
cartera.addMoneda()
print(str(cartera.getDinero())+"€")
cartera.addMoneda()
print(str(cartera.getDinero())+"€")