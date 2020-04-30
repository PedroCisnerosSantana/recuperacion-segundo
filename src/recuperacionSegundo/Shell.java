package recuperacionSegundo;

import java.io.PrintStream;
import java.util.Scanner;

public class Shell {
	private Scanner s;
	private PrintStream salida;
	private CatalogoComercial catalogoComercial = new CatalogoComercial();
	private CatalogoUsuario catalogoUsuario = new CatalogoUsuario();
	
	// funcion para imprimir la lista de comandos
	public void help() {
		println("Comandos disponibles:");
		println("listar (comerciales/cliente) - Lista los comerciales o clientes");
		println("alta (comercial/cliente) - Crea un nuevo comercial o cliente");
		println("borrar (comercial/cliente) - Borra un comercial o cliente de la lista");
		println("modifica (comercial/cliente) - Modifica los atributos de un comercial o cliente");
		println("compra [nombre comercial] [nombre producto] [cantidad] - Compra el producto al comercial indicado");
		println("salir - Sale del programa");
	}
	
	public Shell(Scanner s, PrintStream salida) {
		this.s = s;
		this.salida = salida;
	}
	
	// funcion para acortar sout con salto de linea
	private void println(Object o) {
		salida.println(o);
	}
	
	// funcion para acortar sout sin salto de linea
	private void print(Object o) {
		salida.print(o);
	}
	
	// Llama a la funcion scanLine para separar comandos
	private String[] scan(String prompt) {
		return scanLine(prompt).split(" ");
	}
	
	// Lee toda la linea de la entrada
	private String scanLine(String prompt) {
		print(prompt);
		return s.nextLine();
	}

	// funcion de alta principal, se ramifica en dos segun el primer parametro
	private void alta(String[] comando) {
		if (!comprobarArg1(comando)) {
			println("Debes pasar como parametro \"comercial\" o \"cliente\"");
			return;
		}
		if (comando[1].equals("comercial")) {
			altaComercial(comando);
		} else {
			altaCliente(comando);
		}
	}
	
	// funciona alta cliente
	private void altaCliente(String[] comando) {
		if (comando.length == 2) {
			// Modo interactivo
			String nombreCliente = scanLine("Nombre del cliente: ");
			String correoCliente = scanLine("Correo del cliente: ");
			String tlfCliente = scanLine("Telefono del cliente: ");
			catalogoUsuario.put(new Usuario(nombreCliente, correoCliente, tlfCliente));
		} else if (comando.length == 5) {
			// Modo automatico
			catalogoUsuario.put(new Usuario(comando[2], comando[3], comando[4]));
		}
		
	}

	// funcion alta comercial
	private void altaComercial(String[] comando) {
		if (comando.length == 2) {
			// Modo interactivo
			String nombreComercial = scanLine("Nombre del comercial: ");
			String correoComercial = scanLine("Correo del comercial: ");
			String puestoComercial = scanLine("Puesto del comercial: ");
			catalogoComercial.put(new Comercial(nombreComercial, correoComercial, puestoComercial));
		} else if (comando.length == 5) {
			// Modo automatico
			catalogoComercial.put(new Comercial(comando[2], comando[3], comando[4]));
		}
	}
	
	// funcion de lista, se ramifica en dos segundo el primer parametro
	private void listar(String[] comando) {
		if (!comprobarArg1(comando)) {
			println("Debes pasar como parametro \"comercial\" o \"cliente\"");
			return;
		}
		if (comando[1].equals("comercial")) {
			listarComercial(comando);
		} else {
			listarCliente(comando);
		}
	}
	
	// funcion lista cliente
	private void listarCliente(String[] comando) {
		for (String correo : catalogoUsuario.correos()) {
			println(catalogoUsuario.get(correo));
		}
		
	}

	// funcion lista comercial
	private void listarComercial(String[] comando) {
		for (String correo : catalogoComercial.correos()) {
			println(catalogoComercial.get(correo));
		}
	}
	
	// Funcion principal de borrado
	private void borrar(String[] comando) {
		if (!comprobarArg1(comando)) {
			println("Debes pasar como parametro \"comercial\" o \"cliente\"");
			return;
		}
		if (comando[1].equals("comercial")) {
			borrarComercial(comando);
		} else {
			borrarCliente(comando);
		}
	}
	
	// Borra el cliente, usando el correo como PK
	private void borrarCliente(String[] comando) {
		if (comando.length == 2) {
			// Modo interactivo
			String correoCliente = scanLine("Correo del cliente: ");
			catalogoUsuario.del(correoCliente);
		} else if (comando.length == 3) {
			// Modo automatico
			catalogoUsuario.del(comando[2]);
		}
		
	}

	// Borra el comercial, usando el correo como PK
	private void borrarComercial(String[] comando) {
		if (comando.length == 2) {
			// Modo interactivo
			String correoComercial = scanLine("Correo del comercial: ");
			catalogoComercial.del(correoComercial);
		} else if (comando.length == 3) {
			// Modo automatico
			catalogoComercial.del(comando[2]);
		}
	}

