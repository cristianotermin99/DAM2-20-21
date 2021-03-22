import sys
if(len(sys.argv) > 2):
	suma=int(sys.argv[1])+int(sys.argv[2])
	 
	print (suma)
else:
	print ("Necesario ejecutar con al menos dos parámetros")
	print ("python programa.py param1 param2")
