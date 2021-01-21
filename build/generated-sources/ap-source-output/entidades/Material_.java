package entidades;

import entidades.CitaMaterial;
import entidades.Inventario;
import entidades.MaterialCantidad;
import entidades.Tipomaterial;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-04T07:38:09")
@StaticMetamodel(Material.class)
public class Material_ { 

    public static volatile SingularAttribute<Material, String> descripcion;
    public static volatile ListAttribute<Material, MaterialCantidad> materialCantidadList;
    public static volatile SingularAttribute<Material, Integer> estado;
    public static volatile ListAttribute<Material, CitaMaterial> citaMaterialList;
    public static volatile ListAttribute<Material, Inventario> inventarioList;
    public static volatile SingularAttribute<Material, Integer> idMaterial;
    public static volatile SingularAttribute<Material, String> nombre;
    public static volatile SingularAttribute<Material, Tipomaterial> idTipoMaterial;

}