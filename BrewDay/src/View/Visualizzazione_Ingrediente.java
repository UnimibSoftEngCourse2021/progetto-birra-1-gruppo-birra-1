package View;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import controller.ControllerIngredienti;
import model.Ingrediente;

import org.eclipse.swt.widgets.TableColumn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;

public class Visualizzazione_Ingrediente {

	protected Shell shell;
	private Table table;
	private ControllerIngredienti controller;
		
	public Visualizzazione_Ingrediente(ControllerIngredienti c) {
		controller = c;
	}
	
	public Visualizzazione_Ingrediente() {
		controller = null;
	}
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Visualizzazione_Ingrediente window = new Visualizzazione_Ingrediente();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(625, 293);
		shell.setText("SWT Application");
		ArrayList<Ingrediente> ListaIngredienti = controller.getIngredienti();
	    for (Ingrediente i: ListaIngredienti ) {
	    	System.out.println(i.toString());
	    }
		shell.setLayout(null);

	        TableViewer viewer = new TableViewer(shell);
	        Table table_1 = viewer.getTable();
	        table_1.setBounds(0, 0, 503, 261);
	        viewer.getTable().setHeaderVisible(true);
	        viewer.getTable().setLinesVisible(true);
	        viewer.setContentProvider(new ArrayContentProvider());

	        TableColumn column = new TableColumn(viewer.getTable(), SWT.NONE);
	        column.setText("Ingrediente");
	        column.setWidth(100);
	        TableViewerColumn firstNameCol = new TableViewerColumn(viewer, column);
	        firstNameCol.setLabelProvider(new ColumnLabelProvider(){

	            @Override
	            public String getText(Object element) {
	                Ingrediente p = (Ingrediente)element;

	                return p.getNome();
	            }

	        });

	        column = new TableColumn(viewer.getTable(), SWT.NONE);
	        column.setText("DisponibilitÓ");
	        column.setWidth(100);
	        TableViewerColumn lastNameCol = new TableViewerColumn(viewer, column);
	        lastNameCol.setLabelProvider(new ColumnLabelProvider(){

	            @Override
	            public String getText(Object element) {
	               Ingrediente p = (Ingrediente)element;

	                return ""+ p.getDisponibilita();
	            }

	        });

	        column = new TableColumn(viewer.getTable(), SWT.NONE);
	        column.setText("UnitÓ Misura");
	        column.setWidth(100);
	        TableViewerColumn unitNameCol = new TableViewerColumn(viewer, column);
	        unitNameCol.setLabelProvider(new ColumnLabelProvider(){

	            @Override
	            public String getText(Object element) {
	               Ingrediente p = (Ingrediente)element;

	                return ""+ p.getUnitaMisura();
	            }

	        });



	        column = new TableColumn(viewer.getTable(), SWT.NONE);
	        column.setText("");
	        column.setWidth(100);
	        TableViewerColumn actionsNameCol = new TableViewerColumn(viewer, column);
	        actionsNameCol.setLabelProvider(new ColumnLabelProvider(){
	            //make sure you dispose these buttons when viewer input changes
	            Map<Object, Button> buttons = new HashMap<Object, Button>();


	            @Override
	            public void update(ViewerCell cell) {

	                TableItem item = (TableItem) cell.getItem();
	                Button button;
	                if(buttons.containsKey(cell.getElement()))
	                {
	                    button = buttons.get(cell.getElement());
	                }
	                else
	                {
	                    button = new Button((Composite) cell.getViewerRow().getControl(),SWT.NONE);
	                    button.setText("Remove"+((Ingrediente)cell.getElement()).getNome());
	                    Ingrediente p = (Ingrediente)cell.getElement();
	                    button.addSelectionListener(new SelectionAdapter() {
	            		    @Override
	            		    public void widgetSelected(SelectionEvent e) {
	            		    	
	            		    	
	            		    	/**/
	            		    	
	            		    	
	            		    	System.out.println(""+button.toString());
	            		    	controller.rimuoviIngrediente(p.getIdIngrediente() );
	            		    }
	            		});
	                    buttons.put(cell.getElement(), button);
	                }
	                TableEditor editor = new TableEditor(item.getParent());
	                editor.grabHorizontal  = true;
	                editor.grabVertical = true;
	                editor.setEditor(button , item, cell.getColumnIndex());
	                editor.layout();
	            }

	        });
	        
	        column = new TableColumn(viewer.getTable(), SWT.NONE);
	        column.setText("");
	        column.setWidth(100);
	        TableViewerColumn action2NameCol = new TableViewerColumn(viewer, column);
	        action2NameCol.setLabelProvider(new ColumnLabelProvider(){
	            //make sure you dispose these buttons when viewer input changes
	            Map<Object, Button> buttons = new HashMap<Object, Button>();


	            @Override
	            public void update(ViewerCell cell) {

	                TableItem item = (TableItem) cell.getItem();
	                Button button;
	                if(buttons.containsKey(cell.getElement()))
	                {
	                    button = buttons.get(cell.getElement());
	                }
	                else
	                {
	                    button = new Button((Composite) cell.getViewerRow().getControl(),SWT.NONE);
	                    button.setText("Aggiorna");
	                    button.addSelectionListener(new SelectionAdapter() {
	            		    @Override
	            		    public void widgetSelected(SelectionEvent e) {
	            		    	Ingrediente p = (Ingrediente)cell.getElement();
	            		    	controller.aggiornaIngrediente(p.getIdIngrediente(), p.getNome(), p.getDisponibilita(), p.getUnitaMisura());
	            		    }
	            		});
	                    buttons.put(cell.getElement(), button);
	                }
	                TableEditor editor = new TableEditor(item.getParent());
	                editor.grabHorizontal  = true;
	                editor.grabVertical = true;
	                editor.setEditor(button , item, cell.getColumnIndex());
	                editor.layout();
	            }

	        });
	        
	        

		viewer.setInput(ListaIngredienti);
		
		
		
		
		

	}
}
