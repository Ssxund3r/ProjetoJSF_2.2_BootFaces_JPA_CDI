package br.com.converter;

import java.io.Serializable;

import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.com.entidades.Cidades;

@FacesConverter(forClass = Cidades.class, value = "converterCidades")
public class CidadesConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override // Retorna objeto inteiro
	public Object getAsObject(FacesContext context, UIComponent component, String codigoCidades) {

		EntityManager entityManager = (EntityManager) CDI.current().select(EntityManager.class).get();

		Cidades cidade = (Cidades) entityManager.find(Cidades.class, Long.parseLong(codigoCidades));

		return cidade;

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object cidade) {

		if (cidade == null) {
			return null;
		}

		if (cidade instanceof Cidades) {
			return ((Cidades) cidade).getId().toString();
		} else {
			return cidade.toString();
		}

	}

}
