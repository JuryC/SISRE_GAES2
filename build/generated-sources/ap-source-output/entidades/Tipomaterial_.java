package entidades;

import entidades.Material;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-04T07:38:09")
@StaticMetamodel(Tipomaterial.class)
public class Tipomaterial_ { 

    public static volatile SingularAttribute<Tipomaterial, Integer> estado;
    public static volatile ListAttribute<Tipomaterial, Material> materialList;
    public static volatile SingularAttribute<Tipomaterial, String> nombre;
    public static volatile SingularAttribute<Tipomaterial, Integer> idTipoMaterial;

}