package ventavehiculos.model;

import javax.swing.*;
import java.time.Duration;
import java.util.List;

public class MonitoreoInventario implements Runnable{

        private Inventario inventario;

        public void MonitorInventario(Inventario inventario) {
            this.inventario = inventario;
        }

    public MonitoreoInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    @Override
        public void run() {
            while (true) {
                synchronized (inventario) {
                    List<Vehiculo> disponibles = inventario.listarVehiculosDisponibles();
                    if (disponibles.size() < 5) {
                        JOptionPane.showMessageDialog(null,"⚠️ Alerta: Inventario bajo. Autos disponibles: " + disponibles.size());
                    }
                }
                try {
                    Thread.sleep(Duration.ofSeconds(60)); //
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }
