package entidades;

import entidades.Contrato;
import entidades.Servicio;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-04T07:38:09")
@StaticMetamodel(Tiposervicio.class)
public class Tiposervicio_ { 

    public static volatile SingularAttribute<Tiposervicio, Integer> estado;
    public static volatile SingularAttribute<Tiposervicio, Double> valor;
    public static volatile ListAttribute<Tiposervicio, Contrato> contratoList;
    public static volatile ListAttribute<Tiposervicio, Servicio> servicioList;
    public static volatile SingularAttribute<Tiposervicio, String> nombre;
    public static volatile SingularAttribute<Tiposervicio, Integer> idTipoServicio;

}