package bean;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import com.mysql.cj.jdbc.Driver;
import com.orsonpdf.PDFDocument;
import com.orsonpdf.PDFGraphics2D;
import com.orsonpdf.Page;
import java.awt.Rectangle;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.util.Rotation;
import org.jfree.data.general.DefaultPieDataset;

@ManagedBean
public class Grafico {

    public void graficar() {

        try {
            Driver dvr = new com.mysql.cj.jdbc.Driver();
            DriverManager.deregisterDriver(dvr);

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gaes2?user=Cediel&password=Cc7n5ZxgLY&useSSL=false");

            PreparedStatement ps = conn.prepareStatement("SELECT nombre, SUM(idTipoMaterial) AS total FROM material GROUP BY nombre");
            ResultSet rs = ps.executeQuery();

            DefaultPieDataset datos = new DefaultPieDataset();

            while (rs.next()) {
                datos.setValue(rs.getString("nombre"), rs.getInt("total"));
            }

            JFreeChart grafico = ChartFactory.createPieChart3D("Artículos por nombre", datos, true, true, false);

            HttpServletResponse resp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            resp.setContentType("image/png");
            

            try (ServletOutputStream stream = resp.getOutputStream()) {
                PiePlot3D plot = (PiePlot3D) grafico.getPlot();
                plot.setStartAngle(90);
                plot.setDirection(Rotation.CLOCKWISE);
                plot.setForegroundAlpha(0.4f);

                File p3D = new File("C:\\grafico\\graphic.png");
                ChartUtils.saveChartAsPNG(p3D, grafico, 600, 400);

                ChartUtils.writeChartAsPNG(stream, grafico, 600, 400);

                

                stream.flush();
                stream.close();
            }

            FacesContext.getCurrentInstance().responseComplete();
        } catch (SQLException | IOException ex) {
            System.out.println("Error creando Gráfico");
        }

    }

 

}
