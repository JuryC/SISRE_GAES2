package entidades;

import entidades.Material;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-04T07:38:09")
@StaticMetamodel(MaterialCantidad.class)
public class MaterialCantidad_ { 

    public static volatile SingularAttribute<MaterialCantidad, Date> fechaIngreso;
    public static volatile SingularAttribute<MaterialCantidad, Material> material;
    public static volatile SingularAttribute<MaterialCantidad, Integer> idMatCant;
    public static volatile SingularAttribute<MaterialCantidad, Integer> cantidad;

}