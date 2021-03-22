#name = input("Cual es tu nombre?")
#print(name)

#lista
#lista1 = ["juan",10,true,["nike","won"],"alberto","kjdkjsdc"]
#variableBoolean=bool(lista1[2])
#print(type(lista1))

#print(dir(type(lista1)))
#lista1.push(5.0)
#print(lista)


#lista1.append(5.0) #introduce por el final
#lista1.insert(0,False) #introduce en una posicion especifica
#print(lista1.count(False)) #devuelve numero de ocurrencias
#print(lista1.pop()) #extrae el ultimo elemento
#print(lista1[4].sort())
#print(lista1[0])



#tuplas
#tupla = (10,None,False,"hola")
#print(tupla)
#print(type(tupla))
#print(dir(tupla))
#print(tupla.index(0))
#lista2=list(tupla)
#tupla2=tuple(lista2)
#print(tupla2)

#for item in lista2:
#	print(item," es un ",type(item))


#sumatorio de un numero
def getData():
	numero = int(input("introduce un numero: "))
	return numero

def calculateSumatory(data):
		sumatorio=0
		for cont in range(data+1):
			sumatorio+=cont
		return [data,sumatorio]

def printResult(numero,sumatorio):
	print("Sumatorio de: ",numero," es ",sumatorio)

data,results=calculateSumatory(getData())
printResult(data,results)
