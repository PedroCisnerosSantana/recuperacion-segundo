package recuperacionSegundo;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Ex04pcs3 {

	static ArrayList<GestisimalArt> art = new ArrayList<GestisimalArt>();
	private static Scanner s = new Scanner(System.in);
	private static PrintStream salida = System.out;

	@SuppressWarnings("unused")
	private String[] scan(String prompt) {
		return scanLine(prompt).split(" ");
	}

	private static String scanLine(String prompt) {
		System.out.print(prompt);
		return s.nextLine();
	}

	private static void println(Object o) {
		salida.println(o);
	}

	private static void print(Object o) {
		salida.println(o);
	}

	static public int posicion(String codigo) {
		int i = -1;
		for (GestisimalArt aux : art) {
			i++;
			if (aux.getCode().equals(codigo)) {
				return i;
			}
		}
		return -1;
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	public static void main(String[] args) {
		HashMap<String, Integer> lineasFra = new HashMap<String, Integer>();

		int opcion;
		int opcion2;
		int i;
		int stockIntro;
		int unidades = 0;
		int unidadesEnFactura = 0;
		double precioDeCompraIntro;
		double precioDeVentaIntro;
		double subtotal;
		double baseImponible;
		double totalFactura;
		String codigo;
		String codigoIntro = "";
		String descripcionIntro;
		String precioDeCompraIntroString;
		String precioDeVentaIntroString;
		String stockIntroString;

		// Menu ////////////////////////////////////////////////////////////
		do {
			println("G E S T I S I M A L");
			println("===================");
			println("1. Listado");
			println("2. Alta");
			println("3. Baja");
			println("4. Modificación");
			println("5. Entrada de mercancía");
			println("6. Venta");
			println("7. Salir");
			opcion = Integer.parseInt(scanLine("Introduzca una opción: "));

			switch (opcion) {

			case 1: // Listado
				println("\nLISTADO\n=======");

				for (GestisimalArt aux : art) {
					println(aux);
				}
				break;

			case 2: // Alta
				System.out.println("\nNUEVO ARTÍCULO\n==============");

				System.out.println("Por favor, introduzca los datos del artículo.");
				System.out.print("Código: ");

				do {
					codigoIntro = s.nextLine();
					if (posicion(codigoIntro) != -1) {
						print("Ya existe ese codigo\nIntroduce otro codigo: ");
					}
				} while (posicion(codigoIntro) != -1);

				descripcionIntro = scanLine("Descripcion: ");

				precioDeCompraIntro = Double.parseDouble(scanLine("Precio de compra: "));

				precioDeVentaIntro = Double.parseDouble(scanLine("Precio de venta: "));

				stockIntro = Integer.parseInt(scanLine("Stock: "));

				art.add(new GestisimalArt(codigoIntro, descripcionIntro, precioDeCompraIntro, precioDeVentaIntro,
						stockIntro));

				break;

			case 3: // Baja 
				println("\nBAJA\n====");
				codigoIntro = scanLine("Por favor, introduzca el código del artículo que desea dar de baja: ");

				if (posicion(codigoIntro) == -1) {
					println("Lo siento, el código introducido no existe.");
				} else {
					art.remove(posicion(codigoIntro));
					println("articulo borrado.");
				}

				break;

			case 4: // Modificación
				println("\nMODIFICACIÓN\n============");
				print("Por favor, introduzca el código del artículo cuyos datos desea cambiar: ");

				do {
					codigoIntro = s.nextLine();
					if (posicion(codigoIntro) != -1) {
						println("No hay ningún artículo con ese código.\nIntroduzca otro código: ");
					}
				} while (posicion(codigoIntro) == -1);

				i = posicion(codigoIntro);

				println("Introduzca los nuevos datos del artículo o INTRO para dejarlos igual.");

				println("Código: " + art.get(i).getCode());
				codigoIntro = scanLine("Nuevo código: ");
				if (!codigoIntro.equals("")) {
					art.get(i).setCode(codigoIntro);
				}

				println("Descripción: " + art.get(i).getDesc());
				descripcionIntro = scanLine("Nueva descripción: ");
				if (!descripcionIntro.equals("")) {
					art.get(i).setDesc(descripcionIntro);
				}

				println("Precio de compra: " + art.get(i).getBuyPrice());
				precioDeCompraIntroString = scanLine("Nuevo precio de compra: ");
				if (!precioDeCompraIntroString.equals("")) {
					art.get(i).setBuyPrice(Double.parseDouble(precioDeCompraIntroString));
				}

				println("Precio de venta: " + art.get(i).getSellPrice());
				precioDeVentaIntroString = scanLine("Nuevo precio de venta: ");
				if (!precioDeVentaIntroString.equals("")) {
					art.get(i).setSellPrice(Double.parseDouble(precioDeVentaIntroString));
				}

				println("Stock: " + art.get(i).getStock());
				stockIntroString = scanLine("Nuevo stock: ");
				if (!stockIntroString.equals("")) {
					art.get(i).setStock(Integer.parseInt(stockIntroString));
				}

				break;

			case 5: // Entrada de mercancía
				println("\nENTRADA DE MERCANCÍA\n====================");
				codigoIntro = scanLine("Por favor, introduzca el código del artículo: ");

				do {
					codigoIntro = s.nextLine();
					if (posicion(codigoIntro) != -1) {
						println("No hay ningún artículo con ese código.\nIntroduzca otro código: ");
					}
				} while (posicion(codigoIntro) == -1);

				i = posicion(codigoIntro);

				println("Entrada de mercancía del siguiente artículo: " + art.get(i));
				stockIntro = Integer.parseInt(scanLine("Introduzca el número de unidades que entran al almacén: "));
				art.get(i).setStock(stockIntro + art.get(i).getStock());
				println("La mercancía ha entrado en el almacén.");

				break;

			case 6: // Venta
				println("\nVENTA\n=====");

				do {
					println("\n1. Añadir artículo");
					println("2. Generar factura");
					println("3. Cancelar");
					opcion2 = Integer.parseInt(scanLine("Introduzca una opción: "));

					switch (opcion2) {

					case 1: // Añadir art 
						codigoIntro = scanLine("Por favor, introduzca el código del artículo: ");
						i = posicion(codigoIntro);

						if (i == -1) {
							println("No hay ningún artículo con ese código.");
						} else {
							println(art.get(i));

							if (lineasFra.containsKey(codigoIntro)) {
								unidadesEnFactura = lineasFra.get(codigoIntro);
							} else {
								unidadesEnFactura = 0;
							}

							println("Unidades en la factura provisional: " + unidadesEnFactura);

							unidades = Integer.parseInt(scanLine("Unidades que quiere incorporar a la factura: "));

							if ((art.get(i).getStock()) - unidadesEnFactura < unidades) {
								println("No hay suficiente stock. Puede añadir a la venta un máximo de "
										+ (art.get(i).getStock() - unidadesEnFactura) + " unidades de ese producto.");
							} else if (lineasFra.containsKey(codigoIntro)) {
								lineasFra.put(codigoIntro, lineasFra.get(codigoIntro) + unidades);
							} else {
								lineasFra.put(codigoIntro, unidades);
							}
						}

						// Muestra los articulos
						println("\n\n CÓDIGO |    DESCRIPCIÓN    | UNIDADES | PRECIO UNID. | SUBTOTAL");
						println("------------------------------------------------------------------");
						for (Map.Entry pareja : lineasFra.entrySet()) {
							codigo = pareja.getKey().toString();
							i = posicion(codigo);
							unidades = Integer.parseInt(pareja.getValue().toString());
							subtotal = unidades * art.get(i).getSellPrice();
							System.out.printf(" %6s | %17s | %8d | %12.2f | %8.2f\n", codigo, art.get(i).getDesc(),
									unidades, art.get(i).getSellPrice(), subtotal);
						}

						break;

					case 2: // Genera la factura 
						baseImponible = 0;
						println("\n\n CÓDIGO |   DESCRIPCIÓN   | UNIDADES | PRECIO UNID. | SUBTOTAL");
						println("----------------------------------------------------------------");
						for (Map.Entry pareja : lineasFra.entrySet()) {
							codigo = pareja.getKey().toString();
							i = posicion(codigo);
							unidades = Integer.parseInt(pareja.getValue().toString());
							subtotal = unidades * art.get(i).getSellPrice();
							System.out.printf(" %6s | %15s | %8d | %12.2f | %8.2f\n", codigo, art.get(i).getDesc(),
									unidades, art.get(i).getSellPrice(), subtotal);
							baseImponible += subtotal;
							art.get(i).setStock(art.get(i).getStock() - unidades); // decrementa el stock
						}

						println("----------------------------------------------------------------");
						System.out.printf("                                      BASE IMPONIBLE: %8.2f \n",
								baseImponible);
						System.out.printf("                                           IVA (21%%): %8.2f \n",
								baseImponible * 0.21);
						System.out.printf("                                               TOTAL: %8.2f \n",
								baseImponible * 1.21);

						println("\n\nFactura generada.\nPulse INTRO para volver al menú principal.");
						s.nextLine();

						break;
					} // switch (venta)

				} while (opcion2 == 1);

				break;

			} // switch (menú principal)

		} while (opcion != 7);

	} // main

}