package recuperacionSegundo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Ex04pcs3 {

	static ArrayList<GestisimalArt> art = new ArrayList<GestisimalArt>();

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
			System.out.println("\n\nG E S T I S I M A L");
			System.out.println("===================");
			System.out.println("1. Listado");
			System.out.println("2. Alta");
			System.out.println("3. Baja");
			System.out.println("4. Modificaci�n");
			System.out.println("5. Entrada de mercanc�a");
			System.out.println("6. Venta");
			System.out.println("7. Salir");
			System.out.print("Introduzca una opci�n: ");
			opcion = Integer.parseInt(System.console().readLine());

			switch (opcion) {

			case 1: // Listado /////////////////////////////////////////////
				System.out.println("\nLISTADO\n=======");

				for (GestisimalArt aux : art) {
					System.out.println(aux);
				}
				break;

			case 2: // Alta ////////////////////////////////////////////////
				System.out.println("\nNUEVO ART�CULO\n==============");

				System.out.println("Por favor, introduzca los datos del art�culo.");
				System.out.print("C�digo: ");

				do {
					codigoIntro = System.console().readLine();
					if (posicion(codigoIntro) != -1) {
						System.out.print("Ese c�digo ya existe.\nIntroduzca otro c�digo: ");
					}
				} while (posicion(codigoIntro) != -1);

				System.out.print("Descripcion: ");
				descripcionIntro = System.console().readLine();

				System.out.print("Precio de compra: ");
				precioDeCompraIntro = Double.parseDouble(System.console().readLine());

				System.out.print("Precio de venta: ");
				precioDeVentaIntro = Double.parseDouble(System.console().readLine());

				System.out.print("Stock: ");
				stockIntro = Integer.parseInt(System.console().readLine());

				art.add(new GestisimalArt(codigoIntro, descripcionIntro, precioDeCompraIntro, precioDeVentaIntro,
						stockIntro));

				break;

			case 3: // Baja ////////////////////////////////////////////////
				System.out.println("\nBAJA\n====");
				System.out.print("Por favor, introduzca el c�digo del art�culo que desea dar de baja: ");
				codigoIntro = System.console().readLine();

				if (posicion(codigoIntro) == -1) {
					System.out.println("Lo siento, el c�digo introducido no existe.");
				} else {
					art.remove(posicion(codigoIntro));
					System.out.println("articulo borrado.");
				}

				break;

			case 4: // Modificaci�n ////////////////////////////////////////
				System.out.println("\nMODIFICACI�N\n============");
				System.out.print("Por favor, introduzca el c�digo del art�culo cuyos datos desea cambiar: ");

				do {
					codigoIntro = System.console().readLine();
					if (posicion(codigoIntro) != -1) {
						System.out.println("No hay ning�n art�culo con ese c�digo.\nIntroduzca otro c�digo: ");
					}
				} while (posicion(codigoIntro) == -1);

				i = posicion(codigoIntro);

				System.out.println("Introduzca los nuevos datos del art�culo o INTRO para dejarlos igual.");

				System.out.println("C�digo: " + art.get(i).getCode());
				System.out.print("Nuevo c�digo: ");
				codigoIntro = System.console().readLine();
				if (!codigoIntro.equals("")) {
					art.get(i).setCode(codigoIntro);
				}

				System.out.println("Descripci�n: " + art.get(i).getDesc());
				System.out.print("Nueva descripci�n: ");
				descripcionIntro = System.console().readLine();
				if (!descripcionIntro.equals("")) {
					art.get(i).setDesc(descripcionIntro);
				}

				System.out.println("Precio de compra: " + art.get(i).getBuyPrice());
				System.out.print("Nuevo precio de compra: ");
				precioDeCompraIntroString = System.console().readLine();
				if (!precioDeCompraIntroString.equals("")) {
					art.get(i).setBuyPrice(Double.parseDouble(precioDeCompraIntroString));
				}

				System.out.println("Precio de venta: " + art.get(i).getSellPrice());
				System.out.print("Nuevo precio de venta: ");
				precioDeVentaIntroString = System.console().readLine();
				if (!precioDeVentaIntroString.equals("")) {
					art.get(i).setSellPrice(Double.parseDouble(precioDeVentaIntroString));
				}

				System.out.println("Stock: " + art.get(i).getStock());
				System.out.print("Nuevo stock: ");
				stockIntroString = System.console().readLine();
				if (!stockIntroString.equals("")) {
					art.get(i).setStock(Integer.parseInt(stockIntroString));
				}

				break;

			case 5: // Entrada de mercanc�a //////////////////////////////
				System.out.println("\nENTRADA DE MERCANC�A\n====================");
				System.out.print("Por favor, introduzca el c�digo del art�culo: ");
				codigoIntro = System.console().readLine();

				do {
					codigoIntro = System.console().readLine();
					if (posicion(codigoIntro) != -1) {
						System.out.println("No hay ning�n art�culo con ese c�digo.\nIntroduzca otro c�digo: ");
					}
				} while (posicion(codigoIntro) == -1);

				i = posicion(codigoIntro);

				System.out.println("Entrada de mercanc�a del siguiente art�culo: ");
				System.out.println(art.get(i));
				System.out.print("Introduzca el n�mero de unidades que entran al almac�n: ");
				stockIntro = Integer.parseInt(System.console().readLine());
				art.get(i).setStock(stockIntro + art.get(i).getStock());
				System.out.println("La mercanc�a ha entrado en el almac�n.");

				break;

			case 6: // Venta
				System.out.println("\nVENTA\n=====");

				do {
					System.out.println("\n1. A�adir art�culo");
					System.out.println("2. Generar factura");
					System.out.println("3. Cancelar");
					System.out.print("Introduzca una opci�n: ");
					opcion2 = Integer.parseInt(System.console().readLine());

					switch (opcion2) {

					case 1: // A�adir l�nea ////////////////////////////
						System.out.print("Por favor, introduzca el c�digo del art�culo: ");
						codigoIntro = System.console().readLine();
						i = posicion(codigoIntro);

						if (i == -1) {
							System.out.println("No hay ning�n art�culo con ese c�digo.");
						} else {
							System.out.println(art.get(i));

							if (lineasFra.containsKey(codigoIntro)) {
								unidadesEnFactura = lineasFra.get(codigoIntro);
							} else {
								unidadesEnFactura = 0;
							}

							System.out.println("Unidades en la factura provisional: " + unidadesEnFactura);

							System.out.print("Unidades que quiere incorporar a la factura: ");
							unidades = Integer.parseInt(System.console().readLine());

							if ((art.get(i).getStock()) - unidadesEnFactura < unidades) {
								System.out.println("No hay suficiente stock. Puede a�adir a la venta un m�ximo de "
										+ (art.get(i).getStock() - unidadesEnFactura) + " unidades de ese producto.");
							} else if (lineasFra.containsKey(codigoIntro)) {
								lineasFra.put(codigoIntro, lineasFra.get(codigoIntro) + unidades);
							} else {
								lineasFra.put(codigoIntro, unidades);
							}
						}

						// Muestra las l�neas
						System.out.println("\n\n C�DIGO |    DESCRIPCI�N    | UNIDADES | PRECIO UNID. | SUBTOTAL");
						System.out.println("------------------------------------------------------------------");
						for (Map.Entry pareja : lineasFra.entrySet()) {
							codigo = pareja.getKey().toString();
							i = posicion(codigo);
							unidades = Integer.parseInt(pareja.getValue().toString());
							subtotal = unidades * art.get(i).getSellPrice();
							System.out.printf(" %6s | %17s | %8d | %12.2f | %8.2f\n", codigo, art.get(i).getDesc(),
									unidades, art.get(i).getSellPrice(), subtotal);
						}

						break;

					case 2: // Genera la factura ///////////////////////
						baseImponible = 0;
						System.out.println("\n\n C�DIGO |   DESCRIPCI�N   | UNIDADES | PRECIO UNID. | SUBTOTAL");
						System.out.println("----------------------------------------------------------------");
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

						System.out.println("----------------------------------------------------------------");
						System.out.printf("                                      BASE IMPONIBLE: %8.2f \n",
								baseImponible);
						System.out.printf("                                           IVA (21%%): %8.2f \n",
								baseImponible * 0.21);
						System.out.printf("                                               TOTAL: %8.2f \n",
								baseImponible * 1.21);

						System.out.println("\n\nFactura generada.\nPulse INTRO para volver al men� principal.");
						System.console().readLine();

						break;
					} // switch (venta)

				} while (opcion2 == 1);

				break;

			} // switch (men� principal)

		} while (opcion != 7);

	} // main

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
}