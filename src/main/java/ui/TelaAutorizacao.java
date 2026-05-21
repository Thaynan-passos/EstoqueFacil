package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class TelaAutorizacao extends JFrame {

    private final Color SIDEBAR_BG = new Color(15, 23, 42);
    private final Color SIDEBAR_HOVER = new Color(30, 41, 59);
    private final Color PRIMARY_BLUE = new Color(37, 99, 235);
    private final Color BG = new Color(248, 250, 252);
    private final Color WHITE = Color.WHITE;
    private final Color BORDER = new Color(226, 232, 240);
    private final Color TEXT_DARK = new Color(15, 23, 42);
    private final Color TEXT_MUTED = new Color(100, 116, 139);
    private final Color SUCCESS = new Color(22, 163, 74);
    private final Color DANGER = new Color(220, 38, 38);

    private final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 24);
    private final Font SUBTITLE_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private final Font MENU_FONT = new Font("Segoe UI", Font.BOLD, 15);
    private final Font TABLE_FONT = new Font("Segoe UI", Font.PLAIN, 13);
    private final Font HEADER_FONT = new Font("Segoe UI", Font.BOLD, 13);
    private final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 12);

    private JTable tabela;

    public TelaAutorizacao() {
        configurarJanela();
        montarLayout();
    }

    private void configurarJanela() {
        setTitle("Aprovação de Requisições");
        setSize(1450, 850);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    private void montarLayout() {
        JPanel principal = new JPanel(new BorderLayout());
        principal.add(montarSidebar(), BorderLayout.WEST);
        principal.add(montarConteudo(), BorderLayout.CENTER);
        add(principal);
    }

    private JPanel montarSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(270, getHeight()));
        sidebar.setBackground(SIDEBAR_BG);
        sidebar.setLayout(new BorderLayout());

        JPanel topo = new JPanel();
        topo.setOpaque(false);
        topo.setLayout(new BoxLayout(topo, BoxLayout.Y_AXIS));
        topo.setBorder(new EmptyBorder(35, 28, 25, 20));

        JLabel titulo = new JLabel("Estoque Fácil");
        titulo.setForeground(WHITE);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));

        JLabel subtitulo = new JLabel("Painel Administrativo");
        subtitulo.setForeground(new Color(148, 163, 184));
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        topo.add(titulo);
        topo.add(Box.createVerticalStrut(5));
        topo.add(subtitulo);

        JPanel menu = new JPanel();
        menu.setOpaque(false);
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.setBorder(new EmptyBorder(20, 16, 20, 16));

        menu.add(criarBotaoMenu("Dashboard", false));
        menu.add(Box.createVerticalStrut(10));
        menu.add(criarBotaoMenu("Solicitações", false));
        menu.add(Box.createVerticalStrut(10));
        menu.add(criarBotaoMenu("Aprovações Pendentes", true));
        menu.add(Box.createVerticalStrut(10));
        menu.add(criarBotaoMenu("Histórico", false));
        menu.add(Box.createVerticalStrut(10));
        menu.add(criarBotaoMenu("Relatórios", false));

        sidebar.add(topo, BorderLayout.NORTH);
        sidebar.add(menu, BorderLayout.CENTER);

        return sidebar;
    }

    private JButton criarBotaoMenu(String texto, boolean ativo) {
        JButton botao = new JButton(texto);
        botao.setHorizontalAlignment(SwingConstants.LEFT);
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setFont(MENU_FONT);
        botao.setPreferredSize(new Dimension(220, 48));
        botao.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));
        botao.setBorder(new EmptyBorder(0, 18, 0, 0));

        if (ativo) {
            botao.setBackground(PRIMARY_BLUE);
            botao.setForeground(WHITE);
        } else {
            botao.setBackground(SIDEBAR_BG);
            botao.setForeground(new Color(203, 213, 225));
        }

        botao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!ativo) {
                    botao.setBackground(SIDEBAR_HOVER);
                    botao.setForeground(WHITE);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!ativo) {
                    botao.setBackground(SIDEBAR_BG);
                    botao.setForeground(new Color(203, 213, 225));
                }
            }
        });

        return botao;
    }

    private JPanel montarConteudo() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(BG);
        painel.add(montarHeader(), BorderLayout.NORTH);
        painel.add(montarTabela(), BorderLayout.CENTER);
        return painel;
    }

    private JPanel montarHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(WHITE);
        header.setBorder(new CompoundBorder(
                new MatteBorder(0, 0, 1, 0, BORDER),
                new EmptyBorder(28, 32, 28, 32)
        ));

        JPanel tituloPanel = new JPanel();
        tituloPanel.setOpaque(false);
        tituloPanel.setLayout(new BoxLayout(tituloPanel, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("Aprovação de Requisições");
        titulo.setFont(TITLE_FONT);
        titulo.setForeground(TEXT_DARK);

        JLabel subtitulo = new JLabel("Gerencie solicitações pendentes");
        subtitulo.setFont(SUBTITLE_FONT);
        subtitulo.setForeground(TEXT_MUTED);

        tituloPanel.add(titulo);
        tituloPanel.add(Box.createVerticalStrut(5));
        tituloPanel.add(subtitulo);

        JPanel usuarioPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        usuarioPanel.setOpaque(false);

        JPanel avatar = new JPanel();
        avatar.setPreferredSize(new Dimension(42, 42));
        avatar.setBackground(PRIMARY_BLUE);

        JLabel iniciais = new JLabel("JS");
        iniciais.setForeground(WHITE);
        iniciais.setFont(new Font("Segoe UI", Font.BOLD, 14));

        avatar.add(iniciais);

        JPanel dadosUsuario = new JPanel();
        dadosUsuario.setOpaque(false);
        dadosUsuario.setLayout(new BoxLayout(dadosUsuario, BoxLayout.Y_AXIS));

        JLabel nome = new JLabel("João Silva");
        nome.setFont(new Font("Segoe UI", Font.BOLD, 14));
        nome.setForeground(TEXT_DARK);

        JLabel cargo = new JLabel("Gerente");
        cargo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        cargo.setForeground(TEXT_MUTED);

        dadosUsuario.add(nome);
        dadosUsuario.add(cargo);

        usuarioPanel.add(avatar);
        usuarioPanel.add(dadosUsuario);

        header.add(tituloPanel, BorderLayout.WEST);
        header.add(usuarioPanel, BorderLayout.EAST);

        return header;
    }

    private JPanel montarTabela() {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(BG);
        wrapper.setBorder(new EmptyBorder(30, 32, 32, 32));

        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(WHITE);
        card.setBorder(new CompoundBorder(
                new LineBorder(BORDER, 1, true),
                new EmptyBorder(0, 0, 0, 0)
        ));

        String[] colunas = {
            "Código",
            "Solicitante",
            "Setor",
            "Produto",
            "Quantidade",
            "Data",
            "Status",
            "Ações"
        };

        Object[][] dados = {
            {"REQ-001", "Carlos", "TI", "Notebook Dell", "2", "15/05/2026", "Pendente", ""},
            {"REQ-002", "Ana", "RH", "Cadeira Ergonômica", "5", "16/05/2026", "Pendente", ""},
            {"REQ-003", "Pedro", "Financeiro", "Monitor LG", "3", "16/05/2026", "Pendente", ""},
            {"REQ-004", "Marina", "Marketing", "Mouse Logitech", "10", "17/05/2026", "Pendente", ""},
            {"REQ-005", "Lucas", "Compras", "Teclado Mecânico", "4", "17/05/2026", "Pendente", ""}
        };

        DefaultTableModel model = new DefaultTableModel(dados, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 7;
            }
        };

        tabela = new JTable(model);
        estilizarTabela();

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(WHITE);

        card.add(scroll, BorderLayout.CENTER);
        wrapper.add(card, BorderLayout.CENTER);

        return wrapper;
    }

    private void estilizarTabela() {
        tabela.setRowHeight(58);
        tabela.setFont(TABLE_FONT);
        tabela.setShowGrid(false);
        tabela.setShowVerticalLines(true);
        tabela.setGridColor(BORDER);
        tabela.setSelectionBackground(new Color(239, 246, 255));
        tabela.setSelectionForeground(TEXT_DARK);
        tabela.setIntercellSpacing(new Dimension(0, 0));
        tabela.setForeground(TEXT_DARK);
        tabela.setBackground(WHITE);
        tabela.setFocusable(false);

        tabela.getTableHeader().setFont(HEADER_FONT);
        tabela.getTableHeader().setBackground(new Color(248, 250, 252));
        tabela.getTableHeader().setForeground(TEXT_DARK);
        tabela.getTableHeader().setBorder(new MatteBorder(0, 0, 1, 0, BORDER));
        tabela.getTableHeader().setPreferredSize(new Dimension(0, 45));

        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < tabela.getColumnCount(); i++) {
            tabela.getColumnModel().getColumn(i).setCellRenderer(centralizado);
        }

        tabela.getColumnModel().getColumn(6).setCellRenderer(new StatusRenderer());
        tabela.getColumnModel().getColumn(7).setCellRenderer(new PainelBotoesRenderer());
        tabela.getColumnModel().getColumn(7).setCellEditor(new PainelBotoesEditor());
        tabela.getColumnModel().getColumn(7).setPreferredWidth(220);
    }

    class StatusRenderer extends DefaultTableCellRenderer {
        public StatusRenderer() {
            setHorizontalAlignment(SwingConstants.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel label = new JLabel(value.toString());
            label.setOpaque(true);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(new Font("Segoe UI", Font.BOLD, 12));
            label.setBorder(new EmptyBorder(8, 12, 8, 12));
            label.setForeground(new Color(146, 64, 14));
            label.setBackground(new Color(254, 249, 195));
            return label;
        }
    }

    class PainelBotoesRenderer extends JPanel implements TableCellRenderer {
        private final JButton aprovar = new JButton("Aprovar");
        private final JButton rejeitar = new JButton("Rejeitar");

        public PainelBotoesRenderer() {
            setLayout(new FlowLayout(FlowLayout.CENTER, 8, 8));
            setBackground(WHITE);
            estilizarBotao(aprovar, SUCCESS);
            estilizarBotao(rejeitar, DANGER);
            add(aprovar);
            add(rejeitar);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    class PainelBotoesEditor extends AbstractCellEditor implements TableCellEditor {
        private final JPanel painel;

        public PainelBotoesEditor() {
            painel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 8));
            painel.setBackground(WHITE);

            JButton aprovar = new JButton("Aprovar");
            JButton rejeitar = new JButton("Rejeitar");

            estilizarBotao(aprovar, SUCCESS);
            estilizarBotao(rejeitar, DANGER);

            aprovar.addActionListener(e -> {
                int linha = tabela.getSelectedRow();
                fireEditingStopped(); 
                
                if (linha != -1) {
                    DefaultTableModel model = (DefaultTableModel) tabela.getModel();
                    model.removeRow(linha);
                    
                    // Verifica se a tabela ficou vazia
                    if (model.getRowCount() == 0) {
                        JOptionPane.showMessageDialog(null, "Todas as solicitações foram processadas!\nNão há nenhuma autorização pendente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Requisição aprovada com sucesso!");
                    }
                }
            });

            rejeitar.addActionListener(e -> {
                int linha = tabela.getSelectedRow();
                fireEditingStopped();
                
                if (linha != -1) {
                    DefaultTableModel model = (DefaultTableModel) tabela.getModel();
                    model.removeRow(linha);
                    
                    if (model.getRowCount() == 0) {
                        JOptionPane.showMessageDialog(null, "Todas as solicitações foram processadas!\nNão há nenhuma autorização pendente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Requisição rejeitada com sucesso!");
                    }
                }
            });

            painel.add(aprovar);
            painel.add(rejeitar);
        }

        @Override
        public Object getCellEditorValue() {
            return "";
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return painel;
        }
    }
    private void estilizarBotao(JButton botao, Color cor) {
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setForeground(WHITE);
        botao.setBackground(cor);
        botao.setFont(BUTTON_FONT);
        botao.setPreferredSize(new Dimension(90, 32));
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new TelaAutorizacao().setVisible(true);
        });
    }
} 