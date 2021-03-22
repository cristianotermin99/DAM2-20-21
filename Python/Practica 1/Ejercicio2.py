import sys

if(len(sys.argv) > 2):
	print ("El nombre del programa es " + sys.argv[0])
	print ("El primer parámetro es " + sys.argv[1])
	print ("El segundo parámetro es " + sys.argv[2])
else:
	print ("Necesario ejecutar con al menos dos parámetros")
	print ("python programa.py param1 param2")