	// funcion de modificacion, se ramifica en dos segundo el primer parametro 
	private void modificar(String[] comando) {
		if (!comprobarArg1(comando)) {
			println("Debes pasar como parametro \"comercial\" o \"cliente\"");
			return;
		}
		if (comando[1].equals("comercial")) {
			modificarComercial(comando);
		} else {
			modificarCliente(comando);
		}
	}
	
	// funcion modificacion cliente
	private void modificarCliente(String[] comando) {
		if (comando.length == 2) {
			// Modo interactivo
			String correoCliente = scanLine("Correo del cliente: ");
			String nombreCampo = scanLine("Nombre del campo a modificar: ");
			String valorCampo = scanLine("Nuevo valor del campo: ");
			Usuario usuario = catalogoUsuario.get(correoCliente);
			if (usuario == null) {
				println("No existe ese comercial");
				return;
			}
			switch (nombreCampo.toLowerCase()) {
			case "nombre":
				usuario.setNombre(valorCampo);
				break;
			case "telefono":
				usuario.setTelf(valorCampo);
				break;
			case "correo":
				usuario.setCorreo(valorCampo);
				catalogoUsuario.del(correoCliente);
				catalogoUsuario.put(usuario);
				break;
			default:
				break;
			}
		} else if (comando.length == 5) {
			// Modo automatico
			Usuario usuario = catalogoUsuario.get(comando[2]);
			if (usuario == null) {
				return;
			}
			switch (comando[3].toLowerCase()) {
			case "nombre":
				usuario.setNombre(comando[4]);
				break;
			case "telefono":
				usuario.setTelf(comando[4]);
				break;
			case "correo":
				usuario.setCorreo(comando[4]);
				catalogoUsuario.del(comando[2]);
				catalogoUsuario.put(usuario);
				break;
			default:
				break;
			}
		}
		
	}

	// funcion moficiacion comercial
	private void modificarComercial(String[] comando) {
		if (comando.length == 2) {
			// Modo interactivo
			String correoComercial = scanLine("Correo del comercial: ");
			String nombreCampo = scanLine("Nombre del campo a modificar: ");
			String valorCampo = scanLine("Nuevo valor del campo: ");
			Comercial comercial = catalogoComercial.get(correoComercial);
			if (comercial == null) {
				println("No existe ese comercial");
				return;
			}
			switch (nombreCampo.toLowerCase()) {
			case "nombre":
				comercial.setNombre(valorCampo);
				break;
			case "cargo":
				comercial.setPuesto(valorCampo);
				break;
			case "correo":
				comercial.setCorreo(valorCampo);
				catalogoComercial.del(correoComercial);
				catalogoComercial.put(comercial);
				break;
			default:
				break;
			}
		} else if (comando.length == 5) {
			// Modo automatico
			Comercial comercial = catalogoComercial.get(comando[2]);
			if (comercial == null) {
				return;
			}
			switch (comando[3].toLowerCase()) {
			case "nombre":
				comercial.setNombre(comando[4]);
				break;
			case "cargo":
				comercial.setPuesto(comando[4]);
				break;
			case "correo":
				comercial.setCorreo(comando[4]);
				catalogoComercial.del(comando[2]);
				catalogoComercial.put(comercial);
				break;
			default:
				break;
			}
		}
	}
	
	// funcion modificacion cliente
 	private boolean comprobarArg1(String[] comando) {
		if (comando.length < 2) {
			return false;
		}
		return comando[1].equals("comercial") || comando[1].equals("cliente");
	}
	
 	private void compra(String[] comando) {
 		if (comando.length < 1) {
 			return;
 		}
 		if (comando.length == 1) {
 			String correoComercial = scanLine("Introduce el correo del comercial: ");
 			if (catalogoComercial.get(correoComercial) == null) {
 				println("No existe ese comercial");
 				return;
 			}
 			String nombreProducto = scanLine("Introduce el nombre del producto: ");
 			String cantidad= scanLine("Introduce la cantidad a comprar: ");
 			int cantidadProducto = Integer.parseInt(cantidad);
 			Articulo art = new Articulo(nombreProducto, cantidadProducto);
 			Comercial comercial = catalogoComercial.get(correoComercial);
 			comercial.addSale(art);
 		}
 		
 	}
 	
 	// funcion para añadir datos al inicio
 	private void precarga() {
 		catalogoUsuario.put(new Usuario("Pedro", "pedro@localhost.com", "666-666-666"));
 		catalogoComercial.put(new Comercial("Luis", "luis@ventaonline.com", "coordinador de ventas"));
 	}
 	
 	// funcion principal
	public void ejectuar() {
		precarga();
		help();
		while (true) {
			String[] comando = scan("bash 4.2# ");
			switch (comando[0]) {
			case "salir":
				return;
			case "alta":
				alta(comando);
				break;
			case "listar":
				listar(comando);
				break;
			case "borrar":
				borrar(comando);
				break;
			case "modifica":
				modificar(comando);
				break;
			case "compra":
				compra(comando);
				break;

			default:
				println("Comando no reconocido\n");
				help();
			}
		}
	}

}
