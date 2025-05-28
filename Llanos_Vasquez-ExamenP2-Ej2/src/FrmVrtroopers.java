import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmVrtroopers {
    private JPanel pGeneral;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox comboBoxTecnologiaEspecial;
    private JComboBox comboBox2NivelVirtualidad;
    private JComboBox comboBox3DimensiónOperativa;
    private JTable table1;
    private JButton busquedaBinButton;
    private JButton registrarSoldadoButton;
    private JTextArea textArea1;
    private JButton calcularNumeroDeSoldadosButton;
    private JButton crearNuevaListaButton;
    private JButton ordenarPorNivelButton;

    private ListaSoldados listaOriginal = new ListaSoldados();

    public FrmVrtroopers() {

        registrarSoldadoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int ID = Integer.parseInt(textField1.getText().trim());
                    String alias = textField2.getText().trim();
                    String tecnologia = comboBoxTecnologiaEspecial.getSelectedItem().toString();
                    int nivel = Integer.parseInt(comboBox2NivelVirtualidad.getSelectedItem().toString());
                    String dimension = comboBox3DimensiónOperativa.getSelectedItem().toString();

                    SoldadoVirtual s = new SoldadoVirtual(ID, alias, tecnologia, nivel, dimension);
                    if (listaOriginal.insertarOrdenado(s)) {
                        mostrarEnTabla(listaOriginal);
                        JOptionPane.showMessageDialog(null, "Soldado registrado con éxito.");
                        textField1.setText("");
                        textField2.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "El ID ya existe. Intente con otro.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Datos inválidos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        busquedaBinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int ID = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID a buscar:"));
                    Nodo encontrado = listaOriginal.busquedaBinaria(ID);
                    if (encontrado != null) {
                        SoldadoVirtual s = encontrado.dato;
                        textField1.setText(String.valueOf(s.ID));
                        textField2.setText(s.Alias);
                        comboBoxTecnologiaEspecial.setSelectedItem(s.TecnologiaEspecial);
                        comboBox2NivelVirtualidad.setSelectedItem(String.valueOf(s.NivelVirtualidad));
                        comboBox3DimensiónOperativa.setSelectedItem(s.DimensionOperativa);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró el soldado con ese ID.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al buscar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        crearNuevaListaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tecnologia = comboBoxTecnologiaEspecial.getSelectedItem().toString();
                ListaSoldados filtrada = listaOriginal.filtrarPorTecnologia(tecnologia);
                mostrarEnTabla(filtrada);
            }
        });

        ordenarPorNivelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaOriginal.ordenarPorNivelDescendente();
                mostrarEnTabla(listaOriginal);
            }
        });

        calcularNumeroDeSoldadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder sb = new StringBuilder();
                String[] dimensiones = {
                        "Mundo Virtual",
                        "Torre Ziktor",
                        "Red Mental",
                        "Base Satelital",
                        "Cámara de Enlace"
                };
                for (String dim : dimensiones) {
                    int count = listaOriginal.contarPorDimensionRec(dim);
                    sb.append(dim).append(": ").append(count).append("\n");
                }
                textArea1.setText(sb.toString());
            }
        });
    }

    private void mostrarEnTabla(ListaSoldados lista) {
        String[] columnas = {"ID", "Alias", "Tecnología", "Nivel", "Dimensión"};
        Nodo[] nodos = lista.toArray();
        String[][] datos = new String[nodos.length][5];

        for (int i = 0; i < nodos.length; i++) {
            SoldadoVirtual s = nodos[i].dato;
            datos[i][0] = String.valueOf(s.ID);
            datos[i][1] = s.Alias;
            datos[i][2] = s.TecnologiaEspecial;
            datos[i][3] = String.valueOf(s.NivelVirtualidad);
            datos[i][4] = s.DimensionOperativa;
        }

        table1.setModel(new javax.swing.table.DefaultTableModel(datos, columnas));
    }

    public JPanel getPanel() {
        return pGeneral;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("FrmVrtroopers");
        frame.setContentPane(new FrmVrtroopers().pGeneral);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
