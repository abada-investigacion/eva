package es.sacyl.eva.ui;

/*
 * #%L
 * Eva REST-Jimena
 * %%
 * Copyright (C) 2013 Abada Servicios Desarrollo (investigacion@abadasoft.com)
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import java.io.IOException;
import java.io.StringReader;
import java.util.Collection;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import es.sacyl.eva.beans.CDABean;
import es.sacyl.eva.beans.CodificacionBean;
import es.sacyl.eva.beans.DatoBean;
import es.sacyl.eva.containment.Container;
import es.sacyl.eva.rest.CDAService;

/**
 * Main UI class
 */
@SuppressWarnings("serial")
public class EvaRESTUI extends UI {

	private Button buttonCDAService = new Button(getCDAStatus());;
	private Button buttonRefresh = new Button("Actualiza");
	private Button buttonBorra = new Button("Borra todos");
	private Button buttonProcesa = new Button("Procesa todos");

	private Table tabla;

	final VerticalLayout layout = new VerticalLayout();

	@Override
	protected void init(VaadinRequest request) {
		layout.setMargin(true);
		layout.setSpacing(true);
		setContent(layout);

		buttonCDAService.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				CDAService.activo = !CDAService.activo;
				buttonCDAService.setCaption(getCDAStatus());
			}
		});
		layout.addComponent(buttonCDAService);

		/* ACCIONES */

		HorizontalLayout rowAcciones = new HorizontalLayout();
		rowAcciones.setSpacing(true);
		layout.addComponent(rowAcciones);
		// Añadir
		rowAcciones.addComponent(buttonRefresh);
		rowAcciones.addComponent(buttonBorra);
		rowAcciones.addComponent(buttonProcesa);
		// Acciones
		buttonRefresh.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				doRefresh();
			}
		});
		buttonBorra.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				ConfirmDialog.show(getUI(), "¿Seguro que quieres borrar todos?", new ConfirmDialog.Listener() {
					public void onClose(ConfirmDialog dialog) {
						if (dialog.isConfirmed()) {
							Notification.show("Borrado");
						}
					}
				});
			}
		});

		doRefresh();

	}

	public void doRefresh() {
		if (tabla == null) {
			tabla = new Table();
			layout.addComponent(tabla);
			tabla.setSizeFull();
			tabla.addContainerProperty("CDA", String.class, null);
			tabla.addContainerProperty("object", CDABean.class, null);
			
			tabla.setVisibleColumns(new String[] {"CDA"});
			
			tabla.setSelectable(true);
			tabla.setMultiSelect(true);

//			tabla.addItemClickListener(new ItemClickListener() {
//				@Override
//				public void itemClick(ItemClickEvent event) {
//					CDABean bean = (CDABean) ((Property) event.getItem().getItemProperty("object")).getValue();
//					procesa(bean);
//				}
//			});
		}

		try {
			Collection<CDABean> cdas = Container.getInstance().getCDAs();

			tabla.getContainerDataSource().removeAllItems();
			for (CDABean cda : cdas) {
				Item item = tabla.getContainerDataSource().addItem(cda.getIdentificador());
				Property propertyCDA = item.getItemProperty("CDA");
				propertyCDA.setValue(cda.getIdentificador());
				Property propertyObject = item.getItemProperty("object");
				propertyObject.setValue(cda);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void procesa(CDABean bean) {

		try {

			SAXBuilder builder = new SAXBuilder();
			Document document = (Document) builder.build(new StringReader(bean.getCda()));
			
			Element ClinicalDocument = document.getRootElement();
			
			/* Datos demográficos */
			
			Element recordTarget = ClinicalDocument.getChild("recordTarget", Namespace.getNamespace("ns", "urn:hl7-org:v3"));
			
			Element patientRole = recordTarget.getChild("patientRole", Namespace.getNamespace("ns", "urn:hl7-org:v3"));

			List<Element> ids = patientRole.getChildren("id", Namespace.getNamespace("ns", "urn:hl7-org:v3"));
			
			for (Element element : ids) {
				// Es un ID
				if ("2.16.840.1.113883.2.19.20.17.40.5.50101.10.1".equals(element.getAttributeValue("root"))) {
					bean.setNhc(element.getAttributeValue("extension"));
				} else if ("1.3.6.1.4.1.19126.4".equals(element.getAttributeValue("root"))) {
					bean.setNss(element.getAttributeValue("extension"));
				} else if ("2.16.840.1.113883.2.19.10.1".equals(element.getAttributeValue("root"))) {
					bean.setTarjeta(element.getAttributeValue("extension"));
				} else if ("1.3.6.1.4.1.19126.3".equals(element.getAttributeValue("root"))) {
					bean.setDni(element.getAttributeValue("extension"));
				}
			}
			
			Element patient = patientRole.getChild("patient", Namespace.getNamespace("ns", "urn:hl7-org:v3"));
			
			Element name = patient.getChild("name", Namespace.getNamespace("ns", "urn:hl7-org:v3"));
			
			List<Element> nombre = name.getChildren("given", Namespace.getNamespace("ns", "urn:hl7-org:v3"));
			List<Element> apellidos = name.getChildren("family", Namespace.getNamespace("ns", "urn:hl7-org:v3"));
			
			StringBuffer bufferNombre = new StringBuffer();
			for (Element nom : nombre) {
				if (bufferNombre.length() != 0) {
					bufferNombre.append(" ");
				}
				bufferNombre.append(nom.getValue());
			}
			for (Element ape : apellidos) {
				if (bufferNombre.length() != 0) {
					bufferNombre.append(" ");
				}
				bufferNombre.append(ape.getValue());
			}
			
			bean.setNombre(bufferNombre.toString());

			/* Datos clínicos */
			
			Element masterComponent = ClinicalDocument.getChild("component", Namespace.getNamespace("ns", "urn:hl7-org:v3"));
			Element structuredBody = masterComponent.getChild("structuredBody", Namespace.getNamespace("ns", "urn:hl7-org:v3"));
			List<Element> components = structuredBody.getChildren("component", Namespace.getNamespace("ns", "urn:hl7-org:v3"));
			
			for (Element component : components) {
				Element section = component.getChild("section", Namespace.getNamespace("ns", "urn:hl7-org:v3"));
				
				Element title = section.getChild("title", Namespace.getNamespace("ns", "urn:hl7-org:v3"));
				Element text = section.getChild("text", Namespace.getNamespace("ns", "urn:hl7-org:v3"));

				DatoBean datoBean = new DatoBean();
				datoBean.setTitulo(title.getValue());
				datoBean.setDato(text.getValue());
				bean.getDatos().add(datoBean);
				
				List<Element> codes = section.getChildren("code", Namespace.getNamespace("ns", "urn:hl7-org:v3"));
				for (Element code : codes) {
					datoBean.getCodigos().add(new CodificacionBean(code.getAttributeValue("codeSystem"), code.getAttributeValue("code")));
				}
				
			}
			
			bean.setProcesado(true);
			
			System.out.println(bean);

		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getCDAStatus() {
		if (CDAService.activo) {
			return "CDA service ACTIVO";
		} else {
			return "CDA service INACTIVO (almacenamiento local)";
		}
	}

}