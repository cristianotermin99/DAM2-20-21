# ejemplo 1

Crea un programa que cuente el numero de ocurrencias de un numero dentro de una lista

![ejemplo](python-find-and-count.png "Ejemplo")


# ejemplo 2
Copia y pega el siguiente codigo en una aplicación

<pre>
	import sys

	if(len(sys.argv) > 2):
	    print "El nombre del programa es " + sys.argv[0]
	    print "El primer parámetro es " + sys.argv[1]
	    print "El segundo parámetro es " + sys.argv[2]
	else:
	    print "Necesario ejecutar con al menos dos parámetros"
	    print "python programa.py param1 param2"

</pre>

# ejemplo 3
crea un proigrama llamado suma-numeros.py al que le llamemos desde la terminal de la siguiente manera
<pre>
     python suma-numeros.py 4 5

</pre>

y nos muestre por pantalla

<pre>
	9

</pre>


# ejemplo 4
Crea un programa que llame al programa del ejemplo 3 pidiendo al usuario por pantalla que introduzca dos numetros enteros

<pre>
	from subprocess import call

</pre>

# ejemplo 5
Crea un programa llamado monedero con una variable interna llamada dinero, inicializada a 0.

<pre>
	#!/usr/bin/python

	class monedero:
		dinero = None

		def __int__(self):
			# completa el código
		def addMoneda(self):
			# completa el código
		def getDinero(self):
			# completa el código

</pre>

Ahora debes crearte el fichero ejercicio-5.py donde debes pegar el siguiente código:

<pre>
	#!/usr/bin/python

	from monedero import monedero


	cartera = monedero()
	cartera.addMoneda()
	print(cartera.getDinero())
	cartera.addMoneda()
	print(cartera.getDinero())

</pre>

La salida por la terminal deberá ser la siguiente
<pre>
	1€
	2€

</pre>

Para lograr mostrar el simbolo del Euro(€) hazlo usando la información del siguiente enlace

[how-can-i-print-a-euro-€-symbol-in-python](https://stackoverflow.com/questions/39935857/how-can-i-print-a-euro-%E2%82%AC-symbol-in-python)