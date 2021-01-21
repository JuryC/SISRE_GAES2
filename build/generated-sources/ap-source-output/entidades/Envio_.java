package entidades;

import entidades.Servicio;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-04T07:38:09")
@StaticMetamodel(Envio.class)
public class Envio_ { 

    public static volatile SingularAttribute<Envio, Integer> estado;
    public static volatile SingularAttribute<Envio, Double> valor;
    public static volatile SingularAttribute<Envio, Integer> idEnvio;
    public static volatile ListAttribute<Envio, Servicio> servicioList;
    public static volatile SingularAttribute<Envio, String> tipoEnvio;

}