package uniandes.dpoo.taller0.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Esta clase tiene la informaci�n sobre un pa�s y sus atletas.
 */
public class Pais
{
	// ************************************************************************
	// Atributos
	// ************************************************************************

	/**
	 * El nombre del pa�s
	 */
	private String nombre;

	/**
	 * La lista de los atletas que han representado al pa�s
	 */
	private List<Atleta> atletas;

	// ************************************************************************
	// Constructores
	// ************************************************************************

	/**
	 * Construye un nuevo pa�s con el nombre indicado y con una lista vac�a de
	 * atletas
	 * 
	 * @param nombre El nombre del pa�s
	 */
	public Pais(String nombre)
	{
		this.nombre = nombre;
		atletas = new ArrayList<Atleta>();
	}

	// ************************************************************************
	// M�todos para consultar los atributos
	// ************************************************************************

	/**
	 * Consulta el nombre del pa�s
	 * 
	 * @return nombre
	 */
	public String darNombre()
	{
		return nombre;
	}

	// ************************************************************************
	// Otros m�todos
	// ************************************************************************

	/**
	 * Agrega un nuevo atleta al pa�s
	 * 
	 * @param nuevoAtleta El nuevo atleta
	 */
	public void agregarAtleta(Atleta nuevoAtleta)
	{
		atletas.add(nuevoAtleta);
	}

	/**
	 * Compila la informaci�n de los atletas de un pa�s.
	 * 
	 * @return Una lista de mapas con la informaci�n de todos los atletas del pa�s.
	 *         Cada registro de un atleta queda en un mapa que tiene tres llaves:
	 *         "evento", que tiene asociado el nombre del evento en el que particip�
	 *         el atleta; "anio", que tiene asociado el a�o en el que el atleta
	 *         particip� en el evento; y "nombre" que tiene asociado el nombre del
	 *         atleta.
	 */
	public List<Map<String, Object>> consultarAtletas()
	{
		List<Map<String, Object>> resultado = new ArrayList<Map<String, Object>>();
		for (Atleta unAtleta : atletas)
		{
			List<Map<String, Object>> participacionesAtleta = unAtleta.consultarParticipacionesAtleta();
			resultado.addAll(participacionesAtleta);
		}
		return resultado;
	}

	/**
	 * Calcula la cantidad de medallas de cada tipo que han acumulado en un evento
	 * los atletas del pa�s.
	 * 
	 * @param nombreEvento El nombre del evento de inter�s
	 * @return Un arreglo con tres enteros: la cantidad de medallas de oro, la
	 *         cantidad de medallas de plata y la cantidad de medallas de bronce
	 *         ganadas por los atletas del pa�s.
	 */
	public int[] calcularMedallasEvento(String nombreEvento)
	{
		int[] medallasEvento = { 0, 0, 0 };

		for (Atleta atleta : atletas)
		{
			int[] medallasAtleta = atleta.darMedallasEnEvento(nombreEvento);
			medallasEvento[0] += medallasAtleta[0];
			medallasEvento[1] += medallasAtleta[1];
			medallasEvento[2] += medallasAtleta[2];
		}
		return medallasEvento;
	}

	/**
	 * Calcula la cantidad de atletas diferentes del pa�s que han sido medallistas
	 * 
	 * @return La cantidad de medallistas
	 */
	public int contarMedallistas()
	{
		Set<Atleta> medallistas = new HashSet<>();
		for (Atleta unAtleta : atletas)
		{
			if (!medallistas.contains(unAtleta) && unAtleta.esMedallista())
				medallistas.add(unAtleta);
		}

		return medallistas.size();
	}

	/**
	 * Consulta cu�les han sido los medallistas del pa�s de un determinado g�nero.
	 * 
	 * @param generoAtleta El g�nero de inter�s.
	 * @return Retorna un mapa donde las llaves son los nombres de los atletas del
	 *         pa�s y del g�nero que han sido medallistas y los valores son una
	 *         lista con informaci�n de sus medallas. La informaci�n de cada medalla
	 *         tambi�n es un mapa que tiene tres llaves: "evento", que tiene
	 *         asociado el nombre del evento; "anio", que tiene asociado el a�o en
	 *         el que el atleta gan� la medalla; y "medalla" que tiene asociado el
	 *         tipo de medalla.
	 */
	public Map<String, List<Map<String, Object>>> consultarMedallistasGenero(Genero generoAtleta)
	{
		Map<String, List<Map<String, Object>>> resultado = new HashMap<String, List<Map<String, Object>>>();

		for (Atleta atleta : atletas)
		{
			if (atleta.darGenero().equals(generoAtleta) && atleta.esMedallista())
			{
				List<Map<String, Object>> medallasAtleta = atleta.consultarMedallas();
				resultado.put(atleta.darNombre(), medallasAtleta);
			}
		}
		return resultado;
	}
}
