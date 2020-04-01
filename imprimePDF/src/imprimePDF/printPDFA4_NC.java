package imprimePDF;



import java.io.FileOutputStream;
import java.io.IOException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BarcodePDF417;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;



public class printPDFA4_NC {


	//private static PdfWriter writer;


	public static final String FONT_BOLD = ".\\resources\\fonts\\FrankfurtGothic-Bold.ttf";
	public static final String FONT_CALIBRI = ".\\resources\\fonts\\calibri.ttf";
	public static final String FONT_ARIAL_NARROW = ".\\resources\\fonts\\arial-narrow.ttf";



	public static final String FONT = ".\\resources\\fonts\\Consolas.ttf";
	// private static String FILE = "c:/temp/FirstPdf.pdf";

	public static void imp_factura(String _file_xml, factura_cabecera Cabecera, factura_detalle[] Detalle, int _lineas_de_la_factura, String _file_pdf, String _file_jpg, documentos_rel[] Rel) throws DocumentException, IOException {
		//String reportePDF = ".\\data\\20525719953\\05_pdfs\\xxx.pdf"; 

		String $FECHA_DOC_RELACIONADO="";


		String reportePDF = _file_pdf;
		// 
		String formato_factura = _file_jpg;



		for (int _x=0; _x<12;_x++) {


			String _id = Rel[_x].get_id();
			String _texto = Rel[_x].get_texto();
			try {

				_texto=_texto.replace("_", " ");



				System.out.println(_texto);


				if (_id.equals("1020")) {
					$FECHA_DOC_RELACIONADO=_texto;
				}



			} catch (Exception e) {

			}

		}



		//Document document = new Document();
		//Document document = new Document(PageSize.A4.rotate(), 10, 10, 10, 10);
		Document document = new Document();
		// step 2


		// Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(reportePDF));
		//  PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(reportePDF));

		PdfWriter writer =
				PdfWriter.getInstance(document, new FileOutputStream(reportePDF));

		// step 3
		document.open();


		BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		BaseFont bf_bold = BaseFont.createFont(FONT_BOLD,  BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		Font console = new Font(bf, 7);






		Image img = Image.getInstance(formato_factura);


		img.scalePercent(23);
		img.setAbsolutePosition(0, 70); // horizontal , vertical
		document.add(img);




		// step 4

		/////////////////////////////////////////////










		////////////////////////////////////////

		// ruc  emisor
		PdfContentByte canvas = writer.getDirectContent(); //  getDirectContentUnder();
		writer.setCompressionLevel(0);




		canvas.saveState();                               // q
		canvas.beginText();                               // BT
		canvas.moveText(425, 760);                         // 36 788 Td
		//	canvas.setFontAndSize(BaseFont.createFont(), 11); // /F1 12 Tf
		canvas.setFontAndSize(bf, 11);
		canvas.showText(" RUC: "+Cabecera.get_ruc_emisor());	        // (Hello World)Tj
		canvas.endText();                                 // ET
		canvas.restoreState();                            // Q




		// serie
		canvas.saveState();                               // q
		canvas.beginText();                               // BT
		canvas.moveText(435, 735);                         // 36 788 Td
		canvas.setFontAndSize(bf, 11); // /F1 12 Tf
		canvas.showText(Cabecera.get_serie()+"-"+Cabecera.get_folio());	        // (Hello World)Tj
		canvas.endText();                                 // ET
		canvas.restoreState();                            // Q




		// NOMBRE DEL DOCUMENTO
		canvas.saveState();                               // q
		canvas.beginText();                               // BT
		canvas.moveText(405, 780);                         // 36 788 Td
		canvas.setFontAndSize(bf, 13); // /F1 12 Tf
		//	canvas.setColorFill(BaseColor.WHITE);
		canvas.showText("   "+Cabecera.get_tipo_doc_descripcion());	        // (Hello World)Tj
		canvas.endText();                                 // ET
		canvas.restoreState();                            // Q





		// RAZON SOCIAL  del receptor
		canvas.saveState();                               // q
		canvas.beginText();                               // BT
		canvas.moveText(40, 685);                         // 36 788 Td
		canvas.setFontAndSize(bf, 7); // /F1 12 Tf
		canvas.showText("CLIENTE      :"+ Formato.padRight(Cabecera.get_razon_social_receptor(),50));	        // (Hello World)Tj
		canvas.endText();                                 // ET
		canvas.restoreState();                            // Q










		if (Cabecera.get_direccion_receptor().length()>65) {
			int _tam = Cabecera.get_direccion_receptor().length();
			String _dir01 =  Cabecera.get_direccion_receptor().substring(0, 65);
			String _dir02 =  Cabecera.get_direccion_receptor().substring(65, _tam);

			// direccion social del emisor
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(40, 675);                         // 36 788 Td
			canvas.setFontAndSize(bf, 7); // /F1 12 Tf
			//	canvas.showText("CLIENTE       :"	        // (Hello World)Tj
			canvas.showText("DIRECCION    :"+_dir01);	        // (Hello World)Tj
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q


			// direccion social del emisor
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(40, 665);                         // 36 788 Td
			canvas.setFontAndSize(bf, 9); // /F1 12 Tf
			canvas.showText("        "+_dir02);	        // (Hello World)Tj
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q





		} else {
			// direccion social del emisor
			canvas.saveState();                               // q
			canvas.beginText();                               // BT
			canvas.moveText(40, 675);                         // 36 788 Td
			canvas.setFontAndSize(bf, 7); // /F1 12 Tf
			canvas.showText("DIRECCION    :"+Cabecera.get_direccion_receptor());	        // (Hello World)Tj
			canvas.endText();                                 // ET
			canvas.restoreState();                            // Q



		}






		// ruc del receptor
		canvas.saveState();                               // q
		canvas.beginText();                               // BT
		canvas.moveText(40, 655);                         // 36 788 Td
		canvas.setFontAndSize(bf, 7); // /F1 12 Tf
		//	canvas.showText("CLIENTE    :"	        // (Hello World)Tj
		if (Cabecera.get_tipo_doc().equals("01")) {
			canvas.showText("RUC          :"+Cabecera.get_ruc_receptor());	        // (Hello World)Tj

		} else {
			canvas.showText("RUC/DNI      :"+Cabecera.get_ruc_receptor());	        // (Hello World)Tj

		}

		canvas.endText();                                 // ET
		canvas.restoreState();                            // Q







		// feha emicion
		canvas.saveState();                               // q
		canvas.beginText();                               // BT
		canvas.moveText(40, 645);                         // 36 788 Td
		canvas.setFontAndSize(bf, 7); // /F1 12 Tf
		canvas.showText("Documento Relacionado:"+Cabecera.get_doc_relacionado());	        // (Hello World)Tj
		canvas.endText();                                 // ET
		canvas.restoreState();                            // Q



		// feha emicion
		canvas.saveState();                               // q
		canvas.beginText();                               // BT
		canvas.moveText(40, 635);                         // 36 788 Td
		canvas.setFontAndSize(bf, 7); // /F1 12 Tf
		canvas.showText("Motivo de Anulacion  :"+Cabecera.get_motivo_de_anulacion());	        // (Hello World)Tj
		canvas.endText();                                 // ET
		canvas.restoreState();                            // Q

		// feha emicion
		canvas.saveState();                               // q
		canvas.beginText();                               // BT
		canvas.moveText(40, 625);                         // 36 788 Td
		canvas.setFontAndSize(bf, 7); // /F1 12 Tf
		canvas.showText("Fecha de Doc. Rel.   :"+$FECHA_DOC_RELACIONADO);	        // (Hello World)Tj
		canvas.endText();                                 // ET
		canvas.restoreState();











		// fecha de emision del docto
		canvas.saveState();                               // q
		canvas.beginText();                               // BT
		canvas.moveText(400, 685);                         // 36 788 Td
		canvas.setFontAndSize(bf, 7); // /F1 12 Tf
		// "GUIA       :"
		canvas.showText("F. EMISION     :"+Cabecera.get_fecha());	        // (Hello World)Tj
		canvas.endText();                                 // ET
		canvas.restoreState();                            // Q









		String _moneda="";
		if (Cabecera.get_moneda().equals("PEN")) {
			_moneda="SOLES";
		} else {
			_moneda="DOLARES";
		}




		//moneda
		canvas.saveState();                               // q
		canvas.beginText();                               // BT
		canvas.moveText(400, 675);                         // 36 788 Td
		canvas.setFontAndSize(bf, 7); // /F1 12 Tf
		//canvas.showText("Moneda:           "+Cabecera.get_moneda());	        // (Hello World)Tj
		if (Cabecera.get_moneda().equals("PEN")) {
			canvas.showText("MONEDA         :"+"SOLES");	        // (Hello World)Tj
		} else {
			canvas.showText("MONEDA         :"+"DOLARES");	        // (Hello World)Tj
		}

		canvas.endText();                                 // ET
		canvas.restoreState();                            // Q



		canvas.saveState();                               // q
		canvas.beginText();                               // BT
		canvas.moveText(30, 608);                         // 36 788 Td
		canvas.setFontAndSize(bf, 7); // /F1 12 Tf
		if (Cabecera.get_tipo_doc().equals("01")) {
			canvas.showText("    Cantidad   Unidad    Descripción                                                 Código          V.Unitario              Importe      ");	    	
		} else {
			canvas.showText("    Cantidad   Unidad    Descripción                                                 Código          P.Unitario              Importe      ");	    	        // (Hello World)Tj
		}


		canvas.endText(); 
		canvas.restoreState();                            // Q







		/////////////////////



		/////////////////////














		// cantidad en letra
		canvas.saveState();                               // q
		canvas.beginText();                               // BT
		canvas.moveText(120, 297);                         // 36 788 Td
		canvas.setFontAndSize(bf, 8); // /F1 12 Tf
		if (Cabecera.get_moneda().equals("PEN")) {
			canvas.showText(Cabecera.get_total_letra()+" SOLES.");	        // (Hello World)Tj
		} else {
			canvas.showText(Cabecera.get_total_letra()+" DOLARES.");	        // (Hello World)Tj
		}
		canvas.endText();                                 // ET
		canvas.restoreState();                            // Q








		String _contenido_qr = Cabecera.get_ruc_emisor()+"|"+Cabecera.get_tipo_documento()+"|"+   
				Cabecera.get_serie()+"-"+Cabecera.get_folio()+"|"+
				Cabecera.get_total_igv()+"|"+Cabecera.get_total()+"|"+Cabecera.get_fecha_qr()+"|"+
				Cabecera.get_tipo_doc_adquiriente()+"|"+
				Cabecera.get_ruc_receptor()+"|";

		//+Cabecera.get_codigo_hash()+"|";

		BarcodeQRCode barcodeQRCode = new BarcodeQRCode(_contenido_qr, 90, 90, null);
		Image codeQrImage = barcodeQRCode.getImage();
		codeQrImage.setAbsolutePosition(156, 142);
		document.add(codeQrImage);





		//special font sizes
		BaseFont bf_arial = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		Font arial = new Font(bf_arial, 7);

		Paragraph _linea00 = new Paragraph();
		Chunk _espacio = new Chunk(" ",arial);
		_linea00.add(_espacio);


		for (int z = 1; z<=13; z++) {
			document.add(_linea00);
		}


		Paragraph _linea01 = new Paragraph(7);

		String _desc ="";









		System.out.println(" lineas "+_lineas_de_la_factura);

		for (int i=0; i<_lineas_de_la_factura; i++) {
			//	 System.out.println("esta en la linea "+i);
			//	System.out.println(Detalle[i].get_descripcion());
			if (Detalle[i].get_descripcion()!=null) { 	 
				if (!Detalle[i].get_descripcion().trim().equals("")) {


					if (Detalle[i].get_codigo()==null) {
						Detalle[i].set_codigo(".");
					}

					Chunk _producto = new Chunk(Formato.padRight(Detalle[i].get_codigo(),15));
					Chunk _descripcion = new Chunk(Formato.padRight(Formato.cadena59(Detalle[i].get_descripcion()),59));





					if (Detalle[i].get_unidad()==null) {
						Detalle[i].set_unidad("KG");
					}

					if (Detalle[i].get_unidad().equals("KGM")) {
						Detalle[i].set_unidad("KG");
					}

					if (Detalle[i].get_unidad().equals("MTR")) {
						Detalle[i].set_unidad("MTS");
					}

					if (Detalle[i].get_unidad().equals("NIU")) {
						Detalle[i].set_unidad("UNI");
					}

					if (Detalle[i].get_unidad().equals("BX")) {
						Detalle[i].set_unidad("CJ");
					}

					if (Detalle[i].get_unidad().equals("PF")) {
						Detalle[i].set_unidad("PAL");
					}



					Chunk _unidad_de_medida = new Chunk(Formato.cadena5(Detalle[i].get_unidad()));
					Chunk _cantidad = new Chunk(Formato.cant0(Detalle[i].get_cantidad()));
					Chunk _precio = new Chunk(Formato.dinero5(Detalle[i].get_precio_unitario()));
					Chunk _precio_con_igv = new Chunk(Formato.dinero6(Detalle[i].get_subtotal()/Detalle[i].get_cantidad()));
					Chunk _importe = new Chunk(Formato.dinero(Detalle[i].get_subtotal()));
					Chunk _igv = new Chunk(Formato.dinero(Detalle[i].get_igv()));
					Chunk _importe_sin_igv = new Chunk(Formato.dinero(Detalle[i].get_subtotal()));

					_espacio.setFont(console);
					//  	_lineas_de_la_factura





					_producto.setFont(console);
					_descripcion.setFont(console);
					_cantidad.setFont(console);
					_unidad_de_medida.setFont(console);

					_precio.setFont(console);
					_precio_con_igv.setFont(console);
					_importe.setFont(console);
					_importe_sin_igv.setFont(console);
					_igv.setFont(console);


					// 	_linea01.add(_espacio);
					if (Detalle[i].get_codigo()!=".") {
						if (Detalle[i].get_codigo().equals(".")) {
							_linea01.add(_espacio);
							_linea01.add(_espacio);
							_linea01.add(_espacio);
							_linea01.add(_espacio);
							_linea01.add(_espacio);
							_linea01.add(_espacio);
							_linea01.add(_espacio);
							_linea01.add(_espacio);
							_linea01.add(_espacio);

						} else {
				//			_linea01.add(_espacio);
				//			_linea01.add(_espacio);


							_linea01.add(_cantidad);

							_linea01.add(_espacio);
							_linea01.add(_espacio);
							_linea01.add(_espacio);
							_linea01.add(_espacio);

							_linea01.add(_unidad_de_medida);
							_linea01.add(_espacio);
							



						}
					} else {


						for (int _l=0; _l<35; _l++) {
							_linea01.add(_espacio);
						} 		 


					}


					//		System.out.println(_descripcion);

				
					_linea01.add(_espacio);
					_linea01.add(_espacio);
					_linea01.add(_espacio);
					_linea01.add(_descripcion);


					if (!Detalle[i].get_codigo().equals(".")) {

	
						_linea01.add(_espacio);
						_linea01.add(_producto);



				///		_linea01.add(_espacio);
				//		_linea01.add(_espacio);
				//		_linea01.add(_espacio);

	

						if (Cabecera.get_tipo_doc_descripcion().equals("BOLETA ELECTRONICA")) {
							_linea01.add(_precio_con_igv);
						}

						else
						{
							_linea01.add(_precio_con_igv);
						}
						_linea01.add(_espacio);
						_linea01.add(_espacio);
						_linea01.add(_espacio);
						_linea01.add(_espacio);
						_linea01.add(_espacio);
						_linea01.add(_espacio);

						_linea01.add(_espacio);
						_linea01.add(_espacio);
						_linea01.add(_espacio);
			
						
						if (Cabecera.get_tipo_doc_descripcion().equals("BOLETA ELECTRONICA")) {
							_linea01.add(_importe);
						}

						else
						{
							_linea01.add(_importe);
						}
						// _linea01.add(_importe_sin_igv);




					}
				} 
			}


			document.add(_linea01);
			_linea01.removeAll(_linea01);
		}


		// Q



		String _signo="";

		if (Cabecera.get_moneda().equals("PEN")) {
			_signo="S/";
		} else {
			_signo=" $";
		}


		int _y=-100;



		// TOTAL subtotal
		canvas.saveState();                               // q
		canvas.beginText();                               // BT
		canvas.moveText(362, 272);                         // 36 788 Td
		canvas.setFontAndSize(bf, 9); // /F1 12 Tf
		//			              //"Sub Total   :"
		if (Cabecera.get_tipo_doc().equals("07")) {
			if (Cabecera.get_moneda().equals("PEN")) {
				canvas.showText("Total Ventas Gravadas   S/:"+Formato.dinero(Cabecera.get_total_gravado()+Cabecera.get_total_descuentos()));
			} else {
				canvas.showText("Total Ventas Gravadas    $:"+Formato.dinero(Cabecera.get_total_gravado()+Cabecera.get_total_descuentos()));
			}
		} else {


			if (Cabecera.get_moneda().equals("PEN")) {
				//			canvas.showText("Total Ventas Gravadas   S/:"+Formato.dinero(Cabecera.get_total_gravado()+Cabecera.get_total_descuentos()+Cabecera.get_total_igv()));
			} else {
				//			canvas.showText("Total Ventas Gravadas    $:"+Formato.dinero(Cabecera.get_total_gravado()+Cabecera.get_total_descuentos()+Cabecera.get_total_igv()));
			}

		}
		//			canvas.showText("Sub Total S/:"+Formato.dinero(Cabecera.get_total_gravado()+Cabecera.get_total_inafecto()+Cabecera.get_total_exonerado()+Cabecera.get_total_descuentos()));
		canvas.endText();                                 // ET
		canvas.restoreState();                            // Q



		// TOTAL inafecto
		canvas.saveState();                               // q
		canvas.beginText();                               // BT
		canvas.moveText(362, 261);                         // 36 788 Td
		canvas.setFontAndSize(bf, 9); // /F1 12 Tf
		//	"Sub Total   :"
		if (Cabecera.get_tipo_doc().equals("07")) {
			if (Cabecera.get_moneda().equals("PEN")) {
				//              "Total Ventas Gravadas    $:"
				canvas.showText("Total Ventas Inafectas  S/:"+Formato.dinero(Cabecera.get_total_inafecto()));
			} else {
				canvas.showText("Total Ventas Inafectas   $:"+Formato.dinero(Cabecera.get_total_inafecto()));
			}
		} else {


		}

		canvas.endText();                                 // ET
		canvas.restoreState();                            // Q



		// TOTAL exonerado
		canvas.saveState();                               // q
		canvas.beginText();                               // BT
		canvas.moveText(362, 250);                         // 36 788 Td
		canvas.setFontAndSize(bf, 9); // /F1 12 Tf
		//	"Sub Total   :"

		if (Cabecera.get_tipo_doc().equals("07")) {
			if (Cabecera.get_moneda().equals("PEN")) {
				//   "Descuento S/:"
				//         "Total Ventas Gravadas    $:"
				canvas.showText("Total Ventas Exoneradas S/:"+Formato.dinero(Cabecera.get_total_exonerado()));
			} else {
				canvas.showText("Total Ventas Exoneradas  $:"+Formato.dinero(Cabecera.get_total_exonerado()));
			}
		}

		canvas.endText();                                 // ET
		canvas.restoreState();                            // Q





		// TOTAL descuentos
		canvas.saveState();                               // q
		canvas.beginText();                               // BT
		canvas.moveText(362, 239);                         // 36 788 Td
		canvas.setFontAndSize(bf, 9); // /F1 12 Tf
		//	"Sub Total   :"
		if (Cabecera.get_moneda().equals("PEN")) {
			//   "Descuento S/:"
			//            "Total Ventas Gravadas    $:"
			canvas.showText("Descuento Total     "+Formato.dinero3a(Cabecera.get_porciento_descuento()*100)+ "% S/:"+Formato.dinero(Cabecera.get_total_descuentos()));
		} else {
			canvas.showText("Descuento Total     "+Formato.dinero3a(Cabecera.get_porciento_descuento()*100)+ "%  $:"+Formato.dinero(Cabecera.get_total_descuentos()));
		}


		canvas.endText();                                 // ET
		canvas.restoreState();                            // Q







		// TOTAL anticipo
		canvas.saveState();                               // q
		canvas.beginText();                               // BT
		canvas.moveText(429, 241);                         // 36 788 Td
		canvas.setFontAndSize(bf, 9); // /F1 12 Tf
		//	"Sub Total   :"
		if (Cabecera.get_tipo_doc().equals("07")) {
			if (Cabecera.get_moneda().equals("PEN")) {
				//			canvas.showText("V. Venta  S/:"+Formato.dinero(Cabecera.get_total_gravado()+Cabecera.get_total_inafecto()+Cabecera.get_total_exonerado()));
			} else {
				//			canvas.showText("V. Venta   $:"+Formato.dinero(Cabecera.get_total_gravado()+Cabecera.get_total_inafecto()+Cabecera.get_total_exonerado()));
			}
		}

		canvas.endText();                                 // ET
		canvas.restoreState();                            // Q  




		// TOTAL igv
		canvas.saveState();                               // q
		canvas.beginText();                               // BT
		canvas.moveText(362, 228);                         // 36 788 Td
		canvas.setFontAndSize(bf, 9); // /F1 12 Tf
		//	"Sub Total   :"
		if (Cabecera.get_tipo_doc().equals("07")) {
			//       	         "IGV      18%:"
			if (Cabecera.get_moneda().equals("PEN")) { 
				canvas.showText("Total IGV           18% S/:"+Formato.dinero(Cabecera.get_total_igv()));
			} else {
				canvas.showText("Total IGV           18%  $:"+Formato.dinero(Cabecera.get_total_igv()));

			}
		} else {

		}
		canvas.endText();                                 // ET
		canvas.restoreState();                            // Q  





		// TOTAL DE LA FACTURAS
		canvas.saveState();                               // q
		canvas.beginText();                               // BT
		canvas.moveText(474, 208);                         // 36 788 Td
		canvas.setFontAndSize(bf, 10); // /F1 12 Tf
		//	canvas.setColorFill(BaseColor.WHITE);
		// canvas.showText("Total:      "+Cabecera.get_total());	
		//	"Sub Total   :"
		if (Cabecera.get_moneda().equals("PEN")) {
			canvas.showText("S/:"+Formato.dinero(Cabecera.get_total()));
		} else {
			canvas.showText(" $:"+Formato.dinero(Cabecera.get_total()));
		}


		canvas.endText();                                 // ET
		canvas.restoreState();                            // Q



		// descuento cabecera
		canvas.saveState();                               // q
		canvas.beginText();                               // BT
		canvas.moveText(377, 115);                         // 36 788 Td
		canvas.setFontAndSize(bf, 9); // /F1 12 Tf
		//	canvas.showText("Tipo de Operación:"+Cabecera.get_tipo_operacion());	        // (Hello World)Tj
		canvas.endText();                                 // ET
		canvas.restoreState();                            // Q




		// resumen hash
		canvas.saveState();                               // q
		canvas.beginText();                               // BT
		canvas.moveText(132, 126);                         // 36 788 Td
		canvas.setFontAndSize(bf, 7); // /F1 12 Tf
		canvas.showText("Hash: "+Cabecera.get_codigo_hash());	        // (Hello World)Tj
		canvas.endText();                                 // ET
		canvas.restoreState();                            // Q










		// step 5
		document.close();		



	}






}
