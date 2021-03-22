#!/usr/bin/python

class monedero:
	dinero = None

	def __init__(self):
		# completa el código
		self.dinero=0
	
	def addMoneda(self,monedas=1):
		# completa el código
		self.dinero += int(monedas)
	
	def getDinero(self,monedas=1):
		# completa el código
		return self.dinero
