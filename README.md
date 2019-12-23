# dds-2018-G17-Mie-TN
Integrantes: PATRONELLI Nicolas

INSTRUCCIONES PARA CORRER TEST UNITARIOS CON MYSQL 

1. Abrir el archivo persistence.xml ubicado en SGE\src\main\resources\META-INF\
2. Comentar las etiquetas property de la base de datos HSQLDialect (base de datos en memoria) y descomentar las referidas a MYSQL (se encuentran inmediatamente abajo). Puede hacerse rápidamente desde un IDE con la opción de Add Block Comment/Remove Block Comment.
3. Conectarse a una instancia del motor de base de datos MYSQL con el usuario root y contraseña 1880*. Se recomienda utilizar el cliente gráfico MYSQL Workbench para ello. Asegurarse que el servidor este corriendo.  
4. Crear una base de datos llamada sgedb. Esto puede hacerse ejecutando la instrucción CREATE DATABASE sgedb.
5. Luego de correr los tests se debe ejecutar la instrucción USE sgedb para poder hacer consultas a las tablas.

* Nota: Si se desea utilizar otro usuario y contraseña se pueden alterar las siguientes properties en el archivo persistance.xml ya mencionado: 

			<property name="hibernate.connection.username" value="usuario" />
			<property name="hibernate.connection.password" value="contraseña" />



