package entidades;

import entidades.Cita;
import entidades.Material;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-04T07:38:09")
@StaticMetamodel(CitaMaterial.class)
public class CitaMaterial_ { 

    public static volatile SingularAttribute<CitaMaterial, Cita> idCita;
    public static volatile SingularAttribute<CitaMaterial, Integer> idCitaMaterial;
    public static volatile SingularAttribute<CitaMaterial, Integer> cantidadSolicitada;
    public static volatile SingularAttribute<CitaMaterial, Material> idMaterial;

}