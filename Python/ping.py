import os
import subprocess, platform


def p


direccionesValidas=[]
for x in range(0,255):
	for y in range(0,255):
		for z in range(0,255):
			response = os.system("ping -n 1 " + "172."+str(x)+"."+str(y)+"."+str(z))
			if response == 0:
				direccionesValidas.append("172."+str(x)+"."+str(y)+"."+str(z))

print(direccionesValidas)