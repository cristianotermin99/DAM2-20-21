from subprocess import call
num1=input("introduce el primer numero: ")
num2=input("introduce el segundo numero: ")

call(["python","suma-numeros.py",num1,num2],shell=1)