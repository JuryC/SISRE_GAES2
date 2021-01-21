package entidades;

import entidades.Transporte;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-04T07:38:09")
@StaticMetamodel(Tipotransporte.class)
public class Tipotransporte_ { 

    public static volatile SingularAttribute<Tipotransporte, Integer> estado;
    public static volatile ListAttribute<Tipotransporte, Transporte> transporteList;
    public static volatile SingularAttribute<Tipotransporte, Integer> idTipoTransporte;
    public static volatile SingularAttribute<Tipotransporte, String> nombre;

}