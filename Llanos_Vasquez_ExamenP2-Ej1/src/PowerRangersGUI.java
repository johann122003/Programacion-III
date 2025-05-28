import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PowerRangersGUI {
    private JPanel pGeneral;
    private JTextField textField1;  // ID
    private JTextField textField2;  // NombreReal
    private JComboBox<String> comboBox1;  // TipoDePoder
    private JComboBox<String> comboBox2;  // NivelDeEntrenamiento
    private JComboBox<String> comboBox3;  // BaseSecreta (para filtrar)
    private JButton agregarPowerRangerButton;
    private JButton buscarPorIDButton;
    private JButton filtrarYOrdenarButton;
    private JTable table1;
    private JTextArea textArea1;

    private ListaPowerRanger lista;

    public PowerRangersGUI() {
        lista = new ListaPowerRanger();

        agregarPowerRangerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(textField1.getText().trim());
                    String nombre = textField2.getText().trim();  // solo texto
                    if(nombre.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío.");
                        return;
                    }
                    String tipoPoder = (String) comboBox1.getSelectedItem();
                    int nivelEntrenamiento = Integer.parseInt((String) comboBox2.getSelectedItem());
                    String baseSecreta = (String) comboBox3.getSelectedItem();

                    PowerRanger p = new PowerRanger(id, nombre, tipoPoder, nivelEntrenamiento, baseSecreta);
                    boolean agregado = lista.insertar(p);
                    if (agregado) {
                        JOptionPane.showMessageDialog(null, "Power Ranger agregado correctamente.");
                        actualizarTabla(lista.listar());
                        limpiarCampos();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error: Ya existe un Power Ranger con ese ID.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID y Nivel de Entrenamiento deben ser números válidos.");
                }
            }
        });


        buscarPorIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(textField1.getText());
                    PowerRanger encontrado = lista.buscar(id);
                    if (encontrado != null) {
                        textField2.setText(encontrado.getNombreReal());
                        comboBox1.setSelectedItem(encontrado.getTipoDePoder());
                        comboBox2.setSelectedItem(String.valueOf(encontrado.getNivelDelEntrenamiento()));
                        comboBox3.setSelectedItem(encontrado.getBaseSecreta());
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró Power Ranger con ese ID.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un ID válido para buscar.");
                }
            }
        });

        filtrarYOrdenarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String baseSeleccionada = (String) comboBox3.getSelectedItem();
                java.util.List<PowerRanger> filtrados = lista.filtrarPorBaseNoPertenece(baseSeleccionada);
                lista.ordenarBurbuja(filtrados);
                actualizarTabla(filtrados);

                // Contar por tipo de poder (usando todos los tipos del comboBox1)
                int n = comboBox1.getItemCount();
                String[] tipos = new String[n];
                for (int i = 0; i < n; i++) {
                    tipos[i] = comboBox1.getItemAt(i);
                }
                int[] conteos = lista.contarPorTipoDePoder(tipos);

                // Mostrar conteos en textArea1
                StringBuilder sb = new StringBuilder();
                sb.append("Conteo Recursivo por tipo de Poder:\n");
                for (int i = 0; i < tipos.length; i++) {
                    sb.append(tipos[i]).append(": ").append(conteos[i]).append("\n");
                }
                textArea1.setText(sb.toString());
            }
        });
    }

    private void actualizarTabla(java.util.List<PowerRanger> listaParaMostrar) {
        String[] columnas = {"ID", "NombreReal", "TipoDePoder", "NivelDeEntrenamiento", "BaseSecreta"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        for (PowerRanger p : listaParaMostrar) {
            Object[] fila = {
                    p.getID(),
                    p.getNombreReal(),
                    p.getTipoDePoder(),
                    p.getNivelDelEntrenamiento(),
                    p.getBaseSecreta()
            };
            modelo.addRow(fila);
        }
        table1.setModel(modelo);
    }

    private void limpiarCampos() {
        textField1.setText("");
        textField2.setText("");
        comboBox1.setSelectedIndex(0);
        comboBox2.setSelectedIndex(0);
        comboBox3.setSelectedIndex(0);
    }

    public JPanel getpGeneral() {
        return pGeneral;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Registro de Power Ranger");
        PowerRangersGUI gui = new PowerRangersGUI();
        frame.setContentPane(gui.getpGeneral());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}

