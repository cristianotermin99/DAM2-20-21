#Aplicacion que pedirá por teclado un
#listado de numeros hasta que se pulsa un caracter. 
#Entonces muestra el siguiente menu

	#1.Suma 
	#2.Ordenacion del listado
	#3.Introducir un nuevo listado
	#4.Salir del programa

#Mostrando a continuación el resultado y permitiendo
#al usuario realizar una nueva acción sobre el listado 
#original introducido


#1.Suma 
def suma(lista):
	suma=0
	for item in lista:
		suma+=item
	return(suma)


#2.Ordenacion del listado
def ordenar(lista):
	lista.sort()
	return (lista)


#3.Introducir un nuevo listado
def nuevoListado():
	mensaje=input("Introduce un nuevo listado\n")
	lista2=[]
	try:
		while (True):
			num = int(mensaje)
			lista2.append(num)
			mensaje=input("Introduce tu siguiente número\n")
	
	except ValueError:
		print("Se ha añadido correctamente tu lista\n")
	
	return(lista2)

mensaje=input("Introduce numeros, para parar de introducir numeros, introduce un caracter\n")
lista=[]
try:
	while (True):
		num = int(mensaje)
		lista.append(num)
		mensaje=input("Introduce tu siguiente número\n")

except ValueError:
	print("Se ha añadido correctamente tu lista\n")


salir=False
while (salir==False):
	print("1. Suma\n")
	print("2. Ordenacion del listado\n")
	print("3. Introducir un nuevo listado\n")
	print("4. Salir del programa\n")
	opcion=input("Introduce un numero del 1 al 4: ")
	if opcion == "1":
		print("La suma de los numero de la lista es: " ,suma(lista),"\n")
	if opcion == "2":
  		print(ordenar(lista))
  		print("La lista ordenada: " ,ordenar(lista),"\n")
	if opcion == "3":
  		lista=nuevoListado()
	if opcion == "4":
  		salir=True






		
	