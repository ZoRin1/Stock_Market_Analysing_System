package uiControl;

import java.util.List;

import presentation.Group1;
import presentation.Group3;
import presentation.Group5;
import presentation.GroupFather;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class tableControl<S> {
	private TableView<S> table; //
	private final ObservableList data; //
	private List<TableColumn> colum;
	private Group1 group1;

	public tableControl(List<TableColumn> colum){
		this.colum = colum;
		table = new TableView<S>();
	    table.setEditable(true);
	    table.setStyle("-fx-font-weight:900;-fx-opacity:0.6;-fx-font-size:20px;");  //设置表格透明,字体大小，加粗
	    table.setTableMenuButtonVisible(true);
		data = FXCollections.observableArrayList();
		table.setItems(data);
	    for(int i = 0 ; i < colum.size();i++){
	    	table.getColumns().add(colum.get(i));
	    }
	}
	public tableControl(List<TableColumn> colum,Group1 group){
		group1=group;
		this.colum = colum;
		table = new TableView<S>();
	    table.setEditable(true);
	    table.setStyle("-fx-font-weight:900;-fx-opacity:0.6;-fx-font-size:20px;"); //设置表格透明,字体大小，加粗
	    table.setTableMenuButtonVisible(true);
		data = FXCollections.observableArrayList();
		for (int i = 0; i < colum.size(); i++) {
			colum.get(i).setCellFactory(new TaskCellFactory());
			colum.get(i).setEditable(false);
		}
		table.setItems(data);
	    for(int i = 0 ; i < colum.size();i++){
	    	table.getColumns().add(colum.get(i));
	    }
	}
	private void init(){
		
	}
	public TableView getTable(){
		return this.table;
	}
	public void setX(double x){
		table.setLayoutX(x);
	}
	public void setY(double y){
		table.setLayoutY(y);
	}
	public void setWidth(double width){
		table.setMinWidth(width);
	}
	public void setHeight(double height){
		table.setMinHeight(height);
	}
	public void addData(Object obj){
		data.add(obj);
	}
	public void clear(){
		data.clear();
	}
	private class TaskCellFactory implements Callback<TableColumn,TableCell>{

		/* (non-Javadoc)
		 * @see javafx.util.Callback#call(java.lang.Object)
		 */
		public TableCell call(TableColumn param) {
			// TODO Auto-generated method stub
			final TextFieldTableCell cell = new TextFieldTableCell<>();
			cell.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					// TODO Auto-generated method stub
					if(arg0.getClickCount()==2){
						Group root=group1.getroot();
						WaitLoading waitLoading=new WaitLoading();
						root.getChildren().add(waitLoading);
						
						new Thread(new Runnable() {
							 	@Override public void run() {						
									table1DataPO table1DataPO=(table1DataPO)data.get(cell.getTableRow().getIndex());
									GroupFather group3=new Group3(table1DataPO.getName(), table1DataPO.getSign());
								 Platform.runLater(new Runnable() {
									 @Override public void run() {
										 root.getChildren().remove(group1.getGroupFather().getGroup());
										 group1.setGroupFather(group3);
										root.getChildren().add(group3.getGroup());
										 root.getChildren().remove(waitLoading);
									 }
								 });
							 	}
						}).start();	
				
					}
				}
			});
			return cell;
		}
		
	}
}
